import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	Node head = null; //the head node of linkedlistMultiset
	Node tail = null;
	
	public LinkedListMultiset() {
		// Implement me!
		head = null;
	} // end of LinkedListMultiset()
	
	/**
	 * add element into the LinkedListMultiset
	 */
	public void add(T item) {
		// Implement me!
		if(head ==null){
			head = new Node(item);
			tail = head;
		}else{
			Node found = findNodeWith(item);
			if(found != null){
				found.total++;
			}else{
				Node newNode = new Node(item);
				tail.next = newNode;
				tail = newNode;
			}
		}
	} // end of add()
	
	/**
	 * search method
	 * search for the number of instances that element have in the LinkedListMultiset and prints
	 * this number out. 
	 * The format of the output of a search operation should take the form:
     *              <element> <number of instances in the multiset>
     *              
     * 
	 * If element does not exist in the multiset, then the 0 should be the number of instances returned.
	 */
	public int search(T item) {
		// Implement me!		
		Node found = this.findNodeWith(item);
		return found == null ? 0 : found.total;
	} // end of add()
	

	
	/**
	 * delete one instance of element from the LinkedListMultiset.
	 */
	public void removeOne(T item) {
		// Implement me!
		if(head != null){
			if(head.item.toString().equals(item.toString())){
				
				if(head.total > 1){
					head.total--;
				}else{
					head = head.next;
					if(head == null){
						tail = null;
					} 
				}
				
			}else{
				Node cur = head;
				while( cur.next != null ){
					if(cur.next.item.toString().equals(item.toString())){
						if(cur.next.total > 1){
							cur.next.total--;
						}else{
							cur.next = cur.next.next;
							if(cur.next == null){
								tail = cur;
							}
						}
						break;
					}
					cur = cur.next;
				}
			}
		}
	} // end of removeOne()
	
	/**
	 * delete all instances of element from the LinkedListMultiset.
	 */
	public void removeAll(T item) {
		// Implement me!
		
		if(head != null){
			if(head.item.toString().equals(item.toString())){
				head = head.next;
				if(head == null){
					tail = null;
				} 
			}else{
				Node cur = head;
				while( cur.next != null ){
					if(cur.next.item.toString().equals(item.toString())){
						cur.next = cur.next.next;
						if(cur.next == null){
							tail = cur;
						}
						break;
					}
					cur = cur.next;
					
				}
			}
		}
		
	} // end of removeAll()
	
	
	/**
	 * prints the contents of the LinkedListMultiset. 
	 * 
	 * 
	 * The print operation should output a number of lines. 
	 * Each line specifies an element and the number of instances
	 *  of it in the multiset:
	 *  
     *             <element> | <number of instances in the multiset>
	 */
	public void print(PrintStream out) {
		// Implement me!
		Node cur = head;
		while(cur != null){
			out.println(cur.item.toString() + " | " + cur.total);
			cur = cur.next;
		}
	} // end of print()
	
	/**
	 *  loop list and return the node which's item equals the para item.
	 * @param item
	 * @return
	 */
	private Node findNodeWith(T item){
		Node node = null;
		Node cur = head;
		while(cur != null){
			if(cur.item.toString().equals(item.toString())){
				node = cur;
				break;
			}else{
				cur = cur.next;
			}
		}
		return node;
	}
	
	
	/**
	 * LinkedList node
	 *
	 */
	class Node{
		T item;
		int total;
		Node next;
		
		Node(T item){
			this.item = item;
			this.total = 1;
			this.next = null;
		}
		
	}
	
} // end of class LinkedListMultiset