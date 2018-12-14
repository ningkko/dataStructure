import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

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
	 * how many life left?
	 */
	private int life;
	
	/**
	 * game points
	 */
	private int points;
	
	
	/**
	 * can the player be attacked?
	 */
	private boolean immune;
	
	/**
	 * reborn place in this map
	 */
	private int startX, startY;
	
	/**
	 * for changing image
	 */
	private int useImageNum;
	private boolean isMoving;
	
	
	public Player(int x, int y, int playerID) {
		
		//set bounds
		this.setBounds(x, y, this.HEIGHT, this.WIDTH);
		this.playerID=playerID;
		this.step=Game.playerStep;
		this.life=3;
		this.immune=false;
		this.useImageNum=1;
		this.isMoving=false;
		
	}
	
	
	
	public void draw(Graphics g) {
		
		
		Spirit spirit = null;
		if(this.playerID==1&&this.useImageNum==1) {
			spirit=new Spirit("/players/i1.png");	
		}
		else if(this.playerID==1&&this.useImageNum==2) {
			spirit=new Spirit("/players/i2.png");	
		}
		else if(this.playerID==2&&this.useImageNum==1) {
			spirit=new Spirit("/players/e1.png");
		
		}else if(this.playerID==2&&this.useImageNum==2) {
			spirit=new Spirit("/players/e2.png");

		}
		
		if(this.immune) {
			
			g.setColor(Color.red);
			
		}
		g.drawImage(spirit.getImg(),x,y,Game.playerSize,Game.playerSize,null);
		g.drawRect(this.x, this.y, Game.playerSize, Game.playerSize);
		g.setColor(new Color(1f,0f,0f,0f ));
	}
	
	

	public boolean canMove(int nextX, int nextY) {
		
		Point p=new Point(nextX,nextY);
		Dimension d=new Dimension(this.width,this.height);
		
//		int tileX = nextX / Game.playerSize;
//		int tileY = nextY / Game.playerSize;
//		
//		return map.walls[tileX][tileY] == null;
		
		
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
				else if (this.playerID==1&&!Game.p2Lose==true) {
					if(bounds.intersects(Game.player2)) {
						return false;
					}
				}
				else if(this.playerID==2&&!Game.p1Lose==true) {
					if(bounds.intersects(Game.player1)) {
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
	
	
	public void eat() {
		
		Map map=World.map;
		
		for(Food f:map.food) {
			if (this.intersects(f)) {
				if(f.getType()==0) {
					
					this.points+=10;
					
				}else if(f.getType()==1) {
					
					this.life+=1;
					
				}else if(f.getType()==2) {
					
					this.enterIndefectible();
					
				}else if(f.getType()==3) {
					
					this.points+=100;
				}
				
				
				map.food.remove(f);
				break;
			}
		}
		
	}

	public void enterIndefectible() {
		
		this.immune=true;
		
		if(this.playerID==1) {
	
			//turn back to false after 5 sec
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
	
				@Override
				  public void run() {
					  
					  Game.player1.setImmune(false);
					  
				  }
				}, 5*1000);
			
		}
		else if(this.playerID==2) {
			
			//turn back to false after 5 sec
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
	
				@Override
				  public void run() {
					  
					  Game.player2.setImmune(false);
					  
				  }
				}, 4*1000);
			
		}
	}
	
	public void isTouched() {
		
		if(this.life>0) {
			for (Monster m:Map.monsters) {
				
				if(this.intersects(m)&&!this.immune) {
					if(this.life>0) {
						this.enterIndefectible();
						this.x=this.startX;
						this.y=this.startY;
						this.life-=1;
					}
					
					if(this.life==0) {
						
						if(this.playerID==1) {
							
							Game.p1Lose=true;	
						}
						else if(this.playerID==2) {
							
							Game.p2Lose=true;
							
						}
					}
				}
			}

		}
	}
	
	
	/**
	 * give your life to your friend
	 * 
	 */
	public void saveFriend() {
		
		if(this.playerID==1&&this.life>1&&Game.p2Lose==true&&this.intersects(Game.player2)) {
		
			Game.player2.setLife(1);
			this.life-=1;
			Game.p2Lose=false;
			Game.player2.enterIndefectible();
			
			if(this.x<Game.player2.x&&this.canMove(this.x-Game.playerSize,this.y )) {
				this.x-=Game.playerSize;
			}
			else if(this.x>Game.player2.x&&this.canMove(this.x+Game.playerSize,this.y )) {
				this.x+=Game.playerSize;
			}
			else if(this.y>Game.player2.y&&this.canMove(this.x,this.y-Game.playerSize )) {
				this.y-=Game.playerSize;
			}
			else if(this.y>Game.player2.y&&this.canMove(this.x,this.y+Game.playerSize )) {
				this.y+=Game.playerSize;
			}
		}
		else if(this.playerID==2&&this.life>1&&Game.p1Lose==true&&this.intersects(Game.player1)){
			
			Game.player1.setLife(1);
			this.life-=1;
			Game.p1Lose=false;
			Game.player1.enterIndefectible();
			
			if(this.x<Game.player1.x&&this.canMove(this.x-Game.playerSize,this.y )) {
				this.x-=Game.playerSize;
			}
			else if(this.x>Game.player1.x&&this.canMove(this.x+Game.playerSize,this.y )) {
				this.x+=Game.playerSize;
			}
			else if(this.y>Game.player1.y&&this.canMove(this.x,this.y-Game.playerSize )) {
				this.y-=Game.playerSize;
			}
			else if(this.y>Game.player1.y&&this.canMove(this.x,this.y+Game.playerSize )) {
				this.y+=Game.playerSize;
			}
		}
	}

	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}



	public int getLife() {
		return life;
	}



	public void setLife(int life) {
		this.life = life;
	}



	public synchronized boolean isImmune() {
		return immune;
	}



	public synchronized void setImmune(boolean immune) {
		this.immune = immune;
	}
	



	public int getStartX() {
		return startX;
	}



	public void setStartX(int startX) {
		this.startX = startX;
	}



	public int getStartY() {
		return startY;
	}



	public void setStartY(int startY) {
		this.startY = startY;
	}


	public int getUseImageNum() {
		return useImageNum;
	}



	public void setUseImageNum(int useImageNum) {
		this.useImageNum = useImageNum;
	}



	public boolean isMoving() {
		return isMoving;
	}



	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

}
