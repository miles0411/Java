
public class Fraction {
	int numerator;
	int denominator;  	//as instructed 
	
	public Fraction() { 
	numerator = denominator = 1; //no parameter appointed, initialize numerator and denominator to be 1
	}
	
	Fraction(int n, int d) { // if two int parameter is input, do this method
	numerator = n; 
	denominator = d;
	int gcd = gcd(numerator, denominator); //execute to find greatest common divisor
	numerator /= gcd;					   //let the fraction to be a fraction in the simplest format
	denominator /=gcd;
	}
	
	int gcd(int a, int b) {   // greatest common divisor: as instructed
	if (b == 0)
	return (a);
	else
	return (gcd(b, a % b));	  // return the largest common divisor 
	}
	
	public String toString() {
	  return ""+numerator + "/"+ denominator;  //define the toString() method to print out fraction in String format
	}
	
	String toDecimal() {					   //define a toDecimal() method to transform a fraction into decimal format
		float temp = (float)this.numerator/(float)this.denominator;  
		String decimal = String.valueOf(temp);		//transform the float type variable into String type for the following return
		return decimal;
	}
	
	Fraction add(Fraction f) {						//define add() method to add two fractions together
		Fraction ff = new Fraction();      	     	//new an object to store calculation result
		int c = this.denominator*f.denominator;     //execute calculation by how the elementary school student calculate
		int d = c/this.denominator;					
		int e = c/f.denominator;
		ff.denominator = c;
		this.numerator *= d;
		f.numerator *= e;
		ff.numerator=this.numerator+f.numerator;	
		int gcd = gcd (ff.numerator, ff.denominator);//make the fraction will be the simplest form by executing gcd check
		ff.numerator /=gcd;
		ff.denominator /= gcd;
		return ff;
		
	}

	
}

