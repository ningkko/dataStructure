import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Actor extends Rectangle{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * pixels per step
	 */
	protected int step;
	
	public Actor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void moveLeft(int step) {
		if(this.canMove(this.x-this.step, this.y)) {
			this.x-=step;
		}
	}
	
	public void moveRight(int step) {
		if(this.canMove(this.x+this.step, this.y)) {
			this.x+=step;
		}
	}
	
	public void moveUp(int step) {
		if(this.canMove(this.x, this.y-this.step)) {
			this.y-=step;
		}
	}
	
	public void moveDown(int step) {
		if(this.canMove(this.x, this.y+this.step)) {
			this.y+=step;
		}
	}
	

	
	/**
	 * wrap the monster in the world
	 */
	public void wrap() {
		
		if (this.getX()<3) {
			this.x=Game.gameWorld.worldWidth-6;
		}
		else if(this.getX()>Game.gameWorld.worldWidth-3) {
			this.x=6;
		}
		
		if (this.getY()<3) {
			this.y=Game.gameWorld.WorldHeight-6;
		}
		else if (this.getY()>Game.gameWorld.WorldHeight-3) {
			this.y=6;
		}
	}
	
	public boolean canMove(int nextX, int nextY) {
		
		Point p=new Point(nextX,nextY);
		Dimension d=new Dimension(this.width,this.height);
		/**
		 * bounds of the current actor if moves to the next position
		 */
		Rectangle bounds=new Rectangle(p,d);
		/**
		 * the map we're currently using
		 */
		Map map=World.map;
		
		// check for each cell in the walls list
		for(int mapX=0; mapX<map.walls.length;mapX++) {
			for(int mapY=0;mapY<map.walls[0].length;mapY++) {
				
				// if there's something in the cell then check if the new position intersects with the wall
				if(map.walls[mapX][mapY]!=null) {
					// if yes return false
					if(bounds.intersects(map.walls[mapX][mapY])) {
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
	
	public int getStep() {
		return step;
	}



	public void setStep(int step) {
		this.step = step;
	}
	
	

}
