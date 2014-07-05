import javax.activity.InvalidActivityException;
import javax.swing.SwingUtilities;

public class Main 
{
	public static void main(String[] args) throws InterruptedException 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		        @SuppressWarnings("deprecation")
				@Override
		        public void run() 
		        {
		        	final int itemCount = 10;
		        	SetupModel setupModel = new SetupModel(itemCount);
		        	SetupView setupView = new SetupView();
		        		
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	
		        	SetupController setupController = new SetupController(setupModel, setupView);
		        	
		        	User leftPlayer = new User("Left User");
		    		User rightPlayer = new User("Right Player");
		    				
		    		try {
		    			leftPlayer.setKey(User.UserKeyType.UserKeyLeft, 'A');
		    			leftPlayer.setKey(User.UserKeyType.UserKeyRight, 'D');
		    			leftPlayer.setKey(User.UserKeyType.UserKeyUp, 'W');
		    			leftPlayer.setKey(User.UserKeyType.UserKeyDown, 'S');
		    			leftPlayer.setKey(User.UserKeyType.UserKeyHit, 'J');
		    			setupController.setLeftUser(leftPlayer);
		    			
		    			rightPlayer.setKey(User.UserKeyType.UserKeyLeft, 37);
		    			rightPlayer.setKey(User.UserKeyType.UserKeyRight, 39);
		    			rightPlayer.setKey(User.UserKeyType.UserKeyUp, 38);
		    			rightPlayer.setKey(User.UserKeyType.UserKeyDown, 40);
		    			rightPlayer.setKey(User.UserKeyType.UserKeyHit, 97);
		    			setupController.setRightUser(rightPlayer);
		    		} catch (InvalidActivityException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    		
		    		setupView.pack();
		    		setupView.setVisible(true);
		        }
		});
	}

}
