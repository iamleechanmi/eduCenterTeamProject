package com.test.manager;

import java.util.Scanner;

/**
 * 
 * @author 이진혁
 * 관리자 로그인 후 메인 메뉴
 * 1. 기초정보관리 / 2. 교사계정관리 / 3. 개설과정관리 / 4.개설과목관리 / 5.교육생관리 / 6.시험관리 및 성적 조회 / 7. 출결 관리 및 출결 조회 / 8.스터디그룹 관리
 * 
 */
public class ManagerMenu {
	
	public static void main(String[] args) {
		managerMenu();
	}
	
	//관리자 메인 메뉴
	public static void managerMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t관리자 메뉴");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 기초 정보 관리");
			System.out.println("2. 교사 계정 관리");
			System.out.println("3. 개설 과정 및 과목 관리");
			System.out.println("4. 교육생 관리");
			System.out.println("5. 시험 관리 및 성적 조회");
			System.out.println("6. 출결 관리 및 출결 조회");
			System.out.println("7. 스터디그룹 관리");
			System.out.println("0. 이전 화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호선택 후 출력 화면
			if (sel.equals("1")) {
				//기초 정보 관리 화면으로
				BasicInformation.basicInformationMenu();
				loop = false;
			}else if(sel.equals("2")) {
				//교사 계정 관리 화면으로
				TeacherInformation.teacherInformationMenu();
				loop = false;
			}else if(sel.equals("3")) {
				//개설 과정 관리 화면으로
				ManagerOpenCourse oc = new ManagerOpenCourse();
				loop = false;
			}else if(sel.equals("4")) {
				//교육생 관리 화면으로
				StudentManagement.menu();
				loop = false;
			}else if(sel.equals("5")) {
				//시험 관리 및 성적 조회 화면으로
				ExamScoreManagement.menu();
				loop = false;
			}else if(sel.equals("6")) {
				//출결 관리 및 출결 조회 화면으로
				AttendanceManagement.menu();
				loop = false;
			}else if(sel.equals("7")) {
				//스터디 그룹 화면으로
				StudygroupInformation.studygroupInformationMenu();
				loop = false;
			}else if(sel.equals("0")) {
				// 로그인 화면으로
				ManagerMain.managerLogin();
				loop = false;
			}else if(sel.equals("END")){
				// 프로그램 즉시 종료
				System.out.println("프로그램을 종료합니다.");
				loop = false;
				break;
			}else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
			
		}
	}

}
