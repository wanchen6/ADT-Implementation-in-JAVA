package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
  
  public void insert (EntryPair entry) {
	  int hole = ++size;
	  while (entry.priority < array[hole/2].priority) {
		  array[hole] = array[hole/2];
		  hole = hole/2;
	  }
	  array[hole] = entry;
  }
  
  public void delMin () {
	  if (size == 0) return;
	  EntryPair temp = array[size--];
	  int child;  
	  int hole;
	  for (hole = 1; hole * 2 <= size; hole = child) {
		  child = hole * 2;
		  if (child != size && array[child].priority > array[child + 1].priority) {
			  child ++;
		  }
		  if (temp.priority > array[child].priority) {
			  array[hole] = array[child];
		  } else break;
	  }
	  array[hole] = temp;
  }
  
  public EntryPair getMin () {
	  if (size == 0) return null;
	  return this.array[1];
  }
  
  public int size () {
	  return size;
  }
  
  public void build (EntryPair [] entries) {
	  int arraySize = entries.length;
	  for (int i = 1; i <= arraySize; i++) {
		  this.array[i] = entries[i-1];
	  }
	  size = arraySize;
	  int hole;
	  int child;	  
	  int currentHole = size/2;
	  while (currentHole >= 1) {
		  EntryPair temp = array[currentHole];
		  for (hole = currentHole; hole * 2 <= size; hole = child) {
			  child = hole * 2;
			  if (child != size && array[child].priority > array[child + 1].priority) {
				  child ++;
			  }
			  if (temp.priority > array[child].priority) {
				  array[hole] = array[child];
			  } else break;
		  }
		  array[hole] = temp;
		  currentHole --;
	  }  
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }
}
