package edu.smith.cs.csc212.p2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Food extends WorldObject {
	
	Color color=Color.white;
	
	public Food(World world) {
		super(world);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		Shape food=new Ellipse2D.Double(-.18, -.18, .35, .35);
		g.fill(food);
	}

	@Override
	public void step() {
		// food does not move
	}


}
