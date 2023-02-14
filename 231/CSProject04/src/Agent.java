/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics;

public class Agent {
    protected double x = 0;
	protected double y = 0;
	
	public Agent(double x0, double y0) {
		this.x = x0;
		this.y = y0;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX( double newX ) {
		this.x = newX;
	} 
	
	public void setY( double newY ) {
		this.y = newY;
	} 
	
	public String toString() {
		String x = Double.toString(this.x);
		String y = Double.toString(this.y);
		String coor = "(" + x + ", "+ y + ")";
		return coor;
	}
  
	public void updateState( Landscape scape ) {;}
	public void draw(Graphics g) {;} 
	
	public static void main(String[] args) {
		Agent kill = new Agent(3.4,7.8);
       System.out.println(kill.toString());
	}

}