
/**
 * @category JAVA Homework 3-1
 * @author Preston Lin
 * @date Sep 18,2013
 * @note To calculate fractions and print out in fraction format/decimal format
 */

public class TestFraction {
	public static void main(String[] args) {
	Fraction f1 = new Fraction();      	//new an object named f1
	Fraction f2 = new Fraction(1, 3);   //new an object named f2
	Fraction f3 = new Fraction(3, 6);   //new an object named f3
	System.out.println("f1 = " + f1);   //print out f1~f3 in fraction format
	System.out.println("f2 = " + f2);
	System.out.println("f3 = " + f3);
	System.out.println("f1 + f2 = " + 
	f1.add(f2));						//print out f1+f2 in fraction format
	System.out.println("f2 in decimal is: " + 
	f2.toDecimal());					//print out f2 in decimal format
	}
}
