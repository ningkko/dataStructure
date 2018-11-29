package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Background {

	public void drawGrassland(Graphics2D g){
	      
		//grass land
	    g.setColor(Color.blue);
	    g.fillRect(0,460,500,500);
	}
	public void drawTreasureBox(Graphics2D g) {
	
	    // treasure box
	    g.setColor(Color.yellow);
	    g.fillRect(260, 430, 80, 50);
	    g.setColor(Color.blue);
	    g.setFont(new Font("Serif", Font.PLAIN, 16));
        g.drawString("treasurebox", 263, 460); 

	}
	// The End page
	public void showEnd(Graphics2D g) {
	    g.setColor(Color.red);
	    g.setFont(new Font("Serif", Font.PLAIN, 72));
        g.drawString("The End", 150, 250); 
	}

}