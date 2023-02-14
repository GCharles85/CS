/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

public class SocialAgent extends Agent {
	protected boolean moved;
	
	public SocialAgent(double x0, double y0) {
	    super( x0, y0 );
	}
	
	public void draw(Graphics g ) {
		g.drawOval((int)this.x, (int)this.y, 20, 20);
		if(this.moved == true) {
		 g.setColor(Color.blue);
		}else if(this.moved == false) {
	     g.setColor(Color.cyan);
		}
	}
	
	public void updateState( Landscape scape ) {
	 Random gen =  new Random();
	 double ran = (-(gen.nextDouble())*10) + gen.nextDouble()*10;
		
	 ArrayList<Agent> check = scape.blist.toArrayList();
	 for(int i = 0; i < scape.blist.size(); i++) {
		if(scape.getNeighbors(check.get(i).getX(), check.get(i).getY(), 15.0).size() > 3) {
		  if(gen.nextInt(10) == 5) {
		    check.get(i).setX(check.get(i).getX() + ran);
			check.get(i).setY(check.get(i).getY() + ran);
			this.moved = true;
		  }else {this.moved = false;}
		}else {
		 check.get(i).setX(check.get(i).getX() + ran);
		 check.get(i).setY(check.get(i).getY() + ran);
		 this.moved = true;
		}
	 }
	}
	
	public static void main(String[] args) {
	     
	}
	 
}

