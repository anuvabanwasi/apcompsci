/**
 * This class represents a logical sentence with an implication operator
 * @author anuvabanwasi
 *
 */
public class Implication extends LogicalSentence{

	LogicalSentence s1;
	LogicalSentence s2;
	
	/**
	 * Constructs a logical implication sentence
	 * @param s1 String representing first logical sentence
	 * @param s2 String representing first logical sentence
	 */
	Implication(LogicalSentence s1, LogicalSentence s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * Evaluates the conjunction logical sentence for a truth assignment
	 * @param h TruthAssignment
	 * @return boolean, false if the first logical sentence is true and second logical sentence is false, for the given truth assignment, else return true
	 */
	
	@Override
	public Boolean evaluate(TruthAssignment h) {
		if(s1.evaluate(h) && !s2.evaluate(h))
			return false;
		else
			return true;
	}
}
