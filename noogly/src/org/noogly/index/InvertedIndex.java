package org.noogly.index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.noogly.model.DocumentIndex;
import org.noogly.model.Index;

import com.google.gson.Gson;

/**
 * Build an index. An index maps terms to the documents where they are present
 * @author anuva banwasi
 *
 */
public class InvertedIndex {

	Map<String, Set<Integer>> index = new HashMap<String, Set<Integer>>();
	Map<Integer, String> docIndex = new HashMap<Integer, String>();
	int docCounter;
	static DocumentIndex docIndx = new DocumentIndex();
	
	public static void main (String args[]) {
		System.out.println("Usage -> java <PATH TO LOCAL DIR>/data <PATH TO LOCAL DIR>/index/index.txt <PATH TO LOCAL DIR>/documents/docindex.txt");

		InvertedIndex ii = new InvertedIndex();
		Index ind = ii.buildIndex(args[0]);
		System.out.println("ind is " + ind);
		ii.writeToDisk(ind, args[1]);
		
		ii.writeDocIndexToDisk(docIndx, args[2]);
	}
/**
 * Acknowledgement: https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
 * Write the index to disk
 * @param ind Index 
 * @param path Absolute path on disk to store the index
 */

	private void writeToDisk(Index ind, String path) {
		
		// Convert the index to json string representation
		Gson gson = new Gson();		
		String indexStr = gson.toJson(ind);	
		
		// Write the string to disk
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(indexStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

/**
 * Write the document index to disk
 * Acknowledgement: https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
 * @param doc index representing document index
 * @param path Absolute path on disk to store the index
 */
	
	private void writeDocIndexToDisk(DocumentIndex doc, String path) {
		
		// Convert the index to json string representation
		Gson gson = new Gson();		
		String indexStr = gson.toJson(doc);	
		
		// Write the string to disk
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(indexStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Build the index
	 * @param dataPath Absolute path to the input directory
	 * @return
	 */
	private Index buildIndex(String dataPath) {
		
		Index index = buildIndex(new File(dataPath));		
		return index;
	}
	
	
/**
 * Acknowledgement: https://www.mkyong.com/java/how-to-traverse-a-directory-structure-in-java/
 * Read the files representing input files and build an index
 * @param node File to read from disk
 * @return Index 
 */
	public Index buildIndex(File node) {
		// recursively call method if node is a directory
		if(node.isDirectory()) {
			String[] filenames = node.list();
			for(String filename : filenames) {
				buildIndex(new File(node, filename));
			}
		} else {
			// if the node is a file, read it and add to the document index
			int docId = getDocId();
			docIndex.put(docId, node.getName());
			readFile(docId, node.getAbsolutePath());
		}		
		
		// Build the index and document index
		Index ind = new Index();
		ind.setIndex(index);
		docIndx.setDocIndex(docIndex);
		return ind;
	}
	
	
/**
 * Acknowledgement: https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
 * Read the file on disk
 * @param docId document id of the file
 * @param path absolute path to the file
 */
	private void readFile(int docId, String path) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				//build index			
				String[] tokens = tokenize(sCurrentLine);
				for(String token : tokens ) {
					
					// if index does not contain term, add a new key to the map
					// else add the document id to the list of document ids for the term
					if (index.get(token) == null) {
						addNewTokenToIndex(docId, token);
					} else {
						addExistingTokenToIndex(docId, token);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Add the document id to the list of document ids for the term
	 * @param docId document id containing term
	 * @param token term 
	 */
	private void addExistingTokenToIndex(int docId, String token) {
		System.out.println("increasing count of existing token " + token);
		
		Set<Integer> docIds = index.get(token);
		
		if(!docIds.contains(docId)) {
		
			docIds.add(docId);
		} 
	}

	/**
	 * Add the term to the map. Key is the docId and value is the list of doc ids where the term is present
	 * @param docId document id containing term
	 * @param token term
	 */
	private void addNewTokenToIndex(int docId, String token) {
		System.out.println("adding token " + token);
		
		Set<Integer> docIds = new TreeSet<Integer>();
		docIds.add(docId);
		index.put(token, docIds);
	}

	/**
	 * assign sequential ids to documents
	 * @return
	 */
	private int getDocId() {
		return ++docCounter;
	}
	
	/**
	 * Pre-processing - convert input files to lowercase and replace ',' and ';' with spaces. Split to return an array of words
	 * @param line each line of file
	 * @return array of words in line
	 */
	public String[] tokenize(String line) {
		line.replace(',', ' ');
		line.replace(';', ' ');
		return line.toLowerCase().split(" ");
	}
}
