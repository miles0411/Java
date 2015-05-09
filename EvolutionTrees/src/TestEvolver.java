/**
 * @category JAVA Homework 7-1
 * @author Preston Lin
 * @date Nov 7,2013
 * @note Create a binary tree composed of nodes defined by the depth.
 *       Numerous Trees get created and each tree is evaluated by fitness  
 *		 The fitness is defined by variance in statistics, and the tree with best fitness(with the smallest variance) will be retained 
 *       Successively create 5 generations which are born by their previous generation and printout the best tree in each generation. 
 *       For those parts worked in previous assignment, no further comment is provided if not necessary 
 *       Note: Part of thess codes werere written by the Professor Stephen. We were asked to do some modifications.
 */

import java.util.Random;

/**This class is public and hold member variables for this program to work*/
public class TestEvolver {
   
	static int maxDepth = 5;
	static int numGeneration = 5;
	static int populationsize = 500;
	static Random rand = new Random();	
	
	
	/** The main method is to create a new Generation g1 by given parameters.
	    In the method, it evaluates the fitness of trees across the entire population.
	    And then Print out the best tree in Generation 0.
	    Further, Start to evolve 5 successive generations:
	    Firstly, new a Evolver e1 by given generation, dataset, and rand.
	    Once the new generation is created, it refers the variable g1 to the new generation
	    The best trees in each generation will be print out one by one.
	 */
	public static void main(String[] args) {
        DataSet ds = new DataSet("/Users/Preston/Desktop/CrossoverForStudents/Data2.dat");
    	Node[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
   	 	OperatorFactory o = new OperatorFactory(ops);
    	TerminalFactory t = new TerminalFactory(ds.numIndepVars());      
        
    	
    	Generation g1 = new Generation(populationsize, o, t, maxDepth, rand); 
    	g1.evalAll(ds);														 
    	System.out.println("Generation:"+0);					
    	g1.printBestTree();													  
    	System.out.println();
    	for(int i=0;i<numGeneration;i++){									  
    	Evolver e1 = new Evolver(g1, ds, rand);								 
    	g1 = e1.makeNewGeneration();									     
    	System.out.println("Generatio:" + (i+1));							  
    	g1.printBestTree();													  
        System.out.println();
    	}
	}
}
