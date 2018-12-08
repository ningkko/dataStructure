import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game{

	/**
	 * god of painting
	 */
	Graphics g;
	/**
	 * A world
	 */
	World gameWorld;
	/**
	 * Creatures
	 */
	Player player1;
	Player player2;
	
	public static void main(String[] args) {
		new Game();
	}
	
	
	
	public Game() {
		
		/**
		 * Do we have creatures first or "world" first?
		 * 
		 */
		int playerNumber=this.getplayerNumber();
		
		/**
		 * might be too obvious an attempt
		 */
		ArrayList<Player> players=new ArrayList<Player>();
		
		if(playerNumber==1) {
			
			player1=new Player(World.WORLDWIDTH/2,World.WORLDHEIGHT/2,1);
			players.add(player1);
		}else {
			
			player1=new Player(World.WORLDWIDTH/4,World.WORLDHEIGHT/2,1);
			player2=new Player(World.WORLDWIDTH*3/4,World.WORLDHEIGHT/2,2);
			players.add(player1);
			players.add(player2);
		}

		
		//we have world after knowing about its citizens
		gameWorld=new World(players);
		
		// and then we observe
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
	 * God creates human-beings
	 * @return how many?
	 */
	public int getplayerNumber() {
		return 2;
	}

}