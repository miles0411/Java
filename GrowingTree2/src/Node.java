 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is a taken gift from the professor
import java.util.*;
/** The top-level class for the GPTree hierarchy.
* The classes Binop, Const, and Variable derive from this. 
*/
public abstract class Node implements Cloneable {
    protected int depth;
    public Node() { depth = 0; }
    public int mySize() { return 1; }
    public void setDepth(int d) { depth = d; }
    public int getDepth() { return depth; }
    public abstract void setChild(int position, Node n) throws GPTreeChildPositionException;
    public abstract double eval(double[] data);
    public abstract String toString();
    public abstract void addRandomKids(OperatorFactory o, TerminalFactory t,
                                       int maxDepth, Random rand);
    public abstract Node duplicate();
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Node can't clone.");
        }
        return o;
    }
}
