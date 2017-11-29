/**
 * lets user store files under a keyword of their choice and then view all the files they have stored under a certain keyword
 * @author Anuva Banwasi
 */
package apcs.exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchFile {
	List<Page> pages = new ArrayList<Page>();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SearchFile s = new SearchFile();
		s.takeUserInputForFile();
		s.takeUserInputForFile();
		s.takeUserInputForPages();
		s.handleCleanUp();
	}

	/**
	 * Close scanner
	 */
	private void handleCleanUp() {
		if (scanner != null)
			scanner.close();
	}

	/**
	 * Method to take input from user. 
	 * Input is the location of the file and a word/topic to search in the file
	 */
	public void takeUserInputForFile() {
		System.out.println("\nUsage:"); 
		System.out.println("\nThe Program will prompt the user to enter input in the following sequence.");
		System.out.println("\n1. Location of a file.");
		System.out.println("\n2. Term to search in the above file (program will count number of occurences of the term in the file.)");
		System.out.println("\n3. The program will again prompt the user to enter the location of a file and search term as defined in 1 and 2 above");
		System.out.println("\n4. The program will then ask for the search term and returns the file names where the search terms are found.");
		

		System.out.println("\nPlease enter location of file");
		String location = scanner.nextLine();
		
		System.out.println("Please enter the search topic");
		String topic = scanner.nextLine();

		readFile(location, topic, 0);
	}

	/**
	 * Method to take a string from user representing the topic to search for and print the list of filenames that contain the topic
	 */
	public void takeUserInputForPages() {
		System.out.println("\nRetrieving Results...What topic are you interested in?");
		String topic = scanner.nextLine();

		printPagesUnderATopic(topic);
	}

	/**
	 * Checks if the line contains the keyword
	 * 
	 * @param str string to be checked if contains keyword
	 * @param word keyword
	 */
	public boolean checkLine(String str, String word) {
		if (str.contains(word)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if a file contains a given topic occurs count or more number of times and is hence important
	 * @param count
	 * @return true if file contains keyword at least three times or if user
	 *         overrides and says they still want to store the file under entered
	 *         keyword they entered false if the the file does not contain the
	 *         keyword at least three times and the user says that do not want to
	 *         store the file under the keyword they entered
	 */
	public boolean checkFileIsImportant(int count) {
		if (count >= 3) {
			return true;
		} else {
			System.out.println(
					"This file contains the given keyword little to no times. Are you sure you still wan't to store it under this keyword. Type 'Yes' or 'No'");

			String str = scanner.nextLine();

			if (str.equalsIgnoreCase("yes"))
				return true;
			else
				return false;
		}
	}

	/**
	 * Creates a new page, adds the file to it, and sets its topic as the inputed
	 * string
	 * 
	 * @param f file to be added to Page object
	 * @param str topic to be set for Page object
	 */
	public void storePage(File f, String str) {
		Page p = new Page(f);
		p.setTopic(str);
		pages.add(p);
		System.out.println("File stored under Topic: " + str);
	}

	/**
	 * Print filenames corresponding to the given str
	 * 
	 * @param str
	 */
	public void printPagesUnderATopic(String str) {
		System.out.println("\nThese are all the files you have stored under that topic:");
		for (int i = 0; i < pages.size(); i++)
			if (pages.get(i).getTopic().equals(str)) {
				pages.get(i).toString();
			}
	}

	
	/**
	 * Read a file at a given path and look for string str in the file
	 * Calls checkLine on each line to check if the file contains str
	 * If file does not exist throws a FileNotFoundException, attempts to correct incorrect user input by retrying 2 timess
	 * If no valid input is received after 2 attempts, then aborts with an error code of -1
	 * In addition, 	if user enters blank/empty input for the path, then retries 2 times by asking user for a valid input. 
	 * If no valid input is received after 2 attempts, then aborts with an error code of -1
	 * @param path Path to file
	 * @param str Topic to search in file
	 */
	public void readFile(String path, String str, int cnt) {
		String topic = str;
		int count = 0;

		BufferedReader br = null;
		FileReader fr = null;
			
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				if (checkLine(line, topic)) {
					count++;
				}
			}

			if (checkFileIsImportant(count))
				storePage(new File(path), str);

		} catch (FileNotFoundException e) {
			// catches runtime error for FileNotFound exception

			if(cnt >= 2) {
				System.err.println("Wrong user input. Aborting with error code -1");
				System.exit(-1);
			}
			
			cnt = cnt + 1;
			
			System.out.println("File does not exist. Please enter a valid path to a file");
			String location = scanner.nextLine();
			
			readFile(location, str, cnt);
			
		} catch (IOException e) {
			System.err.println("IOException occured");
			e.printStackTrace();
		} finally {
			try {

				if (br != null)
					br.close();
				
				if(fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class Page {
	private String topic;
	private File file;
	
	public Page() {
		
	}
	public Page(File f) {
		file = f;
	}
	/**
	 * getter method for topic
	 * @return topic of page as entered by user
	 */
 	public String getTopic() {
		return topic;
	}
 	/**setter method for topic
 	 * sets topic of page to the inputed idea string
 	 * @param idea
 	 */
	public void setTopic(String idea) {
		topic = idea;
	}
	/**
	 * getter method for file
	 * @return file of page corresponding to file path entered by user
	 */
	public File getFile() {
		return file;
	}
	/**setter method for file
 	 * sets file of page to the inputed File f
 	 * @param f file that file instance variable is to be set to
 	 */
	public void setFile(File f) {
		file = f;
	}
	/**
	 * toString method that returns the toString of the corresponding File class 
	 * prints File pathway of Page as entered earlier by user
	 */
	public String toString() {
		String result = file.toString();
		System.out.println(result);
		return result;
	}
}
