package com.test.dto;


public class vwFindCourseSubjectDTO {
/*
 * subjectSeq(학생번호), openSubjectSeq(개설과목번호) ,
 *  openCourseSeq(개설과정번호), teacherSeq(교사번호), 
 *  status(교사과목기록상태), subjectName(과목명), 
 *  startdate(개설과정의 시작일), endDate(개설과정의 종료일),
 *   courseName(과정명), 	attendancePoint(출석배점), writtenPoint(필기배점), 
 * practicalPoint(실기배점), whetherExam(시험등록여부), whetherGrade(성적등록여부)
 */
	
	private String subjectSeq;
	private  String openSubjectSeq;
	private  String openCourseSeq;
	private  String teacherSeq;
	private  String status;
	private  String subjectName;
	private String startdate;
	private String endDate;
	private String courseName;
	private String attendancePoint;
	private String writtenPoint;
	private String practicalPoint;
	private String whetherExam;
	private String whetherGrade;
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	public String getWhetherExam() {
		return whetherExam;
	}
	public void setWhetherExam(String whetherExam) {
		this.whetherExam = whetherExam;
	}
	public String getWhetherGrade() {
		return whetherGrade;
	}
	public void setWhetherGrade(String whetherGrade) {
		this.whetherGrade = whetherGrade;
	}
	
	
	
}
