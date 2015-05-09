import java.util.*;

/**
 * @category JAVA Homework 5-1
 * @author Preston Lin
 * @date Oct 24,2013
 * @note Create a binary tree composed of nodes. Calculate an random formatted equation and print out the equation and the result.
 *  	 Nodes can hold either a variable or a constant
 */

public class TestArithmetic {
	
		static Node[] Terminal = new Node[4];     //new a Node type array for carry produced Nodes
		static double [] data1 = {1, 2, 3};       //create data array as instructed
		static double [] data2 = {4, 5, 6}; 
		static int  i=0, l=0;					  //create member variable for calculating time for doing the main method
		public static void main(String[] args) {
		Node n1, n2, n3, n4, n5;				  //new 5 Node type variable for future use
		while(i<2){
			for(int j=0;j<4;j++){				 //continuous create 4 nodes carrying variable or constant based on the flip coin probability
			double k = (Math.random());
			if(k<0.5){						     //1/2 prob to have a node carrying constant class
				n1 = setConstant();
				Terminal[j] = n1;
			}
			else{
				n2 = setVariable();				 // 1/2 prob to have a node carrying variable class
				Terminal[j] = n2;
			}
			}
			n3 = randOperator(Terminal[0], Terminal[1]);	//new a randomly determined Node holding operator having lchild anr rchild
			n4 = randOperator(Terminal[2], Terminal[3]);    
			n5 = randOperator(n3, n4);					
			if(i==0){
				System.out.println(n5+" = "+n5.eval(data1));//if it's the first time run, use data set 1
				l++;
			}
			else{
				System.out.println(n5+" = "+n5.eval(data2));//if it's the first time run, use data set 2 
			} 
			i++;
			}
		}
	

		public static Node setConstant() {	
			double constant;
			constant = (double) (Math.random() * 20 + 1);
			Node n = new Const(constant);
			return n;		// this method is to randomly determine a Node class holding a constant from 1~20
			
		}
		
		public static Node setVariable(){
		int index = (int)(Math.random()*2);
		Node n = new  Variable(index);
		return n;		   // this method is to randomly determine a Node class holding a variable holding a parameter for determining the index of input data set
		
		}
		
		public static Node randOperator(Node a, Node b) {
			char[] OperatorArray = { '+', '-', '*', '/' };
			char operator;
			operator = OperatorArray[(int) (Math.random() * 4)];
			switch(operator){
			case '+': Node n1 = new Plus(a, b);return n1;
			case '-': Node n2 = new Minus(a, b);return n2;
			case '*': Node n3 = new Mult(a, b);return n3;
			case '/': Node n4 = new Divide(a, b);return n4;
			}						//this method is to randomly determine an operator class used and return the instances when it's called
			return null;
}
}




