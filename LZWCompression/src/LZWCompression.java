/**
 * @category Data Structure & Algorithms Project5
 * @author Preston Lin
 * @date Apr 14, 2014
 * @note Implement LWZ Compression Algorithm
 */


import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.omg.CORBA.TIMEOUT;

/**This class hold classes and methods in order to implement LWZ compression/depression.
 * Main routine defines a user interface for taking in inpput data to compress/depress a given file into a file of a given name.
 * */
public class LZWCompression {
	
        /** The main method will launch this program by starting asking for a command line as defined in the instruction.
         *  In this project, I did experiment by 2 data structures: TreeMap and HashMap. The time consumed for each are presented below.
         * 	TreeMap Runtime:
         * 	7.756267 seconds    -- words.txt compressed
			2.886999 seconds    -- words.txt depressed
			1.021309 seconds    -- csv compressed
			0.385915 seconds    -- csv depressed
			213.574737 seconds  -- mp4 compressed
			83.890532 seconds   -- mp4 depressed
         * 	HashMap Runtime:
          	6.942789 seconds    -- words.txt compressed
			2.286562 seconds    -- words.txt depressed
			0.927299 seconds    -- csv compressed
			0.360068 seconds    -- csv depressed
			213.574737 seconds  -- mp4 compressed
			189.509974 seconds  -- mp4 depressed
		 *  This program works for both the ASCII and binary files. The degree of compression for three files are presented below:
  		 	-- The Compressed Degree: 57.20281%    -- txt compressed 
  			-- The Compressed Degree: 63.773884%   -- csv compressed
  			-- The Compressed Degree: 180.05945%   -- mp4 compressed
         */
	   	public static void main(String []args) throws IOException
	    {
	   		
	   		//Use Scanner to take in the user's input
	   	    Scanner input=new Scanner(System.in);
	  	    //java LZWCompression c 01_Overview.mp4 01_Overview_c.mp4
	  		System.out.println("Program Start: -- Put Command --");
	  		String nextLine = input.nextLine();
	  		String [] next = nextLine.split(" ");
	  		
	  		//determine if it's a compression or depression request
		  	if(next[2].equals("c")){
		  		String[] toCompress ={"-c",next[3],next[4]};
		  		long startTime = System.nanoTime();
		    	LZWCompression a = new LZWCompression(toCompress);
		    	long endTime = System.nanoTime();
		    	System.out.println("Compression Completed:" );
		    	File f1 = new File(next[3]);
		    	File f2 = new File(next[4]);
		    	double s2 = f2.length();
		    	double s1 = f1.length();
		    	float rate = (float)(s2/s1);
		    	System.out.println("  -- "+(double)(endTime - startTime)/1000000000+" seconds are used for compression");
		    	System.out.println("  -- The Compressed Degree: "+ rate*100+"%");
		    	
		  	}else{
		  		String[] toCompress ={"-d",next[3],next[4]};
		  		long startTime = System.nanoTime();
		    	LZWCompression a = new LZWCompression(toCompress);
		    	long endTime = System.nanoTime();
		    	System.out.println("Depression Completed:" );;
		    	System.out.println("  -- "+(double)(endTime - startTime)/1000000000+" seconds are used for depression");
		    	
		  	}
		  	 
	    }
	   	
	   	
	   	/**Default constructor to build up read in and write out stream for further purpose.
	   	 * @param args This is to determine what file's is the in file / what is the out file 
	   	 * @throws IOException All IOExceptions will be thrown when occurring
	   	 */
	    public LZWCompression(String []args) throws IOException
	    {
	          DataInputStream in =
	                  new DataInputStream(
	                      new BufferedInputStream(
	                            new FileInputStream(args[1])));
	          DataOutputStream out =
	                  new DataOutputStream(
	                      new BufferedOutputStream(
	                            new FileOutputStream(args[2])));
	        
	          if(args[0].equals(new String("-c"))){
			        code(in,out); //if it's a compression request
			  }
			    else
			  {
			        decode(in,out); //if it's a depression request
			  }      
	   }
	    
	    
	    /**This is the code method to read in byte by byte from the input file. 
	     * The file will read in byte by byte and use LZW algorithm to build up the table of index.
	     * When the word is being written out, it will be described into 16 bits in order to keep the accuracy of the index of that
	     * specific string. The 16bits will be writen out by 2-bytes.
	     * After this method completed, a compressed file will be produced. 
	     * @param in DataInputStream to read in file
	     * @param out DataOutputSteam to write out file
	     * @throws IOException Any IOException will be thrown when occurred.
	     * Bestcase: big theta(1);
	     * Worstcase: big theta(log n); 
	     */
	    public static void code(DataInputStream in,DataOutputStream out) throws IOException
	    {
	    	   //Experiment the data structure between using TreeMap or HashMap
	    	   DictionaryT dic = new DictionaryT();
	           //DictionaryH dic = new DictionaryH(); 
	    	   String s, c;
	           
	    	   //read in byte by byte and cast to char 
	    	   char unSignedbyte = (char) in.readUnsignedByte();
	           s = String.valueOf(unSignedbyte);
	               
	           while(true){	  
	        	   try{
	        		   unSignedbyte = (char)in.readUnsignedByte();			          
	        		   c = String.valueOf(unSignedbyte);
	        		
			    	      if(dic.tm1.containsKey(s+c)){
			    	    	  		s=(s+c);
			    	      }
			    	      else{			    	    	  	   
			    	    	      
			    	    	       String bits16 = String.format("%16s", Integer.toBinaryString(dic.tm1.get(s))).replace(' ' , '0');
			    	    	       int foo = Integer.parseInt(bits16, 2);
			    	    	       out.writeChar(foo);		    	    	   
			    	    	       out.flush();
			    	    	       dic.add(s+c);
			    	               s = c;
			    	      }
			    	      
	        	   }catch(EOFException | NullPointerException e){        		   
	        		   String bits16 = String.format("%16s", Integer.toBinaryString(dic.tm1.get(s))).replace(' ' , '0'); //This is to tackle the last byte when the file is going to reach EOF
    	    	       int foo = Integer.parseInt(bits16, 2);  	    	      
    	    	       out.writeChar(foo);
	        		   in.close();
	        		   out.close();
	        		   return;
	        	   }
	           }       		
	    }
	    
	    
	    
