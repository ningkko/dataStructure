import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
public class World extends Canvas implements Runnable{

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
	public static int WORLDWIDTH=800, WORLDHEIGHT=600;
	
	/**
	 * game threads
	 */
	private Thread thread;
	
	/**
	 * worldName of this world
	 */
	public String worldName;
	
	/**
	 * player1
	 */
	public static Player player1;
	
	/**
	 * player2
	 */
	public static Player player2;
	
	/**
	 * Do you feel lonely?
	 */
	public boolean isTwoPlayers;
	
	/**
	 * A tinny world
	 */
	public World(ArrayList<Player> players){
		/**
		 * Name this world
		 */
		this.worldName="Super Dophine Bepop";
		
		// set world size
		this.setPreferredSize(new Dimension(World.WORLDWIDTH,World.WORLDHEIGHT));
	
		//add creatures
		World.player1=players.get(0);
		
		if(players.size()==1) {
			this.isTwoPlayers=false;
		}else {
			World.player2=players.get(1);
			this.isTwoPlayers=true;
		}
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
	 * 
	 * @param player1
	 */
	private void tick(Player player1) {
		player1.tick();
	}


	/**
	 * two friends!
	 * @param player1
	 * @param player2
	 */
	private void tick(Player player1, Player player2) {
		player1.tick();
		player2.tick();
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
		
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, World.WORLDWIDTH, World.WORLDHEIGHT);
		g.dispose();
		bs.show();
		
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
		
		while(isRunning) {
			
			// get current time using nanotime();
			long currentTime=System.nanoTime();
			//update delta using nano sec as unit
			delta+=(currentTime-previousTime)*targetFPS/1000000000;
			//update time
			previousTime=currentTime;
			
			while(delta>=1) {
				
				if(!this.isTwoPlayers) {
					this.tick(player1);
				}
				else {
					this.tick(player1,player2);
				}
				this.draw();
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

}
