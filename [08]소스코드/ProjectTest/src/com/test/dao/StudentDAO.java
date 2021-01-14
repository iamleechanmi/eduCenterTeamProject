package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.dto.StudentDTO;

/**
 * 교육생 로그인 DAO입니다.
 * @author 오수경
 *
 */
public class StudentDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * 생성자
	 */
	public StudentDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.생성자");
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 
	 * @param dto 이름 / 비밀번호(주민등록번호 뒷자리)
	 * @return 결과값 반환 1이면 성공, 0이면 실패
	 */
	public int loginCheck(StudentDTO dto) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStudent where name = ? and ssn = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,dto.getName());
			pstat.setString(2,dto.getSsn());
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			if(rs.getString("cnt").equals("1")) {
				return 1;
			}else
				return 0;
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.loginCheck()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 
	 * @param dto 이름 / 비밀번호(주민등록번호 뒷자리)
	 * @return 교육생 번호를 반환
	 */
	public String seqCheck(StudentDTO dto) {
		
		try {
			
			String sql = "select seq from tblStudent where name = ? and ssn = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2,dto.getSsn());
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			return rs.getString("seq");
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.seqCheck()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생 이름을 돌려주는 DAO
	 * @param seq 교육생 번호
	 * @return name 교육생 이름
	 */
	public String nameCheck(String seq) {
		
try {
			
			String sql = "select name from tblStudent where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			
			rs = pstat.executeQuery();
			
			rs.next();
			
			return rs.getString("name");
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO.seqCheck()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
