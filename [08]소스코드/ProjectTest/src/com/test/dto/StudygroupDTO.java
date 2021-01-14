package com.test.dto;

/**
 * 
 * 스터디그룹 DTO
 * @author 이진혁
 * seq 스터디그룹 번호
 * goal 스터디그룹 목적
 * registrationDate 등록일
 * endDate 마감일
 *
 */
public class StudygroupDTO {
	
	private String seq;
	private String goal;
	private String registrationDate;
	private String endDate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
