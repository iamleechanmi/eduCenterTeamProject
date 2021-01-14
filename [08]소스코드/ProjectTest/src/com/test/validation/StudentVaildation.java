package com.test.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 유효성 검사 클래스 입니다.
 * rsvpInspection(int year, int month, int date,String format) : 예약일 유효성 검사
 * @author 오수경
 *
 */
public class StudentVaildation {

	// 스터디룸 예약일 유효성 검사 입니다.
	public static boolean rsvpInspection(String rsvpDate,String format) {

		
		SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
		dateFormatParser.setLenient(false);

		// 현재 날짜를 format형식(yyyyMMdd)으로 변경
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

		String today = current.format(formatter);

		// 예약일에서 현재 날짜 빼기
		int diff = Integer.parseInt(rsvpDate) - Integer.parseInt(today);

		if (diff >= 0 || diff<=7 ) {// 예약일과 현재날짜가 0~7일 차이가 나는가

			try {// 유효한 날짜인가
				dateFormatParser.parse(rsvpDate);
				return true;
			} catch (Exception Ex) {
				return false;
			}
		} else
			return false;
		
		
		
		
	}
	
	

}
