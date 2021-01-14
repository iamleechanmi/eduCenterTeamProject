package com.test.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.test.dao.TeacherScoreDAO;
import com.test.dto.TeacherExamScoreDTO;
import com.test.dto.VwFindStudentGradeNotNullDTO;
import com.test.dto.VwFindStudentGradeNullDTO;
/**
 * 성적관리 클래스 입니다.
 * @author 이대홍
 */


public class TeacherScoreManage {

	/**
	 * 학생의 최초 성적입력 메소드입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */
	
	public void scoreAdd(int teacherNumber) throws IOException{
		TeacherScoreDAO dao = new TeacherScoreDAO();
		ArrayList<VwFindStudentGradeNullDTO> list = dao.ScoreNullList(teacherNumber);  
		
		String manu = String.format("[수강번호]\t[과목번호]\t[과정번호]\t\t[과정명]"
				+ "\t\t\t\t[과목명]\t\t[교육생명]\t\t[출석배점]  [필기배점]  [실기배점]");
		//10번마다 한번씩  메뉴 출력
		int count = 0; 
		System.out.println(manu);
		
		for (VwFindStudentGradeNullDTO dto : list) {
				
			String result = String.format("%6s%7s%7s %s    %10.10s\t\t%s\t\t%s\t  %s      %s"
					,dto.getSignUpseq(),dto.getOpenSubjectSeq()
					,dto.getOpenCourseSeq(),dto.getCourseName()
					,dto.getSubjectName(),dto.getStudentName()
					,dto.getAttendancePoint(),dto.getWrittenPoint()
					,dto.getPracticalPoint());
			System.out.println(result);
			count++;
			if(count==10) {
				System.out.println(manu);
				count = 0;
			}
	
		}
		
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("*성적입력을 위해 순차적으로 입력해 주세요");
		System.out.println("*정보 입력을 취소하거나 성적정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		System.out.print("수강 번호 : ");
		String signUpSeq = reader.readLine();
		returnManu(signUpSeq, teacherNumber);
		checkNumber(signUpSeq,teacherNumber);
		
		System.out.print("과목 번호 :");
		String openSubjectSeq = reader.readLine();
		returnManu(openSubjectSeq, teacherNumber);
		checkNumber(openSubjectSeq,teacherNumber);
		
		System.out.print("출석 점수 : ");
		String attendanceScore = reader.readLine();
		returnManu(attendanceScore, teacherNumber);
		checkNumber(attendanceScore,teacherNumber);
		
		System.out.print("필기 점수 : ");
		String writtenScore = reader.readLine();
		returnManu(writtenScore, teacherNumber);
		checkNumber(writtenScore,teacherNumber);
		
		System.out.print("실기 점수 : ");
		String practicalScore = reader.readLine();
		returnManu(practicalScore, teacherNumber);
		checkNumber(practicalScore,teacherNumber);
		
		TeacherExamScoreDTO score = new TeacherExamScoreDTO();
		
		score.setSignUpSeq(signUpSeq);
		score.setOpenSubjectSeq(openSubjectSeq);
		score.setAttendanceScore(attendanceScore);
		score.setWrittenScore(writtenScore);
		score.setPracticalScore(practicalScore);
		
		int result = dao.add(score);
		
		if (result ==1 ) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*성적이 입력 되었습니다.");
			returnManu(teacherNumber);
		} else {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*성적이 입력 되지 않았습니다. 자세한 사항은 관리자에게 문의해주세요");
			System.out.println();
			returnManu(teacherNumber);
		}
		
	} //scoreAdd 
	
