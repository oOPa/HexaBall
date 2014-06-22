import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComponent;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observer;

public class SetupView extends JFrame implements KeyEventDispatcher 
{
	private static final long serialVersionUID = 1L;
	private JTextField txtStadium;
	private JTextField txtGoalKeeperState;
	private JTextField txtTime;
	private JTextField txtLeftPlayerType;
	private JTextField txtLeftPlayerUniformType;
	private JTextField txtLeftPlayerNumber;
	private JTextField txtRightPlayerType;
	private JTextField txtRightPlayerUniformType;
	private JTextField txtRightPlayerNumber;
	
	private JButton btnLeftPlayerReady;
	private JButton btnRightPlayerReady;
	
	private boolean disableLeftUserKeyEvents = false;
	private boolean disableRightUserKeyEvents = false;
	
	private JComponent leftUserFocus = null;
	private JComponent rightUserFocus = null;

	private LinkedList<JComponent> leftUserFocusGroup = new LinkedList<JComponent>();
	private LinkedList<JComponent> rightUserFocusGroup = new LinkedList<JComponent>();
	private Hashtable<JComponent, Integer> rightIndexValues = new Hashtable<JComponent, Integer>();
	private Hashtable<JComponent, Integer> leftIndexValues = new Hashtable<JComponent, Integer>();
	
	private LinkedList<Observer> observers = new LinkedList<Observer>();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupView frame = new SetupView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void attach(Observer observer)
	{
		observers.add(observer);
	}
	
	public boolean detach(Observer observer)
	{
		return observers.remove(observer);
	}	
	
	public void notifyObservers(int value)
	{
		if (observers.size() > 0) {
			Observer node = observers.getFirst();
			
			for (int i = 0; node != null && i < observers.size(); ++i) {
			    node.update(null, value);
			    node = observers.get(i);
			}
		}
	}
	
	void focusBelow(boolean leftUser)
	{
		if (leftUser == true) {
			if (disableLeftUserKeyEvents == true) return;
			
			leftUserFocus.setBackground(Color.WHITE);
			int index = leftUserFocusGroup.indexOf(leftUserFocus);
			
			++index;
			index %= leftUserFocusGroup.size();
			if (leftUserFocusGroup.get(index) == rightUserFocus) 
				++index;
			index %= leftUserFocusGroup.size();
			
			leftUserFocus = leftUserFocusGroup.get(index);
			leftUserFocus.setBackground(Color.BLUE);
		} else {
			if (disableRightUserKeyEvents == true) return;
			
			rightUserFocus.setBackground(Color.WHITE);
			int index = rightUserFocusGroup.indexOf(rightUserFocus);
			
			++index;
			index %= rightUserFocusGroup.size();
			if (rightUserFocusGroup.get(index) == leftUserFocus) 
				++index;
			index %= leftUserFocusGroup.size();
			
			rightUserFocus = rightUserFocusGroup.get(index);
			rightUserFocus.setBackground(Color.RED);
		}
	}

	void focusAbove(boolean leftUser)
	{
		if (leftUser == true) {
			if (disableLeftUserKeyEvents == true) return;

			leftUserFocus.setBackground(Color.WHITE);
			int index = leftUserFocusGroup.indexOf(leftUserFocus);
			
			--index;
			if (index < 0) index = leftUserFocusGroup.size() - 1;
			if (leftUserFocusGroup.get(index) == rightUserFocus) 
				--index;
			if (index < 0) index = leftUserFocusGroup.size() - 1;
			
			leftUserFocus = leftUserFocusGroup.get(index);
			leftUserFocus.setBackground(Color.BLUE);
		} else {
			if (disableRightUserKeyEvents == true) return;

			rightUserFocus.setBackground(Color.WHITE);
			int index = rightUserFocusGroup.indexOf(rightUserFocus);
			
			--index;
			if (index < 0) index = rightUserFocusGroup.size() - 1;
			if (rightUserFocusGroup.get(index) == leftUserFocus) 
				--index;
			if (index < 0) index = rightUserFocusGroup.size() - 1;
			
			rightUserFocus = rightUserFocusGroup.get(index);
			rightUserFocus.setBackground(Color.RED);
		}
	}

