// Output should be



// third lead
// second lead
// first lead
// 
// reworking list
// second lead
// first lead
// third lead
// 
// reworking list again
// first lead
// third lead
// second lead
// 
// reworking empty list
// 
// reworking list with one element

// Homework problems:
// Draw memory as it exists at Mark 1
// Draw memory as it exists at Mark 2
// Draw memory as it exists at Mark 3
// Draw memory as it exists at Mark 4
// Draw memory as it exists at Mark 5
// What would go wrong if the line at Mark 6 were before the previous line, instead of after it (as it is).? The user would be attempting to place the null node as the first node which is not possible
// Describe what the reworkList method does. Do so in terms of what it accomplishes, not in terms of the algorithm. A: reworkList places the lead node last and places the second node first.
import java.util.Iterator;
public class StringList implements Iterable<String> {
    private Node leadElement;

    public StringList() {
        this.leadElement = null;
    }

    public void addToLead( String s ) {
        Node n = new Node( s, this.leadElement );
        this.leadElement = n;
    }
    
    public void reworkList( ) {
         if (this.leadElement == null) { return; }
         if (this.leadElement.neighbor == null) {
             this.leadElement = null;
             return;
         }
         
         Node ptr = this.leadElement.neighbor;
         while (ptr.neighbor != null) {
             ptr = ptr.neighbor;
         }
         
         Node formerLead = this.leadElement;
         ptr.neighbor = formerLead;
         this.leadElement = formerLead.neighbor;
         formerLead.neighbor = null; // Mark 6
     }
    
    public Iterator<String> iterator() {
        return new SLIterator(this.leadElement);
    }

    private class Node {
        String value;
        Node neighbor;
        
        public Node( String m, Node b ) {
            this.value = m;
            this.neighbor = b;
        }
    }
    
    // Class to allow user to iterate through the linked list.
    private class SLIterator implements Iterator<String> {
        private Node next_node;
        
        // creates an LLIterator given the beginning of a list.
        public SLIterator(Node beginning) {
            next_node = beginning;
        }
        
        // returns true if there are still values to traverse (if the current node reference is not null).
        public boolean hasNext() { return next_node != null; }
        
        // returns the next item in the list, which is the item contained in the current node. The method also needs to move the traversal along to the next node in the list.
        public String next() {
            if (next_node == null)
                return null;
            String ret = next_node.value;
            next_node = next_node.neighbor;
            return ret;
        }
        
        // does nothing. Implementing this function is optional for an Iterator.
        public void remove() {;}
    } // end inner class SLIterator

    
    public static void main( String[] args ) {
        StringList sl = new StringList();
        sl.addToLead( "first lead" );
        sl.addToLead( "second lead" );
        sl.addToLead( "third lead" ); 
        for (String s: sl) {
            System.out.println( s );
        } // mark 1
        System.out.println( "\nreworking list" );
        sl.reworkList();
        for (String s: sl) {
            System.out.println( s );
        } //mark 2

        System.out.println( "\nreworking list again" );
        sl.reworkList();
        for (String s: sl) {
            System.out.println( s );
        } //mark 3
        
        sl = new StringList();
        System.out.println( "\nreworking empty list" );
        sl.reworkList();
        for (String s: sl) {
            System.out.println( s );
        } //mark 4

        sl.addToLead( "apples" );
        System.out.println( "\nreworking list with one element" );
        sl.reworkList();
        for (String s: sl) {
            System.out.println( s );
        } //mark 5
        

    }
    
}