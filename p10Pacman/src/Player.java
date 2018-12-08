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
	 * ID
	 */
	public int playerID;
	
	public Player(int x, int y, int playerID) {
		
		//set bounds
		this.setBounds(x, y, this.HEIGHT, this.WIDTH);
		this.playerID=playerID;
	}
	
	
	public void moveLeft() {
		this.x-=Game.playerStep;
	}
	
	public void moveRight() {
		this.x+=Game.playerStep;
	}
	
	public void moveUp() {
		this.y-=Game.playerStep;
	}
	
	public void moveDown() {
		this.y+=Game.playerStep;
	}
	
	
	/**
	 * wrap the player in the world
	 */
	public void wrap() {
		
		if (this.getX()<2) {
			this.x=Game.gameWorld.worldWidth-5;
		}
		else if(this.getX()>Game.gameWorld.worldWidth-2) {
			this.x=5;
		}
		
		if (this.getY()<2) {
			this.y=Game.gameWorld.WorldHeight-5;
		}
		else if (this.getY()>Game.gameWorld.WorldHeight-2) {
			this.y=5;
		}
	}
	
	
	public void draw(Graphics g) {
		
		if(this.playerID==1) {
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.PINK);
		}
		
		g.fillRect(x, y, this.WIDTH, this.HEIGHT);
		
	}
	
	
}
