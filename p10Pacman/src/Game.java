import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Game extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * A world
	 */
	public static World gameWorld;

	
	/**
	 * Creatures
	 */
	public static Player player1;
	public static Player player2;
	
	/**
	 * width/height of player
	 */
	public static int playerSize;
	
	/**
	 * player speed
	 */
	public static int playerStep;
	
	/**
	 * Monster speed
	 */
	public static int monsterStep;
	

	/**
	 * level
	 */
	public static String gameLevel;
	

	/**
	 * painter
	 */
	public static Graphics g;
	
	
	/**
	 * if any of the players get caught by monsters
	 */
	public static boolean p1Lose, p2Lose;
	
	
	
	public static void main(String[] args) {
		
		int playerNumber=2;
		new Game(768/4*5,576/4*5,"Koutenn Madness Returns",32/4*5,playerNumber);
	}
	
	
	
	public Game(int worldWidth, int worldHeight, String worldName, int playerSize, int playerNumber) {
		
		
		Game.playerSize=playerSize;
		
		ArrayList<Player> players=new ArrayList<Player>();
		
		Game.playerStep=5;
		Game.monsterStep=2;
		
		player1=new Player(50,100,1);
		players.add(player1);
		if(playerNumber>1) {
			
			player2=new Player(400,100,2);
			players.add(player2);
		}
		
		// game status
		p1Lose=false;
		p2Lose=false;
		
		
		//start from map l1
		gameLevel="l1";
		
		gameWorld=new World(players,worldWidth,worldHeight,worldName,Game.playerSize);
		
		
		JFrame frame=new JFrame();
		
		frame.setTitle(gameWorld.getWorldName());
		frame.add(gameWorld);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		
		// and then we start living
		gameWorld.startGame();
		
	}
	
	
	/**
	 * @return how many?
	 */
	public int getplayerNumber() {
		return 2;
	}
	

	/**
	 * if all food is eaten, enter new level
	 */
		public static void checkWin() {
			
			if (World.map.food.size()<100) {
				
				Game.gameWorld.drawLevelUp();
				
				//pause for a sec
				try 
			    {
				   Thread.sleep(2000);
			    } 
			    catch(Throwable e) 
			    {
			    	System.out.println(e.getMessage()); 
			    }
			   
		   
		   //go next level
		   Game.nextLevel();
		}
	}
	
	public static boolean lose() {
		
		if (World.players.size()==2) {
			if(Game.p1Lose&&Game.p2Lose) {
				return true;
			}
		}else if(World.players.size()==1) {
			if(Game.p1Lose) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * manually enters a new level
	 */
	public static void nextLevel() {
		
		if(Game.gameLevel=="l1") {
			
			Game.gameLevel="l2";
			
		}
		else if(Game.gameLevel=="l1") {
			
			Game.gameLevel="l3";
			
		}
		else if(Game.gameLevel=="l3") {
			
			Game.gameLevel="l4";
			
		}
		

		World.map=new Map(Game.gameLevel,World.players.size());
	}
	
	
}