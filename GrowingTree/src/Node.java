import java.util.*;

public abstract class Node implements Cloneable {
	
	public double value;    //a node hold a value itself
	public int depth=0;	    //a node hold a variable indicating its depth
    public Node() {}
    public abstract double eval(double[] date);
    public abstract void addRandomkids(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand);
    public Object clone() {		//override the clone method and provide exception processing method
        Object o = null;
        try {
            o = super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("SimpleClass can't clone.");
        }
        return o;
    }
    
}  




