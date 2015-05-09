/**
 * @category Data Structures & Algorithm Homework 1-2
 * @author Preston Lin
 * @date Jan 27, 2014
 * @note Allow users to input texts and use 
 * 		 DoublyLinkedList to hold BigInteger to 
 * 		 complete Merkle-Hellman Knapsack Cryptosystem
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**This class contains multiple methods for Encryption/Decryption 
 * Precondition: A DoublyLinkedList and DoubleNode class has to be
 * in place for this class to function. A user input request will
 * be prompt for further processing. This class assumes ASCII code
 * is used. Merkle-Hellman Knapsack Cryptosystem will be followed.
 * Postcondition: The class carries a several 
 * parameters, which will be initiated 
 * while the class is instantiated.
 */
public class Cryptosystem{
	
	/**ASCII has 128 characters. We choose to use 1 byte to accomondate each character*/ 
	private static int charSize=8; 
	/**As a key for decryption. Because No clone method was constructed, it is an easier way to retain the original superIncreasing sequence*/
	private static DoublyLinkedList superIncreaseforDecrypt = new DoublyLinkedList(); 
	/**q is defined by the Merkle-Hellman Knapsack Cryptosystem*/
	private static BigInteger q = new BigInteger("0"); 
	/**r is defined by the Merkle-Hellman Knapsack Cryptosystem*/
	private static BigInteger r = new BigInteger("0");
	private static Random rand = new Random();
	
	
	/**Allow users to input texts for encryption request. 
	 * Precondition: The lenght of texts should be less than 80 letters.
	 * Postcondition: If input is longer than the limits, warning message
	 * would be prompt and ask for another valid input.
	 * Once the texts are accepted, the needed bytes for input texts 
	 * will be calcualted. A private key and a public key 
	 * will be generated then. Furthermore, texts would be encrypted
	 * and original texts would be cleared for protection.
	 * Decryption is based on encrypted value 
	 * This is a main method, no need to estimate big theta
	 * value.
	 */
    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    System.out.println("Please enter for encryption and press \"Enter\":"); 
	    String decrypted = input.nextLine(); 
	    while(decrypted.length()>=80||decrypted.length()==0){
	    	 if(decrypted.length()>=80){
	    		 System.out.println("The sentence is too long. Please enter 80 characters or less for encryption and press \"Enter\":");
	    	 }
	    	 if(decrypted.length()==0){
	    		 System.out.println("Please enter for encryption and press \"Enter\":");
	    	 }
	    	 decrypted = input.nextLine();
	    }
	    System.out.println("Number of clear text bytes ="+decrypted.getBytes().length);
	    char []	inputbytes = decrypted.toCharArray();
	    DoublyLinkedList privatekey = privatekey(charSize, inputbytes.length); //generate superincrease and q and r
	    DoublyLinkedList publickey = publicKey(privatekey); //generate publickey
	    BigInteger encryption = encrypt(inputbytes, publickey); //start to encrypt
	    System.out.println("Text cleared.");
	    System.out.println(decrypted = "");
	    System.out.println("Eycrypted as:"+encryption);
	    System.out.println("Continue to do decryption");
	    System.out.println(decrypt(encryption));
	  
	    }
    
    /**A Encrypted message will be decrypted back to the original message.
     * Best Case: Big theta(n)
     * Worst Case: Big theta(8n)
     * @param Precondition: b BigInteger type parameter representing the encrypted message should be given.
     * Merkle-Hellman Knapsack Cryptosystem is used.
     * @return Postcondition: A decrypted string representing the original message will be returned. 
     */
	public static String decrypt(BigInteger b){
		
		int [] array = new int[superIncreaseforDecrypt.countNodes()];
		int count = superIncreaseforDecrypt.countNodes()-1;
		
		BigInteger seed = new BigInteger("0");
		seed = seed.add(b.multiply(r.modInverse(q)).mod(q));
		
		//the following is based on the algorithm to find the original values in the superincreasing node
		//in order to decrypt decimals back to long binary values 
		BigInteger bigSuperNode = new BigInteger("0");
		bigSuperNode = bigSuperNode.add(superIncreaseforDecrypt.removeBigIntegerAtEnd());
		
		BigInteger zero = new BigInteger("0");
		while(seed.compareTo(zero)!=0){
			while(bigSuperNode.compareTo(seed)==1){
				bigSuperNode = superIncreaseforDecrypt.removeBigIntegerAtEnd();
				count--;
			}
			seed = seed.subtract(bigSuperNode);
			array[count]=1;
    	}
		
		String tmp = "";
		StringBuffer decryption = new StringBuffer(); 
		//the continuous binary values would be grouped for transforming back to char
		for(int i=0;i<(array.length)-(charSize-1);i+=charSize){
			tmp = "";
			for(int j = i; j < i+charSize; j++){
				tmp += String.valueOf(array[j]);
			}
			int integer = Integer.parseInt(tmp, 2);
			decryption.append((char)integer);
		}
		
    	return decryption.toString();
    }
   
	/**Use public key to encrypt the message stored in a char array.
	 * Best Case: Big theta(3n)
	 * Worst Case: Big theta(3n)
	 * @param Precondition:s A char array should be given;
	 * @param pk A DoublyLinkedList public key should be given;
	 * The method would start encrypt
	 * the texts based on the algorithm of
	 * Merkle-Hellman Knapsack Cryptosystem.
	 * @return Postcondition:a BigInteger object shall be returned;
	 * The given array would be further used to 
	 * calculate with the publickey 
	 * and finally returned a big continuous
	 * integer as encrypted messages;
	 */
	public static BigInteger encrypt(char [] s, DoublyLinkedList pk){
 
    	BigInteger encryption = new BigInteger("0");
    	String bits="";
    	
    	for(int i=0;i<s.length;i++){
    		String currentbits="";
    		currentbits = Integer.toBinaryString(s[i]);
    		while(currentbits.length()<charSize){
    			currentbits = "0"+currentbits;
    		}
    		bits += currentbits;
    	}
    	
    	char array [] = new char [bits.length()];
    	array=bits.toCharArray();
    	
 
    	for(int i=0;i<array.length;i++){    	
    		BigInteger ii = BigInteger.valueOf(array[i]).subtract(new BigInteger("48"));
    		encryption = encryption.add(pk.removeBigIntegerFromFront().multiply(ii));
    		}
		return encryption;
    
	}

	
	/**To generate a private key.
	 * Best Case: Big theta(n+2)
	 * Worst Case: Big theta(2n+1)
	 * @param Precondition: charSize should be set to 8 if ASCII is used.
	 * @param The length of input texts sholud be given.
	 * @return Postcondition: A DoublyLinkedList representing a superincreasing sequence 
	 * will be returned.
	 * After this method is called, a superincreaseing sequence would be
	 * randomly produced. A q and r specified by Merkle-Hellman Knapsack Cryptosystem will
	 * also be generated.

	 */
    public static DoublyLinkedList privatekey(int charSize, int length){
	    
	    int number = 0;
	    BigInteger big = new BigInteger("0");
	    BigInteger sequenceSum = new BigInteger("0");
	    DoublyLinkedList superIncrease = new DoublyLinkedList();
	    while(number!=charSize*length){
	    		big = big.add(BigInteger.valueOf(Math.abs(rand.nextInt())).add(sequenceSum)); 		
	    		superIncrease.addBigIntegerAtEnd(big);
		    	number++;
		    	sequenceSum = sequenceSum.add(big);
	    	}
	    q = q.add(sequenceSum.add(BigInteger.valueOf(Math.abs(rand.nextInt())))); 
	    r = r.add(BigInteger.valueOf(Math.abs(rand.nextInt())));
    	while(!isCoPrime(r, q)){
    		r = r.add(BigInteger.valueOf(rand.nextInt()));
    		}
    	//System.out.println(superIncrease);
    	return superIncrease;
    }

    
    /**Generate the public key.
     * Best Case: Big theta(n)
     * Worst Case: Big theta(n)
     * @param Precondition: d as a DoublyLinkedList type representing superincreasing sequence should be given.
     * The sequence should not be empty. Merkle-Hellman Knapsack Cryptosystem algorithm is used.
     * r and q already generated as private key should be constant.
     * @return Postcondition: a DoublyLinkedList as public key will be returned.
     */
    public static DoublyLinkedList publicKey(DoublyLinkedList d){
    	
    	DoublyLinkedList publickey = new DoublyLinkedList();
    	while(!d.isEmpty()){
    		BigInteger tmp = d.removeBigIntegerFromFront();
	    	superIncreaseforDecrypt.addBigIntegerAtEnd(tmp); //to retain the original superIncrease sequence
	    	BigInteger b = ((tmp.multiply(r)).mod(q));
	    	publickey.addBigIntegerAtEnd(b);
    	}
    	//System.out.println(publickey);
    	return publickey;
    }
    
    /**This method is to evaluate r and q which are needed as private key in Merkle-Hellman Knapsack Cryptosystem.
     * Best Case: Big theta(1)
     * Worst Case: Big theta(1)
     * @param Precondition: r AND q are BigInteger parameters needs to be given and r should be less than q and greater than 1;
     * @return Postcondition: if r and q is coPrime, a true value would be returned. If not, false will be returned;
     */
    public static boolean isCoPrime(BigInteger r, BigInteger q){
    	
    	int mod;
    	if((r.compareTo(q)==-1)&&(q.gcd(r)).intValue()==1){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
