import java.awt.Color;
import java.awt.Graphics;

/*Guyriano Charles
 * 4/30/19
 * CS231
 */

public class Vertex extends Agent implements Comparable<Vertex> {
	private Vertex[] edges;
	enum Direction {north, south, east, west};
	private int cost;
	private boolean marked;
	private boolean visible;
	
	public Vertex (int x, int y) {
		super(x, y);
		this.visible = false;
		this.cost = 0;
		this.marked = false;
		this.edges = new Vertex[4];
	}
	
	public static Direction opposite(Direction d) {
		if(d == Direction.north) {
			return Direction.south;
		}
		if(d == Direction.south) {
			return Direction.north;
		}
		if(d == Direction.east) {
			return Direction.west;
		}
		
		return Direction.east;
	}
	
	public void connect (Vertex other, Direction dir) {
		this.edges[dir.ordinal()] = null;
		this.edges[dir.ordinal()] = other;
	}
	
	public Vertex getNeighbor(Direction dir) {
		if(this.edges[dir.ordinal()] != null){
		return this.edges[dir.ordinal()];
		}
		return null;
	}
	
	public Vertex[] getNeighbors() {
		return this.edges;
	}
	
	public boolean getMarked() {
		return this.marked;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void setMarked(boolean mark) {
		this.marked = mark;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return this.visible;
	}
	
	public String toString() {
		String s = " ";
		for(Vertex e: this.edges) {
			if(e  == null) {
				s = s + "null";
				continue;
			}
			s = s + e.getCost() + " " + e.getMarked() + ", ";
		}
		return s;
	}
	
	public void draw(Graphics g, int scale) {
		if(!this.getVisible()) {
			return;
		}
        int xpos = this.getX()*scale;
        int ypos = this.getY()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        
        // draw rectangle for the walls of the room
        if (this.cost <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black); 
        if  (this.edges[Direction.north.ordinal()] != null)
        	g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.edges[Direction.south.ordinal()] != null)
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
                       eighth, eighth + sixteenth);
        if (this.edges[Direction.west.ordinal()] != null)
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.edges[Direction.east.ordinal()] != null)
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                       eighth + sixteenth, eighth);
	}
	
	public int compareTo(Vertex ver) {
		if(ver.getCost() > this.getCost()) {
			return 1;
		}else if(ver.getCost() < this.getCost()) {
			return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Vertex vtx = new Vertex(5, 0);
		Vertex vtx1 = new Vertex(10, 0);
		Vertex vtx2 = new Vertex(5, 0);
		Vertex vtx3 = new Vertex(10, 0);
		vtx.connect(vtx1, Direction.north);
		vtx.connect(vtx2, Direction.east);
		vtx.connect(vtx3, Direction.south);
		System.out.println(Vertex.opposite(Direction.north));
		System.out.println(Vertex.opposite(Direction.west));
		System.out.println(vtx.toString());

	}

	

}

