package edu.smith.cs.csc212.p1;

import java.io.File;

public class SaveAquariumGIF {

	/**
	 * This class exists to teach you that you can have main methods in different places!
	 * @param args
	 */
	public static void main(String[] args) {
		Aquarium app = new Aquarium();
		int numSeconds = 3;
		app.playToGIF(new File("aquarium.gif"), 50 * numSeconds);
	}

}