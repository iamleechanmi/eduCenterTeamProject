package com.test.dto;

/**
 * 
 * @author 오수경
 * seq 교육생 번호
 * name 교육생 이름
 * ssn 교육생 주민등록번호 뒷자리
 * tel 교육생 전화번호
 * registrationDate 교육생 등록일
 *
 */
public class StudentDTO {
	
	private String seq;
	private String name;
	private String ssn;
	private String tel;
	private String registrationDate;
	
	private String registrationCount;
	
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationCount() {
		return registrationCount;
	}
	public void setRegistrationCount(String registrationCount) {
		this.registrationCount = registrationCount;
	}
	
}
