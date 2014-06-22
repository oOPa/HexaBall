import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class PlayerView extends JPanel  
{
	private static final long serialVersionUID = 7L;
	Color theColor = Color.BLACK;
	Integer theNumber;
	double locationX;
	double locationY;
	
	PlayerView(int number, Color color)
	{
		setBackground(new Color(0,0,0,0));
		theNumber = number;
		theColor = color;
	}
	
	public void setLocation(Point pt)
	{
		super.setLocation(pt);

		locationX = pt.getX();
		locationY = pt.getY();
	}
	
	 public void setColor(Color color)
	 {
		 theColor = color;
	 }
	 
	 public void paint(Graphics g) 
	 {
	   final int r = 15;
	   final int x = 15;
	   final int y = 15;

	   super.paint(g);
	   
	   final Graphics2D g2d = (Graphics2D) g;     
	   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	   g2d.setColor(theColor);
		
	   Polygon s = new Polygon();
	   int i;
	   
	   for(i = 0; i < 6; i++) 
	   {
		   s.addPoint((int)(x + r*Math.cos(i*2*Math.PI/6 + Math.PI/6)), (int) (y + r*Math.sin(i*2*Math.PI/6 + Math.PI/6)));
	   }
		  
	   g2d.fillPolygon(s);
	   g2d.setColor(Color.WHITE);
	   g2d.drawString(theNumber.toString(), x/2, y + x/3);  
	 }
}
