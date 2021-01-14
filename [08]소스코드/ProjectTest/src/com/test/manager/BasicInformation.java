package com.test.manager;

import java.util.Scanner;

/**
 * 
 * @author 이진혁
 * 관리자 - 기초 정보 관리 화면입니다.
 *
 */
public class BasicInformation {
	
	public static void main(String[] args) {
		basicInformationMenu();
	}
	
	/**
	 * 기초정보관리 메뉴 화면
	 */
	public static void basicInformationMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t기초 정보 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 과정 관리");
			System.out.println("2. 과목 관리");
			System.out.println("3. 강의실 관리");
			System.out.println("4. 교재 관리");
			System.out.println("0. 이전화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택을 통한 화면 이등
			if(sel.equals("1")) {
				// 과정 관리 화면으로 이동
				CourseManagement.courseMenu();
				loop = false;
			}else if(sel.equals("2")) {
				// 과목 관리 화면으로 이동
				SubjectManagement.subjectMenu();
				loop = false;
			}else if(sel.equals("3")) {
				// 강의실 관리 화면으로 이동
				ClassroomManagement.classroomMenu();
				loop = false;
			}else if(sel.equals("4")) {
				// 교재 관리 화면으로 이동
				TextbookManagement.textbookMenu();
				loop = false;
			}else if(sel.equals("0")) {
				// 이전 화면으로 이동
				ManagerMenu.managerMenu();
				loop = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘 못 입력하셨습니다.");
			}
			
		}
		
	}
	
}
