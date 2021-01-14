package com.test.manager;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.OpenSubjectDAO;
import com.test.dto.AddOpenSubjectDTO;
import com.test.dto.OpenSubjectDTO;
import com.test.dto.SubjectDTO;
import com.test.dto.TeacherDTO;
import com.test.dto.TextbookDTO;

/**
 * ManagerOpenSubject. 개설 과목 관리를 위한 클래스입니다.
 * @author 이찬미
 *
 */
public class ManagerOpenSubject {

	private static Scanner scan;
	private static OpenSubjectDAO dao;
	
	/**
	 * 클래스 초기화 블럭입니다.
	 */
	static {
		scan = new Scanner(System.in);
		dao = new OpenSubjectDAO();
	}

	
	/**
	 * 개설 과목 관리 메뉴 메소드 호출할 기본 생성자입니다.
	 */
	public ManagerOpenSubject() {
		
		menu();
	}
	
	
	/**
	 * 개설 과목 관리 메뉴를 출력하는 메소드입니다.
	 */
	private void menu() {
	
		ManagerOpenCourse.line();
		System.out.println("\t\t\t\t\t\t\t      1. 개설 과목 조회");
		System.out.println("\t\t\t\t\t\t\t      2. 개설 과목 등록");
		System.out.println("\t\t\t\t\t\t\t      3. 개설 과목 수정");
		System.out.println("\t\t\t\t\t\t\t      4. 개설 과목 삭제");
		ManagerOpenCourse.line();
		
		System.out.print("입력 : ");
		String num = scan.nextLine();
		
		switch (num.toLowerCase()) {
		
		case "b" :
			System.out.println("\n* 개설 과정 관리 화면으로 돌아갑니다.");
			ManagerOpenCourse.pause();
			ManagerOpenCourse oc = new ManagerOpenCourse();
			break;
			
		case "1" :
			ManagerOpenCourse.line();
			System.out.println("\t\t\t\t\t\t\t\t개설 과목 조회");
			ManagerOpenCourse.line();
			System.out.println("* 조회하려는 개설 과목 번호를 입력해주세요.");
			
			System.out.print("\n번호 : ");
			String openSubjectSeq = scan.nextLine();

			find(openSubjectSeq); 
			
			main();
			break;
			
		case "2" :
			//개설 과목 등록
			add(); 
			break;
		
		case "3" :
			//개설 과목 수정
			ManagerOpenCourse.line();
			System.out.println("\t\t\t\t\t\t\t\t개설 과목 수정");
			ManagerOpenCourse.line();
			System.out.println("* 수정하려는 개설 과목 번호를 입력해주세요.");
			
			System.out.print("\n번호 : ");
			String replaceSubjectSeq = scan.nextLine();

			find(replaceSubjectSeq);
			
			System.out.println("\t\t\t\t\t\t\t\t1. 교사 수정");
			System.out.println("\t\t\t\t\t\t\t\t2. 교재 수정");
			ManagerOpenCourse.line();
			
			System.out.print("입력 : ");
			String replaceNum = scan.nextLine();
			
			//교사 수정을 선택했을 경우
			if (replaceNum.equals("1")) {
				
				replaceTeacher(replaceSubjectSeq);
				break;
			
			} else if (replaceNum.equals("2")) { //교재 수정을 선택했을 경우
				
				replaceTextbook(replaceSubjectSeq);
				break;
				
			} else {
				
				System.out.println("\n* 잘못된 입력입니다.");
				main();
				break;
			}
			
		case "4" :
			//개설 과목 삭제
			delete(); 
			break;
			
		default :
			System.out.println("\n* 잘못된 입력입니다.");
			main();
			break;
		}
		
	}


