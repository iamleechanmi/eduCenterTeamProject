package com.test.dto;

/**
 * CourseDTO. 과정 정보를 담은 클래스입니다.
 * seq 과정 번호
 * name 과정명
 * goal 과정 목표
 * period 과정 기간
 * @author 이진혁
 *
 */
public class CourseDTO {
	
	private String seq;
	private String name;
	private String goal;
	private String period;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
}
