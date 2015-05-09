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
 * After the crime data is given, each possibility of path will be travsed to compare their distance. 
 * A path will finally be produced with the minimym distance.
 * Note: No need to consider Big Theta 
 */
public class CrimeData_Optimal {
	/**private data variable for doing file scanning*/
	private Scanner s;
	/**private data variable for holding given crime data*/
	private LinkedList<Node> crimeData = new LinkedList<Node>(); 
	/**private data variable for holding given crime data based on user's input*/
	private static LinkedList<Node> al = new LinkedList<Node>();
	/**private data variable for holding the distance of each pair of vertices*/
	private double [][] distanceMatrix;
	/**private data variable for holding the optimal Hamiltonan Cycle*/
	static int[] path;
	/**private data variable for working as temp to hold the most temporarily optimal Hamiltonan Cycle*/
	private int[] record;
	/**private data variable for recording a vertices is visited or not*/
	private boolean[] visit;
	/**private data variable for holding the value of distance of the optimal path*/
	public static double best_dist;
	
	/**Customized constructor to load in the data crime. 
	 * THe file should exist in the given path, or the FileNotFound exception 
	 * will be thrown*/
	CrimeData_Optimal() {
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
	
	
	 /** This method is to retreive the crime data specified by the user input, 
	  *  and copy them into a new array. Also, this method will call the method to fill the distance metrix for each pair of the vertices.
	  *  After the above procedures are completed. Arrays will be opened to start to explore every possible Hamiltonan Cycle.
	  *  Precondition: two integer number is already input by the user.
	  *  Postcondition: The distance Metrix will be calculated and filled. Each possible Hamiltonan Cycle existing in the given set of vertices will be explored.
	  *  The cycle with minimum distance will be returend.
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
		record = new int[al.size()+1];
		path = new int[al.size()+1];
		best_dist=-1;
		for(int i=0;i<al.size();i++){
			record[0]=i;
			visit = new boolean[al.size()];
			visit[i]=true;
			TSP(0,0,al.size());
			
		}
		path[path.length-1]=path[0]; //copy the first vertices into the end of the array as the termination
	}
	
	 /** This method is to explore each possible Hamiltonan Cycle by brute force approach recursively until the method hits the base case.
	  *  Precondition: a counting variable n, a double type distance and the total number of vertices should be given
	  *  Postcondition: The brute force approach will be performed. The optimal cycle with minimum distance value shall be returned. 
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void TSP(int n, double d, int N){
		
		if(n==N-1){
			if(d+distanceMatrix[record[0]][record[n]]<best_dist||best_dist==-1){
				best_dist=d+distanceMatrix[record[0]][record[n]];
				for(int j=0;j<N;j++){
					path[j]=record[j];
				}
				return;
			}
		}
		
		for(int i=0;i<N;i++){
			if(!visit[i]){
				visit[i]=true;
				record[n+1]=i;
				TSP(n+1,d+distanceMatrix[record[n]][i],N);
				visit[i]=false;
			}
			
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
	
	
	 /** This method is to help represent the content of an array by a string.
	  *  Precondition: The optimal path has been determined
	  *  Postcondition: A string representation of the optimal path will be returned.
	  *  Best Case: theta(n)
	  *  Worst Case: theta(n)
	 */
	public String pathtoString(){
		String cycle="";
		for(int i=0;i<path.length;i++){
			cycle+=path[i]+" ";
		}
		return cycle;
	}

}

