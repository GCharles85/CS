/*Guyriano Charles
 * 3/12/19
 * CS231
 */

import java.util.ArrayList;
import java.util.Iterator;

public class MyQueue<T> implements Iterable<T> {
	private Node head;
	private Node tail;
	private int size; 
	
	public MyQueue () {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	public void offer(T item) {
			Node n = new Node(item);
			//Node r = this.tail;

			if(this.size > 0) {
			 this.tail.setNext(n);
			 this.tail = n;
			}else {
			 this.head = n;
			 this.tail = n;
			}
			this.size++;
   }
   
   public void poll() {
	   if(this.size == 0) {
			return;
		}else {
			Poll();
		}
   }
   
	public T Poll() {
		T temp= this.head.getThing();
    	this.head = this.head.getNext();
    	size--;
    	return temp;
   } 
	
   public Node peek() {
	   return this.head;
   }

   public void clear() {
	   this.head = null;
	   this.tail = null;
	   this.size = 0;
   }
   
   public Node tail() {
	   return this.tail;
   }
   
   public ArrayList<T> toArrayList(){
   	ArrayList<T> list = new ArrayList<T>();
   	Node r = this.head;
   	for(int i = 0; i < this.size; i++) {
   		list.add(r.getThing());
   		r = r.getNext();
   	}
   	return list;
   }
   
   public Iterator<T> iterator() {
	   return new LLIterator( this.head );
	}

   private class Node{
	    Node next;
	    T obj;
		
		public Node(T item) {
			this.next =  null;
			this.obj = item;
		}
		
		public T getThing() {
			return this.obj;
		}
		
		public void setNext(Node n) {
			this.next = n;
		}
		
		public Node getNext() {
			return this.next;
		}
	}
   
   private class LLIterator implements Iterator<T>{
		Node nextN;
		
		public LLIterator(Node head) {
			this.nextN = head;
		}
		
		public boolean hasNext() {
			if( this.nextN != null) { return true; }
			return false;
		}
		
		public T next() {
		T c = this.nextN.getThing();
		this.nextN = this.nextN.getNext();
		return c;
		}
		
		public void remove() {;}	
		
		}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}










