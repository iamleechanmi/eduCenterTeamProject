package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.Student_StudyGroupDAO;
import com.test.dto.Student_StudyGroupDTO;

/**
 * 
 * 스터디 일지 작성/조회 화면 입니다
 * @author 오수경
 *
 */
public class StudyGroupRecord {
	
	private static Scanner scan;
	private static Student_StudyGroupDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new Student_StudyGroupDAO();
	}
	
	public static void main(String[] args) {
		//record("1");
		find("1");
	}

	/**
	 * 
	 * 스터디 일지 작성 / 조회 메뉴 화면
	 * @param sgSeq 스터디 그룹 번호
	 * @param sgrSeq 스터디 그룹 등록 번호
	 * 
	 */
	public static void menu(String sgSeq, String sgrSeq) {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t스터디 일지");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("1. 스터디 일지 작성");
			System.out.println("2. 스터디 일지 조회");
			System.out.println("3. 이전 화면");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호 선택");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("* 메뉴를 선택해주세요");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				//스터디 일지 작성 화면
				record(sgrSeq);
			}else if(sel.equals("2")) {
				//스터디 일지 조회 화면
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
	 * 스터디 일지 작성 화면
	 * @param sgrSeq 스터디 그룹 등록 번호
	 */
	private static void record(String sgrSeq) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t스터디 일지 작성");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("* 내용은 50자 이내로 작성해주세요.");
		System.out.println("* 0을 입력하면 이전 화면으로 돌아갑니다.");
		System.out.println();

		boolean loop = true;
		
		while(loop) {
			
		System.out.printf("내용 : ");
		String content = scan.nextLine();
		
			if (content.equals("0")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				loop = false;
				break;
			} else if (content.length() <= 50) {
				int result = dao.record(sgrSeq, content);
				if (result == 1) {
					System.out.println();
					System.out.println("** 일지를 작성했습니다.");
					System.out.println();
					loop = false;
					break;
				} else {
					System.out.println("일지 작성에 실패했습니다. 재작성해주세요.");
				}
			} else {
				System.out.println();
				System.out.println("일지 작성에 실패했습니다. 50자 이내로 작성해주세요.");
				System.out.println();
			}
		}
	}
	
	/**
	 * 스터디 일지 조회 화면 입니다.
	 * @param sgSeq 스터디 그룹 번호
	 */
	private static void find(String sgSeq) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t스터디 일지 조회");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[작성일]\t[내용]");
		
		ArrayList<Student_StudyGroupDTO> list = dao.findRecord(sgSeq);
		
		for(Student_StudyGroupDTO dto : list) {
			
			System.out.printf("%s\t%s\n",dto.getSrRecordDate(),dto.getSrContent());
			
		}
		
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전 화면으로 돌아갑니다.");
		scan.nextLine();
		
	}

}
