package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.StudentManagementDAO;
import com.test.dto.OpenCourseDTO;
import com.test.dto.SignUpDTO;
import com.test.dto.StudentDTO;
import com.test.dto.VwOneStudentDTO;

/**
 * 교육생 관리에 대한 클래스입니다.
 * @author 이청강
 */

public class StudentManagement {
	
	private static Scanner scan;
	public static StudentManagementDAO dao;
	
	static {
		dao = new StudentManagementDAO();
		scan = new Scanner(System.in);
	}
	
	public static void menu() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.printf("\t\t\t교육생 관리\n");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 교육생 등록");
			System.out.println("2. 교육생 과정 등록");
			System.out.println("3. 교육생 조회 및 수정");
			System.out.println("4. 메인화면으로 돌아가기");
			System.out.println("-----------------------------------------------------------");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			switch (menuInput) {
			case "1":
				//교육생 등록
				registration();
				pause();
				break;
			case "2":
				//교육생 과정 등록
				signUp();
				pause();
				break;
			case "3":
				//교육생 조회 및 수정
				findAndEditStudent();
				pause();
				break;
			default:
				System.out.println("메인화면으로 돌아갑니다.");
				System.out.println();
				ManagerMenu.managerMenu();
				loop = false;
				break;
			}
			
		}
		
	}
	
	/**
	 * 교육생 정보에 대한 수정 및 삭제 전, 입력할 교육생 번호를 찾기 위해
	 * 교육생의 정보를 목록으로 출력해주는 메서드입니다.
	 */
	private static void findAndEditStudent() {

		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t교육생 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		
		boolean loop = true;
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			list = dao.findAllStudent(null);
			StudentDTO dto = new StudentDTO();
			
			int length = list.size();
			
			
			System.out.println("[번호]\t[이름]\t[주민번호]\t[전화번호]\t[등록일]\t[수강신청횟수]");
			
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				
				dto = list.get(i);
				
				System.out.printf("%s\t%s\t%s\t\t%-10s\t%s\t%s\t\n"
						, dto.getSeq()
						, dto.getName()
						, dto.getSsn()
						, dto.getTel()
						, dto.getRegistrationDate()
						, dto.getRegistrationCount());
				
				}	
			
			System.out.println();
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 교육생 수정하기");
			System.out.println("4. 교육생 삭제하기");
			System.out.println("5. [교육생 관리] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			
			switch (menuInput) {
			case "1":
				pageIndex = pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
				break;
			case "2":
				pageIndex = pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
				break;
			case "3":
				System.out.println();
				System.out.println("수정하고자하는 교육생의 번호를 입력해주세요.");
				System.out.print("교육생 번호: ");
				String seqToEdit = scan.nextLine();
				editStudent(seqToEdit);
				break;
			case "4":
				System.out.println();
				System.out.println("삭제하고자하는 교육생의 번호를 입력해주세요.");
				System.out.print("교육생 번호: ");
				String seqToDelete = scan.nextLine();
				deleteStudent(seqToDelete);
				break;
			default:
				loop = false;
				break;
				
			}
			
		}
		
	}
	
	/**
	 * 특정 교육생의 정보를 수정하는 메서드입니다.
	 * @param seqToEdit 정보를 수정할 교육생의 번호
	 */
	private static void editStudent(String seqToEdit) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t교육생 수정\n");
		System.out.println("-----------------------------------------------------------");
		oneStudent(seqToEdit);
		System.out.println("-----------------------------------------------------------");
		System.out.println("수정하지 않을 항목에 대해서는 아무것도 입력하지 않은 채 엔터를 눌러주세요.");
		System.out.println();
		
		System.out.println("해당 교육생의 새 이름을 적어주세요.");
		System.out.print("수정할 새 이름: ");
		String nameInput = scan.nextLine();
		System.out.println();
		
		System.out.println("해당 교육생의 새 전화번호를 적어주세요.");
		System.out.print("수정할 새 전화번호: ");
		String telInput = scan.nextLine();
		System.out.println();
		 
		StudentDTO newDto = new StudentDTO();
		newDto.setSeq(seqToEdit);
		newDto.setName(nameInput);
		newDto.setTel(telInput);
		
		int check = dao.editStudent(newDto);
		
		if (check > 0) {
			System.out.println("교육생 수정이 완료되었습니다.");
		} else if (check == 0){
			System.out.println("교육생 수정에 실패하였습니다. [교육생 조회 및 수정] 메뉴로 돌아갑니다.");
		} else {
			System.out.println("수정한 내용이 없습니다. [교육생 조회 및 수정] 메뉴로 돌아갑니다.");
		}
		System.out.println();
		

	}
	
	/**
	 * 특정 교육생의 정보를 삭제하는 메서드입니다.
	 * @param seqToDelete 정보를 삭제할 교육생의 번호
	 */
	private static void deleteStudent(String seqToDelete) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t교육생 삭제\n");
		System.out.println("-----------------------------------------------------------");		
		oneStudent(seqToDelete);
		System.out.println("-----------------------------------------------------------");
		System.out.println("한 번 삭제된 교육생 정보는 되돌릴 수 없습니다.");
		System.out.print("해당 교육생의 정보를 삭제하시겠습니까(y/n)?: ");
		String yesOrNo = scan.nextLine();
		
		if (yesOrNo.equals("y")) {
			
			if (dao.deleteStudent(seqToDelete) > 0) {
				System.out.println("교육생 정보 삭제가 완료되었습니다.");
			} else {
				System.out.println("교육생 정보 삭제가 실패하였습니다.");
			}
			
		}
		
		System.out.println("[교육생 조회 및 수정] 메뉴로 돌아갑니다.");
		System.out.println();
		
		
	}
	
	/**
	 * 특정 교육생의 정보에 대한 목록을 출력하는 메서드입니다.
	 * @param seq 조회할 교육생의 번호를 매개변수로 받습니다.
	 */
	private static void oneStudent(String seq) {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		list = dao.findAllStudent(seq);
		System.out.println("[번호]\t[이름]\t[주민번호]\t[전화번호]\t[등록일]\t[수강신청횟수]");
		for (StudentDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\t\t%-10s\t%s\t%s\t\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getSsn()
					, dto.getTel()
					, dto.getRegistrationDate()
					, dto.getRegistrationCount());

		}
	}
	
	/**
	 * 교육생을 등록하는 메서드입니다.
	 */
	public static void registration() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t교육생 등록\n");
		System.out.println("-----------------------------------------------------------");
		System.out.println("등록하고자하는 교육생의 이름을 입력해주세요.");
		System.out.println("이름은 한글 2글자 이상, 5글자 이내로 입력하셔야 합니다.");
		System.out.print("이름 : ");
		String inputName = scan.nextLine();
		//boolean checker = true;
		//if (inputName == 유효성검사) {checker = false;};
		System.out.println();
		
		System.out.println("등록하고자하는 교육생의 주민등록번호 뒷자리를 입력해주세요.");
		System.out.println("이는 비밀번호로 사용되며, 7자리 숫자로 입력하셔야 합니다.");
		System.out.print("주민등록번호 뒷자리 : ");
		String inputSsn = scan.nextLine();
		//boolean checker = true;
		//if (inputSsn == 유효성검사) {checker = false;};
		System.out.println();
		
		System.out.println("등록하고자하는 교육생의 전화번호를 입력해주세요.");
		System.out.println("입력 시, 대시('-')를 빼고 입력해주세요.");
		System.out.print("전화번호 : ");
		String inputTel = scan.nextLine();
		//boolean checker = true;
		//if (inputName == 유효성검사) {checker = false;};
		System.out.println();
		
		//int updateChecker = dao.add(inputName, inputSsn, inputTel);
		StudentDTO dto = new StudentDTO();
		dto.setName(inputName);
		dto.setSsn(inputSsn);
		dto.setTel(inputTel);
		int updateChecker = dao.add(dto);		
	
		//if(checker ||
		if(updateChecker > 0) {
			System.out.printf("%s 교육생의 등록이 완료되었습니다.\n", inputName);
		} else { 
			System.out.println("교육생 등록에 실패하였습니다."); 
		};
		
	}
	
	/**
	 * 교육생을 특정 개설 과정에 수강(등록)시키는 메서드입니다.
	 */
	public static void signUp() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t교육생 과정 등록\n");
		System.out.println("-----------------------------------------------------------");
		findStudentBrief();
		System.out.println();
		
		System.out.print("등록하고자하는 교육생의 번호를 입력해주세요: ");
		String studentSeq = scan.nextLine();
		System.out.println();
	
		System.out.print("등록하고자하는 개설과정의 번호를 입력해주세요: ");
		String openCourseSeq = scan.nextLine();
		System.out.println();
		
		SignUpDTO dto = new SignUpDTO();
		
		dto.setStudentSeq(studentSeq);
		dto.setOpenCourseSeq(openCourseSeq);
		
		if (dao.addSignUp(dto) > 0) {
			System.out.println("과정 수강 등록이 완료되었습니다.");
		} else {
			System.out.println("과정 수강 등록에 실패하였습니다.");
		};

	}
	
	/**
	 * 교육생 정보를 번호/이름/주민등록번호/전화번호/등록일 순으로 나열해서 개설 예정인 과정과 함께 보여주는 메서드입니다.
	 @return 조회 후, 교육과정을 등록할 지(true) 메인 메뉴로 돌아갈 지(false) 결정해주는 boolean을 반환합니다.
	 */
	private static void findStudentBrief() {
		
		ArrayList<StudentDTO> studentBriefList = new ArrayList<StudentDTO>();
		int pageIndex = 0;
		String inputMenu = null;
		boolean loop = true;
		
		studentBriefList = dao.findBriefStudent();
		int length = studentBriefList.size();
		int pageSize = 20;
		
		while (loop) {
			
			System.out.println("현재 개설 예정인 과정 목록입니다.\n");
			findexpectedCourse();
			System.out.println("-----------------------------------------------------------");
			System.out.println("교육생 명단입니다.\n");
			
			System.out.println("[교육생번호]\t[이름]\t[주민등록번호]\t[전화번호]\t[등록일]");
			
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				
				System.out.printf("%s\t\t%s\t%s\t\t%-10s\t%s\n"
						, studentBriefList.get(i).getSeq()
						, studentBriefList.get(i).getName()
						, studentBriefList.get(i).getSsn()
						, studentBriefList.get(i).getTel()
						, studentBriefList.get(i).getRegistrationDate()	
						);
				
			}
			System.out.println();
			
			System.out.println("1. 다음페이지");
			System.out.println("2. 이전페이지");
			System.out.println("3. 교육과정 등록하기");
			System.out.print("메뉴 입력: ");
			inputMenu = scan.nextLine();

			
			switch (inputMenu) {
			case "1":
				pageIndex = pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
				break;
			case "2":
				pageIndex = pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
				break;
			default:
				loop = false;
				break;
			}
			
		}

	}
	
	/**
	 * 진행 예정인 개설과정을 나열해서 보여주는 메서드입니다.
	 */
	private static void findexpectedCourse() {
		
		ArrayList<OpenCourseDTO> expectedCourseList = new ArrayList<OpenCourseDTO>();
		
		expectedCourseList = dao.findExpectedCourse();
		
		System.out.println("[개설과정번호]\t[시작일자]\t\t[과목명]");
		
		for (OpenCourseDTO dto : expectedCourseList) {
			System.out.printf("%d\t\t%s\t%s\n"
								, dto.getSeq()
								, dto.getStartDate()
								, dto.getName());
		}
		
	}
	
	/**
	 * boolean 매개 변수에 따라 페이지를 이동시키는 메서드 입니다.
	 * @param pageIndex 현재 페이지 수를 나타냅니다.
	 * @param length 페이지에 들어가는 요소의 총 갯수입니다.
	 * @param pageSize 한 페이지에 나타낼 요소의 개수를 나타냅니다.
	 * @param GoOrBack true일 때 페이지 전진을 실행하고, false일 때 페이지 후진을 실행하기 위한 논리 변수입니다.
	 * @return 현재 페이지 수를 나타내는 pageIndex를 반환합니다.
	 */
	public static int pageForwardSwitch(int pageIndex, int length, int pageSize, boolean GoOrBack) {
		
		if (GoOrBack) {
			pageIndex += pageSize;
			
			if (length > pageIndex) {
			} else {
				System.out.println("마지막 페이지 입니다.");
				pageIndex -= pageSize;
			}
			
		} else {
			pageIndex -= pageSize;
			if (pageIndex > -pageSize) {
			} else {
				System.out.println("첫 페이지 입니다.");
				pageIndex += pageSize;
			}
		}
		return pageIndex;

	}
	
	public static void pause() {
		System.out.println("엔터를 누르면 [교육생 관리] 메뉴로 돌아갑니다.");
		scan.nextLine();
	}
	
}


























