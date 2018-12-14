import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
public class World extends Canvas implements Runnable, KeyListener{

	/**
	 * Eclipse asked me to include this
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * is the game running?
	 */
	private boolean isRunning =false;
	/**
	 * World size
	 */
	public int worldWidth, WorldHeight;
	
	/**
	 * game threads
	 */
	private Thread thread;
	
	/**
	 * worldName of this world
	 */
	private String worldName;
	
	/**
	 * players
	 */
	public static ArrayList<Player> players;
	Player player1;
	Player player2;

	
	/**
	 * Where should the players move?
	 */
	private String direction1;
	private String direction2;
	
	/**
	 * world map
	 */
	public static Map map;
	

	/**
	 * painter
	 */
	private Graphics g;
	
	/**
	 * A tinny world
	 */
	public World(ArrayList<Player> players,int worldWidth,int worldHeight,String worldName,int playerSize){
		/**
		 * Name this world
		 */
		this.setWorldName(worldName);
		
		this.WorldHeight=worldHeight;
		this.worldWidth=worldWidth;
		// set world size
		this.setPreferredSize(new Dimension(this.worldWidth,this.WorldHeight));

		addKeyListener(this);
		
		
		World.players=players;
		
		player1=players.get(0);
		
		if(players.size()==2) {
			player2=players.get(1);
		}
				
		this.direction1="";
		this.direction2="";
		
		World.map=new Map(Game.gameLevel,playerSize);
		
		
	}

	/**
	 * Starts the game
	 */
	public synchronized void startGame() {
		
		if(isRunning) {
			
			return;
			
		}else {
			
			isRunning=true;
			thread=new Thread(this);
			thread.start();
		}
	}
	
	/**
	 * ends the game
	 */
	public synchronized void endGame() {
		
		if(!isRunning) {
			return;
		}else {
		
			isRunning=false;
			
			try {				
				thread.join();				
			}catch(InterruptedException e) {
				e.printStackTrace();
				
			}
		}
	}
	

