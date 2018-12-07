import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	
	/**
	 * Eclipse asked me to add this
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * player size
	 */
	private final int HEIGHT=32;
	private final int WIDTH=32;
	
	/**
	 * inherit later from the world.
	 */
	public Graphics g;
	
	public int playerID;
	
	public Player(int x, int y, int playerID) {
		
		//set bounds
		this.setBounds(x, y, this.HEIGHT, this.WIDTH);
		this.playerID=playerID;
	}
	
	public void tick() {}
	
	public void draw() {
		
		if(this.playerID==1) {
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.PINK);
		}
		
		g.fillRect(x, y, this.WIDTH, this.HEIGHT);
		
	}

}
