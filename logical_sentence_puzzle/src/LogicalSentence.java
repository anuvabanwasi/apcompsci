/**
 * This class represents a logical sentence
 * @author anuvabanwasi
 *
 */
public class LogicalSentence {
	PropositionConstant s;
	
	/**
	 * Constructs a logical sentence
	 */
	LogicalSentence(){
	}
	
	/**
	 * Constructs a logical sentence using the propositional constant
	 * @param s String represents the propositional constant
	 */
	LogicalSentence(PropositionConstant s){
		this.s = s;
	}

	/**
	 * Evaluates the logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, true if the logical sentence satisfies the truth assignment
	 */
	public Boolean evaluate(TruthAssignment h) {
		return s.evaluate(h);
	}
}

