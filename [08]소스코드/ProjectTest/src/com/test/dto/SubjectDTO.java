package com.test.dto;

/**
 * SubjectDTO. 과목 정보를 담은 클래스입니다.
 * seq 과목 번호
 * name 과목명
 * period 과목 기간
 * @author 이찬미
 *
 */
public class SubjectDTO {
	
	private String seq;
	private String name;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
}
