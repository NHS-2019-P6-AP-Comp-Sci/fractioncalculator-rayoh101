
package fracCalc;

import java.util.Scanner;

public class FracCalc  {
	
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter fraction problem: ");
		String userResponse = s.nextLine();
		while(!userResponse.equalsIgnoreCase("quit")) {
			System.out.println(produceAnswer(userResponse));
			System.out.print("Enter a fraction problem: ");
			userResponse = s.nextLine();
		}
	//asks for fraction problem and continues to ask as long as response isn't "quit"	
		
	}
	public static String produceAnswer(String input) {
		
		String temp = input;
		String operand1 = temp.substring(0, temp.indexOf(' '));
		temp = temp.substring(temp.indexOf(' ') + 1);
		String operator = temp.substring(0, temp.indexOf(' '));
		temp = temp.substring(temp.indexOf(' ') + 1);
		String operand2 = temp;
		//parsing input into 3 pieces
		String op2Whole = findWhole(operand2);
		String op2Num = findNum(operand2);
		String op2Denom = findDenom(operand2);
		
		String chk2Answer = "whole:" + op2Whole + " numerator:" + op2Num + " denominator:" + op2Denom;
		
		return chk2Answer;
		
	}
	
	public static String findWhole(String str) {
		//mixed number
		if (str.contains("_")) {
			return str.substring(0, str.indexOf('_'));
		}
		//just a fraction
		else if(str.contains("/")) {
			return "0";
		}
		//just a whole number
		else 
			return str;
	}
	
	public static String findNum(String str) {
		if(str.contains("_")) {
			return str.substring(str.indexOf('_') + 1, str.indexOf('/'));
		}
		else if (str.contains("/")) {
			return str.substring(0, str.indexOf('/'));
		}
		else {
			return "0";
		}
	}
	
	public static String findDenom(String str) {
		if(str.contains("/")) {
			return str.substring(str.indexOf("/") + 1);
		}
		else {
			return "1";
		}
	}
}