	/**
	 * 개설 과목 조회하는 메소드입니다.
	 * @param openSubjectSeq 개설 과목 번호
	 */
	private void find(String openSubjectSeq) {
		
		OpenSubjectDTO dto = dao.findOpenSubject(openSubjectSeq);
		
		ManagerOpenCourse.line();
		System.out.printf("\t\t\t\t  %s  [%s ~ %s]  %s\n"
							,dto.getCourseName()
							,dto.getCourseStartDate()
							,dto.getCourseEndDate()
							,dto.getClassroomName());
		
		ManagerOpenCourse.line();
		System.out.println("[개설과목명]\t\t\t\t[시작일]  [종료일]  [문제등록여부]  [성적등록여부]\t[교사명]    [교재명]");
		System.out.printf("%15s\t\t%s  %s\t %s\t\t %s\t\t %3s\t    %s\n"
							,dto.getSubjectName()
							,dto.getSubjectStartDate()
							,dto.getSubjectEndDate()
							,dto.getWhetherExam()
							,dto.getWhetherGrade()
							,dto.getTeacherName()
							,dto.getTextbookName());
		ManagerOpenCourse.line();
	}

	
	/**
	 * 새로운 개설 과목을 등록하는 메소드입니다.
	 */
	private void add() {
		
		ManagerOpenCourse.line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과목 등록");
		ManagerOpenCourse.line();

		//개설 과정 내에 새 개설 과목 등록이 가능한지 확인
		int difference = dao.availableOpenSubject(ManagerOpenCourse.openCourseSeq);

		//개설 과목 등록이 가능할 경우
		if (difference > 0) {
			
			System.out.println("* 등록하려는 개설 과목의 시작일을 입력해주세요.");
			System.out.println("* 뒤로 가려면 엔터 키를 누르세요.");

			System.out.print("\n연(yyyy) : ");
			String year = scan.nextLine();

			//뒤로 가기
			if (year.equals("")) {

				main();
				return;
			}
			
			String zero = "0";
			
			System.out.print("\n월(mm)   : ");
			String month = scan.nextLine();
			
			if (month.equals("")) {

				main();
				return;

			} else if (month.length() == 1) {

				month = zero + month; 
				
			} else if (Integer.parseInt(month) < 1 && Integer.parseInt(month) > 12) {

				System.out.println("\n* 1 ~ 12 사이의 숫자를 입력해주세요.");
				ManagerOpenCourse.pause();
				add();
				return;

			} else {
				
				month = month;
			}
			
			System.out.print("\n일(dd)   : ");
			String day = scan.nextLine();

			if (day.equals("")) {

				main();
				return;

			} else if (day.length() == 1) {

				day = zero + day; 
				
			} else if (Integer.parseInt(day) < 1 && Integer.parseInt(day) > 31) {

				System.out.println("\n* 1 ~ 31 사이의 숫자를 입력해주세요.");
				ManagerOpenCourse.pause();
				add();
				return;

			} else {
				
				day = day;
			}
			
			String subjectStartDate = year + month + day;
						
			//개설 과목 시작일이 개설 과정 내에 있고 가장 최근 과목 종료일보다 큰지 확인
			int periodResult = dao.availablePeriod(ManagerOpenCourse.openCourseSeq, subjectStartDate);
			
			//개설 과목 시작일이 올바를 경우
			if (periodResult > 0) {
				
				//등록 가능한 과목 목록을 저장한다
				ArrayList<SubjectDTO> subjectList = dao.availableSubject(ManagerOpenCourse.openCourseSeq);
				
				//등록 가능한 과목이 없을 경우
				if (subjectList.isEmpty()) {
					
					System.out.println("\n* 해당 시작일에 등록 가능한 과목이 없습니다.");
					System.out.println("* 개설 과목 시작일을 다시 입력해주세요.");
					add();
					return;
				}
				
				ManagerOpenCourse.line();
				System.out.println("\t\t\t\t\t\t\t\t등록 가능한 과목");
				ManagerOpenCourse.line();
				System.out.println("[번호]   [과목명]\t\t\t\t[과목기간(일)]\t\t | [번호]   [과목명]\t\t\t\t        [과목기간(일)]");
				

				//사용자가 과목 번호를 맞게 입력했는지 알아보기 위해 등록 가능한 과목의 과목 번호를 저장한다
				ArrayList<String> subjectSeqList = new ArrayList<String>();
				
				int count = 0;
				
				for (SubjectDTO dto : subjectList) {
					
					subjectSeqList.add(dto.getSeq());
					
					count++;
					
					if (count % 2 == 0) {

						System.out.printf("%3s\t   %s\t\t%s\t\t", dto.getSeq(), dto.getName(), dto.getPeriod());
						System.out.println();
						
					} else {
						
						System.out.printf(" %3s\t%s\t%s\t\t | ", dto.getSeq(), dto.getName(), dto.getPeriod());								
					}
				}
				
				//출력 개수가 홀수일 경우 행을 한번 바꿔준다
				if (count % 2 == 1) {
					
					System.out.println();
				}
				
				ManagerOpenCourse.line();
				System.out.println("* 위의 과목을 참고하여 과목 번호를 입력해주세요.");
				System.out.println();
				
				System.out.print("번호 : ");
				String subjectSeq = scan.nextLine();

				//등록 가능한 과목 번호를 입력했는지 알아 보기 위한 누적 변수
				int subjectResult = 0;
				
				//등록 가능한 과목 번호를 입력했다면 1을 넣는다
				for (String seq : subjectSeqList) {
					
					if (seq.equals(subjectSeq)) {
				
						subjectResult += 1;
					}
				}
			
				//등록 가능한 과목 번호를 입력했을 경우
				if (subjectResult == 1) {
					
					//강의 가능한 교사 목록을 저장한다
					ArrayList<TeacherDTO> teacherList = dao.availableTeacher(subjectSeq, subjectStartDate);
					
					//강의 가능한 교사가 없을 경우
					if (teacherList.isEmpty()) {
						
						System.out.println("\n* 강의 가능한 교사가 없습니다.");
						main();
						return;
					}
					
					ManagerOpenCourse.line();
					System.out.println("\t\t\t\t\t\t\t\t강의 가능한 교사");
					ManagerOpenCourse.line();
					System.out.println("[번호]\t\t\t\t\t[이름]\t\t\t\t    [주민번호 뒷자리]\t\t\t\t[전화번호]");

					//강의 가능한 교사 번호를 저장한다
					ArrayList<String> teacherSeqList = new ArrayList<String>();
					
					for (TeacherDTO dto : teacherList) {

						teacherSeqList.add(dto.getSeq());
						
						System.out.printf(" %2s\t\t\t\t\t%3s\t\t\t\t\t%s\t\t\t\t\t%13s\n"
											, dto.getSeq()
											, dto.getName()
											, dto.getSsn()
											, dto.getTel());
					}

					ManagerOpenCourse.line();
					System.out.println("* 위의 교사를 참고하여 교사 번호를 입력해주세요.");
					System.out.println();

					System.out.print("번호 : ");
					String teacherSeq = scan.nextLine();

					//강의 가능한 교사 번호를 입력했는지 알아 보기 위한 누적 변수
					int teacherResult = 0;
					
					//강의 가능한 교사 번호를 입력했다면 1을 넣는다
					for (String seq : teacherSeqList) {
						
						if (seq.equals(teacherSeq)) {
					
							teacherResult += 1;
						}
					}
					
					//강의 가능한 교사 번호를 입력했을 경우
					if (teacherResult == 1) {
						
						//교재 목록을 출력한다
						ManagerOpenCourse.line();
						System.out.println("\t\t\t\t\t\t\t\t  교재 목록");
						ManagerOpenCourse.line();
						System.out.println("[번호]\t[교재명]\t\t\t\t[출판사]\t\t |[번호]   [교재명]\t\t\t\t\t[출판사]");
						
						ArrayList<TextbookDTO> textbookList = dao.textbook();
						
						int count2 = 0;
						
						for (TextbookDTO dto : textbookList) {
							
							count2++;
							
							if (count2 % 2 == 0) {

								System.out.printf(" %s\t   %s\t\t%s\t\t", dto.getSeq(), dto.getName(), dto.getPublisher());
								System.out.println();
								
							} else {
								
								System.out.printf(" %s\t%s\t%s\t\t | ", dto.getSeq(), dto.getName(), dto.getPublisher());								
							}
							
						}
						
						//출력 개수가 홀수일 경우 행을 한번 바꿔준다
						if (count2 % 2 == 1) {
							
							System.out.println();
						}
						
						ManagerOpenCourse.line();
						System.out.println("* 등록할 교재 번호를 입력해주세요.");
						System.out.println();
						
						System.out.print("번호 : ");
						String textbookSeq = scan.nextLine();
						
						AddOpenSubjectDTO tempDto = new AddOpenSubjectDTO();
						
						tempDto.setOpenCourseSeq(ManagerOpenCourse.openCourseSeq);
						tempDto.setStartDate(subjectStartDate);
						tempDto.setSubjectSeq(subjectSeq);
						tempDto.setTeacherSeq(teacherSeq);
						tempDto.setTextbookSeq(textbookSeq);
						
						//개설 과목 등록을 한다
						int result = dao.addOpenSubject(tempDto);
						
						//성공 시 1을 반환 받는다
						if (result > 0) {
							
							System.out.println("\n* 개설 과목 등록이 완료되었습니다.");
							main();
							return;
							
						} else {
							
							System.out.println("\n* 개설 과목 등록에 실패했습니다.");
							main();
							return;
						}

						
					} else if (teacherResult == 0) { //강의 가능한 교사 번호를 입력하지 않았을 경우
						
						System.out.println("\n* 목록에 있는 교사 번호가 아닙니다.");
						System.out.println("* 교사 번호를 확인 후 다시 등록해주세요.");
						main();
						return;
					}
					
				} else if (subjectResult == 0) { //등록 가능한 과목 번호를 입력하지 않았을 경우
					
					System.out.println("\n* 목록에 있는 과목 번호가 아닙니다.");
					System.out.println("* 과목 번호를 확인 후 다시 등록해주세요.");
					main();
					return;
				}
				
			} else { //개설 과목 시작일로 올바르지 않을 경우
				
				System.out.println("\n* 개설 과목 시작일이 올바르지 않습니다.");
				System.out.println("* 개설 과목 시작일을 확인 후 다시 등록해주세요.");
				main();
				return;
			}

		} else { //개설 과목 등록이 불가능할 경우
			
			System.out.println("\n* 남은 과정 기간에 새 개설 과목 등록이 불가능합니다.");
			main();		
			return;
		}
	}


