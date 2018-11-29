package edu.smith.cs.csc212.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class provides a word splitter that will do a pretty good job for your books.
 * @author jfoley
 *
 */
public class WordSplitter {

	/**
	 * This is needed for my solution of splitting into words.
	 * Regular expressions are a type of graph (more next week, if time).
	 */
	private static Pattern spacesOrPunctuation = 
			Pattern.compile("(\\s+|\\p{Punct})");
	
	/**
	 * I'm giving you a version of this that is slightly better than
	 * the String.split(" ") that I used in lecture.
	 * 
	 * @return words - the words in the input text.
	 */
	public static List<String> splitTextToWords(String text) {
		List<String> words = new ArrayList<String>();
		
		for (String token : spacesOrPunctuation.split(text)) {
			token = token.trim().toLowerCase();
			if (token.isEmpty()) {
				continue;
			}
			// Skip boring, tiny words.
			if (token.length() <= 2) {
				continue;
			}
			
			words.add(token);
		}
		
		return words;
	}
	
	/**
	 * See the difference by running this.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		String testData = "This is a sentence with punctuation."
				+ "There shouldn't be much here.";
		System.out.println("words: " + splitTextToWords(testData));
	}

}
