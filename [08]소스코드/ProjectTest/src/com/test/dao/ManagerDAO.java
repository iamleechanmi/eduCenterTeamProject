package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.ClassroomDTO;
import com.test.dto.CourseDTO;
import com.test.dto.ManagerDTO;
import com.test.dto.SubjectDTO;
import com.test.dto.TextbookDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 기초정보관리에 관련된 sql문(쿼리)를 처리하기 위한 DAO
 *
 */
public class ManagerDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
	 * 생성자
	 * 디비 자동연결
	 */
	public ManagerDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.생성자");
			e.printStackTrace();
		}
		
	}

	/**
	 * 회원가입되어 있는지 확인하는 메서드
	 * @param dto 이름, 비밀번호를 넣은 dto
	 * @return 결과값 반환. 1이면 성공, 0이면 실패
	 */
	public int loginCheck(ManagerDTO dto) {
		
		try {
			
			String sql = "select count(*) as cnt from tblManager where name = ? and ssn = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			if (rs.getString("cnt").equals("1")) {
				return 1;
			}else {
				return 0;
			}
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.loginCheck()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 로그인하는 관리자의 번호를 반환하는 메서드
	 * @param dto 입력받은 값을 넣은 dto(로그인을 위한 데이터 저장)
	 * @return 관리자 번호를 반환
	 */
	public String seqCheck(ManagerDTO dto) {
		
		try {
			
			String sql = "select seq from tblManager where name = ? and ssn = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			return rs.getString("seq");
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.seqCheck()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//*******************여기부터 기초정보관리를 위한 쿼리

	/**
	 * 과정목록을 반환하는 메서드
	 * @return 결과값을 받는 arrayList
	 */
	public ArrayList<CourseDTO> courseList() {
		
		
		try {
			String sql = "select seq, rpad(name, 70, ' ') as name, rpad(goal, 150, ' ') as goal, rpad(period, 5, ' ') as period "
					+ "from tblCourse where name != '없음' or goal != '없음' order by seq";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
			
			while(rs.next()) {
				
				CourseDTO dto = new CourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setGoal(rs.getString("goal"));
				dto.setPeriod(rs.getString("period"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.courseList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과정 추가하는 메서드
	 * @param dto 입력받은 값을 넣은 dto
	 * @return 결과값을 반환. 1이면 성공, 0이면 실패
	 */
	public int courseAdd(CourseDTO dto) {
		
		
		try {
			String sql = "insert into tblCourse(seq, name, goal, period) values (seqCourse.nextVal, ?, ?, ?)";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getGoal());
			pstat.setString(3, dto.getPeriod());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.CourseAdd()");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	/**
	 * 과정 삭제하는 메서드
	 * @param sel 과목번호
	 * @return 결과값을 반환. 1이면 성공, 0이면 실패
	 */
	public int courseDelete(String sel) {
		
		try {
			
			String sql = "update tblCourse set name = '없음', goal = '없음' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 수정전 과정 데이터를 반환하는 메서드
	 * @param sel 과정 번호
	 * @return 과정 번호에 대한 수정 전의 데이터를 반환
	 */
	public CourseDTO courseGet(String sel) {

		try {
			
			String sql = "select * from tblCourse where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				CourseDTO dto =  new CourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setGoal(rs.getString("goal"));
				dto.setPeriod(rs.getString("period"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과정 수정하는 메서드
	 * @param dto 수정할 과정에 관련된 값을 가진 dto
	 * @return 수정의 성공여부.(1이면 성공, 0이면 실패)
	 */
	public int courseUpdate(CourseDTO dto) {
		
		try {
			
			String sql = "update tblCourse set name = ?, goal = ?, period = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getGoal());
			pstat.setString(3, dto.getPeriod());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.courseUpdate()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 과목 정보에 대한 값을 반환하는 메서드
	 * @return 과목에 관련된 데이터를 담은 dto의 arraylist
	 */
	public ArrayList<SubjectDTO> subjectList() {

		try {
			
			ArrayList<SubjectDTO> list = new ArrayList<SubjectDTO>();
			
			String sql = "select seq, rpad(name, 62, ' ') as name, rpad(period, 5, ' ') as period from tblSubject where name != '없음' order by seq";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				SubjectDTO dto = new SubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
				
				list.add(dto);
				
				
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.subjectList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과목 추가하는 메서드
	 * @param dto 추가할 과목의 정보를 담은 dto
	 * @return 과목 추가 성공 여부.(1이면 성공, 0이면 실패)
	 */
	public int subjectAdd(SubjectDTO dto) {
		
		try {
			
			String sql = "insert into tblSubject(seq, name, period) values (seqSubject.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPeriod());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.subjectAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 과목 삭제하는 메서드
	 * @param sel 삭제할 과목의 번호
	 * @return 삭제 성공 여부.(1이면 성공, 0이면 실패)
	 */
	public int subjectDelete(String sel) {
		
		try {
			
			String sql = "update tblSubject set name = '없음' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.subjectDelete()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	/**
	 * 과목 수정 전 데이터 반환하는 메서드
	 * @param sel 수정할 예정의 과목의 번호
	 * @return 수정하기 전의 과목의 정보를 담은 dto
	 */
	public SubjectDTO subjectGet(String sel) {
		
		try {
			
			String sql = "select * from tblSubject where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			SubjectDTO dto = new SubjectDTO();
			
			dto.setName(rs.getString("name"));
			dto.setSeq(rs.getString("seq"));
			dto.setPeriod(rs.getString("period"));
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.subjectGet()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과목 수정하는 메서드
	 * @param sdto 수정할 데이터를 담은 dto
	 * @return 수정 성공 여부(1이면 성공, 0이면 실패)
	 */
	public int subjectUpdate(SubjectDTO sdto) {
		
		try {
			
			String sql = "update tblSubject set name = ?, period = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sdto.getName());
			pstat.setString(2, sdto.getPeriod());
			pstat.setString(3, sdto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 강의실 목록을 반환하는 메서드
	 * @return 전체 강의실 정보를 담은 list
	 */
	public ArrayList<ClassroomDTO> classroomList() {
		
		try {
			
			String sql = "select * from tblClassroom where name != '없음' order by seq";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ClassroomDTO> list = new ArrayList<ClassroomDTO>();
			
			while(rs.next()) {
				
				ClassroomDTO dto = new ClassroomDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPersonnel(rs.getString("personnel"));
				
				list.add(dto);
				
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.classroomList()");
			e.printStackTrace();
		}
		
		return null;
		
	}//classList()

	/**
	 * 강의실 추가하는 메서드
	 * @param dto 추가할 강의실 정보를 담은 dto
	 * @return 추가 성공여부(1이면 성공, 0이면 실패)
	 */
	public int classroomAdd(ClassroomDTO dto) {
		
		try {
			
			String sql = "insert into tblClassroom(seq, name, personnel) values (seqClassroom.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPersonnel());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.classroomAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 강의실 삭제하는 메서드
	 * @param sel	삭제할 강의실 번호
	 * @return 삭제 성공여부 (1이면 성공, 0이면 실패)
	 */
	public int classroomDelete(String sel) {
		
		try {
			
			String sql = "update tblClassroom set name = '없음' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.classroomDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 강의실 수정전 데이터를 반환하는 메서드
	 * @param sel 수정 예정의 강의실 번호
	 * @return 수정 전의 강의실 데이터를 담은 dto
	 */
	public ClassroomDTO classroomGet(String sel) {
		
		try {
			
			String sql = "select * from tblClassroom where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			ClassroomDTO dto = new ClassroomDTO();
			
			dto.setName(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setPersonnel(rs.getString("personnel"));
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.classroomGet()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 강의실 수정하는 메서드
	 * @param cdto 수정할 정보를 담은 dto
	 * @return 강의실 수정 성공 여부(1이면 성공, 0이면 실패)
	 */
	public int classroomUpdate(ClassroomDTO cdto) {
		
		try {
			
			String sql = "update tblClassroom set name = ?, personnel = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, cdto.getName());
			pstat.setString(2, cdto.getPersonnel());
			pstat.setString(3, cdto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.classroomUpdate()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교재 목록을 반환하는 메서드
	 * @return 전체 교재의 정보를 담은 list
	 */
	public ArrayList<TextbookDTO> textbookList() {
		
		try {
			
			ArrayList<TextbookDTO> list = new ArrayList<TextbookDTO>();
			
			String sql = "select seq, rpad(name, 40, ' ') as name, rpad(publisher, 25, ' ') as publisher from tblTextbook where name != '없음' order by seq";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				TextbookDTO dto = new TextbookDTO();
				
				dto.setName(rs.getString("name"));
				dto.setSeq(rs.getString("seq"));
				dto.setPublisher(rs.getString("publisher"));
			
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.textbookList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교재 추가하는 메서드
	 * @param dto 추가할 교재의 정보를 담은 dto
	 * @return 추가 성공여부 (1이면 성공, 0이면 실패)
	 */
	public int textbookAdd(TextbookDTO dto) {
		
		try {
			
			String sql = "insert into tblTextbook(seq, name, publisher) values (seqTextbook.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPublisher());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.textbookAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 교재 삭제하는 메서드
	 * @param sel 삭제할 교재 번호
	 * @return 삭제 성공여부(1이면 성공, 0이면 실패)
	 */
	public int textbookDelete(String sel) {
		
		try {
			
			String sql = "update tblTextbook set name = '없음', publisher = '없음' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.textbookDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 교재 수정 전 데이터를 반환하는 메서드
	 * @param sel 수정 예정의 교재 번호
	 * @return 수정 전의 데이터를 담은 dto
	 */
	public TextbookDTO textbookGet(String sel) {
		
		try {
			
			String sql = "select * from tblTextbook where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			rs.next();
			
			TextbookDTO dto = new TextbookDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setPublisher(rs.getString("publisher"));
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.textbookGet()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교재 수정하는 메서드
	 * @param dto 수정할 데이터를 담은 dto
	 * @return 수정 성공 여부 (1이면 성공, 0이면 실패)
	 */
	public int textbookUpdate(TextbookDTO dto) {
		
		try {
			
			String sql = "Update tblTextbook set name = ?, publisher = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPublisher());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.textbookUpdate()");
			e.printStackTrace();
		}
		
		return 0;
	}

	

}
