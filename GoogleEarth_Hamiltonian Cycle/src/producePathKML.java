/**
 * @category Data Structures & Algorithm Homework 4-3
 * @author Preston Lin
 * @date March 31, 2014
 * @note Based on Project 4-1 & 4-2 to produce the Google Earth File that represents the two paths we derived
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;



/** This class is hold main routine that will call the program compiled for project 4-1 and 4-2*/
public class producePathKML {

	/**This is the main method. It will ask for the user to input 2 interger for further processing.
	 * The application built in project 4-1 and 4-2 will be run and produce the exact results.
	 * Those path will be record by the latitude and longitude of each vertex via the Google Earth kml format.
	 * A kml file will be produced for use at the specified path.*/
	public static void main(String[] args) throws FileNotFoundException{
		
		CrimeData cd = new CrimeData();
		CrimeData_Optimal cd_opt = new CrimeData_Optimal();
		
		System.out.println("CrimeLatLonXY1990.csv has been loaded"); 
		Scanner input = new Scanner(System.in); 
    	System.out.println("Enter the start index:"); 
    	int start = Integer.parseInt(input.nextLine()); 
    	System.out.println("Enter the end index:");
    	int end = Integer.parseInt(input.nextLine()); 
    	System.out.println("Crime Records Processed.");	

    	cd.CalcCycle(start, end);
    	cd_opt.CalcCycle(start, end);
    	
    	PrintWriter writer = new PrintWriter("/Users/Preston/Desktop/PGHCrimes.kml");
        String globe_TSP ="";
        String globe_OPT ="";
    	
        for(int i=start-start;i<=end-start+1;i++){
    		globe_TSP += cd.crimeData.get(cd.preOrder.get(i)+start).getLongt()+","+cd.crimeData.get(cd.preOrder.get(i)+start).getLat()+","+"0.000000"+"\n";
    	} //construct valid format of vertex on google earth
        
        for(int j=start-start;j<=end-start+1;j++){
    		globe_OPT += cd.crimeData.get(cd_opt.path[j]+start).getLongt()+","+cd.crimeData.get(cd_opt.path[j]+start).getLat()+",0.000000"+"\n";
    	}//construct valid format of vertex on google earth
    	
        //Based on the format of KML file, rewrite the most update information.
        writer.println(
        		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?> "+"\n"+
        	    "<kml xmlns=\"http://earth.google.com/kml/2.2\">"+"\n"+
				"<Document>"+"\n"+
				"<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">"+"\n"+
				"<LineStyle>"+"\n"+
				"<color>73FF0000</color>"+"\n"+
				"<width>5</width>"+"\n"+
				"</LineStyle>"+"\n"+
				"</Style>"+"\n"+
				"<Style id=\"style5\">"+"\n"+
				"<LineStyle>"+"\n"+
				"<color>507800F0</color>"+"\n"+
				"<width>5</width>"+"\n"+
				"</LineStyle>"+"\n"+
				"</Style>"+"\n"+
				"<Placemark>"+"\n"+
				"<name>TSP Path</name>"+"\n"+
				"<description>TSP Path</description>"+"\n"+
				"<styleUrl>#style6</styleUrl>"+"\n"+
				"<LineString>"+"\n"+
				"<tessellate>1</tessellate>"+"\n"+
				"<coordinates>"+"\n"+
				globe_TSP+
				"</coordinates>"+"\n"+
        		"</LineString>"+"\n"+
        		"</Placemark>"+"\n"+
        		"<Placemark>"+"\n"+
        		"<name>Optimal Path</name>"+"\n"+
				"<description>Optimal Path</description>"+"\n"+
				"<styleUrl>#style5</styleUrl>"+"\n"+
				"<LineString>"+"\n"+
				"<tessellate>1</tessellate>"+"\n"+
				"<coordinates>"+"\n"+
				globe_OPT+
				"</coordinates>"+"\n"+
        		"</LineString>"+"\n"+
        		"</Placemark>"+"\n"+
        		"</Document>"+"\n"+
        		"</kml>");
        writer.close();
        
        System.out.println("");
    	
        DecimalFormat df = new DecimalFormat("#.00"); //format the value with 2 decimals
    	df.format(cd.totalMinDistance);
    	System.out.println("Hamiltonan Cycle: "+ cd.preOrder.toString());
    	System.out.println("Length of Cycle: "+ df.format(cd.totalMinDistance) +" miles");
        
    	System.out.println("The Google Earth Presentation File has been produced!");
    	System.out.println("Optimal Path: "+ cd_opt.pathtoString());
    	df.format(cd_opt.best_dist);
    	System.out.println("Length of Cycle: "+ df.format(cd_opt.best_dist) +" miles");
		
	}

}
