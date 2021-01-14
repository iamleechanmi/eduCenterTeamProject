package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudygroupInfoDAO;
import com.test.dto.StudygroupDTO;
import com.test.dto.StudygroupMemberDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 스터디그룹 관리 - 스터디그룹 삭제
 *
 */
public class StudygroupDelete {

	private static Scanner scan;
	private static StudygroupInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new StudygroupInfoDAO();
	}
	
	public static void main(String[] args) {
		studygroupDelete();
	}
	
	/**
	 * 스터디그룹을 삭제하는 화면입니다.
	 */
	public static void studygroupDeleteMenu() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t스터디 그룹 삭제");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 스터디 그룹 삭제");
			System.out.println("2. 스터디 그룹 인원 삭제");
			System.out.println("0. 이전화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택을 통한 화면 이등
			if(sel.equals("1")) {
				// 스터디그룹 삭제 화면으로 이동
				loop = false;
				studygroupDelete();
				break;
			}else if(sel.equals("2")) {
				// 스터디그룹 인원 삭제 화면으로 이동
				loop = false;
				studygroupMemberDelete();
				break;
			}else if(sel.equals("0")) {
				// 이전 화면으로 이동
				StudygroupInformation.studygroupInformationMenu();
				loop = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
			
		}
		
	}

	/**
	 * 스터디그룹을 삭제하는 화면입니다.
	 */
	private static void studygroupDelete() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t스터디 그룹 삭제");
		System.out.println("-----------------------------------------------------------");
		System.out.println("*스터디그룹은 해당 그룹에 인원이 없을 때만 삭제 가능합니다.");
		System.out.println();
		System.out.println("번호\t목적\t\t\t등록일\t\t\t종료일");
		
		ArrayList<StudygroupDTO> glist = dao.studygroupList("ing");
		
		for(StudygroupDTO dto : glist) {
			System.out.printf("%s\t%s\t%s\t\t%s\n", dto.getSeq(), dto.getGoal(), dto.getRegistrationDate(), dto.getEndDate());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");		
		System.out.println("*미입력시 이전 화면으로 돌아갑니다.");
		System.out.print("번호 : ");
		//스터디그룹 번호
		String gsel = scan.nextLine();
		
		//미입력시 이전화면으로 이동
		if(gsel.equals("")) {
			studygroupDeleteMenu();
		}else {
			//스터디그룹에 속한 인원수를 확인
			int mcnt = dao.memberCount(gsel);
			System.out.println(mcnt);
			if(mcnt == 0) {
				
				int result = dao.studygroupDelete(gsel);
				
				if(result == 1) {
					System.out.println("-----------------------------------------------------------");		
					System.out.println("삭제가 완료되었습니다.");
				}else {
					System.out.println("-----------------------------------------------------------");		
					System.out.println("삭제를 실패했습니다.");
				}
				
				System.out.println("-----------------------------------------------------------");
				System.out.print("*enter를 입력하면 이전 화면으로 돌아갑니다.");
				String input = scan.nextLine();
				
				if(input.equals("")) {
					studygroupDeleteMenu();
				}else {
					studygroupDeleteMenu();
				}
				
			}else {
				System.out.println("해당그룹의 인원이 존재합니다.");
				studygroupDelete();
			}
		}
	}

	/**
	 * 스터디그룹 멤버를 삭제하는 화면입니다.
	 */
	private static void studygroupMemberDelete() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t스터디 그룹 인원 삭제");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t목적\t\t\t등록일\t\t\t종료일");
		
		ArrayList<StudygroupDTO> glist = dao.studygroupList("ing");
		
		for(StudygroupDTO dto : glist) {
			System.out.printf("%s\t%s\t%s\t\t%s\n", dto.getSeq(), dto.getGoal(), dto.getRegistrationDate(), dto.getEndDate());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");		
		System.out.println("*미입력시 이전 화면으로 돌아갑니다.");
		System.out.print("번호 : ");
		//스터디그룹 번호
		String gsel = scan.nextLine();
		
		//미입력시 이전화면으로 이동
		if(gsel.equals("")) {
			studygroupDeleteMenu();
		}else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t스터디 그룹 인원 삭제");
			System.out.println("-----------------------------------------------------------");
			System.out.println("번호(등록번호)\t이름\t\t전화번호\t\t\t직위");
			
			ArrayList<StudygroupMemberDTO> mlist = dao.studyGroupMemberFind(gsel);
			

			ArrayList<String> selist = new ArrayList<String>();
			
			for(StudygroupMemberDTO dto : mlist) {
				System.out.printf("%s\t\t%s\t\t%s\t\t%s	\n", dto.getSeq(), dto.getName(), dto.getTel(), dto.getRank());
				selist.add(dto.getSeq());
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");		
			System.out.println("*미입력시 이전 화면으로 돌아갑니다.");
			//미입력시 이전화면으로 이동
			
			selist.add("");
			boolean range = true;
			String dsel = "";
			while(range) {
				System.out.print("번호 : ");
				dsel = scan.nextLine();
				if(selist.contains(dsel)) {
					range = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
				}
			}
			
			if(dsel.equals("")) {
				studygroupDeleteMenu();
			}else {
				
				int result = dao.studygroupMemberDelete(dsel);
				
				if(result == 1) {
					System.out.println("-----------------------------------------------------------");		
					System.out.println("삭제가 완료되었습니다.");
				}else {
					System.out.println("-----------------------------------------------------------");		
					System.out.println("삭제를 실패했습니다.");
				}
				
				System.out.println("-----------------------------------------------------------");
				System.out.print("*enter를 입력하면 이전 화면으로 돌아갑니다.");
				String input = scan.nextLine();
				
				if(input.equals("")) {
					studygroupDeleteMenu();
				}else {
					studygroupDeleteMenu();
				}
				
			}
			
		}
		
	}
	
}
