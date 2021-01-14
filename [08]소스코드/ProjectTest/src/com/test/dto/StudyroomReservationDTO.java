package com.test.dto;

/**
 * 
 * 스터디룸예약 DTO
 * @author 이진혁
 * seq 예약번호
 * studygroupSeq 스터디그룹번호
 * studyroomName 스터디룸이름
 * reservationDate 예약날짜
 *
 */
public class StudyroomReservationDTO {
	
	private String seq;
	private String studygroupSeq;
	private String studyroomName;
	private String reservationDate;
	
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
	public String getStudyroomName() {
		return studyroomName;
	}
	public void setStudyroomName(String studyroomName) {
		this.studyroomName = studyroomName;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

}
