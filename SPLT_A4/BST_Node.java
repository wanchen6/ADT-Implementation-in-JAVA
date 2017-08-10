package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public BST_Node containsNode(String s) {
	  BST_Node tree = this;
	  BST_Node pTree = null;
	  while (tree != null) {
		  pTree = tree;
		  if (tree.data.compareTo(s) > 0) {
			  tree = tree.left;
		  } else if (tree.data.compareTo(s) < 0) {
			  tree = tree.right;
		  } else {
			  splay(tree);
			  return tree;
		  }			  
	  }
	  splay(pTree);
	  return pTree;
  }
  public BST_Node insertNode(String s) {
	  BST_Node tree = this;
	  BST_Node pTree = null;
	  while (tree != null) {
		  pTree = tree;
		  if (tree.data.compareTo(s) > 0) {			  
			  tree = tree.left;
		  } else if (tree.data.compareTo(s) < 0) {
			  tree = tree.right;
		  } else { //if the tree contains s
			  splay(tree);
			  tree.justMade = false;
			  return tree;
		  }
	  }
	  tree = new BST_Node(s);
	  tree.par = pTree;
	  if (pTree.data.compareTo(s) < 0) {
		  pTree.right = tree;
	  } else pTree.left = tree;
	  splay(tree);
	  tree.justMade = true;
	  return tree;
  }
  /*public boolean removeNode(String s) {
	  return false;
  }*/
  public BST_Node findMin() {
	  BST_Node tree = this;
	  if (tree != null) {
		  while (tree.left != null)
			  tree = tree.left;
	  }
	  splay(tree);
	  return tree;
  }
  public BST_Node findMax() {
	  BST_Node tree = this;
	  if (tree != null) {
		  while (tree.right != null)
			  tree = tree.right;
	  }
	  splay(tree);
	  return tree;
  }
  public int getHeight() {
	  return 1 + Math.max((left != null)? left.getHeight():-1, (right != null)? right.getHeight():-1);
  }
  
  private void splay(BST_Node toSplay) { 	 
	  while (toSplay.par != null) {
		  if (toSplay.par.right == toSplay) {
			  rotateRight(toSplay);
		  } else if (toSplay.par.left == toSplay) {
			  rotateLeft(toSplay);
		  }
	  }
  } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  

  // --- end example methods --------------------------------------

  private void rotateLeft(BST_Node splayLeft) {
	  if (splayLeft.par != null && splayLeft.par.par == null) {
		  splayLeft.par.left = splayLeft.right;
		  splayLeft.par.par = splayLeft;		  
		  if (splayLeft.right != null) {
			  splayLeft.right.par = splayLeft.par; 
		  } 
		  splayLeft.right = splayLeft.par;
		  splayLeft.par = null;
	  } else if (splayLeft.par != null && splayLeft.par.par.left == splayLeft.par) {
		  BST_Node tree = splayLeft;
		  while (tree.par != null && tree == tree.par.left) {
			  tree.par.left = tree.right;
			  if (tree.right != null) {
				  tree.right.par = tree.par;  
			  }
			  tree.right = tree.par;		
			  tree = tree.par;
		  }
		  if (tree.par != null) {
			  splayLeft.par = tree.par;
			  tree.par.right = splayLeft;
		  } else {
			  splayLeft.par = null;
		  }
		  for (BST_Node ptree = splayLeft; ptree != tree; ptree = ptree.right) {
			  ptree.right.par = ptree;
		  }		  	  
	  } else if (splayLeft.par != null && splayLeft.par.par.right == splayLeft.par) {
		  splayLeft.par.left = splayLeft.right;
		  if (splayLeft.right != null) {
			  splayLeft.right.par = splayLeft.par;
		  }
		  splayLeft.right = splayLeft.par;
		  splayLeft.par.par.right = splayLeft.left;
		  if (splayLeft.left != null) {
			  splayLeft.left.par = splayLeft.par.par;
		  }
		  splayLeft.left = splayLeft.par.par;
		  if (splayLeft.par.par.par != null) {
			  if (splayLeft.par.par.par.right == splayLeft.par.par) {
				  splayLeft.par.par.par.right = splayLeft;
			  } else {
				  splayLeft.par.par.par.left = splayLeft;
			  }
		  }	
		  splayLeft.par = splayLeft.par.par.par;
		  splayLeft.right.par = splayLeft;
		  splayLeft.left.par = splayLeft;		  
	  }	  
  }
  
  private void rotateRight(BST_Node splayRight) {
	  if (splayRight.par != null && splayRight.par.par == null) {		  
		  splayRight.par.right = splayRight.left;
		  splayRight.par.par = splayRight;	
		  if (splayRight.left != null) {
			  splayRight.left.par = splayRight.par; 
		  }
		  splayRight.left = splayRight.par;	
		  splayRight.par = null;
	  } else if (splayRight.par != null && splayRight.par.par.right == splayRight.par) {
		  BST_Node tree = splayRight;
		  while (tree.par != null && tree == tree.par.right) {
			  tree.par.right = tree.left;
			  if (tree.left != null) {
				  tree.left.par = tree.par;  
			  } 
			  tree.left = tree.par;		
			  tree = tree.par;
		  }
		  if (tree.par != null) {
			  splayRight.par = tree.par;
			  tree.par.left = splayRight;
		  } else {
			  splayRight.par = null;
		  }
		  for (BST_Node ptree = splayRight; ptree != tree; ptree = ptree.left) {
			  ptree.left.par = ptree;
		  }		  		  
	  } else if (splayRight.par != null && splayRight.par.par.left == splayRight.par) {
		  splayRight.par.right = splayRight.left;
		  if (splayRight.left != null) {
			  splayRight.left.par = splayRight.par;
		  }
		  splayRight.left = splayRight.par;
		  splayRight.par.par.left = splayRight.right;
		  if (splayRight.right != null) {
			  splayRight.right.par = splayRight.par.par;
		  }
		  splayRight.right = splayRight.par.par;
		  if (splayRight.par.par.par != null) {
			  if (splayRight.par.par.par.left == splayRight.par.par) {
				  splayRight.par.par.par.left = splayRight;
			  } else {
				  splayRight.par.par.par.right = splayRight;
			  }
		  }	
		  splayRight.par = splayRight.par.par.par;
		  splayRight.left.par = splayRight;
		  splayRight.right.par = splayRight;		  
	  }
  }
  
}
