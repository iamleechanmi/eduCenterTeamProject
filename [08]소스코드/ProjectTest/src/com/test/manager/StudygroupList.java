package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudygroupInfoDAO;
import com.test.dto.StudygroupDTO;
import com.test.dto.StudygroupMemberDTO;
import com.test.dto.StudygroupRegistrationDTO;

/**
 * 
 * @author 이진혁
 * 관리자 - 스터디그룹 관리 - 스터디그룹 조회
 *
 */
public class StudygroupList {
	
	private static Scanner scan;
	private static StudygroupInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new StudygroupInfoDAO();
	}
	
	public static void main(String[] args) {
		studygroupList();
	}
	
	/**
	 * 전체 스터디그룹을 조회하는 화면
	 */
	public static void studygroupList() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t스터디 그룹 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t목적\t\t\t등록일\t\t종료일");
		
		ArrayList<StudygroupDTO> list = dao.studygroupList("all");
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(StudygroupDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getGoal(), dto.getRegistrationDate(), dto.getEndDate());
			selist.add(dto.getSeq());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 스터디 그룹 인원을 조회합니다.");
		
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
		
		//미입력시 이전 화면으로 돌아간다.
		if(sel.equals("")) {
			StudygroupInformation.studygroupInformationMenu();
		}else {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t스터디그룹 인원 조회");
			System.out.println("-----------------------------------------------------------");
			System.out.println("번호(등록번호)\t이름\t\t전화번호\t직위");
			
			ArrayList<StudygroupMemberDTO> mlist = dao.studyGroupMemberFind(sel);
			
			for(StudygroupMemberDTO dto : mlist) {
				System.out.printf("%s\t\t%s\t\t%s\t%s	\n", dto.getSeq(), dto.getName(), dto.getTel(), dto.getRank());
			}
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.print("enter를 입력하면 이전 화면으로 돌아갑니다.");
		String input = scan.nextLine();
		if(input.equals("")) {
			StudygroupInformation.studygroupInformationMenu();
		}else {
			StudygroupInformation.studygroupInformationMenu();	
		}
		
	}

}
