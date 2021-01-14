package com.test.dto;
/**
 * 스터디 그룹 DTO입니다.
 * @author 오수경
 * sName 학생 이름
 * sgSeq 스터디 그룹 번호
 * sgGoal 스터디 그룹 목표
 * sgEndDqte 스터디 그룹 종료일
 * sgStartDate 스터디 그룹 시작일
 * sgrGroupRank 스터디 그룹 직위
 * srContent 스터디 일지 내용
 * srRecordDate 스터디 일지 작성일
 * smName 스터디룸명
 * smSeq 스터디룸 번호
 * srRsvpDate 스터디룸 예약일
 * sgrSeq 스터디 그룹 등록 번호
 *
 */
public class Student_StudyGroupDTO {
	
	private String sName;
	private String sgSeq;
	private String sgGoal;
	private String sgRegDate;
	private String sgEndDate;
	private String sgrGroupRank;
	private String srContent;
	private String srRecordDate;
	private String smName;
	private String smSeq;
	private String srRsvpDate;
	private String sgrSeq;
	
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getSgSeq() {
		return sgSeq;
	}
	public void setSgSeq(String sgSeq) {
		this.sgSeq = sgSeq;
	}
	public String getSgGoal() {
		return sgGoal;
	}
	public void setSgGoal(String sgGoal) {
		this.sgGoal = sgGoal;
	}
	public String getSgRegDate() {
		return sgRegDate;
	}
	public void setSgRegDate(String sgRegDate) {
		this.sgRegDate = sgRegDate;
	}
	public String getSgEndDate() {
		return sgEndDate;
	}
	public void setSgEndDate(String sgEndDate) {
		this.sgEndDate = sgEndDate;
	}
	public String getSgrGroupRank() {
		return sgrGroupRank;
	}
	public void setSgrGroupRank(String sgrGroupRank) {
		this.sgrGroupRank = sgrGroupRank;
	}
	public String getSrContent() {
		return srContent;
	}
	public void setSrContent(String srContent) {
		this.srContent = srContent;
	}
	public String getSrRecordDate() {
		return srRecordDate;
	}
	public void setSrRecordDate(String srRecordDate) {
		this.srRecordDate = srRecordDate;
	}
	public String getSmName() {
		return smName;
	}
	public void setSmName(String smName) {
		this.smName = smName;
	}
	public String getSrRsvpDate() {
		return srRsvpDate;
	}
	public void setSrRsvpDate(String srRsvpDate) {
		this.srRsvpDate = srRsvpDate;
	}
	public String getSgrSeq() {
		return sgrSeq;
	}
	public void setSgrSeq(String sgrSeq) {
		this.sgrSeq = sgrSeq;
	}
	public String getSmSeq() {
		return smSeq;
	}
	public void setSmSeq(String smSeq) {
		this.smSeq = smSeq;
	}

	
}
