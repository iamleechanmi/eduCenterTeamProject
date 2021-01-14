package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.Student_FindSignUpDAO;
import com.test.dao.Student_ScoreDAO;
import com.test.dto.Student_FindSignUpDTO;
import com.test.dto.Student_ScoreDTO;


/**
 * 성적 조회를 위한 과정 선택/ 선택 과정 성적 출력 화면입니다.
 * @author 오수경
 *
 */
public class StudentScore {

	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
	}


	/**
	 * 성적 조회 시 과정 선택 화면 입니다. 
	 * @param seq 학생번호
	 */
	public static void mySignUp(String seq) {

		boolean loop = true;

		while (loop) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t성적 조회");
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
			System.out.println("*수강 번호를 선택해주세요.");
			System.out.println("*0번 입력시 이전화면으로 돌아갑니다.");
			System.out.print("번호 : ");
			String sel = scan.nextLine();

			// 번호 선택 후 출력화면
			for (Student_FindSignUpDTO dto : list) {
				String suSeq = dto.getSuSeq();
				
				if (sel.equals(suSeq)) {
					// 학생 번호와 수강번호(입력)를 갖고 과목별 성적조회 화면으로 이동
					myScore(seq, dto.getSuSeq());
					loop = false;
				} else if (sel.equals("0")) {
					// 이전화면(교육생 메뉴)
					loop=false;
					break;
				} else {
					System.out.println("번호를 잘못 입력하셨습니다.");
				}
			}

		}
	}

	/**
	 * 선택 과정 성적 출력 화면 입니다.
	 * @param seq 학생 번호
	 * @param suSeq 수강번호(입력)
	 */
	private static void myScore(String seq, String suSeq) {
		
		Student_ScoreDAO dao = new Student_ScoreDAO();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t성적 조회");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("[과목명]\t\t\t\t[출석배점]\t[필기배점]\t[실기배점]\t[출석점수]\t[필기점수]\t[실기점수]\t[시험문제여부]");
		
		
		ArrayList<Student_ScoreDTO> list = dao.scoreList(seq, suSeq);
		
		for(Student_ScoreDTO dto : list) {
			System.out.printf("%s%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",dto.getSubjectName(), dto.getAttendancepoint(), dto.getWrittenpoint(), dto.getPracticalpoint(), dto.getAttendancescore(), dto.getWrittenscore(), dto.getPracticalscore(),dto.getWhetherExam());
		}
		
		//엔터키 입력시 이전화면으로 돌아가기
		System.out.println();
		System.out.println("*enter를 입력하면 이전화면으로 돌아갑니다.");
		
		scan.nextLine();
		

		
	}
	
}
