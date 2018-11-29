package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import me.jjfoley.gfx.GFX;

public class Aquarium extends GFX {

	//algae
	int bg_green=0;
	Random rand=new Random();
	
    int fish1X = getWidth() + 100;
    int fish2X = getWidth() + 300;
    int fish3X= 50;
    
    //public Fish(int startX, int startY, int velocity, boolean isLittle, boolean facingLeft, boolean hasDestination, Color color) {
    Fish fishWhite = new Fish(rand.nextInt(500),rand.nextInt(500),1,false,false,true,Color.white);
    Fish fishPink = new Fish(rand.nextInt(500),rand.nextInt(500),3,false,false,false,Color.pink);
  
    // public Bubbles(int speed,int size,int startX, int startY) 
    Bubble[] bubbles=this.createBubbles(30);
   
    Algae algae=new Algae();
    
    // background contains other objects
    Background bg=new Background();
    Snail snail=new Snail();
   
    //create a list of preys
    Preys fish=new Preys(0, 0, 0, false, false, false, null);
    Preys[] preys=fish.createPreyList(8);
   
    //create the shark
    Predator shark=new Predator(rand.nextInt(500), rand.nextInt(500), 8, false, false, true, Color.blue);
    
    @Override
    public void draw(Graphics2D g) {
    	
	        // Draw the "ocean" background.
    		this.bg_green=algae.green_level/3;
	        g.setColor(new Color(0,this.bg_green,0));
	        g.fillRect(0, 0, getWidth(), getHeight());
	        
	        //draw bubbles which is at the back
	        for(int i = 0;i<bubbles.length;i++) {
	            bubbles[i].draw(g);	
	        }
	        
	        bg.drawGrassland(g);
	        bg.drawTreasureBox(g);
	        
	        // Draw the fish!
	        Creatures.drawFishFacingLeft(g, Color.yellow, fish1X, 200);
	        // Draw the confused fish!
	        Creatures.drawFishFacingRight(g, Color.green, fish2X, 300);
	
	        // What if we wanted this little fish to swim, too?
	        Creatures.drawSmallFishFacingLeft(g, Color.red, fish3X, 100);
	        
	        fishWhite.drawFish(g);
	        fishPink.drawFish(g);
	        
	        //check if all fishes are eaten
	        shark.all_eaten=true;
	        shark.chase(preys);
	        // check and eat each fish passing by
	        for(Preys prey:preys) {
	        	if(prey.eaten==false){
	        		prey.drawFish(g);
	        		shark.all_eaten=false;
	        	}
	        }
	        
	        shark.drawFish(g);
	        	
	        // change the eyes of the snail
	        if(algae.eaten==false) {
	        	snail.eye=Color.red;
	        	snail.eyeWhite=Color.red;
	        }else {
	        	snail.eye=Color.black;
	        	snail.eyeWhite=Color.white;
	        }
	        
	    	snail.move(g);
	        algae.draw(g);
	
	        // Move the fish!
	        fish1X -= 1;
	        fish2X -= 2;
	        fish3X -= 3;
	        
	    // stop the nail and two fish, move the shark outside,
	    // Show 'the end' if all preys are eaten by the shark.
	    if(shark.all_eaten==true) {

    		fishWhite.stopMoving();
    		fishPink.stopMoving();
    		bg.showEnd(g);
    		snail.speed=0;

    	}
       
    }
    // this function returns int between two given ints
    public int randomNumber(int small,int large) {
    	Random rand = new Random();
    	return rand.nextInt(large-small)+small;
    }
    
    // returns a list of bubbles.
    public Bubble[] createBubbles(int num) {
    	
    	Bubble[] bubbles = new Bubble[num];
    	for(int i=0;i<num;i++) {
    		bubbles[i]=new Bubble(this.randomNumber(15,30),this.randomNumber(285, 320),this.randomNumber(500, 800));
       	}
        return bubbles;
    }
    
    public static void main(String[] args) {
        // Note that we can store an Aquarium in a variable of type GFX because Aquarium
        // is a very specific GFX, much like 7 can be stored in a variable of type int!
        
    	GFX app = new Aquarium();
        app.start();
    }
    
}