package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;

public class Predator extends Fish{
	
	boolean all_eaten=false;
	int destinationX;
	int destinationY;
	
	public Predator(int startX, int startY, int speed, boolean isLittle, boolean facingLeft, boolean hasDestination,
			Color color) {
		super(startX, startY, speed, isLittle, facingLeft, hasDestination, color);
		// TODO Auto-generated constructor stub
	}
	
	public void wrap() {
		
		if(this.x<20) {
			this.x=480;
		}else if(this.x>480) {
			this.x=20;
		}
		
		if(this.y<20) {
			this.y=480;
		}else if(this.y>480) {
			this.y=20;
		}
		
	}
    
	
	@Override
	public void drawFish(Graphics2D g) {
		if(all_eaten==false) {
			this.wrap();
			this.move();
		}else {
			this.moveOut();
		}
		if (this.facingLeft==true) {
      
			Creatures.drawSharkLeft(g, this.color, this.x, this.y);
        
		}else if (this.facingLeft==false) {
			Creatures.drawSharkRight(g, this.color, this.x, this.y);

		}

	}
	
	// when the shark is within 40 of the prey, it eats it.
	public void eat(Preys prey) {
		if((this.x-prey.x<40)&&(this.x-prey.x>-40)&&(this.y-prey.y<40)&&(this.y-prey.y>-40)) {
			prey.eaten=true;
		}
	}
	
	// the shark looks for fishes
	public void chase(Preys[] preys) {
		for(int i=0;i<preys.length;i++) {
			if(preys[i].eaten==false) {
				this.eat(preys[i]);
				
			}
		}
	}
	
    // move the fish out of the screen
	public void moveOut() {
		this.y-=2;
	}
}
