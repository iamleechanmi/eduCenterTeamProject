package com.test.dto;

/**
 * TextbookDTO. 교재 정보를 담은 클래스입니다.
 * seq 교재 번호
 * name 교재명
 * publisher 출판사
 * @author 이찬미
 *
 */
public class TextbookDTO {

	private String seq;
	private String name;
	private String publisher;
	
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
