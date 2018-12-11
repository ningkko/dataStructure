import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends Rectangle{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Food( int x , int y) {
		
		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize/4, Game.playerSize/4);
	
	}
	
	public void drawFood(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(x+12, y+12, 4, 4);
		
	}
}
