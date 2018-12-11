import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spirit {

	private BufferedImage img;

	public Spirit(String path) {
		

		try
		{
			img=ImageIO.read(getClass().getResource(String.format(path)));

		}
		catch ( IOException e )
		{
		    e.printStackTrace();
		}
		
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}


}
