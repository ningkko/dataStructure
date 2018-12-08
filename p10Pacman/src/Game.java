import java.util.ArrayList;
import javax.swing.JFrame;

public class Game{

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
	
	
	public static void main(String[] args) {
		
		int playerNumber=2;
		new Game(800,600,"Super Dophine Bepop",32,playerNumber);
	}
	
	
	
	public Game(int worldWidth, int worldHeight, String worldName, int playerSize, int playerNumber) {
		
		Game.playerSize=playerSize;
		
		ArrayList<Player> players=new ArrayList<Player>();
		
		Game.playerStep=4;
		Game.monsterStep=2;
		
		player1=new Player(50,100,1);
		players.add(player1);
		if(playerNumber>1) {
			
			player2=new Player(400,100,2);
			players.add(player2);
		}

		
		gameWorld=new World(players,worldWidth,worldHeight,worldName,Game.playerSize);
		
		
		JFrame frame=new JFrame();
		
		frame.setTitle(gameWorld.worldName);
		frame.add(gameWorld);
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

}