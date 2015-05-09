import java.util.Random;

/**This is a Evolver Class holding member variables for old generations and futher create the new generation*/
public class Evolver {					
	
	private Generation oldGeneration;
	private GPTree [] newPopulation;
	private Random r = new Random();
	private DataSet data;
	
	/**Constructor to take in a Generation, a Dataset and a Random variable*/
	Evolver(Generation g, DataSet d, Random rand){			
		
		oldGeneration = g;
		data = d;
		
	}
	
	/**This is the method provided by the professor 
	 It is a static method to retrieve a certain part of two chosen trees and switch*/
	public static void crossover(GPTree t1, GPTree t2, Random rand) {	
        NodePairPlus pair1 = t1.randomParentAndChild(rand);			
        NodePairPlus pair2 = t2.randomParentAndChild(rand);
        pair1.parent.changeChild(pair1.child, pair2.child);
        pair2.parent.changeChild(pair2.child, pair1.child);
	}
	
	/**This is a method is to generation the new population of next Generation.
	   Firstly, it opens up a array with the same size as the old one.
	   And call the makeNewGeneration() to get the tree chosen.
	   Furthermore, call the crossover(GPTree t1, GPTree t2, Random rand) to exchange a certain part of each other
	   Then,fill the population until it is full.
	   Use the clone method to replace the old generation by new generation, so the next generation can be kept evolving along.*/
	public Generation makeNewGeneration(){		
		newPopulation = new GPTree[oldGeneration.population.length];  
		oldGeneration.evalAll(data);
		int i=0;
		while(newPopulation[newPopulation.length-1]==null){
		GPTree t1 = null;	
		GPTree t2 = null;
		t1 = oldGeneration.chooseTreeProportionalToFitness(data);	
		t2 = oldGeneration.chooseTreeProportionalToFitness(data);
		crossover(t1, t2, r);										
		newPopulation[i]=t1.duplicate();							
		newPopulation[i+1]=t2.duplicate();
		i+=2;
		}
		oldGeneration.population=newPopulation.clone();				
		oldGeneration.evalAll(data);                                
        return oldGeneration;
	}
}
