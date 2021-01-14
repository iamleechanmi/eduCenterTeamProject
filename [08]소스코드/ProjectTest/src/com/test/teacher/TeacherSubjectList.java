package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherSubjectListDAO;
import com.test.dto.TeacherSubjectDTO;

/**
 * 선택한 과정의 과목을 확인하는 클래스입니다.
 * @author 윤지봉
 *
 */
public class TeacherSubjectList {

	/**
	 * 과목 목록이 나오는 메서드입니다.
	 * @param seq 교사번호
	 */
	public static void subjectList(int seq) {
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t개설 과목 조회");
		System.out.println("-----------------------------------------------------------");
		System.out.println("조회하고 싶은 과정의 번호를 입력하세요");
		System.out.print("번호 : ");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		ArrayList<TeacherSubjectDTO> list = TeacherSubjectListDAO.subjectList(seq, num); // 과목 목록 가져오는 JDBC 클래스
		
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%s\t%20s\t\t\t%5s\t%5s\t%8s\t\t\t\t%s\n"
				,"[번호]","[과목명]","[시작일]","[종료일]","[교재명]","[학생수]");
		
		for (TeacherSubjectDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%3s\n"
							, dto.getSeq()
							, dto.getName()
							, dto.getStart()
							, dto.getEnd()
							, dto.getTextbook()
							, dto.getCount());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\t1. 과목 수강생 조회");
		System.out.println("\t\t2. 돌아가기");
		System.out.println("-----------------------------------------------------------");
		System.out.println("원하는 번호를 입력하세요");
		System.out.print("번호 : ");
		int input = scan.nextInt();
		
		if (input ==1) {
			Teacherstudentallfind.studentfind(num, seq); //과목 수강생 조회하는 클래스
		} else {
			TeacherCourseList.courseList(seq); //과정 조회하는 클래스(이전클래스)
		}
		
	}

}