	Pair<Integer, String>  getCurrentItemInfo(boolean leftUser)
	{
		Integer componentIndex = 0;
		String componentValue = "";
		
		if (leftUser == true)
		{
			componentIndex = leftIndexValues.get(leftUserFocus);
			if (componentIndex != null)
			{
				componentValue = ((JTextField) leftUserFocus).getText();
			} 
		}
		else
		{
			componentIndex = rightIndexValues.get(rightUserFocus);
			if (componentIndex != null)
			{
				componentValue = ((JTextField) rightUserFocus).getText();
			} 			
		}
		
		return new Pair<Integer, String>(componentIndex, componentValue);
	}
	
	void updateCurrentItemValue(boolean leftUser, String componentValue)
	{
		if (leftUser == true)
		{			
			((JTextField) leftUserFocus).setText(componentValue);
		}
		else
		{
			((JTextField) rightUserFocus).setText(componentValue);		
		}
	}
	
	void applyAction(boolean leftUser)
	{
		if (leftUser == true)
		{
			if (leftUserFocus == btnLeftPlayerReady)
			{
				btnLeftPlayerReady.setEnabled(false);
				disableLeftUserKeyEvents = true;
			}
		}
		else 
		{
			if (rightUserFocus == btnRightPlayerReady)
			{
				btnRightPlayerReady.setEnabled(false);
				disableRightUserKeyEvents = true;

			}
		}
		
		if (!btnLeftPlayerReady.isEnabled() && !btnRightPlayerReady.isEnabled())
		{
			KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
			this.setVisible(false);
			notifyObservers(0xFFFFFFFF);
		}
	}

	void initializeLeftFocusGroup(JButton button)
	{
		leftUserFocusGroup.add(txtStadium);
		leftUserFocusGroup.add(txtGoalKeeperState);
		leftUserFocusGroup.add(txtTime);
		leftUserFocusGroup.add(txtLeftPlayerType);
		leftUserFocusGroup.add(txtLeftPlayerUniformType);
		leftUserFocusGroup.add(txtLeftPlayerNumber);
		leftUserFocusGroup.add(button);
		leftUserFocus = leftUserFocusGroup.get(0);
		leftUserFocus.setBackground(Color.BLUE);
	}
	
	void initializeRightFocusGroup(JButton button)
	{
		rightUserFocusGroup.add(txtStadium);
		rightUserFocusGroup.add(txtGoalKeeperState);
		rightUserFocusGroup.add(txtTime);
		rightUserFocusGroup.add(txtRightPlayerType);
		rightUserFocusGroup.add(txtRightPlayerUniformType);
		rightUserFocusGroup.add(txtRightPlayerNumber);
		rightUserFocusGroup.add(button);

		rightUserFocus = rightUserFocusGroup.get(1);
		rightUserFocus.setBackground(Color.RED);
	}
	
	void initializeLeftIndexValues()
	{
		leftIndexValues.put(txtStadium, 1);
		leftIndexValues.put(txtGoalKeeperState, 2);
		leftIndexValues.put(txtTime, 3);
		leftIndexValues.put(txtLeftPlayerType, 4);
		leftIndexValues.put(txtLeftPlayerUniformType, 5);
		leftIndexValues.put(txtLeftPlayerNumber, 6);		
	}
	
