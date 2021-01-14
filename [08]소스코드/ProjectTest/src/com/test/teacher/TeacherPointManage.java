package com.test.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.test.dao.TeacherPointDAO;
import com.test.dto.TeacherPointDTO;
import com.test.dto.vwFindCourseSubjectDTO;
/**
 * 배점 및 시험정보관리 클래스 입니다.
 * @author 이대홍
 */

public class TeacherPointManage {
	
	/**
	 * 배점 신규입력 메소드 입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */

	public void pointAdd(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		TeacherPointDAO dao = new TeacherPointDAO();

		String info = "";
		System.out.println("*진행 중이거나 진행 예정인 과목을 보여줍니다.");
		ArrayList<vwFindCourseSubjectDTO> list = dao.vwFindCourseSubjectList(teacherNumber, info);
		String manu = String
				.format("[과목번호]\t\t\t[과정명]\t\t\t[과목명]\t\t[시작일]\t\t  " 
		+ "[종료일]    [출석배점]  [필기배점] [실기배점] [시험문제등록]"); // 과목번호
																													// =
																													// 개설과목번호

		System.out.println(manu);

		int count = 0;
		for (vwFindCourseSubjectDTO dto : list) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
					dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEndDate(),
					dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

			count++;
			if (count == 10) {

				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}

		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*배점 최초 입력을 시작합니다.기존입력 수정은 배점수정메뉴를 이용해 주세요.");
		System.out.println("*정보 입력을 취소하거나 배점 및 시험 정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("개설과목 번호 : ");
		String openSubjectSeq = reader.readLine();
		returnManu(openSubjectSeq, teacherNumber);
		checkNumber(openSubjectSeq, teacherNumber);

		String subjectName = "";
		info = openSubjectSeq;
		System.out.println("*입력할 과목 정보가 맞는지 확인하세요.");

		ArrayList<vwFindCourseSubjectDTO> list2 = dao.vwFindCourseSubjectList(teacherNumber, info);
		System.out.println(manu);

		for (vwFindCourseSubjectDTO dto : list2) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n"
					, dto.getOpenSubjectSeq(),dto.getCourseName(), dto.getSubjectName(),
					dto.getStartdate(), dto.getEndDate(),dto.getAttendancePoint(),
					dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

		}


		System.out.println("*출석배점은 최소 20점 이상입니다.");
		System.out.print("출석 배점: ");
		String attendancePoint = reader.readLine();
		returnManu(attendancePoint, teacherNumber);
		checkNumber(attendancePoint, teacherNumber);

		System.out.print("필기 배점 : ");
		String wittenPoint = reader.readLine();
		returnManu(wittenPoint, teacherNumber);
		checkNumber(wittenPoint, teacherNumber);

		System.out.print("실기 배점 : ");
		String practicalPoint = reader.readLine();
		returnManu(practicalPoint, teacherNumber);
		checkNumber(practicalPoint, teacherNumber);

		TeacherPointDTO pointAdd = new TeacherPointDTO();
		pointAdd.setSeq(openSubjectSeq);
		pointAdd.setAttendancePoint(attendancePoint);
		pointAdd.setWrittenPoint(wittenPoint);
		pointAdd.setPracticalPoint(practicalPoint);

		int result = dao.pointAdd(pointAdd);

		if (result == 1) {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("배점이 입력 되었습니다.");
			System.out.println(subjectName + " 과목 배점이 입력되었습니다.");
			returnManu(teacherNumber);
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("기존에 배점이 입력되어 있습니다.\n " + "배점 수정 메뉴를 이용해 주세요.");
			returnManu(teacherNumber);
		}
	}// pointAdd
	
	/**
	 * 배점수정 메소드 입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */

	// 배점 수정.
	public void pointReplace(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		String info = "";
		TeacherPointDAO dao = new TeacherPointDAO();
		
		ArrayList<vwFindCourseSubjectDTO> list = dao.vwFindCourseSubjectList(teacherNumber, info);
		String manu = String
				.format("[과목번호]\t\t\t[과정명]\t\t\t[과목명]\t\t[시작일]\t\t  " 
		+ "[종료일]    [출석배점]  [필기배점] [실기배점] [시험문제등록]"); // 과목번호
																												// 개설과목번호

		System.out.println(manu);

		int count = 0;
		for (vwFindCourseSubjectDTO dto : list) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", 
					dto.getOpenSubjectSeq(),dto.getCourseName(), dto.getSubjectName(), 
					dto.getStartdate(), dto.getEndDate(),dto.getAttendancePoint(), 
					dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

			count++;
			if (count == 10) {

				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}

		
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*배점 수정을 위해 순차적으로 입력해 주세요.");
		System.out.println("*정보 입력을 취소하거나 배점 및 시험 정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.print("개설과목 번호 : ");
		String openSubjectSeq = reader.readLine();
		returnManu(openSubjectSeq, teacherNumber);
		checkNumber(openSubjectSeq, teacherNumber);

		System.out.println("*수정할 정보가 맞는지 확인하세요.");
		info = openSubjectSeq;
		String subjectName = "";
		ArrayList<vwFindCourseSubjectDTO> list2 = dao.vwFindCourseSubjectList(teacherNumber, info);	

		System.out.println(manu);

		for (vwFindCourseSubjectDTO dto : list2) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n",
					dto.getOpenSubjectSeq(),dto.getCourseName(), dto.getSubjectName(),
					dto.getStartdate(), dto.getEndDate(),dto.getAttendancePoint(),
					dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());
			subjectName = dto.getCourseName();
		}


