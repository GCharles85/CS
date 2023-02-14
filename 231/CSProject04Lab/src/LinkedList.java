/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class LinkedList<T> implements Iterable<T> {
	Node head;
	int size;
	
	public LinkedList() {
		this.size = 0;
		this.head = null;
	}
	
	public void clear() {
		this.size = 0;
		this.head = null;
	}
	
	public int size() {
		return this.size;
	}
	
	public void addFirst(T item) {
		Node n = new Node(item);
		n.setNext(this.head);
		this.head = n;
		this.size++;
	}
	
	public void addLast(T item) {
		Node n = new Node(item);
		Node r = this.head;

		if(this.size > 0) {
		for(int i = 0; i < this.size-1; i++) {
			 r = r.getNext();
		 }
		r.setNext(n);
		}else {
		 addFirst(item);
		 return;
		}
		this.size++;
	}
	
	public void add(int index, T item) {
		Node e = this.head;
		Node n = new Node(item);
		Node r = this.head;
		if(index < 0) {
			System.out.println("Index is too small");
			return;
		}if(this.size == 0) {
			addFirst(item);
			return;
		}else if(index == 0) {
			addFirst(item);
			return;
		}else if(index > this.size) {
			System.out.println("Index is too large");
			return;
		}else if(index == this.size) {
			addLast(item);
			return;
		}
		for(int i = 0; i < index; i++) {
			r = r.getNext();
		}
		n.setNext(r);
	
		for(int j = 0; j < index-1; j++) {
			e = e.getNext();
		}
		e.setNext(n);
		this.size++;
  }
	
    public T remove(int index) {
    	Node r = this.head;
    	Node c = this.head;
    	Node f = this.head;
    	if(index  >= this.size || index < 0){ 
    		System.out.println("Index not valid, returning head object");
    		return r.getThing();
    	}else if(index == 0) {
    		T cse = this.head.getThing();
    		this.head = r.getNext();
    		size--;
    		return cse;
    	}
    	
    	for(int i = 0; i < index + 1 ; i++) {
    		r = r.getNext();
    	}
    	
    	for(int i = 0; i < index - 1; i++) {
    		c = c.getNext();
    	}
    	f = c.getNext();
    	c.setNext(r);
    	size--;
    	return f.getThing();
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
    
    public ArrayList<T> toShuffledList() {
    	ArrayList<T> list = new ArrayList<T>();
    	Node r = this.head;
    	for(int i = 0; i < this.size; i++) {
    		list.add(r.getThing());
    		r = r.getNext();
    	}
    	
        if (this.head == null) { 
         System.out.println("List is empty");
         return list; 
         }
        if (this.head.getNext() == null) {
            System.out.println("Size = 1");
            return list;
        }
        
        T thing = list.get(0);
        list.remove(0);
        list.add(size-1, thing);
        return list;
    }
	
	// Return a new LLIterator pointing to the head of the list
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
			nextN = head;
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
}
