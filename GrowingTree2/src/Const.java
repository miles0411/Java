 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is a taken gift from the professor

import java.util.*;
import java.text.*;

/** A Node wrapper for a constant in an algebraic expression. 
 */
public class Const extends Node {
    private double value;
    public Const(double d) {value = d; }
    public void setChild(int position, Node n) {}
    public double eval(double[] data) { return value; }
    public String toString() {
        String s = new String();
        s += NumberFormat.getInstance().format(value);
        return s;
    }
    public void addRandomKids(OperatorFactory o, TerminalFactory t,
                              int maxDepth, Random rand) {}

    public Node duplicate() {
        Const alterEgo = new Const(value);
        return alterEgo;
    }
}
