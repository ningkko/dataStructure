package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.util.Random;

public class Preys extends Fish{
	
	// if it's eaten by the predator.
	boolean eaten=false;
	
	// generally still fish
	public Preys(int startX, int startY, int speed, boolean isLittle, boolean facingLeft, boolean hasDestination,
			Color color) {
		super(startX, startY, speed, isLittle, facingLeft, hasDestination, color);
	}
	
	// create a list of preys
	public Preys[] createPreyList(int num) {
		
		// random numbers for generating colors
		Random rand=new Random();
		
	    Preys[] preys=new Preys[num];
	    for(int i=0;i<preys.length;i++) {
	    	
	    	Color color=new Color(120+rand.nextInt(55),120+rand.nextInt(55),120+rand.nextInt(55));
	    	
	    	// make half of the fishes facing left and half facing right
	    	if(i%2==0) {
	    	
	    		preys[i]=new Preys(rand.nextInt(500),rand.nextInt(500),-1,true,true,false,color);
	    		
	    	}else {
	    		
	    		preys[i]=new Preys(rand.nextInt(500),rand.nextInt(500),1,true,false,false,color);
	    	}
	    }
	    return preys;
	    
	}
	
	@Override
	public void move() {
        
        //check if wrapping needed
        this.wrap();
        //let it move vertically with its velocity
        this.x+=this.speed;
	}
	
	
}
