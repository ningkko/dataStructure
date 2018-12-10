import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spirit {

	private BufferedImage img;

	public Spirit(String path) {
		
		setImg(null);
		try
		{
		    setImg(ImageIO.read( new File(path)));

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
