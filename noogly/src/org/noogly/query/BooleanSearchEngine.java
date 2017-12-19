package org.noogly.query;

import java.util.ArrayList;
import java.util.List;

import org.noogly.model.Expression;

/**
 * Boolean Search Engine - takes in an expression and returns the list of document ids which satisfy the boolean expression
 * @author anuva banwasi
 *
 */
public class BooleanSearchEngine {

	/**
	 * Search for the document ids that satisfy the given expression
	 * @param expression boolean expression like dog and cat
	 * @return list of document ids that satisfy the given expression
	 */
	public List<String> search(Expression expression) {
		System.out.println("received request " + expression);
       
		List<String> results = new ArrayList<String>();
		
		if(expression != null && expression.getTokens() != null && expression.getTokens().size() > 0) {
	        
			// Create a query object
			Query q = new Query();
			
			try {
				// Evaluate the expression, the result is the list of document ids that satisfy the boolean expression
				List<String> searchResults = q.evaluateExpression(expression);
				
				if(searchResults.size() > 0) {
					return searchResults;
				} else {
					
					results.add("No results found");
					return results;
				}
			} catch (Exception e) {	
				// If the expression is not well formed, throw an exception
				results.add("Please enter a well formed boolean search expression");
				return results;
			}
			
		} else {
			// If the expression is not well formed, throw an exception
			results.add("Please enter a well formed boolean search expression");
			return results;
			
		}
	}
}
