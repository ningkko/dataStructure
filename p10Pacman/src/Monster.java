import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Actor{
	
	/**
	 * images it should use
	 */
	private int useImageNum;

	/**
	 * is the monster chasing some player yet?
	 */
	private boolean chasing;
	
	/**
	 * 1=left
	 * 2=right
	 * 3=up
	 * 4=down
	 */
	private int direction;
	
	/**
	 * step
	 */
	private int step;
	
	/**
	 * range able to detect player
	 */
	private int detectableDistance;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Monster( int x , int y) {

		this.step=Game.monsterStep;
		this.chasing=false;
		this.detectableDistance=100;
		this.useImageNum=1;

		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize, Game.playerSize);
		

	}
	
	
	
	public void move() {
		
		this.wrap();
		/**
		 * within detectable distance of P1?
		 */
		boolean nearP1=false;

		//stop chasing once caught
		if(!Game.p1Lose&&!Game.player1.isImmune()) {
			
			if ((Math.abs(this.getX()-Game.player1.getX())<=detectableDistance)&&
					(Math.abs(this.getY()-Game.player1.getY())<=detectableDistance)) {
				nearP1=true;
				this.chasing=true;
				
			}
		}
		/**
		 * within detectable distance of P2?
		 */
		boolean nearP2=false;
		
		if(!Game.p2Lose&&!Game.player2.isImmune()) {
			
			if ((Math.abs(this.getX()-Game.player2.getX())<=detectableDistance)&&
					(Math.abs(this.getY()-Game.player2.getY())<=detectableDistance)) {
				nearP2=true;
				this.chasing=true;
				
			}
		}

		if(this.chasing==false) {
			
			this.randomlyMove();
		}
		
		else {
			
			// randomly chase one of them if near both
			if(nearP1&&nearP2) {
				
				if(Math.random()<0.5) {
					this.chase(1);
				}
				else {
					this.chase(2);
				}
				
			}
			
			else if (nearP1) {
				this.chase(1);
			}
			else if (nearP2){
				this.chase(2);
			}
			else {
				chasing=false;
			}
			
			
		}
		
	}
	
	
	public void chase(int playerID) {
		
		Player p=null;
		//chase player1
		if(playerID==1&&!Game.p1Lose) {
			p=Game.player1;
		}
		//else chase p2
		else if(playerID==2&&!Game.p2Lose){
			p=Game.player2;
		}
		
		if(p!=null) {
			if (p.getX()<this.getX()) {
				this.moveLeft(Game.monsterStep);
			}
			else if (p.getX()>this.getX()) {
				this.moveRight(Game.monsterStep);
			}
			if (p.getY()>this.getY()) {
				this.moveDown(Game.monsterStep);
			}
			else if (p.getY()<this.getY()) {
				this.moveUp(Game.monsterStep);
			}
		}
		
		
	}
	
	
	
	public void randomlyMove() {
		
		if(Game.gameLevel.equals("l1")) {
			// change direction if at a crossover
			this.moveAtCrossover();
		}else if (Game.gameLevel.equals("l2")) {
			if(Math.random()<0.02) {
				this.selectNewDirection();
			}
		}
		
		//move
		if(this.direction==1) {
			this.moveLeft(this.step);
		}
		else if(this.direction==2) {
			this.moveRight(this.step);
		}
		else if(this.direction==3) {
			this.moveUp(this.step);
		}
		else if(this.direction==4){
			this.moveDown(this.step);
		}
		
	}
	
	public void selectNewDirection() {
		
		
		// till the monster chooses a valid direction
		while(true) {
			
			double chance=Math.random();
			
			if(chance<0.25&&this.canMove(this.x-this.step, this.y)) {
				this.direction=1;
				break;
			}
			else if(chance<0.5&&chance>=0.25&&this.canMove(this.x+this.step, this.y)) {
				this.direction=2;
				break;
				
			}else if(chance<0.75&&chance>=0.5&&this.canMove(this.x, this.y-this.step)) {
				
				this.direction= 3;
				break;
				
			}else if(chance>=0.75&&this.canMove(this.x, this.y+this.step)){
				this.direction= 4;
				break;
			}
		}
	}	
	
	
	public void moveAtCrossover() {
		
		boolean l=this.canMove(this.x-this.step, this.y);
		boolean r=this.canMove(this.x+this.step, this.y);
		boolean u=this.canMove(this.x, this.y-this.step);
		boolean d=this.canMove(this.x, this.y+this.step);
		List<Integer> possible = new ArrayList<>();
		if (l) {
			possible.add(1);
		}
		if (r) {
			possible.add(2);
		}
		if (u) {
			possible.add(3);
		}
		if (d) {
			possible.add(4);
		}
		
		
		if (possible.contains(this.direction)) {
			if (possible.size()<=2) {
				return;
			}
			if (Math.random()<0.8) {
				return;
			}
		}
		
		this.direction = possible.get(ThreadLocalRandom.current().nextInt(possible.size()));
		
	}
	

	
	public void drawMonster(Graphics g) {
			
		Spirit spirit = null;
	
		if(this.useImageNum==1) {
			spirit=new Spirit("/monster/n1.png");
			
		
		}else {
			spirit=new Spirit("/monster/n2.png");

		}
		
		g.drawImage(spirit.getImg(),x,y,Game.playerSize,Game.playerSize,null);
		

	}
		
	
	public int useImageNum() {
		return this.useImageNum;
	}
		
	public void setUseImageNum(int n) {
		this.useImageNum=n;
	}
	

}
