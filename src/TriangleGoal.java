import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TriangleGoal implements Goal 
{
	@Override
	public void draw(Object o, Rectangle rect) 
	{
		int width = rect.width;
		int height = rect.height;
		int baseLeft = rect.x;
		int baseTop = rect.y;
	    
		Graphics2D g2d = (Graphics2D) o;
		g2d.setColor(Color.DARK_GRAY);
		
		g2d.setStroke(new BasicStroke(5.0f));
		
		g2d.drawLine(baseLeft, (baseTop + height/ 10 * 3), baseLeft / 5 * 3, (baseTop + height/ 10 * 5));
		g2d.drawLine(baseLeft, (baseTop + height/ 10 * 7), baseLeft / 5 * 3, (baseTop + height/ 10 * 5));
		
		g2d.drawLine(width + baseLeft, (baseTop + height/ 10 * 3), width + baseLeft / 5 * 7, (baseTop + height/ 10 * 5));
		g2d.drawLine(width + baseLeft, (baseTop + height/ 10 * 7), width + baseLeft / 5 * 7, (baseTop + height/ 10 * 5));
	}
}