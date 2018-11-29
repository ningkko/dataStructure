// Lab Buddy: Ziheng Ru
package edu.smith.cs.csc212.p1;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
public class Fish {
    int x;
    int y;
    Color color;
    int speed;
    boolean isLittle;
    boolean facingLeft;
    boolean hasDestination;
    double destinationX;
    double destinationY;
	Random rand = new Random();

    
    // To construct a fish, we need to know 
    //its start point, velocity, if is little / is facing left / has destination, and its color.
    public Fish(int startX, int startY, int speed, boolean isLittle, boolean facingLeft, boolean hasDestination, Color color) {
        this.x=startX;
        this.y=startY;
        this.speed=speed;
        this.facingLeft=facingLeft;
        this.color=color;
        this.isLittle=isLittle;
        this.hasDestination=hasDestination;
    }
    
    public void drawFish(Graphics2D g) {
    	
    	// first update new positions of the fish
        this.move();
        
        // draw the fish using methods from the Creature class
        if (this.facingLeft==true) {
            if (this.isLittle) {
            	Creatures.drawSmallFishFacingLeft(g, this.color, this.x, this.y);
            }else {
            	Creatures.drawFishFacingLeft(g, this.color, this.x, this.y);
            }
        }else if (this.facingLeft==false) {
        	if (this.isLittle) {
            	Creatures.drawSmallFishFacingRight(g, this.color, this.x, this.y);
            }else {
            	Creatures.drawFishFacingRight(g, this.color, this.x, this.y);
            }
        }
    }
   
  
    public void wrap() {

        //wrap horizontally
        if (this.x<0) {
            this.x+=480;
        }else if (x>500) {
            this.x-=480;
        }

    }

    public void checkDirection() {
    	
    	// tells the fish which side to face to
        if (this.x<this.destinationX) {
            this.facingLeft=false;
        }else{
            this.facingLeft=true;
        }
    }

    public void findDestination(){
            
        	// check if the fish reached its destination
            if ((this.destinationX==this.x)&&(this.destinationY==this.y)) {
                // generate a new destination
                this.destinationX=rand.nextInt(500);
                this.destinationY=rand.nextInt(500);
                // set it's reached-destination-state to false.
           
            }else{
            // let the fish move toward the destination point if it's outside of the destination range.  
                
                // First check the direction of the fish
                this.checkDirection();

                // move towards the destination
                
                // the method I used but generated unfixed velocity
                // Calculate the distance from the fish to the destination
                // double distance=Math.sqrt((x-destinationX)*(x-destinationX)+(y-destinationY)*(y-destinationY));
                // x+=(2/distance)*(destinationX-x);
                // y+=(2/distance)*(destinationY-y);
                if(this.x>this.destinationX) {
                	this.x-=this.speed;
                }else if(this.x<this.destinationX) {
                	this.x+=this.speed;
                }
             
                if(this.y>this.destinationY) {
                	this.y-=this.speed;
                }else if(this.y<this.destinationY) {
                	this.y+=this.speed;
                }
            }        
      
    }
    
    
    // this function tells how a fish moves
    public void move() {
        
        // if the fish has a destination
        if (this.hasDestination){
            this.findDestination();
        }
        // if the fish does not have a direction
        else if (this.hasDestination==false) {
            //check if wrapping needed
            this.wrap();
            //let it move vertically with its velocity
            this.x+=this.speed;
        }
    
    }
    
    public void stopMoving() {
    	this.speed=0;
    }
}
    
