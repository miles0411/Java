/**
 * @category Data Structures & Algorithm Homework 1-1
 * @author Preston Lin
 * @date Jan 27, 2014
 * @note Instantiate DoublyLinkedList as defined. 
 */


/** This class is intended as a list to 
 *  hold DoublyNodes.
 *  Precondition: When this class is initiated
 *  No DoublyNodes has to be given right while created.
 *  The list would hold null objects until
 *  any DoublyNode is created then.
 *  Postcondition: Two DoubleNode type pointers are always 
 *  in place pointing to the head and tail node
 *  if the list has one node, the head and tail 
 *  would both point to the node, while zero node
 *  exists in the list, the pointers would be set 
 *  to point to null
 */
public class DoublyLinkedList {
	
	
	private DoubleNode head=null, tail=null;
	
	/**Override the toString method in java.lang.object.
	 * Precondition: When this method is called, 
	 * the list should not be empty. And the value in each node 
	 * has been properly set.
	 * Postcondition:The entire list will be concatenated 
	 * as a String for return;
	 * Best case: Big theta(n)
	 * Worst case: Big theta(n)
	 */
	public String toString(){
		String list="";
		if(isEmpty()==false){
			DoubleNode temp=head;
			while(temp!=null){
			list += temp.getC();
			temp=temp.getNext();
			}
			return list;
		}
		else{
			return list;
		}
	}
	
	/**Reverse the order of linked nodes.
	 * Precondition: The list has to be not empty. 
	 * Postcondition: After this method is called, 
	 * the order of the linked nodes would be reversed.
	 * Best case: Big theta(n/2)
	 * Worst Case: Big theta(n/2)
	 */
	public void reverse() {
		
		if(isEmpty()==false){
			DoubleNode left=head, right=tail;
			char temp;
			while(left!=right&&right!=left.getPrev()){
				temp=left.getC();
				left.setC(right.getC());
				right.setC(temp);
				left=left.getNext();
				right=right.getPrev();
			}
		}
		
	}
	
	/** Remove the node at the very end. 
	 *  And return the value of the removed node.
	 *  Precondition: The list should be not empty
	 *  and node is present with explicitly set value
	 *  Postcondition: After this method is called 
	 *  The tail pointer would point to tail's previous
	 *  node. the removed node's content
	 *  would be returned;
	 *  Best Case: Big theta(1)
	 *  Worst Case: Big theta(1)
	 */
	public char removeCharAtEnd() {
		
		char c = tail.getC();
		if(tail.getPrev()!=null){
			tail=tail.getPrev();
			tail.setNext(null);
		}
		else{
			head=null;
			tail=null;
		}
		return c;
	}
	
	/** Remove the node at the very first,
	 *  and return the value of the removed node.
	 *  Precondition: The list should be not empty
	 *  and node is present with explicitly set value
	 *  Postcondition: After this method is called 
	 *  The head pointer would point to head's next
	 *  node. the removed node's content
	 *  would be returned;
	 *  Best Case: Big theta(1)
	 *  Worst Case: Big theta(1)
	 */
	public char removeCharFromFront(){
		
		char c = head.getC();
		if(head.getNext()!=null){
			head=head.getNext();
			head.setPrev(null);
		}
		else{
			head=null;
			tail=null;
		}
		return c;
	}
	
	
	/**Evaluate the list is empty or not.
	 * Precondition: Only No node exists causes 
	 * head/tail points to null
	 * Postcondition: If there is no node present in 
	 * the list, which means head and tail
	 * are both pointing to null value, 
	 * True value would be returned, vise versa;
	 * Best Case: Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public boolean isEmpty() {
		
		if(head==null){
		return true;
		}
		else{
		return false;
		}
	}
	
	/** Count from the head to tail
	 *  for how many nodes exists.
	 *  Precondition: The list should be not empty
	 *  Postcondition: If nodes exists, the number
	 *  of nodes will be returned; if the list is empty,
	 *  0 would be directly returned;
	 *  Best Case: Big theta(n)
	 *  Worst Case: Big theta(n)
	 */
	public int countNodes() {
		
		if(isEmpty()==false){
			int count =1;
			DoubleNode temp=head;
			while(temp.getNext()!=null){
				count++;
				temp=temp.getNext();
		}
		return count;
		}
		else{
			return 0;
		}
	}
	
