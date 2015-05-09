/* This class to build up an area for users to input the parameters that will need to be defined in the Evolver*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Parameter extends JFrame{  //start to design/create the interface
	
	JLabel generationNum = new JLabel("The Number of Generation");
	JLabel TreeNum = new JLabel("The Number of Population of Trees");
	JLabel Randomseed = new JLabel("Random Number Seed");	
    JTextField generationNumtxt = new JTextField(10);
    JTextField TreeNumtxt = new JTextField(10);
    JTextField Randomseedtxt = new JTextField(10);
    JPanel parameterPanel = new JPanel();
    
    
    public Parameter(){
    Border opsBrd = BorderFactory.createMatteBorder(1,1,1,1, Color.black);
    Box parameterbox1 = Box.createVerticalBox();
    Box parameterbox2 = Box.createVerticalBox();
    Box parameterbox3 = Box.createHorizontalBox();
    Box parameterbox4 = Box.createVerticalBox();
    
    parameterbox2.add(Box.createHorizontalStrut(0));
    parameterbox2.add(generationNum);
    parameterbox2.add(Box.createHorizontalStrut(0));
    parameterbox2.add(TreeNum);
    parameterbox2.add(Box.createHorizontalStrut(0));
    parameterbox2.add(Randomseed);
    parameterbox2.add(Box.createHorizontalStrut(0));
    parameterbox1.add(generationNumtxt);
    parameterbox1.add(TreeNumtxt);
    parameterbox1.add(Randomseedtxt);
    parameterbox3.add(parameterbox2);
    parameterbox3.add(Box.createHorizontalStrut(50));
    parameterbox3.add(parameterbox1);
   
    parameterbox4.add(parameterbox3);
  
    parameterbox4.add(new CheckBox().boxfinal);
    
    parameterPanel.add(parameterbox4);
    parameterPanel.setBorder(opsBrd);
    
    }
}
