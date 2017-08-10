package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST() { 
	  size=0; 
	  root=null; 
  }
  
  public boolean insert(String s) {
	  if (size == 0) {
		  root = new BST_Node(s);
		  size ++;
		  return true;
	  }
	  if (root.containsNode(s)) {
		  return false;
	  }
	  root.insertNode(s);
	  size ++;
	  return true; 
  }
  public boolean remove(String s) {
	  if (size == 0) {
		  return false;
	  }
	  if (!root.containsNode(s)) {
		  return false;
	  }
	  //if delete root
	  if (root.data.compareTo(s) == 0) {
		  if (root.left == null && root.right == null) {
			  root = null;		// root = null?
		  } else if (root.left == null || root.right == null) {		   
			  root = (root.left != null)? root.left:root.right;
			  root.parent = null;
		  } else {
			  root.removeNode(s);
		  }
		  size --;
		  return true;
	  } else { //if not delete root
		  root.removeNode(s);
		  size --;
		  return true;
	  }	  
  }
  public String findMin() {
	  return (size == 0)? null:root.findMin().data;
  }
  public String findMax() {
	  return (size == 0)? null:root.findMax().data;
  }
  public boolean empty() {
	  return (size == 0)? true:false;
  }
  public boolean contains(String s) {
	  return (size == 0)? false:root.containsNode(s);
  }
  public int size() {
	  return size;
  }
  public int height(){
	  return (size == 0)? -1:root.getHeight();
  }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

}
