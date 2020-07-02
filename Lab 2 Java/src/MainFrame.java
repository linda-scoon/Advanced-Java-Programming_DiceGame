import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

/**
 * Controls all the components on the frame and an inner actionListener
 * @author Linda Scoon
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox betSelect;
	private JButton rollDicebtn;
	private JLabel text;
	private JRadioButton high;
	private JRadioButton low;
	private JRadioButton seven;
	private ButtonGroup gameSelect;
	private DiceComponent drawDie;
	private ActionListener listener = null;
	private boolean btnStatus;
	private int bet;
	private String gameType;
	private PlayGame play;
	private JLabel gameOverText;

	/**
	 * 
	 * @param title
	 */
	public MainFrame(String title) {
		super(title);

		// Default values
		bet = 0;
		gameType = null;
		play = new PlayGame();
		btnStatus = false;

		// Inner Class Listener
		class SelectListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == betSelect) {
					String bet = (String) betSelect.getSelectedItem();
					setBet(bet);
				}

				if (high.isSelected()) {
					gameType = "high";
					setGameType(gameType);

				} else if (seven.isSelected()) {
					gameType = "seven";
					setGameType(gameType);

				} else if (low.isSelected()) {
					gameType = "low";
					setGameType(gameType);
				}

				if (gameType != null && bet != 0) {//checking that selections have been made
					rollDicebtn.setEnabled(true);//enabling button

					if (e.getSource() == rollDicebtn) {
						btnStatus = true;
						startGame(btnStatus);
					}

				}
			}
		}
		// instatiating the listener
		listener = new SelectListener();

		manageLayout();
		this.pack();

	}

	/**
	 * Manages the layout
	 */
	public void manageLayout() {

		// diceFaces
		drawDie = play.getDieComp();
		add(drawDie, BorderLayout.CENTER);// component added with layout

		// radioButton
		JPanel rbtnPanel = new JPanel();
		rbtnPanel = createRadioBtn();

		// textfield
		JPanel textPanel = new JPanel();
		textPanel = createJLabel();

		// button
		JPanel btnPanel = new JPanel();
		btnPanel = createBtn();

		// combobox
		JPanel boxPanel = new JPanel();
		boxPanel = createComboBoxes();

		//gameOver panel
		JPanel gameEnd = new JPanel();
		gameOverText = new JLabel();
		gameEnd.add(gameOverText);

		// set a grid layout for the inner control panel
		JPanel innerControlPanel = new JPanel();
		innerControlPanel.setLayout(new GridLayout(1, 3));
		innerControlPanel.add(rbtnPanel);
		innerControlPanel.add(boxPanel);
		innerControlPanel.add(btnPanel);

		//put all elements into one control panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3, 1));
		controlPanel.add(innerControlPanel);
		controlPanel.add(textPanel);
		controlPanel.add(gameEnd);

		//create border
		Border innerBorder = BorderFactory.createTitledBorder("Make a selection and roll the dice");
		Border outterBorder = BorderFactory.createEmptyBorder(5, 10, 10, 10);
		controlPanel.setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

		add(controlPanel, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @return panel of combo boxes
	 */
	public JPanel createComboBoxes() {
		String[] bets = { "Select", "1", "5", "10" };
		betSelect = new JComboBox(bets);

		//add listener to checkboxes
		betSelect.addActionListener(listener);

		JPanel panel = new JPanel();
		panel.add(betSelect);

		return panel;
	}

	/**
	 * 
	 * @return panel of radio btns
	 */
	public JPanel createRadioBtn() {
		high = new JRadioButton("High");
		low = new JRadioButton("Low");
		seven = new JRadioButton("Seven");

		//add actionListeners
		high.addActionListener(listener);
		low.addActionListener(listener);
		seven.addActionListener(listener);

		// declare group btn
		gameSelect = new ButtonGroup();

		// add radio button to group so that only one can be selected at a time
		gameSelect.add(high);
		gameSelect.add(low);
		gameSelect.add(seven);

		// buttons have to be added separately into the JPanel
		JPanel panel = new JPanel();
		panel.add(high);
		panel.add(low);
		panel.add(seven);

		return panel;
	}

	/**
	 * 
	 * @return btn panel
	 */
	public JPanel createBtn() {
		rollDicebtn = new JButton("Roll Dice");
		rollDicebtn.setEnabled(false);

		//add listener to button
		rollDicebtn.addActionListener(listener);

		JPanel panel = new JPanel();
		panel.add(rollDicebtn);

		return panel;
	}

	/**
	 * 
	 * @return panel of the textLabel
	 */
	public JPanel createJLabel() {
		text = new JLabel();

		JPanel panel = new JPanel();
		panel.add(text);

		return panel;
	}

	/**
	 * Update the user of the game winnnings and balance
	 */
	public void updateJLabel() {
		if (play.getGameOver()) {
			text.setText(play.getResult() + ". Your Balance = " + play.getBalance());
			gameOverText.setText("***" + play.getEndMsg() + "***");
			rollDicebtn.setEnabled(false);//disabling button

		} else if (getBet() > play.getBalance()) {
			text.setText(play.getLowBalance() + " ");

		} else {
			text.setText(play.getResult() + ". Your Balance = " + play.getBalance() + " ");
		}
	}

	/**
	 * this method calls play.start() to start the game
	 * @param btnStatus
	 */
	public void startGame(boolean btnStatus) {

		if (btnStatus) {
			//pass the bet & game into play
			play.start(bet, gameType);
			updateJLabel();
		}
	}

	/**
	 * 
	 * @return bet
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * 
	 * @param bet
	 */
	public void setBet(String bet) {
		this.bet = Integer.parseInt(bet);
	}

	/**
	 * @return btnStatus
	 */
	public boolean isbtnStatus() {
		return btnStatus;
	}

	public void setBtnStatus(boolean btnStatus) {
		this.btnStatus = btnStatus;
	}

	/**
	 * @return the gameType
	 */
	public String getGameType() {
		return gameType;
	}

	/**
	 * @param gameType the gameType to set
	 */
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

}
