package com.test.dto;

/**
 * 
 * 교사가능과목 DTO
 * @author jinhu
 *	seq 번호
 *	teacherSeq 교사번호
 *	subjectSeq 과목번호
 *	name 이름
 *	ssn 주민번호뒷자리
 *	tel 전화번호
 * subject 과목번호
 *
 */
public class TeacherPossibleSubjectDTO {
	
	private String seq;
	private String teacherSeq;
	private String subjectSeq;
	private String name;
	private String ssn;
	private String tel;
	private String subject;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
