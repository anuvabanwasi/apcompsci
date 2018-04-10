
/**
 * Represents a node of the graph
 * @author Anuva
 *
 */
public class Node {
	
	int identifier;							// unique identifier of the node
	
	Node(int identifier){
		this.identifier = identifier;
	}

	public int getIdentifier() {
		return identifier;
	}
	
	// Override equals() and hashCode() so that Node class can be used properly in HashMap representation of graph
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identifier;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (identifier != other.identifier)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [identifier=" + identifier +  "]";
	}
}