		System.out.println("출석배점은 최소 20점 이상입니다.");
		System.out.print("출석 배점: ");
		String attendancePoint = reader.readLine();
		returnManu(attendancePoint, teacherNumber);
		checkNumber(attendancePoint, teacherNumber);

		System.out.print("필기 배점 : ");
		String wittenPoint = reader.readLine();
		returnManu(wittenPoint, teacherNumber);
		checkNumber(wittenPoint, teacherNumber);

		System.out.print("실기 배점 : ");
		String practicalPoint = reader.readLine();
		returnManu(practicalPoint, teacherNumber);
		checkNumber(practicalPoint, teacherNumber);

		TeacherPointDTO pointReplace = new TeacherPointDTO();
		pointReplace.setSeq(openSubjectSeq);
		pointReplace.setAttendancePoint(attendancePoint);
		pointReplace.setWrittenPoint(wittenPoint);
		pointReplace.setPracticalPoint(practicalPoint);

		int result = dao.pointReplace(pointReplace);

		if (result == 1) {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(subjectName + " 과목 배점이 수정 되었습니다.");
			System.out.println();
			returnManu(teacherNumber);
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*배점 수정이 완료되지 않았습니다. 배점 입력 메뉴로 돌아갑니다.");
			returnManu(teacherNumber);
		}

	} // pointReplace
	
	/**
	 * 배점 조회 메소드 입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */

	// 배점 조회
	public void pointFind(int teacherNumber) throws IOException {

		String info = "조회";
		TeacherPointDAO dao = new TeacherPointDAO();
		System.out.println("배점 조회 화면입니다.");
		ArrayList<vwFindCourseSubjectDTO> list = dao.vwFindCourseSubjectList(teacherNumber, info);
		String manu = String
				.format("[과목번호]\t\t\t[과정명]\t\t\t[과목명]\t\t[시작일]\t\t  " + "[종료일]    [출석배점]  [필기배점] [실기배점] [시험문제등록]"); // 과목번호
																													// =
																													// 개설과목번호

		System.out.println(manu);

		int count = 0;
		for (vwFindCourseSubjectDTO dto : list) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
					dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEndDate(),
					dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

			count++;
			if (count == 10) {

				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		returnManu(teacherNumber);

	}// pointReplace
	
	/**
	 * 시험정보 입력 메소드 입니다.
	 * @param teacherNumber 교사번호
	 * @throws IOException Bufferedreader
	 */

	// 최초 시험정보 입력 등록.
	public void whetherExamAdd(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub
		System.out.println();
		String info = "";
		TeacherPointDAO dao = new TeacherPointDAO();
		ArrayList<vwFindCourseSubjectDTO> list = dao.vwFindCourseSubjectList(teacherNumber, info);
		String manu = String
				.format("[과목번호]\t\t\t[과정명]\t\t\t[과목명]\t\t[시작일]\t\t  " 
		+ "[종료일]    [출석배점]  [필기배점] [실기배점] [시험문제등록]"); // 과목번호

		System.out.println(manu);

		int count = 0;
		for (vwFindCourseSubjectDTO dto : list) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
					dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEndDate(),
					dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

			count++;
			if (count == 10) {

				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*시험문제 등록 여부의 최초 입력을 시작합니다.\n기존입력 수정은 시험문제 등록여부 수정메뉴를 이용해 주세요.");
		System.out.println("*정보 입력을 취소하거나 배점 및 시험 정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		System.out.print("개설 과목 번호 : ");
		String seq = reader.readLine();
		returnManu(seq, teacherNumber);

		System.out.println("*선택 정보가 맞는지 확인하세요.");
		info = seq;
		ArrayList<vwFindCourseSubjectDTO> list2 = dao.vwFindCourseSubjectList(teacherNumber, info);
		System.out.println(manu);

		for (vwFindCourseSubjectDTO dto : list2) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n",
					dto.getOpenSubjectSeq(),dto.getCourseName(), dto.getSubjectName(), 
					dto.getStartdate(), dto.getEndDate(), dto.getAttendancePoint(), 
					dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());
		}

		System.out.print("등록여부 입력(등록, 미등록) : ");
		String add = reader.readLine();
		returnManu(add, teacherNumber);
		

		vwFindCourseSubjectDTO dto = new vwFindCourseSubjectDTO();

		dto.setOpenSubjectSeq(seq);
		dto.setWhetherExam(add);

		int result = dao.whetherExamAdd(dto);

		if (result == 1) {
			System.out.println("*등록여부가 입력되었습니다.");
			returnManu(teacherNumber);
		} else {
			System.out.println("*등록여부를 다시 입력해 주세요.");
			returnManu(teacherNumber);
		}

	} // whetherExamAdd

	
	/**
	 * 시험정보 수정 메소드 입니다.
	 * @param  teacherNumber 교사번호
	 * @throws IOException BUfferedReader
	 */
	public void whetherExamReplace(int teacherNumber) throws IOException {
		// TODO Auto-generated method stub

		String info = "";
		System.out.println("시험등록 여부 수정");
		TeacherPointDAO dao = new TeacherPointDAO();
		ArrayList<vwFindCourseSubjectDTO> list = dao.vwFindCourseSubjectList(teacherNumber, info);
		String manu = String
				.format("[과목번호]\t\t\t[과정명]\t\t\t[과목명]\t\t[시작일]\t\t  " 
		+ "[종료일]    [출석배점]  [필기배점] [실기배점] [시험문제등록]"); // 과목번호

		System.out.println(manu);

		int count = 0;
		for (vwFindCourseSubjectDTO dto : list) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
					dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEndDate(),
					dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());

			count++;
			if (count == 10) {

				System.out.println();
				System.out.println(manu);
				count = 0;
			}
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*시험문제 등록여부를 수정합니다.");
		System.out.println("*정보 입력을 취소하거나 배점 및 시험 정보관리 메뉴로 돌아가려면 Enter를 입력해 주세요.");
		System.out.print("개설 과목 번호 : ");
		String seq = reader.readLine();

		System.out.println();
		System.out.println("*선택 정보가 맞는지 확인하세요.");
		info = seq;
		ArrayList<vwFindCourseSubjectDTO> list2 = dao.vwFindCourseSubjectList(teacherNumber, info);

		System.out.println(manu);

		for (vwFindCourseSubjectDTO dto : list2) {
			System.out.printf("%5s\t%s\t%10.10s\t%-10.10s\t%10s \t%-3s\t%-3s\t%-3s\t%-3s \n", dto.getOpenSubjectSeq(),
					dto.getCourseName(), dto.getSubjectName(), dto.getStartdate(), dto.getEndDate(),
					dto.getAttendancePoint(), dto.getWrittenPoint(), dto.getPracticalPoint(), dto.getWhetherExam());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*맞으시면 1을 입력해 주세요.");
		System.out.println("*다른 입력시 배점 및 시험정보관리 메뉴로 돌아갑니다.");
		String check = reader.readLine();

		if (check.equals("1")) {
			vwFindCourseSubjectDTO dto = new vwFindCourseSubjectDTO();

			dto.setOpenSubjectSeq(seq);

			int result = dao.whetherExamReplace(dto);

			if (result == 1) {
				System.out.println("*시험문제 등록 여부 수정 되었습니다.");
			} else {
				System.out.println("*잘못입력하셨습니다.");
			}
				returnManu(teacherNumber);
			
		} else {
			returnManu(teacherNumber);
		}

	}// whetherExamReplace
		// 숫자여부 체크

	
	/**
	 * 입력정보의 숫자여부체크 메소드입니다.
	 * @param 확인할 숫자(number),교사번호(teacherSeq)
	 */
	private static void checkNumber(String number, int teacherNumber) throws IOException {

		int count = 0;
		for (int i = 0; i < number.length(); i++) {
			if ((int) number.charAt(i) >= 48 && (int) number.charAt(i) <= 57) {
				count++;
			}
		}

		// 0이면 문자열
		if (number.length() == 0) {
			System.out.println("*이전메뉴로 돌아갑니다.");
			returnManu(teacherNumber);
		} else if (!(count == number.length())) {
			System.out.println("*숫자를 잘 못 입력하였습니다.");
			returnManu(teacherNumber);
		}

	} // checkNumber

	/**
	 * 배점 및 시험정보관리로 되돌아갈 메소드입니다.
	 * @param 사용자 되돌아감 입력(word),교사번호(teacherSeq)
	 * @return teacherSeq
	 */
	// 뒤도돌아가기
	private static void returnManu(String word, int teacherNumber) throws IOException {
	
		if (word.equals("")) {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("*이전메뉴로 돌아갑니다.");
			TeacherMain main = new TeacherMain();
			main.teacherPointManu(teacherNumber);
		}

	}// 뒤도 돌아가기 returnManu
	
	/**
	 * 배점 및 시험정보관리로 되돌아갈 메소드입니다.
	 * @param 교사번호(teacherSeq)
	 * @return teacherSeq
	 */


	private static void returnManu(int teacherNumber) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("*Enter를 입력하시면 이전 메뉴로 돌아갑니다.");
		String word = reader.readLine();

		while (!word.equals("")) {
			word = reader.readLine();
		}

		if (word.equals("")) {
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("*이전메뉴로 돌아갑니다.");
			TeacherMain main = new TeacherMain();
			main.teacherPointManu(teacherNumber);
		}

	}// 뒤도 돌아가기 returnManu

} // class
