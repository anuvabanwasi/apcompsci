/**
 * This class represents a logical sentence with a bi-conditional operator
 * @author anuvabanwasi
 *
 */
public class BiConditional extends LogicalSentence{

	LogicalSentence s1;
	LogicalSentence s2;
	
	/**
	 * Constructs a logical biconditional sentence
	 * @param s1 String representing first logical sentence
	 * @param s2 String representing first logical sentence
	 */
	BiConditional(LogicalSentence s1, LogicalSentence s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * Evaluates the bi-conditional logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, true if both operand logical sentences are true or both operand logical sentences are false. For all other combinations returns false
	 */
	
	@Override
	public Boolean evaluate(TruthAssignment h) {
		if((s1.evaluate(h) && s2.evaluate(h)) || (!s1.evaluate(h) && !s2.evaluate(h)))
			return true;
		else
			return false;
	}
}
