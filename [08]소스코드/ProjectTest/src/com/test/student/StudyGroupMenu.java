package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudentDAO;
import com.test.dao.Student_StudyGroupDAO;
import com.test.dto.StudentDTO;
import com.test.dto.Student_StudyGroupDTO;
/**
 * 스터디 그룹 관리 메뉴 화면 입니다.
 * 스터디 그룹 정보 조회화면에서 관리를 원하는 스터디 번호 입력 시, 스터디 룸 예약/조회, 스터디 그룹 일지 작성/조회를 할 수 있습니다.
 * @author 오수경
 *
 */


public class StudyGroupMenu {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	
	/**
	 * 참여 했던, 참여 중인 스터디 목록을 출력합니다.
	 * 스터디 번호 입력 시 스터디룸 예약/조회, 스터디 일지 작성/조회 메뉴로 넘어갑니다.
	 * @param seq 교육생 번호
	 */
	public static void info(String seq) {
		
		boolean loop = true;
		
		while(loop) {
			
			StudentDAO sdao = new StudentDAO();

			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t스터디 그룹 정보 조회");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			StudentDTO sdto = new StudentDTO();
			sdto.setName(seq);
			System.out.printf("'%s'님의 참여 스터디 그룹은 다음과 같습니다.\n",sdao.nameCheck(seq));
			System.out.println();
			
			Student_StudyGroupDAO dao = new Student_StudyGroupDAO();
			ArrayList<Student_StudyGroupDTO> list = dao.info(seq);		
			
			System.out.println("[스터디 그룹 번호]\t[목적]\t\t\t[등록일]\t[종료일]\t[직위]");
			for(Student_StudyGroupDTO dto : list) {
				System.out.printf("%8s\t\t%-10s\t\t%s\t%s\t%s\n",dto.getSgSeq(),dto.getSgGoal(),dto.getSgRegDate(),dto.getSgEndDate(),dto.getSgrGroupRank());
			}
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 관리할 스터디 그룹 번호를 입력해주세요.");
			System.out.println("* 0번 입력시 이전 화면으로 돌아갑니다.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			
			for(Student_StudyGroupDTO dto : list) {
				String sgSeq = dto.getSgSeq();
				String sgrSeq = dto.getSgrSeq();
				if(sel.equals(sgSeq)) {
					//스터디 번호와 스터디 그룹 등록 번호를 갖고 스터디 그룹 관리 페이지로 넘어갑니다.
					menu(sgSeq,sgrSeq);
				}else if(sel.equals("0")) {
					//이전 화면(교육생 메뉴)
					loop = false;
					break;
				}else {
					System.out.println("번호를 잘못 입력하셨습니다.");
				}
			}

		}

	}

	/**
	 * 스터디 룸 예약/조회, 스터디 일지 작성/조회 선택 화면 입니다.
	 * @param sgSeq 스터디 그룹 번호
	 * @param sgrSeq 스터디 그룹 등록 번호
	 */
	public static void menu(String sgSeq, String sgrSeq) {
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t스터디 그룹 관리");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("1. 스터디 룸 예약/조회");
			System.out.println("2. 스터디 일지 작성/조회");
			System.out.println("0. 이전 화면");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 메뉴를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				// 스터디 룸 예약/조회 메뉴 화면
				StudyGroupReservation.menu(sgSeq,sgrSeq);
			} else if (sel.equals("2")) {
				// 스터디 일지 작성/예약 메뉴 화면
				StudyGroupRecord.menu(sgSeq, sgrSeq);
			} else if (sel.equals("0")) {
				// 이전화면(스터디 그룹 정보 조회 화면)
				loop = false;
				break;
			} else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}

}
