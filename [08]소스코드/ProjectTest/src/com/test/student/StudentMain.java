package com.test.student;

import java.util.Scanner;

import com.test.dao.StudentDAO;
import com.test.dto.StudentDTO;
import com.test.main.Main;

/**
 * 
 * @author 오수경
 * 교육생 로그인 화면입니다.
 * 이름, 비밀번호(주민번호 뒷자리)를 입력하면 로그인이 됩니다.
 *
 */

public class StudentMain {
	

	
	public static void studentLogin() {
		
		Scanner scan = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t로그인");
			System.out.println("-----------------------------------------------------------");
			System.out.println();
			System.out.println("*교육생 로그인입니다.");
			System.out.println("*입력을 하지 않으면 메인 화면으로 돌아갑니다.");
			System.out.println();
			System.out.print("이름 : ");
			String name = scan.nextLine();
			
			//아무것도 입력 안하면 처음 화면으로 돌아감
			if(name.equals("")) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*처음화면으로 돌아갑니다.");
				loop = false;
				//Main.mainMenu();
				break;
			}else {
				// 입력된 값을 dto에 저장
				System.out.print("비밀번호 : ");
				String ssn = scan.nextLine();
				System.out.println();
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
					StudentDTO dto = new StudentDTO();
					dto.setName(name);
					dto.setSsn(ssn);
					
					// 로그인할 교육생의 존재유무 확인
					int result = dao.loginCheck(dto);
					
					// 1이면 로그인 성공, 0이면 로그인 실패
					if (result == 1) {
						loop = false;
						System.out.println("\t\t\t로그인 성공");
						System.out.println("-----------------------------------------------------------");
						// 로그인하는 교육생 번호를 반환
						String seq = dao.seqCheck(dto);
						//교육생 메뉴 화면으로
						StudentMenu.studentMenu(seq);
					}else {
						//로그인 실패시 로그인과정 반복
						System.out.println("\t  로그인에 실패했습니다. 이름, 비밀번호를 확인해주세요.");
						System.out.println("-----------------------------------------------------------");
					}
				}
				
			}
			
			
		}
			
		
	}


}
