/* Guyriano Charles
 * 2/26/19
 * CS231
 */

public class Cell {
	 private int row;
	 private int col;
	 private int value;
	 private boolean Locked;
	 
	 public Cell() {
		 this.row = 0;
		 this.col = 0;
		 this.value = 0;
		 this.Locked = false;
	 }
	 
	 public Cell(int row, int col, int value) {
		 this.Locked = false;
		 this.row = row;
		 this.col = col;
		 this.value =  value;
	 }
	 
	 public Cell(int row, int col, int value, boolean locked) {
		 this.Locked = locked;
		 this.row =  row;
		 this.col = col;
		 this.value =  value;
	 }
	 
	 public int getRow() {
		 return this.row;
	 }
	 
	 public int getCol() {
		 return this.col;
	 }
	 
	 public int getValue() {
		 return this.value;
	 }
	 
	 public void setValue(int newval) {
		 this.value =  newval;
	 }
	 
	 public boolean isLocked() {
		 return this.Locked;
	 }
	 
	 public void setLocked(boolean lock) {
		 this.Locked = lock;
	 }
	 
	 public Cell clone() {
		 Cell newClone = new Cell(this.row,this.col, this.value, this.Locked);
	     return newClone;
	 }
	 
	 public String toString() {
		 String check = ("Cell's row: " + Integer.toString(this.row) + " " + "Cell's column: " + Integer.toString(this.col) + " " + "Cell's value: " + Integer.toString(this.value) + " " +  "Cell's locked value: " + this.Locked);
		 return check;
	 }
	  
	public static void main(String[] args) {
		Cell c1 = new Cell();        // default constructor
		Cell c2 = new Cell(1, 5, 7); // basic constructor
		Cell c3 = new Cell(2, 6, 8, true); // full constructor

		System.out.println( "c1 locked: " + c1.isLocked() );
		System.out.println( "c2 locked: " + c2.isLocked() );
		System.out.println( "c3 locked: " + c3.isLocked() );

		c1.setLocked(true);
		System.out.println( "c1 should be locked: " + c1.isLocked() );

		c3.setLocked(false);
		System.out.println( "c3 should not be locked: " + c3.isLocked() );
		
		System.out.println( "c2 value should be 7: " + c2.getValue() );
		System.out.println( "c3 value should be 8: " + c3.getValue() );

		c1.setValue( 5 );
		System.out.println( "c1 value should be 5: " + c1.getValue() );

		System.out.println( "c1 row, col (0, 0): " + c1.getRow() + ", " + c1.getCol() );
		System.out.println( "c2 row, col (1, 5): " + c2.getRow() + ", " + c2.getCol() );
		System.out.println( "c3 row, col (2, 6): " + c3.getRow() + ", " + c3.getCol() );	

		Cell c4 = c3.clone();
		System.out.println( "c3: " + c3 );
		System.out.println( "c4: " + c4 );
		c4.setValue( 3 );
		c4.setLocked( true );
		System.out.println( "c3: " + c3 );
		System.out.println( "c4: " + c4 );
		

	}

}
