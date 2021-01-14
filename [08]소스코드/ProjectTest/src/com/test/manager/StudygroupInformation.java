package com.test.manager;

import java.util.Scanner;

/**
 * 
 * @author 이진혁
 * 관리자 - 스터디그룹 관리 화면
 *
 */
public class StudygroupInformation {
	
	public static void main(String[] args) {
		studygroupInformationMenu();
	}

	/**
	 * 스터디그룹관리 메뉴화면
	 */
	public static void studygroupInformationMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t스터디 그룹 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 스터디 그룹 조회");
			System.out.println("2. 스터디 그룹 등록");
			System.out.println("3. 스터디 그룹 삭제");
			System.out.println("4. 스터디룸 대여 상태 조회");
			System.out.println("0. 이전화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택을 통한 화면 이등
			if(sel.equals("1")) {
				// 스터디그룹 조회 화면으로 이동
				StudygroupList.studygroupList();
				loop = false;
				break;
			}else if(sel.equals("2")) {
				// 스터디그룹 등록 화면으로 이동
				StudygroupAdd.studygroupAddMenu();
				loop = false;
				break;
			}else if(sel.equals("3")) {
				// 스터디그룹 삭제 화면으로 이동
				StudygroupDelete.studygroupDeleteMenu();
				loop = false;
				break;
			}else if(sel.equals("4")) {
				// 스터디룸 대여 상태 조회 화면으로 이동
				StudyroomReservationList.studyroomReservationList();
				loop = false;
				break;
			}else if(sel.equals("0")) {
				// 이전 화면으로 이동
				ManagerMenu.managerMenu();
				loop = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
			
		}
		
	}
	
}
