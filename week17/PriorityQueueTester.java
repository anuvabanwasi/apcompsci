public class PriorityQueueTester {
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
		
		System.out.println();

		PriorityQueue pq2 = new PriorityQueue();
		System.out.println("=========================== Adding elements to priority queue ===========================");
		pq2.add(4);
		pq2.add(7);
		pq2.add(9);
		pq2.add(4);
		pq2.add(1);
		pq2.add(2);
		
		System.out.println("=========================== Print Heap ===========================");
		pq2.printHeap();
		
		System.out.println("=========================== Returning size of priority queue ===========================");
		System.out.println(pq2.size());
		
		System.out.println( "=========================== Checking if priority queue contains element ===========================");
		System.out.println(pq2.contains(4));
		
		System.out.println("=========================== Delete specified element from priority queue if it exists ===========================");
		System.out.println(pq2.remove(7));
		
		System.out.println("=========================== Print Heap ===========================");
		pq2.printHeap();
		
		System.out.println( "=========================== Deleting all elements of priority queue ===========================");
		pq2.clear();
		
		System.out.println();
		System.out.println("=========================== Adding elements to priority queue ===========================");
		pq2.add(4);
		pq2.add(2);
		pq2.add(1);
		pq2.add(7);
		pq2.add(9);
		pq2.add(5);
		
		System.out.println( "=========================== Repeatedly remove max element of priority queue ===========================");
		while (pq2.size() != 0)
			System.out.println("removed : " + pq2.poll());
	}
}
