package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudygroupInfoDAO;
import com.test.dto.InTheWorkCourseDTO;
import com.test.dto.StudentInCourseDTO;
import com.test.dto.StudygroupDTO;
import com.test.validation.ManagerValidation;

/**
 * 
 * @author 이진혁
 * 관리자 - 스터디그룹 관리 - 스터디 그룹 등록
 *
 */
public class StudygroupAdd {
	
	private static Scanner scan;
	private static StudygroupInfoDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new StudygroupInfoDAO();
	}
	
	public static void main(String[] args) {
		studygroupAddMenu();
	}

	/**
	 * 스터디그룹 등록 메인 화면입니다.
	 */
	public static void studygroupAddMenu() {
		
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t스터디 그룹 등록");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 스터디 그룹 등록");
			System.out.println("2. 스터디 그룹 인원 등록");
			System.out.println("0. 이전화면");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println("*번호를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택을 통한 화면 이등
			if(sel.equals("1")) {
				// 스터디그룹 등록 화면으로 이동
				loop = false;
				studygroupAdd();
				break;
			}else if(sel.equals("2")) {
				// 스터디그룹 인원 등록 화면으로 이동
				loop = false;
				studygroupMemberAdd();
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
	 * 스터디그룹 등록 화면입니다.
	 */
	private static void studygroupAdd() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t스터디 그룹 등록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("* 스터디그룹의 목적을 입력해주세요.");
		System.out.println("* 미입력시 이전 화면으로 돌아갑니다.");
		System.out.println("* 목적은 30자 이내로 입력해주세요.");
		System.out.println();
		
		boolean loop = true;
		String goal = "";
		while(loop) {
			System.out.println("-----------------------------------------------------------");
			System.out.print("목적 : ");
			goal = scan.nextLine();
			boolean result = ManagerValidation.studygroupGoal(goal);
			if(result == true) {
				loop = false;
				break;
			}else {
				System.out.println("30자이내로 입력해주세요.");
			}
		}
		//아무것도 입력하지 않으면 이전 화면으로 돌아감.
		if(goal.equals("")) {
			studygroupAddMenu();
		}else {
			
			int result = dao.studygroupAdd(goal);
			
			//등록 성공 여부 판단
			if(result == 1) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*등록이 성공했습니다.");
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*등록이 실패했습니다.");
			}
			System.out.println("-----------------------------------------------------------");
			System.out.print("*enter를 입력하면 이전 화면으로 돌아갑니다.");
			String input = scan.nextLine();
			
			if(input.equals("")) {
				studygroupAddMenu();
			}else {
				studygroupAddMenu();
			}
			
		}
		
		
	}
	
	/**
	 * 스터디그룹 인원을 등록하는 화면입니다.
	 */
	private static void studygroupMemberAdd() {
				
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t스터디 그룹 인원 등록");
		System.out.println("-----------------------------------------------------------");
		System.out.println("번호\t목적\t\t\t등록일\t\t종료일");
		
		//끝나지 않은 스터디그룹만 목록으로 출력
		ArrayList<StudygroupDTO> glist = dao.studygroupList("ing");
		
		ArrayList<String> selist = new ArrayList<String>();
		
		for(StudygroupDTO dto : glist) {
			System.out.printf("%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getGoal(), dto.getRegistrationDate(), dto.getEndDate());
			selist.add(dto.getSeq());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t\t\t번호 선택");
		System.out.println("-----------------------------------------------------------");		
		System.out.println("*미입력시 이전 화면으로 돌아갑니다.");
		
		selist.add("");
		boolean range = true;
		String gsel = "";
		while(range) {
			System.out.print("번호 : ");
			gsel = scan.nextLine();
			if(selist.contains(gsel)) {
				range = false;
				break;
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
			}
		}
		
		//미입력시 이전화면으로 이동
		if(gsel.equals("")) {
			studygroupAddMenu();
		}else {
			//진행중인 개설 과정 목록을 출력
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t스터디 그룹 인원 등록");
			System.out.println("-----------------------------------------------------------");
			System.out.println("번호\t과정명\t\t\t\t\t\t\t\t강의실");
			ArrayList<InTheWorkCourseDTO> list = dao.InTheWorkCourseList();
			
			ArrayList<String> selist2 = new ArrayList<String>();
			
			for(InTheWorkCourseDTO dto : list) {
				
				System.out.printf("%s\t%s\t%s\n"
						, dto.getSeq()
						, dto.getCourseName()
						, dto.getClassroomName());
				selist2.add(dto.getSeq());
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t번호 선택");
			System.out.println("-----------------------------------------------------------");			
			System.out.println("* 스터디 그룹의 인원이 진행 중인 과정번호를 선택해주세요.");
			
			selist2.add("");
			boolean sgexist = true;
			String sel = "";
			while(sgexist) {
				System.out.print("번호 : ");
				sel = scan.nextLine();
				if(selist2.contains(sel)) {
					sgexist = false;
					break;
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
				}
			}
			
			//미입력시 이전화면으로 돌아감.
			if(sel.equals("")) {
				studygroupAddMenu();
			}else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("\t\t\t\t스터디 그룹 인원 등록");
				System.out.println("-----------------------------------------------------------");			
				System.out.println("번호(수강번호)\t이름\t\t전화번호");
				
				ArrayList<StudentInCourseDTO> slist = dao.studyGroupMemberList(sel);
				
				ArrayList<String> selist5 = new ArrayList<String>();
				
				for(StudentInCourseDTO dto : slist) {
					
					System.out.printf("%s\t\t\t%s\t\t%s\n", dto.getSeq(), dto.getStudentName(), dto.getStudentTel());
					selist5.add(dto.getSeq());
				}
				selist5.add("end");
				System.out.println("-----------------------------------------------------------");
				System.out.println("\t\t\t\t스터디 그룹 인원 등록");
				System.out.println("-----------------------------------------------------------");
				boolean exist = dao.existReader(gsel);
				if(exist == false) {
					
					System.out.println("*팀장 번호를 입력해주세요.");
					
					selist5.add("");
					boolean gmexist = true;
					String msel = "";
					while(gmexist) {
						System.out.print("번호 : ");
						msel = scan.nextLine();
						if(selist5.contains(msel)) {
							gmexist = false;
							break;
						}else {
							System.out.println("-----------------------------------------------------------");
							System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
						}
					}
					
					int result = dao.studygroupMemberAdd(gsel, msel, "팀장");
					
					if(result == 1) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*등록이 성공했습니다.");
					}else {
						System.out.println("-----------------------------------------------------------");
						System.out.println("*등록이 실패했습니다.");
					}
				}
				ArrayList<String> mlist = new ArrayList<String>();
				System.out.println("-----------------------------------------------------------");			
				System.out.println("*end를 입력하면 입력이 종료됩니다.");
				System.out.println("*팀원 번호를 입력해주세요.");
				
				//해당 스터디그룹의 인원수를 가져온다.
				int cnt = dao.memberCount(gsel);
				if(cnt == 6) {
					System.out.println("-----------------------------------------------------------");			
					System.out.println("인원이 6명이기 때문에 더 이상 등록 불가합니다.");
				}
				//정원에 나은 인원수 만큼 for문 실행
				for(int i = 0 ; i < 6-cnt ; i++) {
					
					boolean cntexist = true;
					String tsel = "";
					while(cntexist) {
						System.out.print("번호 : ");
						tsel = scan.nextLine();
						if(selist5.contains(tsel)) {
							cntexist = false;
							break;
						}else {
							System.out.println("-----------------------------------------------------------");
							System.out.println("*입력한 번호는 데이터에 존재하지 않습니다.");
						}
					}
					
					if(tsel.equals("end")) {
						break;
					}
					mlist.add(tsel);
					
				}
				
				for(String s : mlist) {
					
					int result = dao.studygroupMemberAdd(gsel, s, "팀원");
					
					if(result == 1) {
						System.out.println(s + "번 교육생이 팀원으로 등록되었습니다.");
					}else {
						System.out.println(s + "번 교육생이 등록 실패했습니다.");
					}
					
				}
				
				System.out.println("-----------------------------------------------------------");
				System.out.print("*enter를 입력하면 이전 화면으로 돌아갑니다.");
				String input = scan.nextLine();
				
				if(input.equals("")) {
					studygroupAddMenu();
				}else {
					studygroupAddMenu();
				}
			}
		}
		
		
	}

}
