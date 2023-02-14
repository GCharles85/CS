public class PersonPQ {

	private Person[] heap;
	private int size; // how many persons actually in heap

	//constructor
	public PersonPQ(int capacity) {
		size = 0;
		heap = new Person[capacity]; 
	}

	//size method
	public int size() {
		return this.size;
	}

	//isEmpty() method
	public boolean isEmpty() {
		return size == 0;
	}

	//add() method
	public void add( Person p ) {
		heap[size++] = p; //add to lower level - last position leaf
		moveUp(); // reheapify
	}
	
	//you can add an ensureCapacity method to double size

	//moveUp() method
	private void moveUp() {
		//the last leaf position
		int child = size - 1;	
		
		// swap with parents until we reach the root, unless we fixed the heap on our way up.
		while (child > 0) {
			
			// compare keys of child node and parent
			int parent = (child - 1)/2;
			int compare = heap[child].compareTo(heap[parent]);

			// 1 means this child node has a bigger key than the parent 
		    // so we swap
			if (compare > 0) {
				swap(child, parent);
				
				// update the curr index to the parent's
				child = (child - 1)/2;
			}
			else {
				return;		//we are done		
			}
		}
	}

	//swap() method
	private void swap(int a, int b) {
		Person tmp = heap[a];
		heap [a] = heap [b];
		heap [b] = tmp;	
	}

	public Person remove() {
		if (this.size == 0) { 
			return null; 
		}
		
		//save the person with the max age (root)
		Person p = heap[0];
		
		// replace the root with the last node
		heap[0] = heap[--size];
		heap[size] = null;
		reheapDown();
		return p;		
	}

	private void reheapDown() {
		int parent = 0; // parent
		int lChild = 2 * parent + 1; // left child
		int rChild = lChild + 1; // right child

		// keep swapping parent (out-of-place) with bigger child 
		//down the heap until we hit the bottom.
		// We know we haven't hit the bottom yet if the leftInd is still in-bounds
		// (valid index in the heap).
		while (lChild <= size - 1)
		{
			// Goal: Figure out which child is bigger
			//
			// We know that we have a left child. Assume its bigger for now
			int bigChildInd = lChild;
			// if we also have a right child and if it has a bigger key, 
			//update the right child
			if ((rChild <= size-1) && (heap[rChild].compareTo(heap[lChild]) > 0))
				bigChildInd = rChild;

			// is the parent b or equal to the smaller child
			int compare = heap[parent].compareTo(heap[bigChildInd]);
			// if the parent is larger than the child...swap with bigger child
			if (compare < 0)
			{
				swap(parent, bigChildInd);

				// update indices
				parent = bigChildInd;
				lChild = 2 * parent + 1;
				rChild = lChild + 1;;	
			}
			// we're done, get out
			else
			{
				return;
			}
		}
	}

	public void printAsArray( ) {
		for (Person p : this.heap ) {
			if (p != null) {
				System.out.print( p.getAge() + " " );
			}
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		PersonPQ pq = new PersonPQ(20);
		pq.add( new Person( 1 ) );
		pq.add( new Person( 2 ) );
		pq.add( new Person( 3 ) );
		pq.add( new Person( 4 ) );
		pq.add( new Person( 8 ) );
		System.out.println("Size: " +pq.size);
		pq.printAsArray();
		System.out.println();

		pq.remove();
		System.out.println("Size: " +pq.size);
		pq.printAsArray();
		
		System.out.println();
		pq.add( new Person( 5 ) );
		System.out.println("Size: " +pq.size);
		pq.printAsArray();
	}
}



