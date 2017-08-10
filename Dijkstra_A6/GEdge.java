package A6_Dijkstra;

public class GEdge {
	long id;
	String sLabel;  //the label of source node
	String dLabel;  //the label of destination node
	long weight;
	String eLabel;  //the label of the edge
	GEdge next;
	
	public GEdge(long idNum, String sLabel, String dLabel) {
		id = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		weight = 1;
		eLabel = null;
		next = null;
	}

	public String getsLabel() {return sLabel;}
	public String getdLabel() { return dLabel; }
	public long getWeight() { return weight; }
	public String geteLabel() { return eLabel; }
	public GEdge getNext() { return next; }
}
