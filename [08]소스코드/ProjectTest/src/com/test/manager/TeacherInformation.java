package com.test.manager;

import java.util.Scanner;

/**
 * 
 * @author 이진혁
 * 관리자 - 교사계정관리 화면입니다.
 *
 */
public class TeacherInformation {
	
	public static void main(String[] args) {
		teacherInformationMenu();
	}
	
	/**
	 *  교사계정관리 메뉴 화면입니다.
	 */
	public static void teacherInformationMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교사 계정 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 교사 조회");
			System.out.println("2. 교사 등록");
			System.out.println("3. 교사 정보 수정");
			System.out.println("4. 교사 삭제");
			System.out.println("0. 이전화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택을 통한 화면 이등
			if(sel.equals("1")) {
				// 교사 조회 화면으로 이동
				TeacherList.teacherList();
				loop = false;
				break;
			}else if(sel.equals("2")) {
				// 교사 등록 화면으로 이동
				TeacherAdd.teacherAdd();
				loop = false;
				break;
			}else if(sel.equals("3")) {
				// 교사 정보 수정 화면으로 이동
				TeacherUpdate.teacherUpdateMenu();
				loop = false;
				break;
			}else if(sel.equals("4")) {
				// 교사 삭제 화면으로 이동
				TeacherDelete.teacherDelete();
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
