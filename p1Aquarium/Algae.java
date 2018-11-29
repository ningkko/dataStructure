package edu.smith.cs.csc212.p1;

import java.awt.Graphics2D;

public class Algae {

	// the higher the green level, the greener the background.
	int green_level=0;
    boolean eaten=false;
    
    // default
    public Algae(){
        
    }
    
    public void grow() {
        green_level+=1;
    }
    public void eaten() {
        green_level-=1;
    }
    
    // this function tells the snail when to eat the algae.
    public void state() {
        
        if (this.green_level==0) {
            this.eaten=false;
        }else if (this.green_level==360) {
            this.eaten=true;
        }
    }
    
    public void draw(Graphics2D g) {
        
    	this.state();
        if(this.eaten) {
            this.eaten();
        }else {
            this.grow();
        }   
    }
}
