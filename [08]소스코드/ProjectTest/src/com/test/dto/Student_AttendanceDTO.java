package com.test.dto;

/**
 * 교육생 출석 DTO
 * @author 오수경
 * suSeq 수강 번호
 * sSeq 교육생 번호
 * attendanceDate 출석일
 * enterTime 입실 시간
 * exitTime 퇴실 시간
 * status 상태
 * attendanceCount 출석 횟수
 * tardyCount 지각 횟수
 * earlyleaveCount 조퇴 횟수
 * sickLeaveCount 병가 횟수
 * avsentCount 결석 횟수
 * leavingCount 외출 횟수
 */

public class Student_AttendanceDTO {
	private String suSeq;
	private String sSeq;
	private String attendanceDate;
	private String enterTime;
	private String exitTime;
	private String status;
	private Integer attendanceCount; 
	private Integer tardyCount;
	private Integer earlyLeaveCount;
	private Integer sickLeaveCount;
	private Integer absentCount;
	private Integer leavingCount;

	
	public String getSuSeq() {
		return suSeq;
	}
	public void setSuSeq(String suSeq) {
		this.suSeq = suSeq;
	}
	public String getsSeq() {
		return sSeq;
	}
	public void setsSeq(String sSeq) {
		this.sSeq = sSeq;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAttendanceCount() {
		return attendanceCount;
	}
	public void setAttendanceCount(Integer attendanceCount) {
		this.attendanceCount = attendanceCount;
	}
	public Integer getTardyCount() {
		return tardyCount;
	}
	public void setTardyCount(Integer tardyCount) {
		this.tardyCount = tardyCount;
	}
	public Integer getEarlyLeaveCount() {
		return earlyLeaveCount;
	}
	public void setEarlyLeaveCount(Integer earlyLeaveCount) {
		this.earlyLeaveCount = earlyLeaveCount;
	}
	public Integer getSickLeaveCount() {
		return sickLeaveCount;
	}
	public void setSickLeaveCount(Integer sickLeaveCount) {
		this.sickLeaveCount = sickLeaveCount;
	}
	public Integer getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Integer absentCount) {
		this.absentCount = absentCount;
	}
	public Integer getLeavingCount() {
		return leavingCount;
	}
	public void setLeavingCount(Integer leavingCount) {
		this.leavingCount = leavingCount;
	}

	

}
