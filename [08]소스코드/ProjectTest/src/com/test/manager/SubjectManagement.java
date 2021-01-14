package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dto.CourseDTO;
import com.test.dto.SubjectDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 기초정보관리 - 과목관리 화면입니다.
 *
 */
public class SubjectManagement {
	
	private static Scanner scan;
	private static ManagerDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new ManagerDAO();
	}
	
	/**
	 *  과목관리 메뉴 화면입니다.
	 */
	public static void subjectMenu() {
		
		boolean loop = true;
		
		while(loop) {

			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t과목 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 과목 조회");
			System.out.println("2. 과목 입력");
			System.out.println("3. 과목 수정");
			System.out.println("4. 과목 삭제");
			System.out.println("0. 이전 화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				// 과목 조회 화면으로 이동
				loop = false;
				findSubject();
				break;
			}else if(sel.equals("2")) {
				// 과목 입력 화면으로 이동
				loop = false;
				addSubject();
				break;
			}else if(sel.equals("3")) {
				// 과목 수정 화면으로 이동
				loop = false;
				updateSubject();
				break;
			}else if(sel.equals("4")) {
				// 과목 삭제 화면으로 이동
				loop = false;
				deleteSubject();
				break;
			}else if(sel.equals("0")) {
				// 이전 화면으로 이동
				BasicInformation.basicInformationMenu();
				loop = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘 못 입력하셨습니다.");
			}
			
			
		}
	}
	

	/**
	 * 과목을 조회하는 화면입니다.
	 */
	private static void findSubject() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과목 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과목명\t\t\t\t\t\t\t      기간");
		
		ArrayList<SubjectDTO> list = dao.subjectList();
		
		for(SubjectDTO dto : list) {
			
			System.out.printf("%s\t%s\t%5s\n", dto.getSeq(), dto.getName(), dto.getPeriod());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과목 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*전체과정을 조회합니다.");
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String result = scan.nextLine();
		
		if(result.equals("")) {
			subjectMenu();			
		}else {
			subjectMenu();
		}
		
	}
	
	/**
	 * 새로운 과목을 추가하는 화면입니다.
	 */
	private static void addSubject() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t과목 입력");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("*과목명은 30자 이내로 입력해주세요.");
			System.out.println("*기간은 '일수'로 입력해주세요.(숫자만)");
			System.out.println("-----------------------------------------------------------");
			System.out.print("과목명 : ");
			String name = scan.nextLine();
			if (name.equals("")) {
				subjectMenu();
				loop = false;
				break;
			}else {
				System.out.print("과목기간(일수) : ");
				String period = scan.nextLine();
				if(period.equals("")) {
					subjectMenu();
					loop = false;
					break;
				}else {
					loop = false;
					SubjectDTO dto = new SubjectDTO();
					
					dto.setName(name);
					dto.setPeriod(period);
					
					int result = dao.subjectAdd(dto);
					
					if(result == 1) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("새로운 과목이 추가되었습니다.");
						
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("과정 추가에 실패했습니다.");
					}
					
					System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
					String input = scan.nextLine();
					
					if(input.equals("")) {
						subjectMenu();			
					}else {
						subjectMenu();
					}
				}
			}//if문
		}//while문
	}//addSubject();

	
	/**
	 * 과목 수정 화면입니다.
	 */
	
	public static void updateSubject() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과목 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과목명\t\t기간");
		
		ArrayList<SubjectDTO> list = dao.subjectList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(SubjectDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getPeriod());
			selist.add(dto.getSeq());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 수정할 과목 번호를 선택해주세요.");
				
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
		
		SubjectDTO dto = dao.subjectGet(sel);
		System.out.println("-----------------------------------------------------------");
		System.out.println("*다음 정보를 입력하세요.(빈칸이면 이전 정보가 입력됩니다.)");
		System.out.println("*빈칸으로 넘어가면 기존의 값이 유지됩니다.");
		System.out.println("*back을 입력하면 과정 메뉴화면으로 돌아갑니다.");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
		System.out.print("과목명 : ");
		String name = scan.nextLine();
		//back을 입력하면 과목 메뉴로 돌아감
		if (name.equals("back")) {
			subjectMenu();
		}else {
			//빈칸이면 기존의 값 유지
			if(name.equals("")) {
				name = dto.getName();
			}
			System.out.print("기간(일수) : ");
			String period = scan.nextLine();
			
			if(period.equals("back")) {
				subjectMenu();
			}else {
				//빈칸이면 기존의 값 유지
				if(period.equals("")) {
					period = dto.getPeriod();
				}
				
				SubjectDTO sdto = new SubjectDTO();
				
				sdto.setName(name);
				sdto.setPeriod(period);
				sdto.setSeq(sel);
				
				int result = dao.subjectUpdate(sdto);
				
				if(result == 1) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("과목이 수정되었습니다.");
					
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("과목 수정이 실패했습니다.");
				}
				
				System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
				String input = scan.nextLine();
				
				if(input.equals("")) {
					subjectMenu();			
				}else {
					subjectMenu();
				}
				
			}
		}
		
		
	}
	
	
	/**
	 * 과목 삭제 화면입니다.
	 */
	
	public static void deleteSubject() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과목 삭제");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과목명\t\t\t\t\t\t\t\t기간");
		
		ArrayList<SubjectDTO> list = dao.subjectList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(SubjectDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getPeriod());
			selist.add(dto.getSeq());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 삭제할 과목 번호를 선택해주세요.");
		
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
		
		int result = dao.subjectDelete(sel);
		
		if(result == 1) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("*과정이 삭제되었습니다.");
			
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("*삭제를 실패했습니다.");
			
		}
		
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String input = scan.nextLine();
		
		if(input.equals("")) {
			subjectMenu();			
		}else {
			subjectMenu();
		}
		
	}
		

}//class