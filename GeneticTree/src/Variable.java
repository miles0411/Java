 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is a taken gift from the professor
import java.util.*;

/** A variable in an algebraic expression. Holds an index referring to
*   its subscript in a algebraic expression. 
*/

public class Variable extends Node {
    private int index;       		/** @param i the subscript of the variable; subscripts start at zero. */
    
    public Variable(int i) {
        index = i;
    }
									   
	public void setChild(int position, Node n) {
        System.out.println("Attempt to add child to Variable");
	}								        		/** This should never be called! Should throw an exception,
												    * but doesn't yet. */
    
    public String toString() {
        String s = new String();
        s += "X" + index;
        return s;
    }

   
	public double eval(double []data) {
			if (index < data.length){
				return data[index];       /** Returns the value in data indexed by this Variable's index member. */
			}
			else {
				System.out.println("Variable index exceeds data array length.");
			}
			return 0;
		}
	
   
    public void addRandomKids(OperatorFactory o, TerminalFactory t,
                              int maxDepth, Random rand) {}       /** Does nothing, since Variables can't have children. */

   
    public Node duplicate() {
        Variable alterEgo = new Variable(index);
        return alterEgo;
    }															 /** Returns a new Variable object with the same index. */


    
    public void changeChild(Node oldChild, Node newChild) {
        System.out.println("Variable.changeChild() should never be called!");
    }															/** This should never be called! Should throw an exception. */
}
