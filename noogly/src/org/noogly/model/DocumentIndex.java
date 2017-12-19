package org.noogly.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent the document index as a map. Key is the document id and Value is the name of the document 
 * @author anuva banwasi
 *
 */
public class DocumentIndex {
	
	Map<Integer, String> docIndex = new HashMap<Integer, String>();

	public Map<Integer, String> getDocIndex() {
		return docIndex;
	}

	public void setDocIndex(Map<Integer, String> docIndex) {
		this.docIndex = docIndex;
	}

	@Override
	public String toString() {
		return "Document [docIndex=" + docIndex + "]";
	}

	

}
