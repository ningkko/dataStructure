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
	 * pixel one step
	 */
	private int step = 4;
	
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
		this.x-=step;
	}
	
	public void moveRight() {
		this.x+=step;
	}
	
	public void moveUp() {
		this.y-=step;
	}
	
	public void moveDown() {
		this.y+=step;
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
