/**
 * @category JAVA Homework 6-2
 * @Author:  Steve Roehrig, for the MISM/MSIT Java course, This is a gift from the professor for this assignment
 * @Revised: 11/13/2011
 * @date Oct 31,2013
 * @note Create a binary tree composed of nodes defined by the depth. Use GPTree to hold the nature of a tree
 * 	     A class with main() to test the AlgebraWithFactories project.  This is a taken gift from the professor
*/

import java.util.*;

public class TestAlgebra {
    static int numIndepVars = 3;
    static int maxDepth = 5;
    static Random rand = new Random();
    public static void main(String[] args) {
        double[] data = {3.14, 2.78, 1.0};
        Node[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
        OperatorFactory o = new OperatorFactory(ops);
        TerminalFactory t = new TerminalFactory(numIndepVars);
        GPTree gpt = new GPTree(o, t, maxDepth, rand);
        System.out.println("MySize is " + gpt.mySize());
        System.out.println(gpt + " = " + gpt.eval(data));
    }
}

