
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphClient {

	public static void main(String[] args) {

		Graph frame = new Graph("Draw a Graph");
		frame.setSize(800, 800);
		frame.setVisible(true);
		
		List<List<List<Integer>>> adjList = buildAdjacencyList(); // get adjacency list representing graph
		List<int[]> coordinates = getCoordinates1(); // get coordinates of the nodes of graph
		
		for (int i = 0; i < adjList.size(); i++) {

			int x = coordinates.get(i)[0];
			int y = coordinates.get(i)[1];

			frame.addNode(i, x, y); // add nodes to graph
		}
		int cnt = 0;
		for (List<List<Integer>> edgeList : adjList) {

			int source = frame.nodes.get(cnt).identifier;

			for (List<Integer> edge : edgeList) {
				int dest = edge.get(0);
				int weight = edge.get(1);

				frame.addEdge(source, dest, weight); // add edges to graph
			}
			cnt++;
		}
	}

	/**
	 * Sample 1 representing coordinates of nodes of graph
	 * 
	 * @return
	 */
	private static List<int[]> getCoordinates() {
		List<int[]> coordinates = new ArrayList<int[]>();

		coordinates.add(new int[] { 200, 200 });
		coordinates.add(new int[] { 400, 100 });
		coordinates.add(new int[] { 400, 300 });
		coordinates.add(new int[] { 600, 100 });
		coordinates.add(new int[] { 600, 300 });
		return coordinates;
	}

	/**
	 * Sample 2 representing coordinates of nodes of graph
	 * 
	 * @return
	 */
	private static List<int[]> getCoordinates1() {
		List<int[]> coordinates = new ArrayList<int[]>();

		coordinates.add(new int[] { 200, 200 });
		coordinates.add(new int[] { 300, 300 });
		coordinates.add(new int[] { 300, 100 });
		coordinates.add(new int[] { 400, 300 });
		coordinates.add(new int[] { 500, 100 });
		return coordinates;
	}

	/**
	 * Sample 3 representing coordinates of nodes of graph
	 * 
	 * @return
	 */
	private static List<int[]> getCoordinates2() {
		List<int[]> coordinates = new ArrayList<int[]>();

		coordinates.add(new int[] { 200, 200 });
		coordinates.add(new int[] { 200, 300 });
		coordinates.add(new int[] { 300, 200 });
		coordinates.add(new int[] { 400, 200 });
		coordinates.add(new int[] { 400, 100 });
		return coordinates;
	}

	/**
	 * Build an adjacency list
	 * 
	 * @return List of List of List of Integers representing an adjacency list
	 *         representation of the graph
	 */
	private static List<List<List<Integer>>> buildAdjacencyList() {

		List<List<List<Integer>>> adjList = new ArrayList<List<List<Integer>>>();

		List<List<Integer>> edges0 = new ArrayList<List<Integer>>();
		addEdge(edges0, 1, 8);
		addEdge(edges0, 2, 1000);

		List<List<Integer>> edges1 = new ArrayList<List<Integer>>();
		addEdge(edges1, 3, 50);

		List<List<Integer>> edges2 = new ArrayList<List<Integer>>();
		addEdge(edges2, 3, 250);
		addEdge(edges2, 4, 250);

		List<List<Integer>> edges3 = new ArrayList<List<Integer>>();
		addEdge(edges3, 4, 450);

		List<List<Integer>> edges4 = new ArrayList<List<Integer>>();

		adjList.add(edges0);
		adjList.add(edges1);
		adjList.add(edges2);
		adjList.add(edges3);
		adjList.add(edges4);

		return adjList;
	}

	/**
	 * Take an list of lists of integers, and two integers, and make those two
	 * integers into a list and add that list to the given list.
	 * 
	 * @param edges
	 *            List of List of Integers representing edges
	 * @param a
	 *            first integer
	 * @param b
	 *            second integer
	 */
	private static void addEdge(List<List<Integer>> edges, int a, int b) {
		List<Integer> innerList = listify(a, b);
		edges.add(innerList);
	}

	/**
	 * Take two ints and return an List containing them.
	 * 
	 * @param a
	 *            first integer
	 * @param b
	 *            second integer
	 * @return List containing the 2 integers
	 */
	private static List<Integer> listify(int a, int b) {
		return Arrays.asList(a, b);
	}
}
