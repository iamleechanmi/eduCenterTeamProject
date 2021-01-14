package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.InTheWorkCourseDTO;
import com.test.dto.StudentInCourseDTO;
import com.test.dto.StudygroupDTO;
import com.test.dto.StudygroupMemberDTO;
import com.test.dto.StudygroupRegistrationDTO;
import com.test.dto.StudyroomReservationDTO;

/**
 * 
 * @author 이진혁
 * 스터디그룹관리에 관련 dao
 *
 */
public class StudygroupInfoDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 생성자
	 */
	public StudygroupInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.생성자");
			e.printStackTrace();
		}
		
	}

	/**
	 * 스터디그룹의 정보를 반환하는 메서드
	 * @param status 스터디 그룹의 진행 상태
	 * @return 스터디 그룹에 대한 정보를 담은 dto의 list
	 */
	public ArrayList<StudygroupDTO> studygroupList(String status) {
		
		try {
			
			String sql = "";
			
			if(status.equals("all")) {
				sql = "select "
							+ "seq, rpad(goal, 20, ' ') as goal, to_char(registrationDate, 'yyyy/mm/dd') as regDate, to_char(endDate, 'yyyy/mm/dd') as endDate "
							+ "from tblstudygroup where goal != '없음' order by seq";
			}else if(status.equals("ing")) {
				sql = "select "
						+ "seq, rpad(goal, 20, ' ') as goal, to_char(registrationDate, 'yyyy/mm/dd') as regDate, to_char(endDate, 'yyyy/mm/dd') as endDate "
						+ "from tblstudygroup where endDate > sysdate and goal != '없음' order by seq";
				
			}
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<StudygroupDTO> list = new ArrayList<StudygroupDTO>();
			
			while(rs.next()) {
				
				StudygroupDTO dto = new StudygroupDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setGoal(rs.getString("goal"));
				dto.setRegistrationDate(rs.getString("regDate"));
				dto.setEndDate(rs.getString("endDate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studygroupList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 스터디룸예약조목록을 반환하는 메서드
	 * @return 스터디룸 예약 정보를 담은 dto의 list
	 */
	public ArrayList<StudyroomReservationDTO> studyroomReservationList() {
		
		try {
			
			String sql = "select seq, studygroupSeq, studyroomName, to_char(reservationDate, 'yyyy/mm/dd') as rvDate from vwStudyRoomReservation order by seq desc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<StudyroomReservationDTO> list = new ArrayList<StudyroomReservationDTO>();
			
			while(rs.next()) {
				
				StudyroomReservationDTO dto = new StudyroomReservationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setStudygroupSeq(rs.getString("studygroupSeq"));
				dto.setStudyroomName(rs.getString("studyroomName"));
				dto.setReservationDate(rs.getString("rvDate"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studyroomReservationList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 진행중인 과정의 목록을 반환하는 메서드
	 * @return 진행중인 과정의 정보를 담은 dto의 list
	 */
	public ArrayList<InTheWorkCourseDTO> InTheWorkCourseList() {
		
		try {
			
			String sql = "select seq, rpad(courseName, 60, ' ') as courseName, classroomName from vwInTheWorkCourse order by seq";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<InTheWorkCourseDTO> list = new ArrayList<InTheWorkCourseDTO>();
			
			while(rs.next()) {
				
				InTheWorkCourseDTO dto = new InTheWorkCourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setClassroomName(rs.getString("classroomName"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.InTheWorkCourseList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 진행되는 과정에 속한 스터디그룹멤버들을 반환하는 메서드
	 * @param sel 개설 과정 번호
	 * @return 스터디그룹멤버들을 담은 dto의 list
	 */
	public ArrayList<StudentInCourseDTO> studyGroupMemberList(String sel) {
		
		try {
			
			String sql = "select * from vwStudentInCourse where openCourseStatus = '진행' and openCourseSeq = ? and studentname != '없음'";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			ArrayList<StudentInCourseDTO> list = new ArrayList<StudentInCourseDTO>();
			
			while(rs.next()) {
				
				StudentInCourseDTO dto = new StudentInCourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setStudentTel(rs.getString("studentTel"));
				dto.setStudentSsn(rs.getString("studentSsn"));
				dto.setOpenCourseStatus(rs.getString("openCourseStatus"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 스터디그룹을 추가하는 메서드
	 * @param goal 목적
	 * @return 추가 성공여부(성공 1, 실패 0)
	 */
	public int studygroupAdd(String goal) {
		
		try {
			
			String sql = "{ call procAddStudyGroup(?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, goal);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studygroupAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 스터디 그룹 멤버를 추가하는 메서드
	 * @param gsel 그룹번호
	 * @param msel 멤버의 수강번호
	 * @param rank 멤버의 직위
	 * @return 추가성공여부(성공 1, 실패 0)
	 */
	public int studygroupMemberAdd(String gsel, String msel, String rank) {

		try {
			
			String sql = "insert into tblStudyGroupRegistration(seq, studyGroupSeq, signUpSeq, groupRank)"
							+ " values(seqStudyGroupRegistration.nextVal, ?, ?, ?)";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, gsel);
			cstat.setString(2, msel);
			cstat.setString(3, rank);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studygroupMemberAdd()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 스터디 그룹에 팀장이 존재하는지 검사 
	 * @param gsel 그룹번호
	 * @return 팀장존재 여부
	 */
	public boolean existReader(String gsel) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStudyGroupRegistration where groupRank = '팀장' and studyGroupSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, gsel);
			rs = pstat.executeQuery();
			
			rs.next();
			
			int result = Integer.parseInt(rs.getString("cnt"));
			
			if(result >= 1) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.existReader()");
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 스터디그룹의 멤버의 수를 반환하는 메서드
	 * @param gsel 그룹번호
	 * @return 멤버의 수
	 */
	public int memberCount(String gsel) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStudyGroupRegistration where studyGroupSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, gsel);
			rs = pstat.executeQuery();
			
			rs.next();
			
			int result = Integer.parseInt(rs.getString("cnt"));
			
			return result;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.memberCount()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 해당 스터디그룹의 속하는 멤버의 정보를 반환하는 메서드
	 * @param sel 그룹번호
	 * @return 스터디 그룹 멤버의 정보를 담은 dto의 list
	 */
	public ArrayList<StudygroupRegistrationDTO> memberInStudygroupList(String sel) {
		
		try {
			
			String sql = "select * from tblStudyGroupRegistration where studygroupSeq = ? order by seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			rs = pstat.executeQuery();
			
			ArrayList<StudygroupRegistrationDTO> list = new ArrayList<StudygroupRegistrationDTO>();
			
			while(rs.next()) {
				
				StudygroupRegistrationDTO dto = new StudygroupRegistrationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setStudygroupSeq(rs.getString("studygroupSeq"));
				dto.setSignUpSeq(rs.getString("signUpSeq"));
				dto.setGroupRank(rs.getString("groupRank"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.memberInStudygroupList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 스터디그룹에 속하는 멤버의 좀 더 자세한 정보를 반환하는 메서드
	 * @param gsel 그룹 번호
	 * @return 멤버의 정보를 담은 dto의 list
	 */
	public ArrayList<StudygroupMemberDTO> studyGroupMemberFind(String gsel) {
		
		try {
			
			String sql = "select * from vwstudyGroupMember where studyGroupSeq = ? order by seq";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, gsel);
			rs = pstat.executeQuery();
			
			ArrayList<StudygroupMemberDTO> list = new ArrayList<StudygroupMemberDTO>();
			
			while(rs.next()) {
				
				StudygroupMemberDTO dto = new StudygroupMemberDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setRank(rs.getString("rank"));
				dto.setStudygroupSeq(rs.getString("studyGroupSeq"));
				
				list.add(dto);
				
				
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studyGroupMemberFine()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 스터디그룹의 멤버를 삭제하는 메서드
	 * @param dsel 삭제할 멤버의 수강번호
	 * @return 삭제 성공여부(성공1, 실패0)
	 */
	public int studygroupMemberDelete(String dsel) {
		
		try {
			
			String sql = "delete from tblstudygroupRegistration where seq = ?	";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dsel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studygroupMemberDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 스터디그룹을 삭제하는 메서드
	 * @param gsel 그룹 번호
	 * @return 삭제 성공여부(성공1, 실패0)
	 */
	public int studygroupDelete(String gsel) {
		
		try {
			
			String sql = "Update tblStudygroup set goal = '없음' where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, gsel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudygroupInfoDAO.studygroupDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
}
