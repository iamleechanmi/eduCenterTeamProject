package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.Student_FindSignUpDAO;
import com.test.dto.Student_FindSignUpDTO;


/**
 * 로그인 후 교육생 메뉴 화면입니다.
 * @author 오수경
 *
 */
public class StudentMenu {
		

	public static void main(String[] args) {
		studentMenu("86");
	}
	
	/**
	 * 현재 진행중인 수강정보와 학생 정보 출려
	 * @param seq : 학생 번호
	 */
	public static void studentMenu(String seq) {
		
		Scanner scan = new Scanner(System.in);		
		Student_FindSignUpDAO dao = new Student_FindSignUpDAO();
		//Student_FindSignUpDTO dto = new Student_FindSignUpDTO();
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t 교육생 메뉴");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			
			ArrayList<Student_FindSignUpDTO> list = dao.mySignUpNow(seq);
			
			// 수강 정보 및 개인 정보는 과정 진행중일 경우만 출력
			for(Student_FindSignUpDTO dto : list) {
			if(list!=null) {
				System.out.println("이름 : "+dto.getsName());
				System.out.println("전화번호 : "+dto.getsTel());
				System.out.println("등록일 : "+dto.getsRegistrationDate());
				System.out.println();
				System.out.println("[수강번호]\t[개설과정번호]\t[과정명]\t\t\t\t\t\t[과정 시작일]\t[과정 종료일]\t[강의실명]\t[과정 상태]\t[수료 및 중단일]");
				System.out.printf("%5s\t\t%7s\t\t%s\t%s\t%s\t%5s\t%s\t\t%s\n",dto.getSuSeq(),dto.getOcSeq(), dto.getcName(), dto.getOcStartDate(), dto.getOcEndDate(), dto.getCrName(), dto.getSuStatus(), dto.getSuEndDate());
				System.out.println();
				System.out.println("** 수강 정보 및 개인정보는 과정 진행중일 경우에만 출력됩니다.");
				System.out.println();
			}
			}
			System.out.println("1. 성적 조회");
			System.out.println("2. 출결 체크/조회");
			System.out.println("3. 스터디 그룹 관리");
			System.out.println("4. 로그 아웃");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t번호선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 메뉴를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			
			//번호 선택 후 출력 화면
			if(sel.equals("1")) {
				//성적 조회 화면
				StudentScore.mySignUp(seq);
			}else if(sel.equals("2")) {
				//출결 체크 및 출결 현황 조회 화면
				StudentAttendance.menu(seq);
				//loop = false;
			}else if(sel.equals("3")) {
				//스터디 그룹 관리 화면
				StudyGroupMenu.info(seq);

			}else if(sel.equals("4")) {
				//로그 아웃(로그인 화면으로)
				System.out.println();
				System.out.println("**로그아웃 되었습니다.");
				System.out.println();
				loop=false;
				break;
			}else {
				System.out.println("번호를 잘 못 입력하셨습니다.");
			}
			
		}
		
	}

}
