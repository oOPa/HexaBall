import java.awt.Point;

public abstract class PlayerModel 
{
	abstract int acceleration();
	abstract int stamina();
	abstract int shootPower();
	
	final int maxSpeed = 10;
	final int continuousMoveCycleMax = 50;
	
	double theCurrentSpeed = 0;
	int continuousMoveCycle = 0;
	long lastMoveTime;
	
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;	
	private boolean kick;
	
	public void setKick(boolean state)
	{
		kick = state;
	}

	public void setMoveUp(boolean state)
	{
		moveUp = state;
	}

	public void setMoveDown(boolean state)
	{
		moveDown = state;
	}

	public void setMoveLeft(boolean state)
	{
		moveLeft = state;
	}

	public void setMoveRight(boolean state)
	{
		moveRight = state;
	}
	
	public boolean getMoveUp()
	{
		return moveUp;
	}

	public boolean getMoveDown()
	{
		return moveDown;
	}
	
	public boolean getMoveLeft()
	{
		return moveLeft;
	}

	public boolean getMoveRight()
	{
		return moveRight;
	}

	public boolean getKick()
	{
		return kick;
	}

	public double currentSpeed()
	{
		return theCurrentSpeed;
	}
	
	public void increaseSpeed()
	{
		if (theCurrentSpeed < maxSpeed) {
			theCurrentSpeed += 0.1 * acceleration();
			if (theCurrentSpeed > maxSpeed)
				theCurrentSpeed = maxSpeed;
		}
	}

	public void decreaseSpeed(boolean stopCompletely)
	{
		if (stopCompletely) {
			theCurrentSpeed = 0;
			return;
		}
		theCurrentSpeed -= 0.2 * acceleration();
		if (theCurrentSpeed < 1) theCurrentSpeed = 1;
	}
	
	
	public int kick()
	{
		return shootPower() *  (continuousMoveCycleMax - continuousMoveCycle) / continuousMoveCycleMax;
	}
	
	public Point move(Point pt)
	{
		boolean exp = moveUp | moveDown | moveLeft | moveRight;
		
		if (exp == true)
		{
			if (continuousMoveCycle + 1 < continuousMoveCycleMax)
				continuousMoveCycle += 1;			
				increaseSpeed();
		} else {
			if (continuousMoveCycle > 0)
				continuousMoveCycle -= 1;			
				decreaseSpeed(false);
		}
		
		
		double xd = theCurrentSpeed * (continuousMoveCycleMax - continuousMoveCycle) / continuousMoveCycleMax + 1;
		if (moveLeft == false && moveRight == false)
			xd = 0;
		else if (moveLeft == true)
			xd *= -1;
		
		double yd = theCurrentSpeed * (continuousMoveCycleMax - continuousMoveCycle) / continuousMoveCycleMax + 1;
		if (moveUp == false && moveDown == false)
			yd = 0; 
		else if (moveUp  == true)
			yd *= -1;
		
		Point newPoint = new Point();
		newPoint.setLocation(pt.x + xd, pt.y + yd);

		return newPoint;
	}
}
