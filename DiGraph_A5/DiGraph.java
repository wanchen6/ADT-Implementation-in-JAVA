package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
  // and the topo sort operation
	HashSet<Long> nids;
	HashSet<Long> eids;
	HashMap<String, GNode> nodes;
	List<GNode> orderedNodes;
	long edgeNum;
	long nodeNum;
	
  public DiGraph () { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  nids = new HashSet<Long>();
	  eids = new HashSet<Long>();
	  nodes = new HashMap<String, GNode>();
	  orderedNodes = new LinkedList<GNode>();
	  edgeNum = 0;
	  nodeNum = 0;	  
  }

  // rest of your code to implement the various operations
  public boolean addNode(long idNum, String label) {
	  if (nodes.containsKey(label) || label == null) return false;
	  if (idNum < 0 || nids.contains(idNum)) return false; //idNum must be unique too!
	  GNode node = new GNode(idNum, label);
	  nodes.put(label, node);
	  orderedNodes.add(node); //input order maintained by add to the end of the list
	  nids.add(idNum);
	  nodeNum ++;
	  return true;
  }
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	  if (nodes.isEmpty()) return false;
	  if (idNum < 0 || eids.contains(idNum)) return false;
	  if (nodes.containsKey(sLabel) == false) return false;
	  if (nodes.containsKey(dLabel) == false) return false;  
	  
	  //returns false is there already is an edge between these 2 nodes
	  GNode sNode = nodes.get(sLabel);
	  GNode dNode = nodes.get(dLabel);
	  GEdge sEdge = sNode.getEdge();
	  GEdge pEdge = null;
	  while (sEdge != null) {
		  pEdge = sEdge;
		  if (sEdge.getdLabel().equals(dLabel)) return false;
		  sEdge = sEdge.getNext();
	  }
	  
	  //returns true if edge is successfully added
	  GEdge edge = new GEdge(idNum, sLabel, dLabel);
	  edge.weight = weight;
	  edge.eLabel = eLabel;
	  if (pEdge == null) {
		  sNode.adj = edge;
	  } else{
		  pEdge.next = edge;
	  }
	  eids.add(idNum);
	  edgeNum ++;
	  sNode.outdeg ++;
	  dNode.indeg ++;
	  return true;
  }
  public boolean delNode(String label) {
	  if (!nodes.containsKey(label)) return false;
	  
	//remove edges if any involves the node removed
	  Set<Entry<String, GNode>> set = nodes.entrySet();
	  Iterator<Entry<String, GNode>> iterator = set.iterator();
	  while (iterator.hasNext()) {
		  Map.Entry<String, GNode> nodePair = (Map.Entry<String, GNode>)iterator.next();
		  GNode curNode = nodePair.getValue();
		  GEdge pEdge = null;
		  GEdge delEdge = curNode.getEdge();
		  if (delEdge != null) {
			  int step = 0;
			  while (delEdge != null && !delEdge.dLabel.equals(label)) {
				  pEdge = delEdge;
				  delEdge = delEdge.getNext();
				  step ++;
			  }
			  if (delEdge == null) continue;
			  if (step == 0) curNode.adj = delEdge.getNext();
			  else pEdge.next = delEdge.getNext();
			  eids.remove(delEdge.id);
			  curNode.outdeg --;
			  edgeNum --;
		  }		  
	  }
	  
	  //remove the node form nodes, indeg decrease by 1
	  GNode node = nodes.get(label);
	  GEdge edge = node.getEdge();
	  int step = 0;
	  while (edge != null) {
		  step ++;
		  nodes.get(edge.dLabel).indeg --;
		  edge = edge.getNext();
	  }
	  nodes.remove(node.getName());
	  edgeNum = edgeNum - step;
	  nids.remove(node.id);	  	 	  
	  nodeNum --;
	  orderedNodes.remove(node);
	  return true;
  }
  public boolean delEdge(String sLabel, String dLabel) {
	  GNode snode = nodes.get(sLabel);
	  GNode dnode = nodes.get(dLabel);
	  if (snode == null || dnode == null) {
		  return false;
	  }
	  GEdge edge = snode.getEdge();
	  GEdge pEdge = null;
	  int step = 0;
	  while (edge != null) {
		  pEdge = edge;
		  if (edge.dLabel.equals(dLabel)) {
			  if (step == 0) {
				  snode.adj = edge.getNext();
			  } else {
				  pEdge.next = edge.getNext();
			  }
			  snode.outdeg --;
			  dnode.indeg --;
			  edgeNum --;
			  eids.remove(edge.id);
			  return true;
		  }
		  edge = edge.getNext();
		  step ++;
	  }
	  return false;
  }
  public long numNodes() {
	  return nodeNum;
  }
  public long numEdges() {
	  return edgeNum;	  
  }
  public String[] topoSort() {	  
	  Queue<GNode> nodesIndeg0 = new LinkedList<GNode>();
	  int size = (int)nodeNum;
	  int i = 0;
	  String[] result = new String[size];
	  
	  //initialize the queue, input order maintained
	  ListIterator<GNode> iterator = orderedNodes.listIterator();  
	  while (iterator.hasNext()) {
		  GNode node = iterator.next();
		  if (node.indeg == 0) {
			  nodesIndeg0.add(node);
		  }			  
	  }
	  
	  if (nodesIndeg0.isEmpty()) return null;	  
	  while (!nodesIndeg0.isEmpty()) {
		  GNode rmNode = nodesIndeg0.remove();
		  result[i] = rmNode.name;
		  GEdge rmEdge = rmNode.getEdge();
		  while (rmEdge != null) {
			  int dindeg = -- nodes.get(rmEdge.getdLabel()).indeg;
			  if (dindeg == 0) {
				  nodesIndeg0.add(nodes.get(rmEdge.getdLabel()));
			  }
			  rmEdge = rmEdge.getNext();
		  }
		  i ++;
	  }
	  
	  if (i == size) {
		  return result;
	  } else {
		  return null;
	  }  
  }
  
  //print graph
  public void printGraph() {
	  ListIterator<GNode> iterator = orderedNodes.listIterator(); 		  
	  while (iterator.hasNext()) {
		  GNode node = iterator.next();
		  GEdge edge = node.getEdge();
		  System.out.print("(" + node.id + ")" + " " + node.name);
		  System.out.println();		  
		  while (edge != null) {
			  System.out.print("\t(" + edge.id + ")" + "--" + edge.eLabel + ", " + edge.weight + " --> " + edge.dLabel + "\n");
			  edge = edge.getNext();
		  }
		  
	  }	  
	  
  }
}
