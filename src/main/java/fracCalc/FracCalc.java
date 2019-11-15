/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.Scanner;

import org.junit.Test;

public class FracCalc {

    	public static void main(String[] args) {
    		Scanner s = new Scanner(System.in);
    		System.out.println("Enter fraction problem: ");
    		String userResponse = s.nextLine();
    		System.out.println(produceAnswer(userResponse));
    		
    	}
 

    public static String produceAnswer(String input)
    {
		Scanner s = new Scanner(input);
		String operand1 = s.next();
		String operator = s.next();
		String operand2 = s.next();
		
		return operand2;
    }


}