	void initializeRightIndexValues()
	{
		rightIndexValues.put(txtStadium, 1);
		rightIndexValues.put(txtGoalKeeperState, 2);
		rightIndexValues.put(txtTime, 3);
		rightIndexValues.put(txtRightPlayerType, 7);
		rightIndexValues.put(txtRightPlayerUniformType, 8);
		rightIndexValues.put(txtRightPlayerNumber, 9);		
	}
	
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getID() == KeyEvent.KEY_PRESSED) {
			notifyObservers(event.getKeyCode());
		}
		
		return false;
	}


	
	public SetupView() 
	{
		KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();  
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
		
		setSize(800, 500);
		setResizable(false);
		setLocationRelativeTo(null); 
		  
		JLabel lblNewLabel = new JLabel("Left Player");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblRightPlayer = new JLabel("Right Player");
		lblRightPlayer.setForeground(new Color(255, 0, 0));
		lblRightPlayer.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JButton button = new JButton("<");
		
		
		JButton button_1 = new JButton(">");
		
		JButton button_2 = new JButton("<");
		
		JButton button_3 = new JButton(">");
		
		JButton button_4 = new JButton("<");
		
		JButton button_5 = new JButton(">");
		
		JButton button_6 = new JButton(">");
		
		JButton button_7 = new JButton("<");
		
		JButton button_8 = new JButton(">");
		
		JButton button_9 = new JButton("<");
		
		JButton button_10 = new JButton(">");
		
		JButton button_11 = new JButton("<");
		
		JButton button_12 = new JButton("<");
		
		JButton button_13 = new JButton("<");
		
		JButton button_14 = new JButton("<");
		
		JButton button_15 = new JButton(">");
		
		JButton button_16 = new JButton(">");
		
		JButton button_17 = new JButton(">");
		
		btnLeftPlayerReady = new JButton("Left Player Ready!");
		
		btnRightPlayerReady = new JButton("Right Player Ready!");
		
		txtStadium = new JTextField();
		txtStadium.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtStadium.setEditable(false);
		txtStadium.setText("Stadium - 1");
		txtStadium.setHorizontalAlignment(SwingConstants.CENTER);
		txtStadium.setColumns(10);
		
		txtGoalKeeperState = new JTextField();
		txtGoalKeeperState.setText("Goal Keeper ON");
		txtGoalKeeperState.setHorizontalAlignment(SwingConstants.CENTER);
		txtGoalKeeperState.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtGoalKeeperState.setEditable(false);
		txtGoalKeeperState.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setText("01:00");
		txtTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		
		txtLeftPlayerType = new JTextField();
		txtLeftPlayerType.setText("Defender");
		txtLeftPlayerType.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeftPlayerType.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLeftPlayerType.setEditable(false);
		txtLeftPlayerType.setColumns(10);
		
		txtLeftPlayerUniformType = new JTextField();
		txtLeftPlayerUniformType.setText("Blue");
		txtLeftPlayerUniformType.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeftPlayerUniformType.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLeftPlayerUniformType.setEditable(false);
		txtLeftPlayerUniformType.setColumns(10);
		
		txtLeftPlayerNumber = new JTextField();
		txtLeftPlayerNumber.setText("2");
		txtLeftPlayerNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeftPlayerNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLeftPlayerNumber.setEditable(false);
		txtLeftPlayerNumber.setColumns(10);
		
		txtRightPlayerType = new JTextField();
		txtRightPlayerType.setText("Midfielder");
		txtRightPlayerType.setHorizontalAlignment(SwingConstants.CENTER);
		txtRightPlayerType.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtRightPlayerType.setEditable(false);
		txtRightPlayerType.setColumns(10);
		
		txtRightPlayerUniformType = new JTextField();
		txtRightPlayerUniformType.setText("Red");
		txtRightPlayerUniformType.setHorizontalAlignment(SwingConstants.CENTER);
		txtRightPlayerUniformType.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtRightPlayerUniformType.setEditable(false);
		txtRightPlayerUniformType.setColumns(10);
		
		txtRightPlayerNumber = new JTextField();
		txtRightPlayerNumber.setText("3");
		txtRightPlayerNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtRightPlayerNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtRightPlayerNumber.setEditable(false);
		txtRightPlayerNumber.setColumns(10);

		initializeLeftFocusGroup(btnLeftPlayerReady);
		initializeRightFocusGroup(btnRightPlayerReady);
		initializeLeftIndexValues();
		initializeRightIndexValues();
		
		JLabel lblHexaball = new JLabel("HEXABALL");
		lblHexaball.setFont(new Font("Tahoma", Font.PLAIN, 36));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(btnLeftPlayerReady, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRightPlayerReady, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addGap(75))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtLeftPlayerType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(button_11, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtLeftPlayerNumber, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtLeftPlayerUniformType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtGoalKeeperState, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(button_10, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(button_13, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtRightPlayerUniformType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(button_15, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(button_14, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtRightPlayerNumber, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(button_17, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblRightPlayer, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(button_12, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtRightPlayerType, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(button_16, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblHexaball))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtStadium)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(218)))))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblHexaball)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblRightPlayer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtStadium, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(txtGoalKeeperState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_4)
						.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_5))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_7)
									.addComponent(txtLeftPlayerType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_8)
									.addComponent(txtLeftPlayerUniformType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_9))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_10)
									.addComponent(txtLeftPlayerNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_11)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_12)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_16)
									.addComponent(txtRightPlayerType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_13)
									.addComponent(txtRightPlayerUniformType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_15))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_14)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_17)
									.addComponent(txtRightPlayerNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLeftPlayerReady, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRightPlayerReady, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		getContentPane().setLayout(groupLayout);
	}
}
