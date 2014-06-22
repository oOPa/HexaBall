import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.activity.InvalidActivityException;


public class SetupController implements Observer 
{
	SetupModel theModel;
	SetupView theView;
	User theLeftUser;
	User theRightUser;
	
	SetupController(SetupModel model, SetupView view)
	{
		if (null == model || null == view) throw new RuntimeException("Invalid Argument!");
		theModel = model;
		theView = view;

		theView.attach(this);
	}

	private void initializeGame()
	{
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
		
		GameController gameController = new GameController(gameModel, gameView);
		
		GameSettings gameSettings = new GameSettings();
		gameSettings.setStadiumIndex(theModel.getItemValue(1));
		gameSettings.setGoalKeepers(theModel.getItemValue(2));
		gameSettings.setTime(theModel.getItemValue(3));
		
		gameSettings.setLeftPlayerType(theModel.getItemValue(4));
		gameSettings.setLeftPlayerColor(theModel.getItemValue(5));
		gameSettings.setLeftPlayerNumber(theModel.getItemValue(6));
		
		gameSettings.setRightPlayerType(theModel.getItemValue(7));
		gameSettings.setRightPlayerColor(theModel.getItemValue(8));
		gameSettings.setRightPlayerNumber(theModel.getItemValue(9));
		
		gameController.init(gameSettings);
		gameView.init();
		//System.out.println("Start Game!");
		
		User leftPlayer = new User("Left User");
		User rightPlayer = new User("Right Player");
				
		try {
			leftPlayer.setKey(User.UserKeyType.UserKeyLeft, KeyEvent.VK_A);
			leftPlayer.setKey(User.UserKeyType.UserKeyRight, KeyEvent.VK_D);
			leftPlayer.setKey(User.UserKeyType.UserKeyUp, KeyEvent.VK_W);
			leftPlayer.setKey(User.UserKeyType.UserKeyDown, KeyEvent.VK_S);
			leftPlayer.setKey(User.UserKeyType.UserKeyHit, KeyEvent.VK_J);
			gameController.setLeftUser(leftPlayer);
			
			rightPlayer.setKey(User.UserKeyType.UserKeyLeft, KeyEvent.VK_LEFT);
			rightPlayer.setKey(User.UserKeyType.UserKeyRight, KeyEvent.VK_RIGHT);
			rightPlayer.setKey(User.UserKeyType.UserKeyUp, KeyEvent.VK_UP);
			rightPlayer.setKey(User.UserKeyType.UserKeyDown, KeyEvent.VK_DOWN);
			rightPlayer.setKey(User.UserKeyType.UserKeyHit, KeyEvent.VK_1);
			gameController.setRightUser(rightPlayer);
		} catch (InvalidActivityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  gameView.setPreferredSize(new Dimension(400, 500));
		  gameView.setVisible(true);
		  gameController.start();
	}
	
	void setLeftUser(User user) 
	{
		theLeftUser = user;
	}
	
	void setRightUser(User user)
	{
		theRightUser = user;
	}

	User getLeftUser()
	{
		return theLeftUser;
	}
	
	User getRightUser()
	{
		return theRightUser;
	}

	private void checkKeyType(User.UserKeyType keyType, boolean leftUser)
	{
		if (keyType != User.UserKeyType.UserKeyInvalid) {
			switch (keyType) {
			case UserKeyUp:
				theView.focusAbove(leftUser);
				break;
			case UserKeyDown:
				theView.focusBelow(leftUser);
				break;
			case UserKeyLeft:
			case UserKeyRight:
				{
					Pair<Integer, String> retval;
					
					retval = theView.getCurrentItemInfo(leftUser);
					
					if (keyType == User.UserKeyType.UserKeyLeft)
					{
						theModel.decreaseValue(retval.first(), retval.second());
					}
					else
					{
						theModel.increaseValue(retval.first(), retval.second());
					}
					
					theView.updateCurrentItemValue(leftUser, theModel.getItemValue(retval.first()));
				}
				break;
			case UserKeyHit:
				theView.applyAction(leftUser);
				break;
			default:
				break;
			}
		}
	}
	
	public void update(Observable o, Object arg)
	{
		int key = (int) arg;
		User.UserKeyType keyType = null;
		
		if (key == 0xFFFFFFFF) {
			initializeGame();
			return;
		}
		
		keyType = theLeftUser.getKey(key);
		checkKeyType(keyType, true);
		keyType = theRightUser.getKey(key);
		checkKeyType(keyType, false);
	}
}
	
