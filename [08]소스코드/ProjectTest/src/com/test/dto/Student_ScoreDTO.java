package com.test.dto;


/**
 * 교육생 점수 DTO입니다.
 * @author 오수경
 * studentSeq 교육생 번호
 * signUpSeq 수강 번호
 * subjectName 과목명
 * attendancePoint 출석배점
 * writtenPoint 필기배점
 * practicalPoint 실기배점
 * attendanceScore 출석점수
 * writtenScore 필기점수
 * practicalScore 실기저수
 * whetherGrade 점수등록여부
 * whetherExam 시험문제등록여부
 */
public class Student_ScoreDTO {
	
	private String studentSeq;
	private String siguUpSeq;
	private String subjectName;
	private String attendancepoint;
	private String writtenpoint;
	private String practicalpoint;
	private String attendancescore;
	private String writtenscore;
	private String practicalscore;
	private String whetherGrade;
	private String whetherExam;
	
	
	public String getWhetherExam() {
		return whetherExam;
	}
	public void setWhetherExam(String whetherExam) {
		this.whetherExam = whetherExam;
	}
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getSiguUpSeq() {
		return siguUpSeq;
	}
	public void setSiguUpSeq(String siguUpSeq) {
		this.siguUpSeq = siguUpSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getAttendancepoint() {
		return attendancepoint;
	}
	public void setAttendancepoint(String attendancepoint) {
		this.attendancepoint = attendancepoint;
	}
	public String getWrittenpoint() {
		return writtenpoint;
	}
	public void setWrittenpoint(String writtenpoint) {
		this.writtenpoint = writtenpoint;
	}
	public String getPracticalpoint() {
		return practicalpoint;
	}
	public void setPracticalpoint(String practicalpoint) {
		this.practicalpoint = practicalpoint;
	}
	public String getAttendancescore() {
		return attendancescore;
	}
	public void setAttendancescore(String attendancescore) {
		this.attendancescore = attendancescore;
	}
	public String getWrittenscore() {
		return writtenscore;
	}
	public void setWrittenscore(String writtenscore) {
		this.writtenscore = writtenscore;
	}
	public String getPracticalscore() {
		return practicalscore;
	}
	public void setPracticalscore(String practicalscore) {
		this.practicalscore = practicalscore;
	}
	public String getWhetherGrade() {
		return whetherGrade;
	}
	public void setWhetherGrade(String whetherGrade) {
		this.whetherGrade = whetherGrade;
	}


}
