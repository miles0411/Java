import java.util.*;

/**
 * @category JAVA Homework 5-2
 * @author Preston Lin
 * @date Oct 24,2013
 * @note Create a binary tree composed of nodes defined by the depth. 
 *		 Calculate an random formatted equation and print out the equation and the result.
 */

public class TestAlgebra {
	
		static int numIndepvars =3;   		//number of Indepvars is determined by the length of the data set array
		static int maxDepth  =5;      		//number of maxDepth is set as instructed
		static Random rand = new Random(); //new a random class defined in JAVA library for future random selection purpose
		static double [] data1 = {3.14, 2.67, 3.0};	//This data set hold 3 values 
		
		public static void main(String[] args) {
		Node [] ops = {new Plus(), new Minus(), new Mult(), new Divide()};  //Node array holds 4 instances of operators class
		OperatorFactory o = new OperatorFactory(ops);  // new out the OperatorFactory for calling its methods
		TerminalFactory t = new TerminalFactory(numIndepvars); //new out the TerminalFActory for calling its methods
		Node root = o.getOperator(rand);  			//refer Node root to the location of returned Operator class 		
		root.addRandomkids(o, t, maxDepth, rand);   //call the addRandomkids method to grow the trees by adding nodes
		String s = root.toString();					
		System.out.println(s+"="+root.eval(data1)); //print out the equation held in the node and give the value
		
		}
}




