import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

	
public class ColoredFieldLines extends FieldLines 
{	
	private Color theColor = Color.WHITE;

	public void setColor(Color color)
	{
		theColor = color;
	}
	
	public void draw(Object o, Rectangle rect)
	{
		int width = rect.width;
		int height = rect.height;
		int baseLeft = rect.x;
		int baseTop = rect.y;
		int radius = width / 10;
		int stroke = 5;
		
		Graphics2D g2d = (Graphics2D) o;
		
		g2d.setColor(theColor);
		g2d.setStroke(new BasicStroke(stroke));
		
		g2d.drawLine(baseLeft + width / 2, baseTop, baseLeft + width / 2, baseTop  + height);
		g2d.drawRect(baseLeft + 2 , baseTop + 2, width - stroke, height - stroke);
		
		Arc2D circle = new Arc2D.Double(baseLeft + width/2 - radius / 2, baseTop + height/2 - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);		
		g2d.draw(circle);
		
		g2d.drawRect(baseLeft, baseTop + (height / 10), width / 100 * 18, height / 5 * 4);
		g2d.drawRect(width + baseLeft - (width / 100 * 18), baseTop + (height / 10), width / 100 * 18, height / 5 * 4);
		
		g2d.drawRect(baseLeft, baseTop + (height / 10 * 3), width / 100 * 6, height / 5 * 2);
		g2d.drawRect(width + baseLeft - (width / 100 * 6), baseTop + (height / 10 * 3), width / 100 * 6, height / 5 * 2);		
	}
}
