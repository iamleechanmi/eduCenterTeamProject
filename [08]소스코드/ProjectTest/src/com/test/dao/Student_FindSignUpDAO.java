package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.Student_FindSignUpDTO;

import oracle.jdbc.OracleTypes;

/**
 * 교육생 수강정보 조회 DAO
 * @author 오수경
 *
 */
public class Student_FindSignUpDAO {
	
	private Connection conn;
	private Statement stat;
	private CallableStatement pstat;
	private ResultSet rs;
	


	/**
	 * 생성자
	 */
	public Student_FindSignUpDAO() {
		
		try {
		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("Student_FindSignUpDTO.생성자");
			e.printStackTrace();
		}
		
	}


	/**
	 * 현재 진행중인 수강 정보와 개인 정보 조회
	 * @param seq 교육생 번호
	 * @return list가 반환됩니다.
	 * list
	 * suSeq : 수강 번호
	 * ocSeq : 개설 과정 번호
	 * cName : 강의명
	 * ocStartDate : 과정 시작일
	 * ocEndDate : 과정 종료일
	 * suEndDate : 과정 수료/중도탈락일 및 수료 여부
	 * crName : 강의실명
	 * suStatus : 수강 상태
	 * sName : 교육생 이름
	 * sTel : 교육생 번호
	 * sRegistrationDate : 교육생 등록일
	 */
	public ArrayList<Student_FindSignUpDTO> mySignUpNow(String seq) {
		
	
		try {
			
			String sql = String.format(" select * from vwMySignUp WHERE studentSeq = %s AND suStatus = '진행'", seq);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<Student_FindSignUpDTO> list = new ArrayList<Student_FindSignUpDTO>();
			
			while(rs.next()) {
				
				Student_FindSignUpDTO dto = new Student_FindSignUpDTO();
				
				dto.setsName(rs.getString("sName"));
				dto.setsTel(rs.getString("sTel"));
				dto.setsRegistrationDate(rs.getString("sRegistrationDate"));
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setOcSeq(rs.getString("ocSeq"));
				dto.setcName(rs.getString("cName"));
				dto.setOcStartDate(rs.getString("ocStartDate"));
				dto.setOcEndDate(rs.getString("ocEndDate"));
				dto.setSuEndDate(rs.getString("suEndDate"));
				dto.setCrName(rs.getString("crName"));
				dto.setSuStatus(rs.getString("suStatus"));
				
				list.add(dto);
				

			}

			return list;
			
		} catch (Exception e) {
			System.out.println("Student_FindSignUpDAO.mySignUpNow(seq)");
			e.printStackTrace();
		}
		
		return null;
	}


	
	/**
	 * 
	 * @param seq 교육생 번호
	 * @return 전체 수강 목록 ArrayList list
	 * list
	 * suSeq : 수강 번호
	 * cName : 과정명
	 * ocStartDate : 과정 시작일
	 * ocEndDate : 과정 종료일
	 * crName : 강의실명
	 * suStatus : 과정 상태 
	 */
	public ArrayList<Student_FindSignUpDTO> signUpList(String seq) {
		
		try {

			String sql = "{ call procMySignUpFindAll(?, ?) }";
			
			pstat = conn.prepareCall(sql);
			
			pstat.setString(1, seq);
			pstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			pstat.executeQuery();
			
			rs = (ResultSet)pstat.getObject(2);
			
			ArrayList<Student_FindSignUpDTO> list = new ArrayList<Student_FindSignUpDTO>();
			
			while(rs.next()) {
				
				Student_FindSignUpDTO dto = new Student_FindSignUpDTO();
				
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setcName(rs.getString("cName"));
				dto.setOcStartDate(rs.getString("ocStartDate"));
				dto.setOcEndDate(rs.getString("ocEndDate"));
				dto.setCrName(rs.getString("crName"));
				dto.setSuStatus(rs.getString("suStatus"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_FindSignUpDTO.signUpList(seq)");
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
