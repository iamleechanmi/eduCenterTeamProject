package com.test.dto;

/**
 * 
 * 스터디 그룹 멤버 DTO
 * @author 이진혁
 * seq 등록번호
 * name 교육생 이름
 * tel 교육생 번호
 * studygroupSeq 스터디그룹 번호
 * rank 직책
 *
 */
public class StudygroupMemberDTO {
	
	private String seq;
	private String name;
	private String tel;
	private String studygroupSeq;
	private String rank;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStudygroupSeq() {
		return studygroupSeq;
	}
	public void setStudygroupSeq(String studtygroupSeq) {
		this.studygroupSeq = studtygroupSeq;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	

}
