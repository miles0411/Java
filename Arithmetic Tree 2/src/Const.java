
public class Const extends Node {  //create a const class to inherant freatures from node
    
	public Const(double d) { 
    value = d; 	      //write a method to input the randomly determined constant
    }
    
    public String toString(){
    	return String.valueOf(value);  //get the value stated in String for further printout
    }
    
	public double eval(double[] date) {	 //refer to Plus class for similar comments
		return value;
	}
	
}