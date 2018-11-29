package edu.smith.cs.csc212.p3;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We're going to analyze the colors used in Project Gutenberg books.
 * When you switch the book, make sure to get the UTF-8 ".txt" file, not HTML or PDF.
 * 
 * @author jfoley
 *
 */
public class AnalyzeBookColors {
	/**
	 *  This is the book you want to analyze.
	 *  You should change this!
	 */
	
	
	public static final String bookPath = "src/main/resources/The Dreadnought Boys on a Submarine";
	
	/**
	 * COLORS extracted from colors.csv
	 */
	public static Map<String,Color> COLORS=new HTMLColors().COLORS;

	/**
	 * This is where you analyze the use of colors in your book.
	 * @param book - the file to process.
	 * @throws IOException 
	 */
	public static void analyze(File book) throws IOException {
		// Your logic goes here!
		
		Map<String,Integer> data=new HashMap<String,Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(book));
			/*
			 * Line is the lines in the book file
			 */
			String line;
			
			while(true) {
				// use the reader to read line by line
				line=reader.readLine();
				// stop if the line is null
				if(line==null) {
					break;
				}
				//split each line using the WordSplitter class
				List<String> words=WordSplitter.splitTextToWords(line);
				
				for(String word : words) {
					if(COLORS.containsKey(word)) {
						// if the dictionary already contains the key
						if(data.containsKey(word)) {
							//get the previous value of the count
							int before=data.get(word);
							//reset it by itself increased by one
							data.put(word, before+1);
						}else {
							//otherwise put the key into the dictionary
							data.put(word,1);
						}
					}
				}
			}
			//close the reader after finishing reading in
			reader.close();
			
		}catch(FileNotFoundException e){
			
			System.out.println("Could not find the book");
			System.exit(-1);
			
		}catch(IOException e) {
			
			System.out.println("Could not rea the book");
			System.exit(-1);
			
		}
		
		// Open a WordCloud and view it?
		WordCloud viewer = new WordCloud(data);
		// Set window title:
		viewer.setTitle(book.getName());
		// Run the simulation:
		viewer.start();
	}
	
	/**
	 * This is my entry-point. Do not change this.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// This logic is so that I can point your code to my own data while testing.
		String target;
		if (args.length == 0) {
			// But that usually it uses your book!
			target = AnalyzeBookColors.bookPath;
		} else {
			target = args[0];
		}
		
		// Make sure the file exists and is happy.
		File book = new File(target);
		if (!book.canRead()) {
			System.err.println("Book can not be read: "+book);
			System.exit(-1);
		}
		if (!book.exists()) {
			System.err.println("Book does not exist: "+book);
			System.exit(-2);
		}
		if (!book.isFile()) {
			System.err.println("Book is not a file: "+book);
			System.exit(-3);
		}
		
		// Call your code.
		analyze(book);
	}
}
