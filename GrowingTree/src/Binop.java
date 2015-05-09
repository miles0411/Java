import java.util.*;

public abstract  class Binop extends Node {
	
	protected Node lChild, rChild;
	
    public void addRandomkids(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand){ 
    	
    	//this method to grow the trees randomly until any node has reached the maxdepth;
    	
    	if(depth==maxDepth){				//if the maxdepth is reached, set the child as terminals and redefine the depth of the Childs
    		lChild=t.getTerminal(rand); 	
    		rChild=t.getTerminal(rand);
    		lChild.depth=depth+1;
    		rChild.depth=depth+1;
    	}
    	else{
    		int i=rand.nextInt(o.getNumOps()+t.getNumIndepVars()+1); //if the maxdepth is not reached yet, keeping calling the addRandomkids method
    		if(i<o.getNumOps()){
    			
    			lChild=o.getOperator(rand);
    			lChild.depth=depth+1;
    			lChild.addRandomkids(o, t, maxDepth, rand);
    		}
    		
    		else{
    			lChild=t.getTerminal(rand);
    			lChild.depth=depth+1;
    			
    		}
    		
    		int j=rand.nextInt(o.getNumOps()+t.getNumIndepVars());   //repeat the method for right child
    		if(j<o.getNumOps()){
    			
    			rChild=o.getOperator(rand);
    			rChild.depth=depth+1;
    			rChild.addRandomkids(o, t, maxDepth, rand);
    		}
    		
    		else{
    			rChild=t.getTerminal(rand);
    			rChild.depth=depth+1;
    			
    		}
    	}
    }
}	
	

