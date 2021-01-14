package com.test.dto;

/**
 * OpenCourseDTO. 개설 과정 정보를 담은 클래스입니다.
 * seq 개설 과정 번호
 * name 과정명
 * startDate 개설 과정 시작일
 * endDate 개설 과정 종료일
 * classroomName 강의실명
 * whetherRegister 개설 과목 등록 여부
 * personnel 등록 인원수
 * status 개설 과정 상태
 * @author 이찬미
 *
 */
public class OpenCourseDTO {

	private int seq;
	private String name;
	private String startDate;
	private String endDate;
	private String classroomName;
	private String whetherRegister;
	private int personnel;
	private String status;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public String getWhetherRegister() {
		return whetherRegister;
	}
	public void setWhetherRegister(String whetherRegister) {
		this.whetherRegister = whetherRegister;
	}
	public int getPersonnel() {
		return personnel;
	}
	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
