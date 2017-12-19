package org.noogly.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Expression is a list of tokens. For example, the boolean expression dog or cat, is the list of tokens - ['dog', 'or', 'cat']
 * @author anuva banwasi
 *
 */
public class Expression {
	List<String> tokens;

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

	@Override
	public String toString() {
		return "Expression [tokens=" + tokens + "]";
	}
}
