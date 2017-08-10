/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node root;//this will be the entry point to your linked list (the head)
  private int lstSize = 0;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    root=new Node(0); //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!

  public boolean insert(Node n, int index) {
	  Node p;
	  //insert the node from index = 0
	  if (index > size() || index < 0) {
		  return false;
	  }
	  //get the node p at 'index-1'
	  if (index == 0) {
		  p = getRoot();
	  } else {
		  p = get(index - 1);
	  }

	  if (index == size()) {	//add a node at the end of the linked list
		  n.prev = p;
		  p.next = n;
	  }else {					//insert a node in the middle of the linked list
		  n.prev = p;
		  n.next = p.next;
		  p.next.prev = n;
		  p.next = n;
	  }
	  lstSize ++;
	  return true;
  }
  
  public boolean remove(int index) {
	  //do not allow to remove the root
	  if (index >= size() || index < 0) {
		  return false;
	  }
	  //get the node p at 'index'
	  Node p = get(index);
	  if (index == size()-1) {		//remove a node at the end
		  p.prev.next = null;
	  } else {						//remove a node in the middle
		  p.prev.next = p.next;
		  p.next.prev = p.prev;
	  }

	  lstSize --;
	  return true;
  }
  
  public Node get(int index) {
	  Node p = root;
	  //do not allow to get index = -1, it is hided
	  if (index > size() || index < 0) {
		  return null;
	  }
	  for (int i = 0; i < index + 1; i++) {
		  p = p.next;
	  }
	  return p;
  }
   
  public int size() {
	  return lstSize;
  }

  public boolean isEmpty() {
	  return (size() == 0);
  }
  
  public void clear() {
	  root.next = null;
	  lstSize = 0;	  
  }
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return root;
  }
}
