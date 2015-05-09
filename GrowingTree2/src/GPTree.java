 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is a taken gift from the professor

import java.util.*;

/** A wrapper for the root node of a genetic programming tree.
*/
public class GPTree implements Comparable {
    private Node root;
    private int mySize;
    private double fitness = 0;
    public GPTree() { root = null; }

    /** Constructs a random GPTree whose depth is no greater than maxDepth.
    */
    public GPTree(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand) {
        root = o.getOperator(rand);
        root.addRandomKids(o, t, maxDepth, rand);
    }
    public String toString() { return root.toString(); }

    /** Evaluate this GPTree for a single row of data. */
    public double eval(double[] data) { return fitness = root.eval(data); }

    /** Return the number of nodes in this GPTree. */
    public int mySize() { mySize = root.mySize(); return mySize; }

    /** Specifies the natural ordering, based on the value of the fitness member variable. */
	public int compareTo(Object t) {
        if (fitness < ((GPTree)t).fitness)
            return -1;
        else if (fitness > ((GPTree)t).fitness)
            return 1;
        else
            return 0;
    }
	
    public double getFitness() { return fitness; }

    /** Returns the length of the longest branch in this GPTree. */
    public int getDepth() { return root.getDepth(); }

    /** Returns a duplicate copy of this GPTree. Used in the crossover
    * operation and when creating a new generation. */
    public GPTree duplicate() {
        GPTree copy = new GPTree();
        copy.root = root.duplicate();
        return copy;
    }

 }