	/**
	 * 학생의 성적수정 메소드입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */
	//성적수정 
	public void scoreReplace(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		String info ="";
		TeacherScoreDAO dao = new TeacherScoreDAO();
		ArrayList<VwFindStudentGradeNotNullDTO> list = dao.ScoreNullNotList(teacherNumber,info);  
		String manu = String.format("[성적번호]\t\t   [과정명]\t\t    [과목명]\t\t\t[교육생명]"
				+ "\t  [출석성적]  [필기성적]  [실기성적]  [출석배점]  [필기배점]  [실기배점]");
		int count = 0; 
		System.out.println(manu);
		
		for(VwFindStudentGradeNotNullDTO dto :list) {
			System.out.printf("%5s%s\t%10.10s\t\t%3s\t\t%s\t  %s\t   %s\t     %s\t       %s\t%s\n"
					,dto.getExamScoreSeq(),dto.getCourseName()
					,dto.getSubjectName(),dto.getStudentName()
					,dto.getAttendanceScore(),dto.getWrittenscore()
					,dto.getPracticalScore()
					,dto.getAttendancePoint(),dto.getWrittenPoint()
					,dto.getPracticalPoint());
			count++;
			if(count==10) {
				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}


		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*성적 수정을 위해 순차적으로 입력해 주세요 ");
		System.out.println("*정보 입력을 취소하거나 성적정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		System.out.println("*기존의 점수를 유지하려면 E(e)를 입력해 주세요.");
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		System.out.print("성적 번호 : ");
		String examScoreSeq = reader.readLine();
		returnManu(examScoreSeq, teacherNumber);
		checkNumber(examScoreSeq,teacherNumber);
		
		String attendance ="";
		String written ="";
		String practical ="";
		System.out.println("*확인을 위해 정보 재출력합니다.");
		ArrayList<VwFindStudentGradeNotNullDTO> list2 = dao.ScoreNullNotList(teacherNumber,examScoreSeq);  
		for(VwFindStudentGradeNotNullDTO dto :list2) {
			System.out.println(manu);
			String result = String.format("%5s%s\t%10.10s\t\t%3s\t\t%s\t  %s\t   %s\t     %s\t       %s\t%s\n"
					,dto.getExamScoreSeq(),dto.getCourseName()
					,dto.getSubjectName(),dto.getStudentName()
					,dto.getAttendanceScore(),dto.getWrittenscore()
					,dto.getPracticalScore()
					,dto.getAttendancePoint(),dto.getWrittenPoint()
					,dto.getPracticalPoint());
			attendance = dto.getAttendanceScore();
			written = dto.getWrittenscore();
			practical =dto.getPracticalScore();
			System.out.println(result);
		}

		System.out.print("출석 점수 : ");
		String attendanceScore = reader.readLine();
		if ((attendanceScore.toLowerCase()).equals("e")) {
			attendanceScore = attendance;
		} else {
			returnManu(attendanceScore, teacherNumber);
			checkNumber(attendanceScore,teacherNumber);
		}
		
		
		System.out.print("필기 점수 : ");
		String writtenScore = reader.readLine();
		if ((writtenScore.toLowerCase()).equals("e")) {
			writtenScore = written;
		} else {
			returnManu(writtenScore, teacherNumber);
			checkNumber(writtenScore,teacherNumber);
		}
		
		
		System.out.print("실기 점수 : ");
		String practicalScore = reader.readLine();
		if ((practicalScore.toLowerCase()).equals("e")) {
			practicalScore = practical;
		} else {
			returnManu(practicalScore, teacherNumber);
			checkNumber(practicalScore,teacherNumber);
		}
		
		
		TeacherExamScoreDTO score = new TeacherExamScoreDTO();
		
		score.setSeq(examScoreSeq);
		score.setAttendanceScore(attendanceScore);
		score.setWrittenScore(writtenScore);
		score.setPracticalScore(practicalScore);
		
		int result = dao.replace(score);
		
		if (result ==1 ) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*성적이 수정 되었습니다.");
			returnManu(teacherNumber);
		} else {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*성적이 수정이 실패하였습니다.");
			returnManu(teacherNumber);
		}
		
		
	}//scoreReplace
	
	/**
	 * 학생의 성적 조회 메소드입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */

	public void scoreFind(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		String info ="";
		TeacherScoreDAO dao = new TeacherScoreDAO();
		String manu = String.format("[성적번호]\t\t   [과정명]\t\t    [과목명]\t\t\t[교육생명]"
				+ "\t  [출석성적]  [필기성적]  [실기성적]  [출석배점]  [필기배점]  [실기배점]");
		int count = 0; 
		/*
		ArrayList<VwFindStudentGradeNotNullDTO> list = dao.ScoreNullNotList(teacherNumber,info);  
		System.out.println(manu);
		
		for(VwFindStudentGradeNotNullDTO dto :list) {
			System.out.printf("%5s%s\t%10.10s\t\t%3s\t\t%s\t  %s\t   %s\t     %s\t       %s\t%s\n"
					,dto.getExamScoreSeq(),dto.getCourseName()
					,dto.getSubjectName(),dto.getStudentName()
					,dto.getAttendanceScore(),dto.getWrittenscore()
					,dto.getPracticalScore()
					,dto.getAttendancePoint(),dto.getWrittenPoint()
					,dto.getPracticalPoint());
			count++;
			if(count==10) {
				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}		
		*/
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*교육생 명을 입력해 주세요 ");
		System.out.println("*정보 입력을 취소하거나 성적정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		System.out.print("교육생 명 : ");
		String name = reader.readLine();
		returnManu(name, teacherNumber);
		
		info = name;
		ArrayList<VwFindStudentGradeNotNullDTO> list2 = dao.ScoreNullNotList(teacherNumber,info);  
		System.out.println(manu);
		count=0;
		for(VwFindStudentGradeNotNullDTO dto :list2) {
			System.out.printf("%5s%s\t\t%10.10s\t%3s\t\t%s\t  %s\t   %s\t     %s\t       %s\t%s\n"
					,dto.getExamScoreSeq(),dto.getCourseName()
					,dto.getSubjectName(),dto.getStudentName()
					,dto.getAttendanceScore(),dto.getWrittenscore()
					,dto.getPracticalScore()
					,dto.getAttendancePoint(),dto.getWrittenPoint()
					,dto.getPracticalPoint());
			count++;
			if(count==10) {
				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}

		returnManu(teacherNumber);
		
		
	} //scoreFind
	
	
	// 숫자여부 체크 
	private static void checkNumber(String number,int teacherNumber) throws IOException {

		int count=0;
		for (int i = 0; i<number.length() ;i++) {
			if((int)number.charAt(i)>=48 && (int)number.charAt(i)<=57) {
				count ++ ;
			}
		}	
		
		// 0이면 문자열
		if (number.length()==0) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*이전 메뉴로 돌아갑니다.");
			returnManu(teacherNumber);
		} else if (count==0) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*숫자를 잘 못 입력하였습니다.");
			returnManu(teacherNumber);
		}
		
	} //checkNumber
	
	// 뒤도돌아가기
	private static void returnManu(String word, int teacherNumber) throws IOException {
	
		if (word.equals("")) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*이전메뉴로 돌아갑니다.");
			TeacherMain main = new TeacherMain();
			main.teacherScoreManu(teacherNumber);
		}

	}// 뒤도 돌아가기 returnManu

	private static void returnManu(int teacherNumber) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*Enter를 입력하시면 이전 메뉴로 돌아갑니다.");
		String word = reader.readLine();

		while (!word.equals("")) {
			word = reader.readLine();
		}

		if (word.equals("")) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*이전메뉴로 돌아갑니다.");
			TeacherMain main = new TeacherMain();
			main.teacherScoreManu(teacherNumber);
		}

	}// 뒤도 돌아가기 returnManu

}
