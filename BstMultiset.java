import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
	
	Node tree;
	
	public BstMultiset() {
		// Implement me!
	} // end of BstMultiset()

	/**
	 * add element into the BstMultiset
	 */
	public void add(T item) {
		// Implement me!
		//if tree is empty
		if(tree == null){
			tree = new Node(item);
		}else{
			tree.insert(item);
		}
		
	} // end of add()
	
	/**
	 * search method
	 * search for the number of instances that element have in the BstMultiset and prints
	 * this number out. 
	 * The format of the output of a search operation should take the form:
     *              <element> <number of instances in the multiset>
     *              
     * 
	 * If element does not exist in the multiset, then the 0 should be the number of instances returned.
	 */
	public int search(T item) {
		// Implement me!	
		int result = 0;
		if(tree != null){
			Node found = tree.search(item);
			if(found!=null){
				result = found.total;
			}
		}
		// default return, please override when you implement this method
		return result;
	} // end of add()
	
	/**
	 * delete one instance of element from the BstMultiset.
	 */
	public void removeOne(T item) {
		// Implement me!
		if(tree != null){//if tree is not empty
			Node removeNode = tree.search(item);
			if(removeNode !=null && removeNode.total > 1){
				removeNode.total--;
				return;
			}else{
				removeNode(removeNode);
			}
			

		}
	} // end of removeOne()
	
	/**
	 * delete all instances of element from the BstMultiset.
	 */
	public void removeAll(T item) {
		// Implement me!
		if(tree != null){//if tree is not empty
			Node removeNode = tree.search(item);
			removeNode(removeNode);
		}
	} // end of removeAll()
	
	private void removeNode(Node removeNode){
		
		if(removeNode == null){//if there is a node with the item  found in the tree
			return;
		}else if(removeNode.isLeaf()){//if the root is a leaf
			removeLeaf(removeNode);
		}else if(removeNode == tree){//if the found node is the root
			
			if(tree.left == null){//if the left child of the tree is empty
				tree = tree.right;
				tree.father = null;
			}else if(tree.right == null){//if the right child of the tree is empty
				tree = tree.left;
				tree.father = null;
			}else{
				//get the most right node from left child of root
				Node rightMostFromLeft = tree.left.getRightMostChild();
				tree.exchange(rightMostFromLeft);
				if(rightMostFromLeft.isLeaf()){//if the most right node is a leaf
					
					if(rightMostFromLeft.father.left == rightMostFromLeft){
						rightMostFromLeft.father.left = null;
					}else{
						rightMostFromLeft.father.right = null;
					}
					
				}else{//if the rightmost node is not a leaf(which means it has left child)
					
					if(rightMostFromLeft.father.left == rightMostFromLeft){
						rightMostFromLeft.father.left = rightMostFromLeft.left;
						rightMostFromLeft.left.father = rightMostFromLeft.father;
					}else{
						rightMostFromLeft.father.right = rightMostFromLeft.left;
						rightMostFromLeft.left.father = rightMostFromLeft.father;
					}
				}
			}
		}else{//if the removeNode is not the root
			
				if(removeNode.left == null){//if the left child of the removeNode is empty
					if(removeNode.father.left == removeNode){
						removeNode.father.left = removeNode.right;
						removeNode.right.father = removeNode.father;
					}else{
						removeNode.father.right = removeNode.right;
						removeNode.right.father = removeNode.father;
					}
				}else if(removeNode.right == null){//if the right child of the removeNode is empty
					if(removeNode.father.left == removeNode){
						removeNode.father.left = removeNode.left;
						removeNode.left.father = removeNode.father;
					}else{
						removeNode.father.right = removeNode.left;
						removeNode.left.father = removeNode.father;
					}
				}else{
					//get the most right node from left child of removeNode
					Node rightMostFromLeft = removeNode.left.getRightMostChild();
					removeNode.exchange(rightMostFromLeft);
					if(rightMostFromLeft.isLeaf()){//if the most right node is a leaf
						this.removeLeaf(rightMostFromLeft);

					}else{//if the rightmost node is not a leaf(which means it has left child)
						
						if(rightMostFromLeft.father.left == rightMostFromLeft){
							rightMostFromLeft.father.left = rightMostFromLeft.left;
							rightMostFromLeft.left.father = rightMostFromLeft.father;
						}else{
							rightMostFromLeft.father.right = rightMostFromLeft.left;
							rightMostFromLeft.left.father = rightMostFromLeft.father;
						}
					}
				}
			}
	}
	
	private void removeLeaf(Node leaf){
		
		if(leaf.father == null){
			this.tree = null;
		}else{
			if(leaf.father.left == leaf){
				leaf.father.left = null;
			}else{
				leaf.father.right = null;
			}
		}
	}
	
	private int compareItem(T firstItem,T secondItem){
		
		return firstItem.toString().compareTo(secondItem.toString());
	}
	/**
	 * prints the contents of the BstMultiset. 
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
		if(tree != null){
			tree.print(out);
		}
		
	} // end of print()
	
	class Node{
		T item;
		int total;
		Node father;
		Node left;
		Node right;
		
		Node(T item){
			this.item = item;
			this.total = 1;
			this.left = null;
			this.right = null;
			this.father = null;
		}
		
		boolean isLeaf(){
			return this.left == null && this.right == null;
		}
		
		void insert(T item){
		
			int result = compareItem(this.item,item);
			if(result == 0){
				this.total++;
			}else if(result > 0){
				if(this.left == null){
					this.left = new Node(item);
					this.left.father = this;
				}else{
					this.left.insert(item);
				}
			}else{
				if(this.right == null){
					this.right = new Node(item);
					this.right.father = this;
				}else{
					this.right.insert(item);
				}
			}
		}
		
		Node search(T item){
			
			int result = compareItem(this.item,item);
			if(result == 0){
				return this;
			}else if(result > 0){
				if(this.left == null){
					return null;
				}else{
					return this.left.search(item);
				}
			}else{
				if(this.right == null){
					return null;
				}else{
					return this.right.search(item);
				}
			}
		}
		
		Node getRightMostChild(){
			if(this.right == null){
				return this;
			}else{
				return this.right.getRightMostChild();
			}
		}
		
		void exchange(Node node){
			
			T tempItem = this.item;
			int tempTotal = this.total;
			
			this.item = node.item;
			this.total = node.total;
			
			node.item = tempItem;
			node.total = tempTotal;
		}
		
		void print(PrintStream out){
			
			if(this.left != null){
				this.left.print(out);
			}
			out.println(this.item.toString() + " | " + this.total);
			if(this.right != null){
				this.right.print(out);
			}
		}
		
		
		
	}

} // end of class BstMultiset
