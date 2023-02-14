/* Guyriano Charles
 * 2/26/19
 * CS231
 */

import java.io.FileReader;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board {
	Random num = new Random();
	public Board() {
		Cell[][] play = new Cell[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
			  play[i][j] = new Cell(i,j, num.nextInt(10));                     
		}
		
	}
}
	
	 public boolean read(String filename) {
		    try {
		      FileReader loc = new FileReader(filename);
		      BufferedReader fil = new BufferedReader(loc);
		      
		      String line = fil.readLine();
		      while(line != null) {
		    	  String[] prn = line.split("[ ]+");
		    	  System.out.println(line);
		    	  System.out.println(prn.length);
		    	  line = fil.readLine();
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
	public static void main(String[] args) {
		Board board = new Board();
		String filename = args[0];
		board.read(filename);
		
	}

}
