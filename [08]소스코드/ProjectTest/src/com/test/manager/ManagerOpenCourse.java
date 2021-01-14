package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.OpenCourseDAO;
import com.test.dto.ClassroomDTO;
import com.test.dto.CourseDTO;
import com.test.dto.OpenCourseDTO;
import com.test.dto.OpenCourseStudentDTO;
import com.test.dto.OpenSubjectDTO;

/**
 * ManagerOpenCourse. 개설 과정 관리를 위한 클래스입니다.
 * @author 이찬미
 *
 */
public class ManagerOpenCourse {
	
	private static Scanner scan;
	private static OpenCourseDAO dao;
	private static int page = 1; //개설 과정 조회 페이지
	private static int studentPage = 1; //교육생 조회 페이지
	private static int studentTotalPage; //교육생 조회 총 페이지
	
	public static String openCourseSeq;

	/**
	 * 클래스 초기화 블럭입니다.
	 */
	static {
		scan = new Scanner(System.in);
		dao = new OpenCourseDAO();
	}
	
	
	/**
	 * 개설 과정 목록과 관리 메뉴를 호출할 기본 생성자입니다.
	 */
	public ManagerOpenCourse() {
		
		menu();
	}
	
	
	/**
	 * 전체 개설 과정 목록과 관리 메뉴를 출력하는 메소드입니다.
	 */
	private void menu() {
		
		line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과정 관리");
		line();
		System.out.println("[번호]\t[개설과정명]\t\t\t\t\t\t\t[시작일]  [종료일]  [강의실명] [과목등록여부]\t[등록인원(명)]\t[상태]");
		
		ArrayList<OpenCourseDTO> list = dao.findOpenCourse(this.page);
		
		//개설 과정 출력
		for (OpenCourseDTO dto : list) {
			
			System.out.printf("%3d\t%s\t%s  %s   %s\t     %s\t\t     %02d\t\t %s\n"
					,dto.getSeq()
					,dto.getName()
					,dto.getStartDate()
					,dto.getEndDate()
					,dto.getClassroomName()
					,dto.getWhetherRegister()
					,dto.getPersonnel()
					,dto.getStatus());
		} //for
		System.out.printf("\t\t\t\t\t\t\t\t    - %sp -\n", this.page);
		line();
		System.out.println("\t\tb. 뒤로 가기\t\t\t\t\tp. 이전 페이지\t\t\t\t\tn. 다음 페이지");
		line();
		System.out.println("\t\t\t\t\t\t\t\t1. 개설 과정 조회");
		System.out.println("\t\t\t\t\t\t\t\t2. 개설 과정 등록");
		System.out.println("\t\t\t\t\t\t\t\t3. 개설 과정 수정");
		System.out.println("\t\t\t\t\t\t\t\t4. 개설 과정 삭제");
		System.out.println("\t\t\t\t\t\t\t       5. 교육생 정보 조회");
		line();
		
		System.out.print("입력 : ");
		String num = scan.nextLine();

		switch (num.toLowerCase()) {
		
			case "b" :
				ManagerMenu.managerMenu();
				break;
				
			case "p" :
				
				this.page--;

				int previousResult = findPage(this.page);
				
				if (previousResult == 1) {
					
					System.out.println("\n* 현재 첫 페이지입니다.");
					pause();
					this.page++;
					menu();
					break;
				}

				menu();
				break;
				
			case "n" :

				int nextResult = findPage(this.page);
				
				if (nextResult == -1) {
					
					System.out.println("\n* 현재 마지막 페이지입니다.");
					pause();
					menu();
					break;
				}
				
				this.page++;
				menu();
				break;
			
			case "1" :
				//개설 과정 조회
				line();
				System.out.println("\t\t\t\t\t\t\t\t개설 과정 조회");
				line();
				System.out.println("* 조회하려는 개설 과정 번호를 입력해주세요.");
				
				System.out.print("\n번호 : ");
				String openCourseSeq = scan.nextLine();
				
				this.openCourseSeq = openCourseSeq;
				
				find(this.openCourseSeq); 
				break;
				
			case "2" :
				//개설 과정 등록
				add(); 
				break;
			
			case "3" :
				//개설 과정 수정
				line();
				System.out.println("\t\t\t\t\t\t\t\t개설 과정 수정");
				line();
				System.out.println("* 수정하려는 개설 과정 번호를 입력해주세요.");
				
				System.out.print("\n번호 : ");
				String courseSeq = scan.nextLine();
				
				//수정 전 개설 과정 정보 출력
				OpenCourseDTO dto = dao.findOneLineOpenCourse(courseSeq);
				
				line();
				System.out.println("[번호]\t[개설과정명]\t\t\t\t\t\t\t[시작일]  [종료일]  [강의실명] [과목등록여부]\t[등록인원(명)]\t[상태]");

				System.out.printf("%3d\t%s\t%s  %s   %s\t     %s\t\t     %02d\t\t %s\n"
									,dto.getSeq()
									,dto.getName()
									,dto.getStartDate()
									,dto.getEndDate()
									,dto.getClassroomName()
									,dto.getWhetherRegister()
									,dto.getPersonnel()
									,dto.getStatus());
				line();
				System.out.println("\t\t\t\t\t\t\t\t1. 강의실 수정");
				System.out.println("\t\t\t\t\t\t\t\t2. 수료일 수정");
				line();
				
				System.out.print("입력 : ");
				String replaceNum = scan.nextLine();
				
				//강의실 수정을 선택했을 경우
				if (replaceNum.equals("1")) {
					
					replaceClassroom(courseSeq);
					break;
				
				} else if (replaceNum.equals("2")) { //수료일 수정을 선택했을 경우
					
					replaceEndDate(courseSeq);
					break;
					
				} else {
					
					System.out.println("\n* 잘못된 입력입니다.");
					pause();
					menu();
					break;
				}
				
			case "4" :
				//개설 과정 삭제
				delete(); 
				break;
				
			case "5" :
				//교육생 정보 조회
				line();
				System.out.println("\t\t\t\t\t\t\t\t교육생 정보 조회");
				line();
				System.out.println("* 조회하려는 개설 과정 번호를 입력해주세요.");

				System.out.print("\n번호 : ");
				this.openCourseSeq = scan.nextLine();
			
				boolean flag = true;
				
				while (flag) {
					
					findStudent(this.openCourseSeq);
			
					line();
					System.out.println("\t\tb. 뒤로 가기\t\t\t\t\tp. 이전 페이지\t\t\t\t\tn. 다음 페이지");
					line();

					System.out.print("입력 : ");
					String input = scan.nextLine().toLowerCase();
					
					if (input.equals("b")) {
						
						System.out.println("\n* 개설 과정 관리 화면으로 돌아갑니다.");
						pause();
						menu();
						flag = false;
						break;
					
					} else if (input.equals("p")) {

						this.studentPage--;

						if (this.studentPage == 0) {
							
							System.out.println("\n* 현재 첫 페이지입니다.");
							pause();
							this.studentPage++;
						}
					
					} else if (input.equals("n")) {
						
						if (this.studentPage == this.studentTotalPage) {
							
							System.out.println("\n* 현재 마지막 페이지입니다.");
							pause();
							this.studentPage--;
						}
						
						this.studentPage++;
						
					} else {
						
						System.out.println("\n* 개설 과정 관리 화면으로 돌아갑니다.");
						pause();
						menu();
						flag = false;
						return;
					}
					
				}

				break;
				
			default :
				System.out.println("\n* 잘못된 입력입니다.");
				pause();
				menu();
				break;
		}
		
	} //menu

