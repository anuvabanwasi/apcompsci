/**
 * input: String s, a proposed propositional logic sentence
 * output: Boolean, true when sentence is legal
 * effect: no effect/change
 * @author anuvabanwasi
 *
 */

public class LegalSentence {
	public static void main(String[] args) {
		System.out.println(legalSentence("f|(a&(c|d))")); 
		//System.out.println(legalSentence("a|(b)")); 
	}
	
	/**
	 * Check if the sentence represented by s is a legal sentence
	 * @param s String to check
	 * @return boolean, true if the string s is legal
	 */
	public static Boolean legalSentence(String s) {
		// base case 1: empty string
		if(s.equals("")) 
			return false;
		// base case 2: simple sentence
		else if(simpleSentence(s)) 
			return true; 
		
		// if string contains a '('
		else if(s.contains("(")){
			String fm = LogicClient.findMatch(s, 0);
			if(fm.equalsIgnoreCase("error"))
				return false;
			else
				return legalSentence(fm);
		}
		// recursive case 1: negation
		else if(s.substring(0, 1).equals("~")) 
			return legalSentenceNot(s);
		// recursive case 2: sentence LO sentence
		else if(s.contains("&"))
			return legalSentenceAnd(s);
		// recursive case 3: sentence LO sentence 
		else if(s.contains("|"))
			return legalSentenceOr(s);
		else if(s.contains("=>"))
			return legalSentenceImplies(s);
		else if (s.contains("<=>"))
			return legalSentenceBiconditional(s);
		return false;
	}
	
	/**
	 * Checks whether a sentence with negation operator is legal 
	 * @param s String representing sentence
	 * @return boolean, true if sentence is logical, false if not
	 */
	
	private static Boolean legalSentenceNot(String s) {
		if(legalSentence(s.substring(1, s.length()))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether a simple sentence is legal
	 * @param s String to check
	 * @return boolean, true if sentence is simple sentence, false if not
	 */
	private static Boolean simpleSentence(String s) {
		if(s.length() == 1) 
			return true; 
		return false;
	}
	
	/**
	 * Checks whether a sentence with conjunction(and) operator is legal 
	 * @param s
	 * @return boolean, true if sentence is logical, false if not
	 */
	private static Boolean legalSentenceAnd(String s) {
		String a = s.substring(0, s.indexOf("&"));
		String b = s.substring(s.indexOf("&") + 1, s.length());
		if(legalSentence(a) && legalSentence(b))
			return true;
		return false;
	}
	
	/**
	 * Checks whether a sentence with disjunction(or) operator is legal 
	 * @param s
	 * @return boolean, true if sentence is logical, false if not
	 */
	private static Boolean legalSentenceOr(String s) {
		String a = s.substring(0, s.indexOf("|"));
		String b = s.substring(s.indexOf("|") + 1, s.length());
		
		if(legalSentence(a) && legalSentence(b))
			return true;
		return false;
	}
	
	/**
	 * Checks whether a sentence with implies operator is legal 
	 * @param s
	 * @return boolean, true if sentence is logical, false if not
	 */
	private static Boolean legalSentenceImplies(String s) {
		String a = s.substring(0, s.indexOf("="));
		String b = s.substring(s.indexOf(">") + 1, s.length());
		if(legalSentence(a) && legalSentence(b))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param s
	 * @return boolean, true if sentence is logical, false if not
	 */
	private static Boolean legalSentenceBiconditional(String s) {
		String a = s.substring(0, s.indexOf("<"));
		String b = s.substring(s.indexOf(">") + 1, s.length());
		if(legalSentence(a) && legalSentence(b))
			return true;
		return false;
	}
}
