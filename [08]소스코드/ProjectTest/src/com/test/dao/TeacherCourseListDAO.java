package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.dto.TeacherCourseDTO;

import oracle.jdbc.OracleTypes;
/**
 * 
 * @author 윤지봉
 *
 */
public class TeacherCourseListDAO {

	public static ArrayList<TeacherCourseDTO> courseList(int seq, String state) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = DBUtil.open();
			String sql = "{ call procteachercoursefind(?,?,?) }"; // 상태, 교사seq, out cursor
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, state);
			stat.setInt(2, seq);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(3);
			
			ArrayList<TeacherCourseDTO> list = new ArrayList<TeacherCourseDTO>();
			
			while(rs.next()) {
				TeacherCourseDTO dto = new TeacherCourseDTO();
				
				dto.setSeq(rs.getString("courseseq"));
				dto.setName(rs.getString("coursename"));
				dto.setStart(rs.getString("startDate"));
				dto.setEnd(rs.getString("endDate"));
				
				
//				System.out.println(rs.getString("courseseq"));
//				System.out.println(rs.getString("coursename"));
//				System.out.println(rs.getString("startDate"));
//				System.out.println(rs.getString("endDate"));
//				System.out.println();	
				
				list.add(dto);
				
			}
			rs.close();
			stat.close();
			conn.close();
			

			return list;
			
			

		} catch (Exception e) {
			System.out.println("TeacherCourseListDAO.courseList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
