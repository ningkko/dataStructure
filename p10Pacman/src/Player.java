import java.awt.Color;
import java.awt.Graphics;

public class Player extends Actor{
	
	
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
	private int playerID;
	
	
	/**
	 * game points
	 */
	private int points;
	
	
	public Player(int x, int y, int playerID) {
		
		//set bounds
		this.setBounds(x, y, this.HEIGHT, this.WIDTH);
		this.playerID=playerID;
		this.step=Game.playerStep;
		
	}
	
	
	
	public void draw(Graphics g) {
		
		if(this.playerID==1) {
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.PINK);
		}
		
		g.fillRect(x, y, this.WIDTH, this.HEIGHT);
		
	}

	
	public void eat() {
		
		Map map=World.map;
		
		for(Food f:map.food) {
			if (this.intersects(f)) {
				map.food.remove(f);
				this.points+=10;
				break;
			}
		}
		
	}



	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}
	

}
