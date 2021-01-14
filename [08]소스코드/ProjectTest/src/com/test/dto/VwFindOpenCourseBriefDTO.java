package com.test.dto;

public class VwFindOpenCourseBriefDTO {
	
	private String courseSeq;
	private String courseName;
	private String classroomName;
	private String openCourseStartDate;
	
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
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
	public String getOpenCourseStartDate() {
		return openCourseStartDate;
	}
	public void setOpenCourseStartDate(String openCourseStartDate) {
		this.openCourseStartDate = openCourseStartDate;
	} 
	
}
