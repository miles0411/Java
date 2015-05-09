
public class Variable extends Node{
	
	private String [] variable ={"X0", "X1", "X2"};
	private int index;
	Variable(int i){
		index = i;
	}								 //this construct take an integer for indentifying the variable name and the value it is in a input data set

	 public String toString(){
	    	return variable[index];  //get the value stated in String for further printout
	    }
	
	public double eval(double[] data) {
			value = data[index];
			return value;			 //when this method called return the value for the varialbe
		}
		
}
