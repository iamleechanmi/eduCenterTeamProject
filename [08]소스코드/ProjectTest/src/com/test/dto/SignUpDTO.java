package com.test.dto;

public class SignUpDTO {

	private String seq;
	private String OpenCourseSeq;
	private String studentSeq;
	private String status;
	private String endDate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getOpenCourseSeq() {
		return OpenCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		OpenCourseSeq = openCourseSeq;
	}
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
