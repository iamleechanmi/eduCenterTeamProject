package com.test.dto;

public class TeacherAttendanceDTO {
	
	private String seq;
	private String name;
	
	private String attend;//출석
	
	private String out; //외출
	
	private String late;// 지각
	
	private String early;//조퇴
	
	private String sick; //병가
	
	private String absense; //결석
	
	
	
	
	public TeacherAttendanceDTO(String seq, String name, String attend, String out, String late, String early,
			String sick, String absense) {
		super();
		this.seq = seq;
		this.name = name;
		this.attend = attend;
		this.out = out;
		this.late = late;
		this.early = early;
		this.sick = sick;
		this.absense = absense;
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

	public String getAttend() {
		return attend;
	}

	public void setAttend(String attend) {
		this.attend = attend;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getLate() {
		return late;
	}

	public void setLate(String late) {
		this.late = late;
	}

	public String getEarly() {
		return early;
	}

	public void setEarly(String early) {
		this.early = early;
	}

	public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}

	public String getAbsense() {
		return absense;
	}

	public void setAbsense(String absense) {
		this.absense = absense;
	}

	
	
	
}

//번호, 이름, 상태 확인해서 숫자 넣기

