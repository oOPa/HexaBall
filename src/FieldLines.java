import java.awt.Rectangle;

public abstract class FieldLines
{
	Rectangle rect;
	
	public void setBorders(double x, double y, double h, double w)
	{
		rect.setRect(x, y, w, h);
	}
	
	public Rectangle getBorders()
	{
		return rect;
	}
	
	public abstract void draw(Object o, Rectangle rectangle);
}
