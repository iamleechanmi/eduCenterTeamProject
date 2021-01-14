package com.test.main;

import java.util.Scanner;

import com.test.manager.ManagerMain;
import com.test.student.StudentMain;
import com.test.teacher.TeacherMain;


public class Main {
	
	public static void main(String[] args) {
		
		mainMenu();
		
	}
	
	/**
	 * 
	 * @author 이진혁
	 * 프로그램 초기화면입니다.
	 * 번호선택을 통해 관리자, 교사, 교육생, 프로그램 종료를 수행합니다.
	 *
	 */
	public static void mainMenu() {
		
	Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("\r\n"
					+ "    :: ,:~ ;;   .;*$$#$=!~  .::::::::::.  .;;!**!;~    ~;;~ .;;:; -;;;;;;: ~;.  \r\n"
					+ "    ##!!#* ##   :#*.   -##. ,##########, .##=:-~!##!   $##$,~#$=#.:######$ *#,  \r\n"
					+ "   :#####$-##*~ :#=:~~::#$.          =#, ,#=-...,;#*  ~#!*#:=#$=#.:#*      *#,  \r\n"
					+ "   *#!##~#!##!~  -#######.           =#,  ~#$$$$$#!   ;#: #$-#$=#.:#*      *#,  \r\n"
					+ "  .#=-$=.=###  .~~##~~:##~~          =#, ,--~;!;:---. $#  !#*#$=#.:#=:::;:~=#,  \r\n"
					+ "  ~*,,;~ ,;##  ,###########   !*,.** =#,.=$$$$$$$$$$~:$!  -$$#$=#.:#$;;;;::$#,  \r\n"
					+ "    ,:$####!,    ,:=##$!-.    =$-.## =#, ,,!#;,,=$~,        .~~!* :#*      *#,  \r\n"
					+ "   ,$#:---:#$,  :#=:--~!#=.   =$-.## !*, ~!=#=!!$#*;  ~*:         :#*      *#,  \r\n"
					+ "   :#!     ;#!  $#-     $#-   =$-.##     .-------:#*  :#;         :#*..... *#,  \r\n"
					+ "   ,$$;;:;;#$.  ;#=;:~:;#$. ,-=$:~##---,         -#*  :#!-------- :######$ *#,  \r\n"
					+ "     ;$###$;     ,=####$!   =##########=         -#*  :##########          *#,  \r\n"
					+ "                                                  ..                            \r\n"
					+ "");
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\t\t\t계정 선택");
			System.out.println("-----------------------------------------------------------");
			System.out.println();
			System.out.println("* 로그인할 계정을 선택해주세요");
			System.out.println();
			System.out.println("1. 관리자");
			System.out.println("2. 교사");
			System.out.println("3. 교육생");
			System.out.println("4. 종료");
			System.out.println();
			System.out.println("-----------------------------------------------------------");
			System.out.print("번호 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				//관리자 로그인으로 이동
				loop = false;
				ManagerMain.managerLogin();
			}else if(sel.equals("2")) {
				//교사 로그인으로 이동
				loop = false;
				com.test.teacher.TeacherMain teacherMain =new com.test.teacher.TeacherMain();
				teacherMain.login();
			}else if(sel.equals("3"))	{
				StudentMain.studentLogin();
			}else if(sel.equals("4"))	{
				//시스템 종료
				loop = false;
			}else {
				//잘 못 입력한 경우
				System.out.println("-----------------------------------------------------------");			
				System.out.println("***번호를 잘못 입력하셨습니다.");
				System.out.println("-----------------------------------------------------------");
			}
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("프로그램을 종료합니다.");
	}

}
