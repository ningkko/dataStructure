package edu.smith.cs.csc212.p3;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HTMLColors {
	/**
	 * The color map read from colors.csv
	 */
	Map<String,Color> COLORS=new HashMap<String,Color>();

	public HTMLColors() {
		
		File path = new File("src/main/resources/colors.csv");	    

	    try {
	    
	    	//build a buffered reader to read in the file
		      BufferedReader reader = new BufferedReader(new FileReader(path));
		      
		      String line;
		      while(true) {
		        line = reader.readLine();
		        
		        if (line == null) {
		          break;
		        }
		        
		    	// make all lines to lower case for later indexing

		        line=line.toLowerCase();
		        String[] pieces = line.split(",");
		        int colorNumber = Integer.parseInt(pieces[1].substring(1), 16);
		        COLORS.put(pieces[0], new Color(colorNumber));
		        
		      }
		      reader.close();
		    } catch (FileNotFoundException e) {
		      System.err.println("Couldn't find HTML Colors CSV.");
		      System.exit(-1);
		    } catch (IOException e) {
		      System.err.println("Couldn't read HTML Colors CSV.");
		      System.exit(-1);
		    }
	    
	}
}
