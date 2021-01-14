package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.Student_AttendanceDAO;
import com.test.dao.Student_FindSignUpDAO;
import com.test.dto.Student_AttendanceDTO;
import com.test.dto.Student_FindSignUpDTO;

/**
 * 출결 체크/조회 화면입니다.
 * @author 오수경
 *
 */
public class StudentAttendance {
	
	private static Scanner scan;
	private static Student_AttendanceDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new Student_AttendanceDAO();
	}
	
	public static void main(String[] args) {
		menu("86");
	}

	/**
	 * 출결 체크 / 조회 선택 화면 입니다.
	 * 작성자 : 오수경
	 * @param seq 학생 번호
	 */
	public static void menu(String seq) {
		
		boolean loop = true;
		
		while(loop) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 체크/조회");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("1. 출결 체크");
		System.out.println("2. 출결 조회");
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t번호선택");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("*번호를 선택해주세요.");
		System.out.print("번호 : ");
		String sel = scan.nextLine();
		
		//번호 선택시 출력 화면
		if(sel.equals("1")) {
			//출석 체크
			checkAttendacne(seq);
		}else if(sel.equals("2")) {
			//출결 조회
			fineMyAttendance(seq);
		}else if(sel.equals("0")) {
			//이전 화면(교육생 메뉴)
			loop = false;
			break;
		}else {
			System.out.println("번호를 잘 못 입력하셨습니다.");
		}
		
		
		}//while
	}
	
	/**
	 * 출결 체크 선택 화면 입니다.
	 * @param seq 학생 번호
	 */
	private static void checkAttendacne(String seq) {
		
		boolean loop = true;
		
		while(loop) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 체크");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("1. 입실 체크");
		System.out.println("2. 퇴실 체크");
		System.out.println("3. 외출");
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t번호선택");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("* 메뉴를 선택해주세요.");
		System.out.print("번호 : ");
		String sel = scan.nextLine();
		
		//번호 선택시 출력 화면
		if(sel.equals("1")) {
			//입실 체크
			checkIn(seq);
		}else if(sel.equals("2")) {
			//퇴실 체크
			checkOut(seq);
		}else if(sel.equals("3")) {
			//외출
			checkOuting(seq);
		}else if(sel.equals("0")) {
			//이전 화면(출결 체크/조회 선택 화면)
			loop = false;
			break;
		}else {
			System.out.println("번호를 잘못 입력하셨습니다.");
		}
		
		
		}//while
		
		
	}

	/**
	 * 입실 체크.
	 * @param seq 교육생 번호
	 */
	private static void checkIn(String seq) {
		int result = dao.checkIn(seq);
		
		System.out.println();
		if(result == 1) {
			System.out.println("** 입실 되었습니다.");
		}else {
			System.out.println("확인 후 다시 체크해주세요.");
		}
		System.out.println();
		
	}

	/**
	 * 퇴실 체크
	 * @param seq 교육생 번호
	 */
	private static void checkOut(String seq) {
		int result = dao.checkOut(seq);
		
		System.out.println();
		if(result == 1) {
			System.out.println("** 퇴실 되었습니다.");
		}else {
			System.out.println("확인 후 다시 체크해주세요.");
		}
		System.out.println();
		
	}

	/**
	 * 외출 체크
	 * @param seq 교육생 번호
	 */
	private static void checkOuting(String seq) {
		int result = dao.checkOuting(seq);
		
		System.out.println();
		if(result == 1) {
			System.out.println("** 외출 되었습니다.");
		}else {
			System.out.println("확인 후 다시 체크해주세요.");
		}
		
	}

	/**
	 * 출결 조회 시 과정 선택 화면입니다.
	 * 과정 선택 -> 전체조회, 연도별 조회, 월별조회, 일별조회
	 * @param seq 학생 번호
	 */
	private static void fineMyAttendance(String seq) {
		boolean loop = true;

		while (loop) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t출결 조회");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t수강 목록");
			System.out.println();
			System.out.println("[수강번호]\t[과정명]\t\t\t\t\t\t[과정 시작일]\t[과정 종료일]\t[강의실명]\t[과정 상태]");
			
			Student_FindSignUpDAO dao = new Student_FindSignUpDAO();
			
			ArrayList<Student_FindSignUpDTO> list = dao.signUpList(seq);

			for (Student_FindSignUpDTO dto : list) {

				System.out.printf("%5s\t\t%s\t%s\t%s\t%5s\t%s\n", dto.getSuSeq(), dto.getcName(), dto.getOcStartDate(),
						dto.getOcEndDate(), dto.getCrName(), dto.getSuStatus());

			}
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*수강 번호를 선택해주세요.");
			System.out.println("*0번 입력시 이전화면으로 돌아갑니다.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();

			// 번호 선택 후 출력화면
			for (Student_FindSignUpDTO dto : list) {
				String num = dto.getSuSeq();
				
				if (sel.equals(num)) {
					// 학생 번호와 수강번호(입력)를 갖고 과목별 성적조회 화면으로 이동
					fineMyAttendanceMenu(seq, dto.getSuSeq(),dto.getOcStartDate(),dto.getOcEndDate());
					loop = false;
				} else if (sel.equals("0")) {
					// 이전화면(출결 체크/조회 선택화면)
					loop=false;
					break;
				} else {
					System.out.println("번호를 잘못 입력하셨습니다.");
				}
			}

		}//while
		
	}
	
	/**
	 * 출결 조회 필터 선택 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 */
	private static void fineMyAttendanceMenu(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t출결 조회");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("1. 전체 조회");
			System.out.println("2. 연도별 조회");
			System.out.println("3. 월별 조회");
			System.out.println("4. 일별 조회");
			System.out.println("0. 이전 화면");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t번호선택");
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("* 메뉴를 선택해주세요.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			//번호 선택 후 출력 화면
			if(sel.equals("1")) {
				//전체 조회
				findList(seq,suSeq,ocStartDate,ocEndDate);
			}else if(sel.equals("2")){
				//연도별 조회
				findYear(seq,suSeq,ocStartDate,ocEndDate);
			}else if(sel.equals("3")) {
				//월별 조회
				findMonth(seq,suSeq,ocStartDate,ocEndDate);
			}else if(sel.equals("4")) {
				//일별 조회
				findDate(seq,suSeq,ocStartDate,ocEndDate);
			}else if(sel.equals("0")) {
				//이전 화면(출결 조회 과정 선택 화면)
				loop = false;
				break;
			}else {
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
		
	}

	/**
	 * 전체 출결 조회 출력 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 출력일
	 */
	private static void findList(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 조회(전체 조회)");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.printf("과정 기간 : %s ~ %s\n",ocStartDate,ocEndDate);
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 상태");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[출석]\t[지각]\t[조퇴]\t[외출]\t[병가]\t[결석]");
		ArrayList<Student_AttendanceDTO> status = dao.status(seq,null,null);
		for(Student_AttendanceDTO dto : status) {
			System.out.printf("%d\t%d\t%d\t%d\t%d\t%d",dto.getAttendanceCount(),dto.getTardyCount(),dto.getEarlyLeaveCount(),dto.getLeavingCount(),dto.getSickLeaveCount(),dto.getAbsentCount());
		}
		System.out.println();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t상세 출결 정보");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		System.out.println();
		System.out.println("[날짜]\t\t[입실시각]\t\t[퇴실시각]\t\t[상태]");
		
		ArrayList<Student_AttendanceDTO> list = dao.all(seq,suSeq,ocStartDate,ocEndDate);
		for(Student_AttendanceDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\n",dto.getAttendanceDate(),dto.getEnterTime(),dto.getExitTime(),dto.getStatus());
		}
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		
	}

	
	/**
	 * 연도별 출결 조회 출력 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 출력일
	 */
	private static void findYear(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 조회(연도별 조회)");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		System.out.println();
		System.out.println("**과정 기간에 해당하는 연도를 입력해야 결과가 나옵니다.");
		System.out.printf("과정 기간 : %s ~ %s\n",ocStartDate,ocEndDate);

		
		System.out.println();
		System.out.print("연도 입력(yy) : ");
		String year = scan.nextLine();		
		System.out.println();	
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 상태");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[출석]\t[지각]\t[조퇴]\t[외출]\t[병가]\t[결석]");
		ArrayList<Student_AttendanceDTO> status = dao.status(seq,year,"yy");
		for(Student_AttendanceDTO dto : status) {
			System.out.printf("%d\t%d\t%d\t%d\t%d\t%d",dto.getAttendanceCount(),dto.getTardyCount(),dto.getEarlyLeaveCount(),dto.getLeavingCount(),dto.getSickLeaveCount(),dto.getAbsentCount());
		}
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t상세 출결 정보");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		System.out.println();
		System.out.println("[날짜]\t\t[입실시각]\t\t[퇴실시각]\t\t[상태]");
		
		
		ArrayList<Student_AttendanceDTO> list = dao.year(seq,suSeq,ocStartDate,ocEndDate,year);
		for(Student_AttendanceDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\n",dto.getAttendanceDate(),dto.getEnterTime(),dto.getExitTime(),dto.getStatus());
		}
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		
	}

	/**
	 * 월별 출결 조회 출력 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 출력일
	 */
	private static void findMonth(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 조회(월별 조회)");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("**과정 기간에 해당하는 연도,월를 입력해야 결과가 나옵니다.");
		System.out.printf("과정 기간 : %s ~ %s\n",ocStartDate,ocEndDate);

		
		System.out.println();
		System.out.print("연도/월 입력(yy/mm) : ");
		String month = scan.nextLine();
		
		System.out.println();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 상태");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[출석]\t[지각]\t[조퇴]\t[외출]\t[병가]\t[결석]");
		ArrayList<Student_AttendanceDTO> status = dao.status(seq,month,"yy/mm");
		for(Student_AttendanceDTO dto : status) {
			System.out.printf("%d\t%d\t%d\t%d\t%d\t%d",dto.getAttendanceCount(),dto.getTardyCount(),dto.getEarlyLeaveCount(),dto.getLeavingCount(),dto.getSickLeaveCount(),dto.getAbsentCount());
		}
		System.out.println();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t상세 출결 정보");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[날짜]\t\t[입실시각]\t\t[퇴실시각]\t\t[상태]");
		
		
		ArrayList<Student_AttendanceDTO> list = dao.month(seq,suSeq,ocStartDate,ocEndDate,month);
		for(Student_AttendanceDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\n",dto.getAttendanceDate(),dto.getEnterTime(),dto.getExitTime(),dto.getStatus());
		}
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		
	}

	
	/**
	 * 일별 출결 조회 출력 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강 번호
	 * @param ocStartDate 과정 시작일
	 * @param ocEndDate 과정 출력일
	 */
	private static void findDate(String seq, String suSeq, String ocStartDate, String ocEndDate) {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t출결 조회(일별 조회)");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("**과정 기간에 해당하는 연도,월,일을 입력해야 결과가 나옵니다.");
		System.out.printf("과정 기간 : %s ~ %s\n",ocStartDate,ocEndDate);

		
		System.out.println();
		System.out.print("연도/월/일 입력(yy/mm/dd) : ");
		String date = scan.nextLine();
	
		System.out.println();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t상세 출결 정보");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[날짜]\t\t[입실시각]\t\t[퇴실시각]\t\t[상태]");
		
		
		ArrayList<Student_AttendanceDTO> list = dao.date(seq,suSeq,ocStartDate,ocEndDate,date);
		for(Student_AttendanceDTO dto : list) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\n",dto.getAttendanceDate(),dto.getEnterTime(),dto.getExitTime(),dto.getStatus());
		}
		
		System.out.println();
		System.out.println("* enter를 입력하면 이전화면으로 돌아갑니다.");
		scan.nextLine();
		
	}


}
