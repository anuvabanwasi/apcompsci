public class MergeablePriorityQueueTester {
	public static void main(String[] args) throws Exception {
		MergeablePriorityQueue mpq = new MergeablePriorityQueue();

		System.out.println("=========================== Adding elements to priority queue ===========================");
		mpq.add(6);
		mpq.add(4);
		mpq.add(2);
		mpq.add(7);
		mpq.add(5);
		mpq.add(3);

		System.out.println("=========================== Returning size of priority queue ===========================");
		System.out.println(mpq.size());

		System.out.println( "=========================== Checking if priority queue contains element ===========================");
		System.out.println(mpq.contains(4));

		System.out.println("=========================== Delete specified element from priority queue if it exists ===========================");
		System.out.println(mpq.remove(4));

		System.out.println( "=========================== Deleting all elements of priority queue ===========================");
		mpq.clear();

		System.out.println();
		mpq.add(6);
		mpq.add(4);
		mpq.add(2);
		mpq.add(7);
		mpq.add(5);
		mpq.add(3);

		System.out.println( "=========================== Repeatedly remove min element of priority queue ===========================");
		while (mpq.root != null)
			System.out.println("removed : " + mpq.poll().data);
		
		System.out.println();
		
		MergeablePriorityQueue mpq2 = new MergeablePriorityQueue();

		System.out.println("=========================== Adding elements to priority queue ===========================");
		mpq2.add(7);
		mpq2.add(5);
		mpq2.add(4);
		mpq2.add(6);
		mpq2.add(1);
		mpq2.add(2);

		System.out.println("=========================== Returning size of priority queue ===========================");
		System.out.println(mpq2.size());

		System.out.println( "=========================== Checking if priority queue contains element ===========================");
		System.out.println(mpq2.contains(5));

		System.out.println("=========================== Delete specified element from priority queue if it exists ===========================");
		System.out.println(mpq2.remove(1));

		System.out.println( "=========================== Deleting all elements of priority queue ===========================");
		mpq2.clear();

		System.out.println();
		mpq2.add(7);
		mpq2.add(6);
		mpq2.add(5);
		mpq2.add(4);
		mpq2.add(1);
		mpq2.add(2);

		System.out.println( "=========================== Repeatedly remove min element of priority queue ===========================");
		while (mpq2.root != null)
			System.out.println("removed : " + mpq2.poll().data);
	}
}
