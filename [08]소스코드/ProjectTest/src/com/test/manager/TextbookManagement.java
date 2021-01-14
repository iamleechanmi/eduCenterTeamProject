package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dto.CourseDTO;
import com.test.dto.TextbookDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 기초정보관리 - 교재관리 화면
 *
 */
public class TextbookManagement {
	
	private static Scanner scan;
	private static ManagerDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new ManagerDAO();
	}
	public static void main(String[] args) {
		textbookMenu();
	}
	/**
	 * 교재 관리 메인 화면입니다.
	 */
	public static void textbookMenu() {
		
		boolean loop = true;
		
		while(loop) {

			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교재 관리");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 교재 조회");
			System.out.println("2. 교재 입력");
			System.out.println("3. 교재 수정");
			System.out.println("4. 교재 삭제");
			System.out.println("0. 이전 화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				// 교재 조회 화면으로 이동
				loop = false;
				findTextbook();
				break;
			}else if(sel.equals("2")) {
				// 교재 입력 화면으로 이동
				loop = false;
				addTextbook();
				break;
			}else if(sel.equals("3")) {
				// 교재 수정 화면으로 이동
				loop = false;
				updateTextbook();
				break;
			}else if(sel.equals("4")) {
				// 교재 삭제 화면으로 이동
				loop = false;
				deleteTextbook();
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
	 * 전체 교재 조회 화면입니다.
	 */
	private static void findTextbook() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교재 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t교재명\t\t\t\t\t출판사");
	
		ArrayList<TextbookDTO> list = dao.textbookList();
		
		for(TextbookDTO dto : list) {
			
			System.out.printf("%s\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getPublisher());
			
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교재 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*전체교재을 조회합니다.");
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String result = scan.nextLine();
		
		if(result.equals("")) {
			textbookMenu();			
		}else {
			textbookMenu();
		}
	
	}

	
	/**
	 * 새로운 교재 추가 화면입니다.
	 */
	private static void addTextbook() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t교재 입력");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("*교재명은 35자 이내, 출판사는 10자 이내로 입력해주세요.");
			System.out.println("-----------------------------------------------------------");
			System.out.print("교재명 : ");
			String name = scan.nextLine();
			if (name.equals("")) {
				textbookMenu();
				loop = false;
				break;
			}else {
				System.out.print("출판사 : ");
				String publisher = scan.nextLine();
				if(publisher.equals("")) {
					textbookMenu();
					loop = false;
					break;
				}else {
					
					loop = false;
					TextbookDTO dto = new TextbookDTO();
					
					dto.setName(name);
					dto.setPublisher(publisher);
					
					int result = dao.textbookAdd(dto);
					
					if(result == 1) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("새로운 교재가 추가되었습니다.");
						
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("교재 추가에 실패했습니다.");
					}
					
					System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
					String input = scan.nextLine();
					

					if(input.equals("")) {
						textbookMenu();			
					}else {
						textbookMenu();
					}
					
					
				}
			}
		}
		
	}

	/**
	 * 교재 정보를 수정하는 화면
	 */
	
	private static void updateTextbook() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교재 수정");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t교재명\t\t\t\t\t출판사");
	
		ArrayList<TextbookDTO> list = dao.textbookList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(TextbookDTO dto : list) {
			
			System.out.printf("%s\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getPublisher());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 수정할 교재 번호를 선택해주세요.");
		
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
		
		TextbookDTO dto = dao.textbookGet(sel);
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("*다음 정보를 입력하세요.(빈칸이면 이전 정보가 입력됩니다.)");
		System.out.println("*빈칸으로 넘어가면 기존의 값이 유지됩니다.");
		System.out.println("*back을 입력하면 과정 메뉴화면으로 돌아갑니다.");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
		System.out.print("교재명 : ");
		String name = scan.nextLine();
		//back을 입력하면 과정 메뉴로 돌아감
		if (name.equals("back")) {
			textbookMenu();
		}else {
			//빈값을 입력하면 기존의 값이 유지
			if(name.equals("")) {
				name = dto.getName();
			}
			System.out.print("출판사 : ");
			String publisher = scan.nextLine();
			//back을 입력하면 과정 메뉴로 돌아감
			if (publisher.equals("back")) {
				textbookMenu();
			}else {
				//빈값을 입력하면 기존의 값이 유지
				if(publisher.equals("")) {
					publisher = dto.getPublisher();
				}		
						
				TextbookDTO tdto = new TextbookDTO();
				tdto.setName(name);
				tdto.setPublisher(publisher);
				tdto.setSeq(sel);
						
				//update를 수행
				int result = dao.textbookUpdate(tdto);
						
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
					textbookMenu();			
				}else {
					textbookMenu();
				}
						
			}
		}
		
	}

	
	/**
	 * 교재를 삭제하는 화면
	 */
	private static void deleteTextbook() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t교재 삭제");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t교재명\t\t\t\t\t출판사");
	
		ArrayList<TextbookDTO> list = dao.textbookList();
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(TextbookDTO dto : list) {
			
			System.out.printf("%s\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getPublisher());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 삭제할 교재 번호를 선택해주세요.");
		
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
		
		int result = dao.textbookDelete(sel);
		
		if(result == 1) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("*교재가 삭제되었습니다.");
			
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("*삭제를 실패했습니다.");
			
		}
		
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		String input = scan.nextLine();
		
		if(input.equals("")) {
			textbookMenu();			
		}else {
			textbookMenu();
		}
		
	}
	
}
