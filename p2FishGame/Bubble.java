package edu.smith.cs.csc212.p2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Bubble extends WorldObject {

	public Bubble(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		Shape circle = new Ellipse2D.Double(-0.6, -0.6, 1.2, 1.2);
		g.setColor(new Color(1f,1f,1f,0.5f));
		g.fill(circle);
	}

	@Override
	public void step() {
		//bThebbubble doesn't need to move
	}

}
