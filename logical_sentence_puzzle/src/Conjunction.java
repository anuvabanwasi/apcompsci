/**
 * This class represents a logical sentence with conjunction operator
 * @author anuvabanwasi
 *
 */
public class Conjunction extends LogicalSentence{

	LogicalSentence s1;
	LogicalSentence s2;
	
	/**
	 * Constructs a logical conjunction sentence
	 * @param s1 String representing first logical sentence
	 * @param s2 String representing first logical sentence
	 */
	Conjunction(LogicalSentence s1, LogicalSentence s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * Evaluates the conjunction logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, true if the conjunction of the two sentences is true for the given truth assignment
	 */
	
	@Override
	public Boolean evaluate(TruthAssignment h) {
		return s1.evaluate(h) && s2.evaluate(h);
	}
}
