import java.awt.Color;
import java.awt.Graphics;




public class Hunter extends Agent {
	private Vertex local;
	
	public Hunter(int x, int y) {
		super(x, y);
		this.local = new Vertex(x,y);
		this.local.setVisible(true);
	}
	
	public Vertex getVertex() {
		return this.local;
	}
	
	public void setVertex(Vertex newV) {
     if(newV == null) {return;}
	 newV.setVisible(true);
	 this.local = newV;
	}
	
	public void draw(Graphics g, int scale) {
		this.local.draw(g, scale);
		int border = 2;
		int xpos = this.local.getX()*scale + (scale - 2*border)/2;
        int ypos = this.local.getY()*scale + (scale - 2*border)/2;
        g.setColor(Color.blue);
        g.drawRect(xpos, ypos, scale - border*30 , scale - border*30);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
