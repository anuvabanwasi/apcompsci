/**
 * Mergeable priority queue implemented as a Skew Heap. This is a min heap -
 * head of the queue is the element with the smallest value. Skew Heap has
 * following conditions Heap order property Insert and Delete operations use
 * merge operation Time Complexity - O(log n) 
 * Reference - https://en.wikipedia.org/wiki/Skew_heap
 * 
 * @author Anuva Banwasi
 * @version 02/25/2018
 *
 */
public class MergeablePriorityQueue {
	private Node root;
	private int size = 0;

	MergeablePriorityQueue() {
	}

	/**
	 * Insert a value into a priority queue. Insertion is as follows
	 * 
	 * (1) Create a new node with the data equal to val. (2) Merge root with the
	 * tree with one node
	 * 
	 * @param val
	 *            value to be inserted into the priority queue
	 */
	public void add(int val) {
		Node n = new Node(val);
		root = merge(root, n);
		size++;
	}

	/**
	 * Compare the roots of 2 heaps p and q Let p with the heap with the smaller
	 * value in root Result heap r formed by merging p and q is generated as follows
	 *
	 * (1) Store the left subtree of p in a temp node (2) Recursively merge p's
	 * right subtree with q and assign this merged node to p's left subtree (3) Set
	 * p's right subtree to temp
	 * 
	 * @param p
	 *            first heap
	 * @param q
	 *            second heap
	 * @return merged heap r formed by merging p and q
	 * 
	 * Reference - https://en.wikipedia.org/wiki/Skew_heap
	 */
	private Node merge(Node p, Node q) {
		if (p == null)
			return q; // if one of the heaps is null, return the other
		if (q == null)
			return p; // if one of the heaps is null, return the other

		if (p.data < q.data) {
			Node temp = p.left;
			p.left = merge(p.right, q);
			p.right = temp;
			return p;
		} else
			return merge(q, p);
	}

	/**
	 * Delete the head of the priority queue
	 * 
	 * @return Node at head of priority queue or null if the queue is empty
	 */
	public Node poll() {

		if (root == null)
			return null;

		Node head = root;

		root = merge(root.left, root.right);

		return head;
	}

	/**
	 * Removes the value specified by val from the priority queue.
	 * 
	 * @param val
	 * @return true if queue contains val, false if the queue is null or does not
	 *         contain the element
	 */
	public boolean remove(int val) {

		Node searchNode = remove(root, val);

		if (searchNode != null)
			return true;
		else
			return false;
	}

	/**
	 * Remove element val from priority queue. The element is removed from priority
	 * queue as follows
	 * 
	 * (1) if the left node of current node n contains the value to be deleted,
	 * merge the left and right subtrees of n.left (2) if the right node of current
	 * node n contains the value to be deleted, merge the left and right subtrees of
	 * n.right (3) recurse to left and right subtrees
	 * 
	 * @param n
	 *            node of priority queue
	 * @param val
	 *            element to remove
	 * @return Node containing element to remove or null if element not found in
	 *         priority queue 
	 * Reference -
	 *         https://stackoverflow.com/questions/21463973/remove-method-for-skew-heap
	 */
	private Node remove(Node n, int val) {

		if (n == null)
			return null;

		if (n.left != null && n.left.data == val) {
			n.left = merge(n.left.left, n.left.right);
			return n;
		}

		if (n.right != null && n.right.data == val) {
			n.right = merge(n.right.left, n.right.right);
			return n;
		}

		remove(n.left, val);
		remove(n.right, val);

		return null;

	}

	/**
	 * Delete all elements from priority queue.
	 */
	public void clear() {
		root = null;
	}

	/**
	 * Returns true if priority queue contains the specified element.
	 * 
	 * @param val
	 *            value to be checked for presence in queue
	 * @return boolean true if the queue contains at least one node whose data is
	 *         equal to val
	 */
	public boolean contains(int val) {

		Node searchNode = inorder(root, val);

		if (searchNode != null)
			return true;
		else
			return false;
	}

	/**
	 * Inorder traversal of heap. If the element sought is found, set the found flag
	 * to true
	 * 
	 * @param n
	 *            Node of the priority queue
	 * @param val
	 *            value to be checked for presence in queue
	 */

	private Node inorder(Node n, int val) {
		if (n == null)
			return null;

		Node l = inorder(n.left, val);

		if (n.data == val)
			return n;

		Node r = inorder(n.right, val);

		if (l != null)
			return l;
		if (r != null)
			return r;
		return null;
	}

	/**
	 * Return the root node of the priority queue but does not remove it from the
	 * queue.
	 * 
	 * @return root of the queue or null if root is empty
	 */
	public Node peek() {
		return root;
	}

	/**
	 * Return the number of elements in queue
	 * 
	 * @return number specifying number of elements in queue
	 */
	public int size() {
		return size;
	}

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

	}

	/**
	 * Inorder traversal of heap.
	 * 
	 * @param n
	 *            Node of the priority queue
	 */

	public void inorder() {
		inorder(root);
	}

	private void inorder(Node n) {
		if (n == null)
			return;

		inorder(n.left);
		System.out.println(n.data);

		inorder(n.right);

	}
}

/**
 * Node of the priority queue. Each node has 3 elements. An integer data, left
 * node pointing to a left subtree and a right node pointing to a right subtree.
 * 
 * @author Anuva Banwasi @version 02/25/2018
 */
class Node {
	int data;
	Node left;
	Node right;

	Node(int val) {
		this.data = val;
		left = null;
		right = null;
	}
}
