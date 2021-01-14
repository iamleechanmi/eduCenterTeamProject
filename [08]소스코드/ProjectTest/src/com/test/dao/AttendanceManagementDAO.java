package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.VwFindOpenCourseBriefDTO;
import com.test.dto.VwfindAttByCourseDTO;
import com.test.dto.VwfindAttByStudentDTO;

/**
 * 출결 관리 및 출결 조회에 대한 DAO 메서드를 모아놓은 클래스입니다.
 * @author 이청강
 */

public class AttendanceManagementDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public AttendanceManagementDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("AttendanceManagementDAO.AttendanceManagementDAO()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 개설과정 정보를 담은 ArrayList를 반환하는 메서드입니다.
	 * 매개변수를 받아 특정 개설과정의 정보만을 담은 ArrayList를 반환할 수 있습니다.
	 * @param courseSeq 조회할 특정 개설과정의 번호
	 * @return 개설과정 정보를 담은 ArrayList
	 */
	public ArrayList<VwFindOpenCourseBriefDTO> findOpenCourseBrief(String courseSeq) {
		
		try {
			
			String where = "";
			if (courseSeq != null) {
				
				where = "where courseSeq = " + courseSeq;
			}
			
			ArrayList<VwFindOpenCourseBriefDTO> list = new ArrayList<VwFindOpenCourseBriefDTO>();
			
			String sql = "select * from VwFindOpenCourseBrief " + where;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				VwFindOpenCourseBriefDTO dto = new VwFindOpenCourseBriefDTO();
				
				dto.setCourseSeq(rs.getString("courseSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setOpenCourseStartDate(rs.getString("openCourseStartDate"));
				dto.setClassroomName(rs.getString("classroomName"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("AttendanceManagementDAO.findOpenCourseBrief()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 단위기간별로 특정 과정의 출결정보를 담은 ArrayList를 반환하는 메서드입니다.
	 * 연,월,일별로 검색할 수 있게끔 오버로딩하였습니다.
	 * 
	 * vwFindAbsent : 출결테이블과 교육생, 수강 테이블을 내부 조인한 뷰입니다.
	 * vwFindAbsent2 : vwFindAbsent에서 특정 교육생의 출결정보만을 가져오는 뷰입니다.
	 * vwTemp : 특정기간동안의 날짜를 모두 가져오는, 비교용도로 만든 뷰입니다.
	 * vwFindAbsent3 : vwFindAbsent2에서 가져온 출석일과 vwTemp의 날짜를 비교해 무단결석이 반영된 근태상황과 비교용 날짜를 컬럼으로 갖는 뷰입니다.
	 * vwFindAbsent4 : vwFindAbsnet3의 근태상황 각각을 집계함수로 세어 각각 컬럼으로 갖는 뷰입니다.
	 * 
	 * @param courseSeq 출결정보를 가져올 개설 과정의 번호
	 * @param year 출결정보를 검색할 단위기간 연도
	 * @param month 단위기간 월
	 * @param date 단위기간 일
	 * @return 단위기간별로 특정 과정의 출결정보를 담은 ArrayList를 반환합니다.
	 */
	public ArrayList<VwfindAttByCourseDTO> findAttByCourse(String courseSeq, String year, String month, String date) {
		
		try {
			
			String where = "";
			
			if (year != null) {
				where = String.format("where to_char(tempDate, 'yyyy') = '%s'", year);
			}
			
			if (month != null) {
				where = String.format("where to_char(tempDate, 'yyyy/mm') = '%s/%s'"
										, year, month);
			}
			
			if (date != null) {
				where = String.format("where to_char(tempDate, 'yyyy/mm/dd') = '%s/%s/%s'"
										, year, month, date);
			}
			
			ArrayList<VwfindAttByCourseDTO> list = new ArrayList<VwfindAttByCourseDTO>();
			
			String sql = "create or replace view vwFindAbsent4 as select count(case when attendanceStatus = '출석' then 1 end) as attendanceCount, count(case when attendanceStatus = '지각' then 1 end) as tardyCount, count(case when attendanceStatus = '조퇴' then 1 end) as earlyLeaveCount, count(case when attendanceStatus = '외출' then 1 end) as leavingCount, count(case when attendanceStatus = '병가' then 1 end) as sickLeaveCount, count(case when attendanceStatus = '결석' then 1 end) as absentCount from vwFindAbsent3 "
					+ where;
			
			stat.executeUpdate(sql);
			
			sql = " select sd.name as name, sd.seq as studentSeq from tblOpenCourse oc inner join tblSignUp su on oc.seq = su.openCourseSeq inner join tblStudent sd on su.studentSeq = sd.seq where oc.seq = " + courseSeq;
			
			
			rs = stat.executeQuery(sql);
			
			String tempSeq = "";
			
			
			while (rs.next()) {
				
				//tempSeq = rs.getString("studentSeq");
				
				VwfindAttByCourseDTO dto = new VwfindAttByCourseDTO();
				dto.setStudentName(rs.getString("name"));
				dto.setStudentSeq(rs.getString("studentSeq"));
				
				list.add(dto);
	
			}
			
			for (int i=0; i<list.size(); i++) {
				
				sql = String.format("create or replace view vwFindAbsent2 as select * from vwFindAbsent where studentseq = %s", list.get(i).getStudentSeq());
				
				stat.executeUpdate(sql);
				
				sql = "select * from vwFindAbsent4";
				
				rs = stat.executeQuery(sql);
				rs.next();
				
				list.get(i).setAttendanceCount(rs.getString("attendanceCount"));
				list.get(i).setTardyCount(rs.getString("tardyCount"));
				list.get(i).setEarlyLeaveCount(rs.getString("earlyLeaveCount"));
				list.get(i).setLeavingCount(rs.getString("leavingCount"));
				list.get(i).setSickLeaveCount(rs.getString("sickLeaveCount"));
				list.get(i).setAbsentCount(rs.getString("absentCount"));
				
			}				
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AttendanceManagementDAO.findAttByCourse()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 특정 교육생의 출결정보가 담긴 ArrayList를 반환하는 메서드입니다.
	 * 연/월/일별로 실행가능하도록 오버로딩하였습니다.
	 * 
	 * vwfindAbsent 등의 뷰에 대한 설명은 아래 클래스의 주석을 참고해주세요.
	 * @see findAttByCourse
	 * 
	 * @param studentSeq 출력할 출결 정보를 식별할 특정 교육생 번호
	 * @param year 출력할 출결 정보의 조건이 되는 기간단위 중 연도
	 * @param month 기간단위 중 월
	 * @param date 기간단위 중 일
	 * @return 특정 교육생의 출결정보를 저장한 DTO를 담은 ArrayList
	 */
	public ArrayList<VwfindAttByStudentDTO> findAttByStudent(String studentSeq, String year, String month,
			String date) {
		
		try {

			String where = "";
			
			if (year != null) {
				where = String.format("where to_char(tempDate, 'yyyy') = '%s'", year);
			}
			
			if (month != null) {
				where = String.format("where to_char(tempDate, 'yyyy/mm') = '%s/%s'"
										, year, month);
			}
			
			if (date != null) {
				where = String.format("where to_char(tempDate, 'yyyy/mm/dd') = '%s/%s/%s'"
										, year, month, date);
			}
			
			ArrayList<VwfindAttByStudentDTO> list = new ArrayList<VwfindAttByStudentDTO>();
			
			String sql = String.format("create or replace view vwFindAbsent2 as select * from vwFindAbsent where studentseq = %s", studentSeq);
			
			stat.executeUpdate(sql);
			
			sql = "select * from vwFindAbsent3 " + where;
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				VwfindAttByStudentDTO dto = new VwfindAttByStudentDTO();
				
				dto.setAttendanceStatus(rs.getString("attendanceStatus"));
				dto.setTempDate(rs.getString("tempDate"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AttendanceManagementDAO.findAttByStudent()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 특정 교육생의 근태상황을 수정하는 메서드입니다.
	 * @param statusInput 수정내용에 해당하는 새 근태상황
	 * @param oneDate 출결을 수정할 출결정보의 날짜
	 * @param studentSeq 출결을 수정할 교육생 번호
	 */
	public void editAttendanceStatus(String statusInput, String oneDate, String studentSeq) {
		
		try {

			String sql = String.format("select signUpSeq from tblAttendance at inner join tblSignUp su on at.signUpseq = su.seq where su.studentSeq = %s and at.attendanceDate = '%s'"
					, studentSeq
					, oneDate);
			
			rs = stat.executeQuery(sql);
			rs.next();
			String signUpSeq = rs.getString("signUpseq");
			
			String attTime = "09:00:00"; //입실시간
			String outTime = "17:55:00"; //퇴실시간
			switch (statusInput) {
			case "출석":
				break;
			case "지각":
				attTime = "10:00:00";
				break;
			case "조퇴":
				outTime = "15:00:00";
				break;
			case "외출":
				outTime = null;
				break;
			default:
				outTime = "10:00:00";
				break;
			}
			
			sql = String.format("update tblAttendance set status = '%s' where signUpseq = %s and to_char(attendanceDate, 'yy/mm/dd') = '%s'", statusInput, signUpSeq, oneDate);
			if( stat.executeUpdate(sql) > 0) {
				System.out.println("해당 출결정보의 수정이 완료되었습니다.");
			} else {
				sql = String.format("insert into tblAttendance values (seqAttendance.nextVal, %s, to_date('20%s', 'yyyy-mm-dd'), to_date('%s','hh24:mi:ss'), to_date('%s', 'hh24:mi:ss'), '%s')"
						, signUpSeq, oneDate, attTime, outTime, statusInput); 

				System.out.println(stat.executeUpdate(sql));
				System.out.println("해당 출결정보의 수정이 완료되었습니다.");
			}
			

		} catch (Exception e) {
			System.out.println("AttendanceManagementDAO.editAttendanceStatus()");
			e.printStackTrace();
		}
		
		
	}
	
	
}



















