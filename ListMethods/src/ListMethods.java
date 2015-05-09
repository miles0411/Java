

/**
 * @category JAVA Mid-term
 * @author Preston Lin
 * @date Oct 10,2013
 * @note Print out all the methods of a class the method's name meets the requirment
 */

import java.lang.reflect.Method;   //import javalang.reflect.Method for further usage


public class ListMethods {    //create a class for carrying intended methods
	
	public static void main(String[] args){     //main method to new an Object
		Object object  = new String();          //an Object is created
		listMethods(object);					//use a method to take the Object as argument
	}

	public static void listMethods(Object o){   		//write an static method to take the Object parameter for further processing
		Class c1 = o.getClass();						//transform the Object into Class Class
		Method[] method = c1.getMethods();     			 //create a Method array to store the methods in sequence of the Class
		
		for(int i=0;i <method.length;i++){     			 //use a loop to take a look at the content in the method array
			if(method[i].getName().startsWith("g")){ 	//to meet the requirement, getting out the name for each element in the array
				System.out.println(method[i].getName()); //if the method's name starts with a "g" print out the name. 
			}
		}
		
	}
}
