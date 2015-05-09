import java.util.*;

public class Variable extends Node{
	
	public void addRandomkids(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand){}
	private String [] variable ={"X0", "X1", "X2"};
	private int index, order;
	//static double [] data1 = {1, 2, 3};
	//static double [] data2 = {4, 5, 6};
	Variable(int i){
		index = i;
	}

	 public String toString(){
	    	return variable[index];  //get the value stated in String for further printout
	    }
	
	public double eval(double[] data) {
			value = data[index];
			return value;
		}
		
}
