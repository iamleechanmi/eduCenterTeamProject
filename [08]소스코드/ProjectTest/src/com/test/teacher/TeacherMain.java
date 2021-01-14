package com.test.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherScoreDAO;
import com.test.dto.TeacherSeqDTO;
import com.test.main.Main;

/**
 * 교사 메인화면, 로그인 및 메뉴를 선택하는 화면입니다.
 * @author 이대홍
 *
 */

public class TeacherMain {
	/**
	 * Login() 에서 교사명을 입력받는 맴버변수입니다.
	 */

	private static String teacherName;
	
	/**
	 * manu()에서 교사번호를 반환받아 사용합니다.
	 * 배점 및 시험정보관리 메뉴 이동 메소드입니다.
	 * @param teacherSeq 교사번호
	 * @throws IOException BUfferedReader
	 */
	public static void teacherPointManu(int teacherSeq) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		TeacherPointManage pointManage = new TeacherPointManage();
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t\t배점 및 시험 정보 관리 메뉴");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("1. 배점입력");
		System.out.println("2. 배점수정");
		System.out.println("3. 배점조회");
		System.out.println("4. 시험문제 등록 여부 입력");
		System.out.println("5. 시험문제 등록 여부 수정");
		System.out.println("6. 교사메뉴");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*메뉴를 선택해 주세요.");
		System.out.print("번호 : ");
		String num = reader.readLine();
		
		if (num.equals("1")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t\t배점 입력");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			pointManage.pointAdd(teacherSeq);

		} else if (num.equals("2")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t\t배점수정");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			pointManage.pointReplace(teacherSeq);
		} else if (num.equals("3")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t\t배점조회");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			pointManage.pointFind(teacherSeq);
		} else if (num.equals("4")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t시험문제 등록여부 입력");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			pointManage.whetherExamAdd(teacherSeq);
		} else if (num.equals("5")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t시험문제 등록여부 수정");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			pointManage.whetherExamReplace(teacherSeq);
			
		} else {
			System.out.println();
			System.out.println("*교사 메뉴로 돌아갑니다.");
			manu(teacherSeq);
		}  
		
	}// TeacherPointManu
	
	/**
	 * manu()에서 교사번호를 반환받아 사용합니다.
	 * 성적정보관리 메뉴 이동 메소드입니다.
	 * @param teacherSeq 교사번호
	 * @throws IOException BUfferedReader
	 */
	
	public static void teacherScoreManu(int teacherSeq) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t\t성적정보관리 메뉴");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("*1. 성적입력");
		System.out.println("*2. 성적수정");
		System.out.println("*3. 교육생별 성적조회");
		System.out.println("*4. 교사 메뉴");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*메뉴를 선택해 주세요.");
		System.out.print("번호 : ");
		String num = reader.readLine();
		TeacherScoreManage scoreManage = new TeacherScoreManage();

		if (num.equals("1")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t성적입력");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			scoreManage.scoreAdd(teacherSeq);

		} else if (num.equals("2")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t성적수정");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			scoreManage.scoreReplace(teacherSeq);
		} else if (num.equals("3")) {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t\t교육생별 성적조회");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("*담당교사 : " + teacherName);
			scoreManage.scoreFind(teacherSeq);
		} else {
			System.out.println();
			System.out.println("*교사 메뉴로 돌아갑니다.");
			manu(teacherSeq);
		} //성적정보관리 메뉴");

	} // teacherScoreManu
	 
	/**
	 * login()에서 교사번호를 반환받아 사용합니다.
	 * 교사 메인메뉴 메소드입니다.
	 * @param teacherSeq 교사번호
	 */
	public static void manu(int teacherSeq) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t\t\t교사 메뉴");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("1. 강의 스케줄관리");
		System.out.println("2. 성적정보관리");
		System.out.println("3. 배점 및 시험정보관리");
		System.out.println("4. 출결관리 및 출결조회");
		System.out.println("5. 로그아웃");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*메뉴를 선택해주세요");
		System.out.print("번호 :");
		String number = reader.nextLine();
		
		if (number.equals("1")) {

			TeacherCourseList.courseList(teacherSeq);
		} else if (number.equals("2")) {
			try {
				teacherScoreManu(teacherSeq);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("TeacherMain_Manu()_teacherScoreManu");
				e.printStackTrace();
			}
			
		} else if (number.equals("3")) {
			try {
				teacherPointManu(teacherSeq);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("TeacherMain_Manu()_teacherPointManu");
				e.printStackTrace();
				
			}
		} else if (number.equals("4")) {
			
			TeacherAttendance.attendance(teacherSeq);
		} else {
			System.out.println("*로그아웃 합니다.");
			Main main = new Main();
			String[] args = null;
			main.main(args);
		}

	} //manu
	
	/**
	 * 교사 로그인 메소드입니다.
	 */

	public void login(){
		// TODO Auto-generated method stub
		// 예시 ) 3 박주호 1512385, 4 이동국 1782153

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*교사 로그인 화면입니다.");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		Scanner reader = new Scanner(System.in);
		String name ="";
		String ssn = "";
		System.out.print("이름 : ");
			
		name = reader.nextLine();
		System.out.print("비밀번호 : ");
		ssn = reader.nextLine();
		TeacherSeqDTO teacher = new TeacherSeqDTO();
		teacher.setName(name);
		teacher.setSsn(ssn);

		teacherName = teacher.getName();
		
		TeacherScoreDAO seq = new TeacherScoreDAO();
		int teacherSeq = seq.seq(teacher);
		
		manu(teacherSeq);
	} //login
}
