/* Guyriano Charles
 * 2/26/19
 * CS231
 */

public class CellStack {
	private Cell[] cells;
	private int maxN;
	private int nextSpace;
	
	public CellStack() {
		this.maxN = 10;
		for(int i = 0; i < 10; i++) {
			cells[i] = new Cell();
		}
		this.nextSpace = 0;
	}
	
	public CellStack(int size) {
		this.maxN = size;
		for(int i = 0; i < size; i++) {
			this.cells[i] = new Cell();
		}
		this.nextSpace = size;
	}
	
	public void push(Cell c) {
		if(this.nextSpace < maxN) {
			this.cells[this.nextSpace] = c;
		}else {
			Cell[] newA = new Cell[this.maxN*2];
		    for(int i = 0; i < this.nextSpace; i++) {
		    	newA[i] = this.cells[i];
		    	this.cells = newA;
		    	this.cells[this.nextSpace] = c;
		    }
		}
	}
	
	public Cell pop() {
		if(this.nextSpace == 0) {
			nextSpace = 1;
	    }
			return this.cells[--this.nextSpace];
	}
	
	public int size() {
		return this.maxN;
	}
	
	public boolean empty() {
		if(this.nextSpace == 0) 
			return true;
		else
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("Testing default constructor");
		CellStack cs = new CellStack(); // test constructor w/no arguments

		System.out.println("Testing pre-size constructor");
		CellStack cs2 = new CellStack(50); // test constructor w/arguments

		System.out.println("Pushing lots of stuff on stack 1");
		for(int i=0;i<20;i++) {
		    cs.push(new Cell(0, 0, i));
		}

		System.out.println("Pushing more stuff on stack 2");
		for(int i=0;i<100;i++) {
		    cs2.push(new Cell(i, i, 1));
		}

		System.out.println("Popping 99 things off of stack 2");
		for(int i=0;i<99;i++) {
		    cs2.pop();
		}
		System.out.println("There should be 1 thing left on stack 2: " + cs2.size());
		System.out.println("stack 2 should not be empty: " + cs2.empty());
		
		System.out.println("Popping 11 things off stack 1");
		for(int i=0;i<11;i++) {
		    cs.pop();
		}

		System.out.println("There should be 9 things left on stack 1: " + cs.size());
		System.out.println("stack 1 should not be empty: " + cs.empty());

		System.out.println("Popping 9 things off stack 1");
		for(int i=0;i<9;i++) {
		    cs.pop();
		}
		System.out.println("stack 1 should be empty: " + cs.empty());

		System.out.println("Popping 2 things off stack 2");
		cs2.pop();
		cs2.pop();

		System.out.println("stack 2 should be empty: " + cs.empty());

		System.out.println("this has been your test function. Bye");

	}

}
