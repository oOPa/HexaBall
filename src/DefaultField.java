import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DefaultField implements Field 
{
	@Override
	public void draw(Object o, Rectangle rect)
	{
		Graphics2D g2d = (Graphics2D) o;
		g2d.setStroke(new BasicStroke(5));

		for (int i = 0; i < 20; ++i) {
			if (i % 2 == 0)
				g2d.setColor(new Color(0x33, 0x44, 0x33));
			else
				g2d.setColor(new Color(0x11, 0x55, 0x33));				
			g2d.fillRect(rect.x + i * (rect.width/20), rect.y, rect.width/20, rect.height);		
		}
		
	}
}

