 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //This is taken as a gift from the professor originally, but has been revised accordingly for more assignment requirement
 //Revised: 10/30/2013

import java.util.*;


public class GPTree implements Comparable {
    private Node root;
    private int mySize;
    private double fitness = 0;
    public GPTree() { 
    	root = null; 								/** A wrapper for the root node of a genetic programming tree.*/
    }
    
    public GPTree(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand) {
        root = o.getOperator(rand);
        root.addRandomKids(o, t, maxDepth, rand);
    }												/** Constructs a random GPTree whose depth is no greater than maxDepth.*/
    
    public String toString() { 
    	return root.toString(); 
    }

    
    public double eval(DataSet data) { 
    	for(int i=0;i<data.numLine;i++){
        fitness += Math.pow((root.eval(data.list.get(i).x)-data.list.get(i).y),2); 
    	}
		return fitness;   
    }												//Calculate the sum of variance according the input data which are held in an Arraylist

   
    public int mySize() { 
    	mySize = root.mySize(); 
    	return mySize; 
    }												 /** Return the number of nodes in this GPTree. */

    
	public int compareTo(Object t) {
        if (fitness < ((GPTree)t).fitness)
            return -1;
        else if (fitness > ((GPTree)t).fitness)
            return 1;
        else
            return 0;
    }												/** Specifies the natural ordering, based on the value of the fitness member variable. */
	
    public double getFitness() { 
    	return fitness; 
    }

    
    public int getDepth() { 
    	return root.getDepth(); 
    }												/** Returns the length of the longest branch in this GPTree. */

   
    public GPTree duplicate() {
        GPTree copy = new GPTree();
        copy.root = root.duplicate();
        return copy;
    }
												    /** Returns a duplicate copy of this GPTree. Used in the crossover
												     * operation and when creating a new generation. */
 }
