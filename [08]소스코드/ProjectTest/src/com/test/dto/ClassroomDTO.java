package com.test.dto;

/**
 * ClassroomDTO. 강의실 정보를 담은 클래스입니다.
 * seq 강의실 번호
 * name 강의실명
 * personnel 정원
 * @author 이찬미
 *
 */
public class ClassroomDTO {

	private String seq; //강의실 번호
	private String name; //강의실명
	private String personnel; //정원
	
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
	public String getPersonnel() {
		return personnel;
	}
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
}
