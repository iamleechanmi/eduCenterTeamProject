package com.test.dto;

/**
 * 
 * 관리자 DTO
 * @author 이진혁
 * seq 관리자 번호
 * ssn 주민번호 뒷자리
 * name 이름
 *
 */
public class ManagerDTO {
	
	private String seq;
	private String ssn;
	private String name;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
