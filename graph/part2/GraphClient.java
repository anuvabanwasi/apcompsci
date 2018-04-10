
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GraphClient {

	public static void main(String[] args) {
		Graph frame = new Graph("Draw a graph", true);

		frame.setSize(1000, 900);

		frame.setVisible(true);

		List<List<List<Integer>>> adjList = buildAdjacencyList1(); // get adjacency list representing graph

		for (int i = 0; i < adjList.size(); i++) {
			frame.addNode(i); // add nodes to graph
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

		for (Map.Entry<Integer, List<Edge>> entry : frame.prevEdges.entrySet()) { // keep list in sorted order
			Collections.sort(entry.getValue());
		}

		// print hashmap
		for (Map.Entry<Integer, List<Edge>> entry : frame.prevEdges.entrySet()) {
			System.out.print(entry.getKey() + " -> ");
			for (Edge e : entry.getValue()) {
				System.out.print("( " + e.getSource().identifier + ", " + e.getDestination().identifier + ", "
						+ e.getWeight() + " ) " + " ");
			}
			System.out.println();
		}
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
		addEdge(edges0, 1, 100);
		addEdge(edges0, 2, 400);

		List<List<Integer>> edges1 = new ArrayList<List<Integer>>();
		addEdge(edges1, 3, 300);

		List<List<Integer>> edges2 = new ArrayList<List<Integer>>();
		addEdge(edges2, 3, 60);
		addEdge(edges2, 4, 700);

		List<List<Integer>> edges3 = new ArrayList<List<Integer>>();
		addEdge(edges3, 4, 8);
		addEdge(edges3, 5, 900);

		List<List<Integer>> edges4 = new ArrayList<List<Integer>>();
		addEdge(edges4, 5, 10000);

		List<List<Integer>> edges5 = new ArrayList<List<Integer>>();

		adjList.add(edges0);
		adjList.add(edges1);
		adjList.add(edges2);
		adjList.add(edges3);
		adjList.add(edges4);
		adjList.add(edges5);

		return adjList;
	}

	private static List<List<List<Integer>>> buildAdjacencyList1() {

		List<List<List<Integer>>> adjList = new ArrayList<List<List<Integer>>>();

		List<List<Integer>> edges0 = new ArrayList<List<Integer>>();
		addEdge(edges0, 1, 150);
		addEdge(edges0, 2, 400);

		List<List<Integer>> edges1 = new ArrayList<List<Integer>>();
		addEdge(edges1, 3, 450);

		List<List<Integer>> edges2 = new ArrayList<List<Integer>>();
		addEdge(edges2, 3, 6000);
		addEdge(edges2, 4, 700);

		List<List<Integer>> edges3 = new ArrayList<List<Integer>>();
		addEdge(edges3, 4, 5);

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
