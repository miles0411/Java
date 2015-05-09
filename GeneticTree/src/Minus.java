 //Author: Steve Roehrig, for the MISM/MSIT Java course
 //Revised: 11/13/2011
 //This is taken as a gift from the professor

public class Minus extends Binop {
    public Minus() {}
    public Minus(Node l, Node r) {
        super(l, r);
    }
    public double eval(double []data) {
        return lChild.eval(data) - rChild.eval(data);
    }
    public String toString() {
        String s = new String();
        s += "(" + lChild.toString() + " - " + rChild.toString() + ")";
        return s;
    }
    public Node duplicate() {
        Minus alterEgo = new Minus();
        alterEgo.lChild = lChild.duplicate();
        alterEgo.rChild = rChild.duplicate();
        return alterEgo;
    }
}
