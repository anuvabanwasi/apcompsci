import java.util.ArrayList;
import java.util.List;

public class SetStuff {
	/**
	 * Returns an array list of integers that contains 17
	 * @return ArrayList<Integer> that contains 17
	 */
	ArrayList<Integer> ret17() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(17);
		return list;
	}

	/**
	 * Takes an array list of integers and returns its zeroth element
	 * @param al an ArrayList containing Integer elements 
	 * @return an Integer specifying the zeroth element of the list
	 */
	Integer zerothElement(ArrayList<Integer> al){
		return al.get(0);
	}

	/**
	 * A method that returns true when the argument ar, an integer array, contains a, the int argument
	 * @param a int element being sought
	 * @param ar integer array
	 * @return true when the array ar contains the element a
	 */
	boolean contains(int a, int[] ar){
		for(int i = 0; i < ar.length; i++) {
			if(ar[i] == a)
				return true;
		}
		return false;
	}

	/**
	 * Takes two arrays and returns true if any element of a is in b
	 * @param a integer array whose elements will be tested for presence in target array b
	 * @param b target integer array 
	 * @return true if any of the elements of array a are present in b
	 */
	boolean anyContains(int[] a, int[] b){
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				if(a[i] == b[j])
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns union of two int arrays a and b. Adds all the elements of int arrays a and b to result int array 
	 * @param a first int array
	 * @param b second int array
	 * @return the int array that contains all the elements in either a or b
	 */
	int[] union(int[] a, int[] b){
		int[] union = new int[a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			union[i] = a[i];
		}
		for(int i = 0; i < b.length; i++) {
			union[i + a.length] = b[i];
		}

		return union;
	}

	/**
	 * Computes intersection of two int arrays and returns an array containing values that are in both arrays
	 * It returns an array that
	 * 1. Is sorted
	 * 2. Has no repeat values
	 * 3. Contains any value that is in both a and b
	 * @param a first int array
	 * @param b second int array
	 * @return int array containing elements in both a and b
	 */
	int[] intersect(int[] a, int[] b){
		int i = 0, j = 0;
		a = sort(a);
		b = sort(b);
		int[] newArr = new int[findLength(a, b)];
		int count = 0;

		// adds elements common to both a and b to the new array
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				i++;
			else if (b[j] < a[i])
				j++;
			else {
				newArr[count] = b[j++];
				count++;
				i++;
			}
		}

		return newArr;
	}

	// sorts an array
	int[] sort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i+1] < arr[i]) {
				arr = swap(arr, i, i+1);
				for(int j = i; j > 0; j--) {
					if(arr[j-1] > arr[j])
						arr = swap(arr, j-1, j);
				}
			}
		}
		return arr;
	}

	// swap method for sorting the array
	public int[] swap(int[] array, int first, int second) {
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
		return array;
	}

	// finds the length of the intersected array
	int findLength(int[] a, int[] b) {
		int count = 0;
		int i = 0;
		int j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				i++;
			else if (b[j] < a[i])
				j++;
			else {
				count++;
				i++;
			}
		}
		return count;
	}

	/**
	 * Takes three arrays, one boolean one integer. and one String. 
	 * Returns the String at the index of the smallest integer for which the corresponding boolean is false. 
	 * For example: Boolean (visited): {true, false, false} integer (distance): {1,2,3} String (node): {"A", "B", "C"} results in a return value of "B" 
	 * because node B has a distance of 2 which is the smallest of the values not yet visited. 
	 * Returns the empty string if no distances qualify. Assumption - The largest value in distance is less than 99999.
	 * @param visited boolean array specifying whether node has been visited
	 * @param distance int array specifying distance of node
	 * @param nodeName String array specifying name of node
	 * @return the String at the index of the smallest integer for which the corresponding boolean is false
	 */
	String nearesUnvisitedNode(boolean[] visited, int[] distance, String[] nodeName){
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		for(int i = 0; i < distance.length; i++) {
			if(distance[i] < min && visited[i] == false) {
				min = distance[i];
				minIndex = i;
			}
		}

		if(min == Integer.MAX_VALUE)
			return "";

		return nodeName[minIndex];
	}

	/**
	 * Takes three ints and returns an arrayList containing them.
	 * @param a first integer
	 * @param b second integer
	 * @param c third integer
	 * @return an ArrayList containing the two integers
	 */
	ArrayList<Object> listify(int a, int b, int c){
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(a);
		list.add(b);
		list.add(c);

		return list;
	}

	/**
	 * Add an edge to the graph. An edge is represented by 3 integers - (a,b,c) where a and b are nodes and c is the distance
	 * Takes an ArrayList of ArrayList of integers, and three integers, 
	 * and makes those three integers into an ArrayList 
	 * Then, adds that ArrayList to the given ArrayList and returns that.
	 * @param graph ArrayList of ArrayList representing a graph 
	 * @param a first integer
	 * @param b second integer
	 * @param c third integer
	 * @return ArrayList of ArrayList of integers
	 */
	ArrayList<ArrayList<Object>> addEdge(ArrayList<ArrayList<Object>> graph, int a, int b, int c){
		ArrayList<Object> innerList = listify(a, b, c);
		graph.add(innerList);
		return graph;
	}

	/**
	 * Takes an ArrayList of ArrayLists of size three of integers and returns an ArrayList that contains the union of all the integers that appear in the first two elements of each ArrayList of size three. 
	 * @param edgelist graph represented as a list of edges. Each edge is a list of 3 integers.  
	 * @return a list containing the union of all the integers that appear in the first two elements of each ArrayList representing an edge
	 */
	List<Integer> collectNodes(ArrayList<ArrayList<Integer>> edgelist){
		List<Integer> unionList = new ArrayList<Integer>();
		for(List<Integer> a: edgelist) {
			addElements(unionList, a);
		}
		return unionList;
	}

	/**
	 * Adds first 2 elements of original list to a result list
	 * @param newList result list containing the first 2 elements of original list. Ensures element is not added if already present in result list.
	 * @param originalList List of integers of size 3 representing an edge (a,b,c) where a and b are node and c is distance between a and b
	 */
	void addElements(List<Integer> newList, List<Integer> originalList) {
		for(int i = 0; i < originalList.size(); i++) {
			if(i < 2 && newList.indexOf(originalList.get(i)) == -1) {
				newList.add(originalList.get(i));
			}
		}
	}
	
	/**
	 * Given an integer representing the size of an array and an integer representing the start node, creates an ArrayList of integers whose values are maximal except the entry at the index of the start, which will be zero.
	 * @param args
	 */
	ArrayList<Integer> setInfinity(int n, int start) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			newList.add(Integer.MAX_VALUE);
		}
		newList.set(start, 0);
		return newList;
	}

	ArrayList<Integer> buildList(int a, int b, int c){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(a);
		list.add(b);
		list.add(c);

		return list;
	}
	
	private static void printArr(int[] arr)
	{
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		SetStuff stuff = new SetStuff();
		
		System.out.println("================================ Return 17 =================================================");
		System.out.println(stuff.ret17());
		
		System.out.println("================================ Zeroth element =================================================");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(17);
		System.out.println(stuff.zerothElement(list));
		
		
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(3);
		list.add(1);
		list.add(4);
		list.add(1);
		list.add(5);
		System.out.println(stuff.zerothElement(list));
			
		list = new ArrayList<Integer>();
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(222);
		list.add(22);
		list.add(222);
		list.add(22);
		list.add(2222);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(314);
		list.add(159);
		list.add(265);
		list.add(3);		
		System.out.println(stuff.zerothElement(list));
		
		
		System.out.println("================================ Contains =================================================");
		System.out.println(stuff.contains(1, new int[]{1}));
		System.out.println(stuff.contains(77, new int[]{1, 2, 3, 4, 5, 6, 77, 7}));
		System.out.println(stuff.contains(1, new int[]{2, 3, 4, 5, 6, 7}));
		System.out.println(stuff.contains(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
		System.out.println(stuff.contains(3, new int[]{1, 2, 3, 4, 5, 6, 7}));

		
		System.out.println("================================ Any Contains =================================================");
		System.out.println(stuff.anyContains(new int[] {}, new int[]{}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{}));
		System.out.println(stuff.anyContains(new int[] {}, new int[]{1}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{1}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {2}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {3}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {1,2}, new int[]{1,2}));




		
		System.out.println("================================ Union =================================================");
		printArr(stuff.union(new int[] {}, new int[]{}));
		printArr(stuff.union(new int[] {1}, new int[]{}));
		printArr(stuff.union(new int[] {1, 2}, new int[]{3, 4}));
		printArr(stuff.union(new int[] {1, 2}, new int[]{}));
		printArr(stuff.union(new int[] {}, new int[]{3, 4}));
		printArr(stuff.union(new int[] {1,2}, new int[]{3, 4,5,6,7,8}));
		printArr(stuff.union(new int[] {1,2,7}, new int[]{3, 4}));
		printArr(stuff.union(new int[] {1,2,2222}, new int[]{3, 4}));

		
		System.out.println("================================ Intersect =================================================");
		printArr(stuff.intersect(new int[] {1, 2}, new int[]{3, 4}));
		printArr(stuff.intersect(new int[] {1, 2}, new int[]{1, 3, 4}));
		printArr(stuff.intersect(new int[] {1, 2}, new int[]{2, 3, 4}));
		printArr(stuff.intersect(new int[] {1, 2}, new int[]{1, 2, 3, 4}));
		printArr(stuff.intersect(new int[] {1, 2, 4, 5}, new int[]{3, 2, 4}));
		printArr(stuff.intersect(new int[] {1, 2, 4, 5}, new int[]{3, 4, 5, 7}));



		
		System.out.println("================================ NearestUnvisitedNode =================================================");
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, false}, new int[] {1, 2, 3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, true, true}, new int[] {1, 2, 3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, false, true}, new int[] {1, 2, 3}, new String[] {"AB", "BB", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, false, true}, new int[] {1, 2, 3, 4}, new String[] {"A", "B", "C", "D"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, true, false}, new int[] {1, 2, 3, 4}, new String[] {"A", "B", "C", "D"})) ;	
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, true, false}, new int[] {4, 3, 2, 1}, new String[] {"A", "B", "C", "D"}));	
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, true, false, true, false}, new int[] {-3, 4, 3, 2, 1}, new String[] {"hey", "A", "B", "C", "D"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, true, true, true, false}, new int[] {33, 4, 3, 2, 11}, new String[] {"hey", "A", "B", "C", "DD"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, false, false}, new int[] {1,2,3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, true, false}, new int[] {1,2,3}, new String[] {"A", "B", "CC"}));
		
		System.out.println("================================ Listify =================================================");
		System.out.println(stuff.listify(1, 2, 4));
		
		System.out.println("================================ Add Edge =================================================");
		ArrayList<ArrayList<Object>> graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		graph.add(stuff.listify(3, 1, 4));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 7));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 3, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 33));
		graph.add(stuff.listify(3, 1, 4));
		graph.add(stuff.listify(3, 5, 7));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
				
		System.out.println("================================ collectNodes =================================================");
		ArrayList<ArrayList<Integer>> edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 2, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 7, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(7, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 1, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 444));
		edgeList.add(stuff.buildList(3, 2, 44));
		System.out.println(stuff.collectNodes(edgeList));
		
		
		System.out.println("================================ setInfinity =================================================");
		System.out.println(stuff.setInfinity(1, 0));
		System.out.println(stuff.setInfinity(2, 0));
		System.out.println(stuff.setInfinity(3, 1));
		System.out.println(stuff.setInfinity(4, 2));
		System.out.println(stuff.setInfinity(5, 3));
		System.out.println(stuff.setInfinity(6, 3));
	}
}
