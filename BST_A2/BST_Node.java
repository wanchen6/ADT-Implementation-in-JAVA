package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node parent;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }
  public BST_Node getParent() { return parent; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s) {
	  BST_Node tree = this;
	  while (tree != null) {
		  if (tree.data.compareTo(s) > 0) {
			  tree = tree.left;
		  } else if (tree.data.compareTo(s) < 0) {
			  tree = tree.right;
		  } else
			  return true;
	  }
	  return false;
  }
  public void insertNode(String s) {
	  BST_Node tree = this;
	  BST_Node pTree = null;
	  while (tree != null) {
		  pTree = tree;
		  if (tree.data.compareTo(s) > 0) {			  
			  tree = tree.left;
		  } else if (tree.data.compareTo(s) < 0) {
			  tree = tree.right;
		  } else return;
	  }
	  tree = new BST_Node(s);
	  tree.parent = pTree;
	  if (pTree.data.compareTo(s) < 0) {
		  pTree.right = tree;
	  } else pTree.left = tree;
  }
  public void removeNode(String s) {
	  BST_Node tree = this;
	  BST_Node pTree = null;
	  while (tree.data.compareTo(s) != 0) {
		  pTree = tree;
		  if (tree.data.compareTo(s) > 0) {
			  tree = tree.left;
		  } else if (tree.data.compareTo(s) < 0) {
			  tree = tree.right;
		  } 
	  }
	  if (tree.left == null && tree.right == null) {	//no child
		  tree.parent = null;
		  if (pTree.data.compareTo(s) < 0) {
			  pTree.right = null;
		  } else {
			  pTree.left = null;
		  }
	  } else if (tree.left == null || tree.right == null) {// 1 child
		  if (pTree.data.compareTo(s) < 0) {				  
			  pTree.right = (tree.right == null)? tree.left:tree.right;		  
		  } else {				  
			  pTree.left = (tree.right == null)? tree.left:tree.right;	  
		  }			 
		  if (tree.right == null) {
			  tree.left.parent = pTree;
		  } else tree.right.parent = pTree;  
	  } else if (tree.left != null && tree.right != null) { // 2 child
		  BST_Node treeMin = tree.right.findMin();
		  tree.data = treeMin.data;
		  if (treeMin.left == null && treeMin.right == null) {
			  if (tree.right == treeMin) {
				  tree.right = null;
				  treeMin.parent = null;
			  } else {
				  treeMin.parent.left = null;
				  treeMin.parent = null;
			  }
		  } else if (treeMin.left == null && treeMin.right != null) {
			  if (tree.right == treeMin) {
				  tree.right = treeMin.right;
				  treeMin.right.parent = tree;
			  } else {
				  treeMin.parent.left = treeMin.right;
				  treeMin.right.parent = treeMin.parent;
			  }
		  }	  
	  }	  
  }
  public BST_Node findMin() {
	  BST_Node tree = this;
	  if (tree != null) {
		  while (tree.left != null)
			  tree = tree.left;
	  }
	  return tree;
  }
  public BST_Node findMax() {
	  BST_Node tree = this;
	  if (tree != null) {
		  while (tree.right != null)
			  tree = tree.right;
	  }
	  return tree;
  }
  public int getHeight() {
	  return 1 + Math.max((left != null)? left.getHeight():-1, (right != null)? right.getHeight():-1);
  }
  

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  public String see(){
	    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
	            +", Right: "+((this.right!=null)?right.data:"null")
	            +", parent: "+((this.parent!=null)?parent.data:"null");
  }
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}
