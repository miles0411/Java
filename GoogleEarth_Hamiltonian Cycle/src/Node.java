/**
 * @category Data Structures & Algorithm Homework 4-3
 * @author Preston Lin
 * @date March 31, 2014
 * @note Based on Project 4-1 & 4-2 to produce the Google Earth File that represents the two paths we derived
 */

/**This class is designed to hold the value defined by a node*/
public class Node {
	/**member varaible to hold the data of a node*/
	private double x;
	/**member varaible to hold the data of a node*/
	private double y;
	/**member varaible to hold the data of a node*/
	private String time;
	/**member varaible to hold the data of a node*/
	private String street;
	/**member varaible to hold the data of a node*/
	private String office;
	/**member varaible to hold the data of a node*/
	private String date;
	/**member varaible to hold the data of a node*/
	private String tract;
	/**member varaible to hold the data of a node*/
	private String Lat;
	/**member varaible to hold the data of a node*/
	private String Long;
	/**member varaible to hold the data of a node*/
	private boolean visited;
	/**member varaible to hold the data of a node*/
	private int no;
	
	/**Default constructor*/
	Node(double x, double y, String time, String street, String office, String date, String tract, String Lat, String Long){
		
		this.x = x;
		this.y = y;
		this.time = time;
		this.street = street;
		this.office = office;
		this.date = date;
		this.tract = tract;
		this.Lat = Lat;
		this.Long = Long;
		this.visited = false;
		
	}
	
	/**This is to set the order of a node in a linkedlist
	 * Precondition: The parameter is given
	 * Postcondition: The member variable is set to be equal to the given value
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public Node setNo(int i){
	
		no = i;
		return this;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public int getNo(){
		
		return no;
	}
	
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public double getX(){
		
		return x;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public double getY(){
		
		return y;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getTime(){
		
		return time;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getStreet(){
		
		return street;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getOffice(){
		
		return office;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getDate(){
		
		return date;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getTract(){
		
		return tract;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getLat(){
		
		return Lat;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getLongt(){
		
		return Long;
	}
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public boolean getVisited(){
		
		return visited;
	}
	/**This is to set the boolean value in a node to indicate it's visited
	 * Precondition: N/A
	 * Postcondition: The value is set to be true
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public void setVisited(){
		
		this.visited=true;
	}
	
	/**This is to describe the data of a node 
	 * Precondition: N/A
	 * Postcondition: A string representation of a node will be returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String toString(){
		
		return x+", "+y+", "+time+", "+street+", "+office+", "+date+", "+tract+", "+Lat+", "+Long+"\n";
	}
	
}
