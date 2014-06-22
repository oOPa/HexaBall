import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class TexturedField implements Field 
{
	private Image theImage = null;
	
	public void setTexture(Image image)
	{
		theImage = image;
	}
	
	public void draw(Object o, Rectangle rect) 
	{
		Graphics2D g2d = (Graphics2D) o;
		
		g2d.drawImage(theImage, rect.x, rect.y, rect.width, rect.height, null);
	}

}
	