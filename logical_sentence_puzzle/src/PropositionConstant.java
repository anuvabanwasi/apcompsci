/**
 * Represents a Proposition Constant
 * @author anuvabanwasi
 *
 */
public class PropositionConstant{
	
	String constant;
	
	/**
	 * Constructs a proposition constant
	 * @param constant String representing propositional constant
	 */
	PropositionConstant(String constant){
		this.constant = constant;
	}

	/**
	 * Evaluate the proposition constant for a truth assignment
	 * @param h represents a truth assignment
	 * @return
	 */
	public Boolean evaluate(TruthAssignment h) {
		return h.get(this);
	}

	@Override
	public String toString() {
		return "PropositionalConstant [constant=" + constant + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constant == null) ? 0 : constant.hashCode());
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
		PropositionConstant other = (PropositionConstant) obj;
		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.equals(other.constant))
			return false;
		return true;
	}
}
