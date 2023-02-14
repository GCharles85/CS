/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.awt.Graphics;

public class Landscape {
    int width;
    int height;
    LinkedList<Agent> flist;
    LinkedList<Agent> blist;
    
   
    public Landscape(int w, int h) {
    	this.width = w;
    	this.height = h;
    	blist = new LinkedList<Agent>();
    	flist = new LinkedList<Agent>();

    } 
    
    public int getHeight() {
    	return this.height;
    } 
    
    public int getWidth() {
    	return this.width;
    }
    
    public void addBackgroundAgent( Agent a ) {
    	this.blist.addFirst(a);
    }
    
    public String toString() {
    	String def = "Number of agents" + blist.size();
    	return def;
    }
    
    
    public void draw(Graphics g, int scale) {
    	for(int i = 0; i < blist.size(); i++) {
    		this.blist.toArrayList().get(i).draw(g, scale);
    	}
    	for(int i = 0; i < flist.size(); i++) {
    		this.flist.toArrayList().get(i).draw(g, scale);
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
	}

}

