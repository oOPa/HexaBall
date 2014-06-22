import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ColoredField implements Field 
{
	Color theColor;
	
	@Override
	public void draw(Object o, Rectangle rect) 
	{
		Graphics2D g2d = (Graphics2D) o;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(theColor);
		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);					
	}
	
	public void setColor(Color color)
	{
		theColor = color;
	}
}
