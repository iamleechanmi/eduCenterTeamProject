package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.dto.TeacherstudentallfindDTO;

import oracle.jdbc.OracleTypes;
/**
 * 
 * @author 윤지봉
 *
 */
public class TeacherstudentallfindDAO {
/**
 * 학생 목록을 가져오는메서드 입니다.
 * @param num 교사 번호
 * @return list 목록
 */
	public static ArrayList<TeacherstudentallfindDTO> teacherstudentfind(int num) {
	
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs =null;
		
		Scanner scan = new Scanner(System.in);
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procteastuallfind(?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setInt(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			ArrayList<TeacherstudentallfindDTO> list = new ArrayList<TeacherstudentallfindDTO>();
			
			while(rs.next()) {
				
				TeacherstudentallfindDTO dto = new TeacherstudentallfindDTO();
				
				dto.setSeq(rs.getString("studentseq"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setRegist(rs.getString("registrationdate"));
				dto.setStatus(rs.getString("status"));
				
				list.add(dto);
			
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("test.m2()");
			e.printStackTrace();
		}
		
		return null;
	}

}
