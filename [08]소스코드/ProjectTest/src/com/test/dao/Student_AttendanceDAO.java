package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.util.ArrayList;

import com.test.dto.Student_AttendanceDTO;
import com.test.dto.Student_FindSignUpDTO;

import oracle.jdbc.OracleTypes;

/**
 * 교육생 출결 조회 DAO입니다.
 * @author 오수경
 *
 */
public class Student_AttendanceDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 생성자
	 */
	public Student_AttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("Student_FindAttendanceDTO.생성자");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 입실 체크 DAO
	 * @param seq 학생 번호
	 * @return 성공하면 1, 실패하면 0을 돌려줍니다.
	 */
	public int checkIn(String seq) {
		
		try {
			
			String sql = "{ call procAddAttendanceOn(?) }";			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			
			// 성공하면 1, 실패하면 0 반환
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Student_AttendanceDAO.checkIn(seq)");
			e.printStackTrace();
		}
		
		return 0;
	}
	

	/**
	 * 퇴실 체크 DAO
	 * @param seq 학생 번호
	 * @return 성공하면 1, 실패하면 0을 돌려줍니다.
	 */
	public int checkOut(String seq) {
		
		try {
			
			String sql = "{ call procAddAttendanceOff(?) }";			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			
			// 성공하면 1, 실패하면 0 반환
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Student_AttendanceDAO.checkOut(seq)");
			e.printStackTrace();
		}
		return 0;
		
		
	}

	/**
	 * 외출 체크 DAO
	 * @param seq 학생 번호
	 * @return 성공하면 1, 실패하면 0을 돌려줍니다.
	 */
	public int checkOuting(String seq) {
		
		try {
			
			String sql = "{ call procAddAttendanceOuting(?) }";			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			
			// 성공하면 1, 실패하면 0 반환
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Student_AttendanceDAO.checkOuting(seq)");
			e.printStackTrace();
		}
		
		return 0;
		
		
	}

	


	
	/**
	 * 전체 출결 조회 DAO입니다
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 종료일
	 * @return list
	 * sSeq : 교육생 번호
	 * suSeq : 수강 번호
	 * attendanceDate : 출석일
	 * enterTime : 입실 시간
	 * exitTime : 퇴실 시간
	 * status : 상태
	 * 
	 */
	public ArrayList<Student_AttendanceDTO> all(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		
		
		try {
			
			String view = String.format("create or replace view vwattendance as select to_date('%s','yy/mm/dd') + level -1 as regdate from dual    connect by level <=(to_date('%s','yy/mm/dd') - to_date('%s','yy/mm/dd') + 1)", ocStartDate, ocEndDate,ocStartDate);
			
			rs = stat.executeQuery(view);
			
			String sql ="{ call procFindAttendanceAll(?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.setString(2, suSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs=(ResultSet)cstat.getObject(3);
			
			ArrayList<Student_AttendanceDTO> list = new ArrayList<Student_AttendanceDTO>();
			
			while(rs.next()) {
				Student_AttendanceDTO dto = new Student_AttendanceDTO();
				
				dto.setsSeq(rs.getString("sSeq"));
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setAttendanceDate(rs.getString("attendanceDate"));
				dto.setEnterTime(rs.getString("enterTime"));
				dto.setExitTime(rs.getString("exitTime"));
				dto.setStatus(rs.getString("status"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("Student_FindAttendanceDAO.all");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 입력 연도 출결 조회 DAO입니다.
	 * @param seq 교육생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 종료일
	 * @param year 조회 연도(yy)
	 * @return list
	 * sSeq : 교육생 번호
	 * suSeq : 수강 번호
	 * attendanceDate : 출석일
	 * enterTime : 입실 시간
	 * exitTime : 퇴실 시간
	 * status : 상태
	 * 
	 */
	public ArrayList<Student_AttendanceDTO> year(String seq, String suSeq, String ocStartDate, String ocEndDate,String year) {
		
		try {
			
			String view = String.format("create or replace view vwattendance as select to_date('%s','yy/mm/dd') + level -1 as regdate from dual    connect by level <=(to_date('%s','yy/mm/dd') - to_date('%s','yy/mm/dd') + 1)", ocStartDate, ocEndDate,ocStartDate);
			
			rs = stat.executeQuery(view);
			
			String sql ="{ call procFindAttendanceYear(?,?,?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.setString(2, suSeq);
			cstat.setString(3, year);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs=(ResultSet)cstat.getObject(4);
			
			ArrayList<Student_AttendanceDTO> list = new ArrayList<Student_AttendanceDTO>();
			
			while(rs.next()) {
				Student_AttendanceDTO dto = new Student_AttendanceDTO();
				
				dto.setsSeq(rs.getString("sSeq"));
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setAttendanceDate(rs.getString("attendanceDate"));
				dto.setEnterTime(rs.getString("enterTime"));
				dto.setExitTime(rs.getString("exitTime"));
				dto.setStatus(rs.getString("status"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_FindAttendanceDTO.year()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 입력 연도/월 출결 조회 DAO입니다.
	 * @param seq 교육생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 종료일
	 * @param month 조회 연도/월(yy/mm)
	 * @return list를 반환합니다.
	 * sSeq : 교육생 번호
	 * suSeq : 수강 번호
	 * attendanceDate : 출석일
	 * enterTime : 입실 시간
	 * exitTime : 퇴실 시간
	 * status : 상태
	 * 
	 */
	public ArrayList<Student_AttendanceDTO> month(String seq, String suSeq, String ocStartDate, String ocEndDate,
			String month) {
		try {
			
			String view = String.format("create or replace view vwattendance as select to_date('%s','yy/mm/dd') + level -1 as regdate from dual    connect by level <=(to_date('%s','yy/mm/dd') - to_date('%s','yy/mm/dd') + 1)", ocStartDate, ocEndDate,ocStartDate);
			
			rs = stat.executeQuery(view);
			
			String sql = "{ call procFindAttendanceMonth (?,?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.setString(2, suSeq);
			cstat.setString(3, month);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(4);
			
			ArrayList<Student_AttendanceDTO> list = new ArrayList<Student_AttendanceDTO>();
			
			while(rs.next()) {
				
				Student_AttendanceDTO dto = new Student_AttendanceDTO();
				
				dto.setsSeq(rs.getString("sSeq"));
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setAttendanceDate(rs.getString("attendanceDate"));
				dto.setEnterTime(rs.getString("enterTime"));
				dto.setExitTime(rs.getString("exitTime"));
				dto.setStatus(rs.getString("status"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_FindAttendanceDTO.month()");
			e.printStackTrace();
		}
		
		return null;
	}
	


	/**
	 * 입력 연도/월 출결 조회 DAO입니다.
	 * @param seq 교육생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 종료일
	 * @param date 조회 연도/월(yy/mm)
	 * @return list를 반환합니다.
	 * sSeq : 교육생 번호
	 * suSeq : 수강 번호
	 * attendanceDate : 출석일
	 * enterTime : 입실 시간
	 * exitTime : 퇴실 시간
	 * status : 상태
	 * 
	 */
	public ArrayList<Student_AttendanceDTO> date(String seq, String suSeq, String ocStartDate, String ocEndDate,
			String date) {
		
		try {
			
			String view = String.format("create or replace view vwattendance as select to_date('%s','yy/mm/dd') + level -1 as regdate from dual    connect by level <=(to_date('%s','yy/mm/dd') - to_date('%s','yy/mm/dd') + 1)", ocStartDate, ocEndDate,ocStartDate);
			
			rs = stat.executeQuery(view);
			
			String sql = "{ call procFindAttendanceDate (?,?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.setString(2, suSeq);
			cstat.setString(3, date);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(4);
			
			ArrayList<Student_AttendanceDTO> list = new ArrayList<Student_AttendanceDTO>();
			
			while(rs.next()) {
				
				Student_AttendanceDTO dto = new Student_AttendanceDTO();
				
				dto.setsSeq(rs.getString("sSeq"));
				dto.setSuSeq(rs.getString("suSeq"));
				dto.setAttendanceDate(rs.getString("attendanceDate"));
				dto.setEnterTime(rs.getString("enterTime"));
				dto.setExitTime(rs.getString("exitTime"));
				dto.setStatus(rs.getString("status"));
				
				
				list.add(dto);
				
			}
			
			return list;			
			
			
		} catch (Exception e) {
			System.out.println("Student_AttendaneDAO.date()");
			e.printStackTrace();
		}
		
		return null;
		
	}

	/**
	 * 출석 상태 조회 DAO
	 * @param seq 학생 번호
	 * @param perior 조회 기간
	 * @param format 년/월/일
	 * @return list을 반환합니다.
	 * attendanceCount 출석 횟수
	 * tardyCount 지각 횟수
	 * earlyleaveCount 조퇴 횟수
	 * sickLeaveCount 병가 횟수
	 * avsentCount 결석 횟수
	 * leavingCount 외출 횟수
	 */
	public ArrayList<Student_AttendanceDTO> status(String seq,String perior,String format) {
		
		try {
			

			String where = "";
			
			if(perior!=null)
				where = String.format("and to_char(attendanceDate,'%s') = '%s'", format, perior);
				
			String sql = String.format("select count(case when status = '출석' then 1 end) as attendanceCount, count(case when status = '지각' then 1 end) as tardyCount, count(case when status = '조퇴' then 1 end) as earlyLeaveCount, count(case when status = '외출' then 1 end) as leavingCount, count(case when status = '병가' then 1 end) as sickLeaveCount, count(case when status = '결석' then 1 end) as absentCount from vwfindattendance2 where sseq = %s %s", seq, where);
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<Student_AttendanceDTO> list = new ArrayList<Student_AttendanceDTO>();
			
			while(rs.next()) {
				
				Student_AttendanceDTO dto = new Student_AttendanceDTO();
				
				dto.setAttendanceCount(rs.getInt("attendanceCount"));
				dto.setAbsentCount(rs.getInt("absentCount"));
				dto.setEarlyLeaveCount(rs.getInt("earlyLeaveCount"));
				dto.setTardyCount(rs.getInt("tardyCount"));
				dto.setSickLeaveCount(rs.getInt("sickLeaveCount"));
				dto.setLeavingCount(rs.getInt("leavingCount"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Student_AttendanceDAO.status(seq)");
			e.printStackTrace();
		}
		
		return null;
	}


	




	
}
