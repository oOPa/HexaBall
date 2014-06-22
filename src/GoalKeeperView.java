import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
	
public class GoalKeeperView extends JPanel 
{		
	private static final long serialVersionUID = 1L;

	GoalKeeperView(int x, int y)
	{
		setLocation(x, y);
	}
				
	 public void paint(Graphics g) 
	 {
	   super.paint(g);
		
	   final Graphics2D g2d = (Graphics2D) g;     
	   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	   g2d.setColor(Color.BLACK);
		
	   Point pt = getLocation();
	   g2d.fill(new Rectangle(pt.x, pt.y, 30, 50));
	 }
}
