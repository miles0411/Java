
/**
 * @category JAVA Homework 2-1
 * @author Preston Lin
 * @date Sep 11,2013
 * @note To use BigInterger Class calculate from 2! through 50!, and print out the result.
 */

import java.math.BigInteger;

public class Factorials {
	public static void main(String args[]) {	// Calculate factorials with an int	as instructed in the assignment. 
		long factorial = 1;
		for (int i = 2; i <= 40; i++) {
			factorial *= i;
		System.out.println(i + "! = " + factorial);
		}
	System.out.println("Now try it with BigIntegers");	// Calculate factorials with a BigInteger as follows as required in the assignment
	
	
	BigInteger bigfactorial = new BigInteger("1"); // new a variable named bigfactorial and assign value 1 to the constructor because the first number of a factorial should be 1.
	
	for (int i = 2; i <= 50; i++) { 
	bigfactorial = bigfactorial.multiply(BigInteger.valueOf(i)); //use BigInteger method to Multiply i from 2 through 50, valueOf(i) method is required for take in the loop parameter i 
	System.out.println(i + "! = " + bigfactorial); //printout each factorial calculation result from 2 through 50
		}
	
	}	
}

