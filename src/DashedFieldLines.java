import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

	
	public class DashedFieldLines extends FieldLines 
	{	
		public void draw(Object o, Rectangle rect)
		{
			int width = rect.width;
			int height = rect.height;
			int baseLeft = rect.x;
			int baseTop = rect.y;
			int radius = width / 10;
		    float dash[] = { 10.0f };

			Graphics2D g2d = (Graphics2D) o;
			
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			
			g2d.drawLine(baseLeft + width / 2, baseTop, baseLeft + width / 2, baseTop  + height);
			g2d.drawRect(baseLeft, baseTop, width, height);
			
			Arc2D circle = new Arc2D.Double(baseLeft + width/2 - radius / 2, baseTop + height/2 - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);		
			g2d.draw(circle);
			
			g2d.drawRect(baseLeft, baseTop + (height / 10), width / 100 * 18, height / 5 * 4);
			g2d.drawRect(width + baseLeft - (width / 100 * 18), baseTop + (height / 10), width / 100 * 18, height / 5 * 4);
			
			g2d.drawRect(baseLeft, baseTop + (height / 10 * 3), width / 100 * 6, height / 5 * 2);
			g2d.drawRect(width + baseLeft - (width / 100 * 6), baseTop + (height / 10 * 3), width / 100 * 6, height / 5 * 2);	
		}
	}