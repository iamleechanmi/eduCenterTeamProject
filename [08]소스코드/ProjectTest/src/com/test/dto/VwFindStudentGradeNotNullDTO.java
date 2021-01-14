package com.test.dto;

public class VwFindStudentGradeNotNullDTO {
/*
 * signUpSeq(수강번호), studentSeq(교육생번호), 
 * examScoreSeq(성적번호), openSubjectSeq(개설과목번호), subjectName(과목명), 
 * openCourseSeq(과정명), StudentName(교육생이름), ssn(뒷자리), 
 * attendanceScore(출석성적), writtenscore(필기성적),practicalScore(실기성적),
 * attendancePoint(출석배점), writtenPoint(필기배점), practicalPoint(실기배점)
 * 
 */
	
	private String signUpSeq;
	private String studentSeq;
	private String examScoreSeq;
	private String openSubjectSeq;
	private String CourseName;
	private String subjectName;
	private String openCourseSeq;
	private String StudentName;
	private String ssn;
	private String attendanceScore;
	private String writtenscore;
	private String practicalScore;
	private String attendancePoint;
	private String writtenPoint;
	
	public String getSignUpSeq() {
		return signUpSeq;
	}
	public void setSignUpSeq(String signUpSeq) {
		this.signUpSeq = signUpSeq;
	}
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getExamScoreSeq() {
		return examScoreSeq;
	}
	public void setExamScoreSeq(String examScoreSeq) {
		this.examScoreSeq = examScoreSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAttendanceScore() {
		return attendanceScore;
	}
	public void setAttendanceScore(String attendanceScore) {
		this.attendanceScore = attendanceScore;
	}
	public String getWrittenscore() {
		return writtenscore;
	}
	public void setWrittenscore(String writtenscore) {
		this.writtenscore = writtenscore;
	}
	public String getPracticalScore() {
		return practicalScore;
	}
	public void setPracticalScore(String practicalScore) {
		this.practicalScore = practicalScore;
	}
	public String getAttendancePoint() {
		return attendancePoint;
	}
	public void setAttendancePoint(String attendancePoint) {
		this.attendancePoint = attendancePoint;
	}
	public String getWrittenPoint() {
		return writtenPoint;
	}
	public void setWrittenPoint(String writtenPoint) {
		this.writtenPoint = writtenPoint;
	}
	public String getPracticalPoint() {
		return practicalPoint;
	}
	public void setPracticalPoint(String practicalPoint) {
		this.practicalPoint = practicalPoint;
	}
	private String practicalPoint;
	
	
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
}
