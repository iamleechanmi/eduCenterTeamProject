package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.ExamScoreManagementDAO;
import com.test.dao.StudentManagementDAO;
import com.test.dto.OpenCourseDTO;
import com.test.dto.StudentDTO;
import com.test.dto.VwCourseByStudentDTO;
import com.test.dto.VwFindGradeBySubjectDTO;
import com.test.dto.VwOpenCourseWithSubjectDTO;

/**
 * 시험관리 및 성적 조회에 대한 클래스 파일입니다.
 * @author 이청강
 */

public class ExamScoreManagement {

	private static Scanner scan;
	private static ExamScoreManagementDAO dao;
	private static StudentManagementDAO importedDao;
	
	static {
		scan = new Scanner(System.in);
		dao = new ExamScoreManagementDAO();
		importedDao = new StudentManagementDAO();
	}
	
	public static void menu () {
		
		boolean loop = true;
		
		while (loop) {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("\t\t시험관리 및 성적 조회\n");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 과목별 성적 조회하기");
			System.out.println("2. 교육생별 성적 조회하기");
			System.out.println("3. 메인화면으로 돌아가기");
			System.out.println("-----------------------------------------------------------");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			
			if (menuInput.equals("1")) {
				findOpenCourse();
				pause();
			} else if (menuInput.equals("2")) {
				findStudentBrief();
				pause();
			} else {
				ManagerMenu.managerMenu();
				loop = false;
			}
		}
	}
	
