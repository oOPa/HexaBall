import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class GameController extends Thread implements KeyEventDispatcher 
{
	private GameView theView = null;
	private GameModel theModel = null;
	
	private User theLeftUser = null;
	private User theRightUser = null;
	
	private PlayerModel leftPlayer;
	private PlayerModel rightPlayer;
	
	final int leftPlayerDefaultX = 280;
	final int leftPlayerDefaultY = 210;
	
	final int rightPlayerDefaultX = 540;
	final int rightPlayerDefaultY = 210;
	
	private Point leftPt = new Point(leftPlayerDefaultX, leftPlayerDefaultY);
	private Point rightPt = new Point(rightPlayerDefaultX, rightPlayerDefaultY);
	
	
	GameController(GameModel model, GameView view)
	{
		theView = view;
		theModel = model;
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	void init(GameSettings settings)
	{		
		if (settings.getLeftPlayerType().equals("Defender"))
			leftPlayer = new DefenderPlayerModel();
		else if (settings.getLeftPlayerType().equals("Midfielder"))
			leftPlayer = new MidfielderPlayerModel();
		else
			leftPlayer = new ForwardPlayerModel();

		if (settings.getRightPlayerType().equals("Defender"))
			rightPlayer = new DefenderPlayerModel();
		else if (settings.getRightPlayerType().equals("Midfielder"))
			rightPlayer = new MidfielderPlayerModel();
		else
			rightPlayer = new ForwardPlayerModel();

		theView.setLeftPlayerNumber(settings.getLeftPlayerNumber());
		theView.setLeftPlayerColor(settings.getLeftPlayerColor());

		theView.setRightPlayerNumber(settings.getRightPlayerNumber());
		theView.setRightPlayerColor(settings.getRightPlayerColor());

		
		if (settings.GoalKeepersOn().contains("ON")) 
		{
			theView.createGoalKeepers();
		}
		
		theView.setTimeLeft(settings.getTime());
		theModel.setTimeLimit(settings.getTime());
		theModel.startCountDown();

		theView.setStadium(settings.getStadiumIndex());
	}
	
	void setLeftUser(User user)
	{
		theLeftUser = user;
	}
	
	void setRightUser(User user)
	{
		theRightUser = user;
	}	
	

	private boolean checkGoalKeeper(GoalKeeperView goalKeeperView, BallView ballView)
	{
		Dimension ballSize = ballView.getSize();
		Point ballLocation = ballView.getLocation();
		
		if ((ballLocation.x > goalKeeperView.getX()) && (ballLocation.x < goalKeeperView.getX() + goalKeeperView.getWidth())) {
			if (ballLocation.getY() == (goalKeeperView.getY() + goalKeeperView.getHeight())) return false;
			if ((ballLocation.getY() + ballSize.height) == (goalKeeperView.getY())) return false;			
		}
		
		return true;
	}
	
	private boolean checkPosition(Point currentPt, Dimension playerSize, BallView ballView, PlayerModel player)
	{
		Point ballLocation = ballView.getLocation();
		Dimension ballSize = ballView.getSize();
		StadiumView stadium = theView.getStadium();
		GoalKeeperView keeper1 = theView.getGoalKeeper(1);
		GoalKeeperView keeper2 = theView.getGoalKeeper(2);

		Rectangle rect = stadium.getStadiumRect();	
		
		if (currentPt.x > rect.x && (currentPt.x + playerSize.width < rect.x + rect.width) &&
				currentPt.y > rect.y && (currentPt.y + playerSize.height < rect.x + rect.height))
			{
				if (player.getMoveUp() && currentPt.y == (ballLocation.y + ballSize.height) && 
						((ballLocation.x + ballSize.width) > currentPt.x) && ((ballLocation.x) < currentPt.x + playerSize.width)) {
					
					if (ballLocation.y > rect.y) {
						if (!checkGoalKeeper(keeper1, ballView) || !checkGoalKeeper(keeper2, ballView)) return false;
						ballLocation.setLocation(ballLocation.x, ballLocation.y - 1);
						System.out.println("Ballx: " + ballLocation.x + " Bally: " + ballLocation.y);
						ballView.setLocation(ballLocation);
					} else { 
						return false;
					}
				}
				
				if (player.getMoveDown() && (currentPt.y + playerSize.height == ballLocation.y) && 
						((ballLocation.x + ballSize.width) > currentPt.x) && ((ballLocation.x) < currentPt.x + playerSize.width)) {
					if (ballLocation.y < rect.y + rect.height) {
						if (!checkGoalKeeper(keeper1, ballView) || !checkGoalKeeper(keeper2, ballView)) return false;
						ballLocation.setLocation(ballLocation.x, ballLocation.y + 1);
						System.out.println("Ballx: " + ballLocation.x + " Bally: " + ballLocation.y);
						ballView.setLocation(ballLocation);
					} else { 
						return false;
					}
				}
				
				if (player.getMoveLeft() && currentPt.x == (ballLocation.x + ballSize.width) && 
						((ballLocation.y + ballSize.height) > currentPt.y) && ((ballLocation.y) < currentPt.y + playerSize.height)) {
					if (ballLocation.x > rect.x - ballSize.width) {
						if (!checkGoalKeeper(keeper1, ballView) || !checkGoalKeeper(keeper2, ballView)) return false;
						ballLocation.setLocation(ballLocation.x - 1, ballLocation.y);
						System.out.println("Ballx: " + ballLocation.x + " Bally: " + ballLocation.y);
						ballView.setLocation(ballLocation);
					} else { 
						return false;
					}
				}
				
				if (player.getMoveRight() && (currentPt.x + playerSize.width == ballLocation.x) && 
						((ballLocation.y + ballSize.height) > currentPt.y) && ((ballLocation.y) < currentPt.y + playerSize.height)) {
					if (ballLocation.x < rect.x + rect.width) {
						if (!checkGoalKeeper(keeper1, ballView) || !checkGoalKeeper(keeper2, ballView)) return false;
						ballLocation.setLocation(ballLocation.x + 1, ballLocation.y);
						System.out.println("Ballx: " + ballLocation.x + " Bally: " + ballLocation.y);
						ballView.setLocation(ballLocation);
					} else { 
						return false;
					}
				}
				return true;
			}
		return false;
	}
	
	public void run()
	{
		PlayerView leftPlayerView;
		PlayerView rightPlayerView;
		BallView ballView;
		StadiumView stadium;
		
		Point currentLeftPt;
		Point currentRightPt;
		int cycle = 0;
		boolean ballOutOfBorder = false;
		
		for (;;) {
			try {
				currentLeftPt = leftPlayer.move(leftPt);
				currentRightPt = rightPlayer.move(rightPt);				
				
				leftPlayerView = theView.getLeftPlayer();
				rightPlayerView = theView.getRightPlayer();
				ballView = theView.getBall();
				
				Dimension leftPlayerSize = leftPlayerView.getSize();
				Dimension rightPlayerSize = rightPlayerView.getSize();
				
				stadium = theView.getStadium();
				Rectangle rect = stadium.getStadiumRect();	
				
				if (cycle++ % 100 == 0)
				{
					int timeRemaining = theModel.timeRemaining();
					
					theView.setTimeLeft(String.format("%02d:%02d", (timeRemaining / 60) , (timeRemaining % 60)));
					
					if (timeRemaining == 0) {
						theView.endGame(theModel.getScore());
						return;
					}
					
					if (ballOutOfBorder == true) {
						if ((ballView.getY() > rect.height * 3 / 10) && (ballView.getY() < rect.height * 7 / 10))
						{
							if (ballView.getX() > rect.getWidth())
								theModel.leftScored();
							else
								theModel.rightScored();
							
							theView.showScore(theModel.getScore());
							ballView.setLocation(stadium.getBallPosition());	
							leftPt.setLocation(leftPlayerDefaultX, leftPlayerDefaultY);
							rightPt.setLocation(rightPlayerDefaultX, rightPlayerDefaultY);
							leftPlayerView.setLocation(leftPlayerDefaultX, leftPlayerDefaultY);
							rightPlayerView.setLocation(rightPlayerDefaultX, rightPlayerDefaultY);
							ballOutOfBorder = false;
							continue;
						} else {
							ballView.setLocation(stadium.getBallPosition());
						}
						ballOutOfBorder = false;
					}
					
					if (ballView.getX() > rect.getWidth() || ballView.getX() < rect.x)
					{
						ballOutOfBorder = true;
					}
					
				}
				
				
				
				if (checkPosition(currentLeftPt, leftPlayerSize, ballView, leftPlayer))
				{
					leftPlayerView.setLocation(currentLeftPt);
					leftPt = currentLeftPt;
				}

				if (checkPosition(currentRightPt, rightPlayerSize, ballView, rightPlayer))
				{					
					rightPlayerView.setLocation(currentRightPt);
					rightPt = currentRightPt;
				}
				
				Thread.sleep(10);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	

	private void setModelAction(PlayerModel playerModel, User.UserKeyType userKey, boolean state)
	{
		switch (userKey)
		{
		case UserKeyLeft:
			playerModel.setMoveLeft(state);
			break;
		case UserKeyRight:
			playerModel.setMoveRight(state);
			break;					
		case UserKeyUp:
			playerModel.setMoveUp(state);
			break;			
		case UserKeyDown:
			playerModel.setMoveDown(state);
			break;
		case UserKeyHit:
			playerModel.setKick(state);
			break;
		default:
			break;			
		}	
	}
	
	private void keyPressed(PlayerModel playerModel, User.UserKeyType userKey)
	{
		setModelAction(playerModel, userKey, true);
	}
	
	private void keyReleased(PlayerModel playerModel, User.UserKeyType userKey)
	{
		setModelAction(playerModel, userKey, false);
	}
	
	@Override
	public boolean dispatchKeyEvent(final KeyEvent e) 
	{
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			User.UserKeyType leftUserKey = theLeftUser.getKey(e.getKeyCode());
			User.UserKeyType rightUserKey = theRightUser.getKey(e.getKeyCode());
			
			keyPressed(leftPlayer, leftUserKey);
			keyPressed(rightPlayer, rightUserKey);
		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			User.UserKeyType leftUserKey = theLeftUser.getKey(e.getKeyCode());
			User.UserKeyType rightUserKey = theRightUser.getKey(e.getKeyCode());

			keyReleased(leftPlayer, leftUserKey);
			keyReleased(rightPlayer, rightUserKey);
		}
		
		return false;
	}
}
