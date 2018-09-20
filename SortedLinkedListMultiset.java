import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	Node head = null; //the head node of SortedLinkedListMultiset
	Node tail = null;
	
	public SortedLinkedListMultiset() {
		// Implement me!
		head = null;
	} // end of LinkedListMultiset()
	
	/**
	 * add element into the SortedLinkedListMultiset
	 */
	public void add(T item) {
		// Implement me!
		if(head ==null){
			head = new Node(item);
			tail = head;
		}else{
			
			int result = this.compareItem(head.item, item);
			if(result > 0){
				Node newNode = new Node(item);
				newNode.next = head;
				head = newNode;
				return;
			}else if(result == 0){
				head.total++;
				return;
			}
			
			Node found = findNodePositionWith(item);
			if(found != null){
				if(found.item.toString().equals(item.toString())){
					found.total++;
				}else{
					Node newNode = new Node(item);
					newNode.next = found.next;
					found.next = newNode;
					if(newNode.next == null){
						tail = newNode;
					}
				}
			}else{
				Node newNode = new Node(item);
				tail.next = newNode;
				tail = newNode;
			}
		}
	} // end of add()
	
	/**
	 * search method
	 * search for the number of instances that element have in the SortedLinkedListMultiset and prints
	 * this number out. 
	 * The format of the output of a search operation should take the form:
     *              <element> <number of instances in the SortedLinkedListMultiset>
     *              
     * 
	 * If element does not exist in the SortedLinkedListMultiset, then the 0 should be the number of instances returned.
	 */
	public int search(T item) {
		// Implement me!	
		if(this.head == null){
			return 0;
		}
		int result = this.compareItem(this.head.item, item);
		if(result == 0){
			return this.head.total;
		}
		result = this.compareItem(this.tail.item, item);
		if(result < 0){
			return 0;
		}

		Node found = this.findNodePositionWith(item);
		if(found == null){
			return 0;
		}
		return !found.item.toString().equals(item.toString()) ? 0 : found.total;
	} // end of add()
	

	
	/**
	 * delete one instance of element from the SortedLinkedListMultiset.
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
					int result = this.compareItem(cur.next.item, item);
					if(result == 0){
						if(cur.next.total > 1){
							cur.next.total--;
						}else{
							cur.next = cur.next.next;
							if(cur.next == null){
								tail = cur;
							}
						}
						return;
					}else if(result > 0){
						return;
					}else{
						cur = cur.next;
					}
				}
			}
		}
	} // end of removeOne()
	
	/**
	 * delete all instances of element from the SortedLinkedListMultiset.
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
					int result = this.compareItem(cur.next.item, item);
					if(result == 0){
						cur.next = cur.next.next;
						if(cur.next == null){
							tail = cur;
						}
						return;
					}else if(result > 0){
						return;
					}else{
						cur = cur.next;
					}
				}
			}
		}
		
	} // end of removeAll()
	
	
	/**
	 * prints the contents of the SortedLinkedListMultiset. 
	 * 
	 * 
	 * The print operation should output a number of lines. 
	 * Each line specifies an element and the number of instances
	 *  of it in the SortedLinkedListMultiset:
	 *  
     *             <element> | <number of instances in the SortedLinkedListMultiset>
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
	private Node findNodePositionWith(T item){
		Node node = null;
		Node cur = head;
		while(cur.next != null){
			int result = this.compareItem(cur.next.item, item);
			if(result == 0){
				return cur.next;
			}else if(result > 0){
				return cur;
			}else{
				cur = cur.next;
			}
		}
		return node;
	}
	
	private int compareItem(T firstItem,T secondItem){
		
		return firstItem.toString().compareTo(secondItem.toString());
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
	
} // end of class SortedLinkedListMultiset