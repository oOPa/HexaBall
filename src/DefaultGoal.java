import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

public class DefaultGoal implements Goal 
{
	public void draw(Object o, Rectangle rect)
	{
		int width = rect.width;
		int height = rect.height;
		int baseLeft = rect.x;
		int baseTop = rect.y;
		int radius = 14;
	    float dash[] = { 10.0f };
	    
		Graphics2D g2d = (Graphics2D) o;
		g2d.setColor(Color.BLACK);
		
		Arc2D circle = new Arc2D.Double(baseLeft - radius / 2, (baseTop + height/ 10 * 3) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
		g2d.fill(circle);

		circle = new Arc2D.Double(baseLeft - radius / 2, (baseTop + height/ 10 * 7) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
		g2d.fill(circle);

		circle = new Arc2D.Double(baseLeft + width - radius / 2, (baseTop + height/ 10 * 3) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
		g2d.fill(circle);
		
		circle = new Arc2D.Double(baseLeft + width - radius / 2, (baseTop + height/ 10 * 7) - radius / 2, radius, radius, 0, 360, Arc2D.CHORD);
		g2d.fill(circle);

		g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		
		g2d.drawLine(baseLeft, (baseTop + height/ 10 * 3), (baseLeft / 2), (baseTop + height/ 10 * 3));
		g2d.drawLine((baseLeft / 2), (baseTop + height/ 10 * 3), (baseLeft / 2), (baseTop + height/ 10 * 7));		
		g2d.drawLine(baseLeft, (baseTop + height/ 10 * 7), (baseLeft / 2), (baseTop + height/ 10 * 7));
		
		g2d.drawLine(baseLeft + width, (baseTop + height/ 10 * 3), (3 * baseLeft / 2) + width, (baseTop + height/ 10 * 3));
		g2d.drawLine((3 * baseLeft / 2)  + width, (baseTop + height/ 10 * 3), (3 *baseLeft / 2) + width, (baseTop + height/ 10 * 7));		
		g2d.drawLine(baseLeft + width, (baseTop + height/ 10 * 7), (3 * baseLeft / 2) + width, (baseTop + height/ 10 * 7));
	}
}