import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {
	
	
	public int width;
	public int height;
	/**
	 * a list to store which positions are walls
	 */
	public Wall[][] walls;
	
	/**
	 * A list of food
	 */
	public ArrayList<Food> food;
	
	/**
	 * monsters
	 */
	public ArrayList<Monster> monsters;
	
	
	public Map(String mapPath, int playerSize) {
		
		try{
			// read in map
			BufferedImage map=ImageIO.read(getClass().getResource(mapPath));
			this.width=map.getWidth();
			this.height=map.getHeight();

			walls=new Wall[width][height];

			food=new ArrayList<Food>();

			monsters=new ArrayList<Monster>();

			// read the color of each pixel in the map
			for(int mapX=0;mapX<this.width;mapX++) {
				for(int mapY=0;mapY<this.height;mapY++) {
			
					int colorValue =map.getRGB(mapX, mapY);					
					//System.out.printf("colorValue: %08x\n",colorValue);
					
					// if the pixel if black, it's a wall
					if(colorValue==0xFF000000) {
						
						walls[mapX][mapY]= new Wall(mapX,mapY);
					}
					//else if the position on the map is a red tile,
					else if(colorValue==0xFFFF0000) {
						
						//move player 1 to the position
						Game.player1.x=mapX*playerSize;
						Game.player1.y=mapY*playerSize;
					}
					// if purple
					else if(colorValue==0xFFFF00FF) {
						
						// move player2 there
						Game.player2.x=mapX*playerSize;
						Game.player2.y=mapY*playerSize;
					}
					// if blue. then it's a monster
					else if(colorValue==0xFF0000FF) {
						
						monsters.add(new Monster(mapX,mapY));
					}
					// else it's food
					else {
						
						food.add(new Food(mapX,mapY));

					}
					
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawMap(Graphics g) {
		
		//draw walls	
		for(int x =0; x <width; x++) {
			for(int y=0; y<height; y++) {
				if (walls[x][y]!=null){
					walls[x][y].drawWall(g);
				}
			}
		}
		
		//draw food
		
		for(Food f : food) {
			f.drawFood(g);
		}
		
		for (Monster m : monsters) {
			m.drawMonster(g);
		}
		
	}
	
	/**
	 * Since I created monsters inside map, this function is for moving monsters in World.
	 */
	public void moveMosters() {
		
		for(Monster m: monsters) {
			m.move();
		}
	}
	
	public void detectLose() {
		
		for (Monster m:monsters) {
			
			if(Math.abs(Game.player1.getX()-m.getX())<20&&
				Math.abs(Game.player1.getY()-m.getY())<20) {
				
				Game.p1Lose=true;
			}
			
			if(Math.abs(Game.player2.getX()-m.getX())<20&&
					Math.abs(Game.player1.getY()-m.getY())<20) {
					
					Game.p2Lose=true;
				}
		}
	}
	

}
