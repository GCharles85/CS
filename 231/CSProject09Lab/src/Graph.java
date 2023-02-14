import java.util.ArrayList;
import java.util.PriorityQueue;



/*Guyriano Charles
 * 4/30/19
 * CS231
 */

public class Graph {
	private ArrayList<Vertex> vert;
	
	public Graph() {
		this.vert = new ArrayList<Vertex>();
	}
	
	public ArrayList<Vertex> getVert (){
		return this.vert;
	}
	
	public int vertexCount() {
		return this.vert.size();
	}
	
	public void addEdge(Vertex v1, Vertex.Direction dir, Vertex v2) {
	 if(!this.vert.contains(v1)) {
		this.vert.add(v1);
	 }
	 if(!this.vert.contains(v2)) {
		this.vert.add(v2);
	 }
		v2.connect(v1, dir);
		v1.connect(v2, Vertex.opposite(dir));
	}
	
	public void shortestPath(Vertex v0) {
		for(Vertex e: this.vert) {
			e.setMarked(false);
		}
		PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>(); 
		v0.setCost(0);
		heap.add(v0);
		while(heap.size() != 0) {
			Vertex v = heap.remove();
		    v.setMarked(true);
		    for(Vertex w: v.getNeighbors()) {
		    	if(w == null) {
		    	  continue;
		    	}
		    	if(w.getMarked() != true && (v.getCost() + 1) < w.getCost()) {
		    		w.setCost(v.getCost() + 1);
		    		heap.remove(w);
		    		heap.add(w);
		    	}
		    }
		}
		
	}

	public static void main(String[] args) {
		Graph web = new Graph();
		web.addEdge(new Vertex(3), Vertex.Direction.north, new Vertex(2));
		web.addEdge(new Vertex(1), Vertex.Direction.south, new Vertex(5));
		web.addEdge(new Vertex(2), Vertex.Direction.east, new Vertex(6));
		web.shortestPath(web.vert.get(0));

	}

}
