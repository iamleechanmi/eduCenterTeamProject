package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherLoginDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;


	public int login(String name, String pw) {
		
		
		try {
						
			conn = DBUtil.open();
						
			String sql = "select seq from tblTeacher where name = ? and ssn = ?";	// 이름, 주민번호 입력시 교사seq 반환		
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setNString(2, pw);
			
//			pstat.setString(1, "박주호");
//			pstat.setNString(2, "1512385");
			
			rs = pstat.executeQuery();
			
			
			if(rs.next()) {
			
			//System.out.println(rs.getInt(1));
			
			return rs.getInt(1);
			} 

		} catch (Exception e) {
			System.out.println("Teacher_login.main()");
			e.printStackTrace();
		}
		
		return 0;
				
	}
	
}
