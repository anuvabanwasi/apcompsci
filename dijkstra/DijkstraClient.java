

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Client program to test Dijkstra algorithm
 * @author Anuva
 */
public class DijkstraClient {

	public static void main(String[] args) throws Exception {

		List<List<List<Integer>>> adjList = buildAdjacencyList();
		
		Graph inputGraph = new Graph(adjList);
		Node inputNode = new Node(6);
				
		Dijkstra d = new Dijkstra(inputGraph, inputNode);
		
		List<int[]> expectedDist = buildExpectedDist();
		d.setExpectedDist(expectedDist);
		
		List<int[]> expectedPrev = buildExpectedPrev();
		d.setExpectedPrev(expectedPrev);
		
		d.dijkstra();

		testGraphBuiltSuccessfully(inputGraph, adjList);
		testGraphSize(inputGraph, 8);
		testGraphEdges(inputGraph, 17);
		
	}
	
	/**
	 * Build an adjacency list
	 * @return List of List of List of Integers representing an adjacency list representation of the graph
	 */
	private static List<List<List<Integer>>> buildAdjacencyList() {
		
		List<List<List<Integer>>> adjList = new ArrayList<List<List<Integer>>>();
		
		List<List<Integer>> edges0 = new ArrayList<List<Integer>>();
		addEdge(edges0, 1, 8);
		addEdge(edges0, 5, 10);
		
		List<List<Integer>> edges1 = new ArrayList<List<Integer>>();
		addEdge(edges1, 2, 4);
		addEdge(edges1, 4, 10);
		
		List<List<Integer>> edges2 = new ArrayList<List<Integer>>();
		addEdge(edges2, 3, 3);
		
		List<List<Integer>> edges3 = new ArrayList<List<Integer>>();
		addEdge(edges3, 4, 25);
		addEdge(edges3, 5, 18);
		
		List<List<Integer>> edges4 = new ArrayList<List<Integer>>();
		addEdge(edges4, 3, 9);
		addEdge(edges4, 6, 7);
		
		List<List<Integer>> edges5 = new ArrayList<List<Integer>>();
		addEdge(edges5, 0, 5);
		addEdge(edges5, 1, 7);
		addEdge(edges5, 2, 3);
		addEdge(edges5, 4, 2);
		
		List<List<Integer>> edges6 = new ArrayList<List<Integer>>();
		addEdge(edges6, 3, 2);
		addEdge(edges6, 7, 3);
		
		List<List<Integer>> edges7 = new ArrayList<List<Integer>>();
		addEdge(edges7, 0, 4);
		addEdge(edges7, 1, 9);
			
		adjList.add(edges0);
		adjList.add(edges1);
		adjList.add(edges2);
		adjList.add(edges3);
		adjList.add(edges4);
		adjList.add(edges5);
		adjList.add(edges6);
		adjList.add(edges7);
		
		return adjList;
	}

	/**
	 * Take an list of lists of integers, and two integers, and make those two integers into a list and add that list to the given list.
	 * @param edges List of List of Integers representing edges
	 * @param a first integer
	 * @param b second integer
	 */
	private static void addEdge(List<List<Integer>> edges, int a, int b) {
		List<Integer> innerList = listify(a, b);
		edges.add(innerList);
	}
	
	/**
	 * Take two ints and return an List containing them.
	 * @param a first integer
	 * @param b second integer
	 * @return List containing the 2 integers
	 */
	private static List<Integer> listify(int a, int b) {
		return Arrays.asList(a,b);
	}
	
