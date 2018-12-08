import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wall( int x , int y) {

		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize,Game.playerSize);
		
	}
	
	public void drawWall(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, Game.playerSize, Game.playerSize);
	}

}
