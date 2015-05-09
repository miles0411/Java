/*This is a bridge class for GUI and Evolver to call variables from each other*/


import java.util.ArrayList;


public class Platform{
	
	private int generationNum;
	private int treeNum;
	private int RandomSeed;
	private String filename;
	private Node [] userop;
	private static TestEvolver testevolver = null;
	
	Platform(int g, int t, int r, ArrayList<Node> a, String f){  //A constructor will take in input parameters on the interface

		generationNum = g;
		treeNum = t;
		RandomSeed=r;		
		filename = f;
		userop = new Node[a.size()];
		for(int i=0;i<a.size();i++){
			userop[i] = a.get(i);
		}
	}
	
	public String getResult(){   //write a method for the GUI to take the result for out printing
		testevolver = null;
		testevolver = new TestEvolver(generationNum, treeNum, RandomSeed, userop, filename); //when the method is called, the Evolver will be instantiated
		return testevolver.getresult(); 
	}

}
