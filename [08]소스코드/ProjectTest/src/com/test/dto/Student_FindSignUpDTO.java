package com.test.dto;

/**
 * 교육색 수강목록 DTO
 * @author 오수경
 * suSeq : 수강 번호
 * ocSeq : 개설 과정 번호
 * cName : 강의명
 * ocStartDate : 과정 시작일
 * ocEndDate : 과정 종료일
 * suEndDate : 과정 수료/중도탈락일 및 수료 여부
 * crName : 강의실명
 * suStatus : 수강 상태
 * sName : 교육생 이름
 * sTel : 교육생 번호
 * sRegistrationDate : 교육생 등록일
 *
 */

public class Student_FindSignUpDTO {
	private String suSeq;
	private String ocSeq;
	private String cName;
	private String ocStartDate;
	private String ocEndDate;
	private String suEndDate;
	private String crName;
	private String suStatus;
	private String sName;
	private String sTel;
	private String sRegistrationDate;
	
	public String getSuSeq() {
		return suSeq;
	}
	public void setSuSeq(String suSeq) {
		this.suSeq = suSeq;
	}
	public String getOcSeq() {
		return ocSeq;
	}
	public void setOcSeq(String ocSeq) {
		this.ocSeq = ocSeq;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getOcStartDate() {
		return ocStartDate;
	}
	public void setOcStartDate(String ocStartDate) {
		this.ocStartDate = ocStartDate;
	}
	public String getOcEndDate() {
		return ocEndDate;
	}
	public void setOcEndDate(String ocEndDate) {
		this.ocEndDate = ocEndDate;
	}
	public String getSuEndDate() {
		return suEndDate;
	}
	public void setSuEndDate(String suEndDate) {
		this.suEndDate = suEndDate;
	}
	public String getCrName() {
		return crName;
	}
	public void setCrName(String crName) {
		this.crName = crName;
	}
	public String getSuStatus() {
		return suStatus;
	}
	public void setSuStatus(String suStatus) {
		this.suStatus = suStatus;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsTel() {
		return sTel;
	}
	public void setsTel(String sTel) {
		this.sTel = sTel;
	}
	public String getsRegistrationDate() {
		return sRegistrationDate;
	}
	public void setsRegistrationDate(String sRegistrationDate) {
		this.sRegistrationDate = sRegistrationDate;
	}

}
