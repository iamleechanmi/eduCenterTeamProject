package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherInfoDAO;
import com.test.dto.TeacherDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 교사계정관리 - 교사 삭제 화면
 *
 */
public class TeacherDelete {
	
	private static Scanner scan;
	private static TeacherInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherInfoDAO();
	}
	
	public static void main(String[] args) {
		teacherDelete();
	}
	
	/**
	 * 교사를 삭제하는 화면
	 */
	public static void teacherDelete() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 삭제");
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
		
		selist.add("");
		boolean exist = true;
		String sel = "";
		while(exist) {
			System.out.print("번호 : ");
			sel = scan.nextLine();
			if(selist.contains(sel)) {
				exist = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
			}
		}
		
		int result = dao.teacherDelete(sel);
		
		if(result == 1) {
			System.out.println("교사 데이터가 삭제되었습니다.");
		}else {
			System.out.println("삭제 실패했습니다.");
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.print("*이전 화면으로 돌아가려면 enter를 입력해주세요.");
		String input = scan.nextLine();
		
		if(input.equals("")) {
			TeacherInformation.teacherInformationMenu();
		}else {
			TeacherInformation.teacherInformationMenu();			
		}
		
	}

}
