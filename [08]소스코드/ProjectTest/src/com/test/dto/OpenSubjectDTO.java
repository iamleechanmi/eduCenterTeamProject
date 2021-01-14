package com.test.dto;

/**
 * OpenSubjectDTO. 개설 과목 정보를 담은 클래스입니다.
 * subjectSeq 개설 과목 번호
 * subjectName 개설 과목명
 * courseName 개설 과정명
 * subjectStartDate 개설 과목 시작일
 * subjectEndDate 개설 과목 종료일
 * courseStartDate 개설 과정 시작일
 * courseEndDate 개설 과정 종료일
 * whetherExam 시험 문제 등록 여부
 * whetherGrade 성적 등록 여부
 * classroomName 강의실명
 * teacherName 교사명
 * textbookName 교재명
 * @author 이찬미
 *
 */
public class OpenSubjectDTO {

	private int subjectSeq;
	private String subjectName;
	private String courseName;
	private String subjectStartDate;
	private String subjectEndDate;
	private String courseStartDate;
	private String courseEndDate;
	private String whetherExam;
	private String whetherGrade;
	private String classroomName;
	private String teacherName;
	private String textbookName;
	
	public int getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(int subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSubjectStartDate() {
		return subjectStartDate;
	}
	public void setSubjectStartDate(String subjectStartDate) {
		this.subjectStartDate = subjectStartDate;
	}
	public String getSubjectEndDate() {
		return subjectEndDate;
	}
	public void setSubjectEndDate(String subjectEndDate) {
		this.subjectEndDate = subjectEndDate;
	}
	public String getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
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
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTextbookName() {
		return textbookName;
	}
	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
	}
	
}
