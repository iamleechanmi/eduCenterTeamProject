package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.dto.TeacherAttendanceDTO;
import com.test.dto.TeacherStudentListDTO;

import oracle.jdbc.OracleTypes;
/**
 * 
 * @author 윤지봉
 *
 */
public class TeacherAttendanceDAO {
	
/**
 * 
 * @param num 학생 번호
 * @param year 년도
 * @return attendance //출석 결과
 */
	public static ArrayList<TeacherAttendanceDTO> attendance(int num, int year) { 
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs =null;
		
		try {

			
			conn = DBUtil.open();
			
				
			String sql = "{ call procteastuattendanceyearfind(?,?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setInt(1, num);
			stat.setInt(2, year);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(3);
			
			
			
			return getlist(rs);
			
//			while(rs.next()) {
//				System.out.println(rs.getString(1)); 번호
//				System.out.println(rs.getString(2)); 이름
//				System.out.println(rs.getString(3)); 상태
//				System.out.println(rs.getString(4)); 횟수
//			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("test.m2()");
			e.printStackTrace();
		}
		
		return null;
		

	}

	
	/**
	 * 
	 * @param num 학생번호
	 * @param year 년도
	 * @param month 월
	 * @return attendance //출석 결과
	 */
	public static ArrayList<TeacherAttendanceDTO> attendance(int num, int year, int month) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs =null;
		
		try {

			
			conn = DBUtil.open();
			
				
			String sql = "{ call procteastuattendancemonthfind(?,?,?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setInt(1, num);
			stat.setInt(2, year);
			stat.setInt(3, month);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(4);
			

			
			
			return getlist(rs);

			

		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.attendance()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @param num 학생번호
	 * @param year 년
	 * @param month 월
	 * @param date 일
	 * @return attendance //출석 결과
	 */
	public static ArrayList<TeacherAttendanceDTO> attendance(int num, int year, int month, int date) {
	
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs =null;
		
		try {

			
			conn = DBUtil.open();
			
				
			String sql = "{ call procteastuattendancedayfind(?,?,?,?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setInt(1, num);
			stat.setInt(2, year);
			stat.setInt(3, month);
			stat.setInt(4, date);
			
			stat.registerOutParameter(5, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(5);
			

			
			
			return getlist(rs);


			

		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.attendance()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
	 * 출석 상태 이름별로 모아주는 메서드입니다
	 * @param rs
	 * @return ArrayList<TeacherAttendanceDTO> list
	 */
	private static ArrayList<TeacherAttendanceDTO> getlist(ResultSet rs) { 
		
		try {

			ArrayList<TeacherAttendanceDTO> list = new ArrayList<TeacherAttendanceDTO>();
			
			while(rs.next()) {
				
				TeacherAttendanceDTO dto = getcount(list, rs.getString(1));
				
				if(dto == null) {
					dto = new TeacherAttendanceDTO(rs.getString(1), rs.getString(2),"0","0","0","0","0","0");
						list.add(dto);
				}
				
				if( rs.getString(3).equals("출석")) {
					dto.setAttend(rs.getString(4));
				}
				if( rs.getString(3).equals("외출")) {
					dto.setOut(rs.getString(4));
				}	
				if( rs.getString(3).equals("지각")) {
					dto.setLate(rs.getString(4));
				}
				if( rs.getString(3).equals("병가")) {
					dto.setSick(rs.getString(4));
				}
				if( rs.getString(3).equals("조퇴")) {
					dto.setEarly(rs.getString(4));
				}
				if( rs.getString(3).equals("결석")) {
					dto.setAbsense(rs.getString(4));
				}
				
			}
			
			rs.close();
			
			
			return list;
					


		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.getlist()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * getlist() 연관, 이름 중복 검사해서 누적여부 확인하는 메서드
	 * @param list 학생 출석을 담는 Arraylist
	 * @param string 학생이름
	 * @return TeacherAttendanceDTO dto
	 */
	private static TeacherAttendanceDTO getcount(ArrayList<TeacherAttendanceDTO> list, String string) {

		for (TeacherAttendanceDTO dto : list) {
			if (dto.getSeq().equals(string)) {
				
				return dto;
					
			}
		}
		return null;
	}



	/**
	 * 과목번호를 구하는 메서드입니다.
	 * @param seq 교사번호
	 * @param state 상태
	 * @return subjectseq 과목번호
	 */
	public static String getsubjectseq(int seq, String state) { // 교사 번호, 상태로 과목번호 구하기
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "select openSubjectSeq from tblteacherrecord where teacherseq = ? and status = ? and rownum = 1";
			
			stat = conn.prepareStatement(sql);
			
			stat.setInt(1, seq);
			stat.setString(2, state);
			
			rs = stat.executeQuery();
			
			String subjectseq = null;
			if(rs.next()) {
			subjectseq = rs.getString("openSubjectSeq");
			}
			return subjectseq;

		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.getsubjectseq()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 교사번호와 과목번호로 학생 목록 조회하는 메서드입니다.
	 * @param seq 교사번호
	 * @param subjectseq 과목번호
	 * @param state 상태
	 * @return list 학생 목록
	 */
	public static ArrayList<TeacherStudentListDTO> attendanceStudentList(int seq, String subjectseq, String state) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteastulistfind(?,?,?,?)}";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1,subjectseq);
			stat.setInt(2, seq);
			stat.setString(3, state);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(4); // 번호, 이름
			
			ArrayList<TeacherStudentListDTO> list = new ArrayList<TeacherStudentListDTO>();
			
			while (rs.next()) {
				
				TeacherStudentListDTO dto = new TeacherStudentListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setRegistrationDate(rs.getString("regist"));
				
				list.add(dto);
				
			}
			
			return list;
			

		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.attendanceStudentList()");
			e.printStackTrace();
		}
		
		return null;
	}




	/**
	 * 
	 * @param seq 학생번호
	 * @param year 년
	 * @return attendance //출석 결과
	 */
	public static ArrayList<TeacherAttendanceDTO> attendances(int seq, int year) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteastsequattendyearfind(?,?,?)}";

			stat = conn.prepareCall(sql);
			
			stat.setInt(1, year);
			stat.setInt(2,seq);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(3);
			
			return getlist(rs);
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.enclosing_method()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 
	 * @param seq 학생번호
	 * @param year 년
	 * @param month 월
	 * @return attendance //출석 결과
	 */
	public static ArrayList<TeacherAttendanceDTO> attendances(int seq, int year, int month) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteastsequattendmonthfind(?,?,?,?)}";

			stat = conn.prepareCall(sql);
			
			stat.setInt(1, year);
			stat.setInt(2, month);
			stat.setInt(3,seq);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(4);
			
			return getlist(rs);
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.enclosing_method()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 
	 * @param seq 학생번호
	 * @param year 년
	 * @param month 월
	 * @param date 일
	 * @return attendance //출석 결과
	 */
	public static ArrayList<TeacherAttendanceDTO> attendances(int seq, int year, int month, int date) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteastsequattenddayfind(?,?,?,?,?)}";

			stat = conn.prepareCall(sql);
			
			stat.setInt(1, year);
			stat.setInt(2, month);
			stat.setInt(3, date);			
			stat.setInt(4,seq);
			stat.registerOutParameter(5, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(5);
			
			return getlist(rs);
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.enclosing_method()");
			e.printStackTrace();
		}
		
		return null;
	}
}
