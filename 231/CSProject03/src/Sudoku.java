/* Guyriano Charles
 * 2/26/19
 * CS231
 */

import java.util.Random;
import java.util.ArrayList;

public class Sudoku {
	Board board;
	LandscapeDisplay1 scp;
	
	public Sudoku() {
	    board = new Board();
	    scp = new LandscapeDisplay1(board, 30); //Creates a new board and displays it
	}
	
	public Sudoku(int Start) {
		board = new Board();
		scp = new LandscapeDisplay1(board, 30); //Creates a new board and displays it
		Random num = new Random();
		int row = num.nextInt(9); //Random row, column, and value are chosen
		int col = num.nextInt(9);
		int value = num.nextInt(10)+1;
		
		for(int i = 0; i < Start; i++) { //Start is the number of cells that will be randomly given a value
			if(board.value(row,col) == 0) {
				if(board.validValue(row, col, value)==true) {
			      board.set(row, col,value, true);
		 }
	   }
	 }
  }
	
     public boolean solve(int delay) {
    	
    	        CellStack cels = new CellStack(81); //Equal to the number of spaces on the board
    	        int curRow = 0;
    	        int curCol = 0;
    	        int curValue = 1;
    	        int time = 0; //Number of steps taken to solve the board
    	        int ret = 0; //Later used to track index of newTemp
    	        ArrayList<Cell> newTemp = new ArrayList<Cell>(); //Holds objects popped from cels

    	        while(cels.next() < Board.Size*Board.Size) {//While all of the cells have not been run through
    	            time++; //Increment the number of steps

    	            if(board.isLocked(curRow, curCol)) { //If a cell is locked, it is pushed onto cels and skipped
    	                cels.push(board.get(curRow, curCol));
    	                
    	                if( curCol != 8) { 
    	                	curCol++; 
    	                }else { 
    	                	curRow++; 
    	                	curCol = 0;
    	                }
    	                
    	                continue;
    	            }			
    	            
    	            for(int i = curValue; i < 10; i++) { //If a valid value is found, the code immediately moves to set the cell at
    	             if(board.validValue(curRow, curCol, i)) { //curRow, curCol to that value
    	            	 curValue = i;
    	            	 break;
    	             }
    	           }
    	             
    	           if(board.validValue(curRow, curCol, curValue)) { //The valid value is put in and the index is incremented
    	                board.set(curRow, curCol, curValue);
    	                cels.push(board.get(curRow, curCol));
    	                if( curCol != 8) { 
    	                	curCol++; 
    	                }else { 
    	                	curRow++; 
    	                	curCol = 0;
    	                }
    	               curValue = 1;
    	            }else {
    	            	  if(cels.next() > 0) { //if there is no valid value, while there are still cells to go back to
    	            		  Cell tempB = cels.pop(); //the previous cell is popped
    	            		  newTemp.add(tempB);
    	            		  ret++;
    	            		  if(ret > 0) {
    	            		  curRow = newTemp.get(ret-1).getRow(); //The index is decremented as to attempt to reset the previous cell
    	            		  curCol = newTemp.get(ret-1).getRow();
    	            		  curValue = newTemp.get(ret-1).getValue() + 1;
    	            		  }
    	            		  continue;
    	            	  }else { //if there are no cells to go back to, the board cannot be solved
      	                    board.set(curRow, curCol, 0);
      	                    System.out.println(time);
  	            		    return false;
    	            	  }
    	            }
    	            	
    	        }
    	        System.out.println(time);
    	        return true;
    }
	
	public static void main(String[] args) {
		Sudoku game = new Sudoku(15);
		game.board.read(args[0]);
		game.solve(10);
		game.scp = new LandscapeDisplay1(game.board,30);
	}

}
