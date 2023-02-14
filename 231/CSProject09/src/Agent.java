/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.awt.Graphics;

public class Agent {
    protected int x = 0;
	protected int y = 0;
	
	public Agent(int x0, int y0) {
		this.x = x0;
		this.y = y0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX( int newX ) {
		this.x = newX;
	} 
	
	public void setY( int newY ) {
		this.y = newY;
	} 
	
	public String toString() {
		String x = Double.toString(this.x);
		String y = Double.toString(this.y);
		String coor = "(" + x + ", "+ y + ")";
		return coor;
	}
  
	public void draw(Graphics g, int scale) {;} 
	
	public static void main(String[] args) {
		Agent kill = new Agent(3,7);
       System.out.println(kill.toString());
	}

}
