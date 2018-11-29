package edu.smith.cs.csc212.p3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.TextBox;

public class WordCloud extends GFX {
	/**
	 * This is how many random locations to consider for words.
	 */
	public static final int RANDOM_TRIES = 50;
	/**
	 * This is the biggest font to use.
	 */
	public static double biggestFont = 36.0;
	/**
	 * This is the smallest font to use.
	 */
	public static double smallestFont = 12.0;
	/**
	 * The word locations and colors.
	 */
	public Map<String, TextBox> plottedWords = new HashMap<>();

	/**
	 * The color map retrieved from colors.cvs
	 */
	
	public static Map<String,Color> COLORS=new HTMLColors().COLORS;
	
	/**
	 * Implement this based of my HTMLColor example in class.
	 * 
	 * @param input - the color name.
	 * @return the color itself.
	 */
	public static Color getColorByName(String input) {
		// You probably want to keep this... (and adjust class code).
		input = input.toLowerCase();
		// Or you want to define capitalize(input).
		
		
		// if COLORS contains the key
		if(COLORS.containsKey(input)) {
			
			// return the corresponding color
			return COLORS.get(input);
			
		}else {
			//else return a transparent color
			return new Color(1,1,1,1);
		}
	  }		
	

	public WordCloud(Map<String, Integer> counts) {
		// If you give us no data, don't plot anything.
		if (counts.size() == 0) {
			return;
		}
		
		// Get some random numbers from here.
		Random rand = new Random();

		// Normalize the counts coming in, so we can compute font sizes.
		double maxCount = -Double.MAX_VALUE;
		double minCount = Double.MAX_VALUE;
		for (int count : counts.values()) {
			if (count > maxCount) {
				maxCount = count;
			}
			if (count < minCount) {
				minCount = count;
			}
		}


		// We're going to try k times to place a word randomly with no overlap, and then
		// skip it if we can't.
		Map<String, Rectangle2D> placed = new HashMap<String, Rectangle2D>();
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			// Calculate the font size.
			double importance = (entry.getValue() - minCount) / (maxCount - minCount);
			double fontSize = importance * (biggestFont - smallestFont) + smallestFont;

			String word = entry.getKey();
			TextBox forEntry = new TextBox(word.toUpperCase());
			forEntry.setFont(TextBox.BOLD_FONT);
			forEntry.setFontSize(fontSize);
			forEntry.setColor(getColorByName(word));
			Rectangle2D shapeOfWord = forEntry.getBoundingBox();

			int wordWidth = (int) Math.ceil(shapeOfWord.getWidth());
			int wordHeight = (int) Math.ceil(shapeOfWord.getHeight());

			boolean wasPlaced = false;
			for (int i = 0; !wasPlaced && i < RANDOM_TRIES; i++) {

				double x;
				double y;

				// Half the time, try to place randomly.
				x = rand.nextInt(getWidth() - wordWidth);
				y = rand.nextInt(getHeight() - wordHeight);

				// Construct a random location.
				Rectangle2D maybe = new Rectangle2D.Double(x, y, shapeOfWord.getWidth(), shapeOfWord.getHeight());

				// Check to see if it's OK for a new word:
				boolean overlaps = false;
				for (Rectangle2D already : placed.values()) {
					if (already.intersects(maybe)) {
						overlaps = true;
						break;
					}
				}

				if (!overlaps) {
					// place the word into list of conflicts.
					placed.put(word, maybe);
					// set location of the textbox.
					forEntry.centerInside(maybe);
					// Add to draw map:
					this.plottedWords.put(word, forEntry);
					// Exit loops:
					wasPlaced = true;
				}
			}

			if (!wasPlaced) {
				System.out.println("Couldn't place " + word);
			}

		}
	}


	/**
	 * Draw the gray background and then the words.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (TextBox box : plottedWords.values()) {
			box.draw(g);
		}
	}

	/**
	 * Test the WordCloud plotting with some fake data.
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> fakeData = new HashMap<>();
		System.out.println(COLORS);
		fakeData.put("red", 100);
		fakeData.put("blue", 50);
		fakeData.put("orange", 25);
		fakeData.put("yellow", 30);
		fakeData.put("green", 40);
		fakeData.put("magenta", 50);
		fakeData.put("white", 70);
		fakeData.put("black", 80);
		WordCloud app = new WordCloud(fakeData);
		app.start();
	}
}
