import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataSet {

	protected int numVar;
	protected int numLine;
	protected double variable;
	ArrayList<DataRow> list = new ArrayList<DataRow>();  // create an Arraylist whose data type is DataRow for further use
	public DataSet() {}
	
	public DataSet(String s){
		
        //System.out.println("The file is being read."); 
            try {											
            	Scanner file = new Scanner(new File(s));	//new a file to use the Scanner class     
                if(file.hasNextInt()){						
                		numVar = file.nextInt();			//the first int in the .txt means the number of independent variable 
                		
                		if(file.hasNextInt()){
                			numLine = file.nextInt();		//the second int in the .txt means the number of sets of y=f(x[])
                		}
                		for(int i=0; i<numLine;i++){	//create a loop to take in the y and x and store them row by row in Arraylist
                			list.add(i, new DataRow());
                			list.get(i).y = file.nextDouble();
                			list.get(i).x = new double[numVar];
                			for(int j=0; j<numVar;j++){
                				list.get(i).x[j] = file.nextDouble();		
                			}
                		}
                }
            	
            }
            
            catch (FileNotFoundException e) {				//catch any exception identified 
            	System.out.println("No such file is found.");
            }	
	}

}

