package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherCourseListDAO;
import com.test.dto.TeacherCourseDTO;

/**
 * 담당 과정의 목록을 확인하는 클래스입니다.
 * @author 윤지봉
 *
 */
public class TeacherCourseList {
	
/**
 * 과정상태 선택후 해당하는 과정이 나오는 메서드입니다.
 * @param seq 교사번호(seq)
 */
	public static void courseList(int seq) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t담당 과정 상태 선택하기");
		System.out.println("\t\t1. 강의 종료");
		System.out.println("\t\t2. 강의 진행");
		System.out.println("\t\t3. 강의 예정");
		System.out.println("-----------------------------------------------------------");
		
		boolean flag = true;
		String state = "";

		while (flag) {
			System.out.print("번호 : ");
			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();
			if (num == 1) {
				flag = false;
				state = "종료";

			} else if (num == 2) {
				flag = false;
				state = "진행";

			} else if (num == 3) {
				flag = false;
				state = "예정";

			} else {
				System.out.println("-----------------------------------------------------------");
				System.out.println("번호를 다시 확인해주세요");
				System.out.println("-----------------------------------------------------------");
			}
		}
		
		ArrayList<TeacherCourseDTO> list = TeacherCourseListDAO.courseList(seq, state);//과정목록 가져오는 JDBC 클래스
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%s\t%20s\t\t\t\t%5s\t%5s\n"
				,"[번호]","[과정명]","[시작일]","[종료일]");
		
		for ( TeacherCourseDTO dto : list) {
			System.out.printf("%2s\t%s\t%s\t%s\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getStart()
					, dto.getEnd());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 개설과목 조회하기");
		System.out.println("\t\t2. 메인으로 돌아가기");
		System.out.println("-----------------------------------------------------------");
		System.out.println("원하는 번호를 입력하세요");
		System.out.print("번호 : ");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		if (num == 1) {
			TeacherSubjectList.subjectList(seq);//과목조회하는 클래스
		} else {
			TeacherMain.manu(seq); //교사 메인화면
		}
		
		
		
	}
	
	
}
