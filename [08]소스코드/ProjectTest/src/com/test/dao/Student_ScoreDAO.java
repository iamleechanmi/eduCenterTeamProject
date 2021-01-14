package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.Student_ScoreDTO;

import oracle.jdbc.OracleTypes;

/**
 * 교육생 점수 DAO입니다.
 * @author 오수경
 *
 */
public class Student_ScoreDAO {

	private Connection conn;
	private Statement stat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 
	 * 생성자
	 * 
	 */
	public Student_ScoreDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("Student_ScoreDAO.생성자");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 점수 조회 DAO
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @return list 반환
	 * studentSeq 교육생 번호
	 * signUpSeq 수강 번호
	 * subjectName 과목명
	 * attendancePoint 출석배점
	 * writtenPoint 필기배점
	 * practicalPoint 실기배점
	 * attendanceScore 출석점수
	 * writtenScore 필기점수
	 * practicalScore 실기저수
	 * whetherGrade 점수등록여부
	 * whetherExam 시험문제등록여부
	 */
	public ArrayList<Student_ScoreDTO> scoreList(String seq, String suSeq) {
		
		try {
			
			String sql = "{ call procFindMyScore(?,?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1,seq);
			cstat.setString(2,suSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			ArrayList<Student_ScoreDTO> list = new ArrayList<Student_ScoreDTO>();
			
			while(rs.next()) {
				
				Student_ScoreDTO dto = new Student_ScoreDTO();
				
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setAttendancepoint(rs.getString("attendancepoint"));
				dto.setWrittenpoint(rs.getString("writtenpoint"));
				dto.setPracticalpoint(rs.getString("practicalpoint"));
				dto.setAttendancescore(rs.getString("attendancescore"));
				dto.setWrittenscore(rs.getString("writtenscore"));
				dto.setPracticalscore(rs.getString("practicalscore"));
				dto.setWhetherGrade(rs.getString("whetherGrade"));
				dto.setWhetherExam(rs.getString("whetherExam"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_ScoreDTO.scoreList(seq,suSeq)");
			e.printStackTrace();
		}
		
		return null;
	}

}
