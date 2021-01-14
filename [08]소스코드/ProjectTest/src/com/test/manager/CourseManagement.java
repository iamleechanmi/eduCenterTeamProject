package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dto.CourseDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 기초정보관리 - 과정관리 화면입니다.
 *
 */
public class CourseManagement {
	
	private static Scanner scan;
	private static ManagerDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new ManagerDAO();
	}
	
	
	/**
	 * 과정 관리 메뉴 화면입니다.
	 */
	public static void courseMenu() {
		
		boolean loop = true;
		
		while(loop) {

			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t과정 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 과정 조회");
			System.out.println("2. 과정 입력");
			System.out.println("3. 과정 수정");
			System.out.println("4. 과정 삭제");
			System.out.println("0. 이전 화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				// 과정 조회 화면으로 이동
				loop = false;
				findCourse();
				break;
			}else if(sel.equals("2")) {
				// 과정 입력 화면으로 이동
				loop = false;
				addCourse();
				break;
			}else if(sel.equals("3")) {
				// 과정 수정 화면으로 이동
				loop = false;
				updateCourse();
				break;
			}else if(sel.equals("4")) {
				// 과정 삭제 화면으로 이동
				loop = false;
				deleteCourse();
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
	 * 과정 조회 화면입니다.
	 */
	public static void findCourse() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과정 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과정명\t\t\t\t\t\t\t\t\t\t\t목적\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t기간");
		
		//과정 목록을 받음.
		ArrayList<CourseDTO> list = dao.courseList();
		
		for(CourseDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s%s\n", dto.getSeq(), dto.getName(), dto.getGoal(), dto.getPeriod());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과정 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*전체과정을 조회합니다.");
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String result = scan.nextLine();
		
		if(result.equals("")) {
			courseMenu();			
		}else {
			courseMenu();
		}
		
	}
	
	/**
	 * 새로운 과정을 추가하는 화면입니다.
	 */
	public static void addCourse() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t과정 입력");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("*과정명은 30자 이내, 과정 목표는 60자 이내로 입력해주세요.");
			System.out.println("*과정기간은 '일수'로 입력해주세요.");
			System.out.println("*과정기간은 160, 180, 220만 입력 가능합니다.");
			System.out.println("-----------------------------------------------------------");
			System.out.print("과정명 : ");
			String name = scan.nextLine();
			//빈칸을 입력하면 이전 화면으로 돌아감.
			if (name.equals("")) {
				courseMenu();
				loop = false;
				break;
			}else {
				System.out.print("과정목표 : ");
				String goal = scan.nextLine();
				//빈칸을 입력하면 이전 화면으로 돌아감.
				if(goal.equals("")) {
					courseMenu();
					loop = false;
					break;
				}else {
					System.out.print("과정기간(일수) : ");
					String period = scan.nextLine();
					//빈칸을 입력하면 이전 화면으로 돌아감.
					if(period.equals("")) {
						courseMenu();
						loop = false;
						break;
					//과정기간을 검사하는 구간
					}else if(!period.equals("160") && !period.equals("180") && !period.equals("220")){
						System.out.println("-----------------------------------------------------------");
						System.out.println("과정 기간은 160,180,220만 입력 가능합니다.");
					}else {
						//입력한 정보를 데이터베이스에 저장
						
						loop = false;
						CourseDTO dto = new CourseDTO();
						
						dto.setName(name);
						dto.setGoal(goal);
						dto.setPeriod(period);
						
						int result = dao.courseAdd(dto);
						
						if(result == 1) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("새로운 과정이 추가되었습니다.");
							
						}else {
							System.out.println("-----------------------------------------------------------");
							System.out.println("과정 추가에 실패했습니다.");
						}
						
						System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
						String input = scan.nextLine();
						

						if(input.equals("")) {
							courseMenu();			
						}else {
							courseMenu();
						}
						
						
					}
				}
			}
		}
		
	}
	
	/**
	 * 과정 수정 화면입니다.
	 */
	public static void updateCourse() {
		
		//전체 과정 출력
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과정 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과정명\t\t목적\t\t\t기간");
		
		ArrayList<CourseDTO> list = dao.courseList();
		
		//입력한 번호가 데이터에 존재하는지 확인하기 위한 list
		ArrayList<String> selist = new ArrayList<String>();
		
		for (CourseDTO dto : list) {
			
			System.out.printf("%s\t%-75s%-100s\t%20s\n", dto.getSeq(), dto.getName(), dto.getGoal(), dto.getPeriod());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 수정할 과정 번호를 선택해주세요.");
		
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
		CourseDTO cdto = dao.courseGet(sel);
		System.out.println("-----------------------------------------------------------");
		System.out.println("*다음 정보를 입력하세요.(빈칸이면 이전 정보가 입력됩니다.)");
		System.out.println("*빈칸으로 넘어가면 기존의 값이 유지됩니다.");
		System.out.println("*back을 입력하면 과정 메뉴화면으로 돌아갑니다.");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
		System.out.print("과정명 : ");
		String name = scan.nextLine();
		//back을 입력하면 과정 메뉴로 돌아감
		if (name.equals("back")) {
			courseMenu();
		}else {
			//빈값을 입력하면 기존의 값이 유지
			if(name.equals("")) {
				name = cdto.getName();
			}
			System.out.print("과정목표 : ");
			String goal = scan.nextLine();
			//back을 입력하면 과정 메뉴로 돌아감
			if (goal.equals("back")) {
				courseMenu();
			}else {
				//빈값을 입력하면 기존의 값이 유지
				if(goal.equals("")) {
					goal = cdto.getGoal();
				}
				boolean loop = true;
				String period = "0";
				System.out.println("*과정기간은 160, 180, 220만 입력가능합니다.");
				
				while(loop) {
					
					System.out.print("과정기간(일수) : ");
					period = scan.nextLine();
					
					//기간일수를 160, 180, 220만 입력가능. 빈칸일 경우는 기존의 값이 유지
					if(period.equals("160") || period.equals("180") || period.equals("220") || period.equals("")) {
						loop = false;
						//빈값을 입력하면 기존의 값이 유지
						if(period.equals("")) {
							period = cdto.getPeriod();
						}
						
						CourseDTO dto = new CourseDTO();
						dto.setName(name);
						dto.setGoal(goal);
						dto.setPeriod(period);
						dto.setSeq(sel);
						
						//update를 수행
						int result = dao.courseUpdate(dto);
						
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
							courseMenu();			
						}else {
							courseMenu();
						}
						
						break;
					//back을 입력하면 과정 메뉴로 돌아감
					}else if(period.equals("back")){
						loop = false;
						courseMenu();
						break;
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*과정기간은 160, 180, 220만 입력가능합니다.");
					}
				}
				
				
			}
		}
	}
	
	/**
	 * 과정 삭제 화면입니다.
	 */
	
	public static void deleteCourse() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t과정 삭제");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t과정명\t\t목적\t\t\t기간");
		
		ArrayList<CourseDTO> list = dao.courseList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(CourseDTO dto : list) {
			
			System.out.printf("%s\t%-75s%-100s\t%20s\n", dto.getSeq(), dto.getName(), dto.getGoal(), dto.getPeriod());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 삭제할 과정 번호를 선택해주세요.");
		
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
		
		int result = dao.courseDelete(sel);
		
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
			courseMenu();			
		}else {
			courseMenu();
		}
		
	}

}
