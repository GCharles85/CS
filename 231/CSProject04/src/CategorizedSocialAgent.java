/*Guyriano Charles
 * 3/5/19
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class CategorizedSocialAgent extends SocialAgent {
	int cat;
	public CategorizedSocialAgent(double x0, double y0, int cat) {
		super(x0, y0);
		this.cat = cat;
	}
	
	public int getCategory() {
		return this.cat;
	}
	
	public String toString() {
		return Integer.toString(this.cat);
	}
	
	public void draw(Graphics g) {
		g.drawOval((int)this.getX(), (int)this.getY(), 20, 20);
		if(this.cat < 3 && this.moved == true) {
			g.setColor(Color.yellow);
		}else {
			g.setColor(Color.green);
		}
	} 
	
	public void updateState(Landscape scape) {
		Random gen = new Random();
		double ran = (-(gen.nextDouble())*10) + gen.nextDouble()*10;
		ArrayList<Agent> catA = scape.blist.toArrayList();
		ArrayList<Agent> catC = new ArrayList<Agent>();
		
		for(int i = 0; i < scape.blist.size(); i++) {
			ArrayList<Agent> catB = scape.getNeighbors(catA.get(i).getX(),catA.get(i).getX(),20.0);
			for(Agent e: catB) {
				if(scape.getNeighbors(e.getX(),e.getX(),20.0).size()==this.getCategory()) {
					catC.add(e);
				}
			}
			
		 if(catB.size() >= 2 && catC.size() > 3) {
		    if(gen.nextInt(10) == 5) {
			  catA.get(i).setX(catA.get(i).getX() + ran);
			  catA.get(i).setY(catA.get(i).getY() + ran);
			  this.moved = true;
		    }else {this.moved = false;}
		 }else {
		  catA.get(i).setX(catA.get(i).getX() + ran);
	      catA.get(i).setY(catA.get(i).getY() + ran);
		  this.moved = true;
		 }
	  }
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
