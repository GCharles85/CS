/* Guyriano Charles
 * 2/26/19
 * CS231
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;

public class Board {
	private Cell[][] play; //The 2D array representing the board
	public static int Size = 9;
	
	
	public Board() {
		this.play = new Cell[Board.Size][Board.Size]; //Sets up the board and populates it
		for(int i = 0; i < Board.Size; i++) {
			for(int j = 0; j < Board.Size; j++) {
			  this.play[i][j] = new Cell(i,j,0); 
		}
	}
   }  
	
	public String toString() { //Prints out all values on the board
	 for(int i = 0; i < Board.Size; i++) {
			for(int j = 0; j < Board.Size; j = j+3) {
				for(int k = 0+j; k < (Board.Size/3)+j; k++) {
				  System.out.print(this.play[i][k].getValue());
				  if(k == (((Board.Size/3)+j)-1)) //Spacing each 3 characters
					System.out.print(" ");
			}
		   } 
			if( i == 2 || i == 5 || i == 8) { //Double carriage returns if these rows were just printed to help make 3x3 blocks
				System.out.print("\n");
			    System.out.print("\n");
			}else {
				System.out.print("\n");}
		}
	 return "";
 }
		

	public int getCols() {
		return Board.Size;
	}
	
	public int getRows() {
		return Board.Size;
	}
	
	public Cell get(int r, int c) {
		return this.play[r][c];
	}
	
	public boolean isLocked(int r, int c) {
		return this.play[r][c].isLocked();
	}
	
	public int value(int r, int c) {
		return this.play[r][c].getValue();
	}
	
	public void set(int r, int c, int value) {
		this.play[r][c].setValue(value);
	}
	
	public void set(int r, int c, int value, boolean locked) {
		this.play[r][c].setValue(value);
		this.play[r][c].setLocked(locked);
	}
	 public boolean read(String filename) {
		    try {
		      FileReader loc = new FileReader(filename);
		      BufferedReader fil = new BufferedReader(loc);
		      int row = 0;
		      String line = fil.readLine();
		      while(line != null) {
		    	  String[] prn = line.split("[ ]+");
		    	  for(int i = 0; i < prn.length; i++) {
		    	   this.play[row][i] = new Cell(row,i,Integer.parseInt(prn[i])); //Cells are set with characters from prn
		      }
		       if(row != 3 || row != 10) { 
		    	   System.out.println(line); 
		       }
			    System.out.println(prn.length);
		    	line = fil.readLine();
		    	row++;
		    	
		    	if(row == 3 || row == 6) { //These rows have not characters to set the board's cells and are skipped
		    	 line =  fil.readLine(); 
		    	}
		    }
		      fil.close();
	    	  return true;
		    }
		    catch(FileNotFoundException ex) {
		      System.out.println("Board.read():: unable to open file " + filename );
		    }
		    catch(IOException ex) {
		      System.out.println("Board.read():: error reading file " + filename);
		    }

		    return false;
		  }
	 
	 public boolean validValue(int row, int col, int value) {
		 int place = 0;
		 int checks3 = 0;
		 ArrayList<Integer> tempA = new ArrayList<Integer>();
		 
		 if((value >= 0 && value <= 9) && (row >= 0 && row <= 8) && (col >= 0 && col <= 8)) {
		    ArrayList<Integer> check1 = new ArrayList<Integer>(); //Used to check the column of the value at that location
		    ArrayList<Integer> check2 = new ArrayList<Integer>(); //Used to check the row at that location
			
			for(int i = 0; i < Board.Size; i++) {
				check1.add(play[i][col].getValue());
				check2.add(play[row][i].getValue());
			}
			check1.remove(row); //The value itself is removed before the check
			check2.remove(col);
			for(int t:check1)
				if(t == value) { return false; } //If the value is found again in its row or column, return false
			for(int q:check2)
				if(q == value) { return false; }
		 }else {
			 return false;
		 }
		 
		 double cases = 10*(row/8.0);
		 if(cases <= 2.500) { checks3 = 10;}
		 if(cases >= 3.750 && cases <= 6.250) { checks3 = 2; }
		 if(cases >= 7.500 && cases <= 10.000) { checks3 = 3; }
		 
		 if(col >= 0 && col < 3) { //These statements are used assigned a number to place corresponding 
			 switch(checks3) { //to the value's 3x3 square
			 case 1: place = 1;
			 case 2: place = 4;
			 case 3: place = 7;
			 }
		 }else if(col >= 3 && col < 6) {
			 switch(checks3) {
			 case 1: place = 2;
			 case 2: place = 5;
			 case 3: place = 8;
			 }
		 }else if(col >= 6 && col < 8) {
			 switch(checks3) {
			 case 1: place = 3;
			 case 2: place = 6;
			 case 3: place = 9;
			 }
		 }
		
		 switch(place) { //Each case returns an array of the numbers in its 3x3 square, case 1 returns the numbers in the first 3x3 square
		 case 1:
			 for(int c = 0; c < 3; c++) {
				 for(int d = 0; d < 3; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 2:
			 for(int c = 0; c < 3; c++) {
				 for(int d = 3; d < 6; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 3:
			 for(int c = 0; c < 3; c++) {
				 for(int d = 6; d < 9; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 4:
			 for(int c = 3; c < 6; c++) {
				 for(int d = 0; d < 3; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 5:
			 
			 for(int c = 3; c < 6; c++) {
				 for(int d = 3; d < 6; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 6:
			 for(int c = 3; c < 6; c++) {
				 for(int d = 6; d < 9; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 7:
			 for(int c = 6; c < 9; c++) {
				 for(int d = 0; d < 3; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 8:
			 for(int c = 6; c < 9; c++) {
				 for(int d = 3; d < 6; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
			 
		 case 9:
			 for(int c = 6; c < 9; c++) {
				 for(int d = 6; d < 9; d++) {
					 if(c != row || d != col) { tempA.add(play[c][d].getValue()); };
				 }
			 }
			 break;
		 }
		 
		 for(int r:tempA) //If the value is in the square, return false
			if(r == value) { return false; }
	
		 return true;
	 }
	 
	 public boolean validSolution() {
		 int count = 0;;
		 for(int i = 0; i < Board.Size; i++) {
			 for(int e = 0; e < Board.Size; e++) {
				 if(play[i][e].getValue()==0 || validValue(i,e,play[i][e].getValue())!=true) {
					 count++;
			}
          }
	   }
		 if(count > 0) { return false; }
         return true;
	 }
	 
	 public void draw(Graphics g, int scale) {
		 int y = 20;
		 char[] drawAY = new char[9];
		 for (int i = 0; i < Board.Size; i++) {
			for (int j = 0; j < Board.Size; j++) {
				
				 drawAY[j] = (char)('0' + this.play[i][j].getValue()); 
				  
				  if(j == 8) {
					  this.play[i][j].draw(g, 20 , y, 30, drawAY); 
				      y = y + 20;
				  }
		     }
		}
 }
	 
	public static void main(String[] args) {
		Board board = new Board();
	    board.read(args[0]);
	    board.toString();
		System.out.print(board.validValue(1,1,4));
		System.out.print(board.validValue(1,8,2));
		System.out.print(board.validValue(8,5,4));
	}

}
