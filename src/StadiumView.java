import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class StadiumView extends JComponent 
{
	private static final long serialVersionUID = 1L;
	private AbstractStadiumFactory stadiumFactory = null;
	private Field field = null;
	private FieldLines fieldLines = null;
	private Goal goals = null;
	
	StadiumView(String stadiumType) 
	{
		if (stadiumType.equals("Stadium - 1"))
			stadiumFactory = new Stadium1Factory();
		else if (stadiumType.equals("Stadium - 2"))
			stadiumFactory = new Stadium2Factory();
		else
			stadiumFactory = new Stadium3Factory();
		
		field = stadiumFactory.createField();
		fieldLines = stadiumFactory.createFieldLines();
		goals = stadiumFactory.createGoal();	
	}
	

	public void paint(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g);		
		
		field.draw((Object) g2d, getStadiumRect());
		fieldLines.draw((Object) g2d, getStadiumRect());
		goals.draw((Object) g2d, getStadiumRect());
	}
	
	public Point getBallPosition()
	{
		return new Point(415, 215);
	}
	
	public Rectangle getStadiumRect()
	{
		return new Rectangle(20 + 5, 20 + 5 , 800, 400);
	}
		
}
