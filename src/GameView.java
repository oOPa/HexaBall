import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class GameView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerView player1;
	private PlayerView player2;
	
	private GoalKeeperView keeper1;
	private GoalKeeperView keeper2;
	
	private String theStadium;
	private String theLeftPlayerNumber;
	private String theRightPlayerNumber;
	private String theLeftPlayerColor;
	private String theRightPlayerColor;
	
	private JComponent ball = null;
	private StadiumView stadium = null;
	private Point ballPosition = null;
	
	GameView() 
	{
		setSize(860, 460);
		setResizable(false);
		setLocationRelativeTo(null); 
	}


	private boolean goalKeepersOn = false;	
	
	void setTimeLeft(String limit)
	{
		setTitle("Time Left: " + limit);
	}
	
	void setStadium(String stadium)
	{
		theStadium = stadium;
	}

	void setLeftPlayerNumber(String leftPlayerNumber)
	{
		theLeftPlayerNumber = leftPlayerNumber;
	}
	
	void setRightPlayerNumber(String rightPlayerNumber)
	{
		theRightPlayerNumber = rightPlayerNumber;
	}

	void setLeftPlayerColor(String leftPlayerColor)
	{
		theLeftPlayerColor = leftPlayerColor;
	}
	
	void setRightPlayerColor (String rightPlayerColor)
	{
		theRightPlayerColor = rightPlayerColor;
	}
	
	void createGoalKeepers()
	{
		goalKeepersOn  = true;
	}
	
	public BallView getBall()
	{
		return (BallView) ball;
	}
	
	public PlayerView getLeftPlayer()
	{
		return player1;
	}
	
	public PlayerView getRightPlayer()
	{
		return player2;
	}
	
	public StadiumView getStadium()
	{
		return stadium;
	}
	
	public GoalKeeperView getGoalKeeper(int index)
	{
		switch (index)
		{
		case 1:
			return keeper1;
		case 2:
			return keeper2;
		default:
			return null;
		}
	}

	public void endGame(String score)
	{
		JOptionPane.showMessageDialog(null, "GAME OVER", "Score: " + score, JOptionPane.INFORMATION_MESSAGE);
	}
	

	public void showScore(String score)
	{
		JOptionPane.showMessageDialog(null, "Score has changed!", "Score: " + score, JOptionPane.INFORMATION_MESSAGE);		
	}
	
	
	private Color getPlayerColor(String scolor)
	{
		if (scolor.equals("Red")) {
			return Color.RED;
		} else if (scolor.equals("Yellow")) {
			return Color.YELLOW;
		} else if (scolor.equals("Green")) {
			return Color.GREEN;
		} else if (scolor.equals("Blue")) {
			return Color.BLUE;	
		} else if (scolor.equals("Black")) {
			return Color.BLACK;
		} else if (scolor.equals("Purple")) {
			return Color.PINK;
		} else {
			return Color.WHITE;
		}	
	}
	
	void init()
	{   
		stadium = new StadiumView(theStadium);
		stadium.setVisible(true);
	    final Dimension StadiumSize = new Dimension(800,450);
	    stadium.setPreferredSize(StadiumSize);
	    stadium.setSize(StadiumSize);
		ballPosition = stadium.getBallPosition();
	    
		ball = new BallView();
	    final Dimension size = new Dimension(20,20);
	    ball.setPreferredSize(size);
	    ball.setSize(size);
	    ball.setLocation(ballPosition);
	    ball.setVisible(true);
	    
	    Color player1Color = getPlayerColor(theLeftPlayerColor);
	    int player1Number = Integer.parseInt(theLeftPlayerNumber);
	    
		player1 = new PlayerView(player1Number, player1Color);
		final Dimension player1Size = new Dimension(30,30);
		player1.setPreferredSize(player1Size);
	    player1.setSize(player1Size);
	    player1.setLocation(280, 240);
	    player1.setVisible(true);
	    
	    Color player2Color = getPlayerColor(theRightPlayerColor);
	    int player2Number = Integer.parseInt(theRightPlayerNumber);
	    
		player2 = new PlayerView(player2Number, player2Color);
		final Dimension player2Size = new Dimension(30,30);
		player2.setPreferredSize(player2Size);
	    player2.setSize(player2Size);
	    player2.setLocation(600, 240);
	    player2.setVisible(true);
	    
	    if (goalKeepersOn) 
	    {
	    	keeper1 = new GoalKeeperView(35, 200);
	    	keeper2 = new GoalKeeperView(785, 200);

			final Dimension keeperSize= new Dimension(30,50);
			keeper1.setPreferredSize(keeperSize);
			keeper1.setSize(keeperSize);
			keeper2.setPreferredSize(keeperSize);
			keeper2.setSize(keeperSize);
			
	    	keeper1.setVisible(true);
	    	keeper2.setVisible(true);
	    	
	    	add(keeper1);
	    	add(keeper2);
	    }
	    
	    add(player2);
	    add(player1);
	    add(ball);
	    add(stadium);
	}
}
