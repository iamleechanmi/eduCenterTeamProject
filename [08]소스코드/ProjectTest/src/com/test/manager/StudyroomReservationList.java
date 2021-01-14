package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudygroupInfoDAO;
import com.test.dto.StudyroomReservationDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 스터디 그룹 관리 - 스터디룸 예약 조회
 *
 */
public class StudyroomReservationList {
	
	private static Scanner scan;
	private static StudygroupInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new StudygroupInfoDAO();
	}
	
	public static void main(String[] args) {
		studyroomReservationList();
	}
	
	/**
	 * 스터디룸 예약 조회 화면
	 */
	public static void studyroomReservationList() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t스터디룸 대여 상태 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t스터디그룹번호\t강의실\t\t대여날짜");
		
		ArrayList<StudyroomReservationDTO> list = dao.studyroomReservationList();
		
		for(StudyroomReservationDTO dto : list) {
			
			System.out.printf("%s\t%s\t\t\t%s\t\t%s\n", dto.getSeq(), dto.getStudygroupSeq(), dto.getStudyroomName(), dto.getReservationDate());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		
		System.out.print("enter를 입력하면 이전 화면으로 돌아갑니다.");
		String input = scan.nextLine();
		if(input.equals("")) {
			StudygroupInformation.studygroupInformationMenu();
		}else {
			StudygroupInformation.studygroupInformationMenu();		
		}
		
	}

}
