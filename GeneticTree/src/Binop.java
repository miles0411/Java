 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is taken as a gift from the professor
import java.util.*;

/** A superclass for all arithmetic binary operators.
 Author: Steve Roehrig, for the MISM/MSIT Java course
 Revised: 11/13/2011
 */

	public abstract class Binop extends Node {
    protected Node lChild, rChild;
    public Binop() {}
    public Binop(Node l, Node r) {
    	lChild = l; rChild = r;
    }
    public void setChild(int position, Node n) {
        if (position == 1)
        	lChild = n;
        else
            rChild = n;
    }

	/**
    * Add left and right children, and recursively
    * extend them by a specified amount. If maxDepth has been reached,
    * add terminals as children. Otherwise
	* add a random operator or terminal child on the left and right,
    * and ask each child to addRandomKids.  Each child has a depth
    * one greater than the current node. The process stops either when
    * both children are terminals, or when maxDepth has been reached.
	*/
    public void addRandomKids(OperatorFactory o, TerminalFactory t,
                              int maxDepth, Random rand) {
        int i;
        if (depth < maxDepth) {
            i = rand.nextInt(o.getNumOps() + t.getNumIndepVars());
            if (i < t.getNumIndepVars()) {
                lChild = t.getTerminal(rand);
                lChild.depth = depth + 1;
            }
            else {
                lChild = o.getOperator(rand);
                lChild.depth = depth + 1;
                lChild.addRandomKids(o, t, maxDepth, rand);
            }
            i = rand.nextInt(o.getNumOps() + t.getNumIndepVars());
            if (i < t.getNumIndepVars()) {
                rChild = t.getTerminal(rand);
                rChild.depth = depth + 1;
            }
            else {
                rChild = o.getOperator(rand);
                rChild.depth = depth + 1;
                rChild.addRandomKids(o, t, maxDepth, rand);
            }
		}
        else {
            lChild = t.getTerminal(rand);
			lChild.depth = depth + 1;
			rChild = t.getTerminal(rand);
			rChild.depth = depth + 1;
        }
    }
    public int mySize() {
        int size = 1;
        size += lChild.mySize();
        size += rChild.mySize();
        return size;
    }

}
