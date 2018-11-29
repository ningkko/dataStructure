package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Bubble {
    int speed;
    int size;
    int x;
    int y;
    Random rand=new Random();
    int frequency=((int)Math.random()*100)%3;

    
    public Bubble(int size,int x, int y){
        this.size=size;
        this.x=x;
        this.y=y;
    }

    // makes the bubble appear again at the bottom of the scrren
    public void wrap(){

        if(y<=0) {
        	
            y=rand.nextInt(100)+500;
        }

    }
    
    // Bubble speeds according to the size
    public void move(){
        y-=50/size;

    }


    public void draw(Graphics2D g){
        
        this.wrap();
        this.move();
        
        Color color=new Color(1f,1f,1f,0.5f);
        g.setColor(color);

        Shape bubble=new Ellipse2D.Double(x-15,y-10,(int)(240/(size)),(int)(240/(size)));

        g.fill(bubble);
        g.draw(bubble);
    }
}
