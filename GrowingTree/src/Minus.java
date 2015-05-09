import java.util.*;

public class Minus extends Binop{     //refer to Plus class for similar comments

	Minus(){}
	Minus(Node l, Node r){
		lChild=l;
		rChild=r;
	}
	public double eval(double[] data){
		
		return lChild.eval(data)-rChild.eval(data);
	}
	
	public String toString(){
		return "("+String.valueOf(lChild)+"-"+String.valueOf(rChild)+")";
	}
	
}
