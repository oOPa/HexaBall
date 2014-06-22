import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameModel extends Thread  {

	private int theTimeLimit;
	private int theTimeRemaining;
	private int rightScoreCount;
	private int leftScoreCount;
	
	public void rightScored()
	{
		++rightScoreCount;
	}

	public void leftScored()
	{
		++leftScoreCount;
	}
	
	public int timeLimit()
	{
		return theTimeLimit;	
	}
	
	public int timeRemaining()
	{
		return theTimeRemaining;
	}
	
	public String getScore()
	{
		return String.format("Left: %d - Right: %d", leftScoreCount, rightScoreCount);
	}
	
	public void setTimeLimit(String timeLimit)
	{
		String timeRegex = "([0-9][0-9]):([0-5][0-9])";
		Pattern pattern = Pattern.compile(timeRegex);
		Matcher matcher = pattern.matcher(timeLimit);
		
		if (matcher.matches()) {
		    String minutes = matcher.group(1);
		    String seconds = matcher.group(2);		    
		    theTimeLimit = Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds);
		}		
	}
	
	public void startCountDown()
	{
		theTimeRemaining = theTimeLimit;
		start();
	}
	
	@SuppressWarnings("deprecation")
	public void resetCoundDown()
	{
		theTimeRemaining = 0;
		stop();
	}
	
	public void run()
	{
		while (theTimeRemaining > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			--theTimeRemaining;
		}
	}
}
