package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherInfoDAO;
import com.test.dto.TeacherDTO;
import com.test.dto.TeacherPossibleSubjectDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 교사 계정 관리 - 교사 조회 화면입니다.
 *
 */
public class TeacherList {
	
	private static Scanner scan;
	private static TeacherInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherInfoDAO();
	}
	
	public static void main(String[] args) {
		teacherList();
	}
	
	/**
	 * 전체 교사 조회하는 화면
	 */
	public static void teacherList() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과정 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t이름\t\t주민번호 뒷자리\t전화번호");
		
		ArrayList<TeacherDTO> list = dao.teacherList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(TeacherDTO dto : list) {
			
			System.out.printf("%s\t%s\t\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getSsn(), dto.getTel());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*선생님별 강의가능한 과목을 보시려면 선생님 번호를 입력해주세요.");
		System.out.println("*아무것도 입력 안하시면 교사 메뉴로 돌아갑니다.");
		
		boolean loop = true;
		selist.add("");
		String sel = "";
		while(loop) {
			
			System.out.println("-----------------------------------------------------------");
			boolean exist = true;
			while(exist) {
				System.out.print("번호 : ");
				sel = scan.nextLine();
				if(selist.contains(sel)) {
					exist = false;
					loop = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
				}
			}
			
			if(!sel.equals("")) {
				possibleSubject(sel);
				break;
			}else {
				TeacherInformation.teacherInformationMenu();;
			}
			
			
		}
		
	}

	/**
	 * 선택된 교사의 강의 가능 과목 출력하는 화면
	 * @param sel 교사 번호
	 */
	private static void possibleSubject(String sel) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t가능 과목 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과목명");
		
		ArrayList<TeacherPossibleSubjectDTO> list = dao.possibleSubjectList(sel);
		
		for(TeacherPossibleSubjectDTO dto : list) {
			
			System.out.printf("%s\t%s\n", dto.getSeq(), dto.getSubject());
			
		}
		System.out.println("-----------------------------------------------------------");
		System.out.print("* 이전 화면으로 돌아가시려면 enter를 눌러주세요");
		String input = scan.nextLine();
		
		if(input.equals("")) {
			teacherList();
		}else {
			teacherList();			
		}
		
	}

	

}