	/**
	 * 첫 페이지, 마지막 페이지인지 확인하는 메소드입니다.
	 * @param page 페이지 수
	 * @return 첫 페이지일 경우 1, 마지막 페이지일 경우 -1을 반환합니다.
	 */
	private int findPage(int page) {
		
		//첫 페이지인지 마지막 페이지인지 확인하여 결과값을 반환한다
		int result = dao.findPage(this.page);
		
		return result;
	}


	/**
	 * 특정한 개설 과정 하나를 조회하는 메소드입니다.
	 */
	static void find(String openCourseSeq) {

		ArrayList<OpenSubjectDTO> list = dao.findOneOpenCourse(openCourseSeq);
		
		line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과목 정보");
		line();
		
		System.out.println("[번호]\t[개설과목명]\t\t\t\t\t\t[시작일]  [종료일]\t[교사명]\t[교재명]");
		
		for (OpenSubjectDTO dto : list) {
			
			System.out.printf("%4d\t%15s\t\t%s  %s\t %s\t\t%s\n"
								,dto.getSubjectSeq()
								,dto.getSubjectName()
								,dto.getSubjectStartDate()
								,dto.getSubjectEndDate()
								,dto.getTeacherName()
								,dto.getTextbookName());
		}
		
		line();
		System.out.println("\t\t\t\t\t\t\t\tb. 뒤로 가기");

		//개설 과목 관리
		ManagerOpenSubject oj = new ManagerOpenSubject();
		
	} //find

	
	/**
	 * 새로운 개설 과정을 등록하는 메소드입니다.
	 */
	private void add() {

		line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과정 등록");
		line();
		System.out.println("* 등록하려는 개설 과정의 시작일을 입력해주세요.");
		System.out.println("* 뒤로 가려면 엔터 키를 누르세요.");

		System.out.print("\n연(yyyy) : ");
		String year = scan.nextLine();

		//뒤로 가기
		if (year.equals("")) {

			menu();
			return;
		}
		
		String zero = "0";
		
		System.out.print("\n월(mm)   : ");
		String month = scan.nextLine();
		
		if (month.equals("")) {

			menu();
			return;

		} else if (month.length() == 1) {

			month = zero + month; 
			
		} else if (Integer.parseInt(month) < 1 && Integer.parseInt(month) > 12) {

			System.out.println("\n* 1 ~ 12 사이의 숫자를 입력해주세요.");
			pause();
			add();
			return;

		} else {
			
			month = month;
		}
		
		System.out.print("\n일(dd)   : ");
		String day = scan.nextLine();

		if (day.equals("")) {

			menu();
			return;

		} else if (day.length() == 1) {

			day = zero + day; 
			
		} else if (Integer.parseInt(day) < 1 && Integer.parseInt(day) > 31) {

			System.out.println("\n* 1 ~ 31 사이의 숫자를 입력해주세요.");
			pause();
			add();
			return;

		} else {
			
			day = day;
		}
		
		String courseStartDate = year + month + day;

		//등록 가능한 과정 목록을 저장한다
		ArrayList<CourseDTO> courseList = dao.availableCourse(courseStartDate);

		//등록 가능한 과정이 없을 경우
		if (courseList.isEmpty()) {
			
			System.out.println("\n* 해당 시작일에 등록 가능한 과정이 없습니다.");
			System.out.println("* 개설 과정 시작일을 확인 후 다시 등록해주세요.");
			pause();
			menu();
			return;
		}
		
		line();
		System.out.println("\t\t\t\t\t\t\t 등록 가능한 과정");
		line();
		
		//사용자가 과정 번호를 맞게 입력했는지 알아보기 위해 등록 가능한 과정의 과정 번호를 저장한다
		ArrayList<String> courseSeqList = new ArrayList<String>();
		
		for (CourseDTO dto : courseList) {

			courseSeqList.add(dto.getSeq());
			
			System.out.printf("▶과정 번호 : %s\n", dto.getSeq());
			System.out.printf("과정명 : %s\n", dto.getName());
			System.out.printf("과정 목표 : %s\n", dto.getGoal());
			System.out.printf("과정 기간(일) : %s\n", dto.getPeriod());
			System.out.println();
		}

		line();
		System.out.println("* 위의 과정을 참고하여 과정 번호를 입력해주세요.");
		System.out.println();
	
		System.out.print("번호 : ");
		String courseSeq = scan.nextLine();
		
		//등록 가능한 과정 번호를 입력했는지 알아 보기 위한 누적 변수
		int courseResult = 0;
		
		//등록 가능한 과정 번호를 입력했다면 1을 넣는다
		for (String seq : courseSeqList) {
			
			if (seq.equals(courseSeq)) {
				
				courseResult += 1;
			}
		}
		
		//등록 가능한 과정 번호를 입력했을 경우
		if (courseResult == 1) {
			
			ArrayList<ClassroomDTO> classroomList = dao.availableClassroom(courseSeq, courseStartDate);
			
			//사용 가능한 강의실이 없을 경우
			if (classroomList.isEmpty()) {
				
				System.out.println("\n* 사용 가능한 강의실이 없습니다.");
				System.out.println("* 개설 과정 시작일과 과정을 확인 후 다시 등록해주세요.");
				pause();
				menu();
				return;
			}

			line();
			System.out.println("\t\t\t\t\t\t\t사용 가능한 강의실");
			line();
			System.out.println("[번호]\t\t\t\t\t\t\t\t[강의실명]\t\t\t\t\t\t\t[정원(명)]");
			
			//사용 가능한 강의실 번호를 저장한다
			ArrayList<String> classroomSeqList = new ArrayList<String>();
			
			for (ClassroomDTO dto : classroomList) {

				classroomSeqList.add(dto.getSeq());
				
				System.out.printf(" %2s\t\t\t\t\t\t\t\t%5s\t\t\t\t\t\t\t  %3s\n", dto.getSeq(), dto.getName(), dto.getPersonnel());
			}
			
			line();
			System.out.println("* 위의 강의실을 참고하여 강의실 번호를 입력해주세요.");
			System.out.println();

			System.out.print("번호 : ");
			String classroomSeq = scan.nextLine();

			//사용 가능한 강의실 번호를 입력했는지 알아 보기 위한 누적 변수
			int classroomResult = 0;
			
			//사용 가능한 강의실 번호를 입력했다면 1을 넣는다
			for (String seq : classroomSeqList) {
				
				if (seq.equals(classroomSeq)) {
			
					classroomResult += 1;
				}
			}

			//사용 가능한 강의실 번호를 입력했을 경우
			if (classroomResult == 1) {
				
				//개설 과정 등록을 한다
				int result = dao.addOpenCourse(courseStartDate, courseSeq, classroomSeq);
				
				//성공 시 1을 반환 받는다
				if (result > 0) {
					
					System.out.println("\n* 개설 과정이 등록되었습니다.");
					pause();
					menu();
					return;
					
				} else {
					
					System.out.println("\n* 개설 과정 등록에 실패했습니다.");
					pause();
					menu();
					return;
				}
				
			} else if (classroomResult == 0) { //사용 가능한 강의실 번호를 입력하지 않았을 경우
				
				System.out.println("\n* 목록에 있는 강의실 번호가 아닙니다.");
				System.out.println("* 강의실 번호를 확인 후 다시 등록해주세요.");
				pause();
				menu();
				return;
			}

		} else if (courseResult == 0) { //등록 가능한 과정 번호를 입력하지 않았을 경우
			
			System.out.println("\n* 목록에 있는 과정 번호가 아닙니다.");
			System.out.println("* 과정 번호를 확인 후 다시 등록해주세요.");
			pause();
			menu();
			return;
		}
				
	} //add


