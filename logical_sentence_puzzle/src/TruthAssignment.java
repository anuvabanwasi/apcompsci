import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a truth assignment
 * @author anuvabanwasi
 *
 */
public class TruthAssignment {
	
	// Map representing a truth assignment
	private Map<PropositionConstant, Boolean> map =  new HashMap<PropositionConstant, Boolean>();     
	
	TruthAssignment(){
	}
	
	/**
	 * Associates the specified truth value with the specified key (proposition constant) in this map 
	 * @param constant Represents PropositionalConstant
	 * @param value boolean true if the PropositionConstant is set to true, false otherwise
	 */
	void put(PropositionConstant constant, boolean value){
		map.put(constant, new Boolean(value));
	}
	
	/**
	 * Returns the truth value to which the specified key (proposition constant) is mapped
	 * @param constant
	 * @return
	 */
	Boolean get(PropositionConstant constant){
		return map.get(constant);
	}

	@Override
	public String toString() {
		return "TruthAssignment [map=" + map + "]";
	}
}