	/**
	 * draw the world
	 */
	private void draw() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		
		// world
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.worldWidth, this.WorldHeight);
		
		//players
		for(Player p:World.players){
			p.draw(g);
		}
		
		//map
		map.drawMap(g);

		bs.show();
		g.dispose();
		
	}
	
	@Override
	public void run() {
		
		/**
		 * how many frames per second?
		 */
		int fps=0;
		
		double timer=System.currentTimeMillis();
		/**
		 * Time recorded last time
		 */
		long previousTime=System.nanoTime();
		/**
		 * target frames per sec
		 */
		double targetFPS=60.0;
		/**
		 * use delta to keep fps around target regardless to the flow of System.nanoTime
		 */
		double delta = 0;
		
		/**
		 * for changing images
		 */
		int frameP1 = 0,frameP2=0;
		int frameMonster=0;
		
		while(isRunning) {
			

			
			// get current time using nanotime();
			long currentTime=System.nanoTime();
			//update delta using nano sec as unit
			delta+=(currentTime-previousTime)*targetFPS/1e9;
			//update time
			previousTime=currentTime;
			
			while(delta>=1) {
				
				this.draw();
				
				//SWITCH IMAGES
				if(Game.player1.isMoving()) {
					frameP1++;
				}
				
				if(Game.player2.isMoving()) {
					frameP2++;
				}
				
				if (frameP1%21==0) {
					Game.player1.setUseImageNum(1);
				}else if (frameP1%40==0) {
					Game.player1.setUseImageNum(2);
					frameP1=0;
				}
				
				if (frameP2%21==0) {
					Game.player2.setUseImageNum(1);
				}else if (frameP2%40==0) {
					Game.player2.setUseImageNum(2);
					frameP1=0;
				}

				if(frameMonster==21) {
					
					for(Monster m: Map.monsters) {
						m.setUseImageNum(1);
					}
					
					
				}else if(frameMonster==40) {

					for(Monster m: Map.monsters) {
						m.setUseImageNum(2);
					}
					
					frameMonster=0;
				}
				frameMonster++;	
				
				// DETECT LOSE
				if(Game.lose()) {
				
					// game over
					this.drawGameOver();
					
					//pause for a sec
					try 
				    {
					   Thread.sleep(2000);
				    } 
				    catch(Throwable e) 
				    {
				    	System.out.println(e.getMessage()); 
				    }
					
					endGame();
					
				}
				
				//DETECT WIN
				
				Game.checkWin();
				
				
				//PLAYER ACTIONS
				this.movePlayers();
				
				
				for(Player p: players) {
					p.isTouched();
					p.eat();
					p.saveFriend();
				}

				//MONSTER ACTION
				this.moveMosters();
				
				//LOOP VARIANTS
				fps++;
				delta--;
				
			}
			if(System.currentTimeMillis()-timer>=1000) {
				fps=0;
				timer+=1000;
			}
		}
		
		endGame();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		//player1	
		if(!Game.p1Lose) {

			//I use direction instead of calling move functions directly because so we can make the character move smoothly
			if(e.getKeyCode()==KeyEvent.VK_A) {
				
				this.direction1="l";
				player1.setMoving(true);
				
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				
				this.direction1="r";
				player1.setMoving(true);

			}
			if(e.getKeyCode()==KeyEvent.VK_W) {
				
				this.direction1="u";
				player1.setMoving(true);

			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				
				this.direction1="d";
				player1.setMoving(true);

			}
			
		}else {
			
			this.direction1="";
		}
		
		//player2
		
		if(!Game.p2Lose) {
			
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				
				this.direction2="r";
				player2.setMoving(true);

			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				
				this.direction2="l";
				player2.setMoving(true);

			}
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				
				this.direction2="u";
				player2.setMoving(true);

			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				
				this.direction2="d";
				player2.setMoving(true);

			}
			
		}else {
			
			this.direction2="";
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		//player1
		if(e.getKeyCode()==KeyEvent.VK_A) {
			this.direction1="";
			player1.setMoving(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_D) {
			this.direction1="";
			player1.setMoving(false);

		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			this.direction1="";
			player1.setMoving(false);

		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			this.direction1="";
			player1.setMoving(false);

		}
		
		
		//player2
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			this.direction2="";
			player2.setMoving(false);
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			this.direction2="";
			player2.setMoving(false);

		}
		else if (e.getKeyCode()==KeyEvent.VK_UP) {
			this.direction2="";
			player2.setMoving(false);

		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			this.direction2="";
			player2.setMoving(false);

		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	
	}
	
	
	public void movePlayers() {
		
		for(Player p: players) {
			p.wrap();
		}
		
		//p1
		if(this.direction1.equals("l")) {
			this.player1.moveLeft(player1.getStep());
		}
		else if (this.direction1.equals("r")) {
			this.player1.moveRight(player1.getStep());
		}
		else if (this.direction1.equals("u")) {
			this.player1.moveUp(player1.getStep());
		}
		else if (this.direction1.equals("d")) {
			this.player1.moveDown(player1.getStep());
		}
		
		//p2
		if(this.direction2.equals("l")) {
			this.player2.moveLeft(player2.getStep());
		}
		else if (this.direction2.equals("r")) {
			this.player2.moveRight(player2.getStep());
		}
		else if (this.direction2.equals("u")) {
			this.player2.moveUp(player2.getStep());
		}
		else if (this.direction2.equals("d")) {
			this.player2.moveDown(player2.getStep());
		}
	}

	
	public void moveMosters() {
		
		for(Monster m: Map.monsters) {
			m.move();
		}
	}
	
	public  void drawGameOver() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		
		
		Spirit s=new Spirit("/system/gameover.png");
		
		g.drawImage(s.getImg(),Game.gameWorld.worldWidth/2,Game.gameWorld.WorldHeight/2,400,400,null);	
		g.dispose();
		
	}
	
	
	public void drawLevelUp() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		
		
		Spirit s=new Spirit("/system/levelup.png");
		
		g.drawImage(s.getImg(),Game.gameWorld.worldWidth/2,Game.gameWorld.WorldHeight/2,400,400,null);	
		
		g.dispose();
	}
	

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public String getWorldName() {
		return this.worldName;
	}

}
