
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class creates a Binary Search Tree(BST). A BST is a binary tree where
 * the nodes are arranged in order : for each node, all elements in its left
 * subtree are less-or-equal to the node (<=), and all the elements in its right
 * subtree are greater than the node (>) Reference -
 * http://cslibrary.stanford.edu/110/BinaryTrees.html
 * 
 * @author Anuva Banwasi
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>> {

	private T datum;
	private BST<T> left;
	private BST<T> right;

	/**
	 * Constructor takes in the datum for the BST. A single node is also a BST
	 */
	public BST(T datum) {
		this.datum = datum;
		left = null;
		right = null;
	}

	/**
	 * Insert datum into the BST , respecting the BST order. To insert into a BST,
	 * we check the root and recursively insert the new node to the left subtree if
	 * its key is less than that of the root, or the right subtree if its key is
	 * greater than or equal to the root 
	 * Reference - https://en.wikipedia.org/wiki/Binary_search_tree#Insertion
	 * 
	 * @param datum
	 */
	public void insert(T num) {
		if (num.compareTo(datum) < 0) {
			if (left == null)
				left = new BST<T>(num);
			else
				left.insert(num);

		} else {
			if (right == null)
				right = new BST<T>(num);
			else
				right.insert(num);
		}
	}

	/**
	 * Print BST using inorder traversal. Inorder traversal prints left subtree,
	 * followed by node and then followed by right sub tree. In infix or inorder
	 * tranversal, we recursively traverse the left subtree of the root node,
	 * accessing the node itself, then recursively traverse the right subtree of the
	 * node 
	 * Reference - https://en.wikipedia.org/wiki/Binary_search_tree#Traversal
	 */
	public void printTree() {
		if (left != null) {
			left.printTree();
		}
		System.out.print(datum + " ");

		if (right != null) {
			right.printTree();
		}
	}

	/**
	 * Calculates the depth of the BST. The number of nodes from the root to the
	 * farthest leaf. The depth of an empty BST is 0. The depth of a BST with only 1
	 * nodes is 1. 
	 * Reference - https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/trees.html
	 * 
	 * @return depth
	 */
	public int depth() {
		if (left == null && right == null)
			return 1;

		if (left == null && right != null)
			return right.depth() + 1;
		else if (left != null && right == null)
			return left.depth() + 1;
		else
			return Math.max(left.depth(), right.depth()) + 1;

	}

	/**
	 * Returns the BST node with the minimum data value in the tree
	 * 
	 * @return BST representing node with the minimum datum in the entire tree
	 */
	private BST<T> min() {
		if (left == null)
			return this;
		else
			return left.min();
	}

	/**
	 * Delete an value from a BST Finds datum in the binary search tree; if found,
	 * deletes node, ensure tree remains a BST after deletion
	 * 
	 * @param val
	 *            to delete from the BST
	 * @return
	 */
	private BST<T> delete(T val) {

		if (left == null && right == null && datum != val) {
			System.out.println("not found");
			return this;
		} else if (left == null && right == null)
			return null;

		if (val.compareTo(datum) < 0) {
			if (left != null) {
				left = left.delete(val);
			}
		} else if (val.compareTo(datum) > 0) {
			if (right != null) {
				right = right.delete(val);
			}
		} else {
			if (left == null)
				return right;
			else if (right == null)
				return left;
			else {
				BST<T> temp = right.min();
				this.datum = temp.datum;
				this.right = right.delete(datum);
			}
		}
		return this;
	}

	/*
	 * Accessors
	 */
	public T getDatum() {
		return datum;
	}

	public BST<T> getLeft() {
		return left;
	}

	public BST<T> getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "BST [datum=" + datum + ", left=" + left + ", right=" + right + "]";
	}

	public static void main(String[] args) {

		// Create a BST by inserting the numbers 1 ... 10000 in random order
		List<Integer> arrList = new ArrayList<Integer>();

		for (int i = 1; i <= 10000; i++)
			arrList.add(i);

		Collections.shuffle(arrList);

		BST<Integer> bt = null;

		for (int num : arrList) {
			if (bt == null) {
				bt = new BST<Integer>(num);
			} else {
				bt.insert(num);
			}
		}
		
		System.out.println("============== Inorder traversal of tree after insertion =============");
		bt.printTree();
		System.out.println();

		System.out.println("========================== Depth of the tree =========================");
		System.out.println(bt.depth());
		System.out.println();

		int valToDelete = 10;
		System.out.println(
				"========================== Deletion of " + valToDelete + " in BST ===========================");
		bt = bt.delete(valToDelete);
		System.out.println();

		System.out.println("================== Inorder traversal of tree after deletion ================");
		bt.printTree();
		System.out.println();
	}
}