	/**
	 * 선택한 개설 과정의 강의실을 수정하는 메소드입니다.
	 * @param courseSeq 개설 과정 번호
	 */
	private void replaceClassroom(String courseSeq) {
		
		ArrayList<ClassroomDTO> classroomList = dao.availableReplaceClassroom(courseSeq);

		//수정 가능한 강의실이 없을 경우
		if (classroomList.isEmpty()) {
			
			System.out.println("\n* 해당 개설 과정 기간 내에 수정 가능한 강의실이 없습니다.");
			System.out.println("* 개설 과정 관리 메인 화면으로 돌아갑니다.");
			pause();
			menu();
			return;
		}

		line();
		System.out.println("\t\t\t\t\t\t\t     수정 가능한 강의실");
		line();
		System.out.println("[번호]\t\t\t\t\t\t\t\t[강의실명]\t\t\t\t\t\t\t[정원(명)]");
		
		//수정 가능한 강의실의 강의실 번호를 저장한다
		ArrayList<String> classroomSeqList = new ArrayList<String>();

		for (ClassroomDTO dto : classroomList) {
			
			classroomSeqList.add(dto.getSeq());
			
			System.out.printf(" %2s\t\t\t\t\t\t\t\t%5s\t\t\t\t\t\t\t  %3s\n", dto.getSeq(), dto.getName(), dto.getPersonnel());
		}
		
		line();
		System.out.println("* 위의 강의실을 참고하여 수정할 강의실 번호를 입력해주세요.");
		System.out.println();

		System.out.print("번호 : ");
		String classroomSeq = scan.nextLine();

		//사용 가능한 강의실 번호를 입력했는지 알아 보기 위한 누적 변수
		int classroomResult = 0;
		
		//사용 가능한 강의실 번호를 입력했다면 1을 넣는다
		for (String seq : classroomSeqList) {
			
			if (seq.equals(classroomSeq)) {
		
				classroomResult += 1;
			}
		}
		
		//수정 가능한 강의실 번호를 입력했을 경우
		if (classroomResult == 1) {
			
			//강의실 수정의 성공 유무를 int로 받아 온다
			int result = dao.replaceClassroom(courseSeq, classroomSeq);
			
			//강의실 수정 성공 시
			if (result > 0) {
				
				System.out.println("\n* 강의실 수정이 완료되었습니다.");
				pause();
				menu();
				return;
				
			} else { //강의실 수정 실패 시
				
				System.out.println("\n* 강의실 수정에 실패했습니다.");
				pause();
				menu();
				return;
			}
			
		} else if (classroomResult == 0) { //수정 가능한 강의실 번호를 입력하지 않았을 경우
		
			System.out.println("\n* 목록에 있는 강의실 번호가 아닙니다.");
			pause();
			replaceClassroom(courseSeq);
			return;
		}
	}

