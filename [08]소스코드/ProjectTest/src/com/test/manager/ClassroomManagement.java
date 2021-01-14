package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dto.ClassroomDTO;
import com.test.dto.CourseDTO;

/**
 * 강의실 관리
 * @author 이진혁
 *
 */
public class ClassroomManagement {

	private static Scanner scan;
	private static ManagerDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new ManagerDAO();
	}
	
	/**
	 * 강의실 관리 메인 메뉴
	 */
	public static void classroomMenu() {
		
		boolean loop = true;
		
		while(loop) {

			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t강의실 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 강의실 조회");
			System.out.println("2. 강의실 입력");
			System.out.println("3. 강의실 수정");
			System.out.println("4. 강의실 삭제");
			System.out.println("0. 이전 화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				// 강의실 조회 화면으로 이동
				loop = false;
				findClassroom();
				break;
			}else if(sel.equals("2")) {
				// 강의실 입력 화면으로 이동
				loop = false;
				addClassroom();
				break;
			}else if(sel.equals("3")) {
				// 강의실 수정 화면으로 이동
				loop = false;
				updateClassroom();
				break;
			}else if(sel.equals("4")) {
				// 강의실 삭제 화면으로 이동
				loop = false;
				deleteClassroom();
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
	 * 전체 강의실 데이터를 조회하는 화면
	 */
	private static void findClassroom() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t강의실 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t강의실명\t정원");
		
		ArrayList<ClassroomDTO> list = dao.classroomList();
		
		for(ClassroomDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getPersonnel());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t강의실 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*전체강의실을 조회합니다.");
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String result = scan.nextLine();
		
		if(result.equals("")) {
			classroomMenu();			
		}else {
			classroomMenu();
		}
		
	}

	/**
	 * 새로운 강의실 데이터 추가하는 화면
	 */
	private static void addClassroom() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t강의실 입력");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("*강의실명은 5자 이내로 입력해주세요.");
			System.out.println("*정원은 26, 30만 입력 가능합니다.(숫자만 입력)");
			System.out.println("-----------------------------------------------------------");
			System.out.print("강의실명 : ");
			String name = scan.nextLine();
			if (name.equals("")) {
				classroomMenu();
				loop = false;
				break;
			}else {
				System.out.print("정원 : ");
				String personnel = scan.nextLine();
				if(personnel.equals("")) {
					classroomMenu();
					loop = false;
					break;
				}else if(!personnel.equals("26") && !personnel.equals("30")){
					System.out.println("-----------------------------------------------------------");
					System.out.println("정원은 26, 30만 입력가능합니다.");
				}else {
					//입력한 정보를 데이터베이스에 저장
					loop = false;
					ClassroomDTO dto = new ClassroomDTO();
					
					dto.setName(name);
					dto.setPersonnel(personnel);
					
					int result = dao.classroomAdd(dto);
					
					if(result == 1) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("새로운 강의실이 추가되었습니다.");
							
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("강의실 추가에 실패했습니다.");
					}
					
					System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
					String input = scan.nextLine();
						

					if(input.equals("")) {
						classroomMenu();			
					}else {
						classroomMenu();
					}
					
						
				}
			}	
		}
	}

	/**
	 * 강의실 데이터를 삭제하는 화면
	 */
	private static void updateClassroom() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t강의실 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t강의실명\t정원");
		
		ArrayList<ClassroomDTO> list = dao.classroomList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(ClassroomDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getPersonnel());
			selist.add(dto.getSeq());
			
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 수정할 강의실 번호를 선택해주세요.");
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
		
		//수정시 아무 값도 넣지 않으면 기존의 데이터를 유지하기 위한 dto
		ClassroomDTO dto = dao.classroomGet(sel);
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("*다음 정보를 입력하세요.(빈칸이면 이전 정보가 입력됩니다.)");
		System.out.println("*빈칸으로 넘어가면 기존의 값이 유지됩니다.");
		System.out.println("*back을 입력하면 과정 메뉴화면으로 돌아갑니다.");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
		System.out.print("강의실명 : ");
		String name = scan.nextLine();
		//back을 입력하면 과정 메뉴로 돌아감
		if (name.equals("back")) {
			classroomMenu();
		}else {
			//빈값을 입력하면 기존의 값이 유지
			if(name.equals("")) {
				name = dto.getName();
			}
			boolean loop = true;
			
			while(loop) {
				
				System.out.println("-----------------------------------------------------------");
				System.out.println("*정원은 26, 30만 입력가능합니다.");
				System.out.print("정원 : ");
				String personnel = scan.nextLine();
				//back을 입력하면 과정 메뉴로 돌아감
				if (personnel.equals("back")) {
					classroomMenu();
					loop = false;
					break;
				}else if(personnel.equals("26") || personnel.equals("30") || personnel.equals("")){
					loop = false;
					//빈값을 입력하면 기존의 값이 유지
					if(personnel.equals("")) {
						personnel = dto.getPersonnel();
					}
					
					ClassroomDTO cdto = new ClassroomDTO();
					
					cdto.setSeq(sel);
					cdto.setName(name);
					cdto.setPersonnel(personnel);
					
					int result = dao.classroomUpdate(cdto);
					
					// result 값이 1이면 수정 성공, 0이면 수정 실패
					if(result == 1) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*수정이 성공하였습니다.");
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*수정이 실패하였습니다.");
					}
					
					System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
					String input = scan.nextLine();
					
					if(input.equals("")) {
						classroomMenu();			
					}else {
						classroomMenu();
					}
					
					
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("*정원은 26, 30만 입력가능합니다.");
				}
				
				
				
			}
		}
		
		
		
		
	}

	/**
	 * 강의실 데이터 삭제 화면
	 */
	private static void deleteClassroom() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t강의실 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t강의실명\t정원");
		
		ArrayList<ClassroomDTO> list = dao.classroomList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(ClassroomDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getPersonnel());
			selist.add(dto.getSeq());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 삭제할 강의실 번호를 선택해주세요.");
		
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
		
		int result = dao.classroomDelete(sel);
		
		if(result == 1) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("*강의실이 삭제되었습니다.");
			
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("*강의실 삭제를 실패했습니다.");
			
		}
		
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String input = scan.nextLine();
		
		if(input.equals("")) {
			classroomMenu();			
		}else {
			classroomMenu();
		}
		
	}
	
}
