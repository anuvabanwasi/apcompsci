
import java.util.ArrayList;
import java.util.List;

/**
 * Client program to test logical sentence puzzle
 * @author anuvabanwasi
 *
 */
public class LogicClient {
	public static void main(String[] args) {
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");

		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);

		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);

		LogicalSentence l5 = new Conjunction(l3, new Negation(l4));
		
		LogicalSentence l6 =  new Disjunction(l1, l2);
		
		LogicalSentence l7 =  new Implication(l1, l2);
		
		LogicalSentence l8 =  new BiConditional(l1, l2);
		
		TruthAssignment ta = new TruthAssignment();
		ta.put(a, false);
		ta.put(b, true);

		System.out.println("Disjunction " + l6.evaluate(ta));
		
		System.out.println("Implication " + l7.evaluate(ta));
		
		System.out.println("BiConditional " + l8.evaluate(ta));

		System.out.println("Evaluate: "  + l5.evaluate(ta));

		System.out.println("Legal: " + legal("a&(b|c)"));
		
		System.out.println("Find Match: " + findMatch("(a&(b&c))|(c&d)", 0));
		
		System.out.println("Truth Table");
		truthTable(new String[] { "p", "q" });
	}

	/**
	 * Finds the string embedded in matching parenthesis, the string returned is without nested parenthesis
	 * For example, in the example, findMatch("(b|c)", 0) returns b|c
	 * @param str String containing logical expression with nested parenthesis
	 * @param i integer depth incremented each time ( is encountered. Helps check that the number of '(' is equal to the number of ')' 
	 * @return String representing input string without parenthesis
	 */
	public static String findMatch(String str, int i) {
		//System.out.println("Str : " + str + " i: " + i);

		if(str.length() == 0) {
			// Number of '(' is equal to the number of ')'
			if(i == 0)
				return str;
			else
				return "error";
		}
		// Each time ( is encountered, increment i - the number of '(' seen
		else if (str.charAt(0) == '(')
			return findMatch(str.substring(1), ++i);
		
		// Each time ) is encountered, decrement i - the number of '(' seen
		else if (str.charAt(0) == ')')
			return findMatch(str.substring(1), --i);
		else
			
		// Concatenate the characters which are not '(' or ')'
			return str.charAt(0) + findMatch(str.substring(1), i);
	} 
	
	/**
	 * Generates truth table for specified propositional constants
	 * @param pc String array representing propositional constants
	 */
	public static void truthTable(String[] pc) {
		List<String> pcList = new ArrayList<String>();
		
		// Add propositional constants to list. For example, if pc = {"p"}, add "p" to the list
		for (int i = 0; i < pc.length; i++) {
			pcList.add(pc[i].trim());
		}

		// Add negated propositional constants to list. For example, if pc = {"p"}, add "~p" to the list
		for (int i = 0; i < pc.length; i++) {
			pcList.add("~" + pc[i].trim());
		}

		int n = pc.length;
		// For n proposition constants, there are 2^n rows in the truth table. Each row represent a number from 0 .. 2^n
		for (int i = 0; i < Math.pow(2,n); i++) {
			
			// Use Java library to convert index of row to a binary string
			String binary = Integer.toBinaryString(i);

			// if the length of the binary string is less than n, pad with zeros since truth table has n columns
			binary = padWithZero(pc, binary);

			String ta = new String();
			
			// For each character in the generated binary string, check if the character is '0' or '1'
			for (int j = 0; j < binary.length(); j++) {
				
				// if the character is '0', replace it with the negated propositional constant. For example, in the string "0", replace "0" with ~p
				if (binary.charAt(j) == '0') {
					ta = ta + " " + pcList.get(j + pc.length);
				} 
				// if the character is '1', replace it with the propositional constant. For example, in the string "1", replace "1" with p
				else if (binary.charAt(j) == '1') {
					ta = ta + "  " + pcList.get(j);
				}
			}
			System.out.println(ta.toString());
		}
	}

	/**
	 * Pads a string with zero's in the beginning till string is of desired length
	 * @param pc String array representing propositional constants
	 * @param s String representing padded string
	 * @return
	 */
	private static String padWithZero(String[] pc, String s) {
		while (s.length() != pc.length) {
			s = '0' + s;
		}
		return s;
	}

	/**
	 * Checks whether an expression is legal 
	 * @param expression String representation of logical expression
	 * @return boolean, true if the sentence is legal, false if not
	 */
	private static boolean legal(String expression) {
		return LegalSentence.legalSentence(expression);
	}
}