	/**
	 * 선택한 개설 과정의 수료일을 수정하는 메소드입니다.
	 * @param courseSeq 개설 과정 번호
	 */
	private void replaceEndDate(String courseSeq) {
		
		line();
		System.out.println("\t\t\t\t\t\t\t\t  수료일 수정");
		line();
		
		boolean flag = true;
		
		this.studentPage = 1;
		
		while (flag) {

			findStudent(courseSeq);

			line();
			System.out.println("\t\tb. 뒤로 가기\t\t\tp. 이전 페이지\t\t\tn. 다음 페이지\t\t\t0. 수료일 수정");
			line();

			System.out.print("입력 : ");
			String input = scan.nextLine().toLowerCase();
			
			if (input.equals("b")) {
				
				System.out.println("\n* 개설 과정 관리 화면으로 돌아갑니다.");
				pause();
				menu();
				flag = false;
				return;
			
			} else if (input.equals("p")) {

				this.studentPage--;

				if (this.studentPage == 0) {
					
					System.out.println("\n* 현재 첫 페이지입니다.");
					pause();
					this.studentPage++;
				}
			
			} else if (input.equals("n")) {
				
				if (this.studentPage == this.studentTotalPage) {
					
					System.out.println("\n* 현재 마지막 페이지입니다.");
					pause();
					this.studentPage--;
				}
				
				this.studentPage++;
				
			} else if (input.equals("0")) {
				
				flag = false;
			}
			
		}

		System.out.println("\n* 중도 탈락자를 제외한 교육생의 수료일이 수정됩니다.");
		System.out.println("* 수정할 수료일을 입력해주세요.");

		System.out.print("\n연(yyyy) : ");
		String year = scan.nextLine();

		//뒤로 가기
		if (year.toLowerCase().equals("b")) {

			menu();
			return;
		}
		
		String zero = "0";
		
		System.out.print("\n월(mm)   : ");
		String month = scan.nextLine();
		
		//뒤로 가기
		if (month.toLowerCase().equals("b")) {

			menu();
			return;

		} else if (month.length() == 1) {

			month = zero + month; 
			
		} else if (Integer.parseInt(month) < 1 && Integer.parseInt(month) > 12) {

			System.out.println("\n* 1 ~ 12 사이의 숫자를 입력해주세요.");
			pause();
			add();
			return;

		} else {
			
			month = month;
		}
		
		System.out.print("\n일(dd)   : ");
		String day = scan.nextLine();

		if (day.toLowerCase().equals("b")) {

			menu();
			return;

		} else if (day.length() == 1) {

			day = zero + day; 
			
		} else if (Integer.parseInt(day) < 1 && Integer.parseInt(day) > 31) {

			System.out.println("\n* 1 ~ 31 사이의 숫자를 입력해주세요.");
			pause();
			add();
			return;

		} else {
			
			day = day;
		}
		
		String endDate = year + month + day;

		//수료일 수정이 가능한지 가능하면 1, 불가능하면 0을 받는다
		int availableResult = dao.availableReplaceEndDate(courseSeq, endDate);
		
		//수정 가능할 경우
		if (availableResult == 1) {
			
			int result = dao.replaceCompletionDate(courseSeq, endDate);
			
			//수료일 수정 성공 시
			if (result > 0) {
				
				System.out.println("\n* 수료일이 수정되었습니다.");
				pause();
				menu();
				return;

			} else {
				
				System.out.println("\n* 수료일 수정에 실패했습니다.");
				pause();
				menu();
				return;
			}
			
		} else {
			
			System.out.println("\n* 수료일은 개설 과정 종료일 이후여야 합니다.");
			pause();
			menu();
		}
	}

	
	/**
	 * 개설 과정 번호를 입력 받아 삭제하는 메소드입니다.
	 */
	private void delete() {

		line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과정 삭제");
		line();
		System.out.println("* 삭제하려는 개설 과정 번호를 입력해주세요.");

		System.out.print("\n번호 : ");
		String openCourseSeq = scan.nextLine();
		
		//삭제 전 개설 과정 정보 출력
		OpenCourseDTO dto = dao.findOneLineOpenCourse(openCourseSeq);
		
		line();
		System.out.println("[번호]\t[개설과정명]\t\t\t\t\t\t\t[시작일]  [종료일]  [강의실명] [과목등록여부]\t[등록인원(명)]\t[상태]");
		
		System.out.printf("%3d\t%s\t%s  %s   %s\t     %s\t\t     %02d\t\t %s\n"
							,dto.getSeq()
							,dto.getName()
							,dto.getStartDate()
							,dto.getEndDate()
							,dto.getClassroomName()
							,dto.getWhetherRegister()
							,dto.getPersonnel()
							,dto.getStatus());
		line();

		System.out.print("* 정말로 삭제하시겠습니까?(y/n) : ");
		String answer = scan.nextLine();
		
		//삭제 진행
		if (answer.toLowerCase().equals("y")) {
			
			int result = dao.deleteOpenCourse(openCourseSeq);
			
			//삭제가 되었을 경우
			if (result > 0) {
		
				System.out.println();
				System.out.println("* 개설 과정이 삭제되었습니다.");
			
			} else {
				
				System.out.println("\n* 개설 과정 삭제에 실패했습니다.");
			}
			
		} else if (answer.toLowerCase().equals("n")) { //삭제 취소
			
			System.out.println();
			System.out.println("* 삭제가 취소되었습니다.");
		
		} else {
			
			System.out.println();
			System.out.println("* 잘못된 입력입니다.");
		}
		
		pause();
		menu();
	} //delete
		
	
	/**
	 * 선택한 개설 과정의 교육생 정보를 조회하는 메소드입니다.
	 * @param openCourseSeq 개설 과정 번호
	 */
	private void findStudent(String openCourseSeq) {
		
		//교육생 정보를 가져온다
		ArrayList<OpenCourseStudentDTO> studentList = dao.findStudent(openCourseSeq);
		
		//선택한 개설 과정의 교육생이 없을 경우
		if (studentList.isEmpty()) {
			
			System.out.println("\n* 해당 개설 과정의 교육생이 없습니다.");
			pause();
			menu();
			return;
		}
		
		//선택한 개설 과정명과 기간 출력
		OpenCourseDTO course = dao.findOneLineOpenCourse(openCourseSeq);
		
		line();
		System.out.printf("\t\t\t\t\t\t     %s\n\t\t\t\t\t\t\t       [%s ~ %s]\n"
							,course.getName()
							,course.getStartDate()
							,course.getEndDate());
		
		line();
		
		System.out.println("[번호]\t\t[이름]\t\t   [주민번호 뒷자리]\t\t [전화번호]\t\t       [수료상태]\t\t     [수료 및 중단일]");
		
		ArrayList<String> list = new ArrayList<String>();
		
		//가져온 교육생 정보를 출력
		for (OpenCourseStudentDTO dto : studentList) {
			
			list.add(String.format(" %3s\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getSsn()
								, dto.getTel()
								, dto.getStatus()
								, dto.getEndDate()));
		}
		
		//총 페이지 수 계산
		this.studentTotalPage = (int)(Math.ceil(list.size() / (double) 10));
		
		for (int i = (this.studentPage-1) * 10; i < (this.studentPage*10); i++) {
			
			if (i >= list.size()) {
				
				break;
			}
			
			System.out.print(list.get(i));
		}

		System.out.printf("\t\t\t\t\t\t\t\t    - %dp -\n", this.studentPage);

		

				
		
	}

	
	/**
	 * 라인을 출력하는 메소드입니다.
	 */
	public static void line() {

		String line = "";
		
		for (int i=0; i<150; i++) {
		
			line += "-";
		}
		
		System.out.println(line);
	}
	
	
	/**
	 * 일시 정지하는 메소드입니다.
	 */
	public static void pause() {
		
		System.out.println("* 계속하시려면 엔터 키를 누르세요.");
		scan.nextLine();
	}
}
