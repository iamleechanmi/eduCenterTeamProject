package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherAttendanceDAO;
import com.test.dao.TeacherCourseListDAO;
import com.test.dao.TeacherSubjectListDAO;
import com.test.dto.TeacherAttendanceDTO;
import com.test.dto.TeacherCourseDTO;
import com.test.dto.TeacherStudentListDTO;
import com.test.dto.TeacherSubjectDTO;
/**
 * 출석을 조회하는 클래스입니다.
 * @author 윤지봉
 *
 */
public class TeacherAttendance {
	private static ArrayList<TeacherAttendanceDTO> attendlist;
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
		attendlist = null;
	}
	
	 
	/**
	 * 조회 기준을 선택해서 출결을 조회하는 메서드입니다.
	 * @param seq 교사번호
	 */
	public static void attendance(int seq) {
	
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 담당 과정별 출석 조회하기");
		System.out.println("\t\t2. 수강생별 출석 조회하기");
		System.out.println("\t\t3. 돌아가기");
		
		System.out.println("-----------------------------------------------------------");
		System.out.print("번호 : ");
		int input = scan.nextInt();
		
		
		if(input ==1) {
			attendlist = subject(seq); //담당과정별 조회 메서드 //리턴값은 출석 결과 ArrayList<TeacherAttendanceDTO>
		} else if (input ==2) {
			attendlist = student(seq); //수강생별 조회 메서드 //리턴값은 출석 결과 ArrayList<TeacherAttendanceDTO>
		} else {
			TeacherMain.manu(seq); //교사메인화면 클래스
		}
		
		if(attendlist.isEmpty()) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("해당되는 교육생 기록이 없습니다.");
		} else {
			System.out.println("-----------------------------------------------------------");
			
			System.out.printf("%s\t%3s\t%3s\t%s\t%s\t%s\t%s\t%s\t%s\n"
					,"[순서]","[학생번호]","[이름]","[출석]","[조퇴]","[지각]","[외출]","[병가]","[결석]" );
			
			int index = 1;
			for (TeacherAttendanceDTO dto : attendlist) {
				System.out.printf("%3d\t%5s\t\t%3s\t%4s\t%3s\t%3s\t%3s\t%3s\t%3s\n",index,dto.getSeq(),dto.getName(),dto.getAttend(),dto.getEarly(),dto.getLate(), dto.getOut(),dto.getSick(),dto.getAbsense());
				index++;
			}
			
			
		}
		
		
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 이전화면으로 돌아가기");
		System.out.println("\t\t2. 메인화면으로 돌아가기");
		System.out.println("-----------------------------------------------------------");
		System.out.print("번호 : ");
		int num = scan.nextInt();
		
		if (num ==1) {
			attendance(seq);
		} else {
			TeacherMain.manu(seq);
		}
		
	}

	/**
	 * 담당과정,과목을 기준으로 출석을 조회하는 메서드입니다.
	 * @param seq
	 * @return 출석 결과 ArrayList<TeacherAttendanceDTO> attendlist
	 */
	private static ArrayList<TeacherAttendanceDTO> subject(int seq) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t    담당 과정 조회하기");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 강의 상태 : 강의 종료");
		System.out.println("\t\t2. 강의 상태 : 강의 중");
		
		
		boolean flag = true;
		String state = "";

		while (flag) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("원하는 번호를 입력하세요");
			System.out.print("번호 : ");
			int num = scan.nextInt();
			if (num == 1) {
				flag = false;
				state = "종료";

			} else if (num == 2) {
				flag = false;
				state = "진행";

			} else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 다시 확인해주세요");
				System.out.println("-----------------------------------------------------------");
			}			
		}
		
		ArrayList<TeacherCourseDTO> courselist = TeacherCourseListDAO.courseList(seq, state); 

		System.out.println("-----------------------------------------------------------");
		System.out.printf("%s\t%20s\t\t\t\t%5s\t%5s\n", "[번호]", "[과정명]", "[시작일]", "[종료일]");
		
		for ( TeacherCourseDTO dto : courselist) {
			System.out.printf("%2s\t%s\t%s\t%s\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getStart()
					, dto.getEnd());	
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("조회를 원하는 과정번호를 입력하세요");
		System.out.print("번호 : ");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		ArrayList<TeacherSubjectDTO> subjectlist = TeacherSubjectListDAO.subjectList(seq, num);
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%s\t%20s\t\t\t%5s\t%5s\t%8s\t\t\t\t%s\n"
				,"[번호]","[과목명]","[시작일]","[종료일]","[교재명]","[학생수]");
		
		for (TeacherSubjectDTO dto : subjectlist) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%3s\n"
							, dto.getSeq()
							, dto.getName()
							, dto.getStart()
							, dto.getEnd()
							, dto.getTextbook()
							, dto.getCount());
		}
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("원하는 번호를 입력하세요");
		System.out.print("번호 : ");
		num = scan.nextInt();

		boolean flag2 = true;		
		
		while (flag2) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t조회 단위를 선택하세요");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t1.년도별 조회");
			System.out.println("\t\t2.월별 조회");
			System.out.println("\t\t3.일별 조회");
			System.out.println("-----------------------------------------------------------");
			System.out.println("원하는 번호를 입력하세요");
			System.out.print("번호 : ");
			int pnum = scan.nextInt();
								
		
			if (pnum == 1) {
				flag2 = false;
				
				//boolean flag_year = true;
				//while( flag_year) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("원하는 년도를 입력하세요");
					System.out.print("년(yy) : ");
					int year = scan.nextInt();
					
//					if(year<1 && year>=100) {
//						System.out.println("년도를 잘못입력하였습니다.");
//					}
//				}
				
				attendlist = TeacherAttendanceDAO.attendance(num, year);//과목번호, 기간
				
			} else if (pnum == 2) {
				flag2 = false;
				System.out.println("-----------------------------------------------------------");
				System.out.println("원하는 년도, 월을 입력하세요");
				System.out.print("년(yy) : ");
				int year = scan.nextInt();
				System.out.print("월(mm) : ");
				int month = scan.nextInt();
				
				attendlist = TeacherAttendanceDAO.attendance(num, year, month);//과목번호, 기간

			} else if (pnum == 3) {
				flag2 = false;
				System.out.println("-----------------------------------------------------------");
				System.out.println("원하는 년도, 월, 일을 입력하세요");
				System.out.print("년(yy) : ");
				int year = scan.nextInt();
				System.out.print("월(mm) : ");
				int month = scan.nextInt();
				System.out.print("일(dd) : ");
				int date = scan.nextInt();
				
				attendlist = TeacherAttendanceDAO.attendance(num, year, month, date);//과목번호, 기간

			} else {
				flag2 = true;
				System.out.println("번호를 잘못 입력하였습니다.");
			}
			
			
		}
		
		return attendlist;
		
		
		
	}
	
	
	/**
	 *수강생별 조회 메서드입니다.
	 * @param 교사번호
	 * @return 출석 결과 ArrayList<TeacherAttendanceDTO> attendlist
	 */
	private static ArrayList<TeacherAttendanceDTO> student(int seq) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 수강생 목록 확인하기");
		System.out.println("\t\t2. 수강생 번호 입력하기");
		System.out.println("\t\t3. 돌아가기");
		System.out.println("-----------------------------------------------------------");
		System.out.println("원하는 번호를 입력하세요");
		System.out.print("번호 : ");
		int input = scan.nextInt();
		
		if (input ==1) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t진행 상태 선택하기");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t1. 수강 완료");
			System.out.println("\t\t2. 수강 중");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println("원하는 번호를 입력하세요");
			System.out.print("번호 : ");
			int num = scan.nextInt();
			
			boolean flag = true;
			String state = "";

			while (flag) {
				if (num == 1) {
					flag = false;
					state = "종료";

				} else if (num == 2) {
					flag = false;
					state = "진행";

				} else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("번호를 다시 확인해주세요");
					System.out.println("-----------------------------------------------------------");
				}
			}

			String subjectseq = TeacherAttendanceDAO.getsubjectseq(seq, state);//과목번호 찾기

			ArrayList<TeacherStudentListDTO> list = TeacherAttendanceDAO.attendanceStudentList(seq, subjectseq, state);
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%s\t%3s\t%5s\n", "[번호]","[이름]","[등록일]");
			for (TeacherStudentListDTO dto : list) {
				System.out.printf("%3s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getRegistrationDate().substring(0,10));
			}
			
			
			attendlist = attendancecheck();
			
			
			
			
		} else if ( input ==2) {
			attendlist = attendancecheck();
		} else {
			TeacherMain.manu(seq);
		}
		
		return attendlist;
		
	}


	private static ArrayList<TeacherAttendanceDTO> attendancecheck() {
		
		System.out.println("-----------------------------------------------------------");			
		System.out.println("출결 조회를 희망하는 학생의 번호를 입력하세요");
		System.out.print("번호 : ");
		int seq = scan.nextInt();
		
		
		boolean flag2 = true;
		
		ArrayList<TeacherAttendanceDTO> attendlist = new ArrayList<TeacherAttendanceDTO>();
		
		while (flag2) {
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t조회 단위를 선택하세요");
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t1.년도별 조회");
			System.out.println("\t\t2.월별 조회");
			System.out.println("\t\t3.일별 조회");
			System.out.println("-----------------------------------------------------------");
			System.out.println("원하는 번호를 입력하세요");
			System.out.print("번호 : ");
			int pnum = scan.nextInt();
								
		
			if (pnum == 1) {
				flag2 = false;
				System.out.println("-----------------------------------------------------------");
				System.out.println("원하는 년도를 입력하세요");
				System.out.print("년(yy) : ");
				int year = scan.nextInt();
				
				attendlist = TeacherAttendanceDAO.attendances(seq, year);//과목번호, 기간
			} else if (pnum == 2) {
				flag2 = false;
				System.out.println("-----------------------------------------------------------");
				System.out.println("원하는 년도, 월을 입력하세요");
				System.out.print("년(yy) : ");
				int year = scan.nextInt();
				System.out.print("월(mm) : ");
				int month = scan.nextInt();
				
				attendlist = TeacherAttendanceDAO.attendances(seq, year, month);//과목번호, 기간

			} else if (pnum == 3) {
				flag2 = false;
				System.out.println("-----------------------------------------------------------");
				System.out.println("원하는 년도, 월, 일을 입력하세요");
				System.out.print("년(yy) : ");
				int year = scan.nextInt();
				System.out.print("월(mm) : ");
				int month = scan.nextInt();
				System.out.print("일(dd) : ");
				int date = scan.nextInt();
				
				attendlist = TeacherAttendanceDAO.attendances(seq, year, month, date);//과목번호, 기간

			} else {
				flag2 = true;
				System.out.println("번호를 잘못 입력하였습니다.");
			}
		}
		return attendlist;
		
	}


}






