/* This class is to establish checkbox for user to check what operators they want to use in the generation simulation*/


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class CheckBox{

	    JLabel operatorsLabel = new JLabel("Choose Operators Available");   //start to design and build up the interface 
	    
	    JCheckBox plus = new JCheckBox("Plus");
	    static boolean plusVal=true;
	    JCheckBox minus = new JCheckBox("Minus");
	    static boolean minusVal=true;
	    JCheckBox mult = new JCheckBox("Mult");
	    static boolean multVal=true;
	    JCheckBox divide = new JCheckBox("Divide");
	    static boolean divideVal=true;
	    Box operatorsBox = Box.createVerticalBox();
	    Box boxfinal = Box.createHorizontalBox();
	    
	    CheckBox(){
        operatorsBox.add(Box.createHorizontalStrut(15));
        operatorsBox.add(Box.createVerticalStrut(3));
        operatorsBox.add(operatorsLabel);
        
        operatorsBox.add(plus);
        plus.setSelected(true);
        plus.addActionListener(new plusL());
        
        operatorsBox.add(minus);
        minus.setSelected(true);
        minus.addActionListener(new minusL());
        
        operatorsBox.add(mult);
        mult.setSelected(true);
        mult.addActionListener(new multL());
        
        operatorsBox.add(divide);
        divide.setSelected(true);
        divide.addActionListener(new divideL());
        
        
        boxfinal.add(Box.createHorizontalStrut(150));
        boxfinal.add(operatorsBox);
        
        Border opsBrd = 
        BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        boxfinal.setBorder(opsBrd);
         
	    }
	    
        class plusL implements ActionListener {   //Give by lectures, the followin define the action taken when the checkbox is checked
            public void actionPerformed(ActionEvent e) {
            	plusVal = plus.isSelected(); //True or False
                String s = new String();
                s += "Plus is ";
                if (!plusVal)
                    s += "not ";
                s += "selected" + "\n";
                System.out.print(s);	// use for debugging
            }
        }
	    
        class minusL implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            	minusVal = minus.isSelected(); //True or False
                String t = new String();
                t += "Minus is ";
                if (!minusVal)
                    t += "not ";
                t += "selected" + "\n";
                System.out.print(t);	// use for debugging
            }
        }
        
        class multL implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            	multVal = mult.isSelected(); //True or False
                String u = new String();
                u += "Multiply is ";
                if (!multVal)
                    u += "not ";
                u += "selected" + "\n";
                System.out.print(u);	// use for debugging
            }
        }
           
        class divideL implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            	divideVal = divide.isSelected(); //True or False
                String v = new String();
                v += "Divide is ";
                if (!divideVal)
                    v += "not ";
                v += "selected" + "\n";
                System.out.print(v);	// use for debugging
            }
        }           
}

