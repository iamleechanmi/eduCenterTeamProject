package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.OpenCourseDTO;
import com.test.dto.SignUpDTO;
import com.test.dto.StudentDTO;
import com.test.dto.VwOneStudentDTO;

import oracle.jdbc.OracleTypes;

/**
 * 교육생 관리에 대한 DAO 메서드를 모아놓은 클래스입니다.
 * @author 이청강
 */

public class StudentManagementDAO {

	private static Connection conn;
	private static Statement stat;
	private static PreparedStatement pstat;
	private static CallableStatement cstat;
	private static ResultSet rs;
	
	public StudentManagementDAO() {
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("StudentManagementDAO.StudentManagementDAO()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 교육생 정보를 등록하는 프로시저를 호출하는 메서드입니다.
	 * @param dto 교육생 정보가 들어간 객체
	 * @return 등록에 성공한 레코드의 개수(성공시 1, 실패시 0)
	 */
	public int add(StudentDTO dto) {

		try {

			String sql = "{ call procAddStudent(?,?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getName());
			cstat.setString(2, dto.getSsn());
			cstat.setString(3, dto.getTel());
			
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("StudentManagementDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 * 교육생의 정보를 DB에서 읽어온 뒤 ArrayList에 담아서 반환하는 메서드입니다.
	 * 읽어오는 교육생 정보는 교육생번호/이름/주민등록번호/전화번호/등록일입니다.
	 * @return StudentDTO 클래스의 객체를 요소로 갖는 ArrayList를 반환합니다.
	 */
	public ArrayList<StudentDTO> findBriefStudent() {
		
		try {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			
			String sql = "select seq, name, ssn, tel, to_char(registrationDate, 'yy/mm/dd') as regdate from tblStudent order by regdate desc";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setRegistrationDate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentManagementDAO.findBrief()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 진행 상태가 예정인, 개설 과정 정보를 담은 ArrayList를 반환하는 메서드입니다.
	 * @return 진행 상태가 예정인, 개설 과정 정보를 담은 OpenCourseDTO 객체를 담은 ArrayList를 반환합니다.
	 */
	public ArrayList<OpenCourseDTO> findExpectedCourse() {
		
		try {
			
			ArrayList<OpenCourseDTO> list = new ArrayList<OpenCourseDTO>();
			
			String sql = "select * from vwFindYetOpenCourse";
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				OpenCourseDTO dto = new OpenCourseDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setStartDate(rs.getString("startDate"));
				
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.findExpectedCourse()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 교육생을 개설 과정에 수강 등록하는 프로시저를 실행하는 메서드입니다.
	 * @param dto 프로시저에 매개변수로 들어갈 교육생 번호와 개설 과정 번호를 담은 객체입니다.
	 * @return 쿼리 실행에 성공한 행의 개수를 반환합니다. 실패할 경우 0이 반환됩니다.
	 */
	public int addSignUp(SignUpDTO dto) {

		try {

			String sql = "{ call procAddSignUp(?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getStudentSeq());
			cstat.setString(2, dto.getOpenCourseSeq());
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.addSignUp()");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	/**
	 * 교육생의 정보를 ArrayList에 담아 반환하는 메서드입니다.
	 * @param seq 특정 교육생의 정보만을 가져올 때 해당 교육생의 번호를 매개변수로 받습니다.
	 * @return 전체 교육생 혹은 특정 교육생의 정보에 해당하는 StudentDTO 객체를 담은 ArrayList를 반환합니다.
	 */
	public ArrayList<StudentDTO> findAllStudent(String seq) {
		
		try {

			String where = "";
			if (seq != null) {
				where = "where studentseq = " + seq;
			}

			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			
			String sql = "select * from vwFindAllStudent " + where + " order by studentRegDate desc";
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				
				dto.setSeq(rs.getString("studentSeq"));
				dto.setName(rs.getString("studentName"));
				dto.setSsn(rs.getString("studentSsn"));
				dto.setTel(rs.getString("studentTel"));
				dto.setRegistrationDate(rs.getString("studentregdate"));
				dto.setRegistrationCount(rs.getString("signupcount"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.findAllStudent()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 특정 교육생의 정보를 ArrayList에 담아 반환하는 메서드입니다. (사용되지 않은 메서드입니다.)
	 * @param seq 정보를 가져올 교육생의 번호를 매개 변수로 받습니다.
	 * @return 특정 교육생의 정보를 담은 VwOneStudentDTO의 ArrayList를 반환합니다.
	 */
	public ArrayList<VwOneStudentDTO> findOneStudent(String seq) {
		
		try {
			ArrayList<VwOneStudentDTO> list = new ArrayList<VwOneStudentDTO>();
			
			String sql = "{ call procFindStudent(?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				VwOneStudentDTO dto = new VwOneStudentDTO();
				dto.setSignUpSeq(rs.getString("signUpSeq"));
				dto.setSignUpStatus(rs.getString("signUpSTAUS"));
				dto.setSignUpEnddate(rs.getNString("signUpEnddate"));
				dto.setOpenCourseStartDate(rs.getString("openCourseStartDate"));
				dto.setOpenCourseEndDate(rs.getString("openCourseEndDate"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setClassroomName(rs.getString("classroomName"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.findOneStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생 정보 프로시저를 실행하는 메서드입니다.
	 * @param newDto 수정할 교육생의 번호를 매개변수로 받습니다.
	 * @return 쿼리 실행에 성공한 행의 개수를 반환합니다. 실패할 경우 0이 반환됩니다.
	 * 	단, 수정할 내용을 입력하지 않은 경우 음수를 반환합니다.
	 */
	public int editStudent(StudentDTO newDto) {
		
		try {

			int check = 0;
			int emptyCheck = 0;
			
			if (!newDto.getName().equals("")) {
				String sqlNameEdit = "{ call procReplaceStudentName(?,?) }";
				
				cstat = conn.prepareCall(sqlNameEdit);
				cstat.setString(1, newDto.getSeq());
				cstat.setString(2, newDto.getName());
				
				check += cstat.executeUpdate();
			} else {
				emptyCheck -= 1;
			}
			
			if (!newDto.getTel().equals("")) {
				String sqlTelEdit = "{ call procReplaceStudentTel(?,?) }";
				
				cstat = conn.prepareCall(sqlTelEdit);
				cstat.setString(1, newDto.getSeq());
				cstat.setString(2, newDto.getTel());
				
				check += cstat.executeUpdate();
			} else {
				emptyCheck -= 1;
			}
		
			
			return emptyCheck == -2 ? -1 : check;

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.editStudent()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교육생 정보 삭제 프로시저를 실행하는 메서드입니다.
	 * @param seqToDelete 삭제할 교육생의 번호를 매개변수로 받습니다.
	 * @return 쿼리 실행에 성공한 행의 개수를 반환합니다. 실패할 경우 0이 반환됩니다.
	 */
	public int deleteStudent(String seqToDelete) {
		
		try {

			String sql = "{ call procDeleteStudent(?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seqToDelete);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudentManagementDAO.deleteStudent()");
			e.printStackTrace();
		}
		
		return 0;
	}
	

}




















