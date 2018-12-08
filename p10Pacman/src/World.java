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
	 * players
	 */
	ArrayList<Player> players;
	Player player1;
	Player player2;
	
	/**
	 * Where should the players move?
	 */
	private String direction1;
	private String direction2;
	
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
	
		this.players=players;
		
		player1=players.get(0);
		
		if(players.size()==2) {
			player2=players.get(1);
		}
		
		addKeyListener(this);
		
		this.direction1="";
		this.direction2="";
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
		
		Graphics g=bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, World.WORLDWIDTH, World.WORLDHEIGHT);
		for(Player p:this.players){
			p.draw(g);
		}
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
			delta+=(currentTime-previousTime)*targetFPS/1e9;
			//update time
			previousTime=currentTime;
			
			while(delta>=1) {
				
				this.movePlayers();
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

	@Override
	public void keyPressed(KeyEvent e) {
		
		//player1
		if(e.getKeyCode()==KeyEvent.VK_A) {
			this.direction1="l";
		}
		else if(e.getKeyCode()==KeyEvent.VK_D) {
			this.direction1="r";
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			this.direction1="u";
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			this.direction1="d";
		}
		
		
		//player2
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			this.direction2="r";
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			this.direction2="l";
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP) {
			this.direction2="u";
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			this.direction2="d";
		}
	}

	
	public void movePlayers() {
		
		//p1
		if(this.direction1.equals("l")&&this.player1.x>5) {
			this.player1.moveLeft();
		}
		else if (this.direction1.equals("r")&&this.player1.x<WORLDWIDTH-5) {
			this.player1.moveRight();
		}
		else if (this.direction1.equals("u")&&this.player1.y>5) {
			this.player1.moveUp();
		}
		else if (this.direction1.equals("d")&&this.player1.x<WORLDHEIGHT-5) {
			this.player1.moveDown();
		}
		
		//p2
		if(this.direction2.equals("l")&&this.player2.x>5) {
			this.player2.moveLeft();
		}
		else if (this.direction2.equals("r")&&this.player2.x<5) {
			this.player2.moveRight();
		}
		else if (this.direction2.equals("u")&&this.player2.y>5) {
			this.player2.moveUp();
		}
		else if (this.direction2.equals("d")&&this.player2.x<WORLDHEIGHT-5) {
			this.player2.moveDown();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		//player1
		if(e.getKeyCode()==KeyEvent.VK_A) {
			this.direction1="";
		}
		else if(e.getKeyCode()==KeyEvent.VK_D) {
			this.direction1="";
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			this.direction1="";
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			this.direction1="";
		}
		
		
		//player2
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			this.direction2="";
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			this.direction2="";
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP) {
			this.direction2="";
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			this.direction2="";
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
