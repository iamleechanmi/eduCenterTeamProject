package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.dto.TeacherSubjectDTO;

import oracle.jdbc.OracleTypes;
/**
 * 
 * @author 윤지봉
 *
 */
public class TeacherSubjectListDAO {
/**
 * 과목 목록을 가져오는 메서드입니다.
 * @param seq 교사번호
 * @param num 과정번호
 * @return list 목록
 */
	public static ArrayList<TeacherSubjectDTO> subjectList(int seq, int num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteacheropensubjectfind(?,?,?)}";
			
			stat = conn.prepareCall(sql);
			
			stat.setInt(1, seq);
			stat.setInt(2, num);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			
			rs = (ResultSet)stat.getObject(3);
			//System.out.println(rs.next());

			ArrayList<TeacherSubjectDTO> list = new ArrayList<TeacherSubjectDTO>();
			
			while(rs.next()) {
				TeacherSubjectDTO dto = new TeacherSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("subjectname"));
				dto.setStart(rs.getString("startDate"));
				dto.setEnd(rs.getString("endDate"));
				dto.setTextbook(rs.getString("textbook"));
				dto.setCount(rs.getString("studentcount"));
						
				list.add(dto);
			}
			rs.close();
			stat.close();
			conn.close();
			
			return list;
			
					
		} catch (Exception e) {
			System.out.println("TeacherSubjectListDAO.subjectList()");
			e.printStackTrace();
		}
		
		return null;
	}


	

}
