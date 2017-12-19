package org.noogly.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.noogly.model.DocumentIndex;
import org.noogly.model.Expression;
import org.noogly.model.Index;

import com.google.gson.Gson;

/**
 * Evaluate a boolean query expression
 * @author  anuva banwasi
 *
 */
public class Query {

	Map<Integer, String> docIndex = new HashMap<Integer, String>();
	Map<String, Set<Integer>> index = new HashMap<String, Set<Integer>>();

	public Query() {

		this.docIndex = getDocIndex();
		this.index = getInvertedIndex();
	}

	/**
	 * Method to evaluate input boolean expression 
	 * @param expression Boolean expression containing tokens of operands and operators. For example, the boolean expression, dog and cat has 2 operands - dog, cat and an operator - and 
	 * @return List Documents that match the boolean query
	 * @throws Exception
	 */
	public List<String> evaluateExpression(Expression expression) throws Exception {

		// Obtain list of doc ids
		List<Integer> docIds = evaluate(expression);
		
		List<String> results = new ArrayList<String>();
		
		if (docIds != null && docIds.size() > 0) {
			Set<Integer> uniqueDocIds = new HashSet<Integer>(docIds);

			// Translate docId to actual document
			for (Integer docId : uniqueDocIds) {

				results.add(docIndex.get(docId));

			}
		}
		return results;
	}

	/**
	 * Evaluate the given boolean expression. An expression comprises of tokens made of operands(terms) and operators(and,or,not). Precedence of operators is as follows
	 * not > and > or. It is possible to explicitly specify precedence of operators using parenthesis
	 * @param expression boolean expression to evaluate
	 * @return list of document ids representing documents that match the boolean search query
	 * @throws Exception
	 * Acknowledgement - http://www.geeksforgeeks.org/expression-evaluation/
	 */
	public List<Integer> evaluate(Expression expression) throws Exception {
		// Get tokens of an expression
		List<String> tokens = expression.getTokens();

		// Create a stack for operands : 'terms'
		Stack<List<Integer>> terms = new Stack<List<Integer>>();

		// Create a stack for operators: 'ops'
		Stack<String> ops = new Stack<String>();

		List<String> operators = new ArrayList<String>();
		operators.add("or");
		operators.add("and");
		operators.add("not");
		operators.add("leftP");
		operators.add("rightP");

		for (String token : tokens) {
			
			if (token.length() > 0) {
				// if the token is not an operator, push it to the terms stack
				if (!operators.contains(token)) {
					if (ops.size() > 0 && ops.peek().equalsIgnoreCase("not")) {
						ops.pop();
						terms.push(getAllDocIdsButNot(token));
					} else
						terms.push(getDocIds(token));
				}

				// if the token is a left parenthesis, push it to the operators stack
				else if (token.equalsIgnoreCase("leftP"))
					ops.push(token);

				// if the token is a right parenthesis, pop twice from the terms stack and once from the operator stack 
				// apply the operator to the terms and push the results back to the terms stack
				else if (token.equalsIgnoreCase("rightP")) {
					while (!ops.peek().equalsIgnoreCase("leftP") )
						terms.push(applyOperator(ops.pop(), terms.pop(), terms.pop()));
					ops.pop();
				}

				// if the token is an operator
				else if (token.equalsIgnoreCase("or") || token.equalsIgnoreCase("and")) {
					// while the precendence of operator on top of operators stack >= current operator 
					// pop twice from the terms stack and once from the operator stack 
					// apply the operator to the terms and push the results back to the terms stack
					while (!ops.empty() && terms.size() >= 2 && hasPrecedence(token, ops.peek())) {
						List<Integer> docIds = applyOperator(ops.pop(), terms.pop(), terms.pop());
						terms.push(docIds);
					}

					// Add the current token to the operators stack
					ops.push(token);
				} else if (token.equalsIgnoreCase("not")) {
					ops.push(token);
				}
			}
		}

		// while there are operators remaining in on the operator stack,
		// pop twice from the terms stack and once from the operator stack 
		// apply the operator to the terms and push the results back to the terms stack
		while (!ops.empty() && terms.size() >= 2)
			terms.push(applyOperator(ops.pop(), terms.pop(), terms.pop()));

		
		System.out.println("\n\n\nthe ops size " + ops.size());
		System.out.println("\n\n\nthe terms size " + terms.size());

		if (terms.size() != (ops.size() + 1))
			throw new Exception("malformed expression");
		
		// top of the terms stack is the list of document ids satisfying the booolean expression
		return terms.pop();
	}

