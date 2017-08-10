package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 

  public void insert(String s) {
	  if (size == 0) {
		  root = new BST_Node(s);
		  size ++;
		  return;
	  }	  
	  root = root.insertNode(s);
	  if (root.justMade) {
		  size ++;
	  }	  
  }
  public void remove(String s) {
	  if (size == 0) {
		  return;
	  }  
	  if (!contains(s)) {
		  return;
	  }
	  if (size == 1) {
			 root = null;
			 size --;
			 return;
	  }
	  BST_Node rChild = root.right;
	  if (root.left != null && rChild != null) {
		  root = root.left.findMax();
		  root.right = rChild;
		  rChild.par = root; 
	  } else if (root.left == null && rChild != null) {
		  root = root.right;
		  root.par = null;
	  } else if (root.left != null && rChild == null) {
		  root = root.left;
		  root.par = null;
	  }
	  size --;	   
  }
  public String findMin() {
	  if (size == 0) {
		  return null;
	  } else {
		  root = root.findMin();
		  return root.data;
	  }
  }
  public String findMax() {
	  if (size == 0) {
		  return null;
	  } else {
		  root = root.findMax();
		  return root.data;
	  }
  }
  public boolean empty() {
	  return (size == 0)? true:false;
  }
  public boolean contains(String s) {
	  if (size == 0) return false;
	  root = root.containsNode(s);
	  if (root.data.compareTo(s) == 0) {
		  return true;
	  } else return false;
  }
  public int size() {
	  return size;
  }
  public int height(){
	  return (size == 0)? -1:root.getHeight();
  }  
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }  

}
