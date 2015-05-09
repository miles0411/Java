import java.util.*;

public class Plus extends Binop{

	Plus(){}
	Plus(Node l, Node r){
		lChild=l;
		rChild=r;				//take in the l and r child for further calculation
	}
	public double eval(double[] data){
		
		return lChild.eval(data)+rChild.eval(data);    //return the result for calculation when this method is called
	}
	
	public String toString(){
		return "("+String.valueOf(lChild)+"+"+String.valueOf(rChild)+")";  //return the entire equation
	}
}
