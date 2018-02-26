/**
 * Priority queue implementation using arrays. This is a max heap - head of the queue is the element with the largest value
 * Time Complexity - Get largest element in queue - O(1), Insert - O(logn) 
 * Reference - https://en.wikipedia.org/wiki/Priority_queue
 * @author Anuva Banwasi
 * @version 02/25/2018
 *
 */
public class PriorityQueue {
	int[] arr = new int[15];
	int size = 0;
	
	public static void main(String args[]){
		PriorityQueue pq = new PriorityQueue();
		System.out.println("=========================== Adding elements to priority queue ===========================");
		pq.add(6);
		pq.add(4);
		pq.add(2);
		pq.add(7);
		pq.add(5);
		pq.add(3);
		
		System.out.println("=========================== Print Heap ===========================");
		pq.printHeap();
		
		System.out.println("=========================== Returning size of priority queue ===========================");
		System.out.println(pq.size());
		
		System.out.println( "=========================== Checking if priority queue contains element ===========================");
		System.out.println(pq.contains(4));
		
		System.out.println("=========================== Delete specified element from priority queue if it exists ===========================");
		System.out.println(pq.remove(7));
		
		System.out.println("=========================== Print Heap ===========================");
		pq.printHeap();
		
		System.out.println( "=========================== Deleting all elements of priority queue ===========================");
		pq.clear();
		
		System.out.println();
		System.out.println("=========================== Adding elements to priority queue ===========================");
		pq.add(6);
		pq.add(4);
		pq.add(2);
		pq.add(7);
		pq.add(5);
		pq.add(3);
		
		System.out.println( "=========================== Repeatedly remove max element of priority queue ===========================");
		while (pq.size() != 0)
			System.out.println("removed : " + pq.poll());
		
	}

	/**
	 * Insert a value into a priority queue. Insertion is as follows
	 * (1) Insert the element at the end of the array. 
	 * (2) swimUp the element to its correct position in the heap
	 * 
	 * @param val value to be inserted into the priority queue
	 */
	private void add(int num) {
		arr[size] = num;
		//when inserting an element in the heap, do not rebuild heap - building heap is n log n operation, just do a swim up which is log n operation
		swimUp(size);
		size++;
	}
	
	/**
	 * Build a heap out of elements of array of length n at position i
	 * If the heap order is violated at a node, then fix the violation by exchanging the node with the larger of its 2 children. This may cause
	 * a heap order violation at the child, therefore recursively swim down the heap fixing heap violations if any till heap order fixed or leaf is reached
	 * Reference - https://algs4.cs.princeton.edu/24pq/
	 * @param n number of elements in array
	 * @param i position of node
	 */
	private void heapify(int n, int i) {
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		if(l < n && arr[l] > arr[i])
			largest = l;
		
		if(r < n && arr[r] > arr[largest])
			largest = r;
		
		if(largest != i){
			swap(arr, largest, i);
			
		 heapify(n, largest);
		}
	}
	
	/**
	 * Delete the head of the priority queue
	 * 
	 * @return value at head of priority queue or -1 if the queue is empty
	 */
	private int poll(){
		if(arr.length == 0)
			return -1;
		
		int max = arr[0];
		size--;
		arr[0] = arr[size];
		heapify(size, 0);
		
		return max;
	}
	
	/**
	 * Remove an occurrence of value from the priority queue if present
	 * @param value value to be removed from queue
	 * @return true if the remove was successful, false otherwise
	 */
	private boolean remove(int value) {
		int index = index(value);
		if(index != -1) {
			size--;
			arr[index] = arr[size];
			heapify(size, index);

			return true;
		}
		return false;
	}
	
	/**
	 * Fix heap violation at position i
	 * If the heap order is violated at a node, then fix the violation by exchanging the node with its parent. This may cause
	 * a heap order violation at the parent, therefore recursively swim up the heap fixing heap violations if any till heap order fixed or root is reached
	 * Reference - https://algs4.cs.princeton.edu/24pq/
	 * @param i position of node
	 */
	
	private void swimUp(int i) {
		
		while( i >= 0 && arr[i] > arr[(i-1)/2]){
			swap(arr,i, (i-1)/2);
			i = (i-1) / 2;
		}
	}
	
	/**
	 * Returns true if priority queue contains the specified element.
	 * 
	 * @param value value to be checked for presence in queue
	 * @return boolean true if the queue contains at least one node whose data is equal to value
	 */
	public boolean contains(int value) {
		for(int i  = 0; i < arr.length; i++) {
			if(arr[i] == value)
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the index of the specified value in the array representing the heap
	 * @param value value to be checked for presence in the queue
	 * @return index of the value in the priority queue
	 */
	private int index(int value) {
		for(int i  = 0; i < arr.length; i++) {
			if(arr[i] == value)
				return i;
		}
		return -1;
	}
	
	/**
	 * Return the value at the root node of the priority queue but does not remove it from the
	 * queue.
	 * 
	 * @return root of the queue or -1 if root is empty
	 */
	public int peek() {
		if(arr.length < 0)
			return -1;
		return arr[0];
	}
	
	/**
	 * Return the number of elements in queue
	 * 
	 * @return number specifying number of elements in queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Delete all elements from priority queue.
	 */
	public void clear() {
		arr = new int[arr.length];
		size = 0;
	}

	private void printHeap() {
		for(int i = 0; i < size; i++)
			System.out.print (arr[i] + " ");
		System.out.println();
	}

	private void swap(int[] arr, int largest, int i) {
		//System.out.println("swapping " + " arr[largest] "  + arr[largest] + " and " + " i " + arr[i]);
		int tmp = arr[i];
		arr[i] = arr[largest];
		arr[largest] = tmp;
	}
}
