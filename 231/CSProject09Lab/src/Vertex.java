/*Guyriano Charles
 * 4/30/19
 * CS231
 */

public class Vertex implements Comparable<Vertex> {
	private Vertex[] edges;
	enum Direction {north, south, east, west};
	private int cost;
	private boolean marked;
	
	public Vertex (int cost) {
		this.cost = cost;
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
	
	public int compareTo(Vertex ver) {
		if(ver.getCost() > this.getCost()) {
			return 1;
		}else if(ver.getCost() < this.getCost()) {
			return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Vertex vtx = new Vertex(5);
		Vertex vtx1 = new Vertex(10);
		Vertex vtx2 = new Vertex(5);
		Vertex vtx3 = new Vertex(10);
		vtx.connect(vtx1, Direction.north);
		vtx.connect(vtx2, Direction.east);
		vtx.connect(vtx3, Direction.south);
		System.out.println(Vertex.opposite(Direction.north));
		System.out.println(Vertex.opposite(Direction.west));
		System.out.println(vtx.toString());

	}

	

}
