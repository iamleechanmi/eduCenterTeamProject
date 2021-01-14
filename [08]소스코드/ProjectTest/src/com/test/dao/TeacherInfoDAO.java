package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.TeacherDTO;
import com.test.dto.TeacherPossibleSubjectDTO;

/**
 * 
 * @author 이진혁
 * 교사계정관리에 관련된 쿼리문을 작성한 dao
 *
 */
public class TeacherInfoDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	//생성자
	public TeacherInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.생성자");
			e.printStackTrace();
		}
		
	}

	/**
	 * 전체 교사 정보를 가져오는 메서드
	 * @return 전체 교사 정보를 담은 list
	 */
	public ArrayList<TeacherDTO> teacherList() {
		
		try {
			
			String sql = "select * from tblTeacher where name != '없음' order by seq";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			while(rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teacherList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사 강의 가능 과목 목록을 가져오는 메서드
	 * @param sel 교사 번호
	 * @return 해당 교사의 가능 과목 정보를 담은 dto의 list
	 */
	public ArrayList<TeacherPossibleSubjectDTO> possibleSubjectList(String sel) {
		
		try {
			
			String sql = "select seq, subject from vwTeacher where teacherSeq = ? order by seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			ArrayList<TeacherPossibleSubjectDTO> list = new ArrayList<TeacherPossibleSubjectDTO>();
			
			while(rs.next()) {
				TeacherPossibleSubjectDTO dto = new TeacherPossibleSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.possibleSubjectList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사를 추가하는 메서드
	 * @param dto 새로운 교사의 정보를 담은 dto
	 * @return 교사의 추가의 성공 여부. (1이면 성공, 0이면 실패)
	 */
	public int teacherAdd(TeacherDTO dto) {
		
		try {
			
			String sql = "insert into tblTeacher(seq, name, ssn, tel) values (seqTeacher.nextVal, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			pstat.setString(3, dto.getTel());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teacherAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 교사 삭제하는 메서드
	 * @param sel 삭제할 교사 번호
	 * @return 삭제 성공여부.(1이면 성공, 0이면 실패)
	 */
	public int teacherDelete(String sel) {

		try {
			
			String sql = "update tblTeacher set name = '없음', ssn = '없음', tel = '없음' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teacherDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 수정할 교사의 정보를 가져오는 메서드
	 * @param sel 수정 예정인 교사 번호
	 * @return 수정 전의 해당 교사의 정보를 담은 dto
	 */
	public TeacherDTO teacherGet(String sel) {
		
		try {
			
			String sql = "select * from tblTeacher where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			TeacherDTO dto = new TeacherDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setSsn(rs.getString("ssn"));
			dto.setTel(rs.getString("tel"));
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teacherGet()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사 정보 수정하는 메서드
	 * @param tdto 수정할 데이터를 담은 dto
	 * @return 수정성공여부.(1이면 성공, 0이면 실패)
	 */
	public int teacherUpdate(TeacherDTO tdto) {
		
		try {
			
			String sql = "update tblTeacher set name = ?, ssn = ?, tel =? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tdto.getName());
			pstat.setString(2, tdto.getSsn());
			pstat.setString(3, tdto.getTel());
			pstat.setString(4, tdto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teacherUpdate()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 교사 강의 가능 과목을 삭제하는 메서드
	 * @param s 삭제할 과목의 번호
	 * @return 삭제성공여부(1이면 성공, 0이면 실패)
	 */
	public int teachPossibleSubjectDelete(String s) {
		
		try {
			
			String sql = "{ call procDeletePossibleSubject(?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, s);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teachPossibleSubjectDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 강의가능과목을 추가하는 메서드
	 * @param s 추가할 과목번호
	 * @param sel 교사번호
	 * @return 추가성공여부(1이면 성공, 0이면 실패)
	 */
	public int teachPossibleSubjectAdd(String s, String sel) {
		
		try {
			
			String sql = "{ call procAddPossibleSubject(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, s);
			cstat.setString(2, sel);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherInfoDAO.teachPossibleSubjectAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	
}
