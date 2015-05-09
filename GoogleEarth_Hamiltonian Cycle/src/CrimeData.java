/**
 * @category Data Structures & Algorithm Homework 4-3
 * @author Preston Lin
 * @date March 31, 2014
 * @note Based on Project 4-1 & 4-2 to produce the Google Earth File that represents the two paths we derived
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**This class is to define several methods and initialize the loaded data with a customized constrcutor.
 * After the crime data is given, a minimum spanning tree will be constructed, and a preorder travesal will be performed.
 * A path will finally be produced with the distance calculated for that path.
 * Note: No need to consider Big Theta 
 */
public class CrimeData {
	
    /**private data variable for doing file scanning*/
	private Scanner s; 
	/**private data variable for holding given crime data*/
	LinkedList<Node> crimeData = new LinkedList<Node>(); 
	/**private data variable for holding given crime data based on user's input*/
	private static LinkedList<Node> al = new LinkedList<Node>();
	/**private data variable for holding the distance value of the produced path*/
	static double totalMinDistance = 0;
	/**private data variable for holding the distance of each pair of vertices*/
	private double [][] distanceMatrix;
	/**private data variable for holding on-going traversed vertices.*/
	private static LinkedList<Node>[] graphArray;
	/**private data variable for the ideal path based on Prim's algorithm*/
	static LinkedList<Integer> preOrder = new LinkedList<Integer>();
	
	/**Customized constructor to load in the data crime. 
	 * THe file should exist in the given path, or the FileNotFound exception 
	 * will be thrown*/
	CrimeData() {
		try{	
			File f = new File("CrimeLatLonXY1990.csv");
			s = new Scanner(f); 
			s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
		catch ( FileNotFoundException e) {}
		while(s.hasNextLine()){
			String[] curLine = s.nextLine().split(",");
			if(curLine[0].startsWith("X")){}
			else{
				Node n = new Node(Double.parseDouble(curLine[0]),
								  Double.parseDouble(curLine[1]),
								  curLine[2],curLine[3],curLine[4],curLine[5],curLine[6],curLine[7],curLine[8]);		
				crimeData.add(n);
			}
		}
	}
	
	 /** This method is to construct the minimum tree based on MST-Prim's algorithm
	  *  Precondition: A set of vertices is given by an array. Those vertices have been built by a node class
	  *  Postcondition: A minimum node tree will be produced.
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void spanningTree(){
		graphArray = new LinkedList[al.size()];
		for(int i=0;i<al.size();i++){
			graphArray[i]= new LinkedList();
		}
		boolean [] visit = new boolean[al.size()]; 
		visit[0]=true;
		double minDistance = -1;
		int minVertax = -1;
		int correlativeVertax = -1;
    
		for(int i=0;i<al.size()-1;i++){
			for(int j=0;j<al.size();j++){
				if(!visit[j]){
					for(int k=0;k<al.size();k++){
						if(visit[k]){
							if(minDistance>distanceMatrix[j][k]||minDistance==-1){
								minDistance=distanceMatrix[j][k];
								minVertax=j;
								correlativeVertax=k;
							}
						}
					}
				}
			}
		graphArray[correlativeVertax].addFirst(al.get(minVertax));
		visit[minVertax]=true;
		minDistance=-1;
		minVertax=-1;
		correlativeVertax = -1;	
		}
	}
	
	 /** This method is to retreive the crime data specified by the user input, 
	  *  anc copy them into a new array. Also, this method will call the method to fill the distance metrix for each pair of the vertices.
	  *  Precondition: two integer number is already input by the user.
	  *  Postcondition: The distance Metrix will be calculated and filled. A minimum spanning tree will be constructed. A traversal will be performed preorderly 
	  *  on the minimum spanning tree.
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void CalcCycle(int a, int b){
		
		for(int i=a;i<=b;i++){
			al.add(crimeData.get(i).setNo(i-a)); 
		}
		distanceMatrix = new double[b-a+1][b-a+1];
		for(int i=a-a;i<=b-a;i++){
			for(int j=a-a;j<=b-a;j++){
				distanceMatrix[i][j]=calDistance(al.get(i), al.get(j));
			}
		}
		spanningTree();
		traverse(0);
	}
	
	
	 /** This method is to travese a tree preorderly. The node will be visited given a root and construct a path that visit each vertex only once.
	  *  And return the Hamiltonan Cycle.
	  *  Precondition: A minimum spanning has been built for performing travesal.
	  *  Postcondition: The Hamiltonan Cycle will be recorded and returned
	  *  Best Case: theta(n)
	  *  Worst Case: theta(n)
	 */
	public void traverse(int n){
		
		LinkedList temp = new LinkedList<Node>();
		preOrder.addLast(0);
		for(int i=0;i<graphArray.length;i++){
			temp.addFirst(i);
			if(!temp.isEmpty()){
				int curr = (int) temp.removeFirst();
				while(!graphArray[curr].isEmpty()){
					temp.add(graphArray[curr].removeFirst().getNo());
					while(!temp.isEmpty()){
						int innerCurr = (int) temp.removeFirst();
						if(!graphArray[innerCurr].isEmpty()){
							temp.add(graphArray[innerCurr].removeFirst().getNo());
						}
						preOrder.addLast(innerCurr);
					}	
				}
			
			}
		}
		preOrder.addLast(n);
		for(int i=0;i<preOrder.size()-1;i++){
			totalMinDistance += distanceMatrix[(int) preOrder.get(i)][(int) preOrder.get(i+1)];
		}
	}

	
	 /** This method is to calculate the distance between two given coordinates.
	  *  Precondition: two legal Nodes which hold valid value of coordinates are given
	  *  Postcondition: The distance calculated by Pythagorean theorem will be returned in unit of Miles
	  *  Best Case: theta(1)
	  *  Worst Case: theta(1)
	 */
	public double calDistance(Node a, Node b){
		
		double coordvertax1 = a.getX();
		double coordvertay1 = a.getY();
		double coordvertax2 = b.getX();
		double coordvertay2 = b.getY();
		return Math.sqrt((Math.pow(coordvertax2-coordvertax1,2) + Math.pow(coordvertay2-coordvertay1,2)))/5280;
	
	}
	
}

