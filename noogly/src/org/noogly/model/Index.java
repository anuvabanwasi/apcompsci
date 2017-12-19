package org.noogly.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represent an index as a map. Key is the term, Value is the list of document ids that contain the term
 * @author anuva banwasi
 *
 */
public class Index {
	Map<String, Set<Integer>> index = new HashMap<String, Set<Integer>>();

	public Map<String, Set<Integer>> getIndex() {
		return index;
	}

	public void setIndex(Map<String, Set<Integer>> index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Index [index=" + index + "]";
	}
}
