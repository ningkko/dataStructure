package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import me.jjfoley.gfx.GFX;

public class Snail extends GFX {
    
    int x=0;
    int y=0;
    int state=0;
    int speed=1;
    Color eye;
    Color eyeWhite;
    public Snail() {

    }
    
    public void drawDown(Graphics2D g) {
     // By making a new Graphics2D object, we can move everything that gets drawn to it.
     Graphics2D onBottom = (Graphics2D) g.create();
     onBottom.translate(this.x, getHeight()-51);
     Creatures.drawSnail(onBottom, Color.red, eyeWhite, eye);
     // It's OK if you forget this, Java will eventually notice, but better to have it!
     onBottom.dispose();
    }
    
    public void drawUp(Graphics2D g) {
     Graphics2D onBottom2 = (Graphics2D) g.create();
     onBottom2.translate(this.x, 51);
     onBottom2.scale(-1, -1);
     Creatures.drawSnail(onBottom2, Color.red, eyeWhite, eye);
     // It's OK if you forget this, Java will eventually notice, but better to have it!
     onBottom2.dispose();
    }
    public void drawRight(Graphics2D g) {
     Graphics2D onRight = (Graphics2D) g.create();
     onRight.translate(getWidth()-51, this.y);
     // Oh no, radians.
     onRight.rotate(-Math.PI/2);
     Creatures.drawSnail(onRight, Color.red, eyeWhite, eye);
     onRight.dispose();
    }
    public void drawLeft(Graphics2D g) {

     Graphics2D onLeft= (Graphics2D) g.create();
     onLeft.translate(+51, this.y);
     // Oh, cool, we can use degrees if we want:
     onLeft.rotate(Math.toRadians(90));
     Creatures.drawSnail(onLeft, Color.red, eyeWhite, eye);
     onLeft.dispose();
    }

    

    public void move(Graphics2D g){

        //4 states represents 4 lines the snail moves on
        //      state1
        //----------------
        //|              |
        //|state4        | state2
        //|              |
        //----------------
        //      state3


        if (x==0&&y==0) {
            state=1;
            this.x=51;
        }else if (x==450&&y==0) {
            state=2;
            this.y=51;
        }else if (x==450&&y==440) {
            state=3;
            this.x=449;
        }else if(x==0&&y==440){
            state=4;
        }

        if (state==1) {
            x+=this.speed;
            this.drawUp(g);

        }else if (state==2) {
            y+=this.speed;
            this.drawRight(g);

        }else if (state==3) {
            x-=this.speed;
            this.drawDown(g);

        }else if (state==4) {
            y-=this.speed;
            this.drawLeft(g);

        }
    }

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
