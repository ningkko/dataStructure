import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends Rectangle{


	public int detectableDistance;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Monster( int x , int y) {

		
		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize, Game.playerSize);
		
	}
	
	public void move() {
		
		this.wrap();
		/**
		 * within detectable distance of P1?
		 */
		boolean nearP1=false;
		if ((Math.abs(this.getX()-Game.player1.getX())<=detectableDistance)&&
				(Math.abs(this.getY()-Game.player1.getY())<=detectableDistance)) {
			nearP1=true;
		}
		
		/**
		 * within detectable distance of P2?
		 */
		boolean nearP2=false;
		if ((Math.abs(this.getX()-Game.player2.getX())<=detectableDistance)&&
				(Math.abs(this.getY()-Game.player2.getY())<=detectableDistance)) {
			nearP2=true;
		}
		
		// randomly chase one of them if near both
		if(nearP1&&nearP2) {
			
			if(Math.random()<0.5) {
				this.chase(1);
			}else {
				this.chase(2);
			}
		}
		else if (nearP1) {
			this.chase(1);
		}
		else {
			this.chase(2);
		}
	}
	
	
	public void chase(int playerID) {
		
		Player p;
		//chase player1
		if(playerID==1) {
			p=Game.player1;
		}
		//else chase p2
		else {
			p=Game.player2;
		}
		
		if (p.getX()<this.getX()) {
			this.moveLeft();
		}
		else if (p.getX()>this.getX()) {
			this.moveRight();
		}
		else if (p.getY()>this.getY()) {
			this.moveDown();
		}
		else if (p.getY()<this.getY()) {
			this.moveUp();
		}
		
	}
	
	public void moveLeft() {
		this.x-=Game.monsterStep;
	}
	
	public void moveRight() {
		this.x+=Game.monsterStep;
	}
	
	public void moveUp() {
		this.y-=Game.monsterStep;
	}
	
	public void moveDown() {
		this.y+=Game.monsterStep;
	}
	
	/**
	 * wrap the monster in the world
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
	public void drawMonster(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, Game.playerSize, Game.playerSize);
	}
}
