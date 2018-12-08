import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends Rectangle{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Food( int x , int y) {
		
		setBounds(x*Game.playerSize,y,Game.playerSize*Game.playerSize, Game.playerSize);
	
	}
	
	public void drawFood(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(x, y, Game.playerSize,Game.playerSize);
		
	}
}
