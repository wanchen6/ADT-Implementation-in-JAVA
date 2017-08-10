package Dijkstra_A6;

public class GNode {
	long id;
	String name;
	GEdge adj;
	int indeg;
	int outdeg;
	Boolean known;
	long dist;
	String path;
	
	public GNode(long id, String name) {
		this.id = id;
		this.name = name;
		indeg = 0;
		outdeg = 0;
		adj = null;
		known = false;
		dist = Long.MAX_VALUE;
		path = null;
	}
	public String getName() { return name; }
	public GEdge getEdge() { return adj; }
	public int getIndeg() { return indeg; }
	public int getOutdeg() { return outdeg; }
}
