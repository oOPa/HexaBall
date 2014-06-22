import java.awt.Rectangle;

public abstract class AbstractStadiumFactory 
{
	Rectangle rect;
	

	public Rectangle getRect()
	{
		return rect;
	}
	
	public void setRect(Rectangle stadiumRect) 
	{
		rect = stadiumRect;
	}
	
	abstract FieldLines createFieldLines();
	abstract Field createField();
	abstract Goal createGoal();
}