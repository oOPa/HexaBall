import java.awt.*;
import javax.swing.JComponent;


public class BallView extends JComponent {
	private static final long serialVersionUID = 2L;
	Color theColor = Color.BLACK;
	
	BallView()
	{
		setBackground(new Color(0,0,0,0));
	}
	
	 public void setColor(Color color)
	 {
		 theColor = color;
	 }
	 
	 public void paint(Graphics g)
	 {
		super.paint(g);
		 
	    final Graphics2D g2d = (Graphics2D) g;
	    
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(theColor);
		g2d.fillOval(0,  0,  20, 20);
	
	}
}
