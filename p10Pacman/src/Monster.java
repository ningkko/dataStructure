import java.awt.Graphics;

public class Monster extends Actor{

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
	 * spirit
	 */
	public Spirit spirit;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Monster( int x , int y) {

		this.spirit=new Spirit("/monster/ning1.png");
		this.step=Game.monsterStep;
		this.chasing=false;
		this.detectableDistance=100;
		this.setStep(Game.monsterStep);
		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize, Game.playerSize);
		
	}
	
	public void move() {
		
		this.wrap();
		/**
		 * within detectable distance of P1?
		 */
		boolean nearP1=false;
		
		//stop chasing once caught
		if(!Game.p1Lose) {
			
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
		
		if(!Game.p2Lose) {
			
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
	
	public void selectNewDirection() {
		double chance=Math.random();
		
		if(chance<0.25) {
			this.direction=1;
		}
		else if(Math.abs(chance-0.25)<0.25) {
			this.direction=2;
			
		}else if(Math.abs(chance-0.5)<0.25) {
			this.direction= 3;
			
		}else {
			this.direction= 4;
		}
	}	
	
	public void randomlyMove() {
		
		
		if (Math.random()<0.05) {
			this.selectNewDirection();
		}
		
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
			this.moveUp(this.step);
		}
		
	}
	

	
		public void drawMonster(Graphics g) {
			
			g.drawImage(this.spirit.getImg(),x,y,null);

	}

		

}
