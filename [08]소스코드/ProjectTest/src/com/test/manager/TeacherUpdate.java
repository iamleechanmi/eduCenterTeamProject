package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dao.TeacherInfoDAO;
import com.test.dto.SubjectDTO;
import com.test.dto.TeacherDTO;
import com.test.dto.TeacherPossibleSubjectDTO;
import com.test.validation.ManagerValidation;

/**
 * 
 * @author 이진혁
 * 관리자 - 교사계정관리 - 교사정보수정 화면
 *
 */
public class TeacherUpdate {
	
	private static Scanner scan;
	private static TeacherInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherInfoDAO();
	}
	
	public static void main(String[] args) {
		teacherUpdateMenu();
	}
	
	/**
	 * 교사 정보 수정의 메뉴
	 */
	public static void teacherUpdateMenu() {
		
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 정보 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("1. 교사 개인정보 수정");
		System.out.println("2. 강의 가능 과목 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("*입력을 안하시면 이전 화면으로 돌아갑니다.");
			System.out.println("*번호를 입력해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("")) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*교사 계정 관리 메뉴로 돌아갑니다.");
				TeacherInformation.teacherInformationMenu();
				loop = false;
				break;
			}else if(sel.equals("1")) {
				//교사 정보 수정 화면으로..
				teacherInfoUpdate();
				loop = false;
				break;
			}else if(sel.equals("2")) {
				//강의 가능 과목 수정 화면으로..
				loop = false;
				teacherPossibleSubjectUpdate();
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘못 입력하셨습니다.");
				
			}
			
		}
		
	}

	
	/**
	 * 교사 정보 수정 메뉴 화면 
	 */
	public static void teacherInfoUpdate() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 정보 수정");
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
		
		
		//입력이 없다면 교사 정보 수정 메뉴로 돌아갑니다.
		if(sel.equals("")) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("*교사 계정 관리 메뉴로 돌아갑니다.");
			teacherUpdateMenu();
		}else {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교사 정보 수정");
			System.out.println("-----------------------------------------------------------");
			System.out.println("* 수정할 정보를 입력해주세요.");
			
			boolean loop = true;
			String name = "";
			String ssn = "";
			String tel = "";
			
			TeacherDTO dto = dao.teacherGet(sel);
			
			while(loop) {
				System.out.print("이름 : ");
				name = scan.nextLine();
				if(name.equals("")) {
					name = dto.getName();
				}
				
				boolean result = ManagerValidation.teacherName(name);
				
				if(result == true) {
					loop = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("이름은 5자 이내로 입력해주세요.");
				}
			}
			
			loop = true;
			
			while(loop) {
				System.out.print("주민번호 뒷자리 : ");
				ssn = scan.nextLine();
				if(ssn.equals("")) {
					ssn = dto.getSsn();
				}
				
				boolean result = ManagerValidation.teacherSsn(ssn);
				
				if(result == true) {
					loop = false;
					break;
				}else {					
					System.out.println("-----------------------------------------------------------");
					System.out.println("주민번호는 7자리입니다.");
				}
			}
			
			loop = true;
			
			while(loop) {
				System.out.print("전화번호 : ");
				tel = scan.nextLine();
				if(tel.equals("")) {
					tel = dto.getTel();
				}
				boolean result = ManagerValidation.teacherTel(tel);
				
				if(result == true) {
					loop = false;
					break;
				}else {					
					System.out.println("-----------------------------------------------------------");
					System.out.println("전화번호는 10~11자리입니다.");
				}
			}
			
			TeacherDTO tdto = new TeacherDTO();
			
			tdto.setSeq(sel);
			tdto.setName(name);
			tdto.setSsn(ssn);
			tdto.setTel(tel);
			
			int check = dao.teacherUpdate(tdto);
			
			if(check == 1) {
				System.out.println("교사정보 수정이 성공했습니다.");
			}else {
				System.out.println("수정이 실패했습니다.");
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.print("이전화면으로 돌아가시려면 enter를 입력헤주세요.");
			String input = scan.nextLine();
			
			if(input.equals("")) {
				teacherUpdateMenu();
			}else {				
				teacherUpdateMenu();
			}
			
		}
		
	}
	
	
	/**
	 * 교사 강의가능 과목 수정에 관한 메뉴화면
	 */
	private static void teacherPossibleSubjectUpdate() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 정보 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("1. 강의 가능 과목 삭제");
		System.out.println("2. 강의 가능 과목 등록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("*입력을 안하시면 이전 화면으로 돌아갑니다.");
			System.out.println("*번호를 입력해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("")) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*교사 정보 수정 메뉴로 돌아갑니다.");
				loop = false;
				teacherUpdateMenu();
				break;
			}else if(sel.equals("1")) {
				//강의 가능 과목 삭제 화면
				loop = false;
				teacherPossibleSubjectDelete();
				break;
			}else if(sel.equals("2")) {
				//강의 가능 과목 등록 화면
				loop = false;
				teacherPossibleSubjctAdd();
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘못 입력하셨습니다.");
				
			}
			
		}
	}

	
	/**
	 *교사 강의 가능한 과목 등록하는 화면 
	 */
	private static void teacherPossibleSubjctAdd() {
		
		ManagerDAO mdao = new ManagerDAO();
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 목록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t이름\t주민번호");
		
		ArrayList<TeacherDTO> list = dao.teacherList();
		ArrayList<String> selist = new ArrayList<String>();
		
		for (TeacherDTO dto : list) {
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getSsn());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 번호를 선택해주세요.");
		System.out.println("* 빈칸으로 enter를 입력하면 이전 화면으로 돌아갑니다.");		
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
		
		if(sel.equals("")) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("이전화면으로 돌아갑니다.");
			teacherPossibleSubjectUpdate();
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교사 가능 과목 등록");
			System.out.println("-----------------------------------------------------------");
			System.out.println("번호\t과목명\t기간");
			
			ArrayList<SubjectDTO> plist = mdao.subjectList();
			
			ArrayList<String> selist2 = new ArrayList<String>();
			
			for(SubjectDTO dto : plist) {
				
				System.out.printf("%s\t%s\n", dto.getSeq(), dto.getName());
				selist2.add(dto.getSeq());
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("* 강의 가능한 과목 목록에서 삭제할 번호를 입력하세요.");
			System.out.println("* 빈칸으로 enter를 입력하면 이전 화면으로 돌아갑니다.");
			System.out.println("* end를 입력하면 번호입력이 종료됩니다.");
			
			boolean loop = true;
			ArrayList<String> inputList = new ArrayList<String>();
			
			while(loop) {
				
				System.out.println("-----------------------------------------------------------");
				
				selist2.add("");
				selist2.add("end");
				boolean exist2 = true;
				String dsel = "";
				while(exist2) {
					System.out.print("번호 : ");
					dsel = scan.nextLine();
					if(selist2.contains(dsel)) {
						exist2 = false;
						break;
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
					}
				}
				
				if(dsel.equals("")) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("이전화면으로 돌아갑니다.");
					teacherPossibleSubjectUpdate();
					loop = false;
					break;
				}else if(dsel.equals("end")) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("입력을 종료합니다.");
					loop = false;
					break;
				}else {
					//입력한 번호를 저장한다.
					inputList.add(dsel);
					
				}
				
			}
			
			//입력받은 과목 번호로 해당 과목들을 삭제한다.
			for(String s : inputList) {
				
				int result = dao.teachPossibleSubjectAdd(s, sel);
				
				if(result == 1) {
					System.out.println(s + "번 과목 등록을 성공했습니다.");
				}else {
					System.out.println(s + "번 과목 등록을 실패했습니다.");
				}
				
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.print("enter를 입력하면 이전화면으로 돌아갑니다.");
			String input = scan.nextLine();
			
			if(input.equals("")) {
				teacherPossibleSubjectUpdate();
			}else {
				teacherPossibleSubjectUpdate();
			}
		}
		
	}

	
	/**
	 * 교사 강의 가능한 과목 삭제하는 화면 
	 */
	private static void teacherPossibleSubjectDelete() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교사 목록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t이름\t주민번호");
		
		ArrayList<TeacherDTO> list = dao.teacherList();
		ArrayList<String> selist = new ArrayList<String>();
		
		for (TeacherDTO dto : list) {
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getSsn());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 번호를 선택해주세요.");
		System.out.println("* 빈칸으로 enter를 입력하면 이전 화면으로 돌아갑니다.");		
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
		
		if(sel.equals("")) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("이전화면으로 돌아갑니다.");
			teacherPossibleSubjectUpdate();
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교사 가능 과목 삭제");
			System.out.println("-----------------------------------------------------------");
			System.out.println("번호\t과목명\t기간");
			
			ArrayList<TeacherPossibleSubjectDTO> plist = dao.possibleSubjectList(sel);
			
			ArrayList<String> selist2 = new ArrayList<String>();
			
			for(TeacherPossibleSubjectDTO dto : plist) {
				
				System.out.printf("%s\t%s\n", dto.getSeq(), dto.getSubject());
				selist2.add(dto.getSeq());
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("* 강의 가능한 과목 목록에서 삭제할 번호를 입력하세요.");
			System.out.println("* 빈칸으로 enter를 입력하면 이전 화면으로 돌아갑니다.");
			System.out.println("* end를 입력하면 번호입력이 종료됩니다.");
			
			boolean loop = true;
			ArrayList<String> inputList = new ArrayList<String>();
			selist2.add("");
			selist2.add("end");
			
			while(loop) {
				
				System.out.println("-----------------------------------------------------------");
				boolean exist2 = true;
				String dsel = "";
				while(exist2) {
					System.out.print("번호 : ");
					dsel = scan.nextLine();
					if(selist2.contains(dsel)) {
						exist2 = false;
						break;
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
					}
				}
				
				
				if(dsel.equals("")) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("이전화면으로 돌아갑니다.");
					teacherPossibleSubjectUpdate();
					loop = false;
					break;
				}else if(dsel.equals("end")) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("입력을 종료합니다.");
					loop = false;
					break;
				}else {
					//입력한 번호를 저장한다.
					inputList.add(dsel);
					
				}
				
			}
			
			//입력받은 과목 번호로 해당 과목들을 삭제한다.
			for(String s : inputList) {
				
				int result = dao.teachPossibleSubjectDelete(s);
				
				if(result == 1) {
					System.out.println(s + "번 과목 삭제를 성공했습니다.");
				}else {
					System.out.println(s + "번 과목 삭제를 실패했습니다.");
				}
				
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.print("enter를 입력하면 이전화면으로 돌아갑니다.");
			String input = scan.nextLine();
			
			if(input.equals("")) {
				teacherPossibleSubjectUpdate();
			}else {
				teacherPossibleSubjectUpdate();
			}
		}
		
		
		
	}

}
