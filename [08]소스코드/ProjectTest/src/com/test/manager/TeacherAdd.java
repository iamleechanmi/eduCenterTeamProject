package com.test.manager;

import java.util.Scanner;

import com.test.dao.TeacherInfoDAO;
import com.test.dto.TeacherDTO;
import com.test.validation.ManagerValidation;

/**
 * 
 * @author 이진혁
 * 관리자 - 교사계정관리 - 교사입력(추가) 화면
 *
 */
public class TeacherAdd {
	
	private static Scanner scan;
	private static TeacherInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherInfoDAO();
	}
	
	public static void main(String[] args) {
		teacherAdd();
	}
	
	/**	
	 * 새로운 교사의 정보를 입력하는 화면
	 */
	public static void teacherAdd() {
		
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 등록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 주민번호 뒷자리는 pw로 사용됩니다.");
		System.out.println("* 이름은 5자 이내로 입력해주세요.");
		System.out.println("-----------------------------------------------------------");

		boolean loop = true;
		String name = "";
		String ssn = "";
		String tel = "";
		
		while(loop) {
			
			System.out.print("교사명 : ");
			name = scan.nextLine();
			if(name.equals("")) {
				TeacherInformation.teacherInformationMenu();
				loop = false;
				break;
			}else {
				boolean result = ManagerValidation.teacherName(name);
				if(result == true) {
					loop = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("이름은 5자 이내로 입력해주세요.");
				}
			}			
		}
		
		loop = true;
		
		while(loop) {
			
			System.out.print("주민번호 뒷자리 : ");
			ssn = scan.nextLine();
			if(ssn.equals("")) {
				TeacherInformation.teacherInformationMenu();
				loop = false;
				break;
			}else {
				boolean result = ManagerValidation.teacherSsn(ssn);
				if(result == true) {
					loop = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("주민번호 뒷자리는 7자리입니다.");
				}
			}
		}
		
		loop = true;
		
		while(loop) {
			System.out.println("*숫자만 입력해주세요.");
			System.out.print("전화번호 : ");
			tel = scan.nextLine();
			if(tel.equals("")) {
				TeacherInformation.teacherInformationMenu();
				loop = false;
				break;
			}else {
				boolean result = ManagerValidation.teacherTel(tel);
				if(result == true) {
					loop = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("핸드폰 번호는 10~11자리입니다.");
				}
			}
			
		}
		
		
		TeacherDTO dto = new TeacherDTO();
		
		dto.setName(name);
		dto.setSsn(ssn);
		dto.setTel(tel);
		
		int result = dao.teacherAdd(dto);
		
		if(result == 1) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("교사 정보가 추가되었습니다.");
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("교사 추가가 실패하였습니다.");
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.print("enter를 누르면 이전화면으로 돌아갑니다.");
		String input = scan.nextLine();
		if(input.equals("")) {
			TeacherInformation.teacherInformationMenu();
		}else {
			TeacherInformation.teacherInformationMenu();
			
		}
		
	}

}
