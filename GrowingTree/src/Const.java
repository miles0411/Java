import java.util.*;

public class Const extends Node {  //create a const class to inherant freatures from node
    
	public Const() {
	while(value==0)
    value = Math.random();    //write a method to input the randomly determined constant
    }
	public void addRandomkids(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand){}
    public String toString(){
    	return String.valueOf(value);  //get the value stated in String for further printout
    }
    
	public double eval(double[] date) {	 //refer to Plus class for similar comments
		return value;
	}
	
}