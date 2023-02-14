/* Bruce Maxwell
  CS 231
*/

	public class StackNode {
	    Node top;
	    int  size;

	    // constructor for the stack
	    public StackNode() {
		this.top = null;
		this.size = 0;
	    }

	    // put something onto the stack
	    public void push( int value ) {
		Node n = new Node( value, this.top ); // create and initialize
		this.top = n; // adjust top to the new node
		this.size++;
	    }

	    // take something off the stack and return it
	    public int pop() {
		if( this.top == null ) {
		    System.out.println("pop: empty stack");
		    return 0;
		}
		// step 1: save the return value
		int retval = top.getValue();   // save return value
		this.top = this.top.getNext(); // adjust top
		this.size--;

		return retval; // return the top value
	    }

	    public void clear() {
		this.top = null;
	    }

	    public boolean empty() {
		return this.top == null;
	    }

	    public int getSize() {
		return this.size;
	    }

	    public String toString() {
		String s = "";
		Node p = this.top;

		while( p != null ) {
		    s += p.getValue() + " : ";
		    p = p.getNext();
		}
		return s;
	    }


	    private class Node {
		int value;
		Node next;

		public Node(int v, Node n) {
		    this.value = v;
		    this.next = n;
		}
		public int  getValue() {return this.value;}
		public Node  getNext() {return this.next;}
		public void setNext(Node n) {this.next = n;}
	    }

	    public static void main(String argv[]) {
		StackNode n = new StackNode();

		n.push(3);
		n.push(2);
		n.push(1);

		System.out.println(n);

		n.pop();

		System.out.println(n);

		n.clear();

		System.out.println(n);

	    }

}


