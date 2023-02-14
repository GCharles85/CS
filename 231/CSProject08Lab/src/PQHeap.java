import java.util.Comparator;

/*Guyriano Charles
 * 4/23/19
 * CS231
 */


public class PQHeap<T> {
	Object[] heap;
	int numEl;
	Comparator<Object> comp;
	
	
	public PQHeap(Comparator<Object> comparator) {
		this.comp = comparator;
		this.numEl = 0;
		this.heap = new Object[10];
	}
	
	public int size() {
		return this.numEl;
	}
	
	public void add(T obj) {
		if(this.heap.length == this.numEl) {
			ensureCapacity();
		}
		this.heap[this.numEl++] = obj;
		heapUp();
	}
	
	public void ensureCapacity() {
		Object[] newH = new Object[this.numEl*2];
		for(int i = 0; i < this.numEl; i++) {
			newH[i] = this.heap[i];
		}
		this.heap = newH;
	}
	
	private void heapUp(){
		
			//the last leaf position
			int child = this.numEl - 1;	
			
			// swap with parents until we reach the root, unless we fixed the heap on our way up.
			while (child > 0) {
				
				// compare keys of child node and parent
				int parent = (child - 1)/2;
				@SuppressWarnings("unchecked")
				int compare = this.comp.compare( (T) heap[parent], (T) heap[child]);

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
			Object tmp = heap[a];
			heap [a] = heap [b];
			heap [b] = tmp;	
		}
		
		public Object remove() {
			if (this.numEl == 0) { 
				return null; 
			}
			
			//save the person with the max age (root)
			Object p = heap[0];
			
			// replace the root with the last node
			heap[0] = heap[--this.numEl];
			heap[this.numEl] = null;
			heapDown();
			return p;		
		}
		
		private void heapDown() {
			int parent = 0; // parent
			int lChild = 2 * parent + 1; // left child
			int rChild = lChild + 1; // right child

			// keep swapping parent (out-of-place) with bigger child 
			//down the heap until we hit the bottom.
			// We know we haven't hit the bottom yet if the leftInd is still in-bounds
			// (valid index in the heap).
			while (lChild <= this.numEl - 1)
			{
				// Goal: Figure out which child is bigger
				//
				// We know that we have a left child. Assume its bigger for now
				int bigChildInd = lChild;
				// if we also have a right child and if it has a bigger key, 
				//update the right child
				if ((rChild <= this.numEl-1) && (this.comp.compare( rChild, lChild)  > 0))
					bigChildInd = rChild;

				// is the parent b or equal to the smaller child
				int compare = this.comp.compare(heap[parent], heap[bigChildInd]);
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
		
		public int parent(int index) {
			return (index -1)/2;
		}
		
        public int leftChild(int index) {
			return (2*index) + 1;
		}
        
        public int rightChild(int index) {
			return (2*index) + 2;
		}
        
        public void heapEl() {
        	for(Object e: this.heap) {
        		System.out.println(e);
        	}
        }
        
    public void clear() {
    	for (int i = 0; i < this.heap.length; i++) {
			this.heap[i] = null;
		}
    }
	
	static class StringAscending implements Comparator<Object> {
        public int compare( Object a, Object b ) {
        			return ((String) a).compareTo((String) b);
        }
    }

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