	/**
	 * 교육생별로 성적을 조회하기 전에, 조회할 교육생 번호를 입력받기 위해
	 * 교육생 명단을 출력하는 메서드입니다.
	 */
	private static void findStudentBrief() {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
			list = importedDao.findBriefStudent();
			StudentDTO dto = new StudentDTO();
			
			int length = list.size();
			
			System.out.println("교육생 명단입니다.\n");
			System.out.println("[교육생번호]\t[이름]\t[주민등록번호]\t[전화번호]\t[등록일]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t%s\t\t%-10s\t%s\n"
						, dto.getSeq()
						, dto.getName()
						, dto.getSsn()
						, dto.getTel()
						, dto.getRegistrationDate()	
						);
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 교육생 번호 입력");
			System.out.println("4. [시험관리 및 성적조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();

			switch (menuInput) {
			case "1":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				break;
			case "2":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				break;
			case "3":
				System.out.println("성적을 조회하고자하는 교육생의 번호를 입력하세요.");
				System.out.print("교육생 번호 입력: ");
				String seq = scan.nextLine();
				findCourseInStudent(seq);
			default:
				loop = false;
				break;
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 특정 교육생이 수강하고있거나 과거에 한 적이 있는 개설 과정을 출력하는 메서드입니다.
	 * @param seq 조회할 교육생의 번호
	 */
	private static void findCourseInStudent(String seq) {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
	
			ArrayList<VwCourseByStudentDTO> list = new ArrayList<VwCourseByStudentDTO>();
			list = dao.findCourseByStudent(seq);

			System.out.println("해당 교육생이 수강하거나 한 적이 있는 교육 과정입니다.");
			System.out.println("[과정번호]\t[시작일자]\t[종료일자]\t[과정명]");
			
			for(VwCourseByStudentDTO dto : list) {
				System.out.printf("%s\t\t%s\t%s\t%s\n"
									, dto.getOpenCourseSeq()
									, dto.getOpenCourseStartDate()
									, dto.getOpenCourseEndDate()
									, dto.getCourseName()
									);
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("성적을 조회하고자하는 과정 번호를 입력하세요.");
			System.out.print("과정 번호 입력: ");
			String courseSeq = scan.nextLine();
			findGradeByStudent(seq, courseSeq);
			
	}
	
	/**
	 * 특정 교육생이 특정 과정에서 받은 성적을 출력해주는 메서드입니다.
	 * @param seq 조회할 교육생 번호
	 * @param courseSeq 조회할 개설 과정 번호
	 */
	private static void findGradeByStudent(String seq, String courseSeq) {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t교육생별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<VwFindGradeBySubjectDTO> list = new ArrayList<VwFindGradeBySubjectDTO>();
			list = dao.findGradeByStudent(seq, courseSeq);
			VwFindGradeBySubjectDTO dto = new VwFindGradeBySubjectDTO();
			
			int length = list.size();
			System.out.println("해당 교육생의 성적 정보입니다.");
			System.out.println("[출결점수]\t[필기점수]\t[실기점수]\t[과목명]");
			
			for(int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
									, nullConverter(dto.getAttendanceScore())
									, nullConverter(dto.getWrittenScore())
									, nullConverter(dto.getPracticalScore())
									, dto.getSubjectName());
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. [시험관리 및 성적조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();

			switch (menuInput) {
			case "1":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				break;
			case "2":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				break;
			default:
				loop = false;
				break;
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 과목별 성적 조회를 위해 개설 과정 번호를 먼저 입력받고자
	 * 교육센터 내의 진행되고있거나 종료된 개설 과정 목록을 출력해주는 메서드입니다.
	 */
	private static void findOpenCourse() {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t과목별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<OpenCourseDTO> list = new ArrayList<OpenCourseDTO>();
			list = dao.findAllOpenCourse();
			OpenCourseDTO dto = new OpenCourseDTO();
			
			int length = list.size();
			
			System.out.println("교육센터 내의 개설과정 목록입니다.");
			System.out.println("[개설과정번호]\t[강의실명]\t[시작일자]\t[과정명]");
			
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				
				dto = list.get(i);
				
				System.out.printf("%d\t\t%-5s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getClassroomName()
									, dto.getStartDate()
									, dto.getName());	
				
			}
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 과정 선택");
			System.out.println("4. [시험관리 및 성적조회] 메뉴로 돌아가기");
			System.out.print("메뉴 입력: ");
			String menuInput = scan.nextLine();
			
			switch (menuInput) {
			case "1":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, true);
				break;
			case "2":
				System.out.println();
				pageIndex = StudentManagement.pageForwardSwitch(pageIndex, length, pageSize, false);
				break;
			case "3":
				System.out.println("과목별 성적을 조회하고자하는 개설 과정 번호를 입력하세요.");
				System.out.print("개설 과정 번호 입력: ");
				String courseSeq = scan.nextLine();
				findSubjectInCourse(courseSeq);
			default:
				loop = false;
				break;
			}
			System.out.println();
		
		
		}
		
		
	}
	
	/**
	 * 특정 개설 과정에 포함된 개설 과목을 출력해주는 메서드입니다.
	 * @param courseSeq 조회할 개설 과정의 번호
	 */
	private static void findSubjectInCourse(String courseSeq) {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t과목별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		ArrayList<VwOpenCourseWithSubjectDTO> list = new ArrayList<VwOpenCourseWithSubjectDTO>();
		list = dao.findOpenSubjectInCourse(courseSeq);
		
		System.out.println("해당 과정의 개설 과목입니다.");
		System.out.println("[과목번호]\t[과목시작날짜]\t[과목종료날짜]\t[교사명]\t[성적등록여부]\t[시험등록여부]\t[과목명]");
		for (VwOpenCourseWithSubjectDTO dto : list) {
			System.out.printf("%s\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\n"
								, dto.getOpenSubjectSeq()
								, dto.getOpenSubjectStartDate()
								, dto.getOpenSubjectEndDate()
								, dto.getTeacherName()
								, dto.getWhetherGrade()
								, dto.getWhetherExam()
								, dto.getSubjectName());
		}

		System.out.println("과목별 성적을 조회하고자하는 개설 과목 번호를 입력하세요.");
		System.out.print("개설 과목 번호 입력: ");
		String seq = scan.nextLine();
		System.out.println();
		
		findGradeBySubject(seq);
		
	}
	
	/**
	 * 특정 개설 과목의 성적 정보를 출력해주는 메서드입니다.
	 * @param seq 조회하고자하는 개설 과목의 번호
	 */
	private static void findGradeBySubject(String seq) {
		System.out.println("-----------------------------------------------------------");
		System.out.printf("\t\t\t과목별 성적 조회\n");
		System.out.println("-----------------------------------------------------------");
		
		boolean loop = true;
		int pageIndex = 0;
		int pageSize = 20;
		
		while (loop) {
			
			ArrayList<VwFindGradeBySubjectDTO> list = new ArrayList<VwFindGradeBySubjectDTO>();
			list = dao.findGradeBySubject(seq);
			VwFindGradeBySubjectDTO dto = new VwFindGradeBySubjectDTO();
			
			int length = list.size();
			
			System.out.println("해당 과목의 성적 정보입니다.");
			System.out.println("[교육생번호]\t[교육생이름]\t[출결점수]\t[필기점수]\t[실기점수]");
			for (int i=pageIndex; i<pageSize + pageIndex && i<length; i++) {
				
				dto = list.get(i);
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
									, dto.getStudentSeq()
									, dto.getStudentName()
									, nullConverter(dto.getAttendanceScore())
									, nullConverter(dto.getWrittenScore())
									, nullConverter(dto.getPracticalScore())
									);
		
			}
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. [시험 관리 및 성적 조회] 메뉴로 돌아가기");
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
	 * 입력받은 매개변수의 문자열이 null일 경우, 미등록으로 바꿔주는 메서드입니다.
	 * 입력받지 않은 성적이 null일 때 사용합니다.
	 * @param input 입력받은 문자열.
	 * @return 입력받은 문자열이 null이면 "미등록"을, 아니면 입력받은 문자열을 그대로 반환합니다.
	 */
	private static String nullConverter(String input) {
		
		if (input == null) {
			return "미등록";
		} else {
			return input;
		}
	}

	public static void pause() {
		System.out.println("계속하시려면 엔터를 눌러주세요.");
		scan.nextLine();
	}
	
	
	
}













