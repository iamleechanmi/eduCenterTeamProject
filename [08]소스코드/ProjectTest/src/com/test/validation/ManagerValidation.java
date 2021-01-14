package com.test.validation;

public class ManagerValidation {
	
	public static boolean teacherName(String name) {
		
		if(name.length()<=5) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean teacherSsn(String ssn) {
		
		if(ssn.length() == 7) {
			return true;
		}else {
			return false;
		}
		
	}

	public static boolean teacherTel(String tel) {
		
		if(tel.length() <= 11 && tel.length() >= 10) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean studygroupGoal(String goal) {
		
		if(goal.length() <= 30) {
			return true;
		}else {
			return false;
		}
		
	}

}
