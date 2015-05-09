
abstract class Glyph {
    abstract void draw();
    Glyph() {
    	System.out.println("Glyph() before draw");
        draw();
        System.out.println("Glyph() after draw");
        
    }
}

class RoundGlyph extends Glyph {
    int radius = 1;
    
    RoundGlyph(int r) {
	    System.out.println("Yes, radius is set to be " + radius + " at this time"); //write the line to prove raidus is ever assumed to be 1
	    radius = r;
	    System.out.println("Now radius is set to be " + radius); //prove the radius is now assumed to be 5 according the input argument
	    System.out.println("RoundGlyph(), radius=" + radius);
    }
       
    
    void draw() {
        System.out.println("RoundGlyph.draw(), radius=" + radius);
    }

}