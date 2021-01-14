package com.test.manager;

import java.util.Scanner;

import com.test.dao.ManagerDAO;
import com.test.dto.ManagerDTO;
import com.test.main.Main;

/**
 * 
 * @author 이진혁
 * 관리자 로그인화면입니다.
 * 이름, 비밀번호(주민번호 뒷자리)를 입력하면 로그인이 됩니다.
 *
 */
public class ManagerMain {
	
	/**
	 * 관리자 로그인 화면
	 */
	public static void managerLogin() {
		
		Scanner scan = new Scanner(System.in);
		ManagerDAO dao = new ManagerDAO();
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t로그인");
			System.out.println("-----------------------------------------------------------");
			System.out.println();
			System.out.println("*관리자 로그인입니다.");
			System.out.println("*입력을 하지 않으면 메인 화면으로 돌아갑니다.");
			System.out.println("*END를 입력하면 프로그램이 즉시 종료됩니다.(대문자)");
			System.out.println();
			System.out.print("이름 : ");
			String name = scan.nextLine();
			
			//아무것도 입력 안하면 처음 화면으로 돌아감
			if(name.equals("")) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*처음화면으로 돌아갑니다.");
				loop = false;
				Main.mainMenu();
				break;
			}else if(name.equals("END")){
				System.out.println("-----------------------------------------------------------");
				loop = false;
				break;
			}else {
				// 입력된 값을 dto에 저장
				System.out.print("비밀번호 : ");
				String ssn = scan.nextLine();
				System.out.println("-----------------------------------------------------------");
				if(ssn.equals("")) {
					System.out.println("*처음화면으로 돌아갑니다.");
					loop = false;
					Main.mainMenu();
					break;
				}else if(ssn.equals("END")){
					loop = false;
					break;
				}else {
					ManagerDTO dto = new ManagerDTO();
					dto.setName(name);
					dto.setSsn(ssn);
					
					// 로그인할 관리자의 존재유무 확인
					int result = dao.loginCheck(dto);
					
					// 1이면 로그인 성공, 0이면 로그인 실패
					if (result == 1) {
						loop = false;
						System.out.println("로그인 성공");
						// 로그인하는 관리자 번호를 반환
						String seq = dao.seqCheck(dto);
						//관리자 메뉴 화면으로..
						ManagerMenu.managerMenu();
					}else {
						//로그인 실패시 로그인과정 반복
						System.out.println("로그인 실패");
					}
				}
				
			}
			
			
		}
		
		
	}

}
