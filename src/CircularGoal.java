import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

	public class CircularGoal implements Goal 
	{
		@Override
		public void draw(Object o, Rectangle rect) 
		{
			int width = rect.width;
			int height = rect.height;
			int baseLeft = rect.x;
			int baseTop = rect.y;
			int radius = 20;
		    
			Graphics2D g2d = (Graphics2D) o;
			g2d.setColor(Color.DARK_GRAY);
			
			Arc2D circle = new Arc2D.Double(baseLeft - radius / 2, (baseTop + height / 10 * 3) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
			g2d.fill(circle);

			circle = new Arc2D.Double(baseLeft - radius / 2, (baseTop + height / 10 * 7) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
			g2d.fill(circle);

			circle = new Arc2D.Double(baseLeft + width - radius / 2, (baseTop + height / 10 * 3) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
			g2d.fill(circle);
			
			circle = new Arc2D.Double(baseLeft + width - radius / 2, (baseTop + height / 10 * 7) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
			g2d.fill(circle);

			Arc2D back = new Arc2D.Double(baseLeft - radius, (baseTop + height / 10 * 3), radius, radius, 90, 90, Arc2D.OPEN);			
			g2d.draw(back);
			
			back = new Arc2D.Double(baseLeft - radius, (baseTop + height / 10 * 7) - radius, radius, radius, 180, 90, Arc2D.OPEN);			
			g2d.draw(back);
			
			g2d.drawLine(baseLeft - radius, (baseTop + height / 20 * 7), baseLeft - radius, (baseTop + height/ 20 * 13));
			
			back = new Arc2D.Double(width + baseLeft, (baseTop + height / 10 * 3), radius, radius, 90, -90, Arc2D.OPEN);			
			g2d.draw(back);
			
			back = new Arc2D.Double(width + baseLeft, (baseTop + height / 10 * 7) - radius, radius, radius, 270, 90, Arc2D.OPEN);			
			g2d.draw(back);
			
			g2d.drawLine(width + baseLeft + radius, (baseTop + height / 20 * 7), width + baseLeft + radius, (baseTop + height/ 20 * 13));
		}

	}