	    /**This is the decode method to read in 2 bytes  by 2 bytes from the compressed file. 
	     * The file will read in 2 bytes each and use LZW algorithm to build up the table of index.
	     * When the word is being written out, it will be described into a sequence of bytes. out.writeBytes(String s) is used.
	     * After this method completed, a depressed file will be produced.
	     * @param in DataInputStream to read in file
	     * @param out DataOutputSteam to write out file
	     * @throws IOException Any IOException will be thrown when occurred.
	     * Bestcase: big theta(1);
	     * Worstcase: big theta(log n); 
	     */
	    public void decode(DataInputStream in,DataOutputStream out) throws IOException
	    { 	 

	    	 String s, c;
	    	 
	    	 //Experiment the data structure to use
	         DictionaryT dic = new DictionaryT();
	         //DictionaryH dic = new DictionaryH();
	         
	         int unSignedbyte = in.readUnsignedShort();
	         c = String.valueOf(unSignedbyte);
	         String codeword = dic.tm.get(Integer.parseInt(c));   
	         out.writeBytes(codeword);
	         out.flush();
	         s=c;
	         
	         while(true){
	        	
	        	 try{
	        	 	        	    	   
	        		 unSignedbyte = in.readUnsignedShort();
	        		 c = String.valueOf(unSignedbyte);
			          if(!dic.tm.containsKey(Integer.parseInt(c))){
			    		    dic.add(dic.tm.get(Integer.parseInt(s))+(dic.tm.get(Integer.parseInt(s)).substring(0, 1)));  
			    		    codeword = dic.tm.get(Integer.parseInt(s))+(dic.tm.get(Integer.parseInt(s)).substring(0, 1));	    		    
			    		    out.writeBytes(codeword);
			    	        out.flush();	    	          
			          }
		    	      else {
		    	    	    String temp = dic.tm.get(Integer.parseInt(s))+(dic.tm.get(Integer.parseInt(c)).substring(0, 1));
		    	    	    dic.add(temp);
		    	    	    codeword = dic.tm.get(Integer.parseInt(c));	    		 
		    	    	    out.writeBytes(codeword);
		    	    	    out.flush();
		    	    	  	  	  	         
		    	      }
			          s=c;
	    	    }catch(EOFException | NullPointerException e){
   	    	  		in.close();
   	    	  		out.close();
        		   return; 
	    	    }
	         }
        
	    }
}  

    /**This class is to establish a table in a TreeMap format to build indexes along the read in of the files. 
     * A TreeMap will be initialized when this class is instantiated. ASCII code 0~255 will also automatically 
     * put into the table for use. Because we use 12bits compression. The maximum patterns of string we can hold
     * will be 2^12 = 4096. Therefore, the TreeMap has to be rebuilt once it is full. 
     */
	class DictionaryT
	{   
		
		TreeMap<Integer,String> tm = new TreeMap<Integer,String>();
		TreeMap<String,Integer> tm1 = new TreeMap<String,Integer>();		
		   
		int buffercount = 256;
		
		/**Default Constructor to initialize needed table for building indexes of strings
		 * 255 ASCII code will be included at the beginning. New words starts from 256
		 * No needs to consider big theta for constructor.
		 * */
		public DictionaryT()
		{
			for(char i=0;i<256;i++)
			{
	   			tm.put((int)i, Character.toString(i));
	   			tm1.put(Character.toString(i),(int)i);
	   		}
		}

		/**
		 * @param a String type for the string we put into the table
		 * This add method if called will add the given string into the table we have built.
		 * if the accumulative words reached 4096 in total, clear the table and start from 256 again. 
		 * Best case: big theta(1);
		 * Worst case: big theta(1);
		 */
		public void add (String a)
		{   
			if(buffercount==4096){
				tm =new TreeMap<Integer,String>();
				tm1 =new TreeMap<String,Integer>();
					for(char i=0;i<256;i++)
					{
			   			tm.put((int)i, Character.toString(i));
			   			tm1.put(Character.toString(i),(int)i);
			   			buffercount = 256;
			   		}
			}
			
			tm.put(buffercount,a);
			tm1.put(a,buffercount);
			buffercount++;
			
		    
		}
		
		/**pubilc method to get the size of the table. 
		 * Best case: big theta(1);
		 * Worst case: big theta(1); 
		 * */
		public int length()
		{
			return tm.size();
		}
	
	}
	
	
   /**This class is to establish a table in a HashMap format to build indexes along the read in of the files. 
     * A HashMap will be initialized when this class is instantiated. ASCII code 0~255 will also automatically 
     * put into the table for use. Because we use 12bits compression. The maximum patterns of string we can hold
     * will be 2^12 = 4096. Therefore, the TreeMap has to be rebuilt once it is full. 
     */
	class DictionaryH
	{   
			
		HashMap<Integer,String> tm = new HashMap<Integer,String>();
		HashMap<String,Integer> tm1 = new HashMap<String,Integer>();
		    
		int buffercount = 256;
		

		/**Default Constructor to initialize needed table for building indexes of strings
		 * 255 ASCII code will be included at the beginning. New words starts from 256
		 * No needs to consider big theta for constructor.
		 * */
		public DictionaryH()
		{
					
			for(char i=0;i<256;i++)
			{
	   			tm.put((int)i, Character.toString(i));
	   			tm1.put(Character.toString(i),(int)i);
	   		}
		}

		/**
		 * @param a String type for the string we put into the table
		 * This add method if called will add the given string into the table we have built.
		 * if the accumulative words reached 4096 in total, clear the table and start from 256 again. 
		 * Best case: big theta(1);
		 * Worst case: big theta(1);
		 */
		public void add (String a)
		{   
			if(buffercount==4096){
				tm =new HashMap<Integer,String>();
				tm1 =new HashMap<String,Integer>();
					for(char i=0;i<256;i++)
					{
			   			tm.put((int)i, Character.toString(i));
			   			tm1.put(Character.toString(i),(int)i);
			   			buffercount = 256;
			   		}
			}
			
			tm.put(buffercount,a);
			tm1.put(a,buffercount);
			buffercount++;
			
		    
		}

		/**pubilc method to get the size of the table. 
		 * Best case: big theta(1);
		 * Worst case: big theta(1); 
		 * */
		public int length()
		{
			return tm.size();
		}
		
}
