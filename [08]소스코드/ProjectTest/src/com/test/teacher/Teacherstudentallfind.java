package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dao.TeacherstudentallfindDAO;
import com.test.dto.TeacherstudentallfindDTO;

/**
 * 과목을 듣는 수강생을 조회하는 클래스입니다.
 * @author 윤지봉
 *
 */
public class Teacherstudentallfind {

	/**
	 * 수강생을 조회하는 메서드 입니다.
	 * @param num //과정번호
	 * @param seq //교사번호
	 */
	public static void studentfind(int num, int seq) {
		

		
		ArrayList<TeacherstudentallfindDTO> list = TeacherstudentallfindDAO.teacherstudentfind(num); //수강생 목록 가져오는 JDBC 클래스
		
		
		System.out.println("-----------------------------------------------------------");
		
		System.out.printf("%s\t%s\t%s\t%s\t%s\n","[번호]", "[이름]","[전화번호]","[등록일]","[상태]");
		
		for (TeacherstudentallfindDTO dto : list ) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getTel()
								, dto.getRegist()
								, dto.getStatus());
		}
	
		System.out.println("-----------------------------------------------------------");

		System.out.println("엔터를 치면 메인화면으로 돌아갑니다.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		TeacherMain.manu(seq);//교사메인화면 클래스
	}
	

}