	/**
	 * 개설 과목의 교사를 수정하는 메소드입니다.
	 * @param replaceSubjectSeq 개설 과목 번호
	 */
	private void replaceTeacher(String replaceSubjectSeq) {
		
		ArrayList<TeacherDTO> list = dao.availableReplaceTeacher(replaceSubjectSeq);
		
		ManagerOpenCourse.line();
		System.out.println("\t\t\t\t\t\t\t\t강의 가능한 교사");
		ManagerOpenCourse.line();
		System.out.println("[번호]\t\t\t\t\t[이름]\t\t\t\t    [주민번호 뒷자리]\t\t\t\t  [전화번호]");
		
		//강의 가능한 교사 번호를 저장한다
		ArrayList<String> teacherSeqList = new ArrayList<String>();
		
		for (TeacherDTO dto : list) {

			teacherSeqList.add(dto.getSeq());
			
			System.out.printf(" %2s\t\t\t\t\t%3s\t\t\t\t\t%s\t\t\t\t\t%13s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getSsn()
								, dto.getTel());
		}
		
		ManagerOpenCourse.line();
		System.out.println("* 위의 교사를 참고하여 교사 번호를 입력해주세요.");
		System.out.println();

		System.out.print("번호 : ");
		String teacherSeq = scan.nextLine();

		//강의 가능한 교사 번호를 입력했는지 알아 보기 위한 누적 변수
		int teacherResult = 0;
		
		//강의 가능한 교사 번호를 입력했다면 1을 넣는다
		for (String seq : teacherSeqList) {
			
			if (seq.equals(teacherSeq)) {
		
				teacherResult += 1;
			}
		}
		
		//강의 가능한 교사 번호를 입력했을 경우
		if (teacherResult == 1) {
			
			int replaceResult = dao.replaceTeacher(replaceSubjectSeq, teacherSeq);
			
			//교사 수정 성공 시 1을 반환 받는다
			if (replaceResult > 0) {
				
				System.out.println("\n* 개설 과목 교사가 수정되었습니다.");
				main();
				return;
				
			} else {
				
				System.out.println("\n* 개설 과목 교사 수정에 실패했습니다.");
				main();
				return;
			}
		
		} else { //목록 외의 번호를 입력했을 경우
			
			System.out.println("\n* 목록에 있는 교사 번호가 아닙니다.");
			main();
			return;
		}
	}

	
	/**
	 * 개설 과목의 교재를 수정하는 메소드입니다.
	 * @param openSubjectSeq 개설 과목 번호
	 */
	private void replaceTextbook(String openSubjectSeq) {
		
		ManagerOpenCourse.line();
		System.out.println("\t\t\t\t\t\t\t\t교재 목록");
		ManagerOpenCourse.line();
		System.out.println("[번호]\t[교재명]\t\t\t\t[출판사]\t\t |[번호]   [교재명]\t\t\t\t\t[출판사]");
		
		ArrayList<TextbookDTO> textbookList = dao.textbook();
		
		int count = 0;
		
		for (TextbookDTO dto : textbookList) {
			
			count++;
			
			if (count % 2 == 0) {

				System.out.printf(" %s\t   %s\t\t%s\t\t", dto.getSeq(), dto.getName(), dto.getPublisher());
				System.out.println();
				
			} else {
				
				System.out.printf(" %s\t%s\t%s\t\t | ", dto.getSeq(), dto.getName(), dto.getPublisher());								
			}

		}
		
		//출력 개수가 홀수일 경우 행을 한번 바꿔준다
		if (count % 2 == 1) {
			
			System.out.println();
		}
		
		ManagerOpenCourse.line();
		System.out.println("* 수정할 교재 번호를 입력해주세요.");
		System.out.println();
		
		System.out.print("번호 : ");
		String textbookSeq = scan.nextLine();

		int result = dao.replaceTextbook(openSubjectSeq, textbookSeq);
		
		//성공 시 1을 반환 받는다
		if (result > 0) {
			
			System.out.println("\n* 개설 과목 교재가 수정되었습니다.");
			main();
			return;
			
		} else {
			
			System.out.println("\n* 개설 과목 교재 수정에 실패했습니다.");
			main();
			return;
		}
	}

	
	/**
	 * 개설 과목 번호를 입력 받아 삭제하는 메소드입니다.
	 */
	private void delete() {

		ManagerOpenCourse.line();
		System.out.println("\t\t\t\t\t\t\t\t개설 과목 삭제");
		ManagerOpenCourse.line();
		System.out.println("* 삭제하려는 개설 과목 번호를 입력해주세요.");

		System.out.print("\n번호 : ");
		String openSubjectSeq = scan.nextLine();
		
		//삭제 전 개설 과목 정보 출력
		find(openSubjectSeq);
		
		System.out.print("* 정말로 삭제하시겠습니까?(y/n) : ");
		String answer = scan.nextLine();
		
		//삭제 진행
		if (answer.toLowerCase().equals("y")) {
			
			int result = dao.deleteOpenSubject(openSubjectSeq);
			
			//삭제가 되었을 경우
			if (result > 0) {
		
				System.out.println("\n* 개설 과목이 삭제되었습니다.");
				main();
				return;
			
			} else {
				
				System.out.println("\n* 개설 과목 삭제에 실패했습니다.");
				main();
				return;
			}
			
		} else if (answer.toLowerCase().equals("n")) { //삭제 취소
			
			System.out.println("\n* 삭제가 취소되었습니다.");
			main();
			return;
			
		} else {
			
			System.out.println("\n* 잘못된 입력입니다.");
			main();
			return;
		}
	}

	
	/**
	 * 개설 과목 관리 화면으로 이동하는 메소드입니다.
	 */
	public static void main() {
		
		System.out.println("\n* 개설 과목 관리 화면으로 돌아갑니다.");
		ManagerOpenCourse.pause();
		ManagerOpenCourse.find(ManagerOpenCourse.openCourseSeq);
	}
}
