package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
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
        int totalSpaces = 0;
        int spaceone = -1;
        int spacetwo = -1;
        int spacethree = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                totalSpaces++;
                if (totalSpaces == 1) {
                	spaceone = i;
                }
                if (totalSpaces == 2) {
                	spacetwo = i;
                }
                if (totalSpaces == 3) {
                    spacethree = i;
                }
            }
        }
        if (totalSpaces == 2) {
            return Operation(input, spaceone, spacetwo, input.length());
        } else {
            return "ERROR: Input is in an invalid format.";
        }
    }
    // returns the answer to equation in the form of a string

    public static String Operation(String input, int spaceone, int spacetwo, int spacethree) {
        String result = "";
        String operation = input.substring(spaceone + 1, spacetwo);
        String firstNumber = input.substring(0, spaceone);
        String secondNumber = input.substring(spacetwo + 1, spacethree);
        if (firstNumber.indexOf('_') != -1) {
            firstNumber = improperFractions(firstNumber);
        }
        if (secondNumber.indexOf('_') != -1) {
            secondNumber = improperFractions(secondNumber);
        }
        if (input.charAt(spaceone + 1) == '+') {
            return (addOrSubtract(firstNumber, secondNumber, true));
        }
        if (input.charAt(spaceone + 1) == '-') {
            return (addOrSubtract(firstNumber, secondNumber, false));
        }
        if (input.charAt(spaceone + 1) == '/') {
            return (multiplyOrDivide(firstNumber, secondNumber, false));
        }
        if (input.charAt(spaceone + 1) == '*') {
            return (multiplyOrDivide(firstNumber, secondNumber, true));
        }
        return "FAILED";

    }
    // computes the operation

    public static String addOrSubtract(String num1, String num2, boolean addOrSubtract) {
        int slashIndex1 = num1.indexOf('/');
        int slashIndex2 = num2.indexOf('/');
        if (slashIndex1 == -1) {
            num1 += "/1";
            slashIndex1 = num1.length() - 2;
        }
        if (slashIndex2 == -1) {
            num2 += "/1";
            slashIndex2 = num2.length() - 2;
        }
        int numerator1 = Integer.parseInt(num1.substring(0, slashIndex1));
        int numerator2 = Integer.parseInt(num2.substring(0, slashIndex2));
        int denominator1 = Integer.parseInt(num1.substring(slashIndex1 + 1, num1.length()));
        int denominator2 = Integer.parseInt(num2.substring(slashIndex2 + 1, num2.length()));
        int newDenominator = denominator1 * denominator2;
        int newNumerator = 0;
        if (addOrSubtract) {
            newNumerator = (numerator1 * newDenominator / denominator1) + (numerator2 * newDenominator / denominator2);
        } else {
            newNumerator = (numerator1 * newDenominator / denominator1) - (numerator2 * newDenominator / denominator2);
        }
        return simplify(newNumerator, newDenominator);
    }
    //performs addition or subtraction
    
    public static String multiplyOrDivide(String num1, String num2, boolean multiplyOrDivide) {
        int slashIndex1 = num1.indexOf('/');
        int slashIndex2 = num2.indexOf('/');
        if (slashIndex1 == -1) {
            num1 += "/1";
            slashIndex1 = num1.length() - 2;
        }
        if (slashIndex2 == -1) {
            num2 += "/1";
            slashIndex2 = num2.length() - 2;
        }
        int numerator1 = Integer.parseInt(num1.substring(0, slashIndex1));
        int numerator2 = Integer.parseInt(num2.substring(0, slashIndex2));
        int denominator1 = Integer.parseInt(num1.substring(slashIndex1 + 1, num1.length()));
        int denominator2 = Integer.parseInt(num2.substring(slashIndex2 + 1, num2.length()));
        int newNumerator = 0;
        int newDenominator = 0;
        if (multiplyOrDivide) {
            newNumerator = numerator1 * numerator2;
            newDenominator = denominator1 * denominator2;
        } else {
            newNumerator = numerator1 * denominator2;
            newDenominator = numerator2 * denominator1;
        }
        return simplify(newNumerator, newDenominator);
    }
    // performs multiplication or division


    public static String improperFractions(String input) {
        int underscoreIndex = input.indexOf('_');
        int slashIndex = input.indexOf('/');
        int wholeNum = Integer.parseInt(input.substring(0, underscoreIndex));
        int numerator = Integer.parseInt(input.substring(underscoreIndex + 1, slashIndex));
        int denom = Integer.parseInt(input.substring(slashIndex + 1, input.length()));
        if (wholeNum < 0) {
            return wholeNum * denom - numerator + "/" + denom;
        }
        return wholeNum * denom + numerator + "/" + denom;
    }
    // converts mixed and proper fractions to improper fractions

    public static boolean isNumber(String input) {
    	int totalSlashes = 0;
    	int totalUnderScores = 0;
    	for (int i = 0 ; i < input.length(); i++) {
    		char current = input.charAt(i);
    		if (current != '/') {
    			if (current != '_') {
    				if (current != '-') {
    					if (!Character.isDigit(current)) {
    						return false;
    					}
    				}
    				else {
    					if (i != 0) {
    						return false;
    					}
    				}
    			}
    			else {
    				totalUnderScores++;
    				if (totalUnderScores > 1) {
    					return false;
    				}
    				else {
    					if (totalSlashes != 0 || !Character.isDigit(input.charAt(i-1)) || !Character.isDigit(input.charAt(i+1))) {
    						return false;
    					}
    				}
    			}
    		}
    		else {
    			totalSlashes++;
    			if (totalSlashes > 1 || !Character.isDigit(input.charAt(i-1)) || !Character.isDigit(input.charAt(i+1))) {
    				return false;
    			}
    		}
    	}
    	return true;
    	
        //returns a boolean if the string is in the correct format 
    }
    public static boolean isOperation(String operation) {
        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
            return true;
        }
        return false;
        
        // returns a boolean if string is valid operation
    }
    
    public static String simplify(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        int newDenominator = denominator / gcd;
        int newNumerator = numerator / gcd;
        if (newDenominator < 0) {
            newDenominator *= -1;
            newNumerator *= -1;
        }
        if (newDenominator == 1) {
            return "" + newNumerator;
        }
        if (Math.abs(newDenominator) < Math.abs(newNumerator)) {
            if (newNumerator < 0) {
                newNumerator *= -1;
                return "-" + newNumerator / newDenominator + "_" + newNumerator % newDenominator + "/" + newDenominator;
            } else {
                return newNumerator / newDenominator + "_" + newNumerator % newDenominator + "/" + newDenominator;
            }
        }
        return newNumerator + "/" + newDenominator;
    }
    // simplify the fraction
    
    
    public static int lcm(int num1, int num2) {
        return (num1 * num2) / gcd(num1, num2);
    }
    // finds the LCM of the numbers
    
    public static int gcd(int num1, int num2) {
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        if (num1 == 0 || num2 == 0) {
            return num1 + num2;
        }
        return gcd(num2, num1 % num2);
    }
    // finds the GCD of numbers

}