	/**
	 * Build an arraylist of int[] representing the dist array after each iteration of Dijkstra's algorithm
	 * Add the expected dist array after each iteration of while loop at line 12 in Pseudocode specified by - https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm to an array list
	 * @return List of int[] representing dist array contents after each iteration
	 */
	private static List<int[]> buildExpectedDist() {
		List<int[]> expectedDist = new ArrayList<int[]>();
		
		expectedDist.add(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2,  Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 3});
		expectedDist.add(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2,  27, 20, 0, 3});
		expectedDist.add(new int[]{7, 12, Integer.MAX_VALUE, 2,  27, 20, 0, 3});
		expectedDist.add(new int[]{7, 12, Integer.MAX_VALUE, 2,  27, 17, 0, 3});
		expectedDist.add(new int[]{7, 12, 16, 2,  22, 17, 0, 3});
		expectedDist.add(new int[]{7, 12, 16, 2,  22, 17, 0, 3});
		expectedDist.add(new int[]{7, 12, 16, 2,  19, 17, 0, 3});
		expectedDist.add(new int[]{7, 12, 16, 2,  19, 17, 0, 3});
		expectedDist.add(new int[]{7, 12, 16, 2,  19, 17, 0, 3});
		
		return expectedDist;
	}
	
	/**
	 * Build an arraylist of int[] representing the prev array after each iteration of Dijkstra's algorithm
	 * Add the expected prev array after each iteration of while loop at line 12 in Pseudocode specified by - https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm to an array list
	 * @return List of int[] representing prev array contents after each iteration
	 */
	private static List<int[]> buildExpectedPrev() {
		List<int[]> expectedPrevArr = new ArrayList<int[]>();
		
		expectedPrevArr.add(new int[]{-1, -1, -1, 6, -1, -1, -1, 6});
		expectedPrevArr.add(new int[]{-1, -1, -1, 6, 3, 3, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, -1, 6, 3, 3, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, -1, 6, 3, 0, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, 1, 6, 1, 0, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, 1, 6, 1, 0, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, 1, 6, 5, 0, -1, 6});
		expectedPrevArr.add(new int[]{7, 7, 1, 6, 5, 0, -1, 6});
		
		return expectedPrevArr;
	}
	
	/**
	 * Test if the graph has been built successfully from the input adjacency list. If the graph has been built correctly, we should be able to create an adjacency list by traversing the graph and compare it with the input adjacency list, 
	 * @param graph input graph to the Dijkstra algorithm
	 * @param expected Adjacency list expected 
	 * @throws Exception throw if the input graph has not been built correctly from the adjacency list
	 */
	private static void testGraphBuiltSuccessfully(Graph graph, List<List<List<Integer>>> expected) throws Exception {
		
		List<List<List<Integer>>> actual = new ArrayList<List<List<Integer>>>();
		
		for(Map.Entry<Node, List<Edge>> entry: graph.graph.entrySet() ){
			
			List<List<Integer>> edgesList = new ArrayList<List<Integer>>();

			List<Edge> edgeList = entry.getValue();

			for(Edge edge : edgeList){
				
				int v = edge.getDestination().getIdentifier();
				int weight = edge.getWeight();
				
				List<Integer> eList = new ArrayList<Integer>();
				
				eList.add(v);
				eList.add(weight);
				
				edgesList.add(eList);
			}
			
			actual.add(edgesList);

		}
		
		testArrayListsEqual(actual, expected);
	}

	/**
	 * Compares the List specified by actual and expected are equal
	 * @param actual List<List<List<Integer>>> Adjacency list built from inputGraph 
	 * @param expected List<List<List<Integer>>> Original input adjacency list
	 * @throws Exception if the actual and expected list differ in size and/or content
	 */
	private static void testArrayListsEqual(List<List<List<Integer>>> actual, List<List<List<Integer>>> expected) throws Exception{
		
		if(!actual.equals(expected))
			throw new Exception("Actual graph and input adjacency list differ. Graph not built correctly. Please check code implementation");

		if(actual.size() != expected.size())
			throw new Exception("Actual graph and input adjacency list differ. Graph not built correctly. Please check code implementation");

		// Iterate through both actual and expected lists and throw exception if they differ.
		for(int i = 0, j = 0; i < actual.size() && j < expected.size(); i++, j++){
			
			List<List<Integer>> actualEdges = actual.get(i);
			List<List<Integer>> expectedEdges = expected.get(j);
			
			if(actualEdges.size() != expectedEdges.size())
				throw new Exception("Actual graph and input adjacency list differ. Graph not built correctly. Please check code implementation");

			for(int k = 0, l = 0; k < actualEdges.size() && l < expectedEdges.size(); k++, l++){
				List<Integer> actualEdge = actualEdges.get(k);
				List<Integer> expectedEdge = expectedEdges.get(l);
				
				if(!actualEdge.equals(expectedEdge))
					throw new Exception("Actual graph and input adjacency list differ. Graph not built correctly. Please check code implementation");
			}
		}
	}
	
	/**
	 * Test the graph has the expected number of nodes/vertices
	 * @param inputGraph Graph input to Dijkstra's algorithm
	 * @param expectedNumOfNodes expected number of nodes in graph
	 * @throws Exception if the actual number of nodes and expected number of nodes differ
	 */
	private static void testGraphSize(Graph inputGraph, int expectedNumOfNodes) throws Exception {
		if(inputGraph.size != expectedNumOfNodes)
			throw new Exception("Actual number of nodes in graph : " + inputGraph.size + " Expected number of nodes " + expectedNumOfNodes + " differ. Check code implementation");
	}
	
	/**
	 * Test the graph has the expected number of edges
	 * @param inputGraph Graph input to Dijkstra's algorithm
	 * @param expectedNumOfEdges expected number of edges in graph
	 * @throws Exception if the actual number of edges and expected number of edges differ
	 */
	private static void testGraphEdges(Graph inputGraph, int expectedNumOfEdges) throws Exception {
		
		int totalEdges = 0;
		
		for(Map.Entry<Node, List<Edge>> entry : inputGraph.graph.entrySet()){
			totalEdges = totalEdges + entry.getValue().size();
		}
		
		if( totalEdges != expectedNumOfEdges)
			throw new Exception("Actual number of edges in graph : " + totalEdges + " Expected number of edges " + expectedNumOfEdges + " differ. Check code implementation");
	}
}
