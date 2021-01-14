package com.test.dto;

/**
 * 
 * 스터디그룹 등록 DTO
 * @author jinhu
 * seq 등록번호
 * studygroupSeq 스터디그룹 번호
 * signUpSeq 수강번호
 * groupRank 직책
 *
 */
public class StudygroupRegistrationDTO {

	private String seq;
	private String studygroupSeq;
	private String signUpSeq;
	private String groupRank;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStudygroupSeq() {
		return studygroupSeq;
	}
	public void setStudygroupSeq(String studygroupSeq) {
		this.studygroupSeq = studygroupSeq;
	}
	public String getSignUpSeq() {
		return signUpSeq;
	}
	public void setSignUpSeq(String signUpSeq) {
		this.signUpSeq = signUpSeq;
	}
	public String getGroupRank() {
		return groupRank;
	}
	public void setGroupRank(String groupRank) {
		this.groupRank = groupRank;
	}
	
	
	
}
