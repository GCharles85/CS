/* Guyriano Charles
 * CS 231
 * CS Project 02
 */


import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {
    private boolean state;
    
	public Cell() {
		state = false;
	}
	public Cell( boolean alive) {
		if(alive == true) 
		    state = true;
		else 
			state = false;
	}
	public boolean getAlive() {
		return state;
	}
	public void reset() {
		state =  false;
	}
	public void setAlive(boolean alive) {
		if(alive == true)
		 state = true;
		else 
		 state = false;
	}
	public String toString() {
		if(this.state ==  true)
			return "1";
		else
			return "0";
	}
	
	public void draw( Graphics g, int x, int y, int scale) {
		if(getAlive() == true) 
		   g.drawOval(x, y, 1*scale, 1*scale);
		else 
		   g.drawRect(x, y, 1*scale, 1*scale);
		
	}				
				
	public void updateState(ArrayList<Cell> neighbors){
		int live = 0;
		for(int i = 0; i < neighbors.size(); i++ ) {
			if(neighbors.get(i).getAlive() == true)
				live++;
		}
		
		switch(live) {
		  
	     case 3:
		    if(getAlive() == false) {
		    	setAlive(true);
		    }
		    break;
		    
		   default: 
			   reset();
			  
		}
	}
}
