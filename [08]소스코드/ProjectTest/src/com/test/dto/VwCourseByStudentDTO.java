package com.test.dto;

public class VwCourseByStudentDTO {
	
	private String studentSeq;
	private String studentName;
	private String openCourseSeq;
	private String courseName;
	private String openCourseStartDate;
	private String openCourseEndDate;
	
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getOpenCourseStartDate() {
		return openCourseStartDate;
	}
	public void setOpenCourseStartDate(String openCourseStartDate) {
		this.openCourseStartDate = openCourseStartDate;
	}
	public String getOpenCourseEndDate() {
		return openCourseEndDate;
	}
	public void setOpenCourseEndDate(String openCourseEndDate) {
		this.openCourseEndDate = openCourseEndDate;
	}
	
}
