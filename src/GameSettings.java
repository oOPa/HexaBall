public class GameSettings
{
	private String theStadiumIndex;
	private String theTime;
	private String goalKeepersOn;
	
	private String theLeftPlayerType;
	private String theLeftPlayerColor;
	private String theLeftPlayerNumber;
	
	private String theRightPlayerType;
	private String theRightPlayerColor;
	private String theRightPlayerNumber;
	
	public String getStadiumIndex()
	{
		return theStadiumIndex;
	}
	
	public String getTime()
	{
		return theTime;
	}
	
	public String GoalKeepersOn()
	{
		return goalKeepersOn;
	}
	
	public String getLeftPlayerType()
	{
		return theLeftPlayerType;
	}

	public String getLeftPlayerColor()
	{
		return theLeftPlayerColor;
	}

	public String getLeftPlayerNumber()
	{
		return theLeftPlayerNumber;
	}
	
	public String getRightPlayerType()
	{
		return theRightPlayerType;
	}

	public String getRightPlayerColor()
	{
		return theRightPlayerColor;
	}

	public String getRightPlayerNumber()
	{
		return theRightPlayerNumber;
	}

	
	public void setStadiumIndex(String stadium)
	{
		theStadiumIndex = stadium;
	}
	
	public void setTime(String time)
	{
		theTime = time;
	}
	
	public void setGoalKeepers(String state)
	{
		goalKeepersOn = state;
	}
	
	public void setLeftPlayerType(String playerType)
	{
		theLeftPlayerType = playerType;
	}

	public void setLeftPlayerColor(String playerColor)
	{
		theLeftPlayerColor = playerColor;
	}

	public void setLeftPlayerNumber(String leftPlayerNumber)
	{
		theLeftPlayerNumber = leftPlayerNumber;
	}
	
	public void setRightPlayerType(String playerType)
	{
		theRightPlayerType = playerType;
	}

	public void setRightPlayerColor(String color)
	{
		theRightPlayerColor = color;
	}

	public void setRightPlayerNumber(String number)
	{
		theRightPlayerNumber = number;
	}
}
