/**
 * This class represents a logical sentence with a negation operator
 * @author anuvabanwasi
 *
 */
public class Negation extends LogicalSentence{

	LogicalSentence s;
	
	/**
	 * Constructs a logical negation sentence
	 * @param s LogicalSentence representing the sentence to negate
	 */
	Negation(LogicalSentence s) {
		this.s = s;
	}
	
	/**
	 * Evaluates the logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, true if the logical sentence is true for the given truth assignment
	 */
	@Override
	public Boolean evaluate(TruthAssignment h) {
		return !s.evaluate(h);
	}
}
