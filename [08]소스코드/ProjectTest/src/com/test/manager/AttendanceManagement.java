package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.AttendanceManagementDAO;
import com.test.dao.StudentManagementDAO;
import com.test.dto.StudentDTO;
import com.test.dto.VwFindOpenCourseBriefDTO;
import com.test.dto.VwfindAttByCourseDTO;
import com.test.dto.VwfindAttByStudentDTO;

/**
 * 출결 관리 및 출결 조회에 대한 클래스 파일입니다.
 * @author 이청강
 *
 */

public class AttendanceManagement {
	
	private static Scanner scan;
	private static AttendanceManagementDAO dao;
	private static StudentManagementDAO importedDao;
	
	static {
		scan = new Scanner(System.in);
		dao = new AttendanceManagementDAO();
		importedDao = new StudentManagementDAO();
	}
	
	public static void menu() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.printf("\t\t출결 관리 및 출결 조회\n");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 과정별 출결 조회하기");
			System.out.println("2. 교육생별 출결 조회하기");
			System.out.println("3. 교육생 출결 수정하기");
			System.out.println("4. 메인화면으로 돌아가기");
			System.out.println("-----------------------------------------------------------");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			
			switch (menuInput) {
			case "1":
				findCourse();
				break;
			case "2":
				findStudent();
				break;
			case "3":
				findStudentBrief();
				break;
			default:
				ManagerMenu.managerMenu();
				loop = false;
				break;
				
				
			}
		
		}
		
	}
	
	/**
	 * 수정 전, 교육생 목록을 확인할 수 있도록 정보를 출력하는 메서드입니다.
	 */
	private static void findStudentBrief() {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생 출결 수정하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			list = importedDao.findBriefStudent();
			StudentDTO dto = new StudentDTO();
			
			int length = list.size();
			
			System.out.println("교육센터 내의 교육생 목록입니다.");
			System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[등록일]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t%s\t%s\t\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getTel()
									, dto.getRegistrationDate());
				
			}
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 교육생 선택");
			System.out.println("4. [출결 관리 및 출결 조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			switch (menuInput) {
			case "1":
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
				break;
			case "2":
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
				break;
			case "3":
				System.out.println("출결 상황을 수정하고자하는 교육생의 번호를 입력하세요.");
				System.out.print("교육생 번호 입력: ");
				String studentSeq = scan.nextLine();
				
				System.out.println("출결 조회할 연도와 월, 일을 입력해주세요.");
				System.out.print("연도 입력(예: 2020): ");
				String year = scan.nextLine();
				System.out.println();
				
				System.out.println("월 입력(예: 07): ");
				String month = scan.nextLine();
				System.out.println();
				
				System.out.println("일 입력(예: 06): ");
				String date = scan.nextLine();
				System.out.println();
				
				findAttendanceOfStudent(studentSeq, year, month, date);
				loop = false;
			case "4":
				loop = false;
				pause();
				break;
			default:
				System.out.println("잘못 입력하셨습니다. [출결 관리 및 출결 조회] 메뉴로 돌아갑니다.");
				pause();
				loop = false;
				break;
			}
			
		}
		
	}

	/**
	 * 출결 수정 전, 특정 교육생의 특정 출결 정보를 출력하는 메서드입니다. 
	 * @param studentSeq 수정할 출결 정보에 대한 교육생 번호
	 * @param year 출결 정보를 검색하기 위한 연도
	 * @param month 검색용 월
	 * @param date 검색용 일자
	 * 
	 */
	private static void findAttendanceOfStudent(String studentSeq, String year, String month, String date) {

		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생 출결 수정하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while(loop) {
			
			ArrayList<VwfindAttByStudentDTO> list = new ArrayList<VwfindAttByStudentDTO>();
			list = dao.findAttByStudent(studentSeq, year, month, date);
			VwfindAttByStudentDTO dto = new VwfindAttByStudentDTO();
			
			int length = list.size();
			String oneDate = "";
			
			System.out.println("교육생의 해당 날짜 출결 정보입니다.");
			System.out.println("[출결날짜]\t[근태상황]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				String convertedDate = dto.getTempDate().replace("-", "");
				oneDate = convertedDate.substring(2, 4) + "/" + convertedDate.substring(4, 6) + "/" + convertedDate.substring(6, 8);
				
				System.out.printf("%s\t%s\t\n"
									, oneDate
									, dto.getAttendanceStatus());

			}
			System.out.println("-----------------------------------------------------------");

			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 근태 상황 수정하기");
			System.out.println("4. [출결 관리 및 출결 조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			if (menuInput.equals("1")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
			} else if (menuInput.equals("2")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
			} else if (menuInput.equals("3")){
				System.out.println("새 근태상황(출석, 지각, 조퇴, 외출, 병가, 결석)을 입력해주세요.");
				System.out.print("새 근태상황: ");
				String statusInput = scan.nextLine();
				
				if (statusInput.equals("출석") || statusInput.equals("지각")
					|| statusInput.equals("조퇴") || statusInput.equals("외출")
					|| statusInput.equals("병가") || statusInput.equals("결석")) {
					
				dao.editAttendanceStatus(statusInput, oneDate, studentSeq);
				
				loop = false;
				} else {
					System.out.println("근태상황을 잘못 입력하셨습니다. 다시 입력해주세요.");
				}
				
			} else {
				loop = false;
			}
			
		}
		
	}

	/**
	 * 교육생별로 출결을 조회하기 전에, 교육생 번호를 입력할 수 있도록 
	 * 교육생 목록을 출력하는 메서드입니다.
	 */
	private static void findStudent() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			list = importedDao.findBriefStudent();
			StudentDTO dto = new StudentDTO();
			
			int length = list.size();
			
			System.out.println("교육센터 내의 교육생 목록입니다.");
			System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[등록일]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t%s\t%s\t\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getTel()
									, dto.getRegistrationDate());
				
			}
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 교육생 선택");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			switch (menuInput) {
			case "1":
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
				break;
			case "2":
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
				break;
			case "3":
				System.out.println("출결 상황을 조회하고자하는 교육생의 번호를 입력하세요.");
				System.out.print("교육생 번호 입력: ");
				String studentSeq = scan.nextLine();
				findOneStudent(studentSeq);
			default:
				pause();
				loop = false;
				break;
			}
			
		}
		
	}

	/**
	 * 입력받은 특정 교육생 번호에 해당하는 교육생 정보를 출력하는 메서드입니다.
	 * @param studentSeq 출력할 교육생 정보의 식별자로 사용할 교육생 번호
	 */
	private static void findOneStudent(String studentSeq) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");
		
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		list = importedDao.findAllStudent(studentSeq);
		
		System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[등록일]");
		for (StudentDTO dto : list) {
			System.out.printf("%s\t\t%s\t%s\t%s\t\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getTel()
					, dto.getRegistrationDate());
		}
		System.out.println("-----------------------------------------------------------");
		
		System.out.println("1. 연 단위 출결 조회");
		System.out.println("2. 월 단위 출결 조회");
		System.out.println("3. 일 단위 출결 조회");
		System.out.print("메뉴 입력: ");
		String menuInput = scan.nextLine();
		System.out.println();
		
		String year = "";
		String month = "";
		String date = "";
		
		switch (menuInput) {
		case "1":
			System.out.println("출결 조회할 연도를 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			
			findAttendanceByStudent(studentSeq, year, null, null);
			break;
		case "2":
			System.out.println("출결 조회할 연도와 월을 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			System.out.println();
			
			System.out.println("월 입력(예: 07): ");
			month = scan.nextLine();
			System.out.println();
			
			findAttendanceByStudent(studentSeq, year, month, null);
			break;
		case "3":
			System.out.println("출결 조회할 연도와 월, 일을 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			System.out.println();
			
			System.out.println("월 입력(예: 07): ");
			month = scan.nextLine();
			System.out.println();
			
			System.out.println("일 입력(예: 06): ");
			date = scan.nextLine();
			System.out.println();
			
			findAttendanceByStudent(studentSeq, year, month, date);
			break;
		default:
			System.out.println("잘못 입력하셨습니다. [출결 관리 및 출결 조회] 메뉴로 돌아갑니다.");
			break;
		}
		
		
		
	}

	/**
	 * 입력받은 기간단위별로 특정 교육생의 출결 정보를 출력하는 메서드입니다.
	 * @param studentSeq 출력할 출결 정보를 식별할 특정 교육생 번호
	 * @param year 출력할 출결 정보의 조건이 되는 기간단위 중 연도
	 * @param month 기간단위 중 월
	 * @param date 기간단위 중 일
	 */
	private static void findAttendanceByStudent(String studentSeq, String year, String month, String date) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while(loop) {
			
			ArrayList<VwfindAttByStudentDTO> list = new ArrayList<VwfindAttByStudentDTO>();
			list = dao.findAttByStudent(studentSeq, year, month, date);
			VwfindAttByStudentDTO dto = new VwfindAttByStudentDTO();
			
			int length = list.size();
			
			System.out.println("해당 교육생의 출결 조회 결과입니다.");
			System.out.println("[출결날짜]\t[근태상황]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				String convertedDate = dto.getTempDate().replace("-", "");
				
				System.out.printf("%s\t%s\t\n"
									, convertedDate.substring(2, 4) + "/" + convertedDate.substring(4, 6) + "/" + convertedDate.substring(6, 8)
									, dto.getAttendanceStatus());
			}
			System.out.println("-----------------------------------------------------------");

			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. [출결 관리 및 출결 조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			if (menuInput.equals("1")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
			} else if (menuInput.equals("2")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
			} else {
				loop = false;
			}
			
		}
		
	}

	/**
	 * 과정별 출결을 조회하기 전, 특정 과정을 선택할 수 있도록 
	 * 개설 과정 정보를 출력하는 메서드입니다.
	 */
	private static void findCourse() {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t과정별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<VwFindOpenCourseBriefDTO> list = new ArrayList<VwFindOpenCourseBriefDTO>();
			list = dao.findOpenCourseBrief(null);
			VwFindOpenCourseBriefDTO dto = new VwFindOpenCourseBriefDTO();
			
			int length = list.size();
			
			System.out.println("교육센터 내의 개설과정 목록입니다.");
			System.out.println("[개설과정번호]\t[강의실명]\t[시작일자]\t[과정명]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				
				dto = list.get(i);
				
				System.out.printf("%s\t\t%-10s\t%s\t%s\n"
									, dto.getCourseSeq()
									, dto.getClassroomName()
									, dto.getOpenCourseStartDate()
									, dto.getCourseName());
				
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 과정 선택");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			System.out.println();
			
			if (menuInput.equals("1")) {
				StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				System.out.println();
			} else if (menuInput.equals("2")) {
				StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				System.out.println();
			} else if (menuInput.equals("3")) {
				System.out.println("출결 상황을 조회하고하는 개설 과정 번호를 입력하세요.");
				System.out.print("개설 과정 번호 입력: ");
				String courseSeq = scan.nextLine();
				findOneCourse(courseSeq);
				loop = false;
			} else {
				loop = false;
			}
			
			
		}
		
		
	}
	
	/**
	 * 선택한 특정 과정의 정보를 출력해주는 메서드입니다.
	 * @param courseSeq
	 */
	private static void findOneCourse(String courseSeq) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t과정별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");

		System.out.println("[개설과정번호]\t[강의실명]\t[시작일자]\t[과정명]");
		
		ArrayList<VwFindOpenCourseBriefDTO> list = new ArrayList<VwFindOpenCourseBriefDTO>();
		list = dao.findOpenCourseBrief(courseSeq);
		
		for (VwFindOpenCourseBriefDTO dto : list) {
			System.out.printf("%s\t\t%s\t\t%s\t%s\n"
								, dto.getCourseSeq()
								, dto.getClassroomName()
								, dto.getOpenCourseStartDate()
								, dto.getCourseName());
		}
		System.out.println("-----------------------------------------------------------");
		
		System.out.println("1. 연 단위 출결조회");
		System.out.println("2. 월 단위 출결조회");
		System.out.println("3. 일 단위 출결조회");
		System.out.print("메뉴 입력: ");
		String menuInput = scan.nextLine();
		
		String year = "";
		String month = "";
		String date = "";
		
		switch (menuInput) {
		case "1":
			System.out.println("출결 조회할 연도를 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			
			findAttendanceByCourse(courseSeq, year, null, null);
			break;
		case "2":
			System.out.println("출결 조회할 연도와 월을 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			System.out.println();
			
			System.out.println("월 입력(예: 07): ");
			month = scan.nextLine();
			System.out.println();
			
			findAttendanceByCourse(courseSeq, year, month, null);
			break;
		case "3":
			System.out.println("출결 조회할 연도와 월, 일을 입력해주세요.");
			System.out.print("연도 입력(예: 2020): ");
			year = scan.nextLine();
			System.out.println();
			
			System.out.println("월 입력(예: 07): ");
			month = scan.nextLine();
			System.out.println();
			
			System.out.println("일 입력(예: 06): ");
			date = scan.nextLine();
			System.out.println();
			
			findAttendanceByCourse(courseSeq, year, month, date);
			break;
		default:
			System.out.println("잘못 입력하셨습니다.\n[출결 관리 및 출결 조회] 메뉴로 돌아갑니다.");
		}
		
	}

	/**
	 * 특정 과정의 출결정보를 입력받은 단위기간별로 출력하는 메서드입니다.
	 * @param courseSeq 출결을 조회할 개설 과정의 번호
	 * @param year 출결을 검색할 단위기간 연도
	 * @param month 단위기간 월
	 * @param date 단위기간 일
	 */
	private static void findAttendanceByCourse(String courseSeq, String year, String month, String date) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t과정별 출결 조회하기\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<VwfindAttByCourseDTO> list = new ArrayList<VwfindAttByCourseDTO>();
			list = dao.findAttByCourse(courseSeq, year, month, date);
			VwfindAttByCourseDTO dto = new VwfindAttByCourseDTO();
			
			int length = list.size();
			
			System.out.println("해당 과정의 출결 조회 결과입니다.");
			System.out.println("[교육생번호]\t[교육생이름]\t[출석]\t[지각]\t[조퇴]\t[외출]\t[병가]\t[결석]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t\t%s\t%s\t%s\t%s\t%s\t%s\t\n"
									, dto.getStudentSeq()
									, dto.getStudentName()
									, dto.getAttendanceCount()
									, dto.getTardyCount()
									, dto.getEarlyLeaveCount()
									, dto.getLeavingCount()
									, dto.getSickLeaveCount()
									, dto.getAbsentCount());
			}
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. [출결 관리 및 출결 조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			
			if (menuInput.equals("1")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
			} else if (menuInput.equals("2")) {
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
			} else {
				loop = false;
			}
			
		}
		
	}


	public static void pause() {
		System.out.println("계속하시려면 엔터를 눌러주세요.");
		scan.nextLine();
	}
	
	
}