	/**
	 * Apply the operator specified by operator to the operands first and second
	 * @param operator operator like or or and
	 * @param first list of document ids
	 * @param second list of document ids
	 * @return list of document ids obtained by applying operator to the lists first and second
	 */
	public List<Integer> applyOperator(String operator, List<Integer> first, List<Integer> second) {
		
		System.out.println("op is " + operator);
		System.out.println("first " + first);
		System.out.println("second " + second);
	
		List<Integer> docIds = new ArrayList<Integer>();
		switch (operator) {
		case "or":
			// if the operator is an or, the result is a union of the two lists of document ids
			if (first != null && second != null) {
				union(first, second);
				return first;
			} else if (first == null)
				return second;
			else if (second == null)
				return first;

		case "and":
			// if the operator is an and, the result is an intersection of the two lists of document ids
			if(first != null && second != null)
				return intersect(first, second);

		}

		return docIds;
	}

	/**
	 * Method to compute the intersection of 2 posting lists. 
	 * @param l1 List representing posting list for a term
	 * @param l2 List representing posting list for a term
	 * @return List representing the posting list that contains the document ids that are present in both lists
	 */
	private List<Integer> intersect(List<Integer> l1, List<Integer> l2) {
		int i = 0;
		int j = 0;

		List<Integer> results = new ArrayList<Integer>();

		while (i < l1.size() && j < l2.size()) {
			if (l1.get(i) == l2.get(j)) {
				results.add(l1.get(i));
				i++;
				j++;
			} else if (l1.get(i) < l2.get(j)) {
				i++;
			} else if (l1.get(i) > l2.get(j)) {
				j++;
			}
		}

		return results;
	}
	
	/**
	 * Method to union 2 lists. Adds all documents of second list to first list
	 * @param first 
	 * @param second
	 */
	private void union(List<Integer> first, List<Integer> second) {
		first.addAll(second);
	}

	
	/**
	 * Checks precedence of 2 operators, returns true if operator2 has higher precedence than operator1
	 * @param operator1 String representing first operator like 'or' or 'and'
	 * @param operator2 String representing first operator like 'or' or 'and'
	 * @return
	 */
	public static boolean hasPrecedence(String operator1, String operator2) {
		if (operator2.equalsIgnoreCase("leftP") || operator2.equalsIgnoreCase("rightP"))
			return false;
		if (operator1.equalsIgnoreCase("and") && operator2.equalsIgnoreCase("or"))
			return false;
		else
			return true;
	}

	/**
	 * Method to compute the documents that do not contain a given search term
	 * @param term Search term to exclude from results
	 * @return list of document ids that do not contain the term
	 */
	private List<Integer> getAllDocIdsButNot(String term) {

		List<Integer> allDocIds = getAllDocIds();

		List<Integer> docIds = getDocIds(term);

		Set<Integer> allDocIdsSet = new HashSet<Integer>(allDocIds);

		if (docIds != null && docIds.size() > 0) {
			Set<Integer> docIdsSet = new HashSet<Integer>(docIds);
			allDocIdsSet.removeAll(docIdsSet);

			return new ArrayList<Integer>(allDocIdsSet);
		} else
			return new ArrayList<Integer>(allDocIdsSet);
		}

	/**
	 * Returns the list of document ids that contain a given term or word
	 * @param string term 
	 */
	public List<Integer> getDocIds(String term) {

		if (index.get(term) == null) {
			System.out.println("\nNo documents contain the search term: " + '\"' + term + '\"');
			return null;
		}

		Set<Integer> results = new TreeSet<Integer>();
		System.out.println("\nThe following documents contain the search term-> " + '\"' + term + '\"');
		results = index.get(term);
		
		return new ArrayList<Integer>(results);

	}

	/**
	 * Returns list of all document ids in the index
	 * @return List representing all the document ids in the index
	 */
	private List<Integer> getAllDocIds() {

		Set<Integer> docIds = new HashSet<Integer>();

		for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
			
			docIds.addAll(entry.getValue());

		}
		return new ArrayList<Integer>(docIds);
	}
	
	/**
	 * Method to read inverted index from disk and create a copy of index in memory
	 * @param path Full path to inverted index on disk
	 * @return Map representing the inverted index. Key is the term and value is a list of all the document ids where the term is found
	 */
	public Map<String, Set<Integer>> getInvertedIndex() {
		
		String path = "index2.txt";
		
		Gson gson = new Gson();

		String jsonStr = readFile(path);
		Index ind = gson.fromJson(jsonStr, Index.class);

		Map<String, Set<Integer>> index = ind.getIndex();
		return index;
	}

	/**
	 * Method to get the document index from disk and represent it as a map
	 * @return Map representing document index. key is document id and value is the actual document name
	 */
	public Map<Integer, String> getDocIndex() {
		 
		 
		String path = "docindex2.txt";

		Gson gson = new Gson();

		String jsonStr = readFile(path);
		DocumentIndex doc = gson.fromJson(jsonStr, DocumentIndex.class);

		Map<Integer, String> docIndex = doc.getDocIndex();
		return docIndex;
	}

	/**
	 * Acknowledgement:
	 * https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
	 * 
	 * @param path Full path to the file
	 * @return String representation of the file
	 */

	private String readFile(String path) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();

		try {
			
			InputStream is = Query.class.getClassLoader().getResourceAsStream(path);
			
			br = new BufferedReader(new InputStreamReader(is));
			
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}
}
