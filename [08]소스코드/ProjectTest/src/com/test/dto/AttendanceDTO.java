package com.test.dto;

public class AttendanceDTO {

	private String seq;
	private String signUpSeq;
	private String attendanceDate;
	private String enterTime;
	private String exitTime;
	private String status;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSignUpSeq() {
		return signUpSeq;
	}
	public void setSignUpSeq(String signUpSeq) {
		this.signUpSeq = signUpSeq;
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
	
}
