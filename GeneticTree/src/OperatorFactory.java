 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is taken as a gift from the professor
import java.util.*;

/** Produces a random operator (e.g., Plus) by cloning from an internally held
* collection of possible operator types. */
public class OperatorFactory {
    private Node[] currentOps;

    /** Sets the types of operators that this factory can supply.
    * @param n An array of Nodes containing instances of the available operator types. */
    public OperatorFactory(Node[] n) { currentOps = n; }

    /** Returns a Node referring to a randomly chosen operator. */
    public Node getOperator(Random rand) {
		Node n = (Node)currentOps[rand.nextInt(currentOps.length)].clone();
        return n;
    }
    public int getNumOps() { return currentOps.length; }
}
