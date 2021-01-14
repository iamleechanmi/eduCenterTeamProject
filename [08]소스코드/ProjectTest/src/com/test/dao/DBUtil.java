package com.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author 이진혁
 * 데이터 베이스에 연결하기 위한 클래스
 * 
 */
public class DBUtil {
	
	
	private static Connection conn;
	
	/**
	 * 데이터 베이스에 연결 메서드
	 * @return 데이터 베이스 연결된 conn
	 */
	public static Connection open() {
		
		//아래의 오류들은 url이 잘 못 작성된 것.
		//java.sql.SQLRecoverableException : IO 오류
		
		//java.sql.SQLException: 부적합한 Oracle URL이 지정되었습니다

		//java.sql.SQLRecoverableException: IO 오류: The Network Adapter could not establish the connection
		
		//java.sql.SQLException: Listener refused the connection with the following error: -- Oracle이 죽어있는것
		//ORA-12505, TNS:listener does not currently know of SID given in connect descriptor
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//java.sql.SQLException: ORA-01017: invalid username/password; logon denied
		String id = "hr";
		String pw = "java1234";
		
		try {
			
			//java.lang.ClassNotFoundException: oracle.jdbc.driver.OracleaDriver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil 에러");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @param server 접속할 서버 주소입니다.
	 * @param id 접속할 계정명입니다.
	 * @param pw 접속할 비밀번호입니다.
	 * @return 연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
