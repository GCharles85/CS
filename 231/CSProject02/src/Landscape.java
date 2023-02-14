/* Guyriano Charles
 * CS 231
 * CS Project 02
 */

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

public class Landscape {
	int rows;
	int cols;
    Cell[][] scape; 
    
    public Landscape(int rows, int cols) {
    	this.rows = rows;
    	this.cols = cols;
    	this.scape = new Cell[rows][cols];
    	boolean status = false;
    	Random num = new Random();
    	
    	for(int i = 0; i < this.rows; i++) {
    		for(int e = 0; e < this.cols; e++) {
    			int status2 = num.nextInt(2);
    			if(status2 == 0)
    			   status = false;
    			else if(status2 == 1)
    			   status = true;
    			scape[i][e] = new Cell(status);
    		}
    	}
    	
    }
    
    public void reset() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int e = 0; e < this.cols; e++) {
    			scape[i][e].reset();
    	    }
        }
    }
    
    public int getRows() {
    	return this.rows;
    }
    
    public int getCols() {
    	return this.cols;
    }
    
    public Cell getCell(int row, int col) {
    	return scape[row][col];
    }
    
    public String toString() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int e = 0; e < this.cols; e++) {
    			if(scape[i][e].getAlive() == true)
    				System.out.print("1");
    			else
    			    System.out.print("0");
    	    }
    		System.out.print("\n");
    	}
    	return " ";
    }
    
    public ArrayList<Cell> getNeighbors(int row, int col){
    
    	ArrayList<Cell> neighbors = new ArrayList<Cell>();
    	if(row > 0) {
    	neighbors.add(scape[row-1][col]);}
    	if(row < this.rows - 1) {
    	neighbors.add(scape[row+1][col]);}
    	if(col > 0) {
    	neighbors.add(scape[row][col-1]);}
    	if(col < this.cols - 1) {
    	neighbors.add(scape[row][col+1]);}
    	return neighbors;
    }
    
    public void advance() {
    	Landscape temp = new Landscape(this.rows, this.cols);
    	for(int i = 0; i < this.rows; i++) {
    		for(int e = 0; e < this.cols; e++) {
    			temp.scape[i][e] = new Cell();
    			temp.scape[i][e].setAlive(this.scape[i][e].getAlive());
    			temp.scape[i][e].updateState(this.getNeighbors(i,e));
    		}
    		this.scape = temp.scape;
    	}
    	
    }
    
	
	public void draw(Graphics g, int gridScale) {
		// draw all the cells
		        Landscape test = new Landscape(10,10);
				for (int i = 0; i < test.getRows(); i++) {
					for (int j = 0; j < test.getCols(); j++) {
						test.scape[i][j].draw(g, i*gridScale, j*gridScale, gridScale);  
    
     }
	}
   }
}