	/**Add a new node at the front containing a char variable
	 * Precondition: @param a type char variable should be given. 
	 * setC(char C) method will be called.
	 * Postcondition: After this method is called, 
	 * a new node is added to the head. If this node is the first node
	 * added, it will be both the head and tail.
	 * Best Case: Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public void addCharAtFront(char c) {
		
		DoubleNode f = new DoubleNode(null, c, null);
		f.setC(c);
		if(head==null&&tail==null){
	    head=f;
	    tail=f;
		}
		else{
			head.setPrev(f);
			f.setNext(head);
			head=f;
		}
	}
	
	/**Delete the node specified. And return a true/false
	 * value if the node is correctly deleted or not.
	 * Precondition: A type char variable should be given 
	 * in order to delete an existing node 
	 * Postcondition: When this method is called, 
	 * the very left node holding the value c would 
	 * be deleted from the list
	 * if no node is deleted, false would
	 * be returned; if any nodes is deleted,
	 * true would be returned;
	 * Best Case: Big theta(1) 
	 * Worst Case: Big theta(n)
	 */
	public boolean deleteChar(char c) {
		
		if(isEmpty()==false){
			DoubleNode temp=head;
			while(temp.getC()!=c){
				temp=temp.getNext();
			}
			if(temp==head&&tail==head){
				head = null;
				tail = null;
				return true;
			}
			else if(temp==head){
				temp.getNext().setPrev(null);	
				head=temp.getNext();
				return true;
			}
			else if(temp==tail){
				temp.getPrev().setNext(null);
				tail=temp.getPrev();
				return true;
			}
			else{
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				return true;
				}
			}
		return false;
	}
	
	/**Add a new node at the end containing a char variable
	 * Precondition: @param a type char variable should be given. 
	 * setC(char C) method will be called.
	 * Postcondition: After this method is called, 
	 * a new node is added to the tail. If this node is the first node
	 * added, it will be both the head and tail.
	 * Best Case: Big theta(1)
	 * Worst Case: Big theta(1)
	 */
	public void addCharAtEnd(char c){
		
		DoubleNode e = new DoubleNode(null, c, null);
		e.setC(c);
		if(tail==null&&head==null){
		tail=e;
		head=e;
		}	
		else{
		tail.setNext(e);
		e.setPrev(tail);;
		tail=e;
			if(head==null){
				head=tail.getPrev();
			}
		}	
	}
	
	
	/** Note: Main method carrying test driver
	 *  specified by instructors;
	 *  No need to consider Big theta
	 *  because it is not part of the 
	 *  program itself
	 */
	public static void main(String a[]) {
		
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('H');
		list.addCharAtEnd('e');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('o');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		System.out.println("Deleting H");
		list.deleteChar('H');
		System.out.println(list);
		System.out.println("Deleting o");
		list.deleteChar('o');
		System.out.println(list);
		System.out.println("Deleting e");
		list.deleteChar('e');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		list.deleteChar('k');
		System.out.println(list);
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println(list);
		
		System.out.println(list.countNodes());
		
		System.out.println("Popping everything");
		while(!list.isEmpty()){
			System.out.println(list.removeCharFromFront());
		}
		
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println("Popping everything from the end");
		while(!list.isEmpty()){
			System.out.println(list.removeCharAtEnd());
		}
		System.out.println(list.countNodes());
		
		list.addCharAtEnd('o');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('e');
		list.addCharAtEnd('H');
	
		list.reverse();
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);
		
	}

}
