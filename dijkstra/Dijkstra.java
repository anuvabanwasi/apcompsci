

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Graph;
import Edge;

/**
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph
 * 
 * The below algorithm designates a single node as the "source" node and finds shortest paths from the source to all other nodes in the graph
 * 
 * Algorithm from Wikipedia is as follows
 * 
 * 1. Initially all nodes are marked as unvisited. Create a set called unvisited set of all the unvisited nodes. Represented by variable Q in the code below.
 * 2. A distance value is assigned to every node : zero for the source node and infinity for all other nodes. 
 * 3. Determine the node in unvisited set with the smallest distance to source. Initially, this will be the source node since the distance of source node to itself is 0
 * 4. For the above selected node, consider all of its unvisited neighbors and calculate their tentative distances through the current node. 
 *    Compare the newly calculated tentative distance to the current assigned value and assign the smaller one. 
 * 5. Set the current node as visited and remove it from the unvisited set. 
 * 6. Move to the next unvisited node with the smallest tentative distances and repeat the above steps which checks neighbors and marks visited.
 * 7. If the destination node has been marked visited the algorithm has finished.
 * 8. Otherwise, select the unvisited node with the smallest tentative distance, set it as the new "current node", and go back to step 3.
 *
 * References - 
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 * 
 * @author Anuva
 *
 */
public class Dijkstra {
	
	private Graph inputGraph;
	private Node inputNode;
	
	private int[] dist;											// keeps track of the distances of inputNode node to each of the other nodes in the graph. dist[i] is the value of the shortest distance from inputNode node to node i
	private int[] prev;											// keeps track of the predecessor in the path from inputNode node to each of the other nodes in the graph
	boolean[] visited;									// keeps track of all nodes visited by the algorithm
	private List<Integer> Q = new ArrayList<Integer>();			// Create a set of all the unvisited nodes

	private List<int[]> expectedDist;					// used for testing
	private List<int[]> expectedPrev;					// used for testing
	
	private int size;
	
	/**
	 * Constructs a Dijkstra object using Graph inputGraph and source Node inputNode
	 * @param inputGraph A Graph represented using adjacency list representation 
	 * @param inputNode Node - calculate the shortest distance from source node to all other nodes 
	 * @throws Exception 
	 */
	Dijkstra(Graph inputGraph, Node inputNode){
		this.inputGraph = inputGraph;
		this.inputNode = inputNode;
		
		size = inputGraph.size;

		dist = new int[size];
		prev = new int[size];
		visited = new boolean[size];
	}
	
	/**
	 * Implements Dijkstra's shortest path algorithm to calculate the shortest distances from source to all nodes of a Graph  
	 * @throws Exception 
	 */
	public void dijkstra() throws Exception {
		int cnt = 0;
		
		// set all nodes as unvisited and set distance to MAX_VALUE (large value signifying infinity)
		initialize();

		// add all nodes to the unvisited set
		addToUnvisitedSet();

		// distance of inputNode node to itself is 0
		dist[inputNode.getIdentifier()] = 0;

		// while there are nodes that have not yet been visited
		while (!Q.isEmpty()) {

			// determine the node in Q with the smallest distance to inputNode
			int u = closestVertex(dist, visited);
			
			// mark the node selected u as visited, remove it from list of unvisited nodes
			Q.remove(new Integer(u));
			visited[u] = true;						
			
			// update the distance of every unvisited node that is directly connected to u
			evaluateDistanceToNeighbors(u);
			
			if(!testArraysEqual(dist, expectedDist.get(cnt)))
				throw new Exception("Expected and Actual dist arrays differ. Check Dijkstra's algorithm");
			
			if(!testArraysEqual(prev, expectedPrev.get(cnt)))
				throw new Exception("Expected and Actual prev arrays differ. Check Dijkstra's algorithm");
			
			
			cnt++;
		}
		System.out.println("Shortest path from Node " + inputNode.getIdentifier() + " to all other nodes has been computed " );
		getPreviousList(prev, 0);
	}

	/**
	 * Add all nodes to the unvisited set
	 */
	private void addToUnvisitedSet() {
		for (int i = 0; i < size; i++)
			Q.add(i);
	}

	/**
	 * Initialize dist, prev and visited
	 * Set all nodes as unvisited and set distance to MAX_VALUE (large value signifying infinity)
	 * Set predecessor as -1
	 */
	private void initialize() {
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			prev[i] = -1;
			visited[i] = false;
		}
	}

	/**
	 * Update the distance of every unvisited node that is directly connected to node u
	 * @param u identifier of node
	 */
	private void evaluateDistanceToNeighbors(int u) {
		for (Edge e: getEdges(u)) {						 
			
			Node v = e.getDestination();
			int weight = e.getWeight();

			int alt = dist[u] + weight;						 					  // for every node v, adjacent to u, if there is an edge from u to v, where v is not visited and
			if (!visited[v.getIdentifier()] && alt < dist[v.getIdentifier()]) {    // total weight of path from inputNode to v through u is smaller than current value of dist[v], 
				dist[v.getIdentifier()] = alt;									 // update the value of dist[v] with the sum of the current total distance to u and the weight of the edge (u,v)
				prev[v.getIdentifier()] = u;
			}
		}
	}

	/**
	 * Get edges of node with id u
	 * @param u id of node
	 * @return List of Edges representing neighbors of node with identifier u
	 */
	private List<Edge> getEdges(int u) {
		return inputGraph.graph.get(new Node(u));
	}

	/**
	 * Calculate the unvisited node with minimum distance from inputNode
	 * @param dist array of integer to track the distance of inputNode to each node of the graph. dist[i] is the value of the shortest distance from inputNode node to node i
	 * @param visited array of boolean to track the nodes visited by the algorithm 
	 * @return the unvisited node with smallest distance from inputNode
	 */
	private int closestVertex(int[] dist, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		// iterate through all the nodes and return the index with the smallest value in dist 
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] < min && !visited[i]) { 	 	
				min = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	/**
	 * Test if actual and expected arrays are equal
	 * @param actual integer array representing array generated by program
	 * @param expected integer array representing expected array
	 * @return true if the arrays are equal
	 */
	private boolean testArraysEqual(int[] actual, int[] expected) {
		return Arrays.equals(actual, expected);
	}
	
	/**
	 * Print the nodes on the path from from src to dest
	 * @return List of nodes which represent a path from node src to node dest
	 */
	public List<Integer> getPreviousList(int[] prev, int dest) {
		List<Integer> path = new ArrayList<Integer>();
		int src = dest;
		
		while(prev[src] != -1){
			path.add(0, src);
			src = prev[src];
		}
		path.add(0,src);

		System.out.println("Shortest path from Node "  + src + " to Node " + dest + " is below");
		printPath(path);
		return path;
	}
	
	/**
	 * Print path 
	 * @param path
	 */
	private void printPath(List<Integer> path){
		for(int i = 0; i < path.size(); i++){
			if(i != path.size()-1)
				System.out.print(path.get(i) + " -> " );
			else
				System.out.print(path.get(i));
		}
	}
	
	/**
	 * Print contents of an integer array
	 * @param arr integer array
	 */
	private void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	/**
	 * Print contents of a boolean array
	 * @param arr boolean array
	 */
	private void printArrBoolean(boolean[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	public void setExpectedDist(List<int[]> expectedDist) {
		this.expectedDist = expectedDist;
	}
	
	public void setExpectedPrev(List<int[]> expectedPrev) {
		this.expectedPrev = expectedPrev;
		
	}
}
