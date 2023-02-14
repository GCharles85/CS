// Here is code for a binary search tree that contains strings. It uses a slightly

// different strategy for implementing the nodes. 
// 
// There are two classes; TreeNode, which is a node that contains data and NullTreeNode,
// which is a Node that replaces a null pointer. The benefit of this design is that the
// base case for the recursion is handled differently. For example, when we add to a
// TreeNode, we add to one of its children (this is the recursive case) When we add to a
// NullTreeNode, we replace it with a new TreeNode and stop the recursion. For this code
// to work, the add method needs to return a pointer to either that node itself or to a
// new node. That way, we can "link it in" easily.
// 
// Your job: 
// 1. Write a printInOrder method that performs an in-order traversal of the
// tree, printing out the string in each node. You should have a method in
// BinarySearchTree that calls a method in TreeNode. That method in TreeNode should call
// it on each of its children. The method in NullTreeNode should do nothing (it will
// merely act as a base case for the recursion).
// 
// 
// 2. Write a contains method that returns true if the tree contains a given string and
// false if it doesn't. Like printInOrder, you should have a method for each of the three
// classes in this file. The base case in NullTreeNode should return false.

import java.util.Comparator;

public class BinarySearchTree {

    private TreeNode root;
    static NullTreeNode NULL_NODE = new NullTreeNode();
    
    
    public BinarySearchTree(  ) {
        this.root = new NullTreeNode();
    }  
    
    public void add( String string ) {
        this.root  = this.root.add( string );
    }
    
    public void printInOrder() {
		if (this.root != null) {
			this.root.PIO();
		}
	}
    
    public boolean contains(String test) {
    	if (this.root != null) {
			return this.root.Contain(test);
		}
    }
    
    public static void main( String[] args ) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add( "Andorians" );
        bst.add( "Klingons" );
        bst.add( "Bajorans" );
        bst.add( "Vulcans" );
        bst.add( "Romulans" );
        bst.add( "Q" );
        // Uncomment these lines as you write the methods!
//         bst.printInOrder();
//         System.out.println( "contains Bajorans: " + bst.contains( "Bajorans" ) );
//         System.out.println( "contains Orions: " + bst.contains( "Orians" ) );
    }
      
} // end BinarySearchTree class

class TreeNode {
    TreeNode left, right;
    String string;

    public TreeNode() {
        this.left = BinarySearchTree.NULL_NODE;
        this.right = BinarySearchTree.NULL_NODE;
        this.string = null;
    }
    
    public TreeNode( String string ) {
        this.left = BinarySearchTree.NULL_NODE;
        this.right = BinarySearchTree.NULL_NODE;
        this.string = string;
    }  
    
    public TreeNode add( String string ) {
        //  Returns a negative integer, zero, or a positive integer as the 
        //  first argument is less than, equal to, or greater than the second.
        int ret = string.compareTo( this.string );
        if (ret < 0) {
            // needs to go in left subtree
            this.left = this.left.add( string );
        }
        else { 
            this.right = this.right.add( string );
        }
        return this;
    }
    
    public void PIO() {
		if (this.left != null) {
			((NullTreeNode) this.left).printIO();
		}
		System.out.println( this.string );
		if (this.right != null) {
			((NullTreeNode) this.right).printIO();
		}
	}
    
    public boolean Contain(String inNode) {
    	if(this.right.string == null && this.left.string == null) {
    		return CTS();
    	}
    	if(this.string == inNode) {
    		return true;
    	};
    }
   

        
} // end TreeNode class

class NullTreeNode extends TreeNode {

    public NullTreeNode() { super();}
    
    public TreeNode add( String string ) {
        return new TreeNode( string );
    }
    
    public void printIO() {
    	if(this.right == null && this.left == null) {
    		return;
    	}
    }
    
    public boolean CTS() {
    		return false;
    }
    
           
} // end NullTreeNode class
