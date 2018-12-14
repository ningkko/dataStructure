import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends Rectangle{

	
	private Spirit spirit;
	
	/**
	 * what is it?
	 */
	private int type;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Food( int x , int y) {
		
		
		spirit = null;
		
		if(Math.random()<0.03) {
			
			double chance=Math.random();
			
			if(chance<0.33) {
				this.type=1;
				spirit=new Spirit("/food/apple.png");
			}else if(chance>=0.33&&chance<0.66) {
				this.type=2;
				spirit=new Spirit("/food/pear.png");
			}else {
				this.type=3;
				spirit=new Spirit("/food/orange.png");
			}
		
		}else {
			this.type=0;

		}
		setBounds(x*Game.playerSize,y*Game.playerSize,Game.playerSize/4, Game.playerSize/4);
	
	}
	
	public void drawFood(Graphics g) {
		
		if(this.type==0) {
			
			g.setColor(Color.GREEN);
			g.fillRect(x+13, y+13, 6,6);
			
		}else {
			
			g.drawImage(spirit.getImg(),x+6,y+6,Game.playerSize*3/4,Game.playerSize*3/4,null);

		}
		
	}

	public Spirit getSpirit() {
		return spirit;
	}

	public void setSpirit(Spirit spirit) {
		this.spirit = spirit;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
