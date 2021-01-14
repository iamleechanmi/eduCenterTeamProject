package com.test.dto;

/**
 * 교사 정보를 담은 클래스입니다.
 * seq 교사 번호
 * name 이름
 * ssn 주민번호 뒷자리
 * tel 전화번호
 * @author 이찬미
 *
 */
public class TeacherDTO {

	private String seq;
	private String name;
	private String ssn;
	private String tel;
	
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
}
