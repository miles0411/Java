/**
 * @category JAVA Homework 6-3
 * @author Preston Lin
 * @date Oct 31,2013
 * @note Create a binary tree composed of nodes defined by the depth.
 *       Numerous Trees get created and each tree is evaluated by fitness  
 *		 The fitness is defined by variance in statistics, and the tree with best fitness(with the smallest variance) will be retained 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;

public class TestGeneration {
    static int numIndepVars;        		//define the number of Independent Variable based on the input file 
    static double [] data;          		//define the value of Independent Variable based on the input file 
    static int maxDepth = 5;        		//define the maximum depth of any tree at user's wish
    static Random rand = new Random();		//for further use
   
    public static void main(String[] args) {
    	//Scanner input = new Scanner(System.in); 		  	//new the scanner class in order to use the class already established in JAVA library - adapted from course slides
       // System.out.println("Please enter the filename:");	
       // String fName = input.nextLine(); 					//add a variable to take the user's input
        DataSet s = new DataSet("/Users/Preston/Desktop/CrossoverForStudents/Data1.dat");                     //new DataSet to process the file
        numIndepVars = s.numVar;        					//initialize the number of Independent Variable based on the input file 
        
        Node[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};  //based on previous assignment
        OperatorFactory o = new OperatorFactory(ops);
        TerminalFactory t = new TerminalFactory(numIndepVars);
       
        GPTree trees [] = new GPTree[500];					//create an array to hold 500 trees
        
        for(int i=0;i<500;i++){
        GPTree gpt = new GPTree(o, t, maxDepth, rand);      //create 500 trees and use the array to hold them
        trees[i]=gpt;
        }
        
        GPTree min = trees[0];								//assuming the first tree in the array is the tree with the best fitness
        
        for(int i=0;i<500;i++){								//start to compare trees by their fitness, the tree with better fitness will be selected out into as the min
        	if(trees[i].eval(s)<min.getFitness()){
        		min = trees[i];
        	}       	
        }
        System.out.println("\r\n");
        System.out.println("The size of the tree with the best fitness is in " + min.mySize() +" nodes.");
        System.out.println("The tree with the best fintness (smallest variance) in this generation is "+ min);
        System.out.println("The fitness of this tree is " + min.getFitness()); //print out the information for the tree with the best fitness
    }
}

