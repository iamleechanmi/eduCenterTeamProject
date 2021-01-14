package com.test.dto;


/**
 * 
 * 진행 중인 과정 DTO
 * @author 이진혁
 * seq 과정 번호
 * courseName 과정명
 * classroomName 강의실명
 *
 */
public class InTheWorkCourseDTO {
	
	private String seq;
	private String courseName;
	private String classroomName;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	
	

}
