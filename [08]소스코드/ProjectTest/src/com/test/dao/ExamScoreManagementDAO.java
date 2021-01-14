package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.OpenCourseDTO;
import com.test.dto.OpenSubjectDTO;
import com.test.dto.VwCourseByStudentDTO;
import com.test.dto.VwFindGradeBySubjectDTO;
import com.test.dto.VwOpenCourseWithSubjectDTO;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 * 시험 관리 및 성적 조회에 대한 DAO 메서드를 모아놓은 클래스입니다.
 * @author 이청강
 */

public class ExamScoreManagementDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public ExamScoreManagementDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.ExamScoreManagementDAO()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 센터 내의 개설 과정 중 진행되고있거나 종료된 개설 과정을 
	 * ArrayList에 담아 반환하는 메서드입니다. 
	 * @return 센터 내의 개설 과정 중 진행되고있거나 종료된 개설 과정담은 ArrayList
	 */
	public ArrayList<OpenCourseDTO> findAllOpenCourse() {
		
		try {

			ArrayList<OpenCourseDTO> list = new ArrayList<OpenCourseDTO>();
			
			String sql = "select * from vwFindExamOpenCourse";
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				OpenCourseDTO dto = new OpenCourseDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setClassroomName(rs.getString("classroomName"));
				dto.setStartDate(rs.getString("startDate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.findAllOpenCourse()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 특정 개설 과정에 포함된 개설 과목의 정보를 담은 ArrayList를 반환하는 메서드입니다.
	 * @param courseSeq 조회할 개설 과정 번호
	 * @return 특정 개설 과정에 포함된 개설 과목의 정보를 담은 ArrayList
	 */
	public ArrayList<VwOpenCourseWithSubjectDTO> findOpenSubjectInCourse(String courseSeq) {
		
		try {

			ArrayList<VwOpenCourseWithSubjectDTO> list = new ArrayList<VwOpenCourseWithSubjectDTO>();
			
			String sql = "select * from VwOpenCourseWithSubject where opencourseSeq = ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, courseSeq);
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				VwOpenCourseWithSubjectDTO dto = new VwOpenCourseWithSubjectDTO();
				
				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setOpenSubjectStartDate(rs.getString("openSubjectStartDate"));
				dto.setOpenSubjectEndDate(rs.getString("openSubjectEndDate"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setWhetherExam(rs.getString("whetherExam"));
				dto.setWhetherGrade(rs.getString("whetherGrade"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.findOpenSubjecyInCourse()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 특정 개설 과목의 성적 정보를 ArrayList에 담아 반환하는 메서드이빈다.
	 * @param seq 조회할 개설 과목의 번호
	 * @return 특정 개설 과목의 성적 정보를 담은 ArrayList
	 */
	public ArrayList<VwFindGradeBySubjectDTO> findGradeBySubject(String seq) {
		
		try {
			
			ArrayList<VwFindGradeBySubjectDTO> list = new ArrayList<VwFindGradeBySubjectDTO>();
			
			String sql = "{ call procFindGradeBySubject(?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while(rs.next()) {
				VwFindGradeBySubjectDTO dto = new VwFindGradeBySubjectDTO();
				
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				dto.setWrittenScore(rs.getString("writtenScore"));
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.findGradeBySubject()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 특정 교육생이 특정 과정에서 받은 성적을 ArrayList에 담아 반환하는 메서드입니다.
	 * @param seq 조회할 교육생 번호
	 * @param courseSeq 조회할 개설 과정 번호
	 * @return 특정 교육생이 특정 과정에서 받은 성적을 담은 ArrayList
	 */
	public ArrayList<VwFindGradeBySubjectDTO> findGradeByStudent(String seq, String courseSeq) {
		
		try {

			ArrayList<VwFindGradeBySubjectDTO> list = new ArrayList<VwFindGradeBySubjectDTO>();
			
			String sql = "{ call procFindGradeByStdOpcs(?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.setString(2, courseSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);

			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			while (rs.next()) {
				VwFindGradeBySubjectDTO dto = new VwFindGradeBySubjectDTO();
				
				dto.setSubjectName(rs.getNString("subjectName"));
				dto.setAttendanceScore(rs.getString("attendanceScore"));
				dto.setWrittenScore(rs.getString("writtenScore"));
				dto.setPracticalScore(rs.getString("practicalScore"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.findGradeByStudent()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 입력받은 교육생 번호에 해당하는 교육생이 수강하고 있거나 수강한 적이 있는
	 * 개설 과정에 대한 정보를 담은 ArrayList를 반환하는 메서드입니다.
	 * @param seq 조회할 교육생의 번호
	 * @return 특정 교육생이 수강하고 있거나 수강한 적이 있는 개설 과정에 대한 정보를 담은 ArrayList
	 */
	public ArrayList<VwCourseByStudentDTO> findCourseByStudent(String seq) {

		try {

			ArrayList<VwCourseByStudentDTO> list = new ArrayList<VwCourseByStudentDTO>();
			
			String sql = "{ call procFindCourseByStudent(?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				VwCourseByStudentDTO dto = new VwCourseByStudentDTO();
				
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setCourseName(rs.getString("CourseName"));
				dto.setOpenCourseStartDate(rs.getString("openCourseStartDate"));
				dto.setOpenCourseEndDate(rs.getString("openCourseEndDate"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("ExamScoreManagementDAO.findCourseByStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}












