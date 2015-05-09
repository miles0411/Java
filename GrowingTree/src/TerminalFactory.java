import java.util.*;

public class TerminalFactory {
	private int numIndepvars;
	TerminalFactory(int n){
		numIndepvars = n;			//constructor to intake the defined number of independent variable.
	}
    public Node getTerminal(Random rand){
    	int i;
    	i=rand.nextInt(numIndepvars+1); //generate a random number as instructed to determine the terminal node is a variable or a constant
    	if(i<numIndepvars){				//if the condition is true, return a variable instance and holding an index for determine the value in a data set
    		Node var = new Variable(i);
    		return var;
    	}
    	else{
    		Node constant = new Const();//else return an instance of Const class
    		return constant;
    	}
    	
    }
    public int getNumIndepVars(){   //returned the value of member variable when it's called.
    	return numIndepvars;
    	
    }
}
