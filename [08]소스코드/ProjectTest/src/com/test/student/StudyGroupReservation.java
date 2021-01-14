package com.test.student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.test.dao.Student_StudyGroupDAO;
import com.test.dto.Student_StudyGroupDTO;
import com.test.validation.StudentVaildation;

/**
 * 스터디 룸 예약/조회 화연 입니다.
 * @author 오수경
 *
 */
public class StudyGroupReservation {
	
	private static Scanner scan;
	private static Student_StudyGroupDAO dao;
	private static Student_StudyGroupDTO dto;
	
	static {
		scan = new Scanner(System.in);
		dao = new Student_StudyGroupDAO();
		dto = new Student_StudyGroupDTO();
	}
	
	public static void main(String[] args) {
		reservationStudyRoom("1","200605");
	}

	/**
	 * 
	 * 스터디 룸 예약/조회 선택 메뉴 화면 입니다.
	 * 스터디 룸 예약은 팀장만 가능합니다.
	 * @param sgSeq 스터디 그룹 번호
	 * @param sgrSeq 스터디 그룹 등록 번호
	 * 
	 */
	public static void menu(String sgSeq, String sgrSeq) {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t스터디 룸 예약");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("1. 스터디 룸 예약");
			System.out.println("2. 스터디 룸 예약 조회");
			System.out.println("3. 이전 화면");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호 선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 메뉴를 선택해주세요");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				//스터디 룹 예약 화면
				reservationDate(sgrSeq);
			}else if(sel.equals("2")) {
				//스터디 룸 예약 조회 화면
				find(sgSeq);
			}else if(sel.equals("3")) {
				//이전 화면(스터디 그룹 정보 화면)
				loop = false;
				break;
			}else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}

		}
	}

	/**
	 * 스터디 룸 예약 화면 입니다.
	 * @param sgrSeq 스터디 그룹 등록 번호
	 */
	

	private static void reservationDate(String sgrSeq) {
		
		boolean loop = true;
		String rsvpDate = "";
		while(loop) {
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t스터디 룸 예약");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 예약할 날짜를 입력해주세요.(오늘부터 일주일 이후까지만 예약가능합니다.)");
			System.out.println();
			System.out.print("연도(yyyy) : ");
			String year = scan.nextLine();
			System.out.print("월(mm) : ");
			String month = scan.nextLine();
			System.out.print("일(dd) : ");
			String date = scan.nextLine();
			
			//예약일(yyyymmdd)
			rsvpDate = year+month+date;
			
			//예약일 유효성 검사
			if(StudentVaildation.rsvpInspection(rsvpDate,"yyyymmdd")){
				reservationStudyRoom(sgrSeq,rsvpDate);
				loop = false;
				break;
			}else if(year.equals("0")||month.equals("0")||date.equals("0")) {
				//이전 화면으로
				loop = false;
				break;
			}else {
				System.out.println("다시 입력해주세요.");
			}

		}

	}
	

	/**
	 * 예약 가능한 스터디 룸 출력 화면입니다.
	 * @param sgrSeq 스터디 그룹 등록 번호
	 * @param rsvpDate 예약날짜
	 */
	private static void reservationStudyRoom(String sgrSeq, String rsvpDate) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t스터디 룸 예약");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("예약 가능한 스터디 룸은 다음과 같습니다.");
		System.out.println();
		System.out.println("[스터디룸 번호]\t[스터디룸명]");

		ArrayList<Student_StudyGroupDTO> list = dao.availableRoom(rsvpDate);
		for(Student_StudyGroupDTO dto : list) {
			System.out.printf("%5s\t%s\n",dto.getSmSeq(), dto.getSmName());
		}
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t번호 입력");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("* 예약을 원하는 스터디룸 번호를 입력해주세요.");
		System.out.println("* 0을 입력하면 이전 화면으로 돌아갑니다.");
		
		boolean loop = true;
		
		while(loop) {
		System.out.print("번호 : ");
		String sel = scan.nextLine();
		
		if (!sel.equals("0")) {
			for (int i = 0; i < sel.length(); i++) {
				if ((sel.charAt(i) >= '0' && sel.charAt(i) <= '9')) {
					int result = dao.reservation(sel, sgrSeq, rsvpDate);
					if (result == 1) {
						System.out.println();
						System.out.println("스터디룸 예약이 완료됐습니다.");
						System.out.println();
						loop = false;
						break;
					} else {
						System.out.println("스터디룸 예약이 실패했습니다. 다시 시도해 주세요.");
					}
				} else
					System.out.println("숫자만 입력해주세요.");
			}
		} else {
			System.out.println("이전 화면으로 돌아갑니다.");
			loop = false;
			break;
		}
	}
}

	/**
	 * 스터디 룸 예약 조회 화면 입니다.
	 * @param seq 교육생 번호
	 * @param sgSeq 스터디 그룹 번호
	 */
	private static void find(String sgSeq) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t스터디 룸 예약 확인");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[예약날짜]\t\t[스터디룸]");
		
		ArrayList<Student_StudyGroupDTO> list = dao.findRsvp(sgSeq);
		
		for(Student_StudyGroupDTO dto : list) {
			
			System.out.printf("%s\t%5s\n",dto.getSrRsvpDate(),dto.getSmName());
			
		}
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전 화면으로 돌아갑니다.");
		scan.nextLine();
	}
}
