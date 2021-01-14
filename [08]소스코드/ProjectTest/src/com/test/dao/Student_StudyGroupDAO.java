package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.Student_StudyGroupDTO;

import oracle.jdbc.OracleTypes;
/**
 * 교육생 스터디 그룹 DAO입니다.
 * @author 오수경
 *
 */
public class Student_StudyGroupDAO {

	private Connection conn;
	private Statement stat;
	private CallableStatement cstat;
	private ResultSet rs;

	
	/**
	 * 
	 * 생성자
	 * 
	 */
	public Student_StudyGroupDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("Student_StudyGroupDAO.생성자");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 교육새이 참여한 스터디 그룹 목록 조회 DAO
	 * 교육생이 참여한 스터디 그룹 목록을 list로 반환합니다.
	 * @param seq 교육생 번호
	 * @return list
	 * sName : 학생 이름
	 * sgSeq : 스터디 그룹 번호
	 * sgGoal : 스터디 그룹 목표
	 * sgRegDate : 스터디 등록일
	 * sgEndDate : 스터디 종료일
	 * sgrGroupRank : 직위(팀장/팀원)
	 */
	public ArrayList<Student_StudyGroupDTO> info(String seq) {
		
		try {
			
			String sql ="{ call procFindMyStudyGroup (?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs=(ResultSet)cstat.getObject(2);
			
			ArrayList<Student_StudyGroupDTO> list = new ArrayList<Student_StudyGroupDTO>();
			
			while(rs.next()) {
				Student_StudyGroupDTO dto = new Student_StudyGroupDTO();
				
				dto.setSgSeq(rs.getString("sgSeq"));
				dto.setSgGoal(rs.getString("sgGoal"));
				dto.setSgRegDate(rs.getString("sgRegDate"));
				dto.setSgEndDate(rs.getString("sgEndDate"));
				dto.setSgrGroupRank(rs.getString("sgrGroupRank"));
				dto.setSgrSeq(rs.getString("sgrSeq"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("Student_StudyGruopDAO.info(seq)");
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
	 * 
	 * 선택한 스터디 그룹의 스터디룸 예약 조회 DAO
	 * 스터디룸 예약 조회 list를 반환합니다.
	 * @param sgSeq 스터디 그룹 번호
	 * @return list
	 * srRsvpDate : 예약일
	 * smName : 스터디룸 명
	 * 
	 */
	public ArrayList<Student_StudyGroupDTO> findRsvp(String sgSeq) {
		
		try {
			
			String sql = "{ call procFindStudyReservation(?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, sgSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<Student_StudyGroupDTO> list = new ArrayList<Student_StudyGroupDTO>();
			
			while(rs.next()) {
				
				Student_StudyGroupDTO dto = new Student_StudyGroupDTO();
				
				dto.setSrRsvpDate(rs.getString("srRsvpDate"));
				dto.setSmName(rs.getString("smName"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_StudyGroupDAO.findRsvp(seq,seSeq)");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 스터디 일지 작성 DAO입니다.
	 * @param sgrSeq 스터디 그룹 등록 번호
	 * @param content 스터디 일지 내용
	 * @return 작성에 성공하면 1 실패하면 0을 반환합니다.
	 */
	public int record(String sgrSeq, String content) {
		try {
			
			String sql = "{ call procAddStudyRecord(?,?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, content);
			cstat.setString(2, sgrSeq);
			
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Student_StudyGroupDAO.record(sgrSeq,content)");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	

	/**
	 * 선택한 스터디 그룹의 일지를 조회합니다.
	 * 스터디 일지 list를 반환합니다.
	 * @param sgSeq 스터디 그룹 번호
	 * @return list
	 * srContent 내용
	 * srRecordDate 작성일
	 */
	public ArrayList<Student_StudyGroupDTO> findRecord(String sgSeq) {
		
		try {
			
			String sql = "{ call procFindStudyRecord(?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, sgSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<Student_StudyGroupDTO> list = new ArrayList<Student_StudyGroupDTO>();
			
			while(rs.next()) {
				
				Student_StudyGroupDTO dto = new Student_StudyGroupDTO();
				
				dto.setSrContent(rs.getString("srContent"));
				dto.setSrRecordDate(rs.getString("srRecordDate"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_StudyGroupDAO.findRecord(sgSeq)");
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	/**
	 * 예약 가능한 스터디룸 조회 DAO입니다.
	 * @param rsvpDate 예약일
	 * @return list
	 */
	public ArrayList<Student_StudyGroupDTO> availableRoom(String rsvpDate) {
		
		try {
			
			String sql = String.format("select seq, name from tblstudyroom where not name in (select sm.name from tblstudyroom sm inner join tblStudyReservation sn on sn.studyroomSeq = sm.seq where to_char(reservationDate,'yymmdd') = '%s') order by seq",rsvpDate);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<Student_StudyGroupDTO> list = new ArrayList<Student_StudyGroupDTO>();
			
			while(rs.next()) {
				
				Student_StudyGroupDTO dto = new Student_StudyGroupDTO();
				
				dto.setSmName(rs.getString("name"));
				dto.setSmSeq(rs.getString("seq"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_StudyGroupDTO.availableRoom(revpDate)");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 스터디룸 예약 DAO입니다.
	 * @param sel 강의실 번호입니다.
	 * @param sgrSeq 스터디 그룹 등록 번호입니다.
	 * @param rsvpDate 예약날짜입니다.
	 * @return 예약에 성공하면 1, 실패하면 0을 반환합니다.
	 */
	public int reservation(String sel, String sgrSeq, String rsvpDate) {
		
		try {
			
			String sql = "{ call procAddstudyRoomReservation(?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, sgrSeq);
			cstat.setString(2, sel);
			cstat.setString(3, rsvpDate);
			
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Student_StudyGruopDTO.reservation(sgrSeq,rsvpDate)");
			e.printStackTrace();
		}
		
		return 0;
	}

	

}
