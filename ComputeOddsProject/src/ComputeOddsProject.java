/**
 * @category Data Structures & Algorithm Homework 2-2
 * @author Preston Lin
 * @date Feb 12, 2014
 * @note Use recursive and dynamic programming to calculate the probability for a team to win the game.
 */

import java.lang.reflect.Array;



public class ComputeOddsProject {
	
	/**a long type parameter to record the beginning time*/
	private static long startTime;
	/**a long type parameter to record the end time*/
	private static long endTime;
	/**a default probability stored in an array for launching the dynamic programming */
	private static double[][] probArray = {{1,1},{0,0.5}};
	
	
	/**This main method is to call both of the recursive and dynamic method for given scenario to calculate probability
	 * The runtime of each calculation will be determined as well.
	 * This is a main method. No need to consider big theta.*/ 
	public static void main(String[] args){
		
		System.out.println("Calculate the probablity to win the game using recursive method");
		System.out.println(ComputeProbRecursive(2,3));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(4,7));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(7,6));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(10,12));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(20,23));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(30,15));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		startTime = System.nanoTime();
		System.out.println(ComputeProbRecursive(50,40));
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		System.out.println("-----------------------------------------------------------------");
		
		System.out.println("Calculate the probablity to win the game using dynamic programming");
		startTime = System.nanoTime();
		ComputeProbDynamic(2,3);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		startTime = System.nanoTime();
		ComputeProbDynamic(4,7);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		startTime = System.nanoTime();
		ComputeProbDynamic(7,6);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		startTime = System.nanoTime();
		ComputeProbDynamic(10,12);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		startTime = System.nanoTime();
		ComputeProbDynamic(20,23);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		ComputeProbDynamic(30,15);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		ComputeProbDynamic(50,40);
		endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
	
	}
	
	
	 /** The methods doing recursive to call the same method for calculating over the sub portion of calculation.
	  *  Precondition: Two int values are given. 
	  *  Postcondition: The timer will start and end; A double value of the probability will be returned.
	  *  Best Case: theta(1)
	  *  Worst Case: theta(?): The sub portion of a given calculation can be very large and unable to be determine. 
	 */
	public static double ComputeProbRecursive(int i, int j){
		startTime = System.nanoTime();
		double prob = 0;
		
		if(i==0&&j>0){
			
			prob += 1;
		}
		else if(i>0&&j==0){
			
			prob += 0;
		}
		else if(i>0&&j>0){
			
			prob += (ComputeProbRecursive(i-1, j)+ComputeProbRecursive(i, j-1))/2;
	
			
		}
		else{
			
			return -1;
		}
		
		return prob;
	
	}
	
	
	 /** The methods is to expand the array to the given size
	  *  Precondition: A array object needed to be given. A int value i of the new array needs to be given to build a i x i array.
	  *  Postcondition: A expanded array with size i x i will be returned. Newly open positions in the array will be given int value 0
	  *  Those value already present will not be alter. 
	  *  Best Case: theta(n)
	  *  Worst Case: theta(n): The sub portion of a given calculation can be very large and unable to be determine. 
	 */
	public static Object arrayGrow(Object a, int i) {					//create arrayGrow method to grow an input array
		 Class cl = Array.get(a, 0).getClass();					//get the parameter of the array as defined in the Class Class
		 if (!cl.isArray()) return null;						//this is an exception catcher if the input Object is not an array
		 Class componentType = cl.getComponentType();			//this is to retrieve the data type in the array (eg. int)
		 
		 int length = Array.getLength(a);						//catch the number of rows of the original array
		 int width = Array.getLength(Array.get(a, 0));			//catch the number of data in the first dimension
		 
		 int newLength = i+1;						
		 int newWidth = i+1;								
		 
		 int[] dimension = new int[]{newLength,newWidth};		//create an array to hold the dimension of the new array
		 
		 Object newArray = Array.newInstance (componentType, dimension);	//use Array.newInstance method defined in Object Class by defining the data type in the array and the dimention
		 																	//the newArray has been created 
		 for(int n = 0; n< length; n++){									
			 System.arraycopy(Array.get(a,n), 0, Array.get(newArray,n), 0, width); 
			 //use loop to copy the original data row by row into the newArray by retrieving the content of the Object as retrieving the data in a regularr array
		 }
		 
		 
		 return newArray;	//return the Object when the method is called
	}
	
	
	 /** The methods is to dynamic update the cell value with the average of the 2 cells above and left.
	  *  Precondition: A index [x][y] to specify the location within the array needed to be given.
	  *  The first row value will all be 1; The very left column except the location [0][0] in the array should be 0. 
	  *  Postcondition: The value in the specified cell will be returned. The valued is determined by 
	  *  the average of the above one cell and the left one cell
	  *  Best Case: theta(1)
	  *  Worst Case: theta(1)
	 */
	public static void ComputeProbDynamic(int x, int y){
			
		Object growingprobArray= null;
		if(x>probArray[0].length||y>probArray.length){		
			
			growingprobArray = arrayGrow(probArray, Math.max(x, y));
			
		}

		double [][] probArray = (double[][]) growingprobArray;
		
		//initialized the first row value all to 1
		for(int j=0;j<probArray.length;j++){	
			probArray[0][j]=1;
		}
		
		
		for(int i=1;i<probArray.length;i++){
			for(int j=1;j<probArray[i].length;j++){
				probArray[i][j]=(probArray[i-1][j]+probArray[i][j-1])/2;
			}
		}
		
		System.out.println(probArray[x][y]);
	
		}	
}

