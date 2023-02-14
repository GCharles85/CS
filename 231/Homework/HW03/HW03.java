

public class HW03{

    

    public static void printBackwards( StackNode s ) {
     StackNode r =  new StackNode();
	 for(int i = 0; i < s.getSize(); i++) {
		r.push(s.pop())	;	 
	 }
	 System.out.print(r);
	
    }
    
    
    public static void main(String argv[]) {
	StackNode s = new StackNode();

	s.push(5);
	s.push(7);
	s.push(9);
	s.push(42);

	System.out.println( "Start: " + s ); // Mark #1

	System.out.println( "Popping:   " + s.pop());
	System.out.println( "Popping:   " + s.pop());

	System.out.println( "Beginning: " + s );

	s.push(42);
	s.push(3);

	System.out.println( "Middle:   " + s );

	System.out.println( "Popping:  " + s.pop());

	System.out.println( "End:      " + s );

	printBackwards(s);
    }
}

Question 1.  Draw a representation of the stack at Mark 1 for a
  node-based implementation.


  Question 2.  Draw a representation of the stack at Mark 1 for an
  array-based implementation.


  Question 3.  For an array-based implementation, what is the relationship
  between the top index and the number of elements on the stack? The top index is the  number of items in the stack.


  Question 4.  For a node-based implementation, what is the
  relationship between the top reference and the number of elements on
  the stack? If the top reference is null, the stack is empty. Otherwise, the top reference will point to the value that was last added.


  Question 5.  Write the printBackwards function, which takes a stack
  as an argument. It should print the elements of the stack from
  bottom to top.  (Hint, you may want to make another stack inside the
  printBackwards function).  



