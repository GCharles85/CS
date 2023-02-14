public class BinaryTree<T> {

	//fields
	private Node<T> root;
	
	//constructor
	public BinaryTree() {
		this.root = null;
	}
	
	public void printPreOrder() {
		if (this.root != null) {
			this.root.printPreOrder();
		}
	}
	
	public void printInOrder() {
		if (this.root != null) {
			this.root.printInOrder();
		}
	}
	
	public void printPostOrder() {
		if (this.root != null) {
			this.root.printPostOrder();
		}
	}

    public static void main( String[] args ) {
        BinaryTree tree = new BinaryTree<String>();
        
        tree.root = new Node<String>( "A" );
        tree.root.left = new Node<String>( "B" );
        tree.root.right = new Node<String>( "C" );
        tree.root.left.left = new Node<String>( "D" );
        tree.root.right.left = new Node<String>( "E" );
        tree.root.right.left.right = new Node<String>( "F" );
        
        System.out.println( "pre-order" );
        tree.printPreOrder();
        
        System.out.println( "in-order" );
        tree.printInOrder();
        
        System.out.println( "post-order" );
        tree.printPostOrder();
        
    } // end main
    
} // end BinaryTree class

//Inner class node
class Node<T> {
	Node left;
	Node right;
	T value;
	
	public Node( T value ) {
		this.left = null;
		this.right = null;
		this.value = value;
	}
	
	public void printPreOrder() {
		System.out.println( this.value );
		if (this.left != null) {
			this.left.printPreOrder();
		}
		if (this.right != null) {
			this.right.printPreOrder();
		}
	}

	public void printInOrder() {
		if (this.left != null) {
			this.left.printInOrder();
		}
		System.out.println( this.value );
		if (this.right != null) {
			this.right.printInOrder();
		}
	}

	public void printPostOrder() {
		if (this.left != null) {
			this.left.printPostOrder();
		}
		if (this.right != null) {
			this.right.printPostOrder();
		}
		System.out.println( this.value );
	}

}