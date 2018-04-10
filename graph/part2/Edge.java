
/**
 * Represents a edge of the graph. An edge of the graph connects a source node to a destination node and has a cost or weight.
 * An edge is represented by a triplet (S, D, W)
 * S - represents the source node
 * D - represents the destination node
 * W - represents the cost of the edge from source node to destination node
 * @author Anuva
 *
 */
public class Edge implements Comparable{
	
	private Node source;									// Source node of the edge
	private Node destination;							// Destination node of the edge
	private int weight;									// cost/weight of the edge

	Edge(Node source, Node destination, int weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}
	
	@Override
    public int compareTo(Object o) {
        Edge e = (Edge) o;
        return getSource().identifier - e.getSource().identifier;
    }

	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", source=" + source + ", destination=" + destination + "]";
	}
}
