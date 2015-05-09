/**
 * @category Data Structures & Algorithm Homework 1-1
 * @author Preston Lin
 * @date Jan 27, 2014
 * @note Instantiate DoublyLinkedList as defined. 
 */

/**This class is to specify a single node containing a char variable
 * Precondition: The newly created Node has to know where to point to
 * its next and previous node. If it is points to nothing, null value
 * still has to be given.
 * Postcondition: When this class is instantiated, a new node will hold 
 * 2 DoubleNode type pointers for a Node's previous
 * and next node, and 1 char type parameter for holding the value
 * of this new node.
 * Note: No need to consider Big Theta 
 */
public class DoubleNode {
	
	private DoubleNode PNode=null, NNode=null;
	private char content;
	
	/**@param p n DoubleNode type parameters
	 * @param i char type parameter 
	 * Initialize parameters by taking previous/next
	 * nodes and content to build up a new node
	 */
	public DoubleNode (DoubleNode p, char i, DoubleNode n){
		
		PNode = p;
		NNode = n;
		content = i;
	
	}
	
	/**return the previous node for the current node.
	 * Precondition: The previous node should exist.
	 * Postcondition: If the previous is not present, null value
	 * would be returned;
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public DoubleNode getPrev(){	
		
		return PNode;		
	}
	
	/**Set a pointer to the previous node for the 
	 * current node. 
	 * Precondition: A Node should be given when this
	 * method is called.
	 * Postcondition:if the next node is not present,
	 * a pointer to null value would be defaulted.
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public void setPrev(DoubleNode p){
		
		PNode = p;
			
	}
	
	/**Return the previous node for the current node.
	 * Precondition: The next node should be present.
	 * Postcondition: If the next is not present, null value
	 * would be returned.
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public DoubleNode getNext(){
		
		return NNode;
		
	}
	
	/**Set a pointer to the previous node for the 
	 * next node. 
	 * Precondition: A DoubleNode should be given when this
	 * method is called.
	 * Postcondition: If the next is not present, 
	 * a pointer to null would be defaulted
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public void setNext(DoubleNode n){
		
		NNode = n;
	}
	
	/**Return the value of the current node;  
	 * Precondition: The current node should exist.
	 * Postcondition: if the value of current node wasn't given
	 * at the time the node created, a char containing ' ' 
	 * would be return. 
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public char getC(){
		return content;
	}
	
	/**Set the value of the current node.
	 * Precondition: A char type variable should
	 * be given when this method is called and 
	 * the node is present for setting the value.  
	 * Postcondition: A char type value will be returned.
	 * Best Case:Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public void setC(char c){
		
		content = c;
		
	}
}
