package com.test.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.TeacherExamScoreDTO;
import com.test.dto.TeacherSeqDTO;
import com.test.dto.VwFindStudentGradeNotNullDTO;
import com.test.dto.VwFindStudentGradeNullDTO;

import oracle.jdbc.internal.OracleTypes;

/**
 * 성적관리 오라클 처리용 클래스 입니다.
 * (DataAccessObject)
 * @author 이대홍
 */


public class TeacherScoreDAO {
	
	//교사 번호 반환 
	/**
	 * 교사번호 반환 메소드입니다. 
	 * @param teacher TeacherSeqDTO
	 * @return TeacherSeqDTO teacher
	 */
	public int seq(TeacherSeqDTO teacher) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		conn = DBUtil.open();
		
		try {
			
			String sql = String.format("select seq from tblTeacher where name = '%s' and ssn = %s",teacher.getName(),teacher.getSsn());
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			rs.next();
		
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("TeacherScoreDAO_seq()");
			e.printStackTrace();
			System.out.println("에러확인 후 관리자에게 문의해 주세요");
			
		}
		
		return 0;
		
	}
	
	/**
	 * 최초학생성적 입력 리스트출력 메소드입니다.
	 * VwFindStudentGradeNull table
	 * @param  teacherNumber VwFindStudentGradeNullDTO
	 * @return VwFindStudentGradeNullDTO ScoreNullList
	 * @throws IOException BUfferdreader
	 */
	
	//최초 학생성적 입력 리스트 출력 
	public ArrayList<VwFindStudentGradeNullDTO> ScoreNullList(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		Connection conn =null;
		CallableStatement cstat =null;
		ResultSet rs = null;
		//교사번호 입력. 커서 반환 
		String number = teacherNumber+"";
		try {
			conn = DBUtil.open();
			/*
			String sql = String.format("select * from VWFINDSTUDENTGRADENULL "
					+ " where openSubjectseq in (select openSubjectseq "
					+ " from tblteacherrecord where "
					+ " teacherseq = %s and status= '진행')", number);
			*/

			String sql = "{ call procFindByTeacherGradeNull (?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1,number);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<VwFindStudentGradeNullDTO> list = new ArrayList<VwFindStudentGradeNullDTO>();
			//메뉴
			System.out.println("*진행중인 과정");
			
			while(rs.next()) {
				VwFindStudentGradeNullDTO dto = new VwFindStudentGradeNullDTO();
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setSignUpseq(rs.getString("signUpseq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setSubjectName(rs.getString("SubjectName"));
				dto.setStudentName(rs.getString("StudentName"));
				dto.setSsn(rs.getString("ssn"));
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setWrittenScore(rs.getString("writtenScore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				dto.setWhetherexam(rs.getString("whetherexam"));
				dto.setWhethergrade(rs.getString("whethergrade"));
				dto.setAttendancePoint(rs.getString("attendancePoint"));
				dto.setWrittenPoint(rs.getString("writtenPoint"));
				dto.setPracticalPoint(rs.getString("practicalPoint"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				//System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
				//		dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEnddate(),
				//		dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherexam());
				list.add(dto);
			}
						
			rs.close();
			cstat.close();
			conn.close();
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherScoreDAO_ScoreNullList()");
			e.printStackTrace();
			System.out.println("에러확인 후 관리자에서 문의해주세요.");

		}
		return null;
	}
	
	/**
	 * 최초학생성적 입력 메소드입니다.
	 * TeacherExamScore table
	 * @param score TeacherExamScoreDTO
	 * @return TeacherExamScoreDTO score
	 * 
	 */
	
	// 최초 성적입력.
	public int add(TeacherExamScoreDTO score) {
		// TODO Auto-generated method stub
		Connection conn =null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			String sql = " { call procAddScore(?,?,?,?,?) } ";
			cstat = conn.prepareCall(sql);
			cstat.setString(1,score.getSignUpSeq());
			cstat.setString(2,score.getOpenSubjectSeq());
			cstat.setString(3,score.getAttendanceScore());
			cstat.setString(4,score.getWrittenScore());
			cstat.setString(5,score.getPracticalScore());
			
			int result = cstat.executeUpdate();			

			cstat.close();
			conn.close();
			
			return result;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherScoreDAO_Add()");
			e.printStackTrace();
		}
		
		return 0;
	} // 최초 성적입력. 
	
	
	/**
	 * 기존에 입력된 학생 성적 메소드입니다.
	 * VwFindStudentGradeNotNull table
	 * @param teacherNumber 교사번호
	 * @param info 정보
	 * @return VwFindStudentGradeNotNullDTO
	 */
	
	
	//기존에 입력된 학생 성적 반환. 
	public ArrayList<VwFindStudentGradeNotNullDTO> ScoreNullNotList(int teacherNumber, String info) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		
		String where = "";
		//숫자 체크여부
		int count=0;
		for (int i = 0; i<info.length() ;i++) {
			if((int)info.charAt(i)>=48 && (int)info.charAt(i)<=57) {
				count ++ ;
			}
		}
		
		if (info.equals("")){
			where =info;
		} else if (count==info.length()) {
			where = "and examScoreSeq = " +info;
		} else if (!info.equals("")) {
			where = String.format(" and studentName = '%s'", info);
		} 
		
		//0 이면 문자열 다르면 숫자
	
		try {
			conn  =DBUtil.open();
			stat = conn.createStatement();
			String sql = String.format("select * from vwFindStudentGradeNotnull  where openSubjectSeq in "
					+ "(select openSubjectSeq from tblteacherrecord  where teacherseq = %d) %s", teacherNumber, where);
			rs = stat.executeQuery(sql);
			
			//메뉴
			ArrayList<VwFindStudentGradeNotNullDTO> list = new ArrayList<VwFindStudentGradeNotNullDTO>();
			
			while(rs.next()) {
				//수정에 필요한정보 재 출력시
				VwFindStudentGradeNotNullDTO dto = new VwFindStudentGradeNotNullDTO();
				dto.setSignUpSeq(rs.getString("signUpSeq"));
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setExamScoreSeq(rs.getString("examScoreSeq"));
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setSubjectName(rs.getString("subjectName"));			
				dto.setCourseName(rs.getString("courseName"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setSsn(rs.getString("ssn"));
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setWrittenscore(rs.getString("writtenscore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				dto.setAttendancePoint(rs.getString("attendancePoint"));
				dto.setWrittenPoint(rs.getString("writtenPoint"));
				dto.setPracticalPoint(rs.getString("practicalPoint"));
				
				list.add(dto);
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherScoreDAO_ScoreNullNotList");
			e.printStackTrace();
			System.out.println("자세한 사항은 관리자에게 문의해주세요");
		}
		
		return null;
	}// ScoreNullNotList
	
	/**
	 * 성적 수정 메소드입니다.
	 * TeacherExamScore table
	 * @param score TeacherExamScoreDTO
	 * @return TeacherExamScoreDTO score
	 */
	
	//성적 수정
	public int replace(TeacherExamScoreDTO score) {
		// TODO Auto-generated method stub
		Connection conn =null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			String sql = " { call procReplaceScore(?,?,?,?) } ";
			cstat = conn.prepareCall(sql);
			cstat.setString(1,score.getSeq());
			cstat.setString(2,score.getAttendanceScore());
			cstat.setString(3,score.getWrittenScore());
			cstat.setString(4,score.getPracticalScore());
			
			int result = cstat.executeUpdate();
			
			cstat.close();
			conn.close();
			
			return result;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherScoreDAO_replace");
			e.printStackTrace();
			System.out.println("자세한 사항은 관리자에게 문의해주세요");
		}
		
		return 0;
		
	}// replace 성적 수정 

}

