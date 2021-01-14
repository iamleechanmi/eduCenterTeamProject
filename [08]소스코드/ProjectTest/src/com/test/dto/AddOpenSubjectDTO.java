package com.test.dto;

/**
 * AddOpenSubjectDTO. 개설 과목 등록에 필요한 정보를 담은 클래스입니다.
 * openCourseSeq 개설 과정 번호
 * subjectSeq 과목 번호
 * startDate 개설 과목 시작일
 * teacherSeq 교사 번호
 * textbookSeq 교재 번호
 * @author 이찬미
 *
 */
public class AddOpenSubjectDTO {
	
	private String openCourseSeq;
	private String subjectSeq;
	private String startDate;
	private String teacherSeq;
	private String textbookSeq;
	
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getTextbookSeq() {
		return textbookSeq;
	}
	public void setTextbookSeq(String textbookSeq) {
		this.textbookSeq = textbookSeq;
	}
}
