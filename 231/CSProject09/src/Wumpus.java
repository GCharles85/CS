import java.awt.Color;
import java.awt.Graphics;


public class Wumpus extends Agent {
	private Vertex home;
	private boolean alive; 
	
	public Wumpus(int x, int y) {
		super(x, y);
		this.home = new Vertex(x,y);
		this.alive = true;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Vertex getVertex() {
		return this.home;
	}
	
	public boolean getAlive() {
		return this.alive;
	}
	
	public void draw(Graphics g, int scale) {
		if(!this.home.getVisible()) {
			return;
		}
		this.home.draw(g, scale);
		int border = 2;
		int xpos = this.home.getX()*scale + (scale - 20*border)/2;
        int ypos = this.home.getY()*scale + (scale - 20*border)/2;
        if(this.alive == true) {
            g.setColor(Color.black); 
            return;
        }
        g.setColor(Color.red);
        g.drawOval(xpos, ypos, scale - 20*border, scale - 20*border);
        
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
