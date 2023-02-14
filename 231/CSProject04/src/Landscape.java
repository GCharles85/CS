/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {
    int width;
    int height;
    LinkedList<Agent> blist;
   
    public Landscape(int w, int h) {
    	this.width = w;
    	this.height = h;
    	blist = new LinkedList<Agent>();
    } 
    
    public int getHeight() {
    	return this.height;
    } 
    
    public int getWidth() {
    	return this.width;
    }
    
    public void addAgent( Agent a ) {
    	this.blist.addFirst(a);
    }
    
    public String toString() {
    	String def = "Number of agents" + blist.size();
    	return def;
    }
    
    public ArrayList<Agent> getNeighbors(double x0, double y0, double radius) {
    	
    	ArrayList<Agent> nearB = blist.toArrayList();
    	for(int i = 0; i < this.blist.size(); i++) {
    		if(nearB.get(i).getX() < x0-radius && nearB.get(i).getX() > x0+radius && nearB.get(i).getY() < y0-radius && nearB.get(i).getY() > y0+radius) {
    			nearB.remove(i);
    		}
    	}
    	return nearB;
    }
    
    public void draw(Graphics g) {
    	for(int i = 0; i < blist.size(); i++) {
    		this.blist.toArrayList().get(i).draw(g);
    	}
    }
    
    public void updateAgents() {
    	ArrayList<Agent> upD = this.blist.toShuffledList();
    	for(Agent e: upD)
    		e.updateState(this);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
	}

}
