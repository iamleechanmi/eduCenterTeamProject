package com.test.dto;

/**
 * 
 * 과정에 듣는 교육생 DTO
 * @author 이진혁
 * seq 교육생 번호
 * openCourseSeq 개설과정번호
 * studentName 교육생 이름
 * studentTel 교육생 번호
 * studentSsn 교육생 주민번호
 * openCourseStatus 개설과정상태
 *
 */
public class StudentInCourseDTO {

	private String seq;
	private String openCourseSeq;
	private String studentName;
	private String studentTel;
	private String studentSsn;
	private String openCourseStatus;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentTel() {
		return studentTel;
	}
	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}
	public String getStudentSsn() {
		return studentSsn;
	}
	public void setStudentSsn(String studentSsn) {
		this.studentSsn = studentSsn;
	}
	public String getOpenCourseStatus() {
		return openCourseStatus;
	}
	public void setOpenCourseStatus(String openCourseStatus) {
		this.openCourseStatus = openCourseStatus;
	}
	
	
	
}
