/**
 * This class represents a logical sentence with Disjunction operator
 * @author anuvabanwasi
 *
 */
public class Disjunction extends LogicalSentence{

	LogicalSentence s1;
	LogicalSentence s2;
	
	/**
	 * Constructs a logical disjunction sentence
	 * @param s1 String representing first logical sentence
	 * @param s2 String representing first logical sentence
	 */
	Disjunction(LogicalSentence s1, LogicalSentence s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * Evaluates the conjunction logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, true if the disjunction of the two logical sentences is true for the given truth assignment
	 */
	
	@Override
	public Boolean evaluate(TruthAssignment h) {
		return s1.evaluate(h) || s2.evaluate(h);
	}
}
