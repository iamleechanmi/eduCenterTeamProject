package com.test.dto;

/**
 * OpenCourseStudentDTO. 특정 개설 과정의 교육생 정보를 담은 클래스입니다.
 * openCourseSeq 개설 과정 번호
 * seq 교육생 번호
 * name 교육생 이름
 * ssn 주민번호 뒷자리
 * tel 전화번호
 * registrationDate 등록일
 * status 수료 상태
 * endDate 수료 및 중단일
 * @author 이찬미
 *
 */
public class OpenCourseStudentDTO {

	private String openCourseSeq;
	private String seq;
	private String name;
	private String ssn;
	private String tel;
	private String registrationDate;
	private String status;
	private String endDate;
	
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
