/**
 * @category JAVA Homework 9
 * @author Preston Lin
 * @date Dec 5,2013
 * @note Establish GUI interface. Allow the user to load the dataset, input the parameters, and choose the Operators.
 * 		 After users click "RUN", the program will start to create a binary tree composed of nodes defined by the depth.
 *       Numerous Trees get created and each tree is evaluated by fitness  
 *		 The fitness is defined by variance in statistics, and the tree with best fitness(with the smallest variance) will be retained 
 *       Successively create 5 generations which are born by their previous generation and printout the best tree in each generation. 
 *       For those parts worked in previous assignment, no further comment is provided if not necessary 
 *       Note: Part of codes were given by the Professor Stephen. We were asked to do some modifications.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

import java.util.*;

public class OpenFileDialog extends JFrame { 
	    JButton openButton = new JButton("Choose Data File"); //start to build label, button, textfield for the user interface
	    JLabel filenameLabel = new JLabel("Current File");
	    JTextField filename = new JTextField(20);
	    JTextArea fileContents = new JTextArea(7, 20); 
	    JScrollPane fileScrollPane = new JScrollPane(fileContents);
	    JButton runEvolution = new JButton("Run The Best Generation Fitness");
	    JButton clear = new JButton("Clear");
	    JTextArea resulttext = new JTextArea(15,59);
	    JScrollPane resultPane = new JScrollPane(resulttext);
	    Parameter setting = new Parameter();
	    String longName;
	    
	    public OpenFileDialog() {   //start to pack everything widget into the Container
	        setTitle("Generation Tree Simulation");
	        Box fileOpenDisplayBox = Box.createVerticalBox();
	        openButton.addActionListener(new OpenL()); //add actionlistener to call a method that pops out loading file window
	        fileOpenDisplayBox.add(openButton);
	        fileOpenDisplayBox.add(Box.createVerticalStrut(4));
	        fileOpenDisplayBox.add(filenameLabel);
	        fileOpenDisplayBox.add(filename);
	        fileOpenDisplayBox.add(Box.createVerticalStrut(4));
	        fileOpenDisplayBox.add(fileScrollPane);
	        JPanel fileOpenDisplayPanel = new JPanel();  
	        fileOpenDisplayPanel.add(fileOpenDisplayBox); 
	        Border brd = BorderFactory.createMatteBorder(1,1,1,1, Color.black); 
	        fileOpenDisplayPanel.setBorder(brd); //part of this method are taken from the class lecture
	        
	        Container cp = getContentPane();
	        cp.setLayout(new FlowLayout());
	        cp.add(fileOpenDisplayPanel);
	        cp.add(setting.parameterPanel);   
	        
	        JPanel result = new JPanel();
	        Box resultarea1 = Box.createHorizontalBox();
	        Box resultarea2 = Box.createVerticalBox();
	        //resulttext.setLineWrap(true);
	        
	        runEvolution.addActionListener(new run());
	        clear.addActionListener(new clear());
	        resultarea1.add(runEvolution);
	        resultarea1.add(clear);
	        resultarea2.add(resultarea1);
	        resultarea2.add(resultPane);
	        //resultarea.setBorder(brd);
	        result.add(resultarea2);
	        cp.add(result);    //complete building the operating interface.
	        	        
	    }
	    
	    class OpenL implements ActionListener {  //this is given by the class lecture; when this actionlistener is called, the interface will allow user to load dataset
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser c = new JFileChooser("/Users/Preston/Documents/workspace/EvolutionTreesGUI"); //default path
	            int rVal = c.showOpenDialog(OpenFileDialog.this);
	            if (rVal == JFileChooser.APPROVE_OPTION) {  //if the user click on Choose
	                filename.setText(c.getSelectedFile().getName());
	                longName = c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName();
	                SimpleInput si = new SimpleInput(longName);
	                String line;
	                while ((line = si.nextLine()) != null) {
	                    fileContents.append(line);
	                    fileContents.append("\n");
	                }
	            
	            if (rVal == JFileChooser.CANCEL_OPTION) { //if the user click on Cancel
	                filename.setText("");
	            }            	
	            }
	        }
	    }
	  
	    
	    class run extends Exception implements ActionListener{  //this actionliseter will be called when RUN button is clicked
	    														//and start to take parameters input by the user
	    	
			public void actionPerformed(ActionEvent e) {
			if(e.getSource()==runEvolution){
				int geneNum = 0, TreeNum=0, randseed=0;
				resulttext.setText("");
				if(!setting.generationNumtxt.getText().equals("")&&
						!setting.TreeNumtxt.getText().equals("")&&
						!setting.Randomseedtxt.getText().equals("")&&
						!(longName==null)){
				geneNum = new Integer(setting.generationNumtxt.getText()).intValue();
				TreeNum = new Integer(setting.TreeNumtxt.getText()).intValue();
				randseed = new Integer(setting.Randomseedtxt.getText()).intValue();    
						}
				else{
					Exception exp = new Exception();				
					try {
						throw exp;
					} catch (Exception e1) {
						resulttext.append("Parameters are missing!");						
					}
				}
				
				ArrayList<Node> node = new ArrayList<Node>();  //new an Arraylist to determien what operator will be used
				CheckBox check = new CheckBox();
				
				if(check.plusVal){
					node.add(new Plus());
				}
				if(check.minusVal){
					node.add(new Minus());
				}
				if(check.multVal){
					node.add(new Mult());
				}
				if(check.divideVal){
					node.add(new Divide());
				}
				
		        Platform pl=null;    //transport parameters to the bridge class 
				pl = new Platform(geneNum, TreeNum, randseed, node, longName);
				resulttext.append(pl.getResult());  //retrieve for the result
				}
			}
	    }
	    
		class clear implements ActionListener{  // this is to allow user clean up the previous result
			public void actionPerformed(ActionEvent e) {  
				if(e.getSource()==clear){
					resulttext.setText("");
				}
			
			}
		}
		 
		 public static void main(String[] args){
		        Console.run(new OpenFileDialog(), 720, 530);    //main method will start here, given by the lecture in order to run application in applet
		        
		 } 
}


