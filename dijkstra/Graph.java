

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Edge;

/**
 * A Weighted Graph built from an adjacency list representation of the graph. Adjacency
 * list representation for a graph associates each vertex in the graph with the
 * collection of its neighboring vertices or edges.
 * 
 * The adjacency list is represented by an array list indexed by vertex number, in which the array element for each vertex points to a singly
 * linked list of the neighboring edges of that vertex. 
 * 
 * For example - The adjacency list 
 * 
 * [ [(1,7), (2,9), (5,14)], [(0,7), (2,10), (3,15)], [(0,9), (1,10), (3,11), (5,2)], [(1,15), (2, 11), (4, 6)], [(3,6), (5, 9)], [(0,14), (2, 2), (4, 9)] ] 
 * 
 * identifies a graph where
 * 
 * 
 * 0 -> [(1,7), (2,9), (5,14)]         =>  Node 0 is adjacent to nodes 1, 2, 5 		with edge weights    7, 9, 14 respectively
 * 1 -> [(0,7), (2,10), (3,15)]        =>  Node 1 is adjacent to nodes 0, 2, 3 		with edge weights    7, 10, 15 respectively 
 * 2 -> [(0,9), (1,10), (3,11), (5,2)] =>  Node 2 is adjacent to nodes 0, 1, 3, 5 	with edge weights	 9, 10, 11, 2 respectively
 * 3 -> [(1,15), (2, 11), (4, 6)]      =>  Node 3 is adjacent to nodes 1, 2, 4  	with edge weights 	 15, 11, 6 respectively
 * 4 -> [(3,6), (5, 9)]				   =>  Node 4 is adjacent to nodes 3, 6 		with edge weights 	 6, 9 respectively
 * 5 -> [(0,14), (2, 2), (4, 9)]       =>  Node 5 is adjacent to nodes 0, 2, 4 		with edge weights 	 14, 2, 9 respectively

 * For undirected graphs, each of the two end point of an edge will have two different linked list nodes. 
 * If Node 0 is connected to Node 1, then there is an entry (0,1,7) in the list for node 0 and an entry (1,0,7) in the list for node 1

 * Reference - https://en.wikipedia.org/wiki/Adjacency_list
 * 
 * 
 * The adjacency list representation of the graph is converted into a HashMap. 
 * The  key of the map is the node of the graph and the value is the list of edges connected to that node. Each edge is represented by a triplet (s,d,w)
 * For example - the above adjacency list is converted in the below map
 * { Node(0) - [(0,1,7), (0,2,9), (0,5,14)]}     		Node 0 has 3 outgoing edges connecting it to Node 1, Node 2 and Node 5 with edge weights 7, 9, 14 respectively
 * { Node(1) - [(1,0,7), (1,2,9), (1,3,14)]}    		Node 1 has 3 outgoing edges connecting it to Node 0, Node 2 and Node 3 with edge weights 7, 10, 15 respectively
 * { Node(2) - [(2,0,9), (2,1,10), (2,3,11), (2,5,2)]}  Node 2 has 4 outgoing edges connecting it to Node 0, Node 1, Node 3 and Node 5 with edge weights 9, 10, 11 and 2 respectively
 * { Node(3) - [(3,1,7), (3,2,9), (3,5,14)]}     		Node 3 has 3 outgoing edges connecting it to Node 1, Node 2 and Node 4 with edge weights 15, 11, 6 respectively
 * { Node(4) - [(4,1,7), (4,2,9), (4,5,14)]}     		Node 4 has 2 outgoing edges connecting it to Node 3, Node 6 with edge weights 6, 9 respectively
 * { Node(5) - [(5,0,14), (5,2,2), (5,4,9)]}     		Node 5 has 3 outgoing edges connecting it to Node 0, Node 2 and Node 4 with edge weights 14, 2, 9 respectively
 * 
 * @author Anuva
 *
 */
public class Graph {
	
	// The number of nodes in the Graph
	int size;
		
	// Graph representation using hashmap
	Map<Node, List<Edge>> graph = new LinkedHashMap<Node, List<Edge>>();
	
	/**
	 * Builds a Graph from an adjacency list representation of the graph
	 * 
	 * @param adjList List of List of List of integers representing a graph as described above
	 */
	Graph(List<List<List<Integer>>> adjList) {

		int cnt = 0;												   // implicitly track source node

		// Iterate through the adjacency list. Each element of the adjacency list is a set of edges. 
		// Each edge connecting node and node v is represented is represented by 3 elements (src, dest,weight). 
		// The first is the identifier of the source node and the second is the identifier of the destination node and the third is the weight
		for (List<List<Integer>> edgeList : adjList) {
			
			Node source = new Node(cnt);							    // Create source node
			
			List<Edge> edges = new ArrayList<Edge>();				// Create empty list of edges associated with the source node
			
			for (List<Integer> edge : edgeList) {
				
				Node destination =  new Node(edge.get(0));			// Identify destination node 
				int weight = edge.get(1);							// Identify edge weight
				
				Edge e = new Edge(source, destination, weight);		// Create edge connecting source node to destination node with weight w
					
				edges.add(e);										// add edge e to the list of edges for source node 
			}
			
			graph.put(source, edges);								// store list of edges for source node in the graph
			cnt++; 
		}

		size = graph.size();										   // store the number of nodes in the graph
		
	}
	
	/**
	 * Print hashmap representation of the graph
	 */
	private void printHashMap(){
		for(Map.Entry<Node, List<Edge>> entry : graph.entrySet()){
			System.out.print("key " + entry.getKey().getIdentifier()  + " -> ");
			
			for(Edge e : entry.getValue())
				System.out.print ("(  " + e.getDestination().getIdentifier() + " , " + e.getWeight()  + " )");
			
			System.out.println();
		}
	}
}
