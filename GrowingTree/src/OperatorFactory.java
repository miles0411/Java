import java.util.*;

public class OperatorFactory {
	
	private int numOperators;      //determined by the number of operators
	private Node[] currentOps;	   //a Node type array member variable will catch the input Node array
	OperatorFactory(Node[] n){     //construct for intake the Node operators array
		currentOps=n;
		numOperators = n.length;   // this number is determiend by the length of the node array; there is 4 operators. 
	}
	public Node getOperator(Random rand){
		int n = rand.nextInt(4);	//doing random determination to pick up one Operator Node of the 4 in the array
		return (Node)currentOps[n].clone();	 //return a copy of the picked operator class 
		
	}
	public int getNumOps(){
		return numOperators ;      //return the number of opertors when it's called
	}
}
