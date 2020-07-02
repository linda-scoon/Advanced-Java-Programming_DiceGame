/**
 * This is where all the game logic is controlled and the dice is updated
 * @author Linda Scoon
 *
 */
public class PlayGame {
	private int[] high;
	private int[] low;
	private int seven;
	private Dice dice;
	private DiceComponent dieComp;
	private int rdiceValue;//red dice value
	private int bdiceValue;//black dice value
	private int noSides;
	private Player player;
	private int balance;
	private int sevenOdds;
	private int hiLoOdds;
	private boolean gameOver;
	private String lowBalance;
	private String result;
	private int highScore;
	private String endMsg;

	/**
	 * 
	 * @param frame
	 */
	public PlayGame() {
		this.dieComp = new DiceComponent();
		gameOver = false;
		endMsg = "Game Over";
		highScore = 100;
		hiLoOdds = 1;
		sevenOdds = 4;
		seven = 7;
		noSides = 6;
		rdiceValue = 6;
		bdiceValue = 6;
		high = new int[] { 8, 9, 10, 11, 12 };
		low = new int[] { 2, 3, 4, 5, 6 };
		player = new Player();
		dice = new Dice(noSides);
	}

	public void start(int bet, String game) {

		boolean match = false;
		balance = player.getBalance();

		if (bet <= balance && balance < highScore) {

			updateDice();

			//high game
			if (game == "high") {
				for (int diceTotal : high) {
					if ((rdiceValue + bdiceValue) == diceTotal) {
						balance = balance + (bet * hiLoOdds);
						match = true;
						result = "you won: £" + (bet * hiLoOdds) + "\n";
						break;
					}
					checkGameOver();
				}
			} else
			//low game
			if (game == "low") {
				for (int diceTotal : low) {
					if ((rdiceValue + bdiceValue) == diceTotal) {
						balance = balance + (bet * hiLoOdds);
						match = true;
						result = "you won: £" + (bet * hiLoOdds) + "\n";
						break;
					}
					checkGameOver();
				}

			} else
			//seven game
			if (game == "seven") {
				if ((rdiceValue + bdiceValue) == seven) {
					balance = balance + (bet * sevenOdds);
					match = true;
					result = "you won: £" + (bet * sevenOdds);
				}
				checkGameOver();
			}

			//check if bet was unsuccessful and deduct bet
			if (!match) {
				balance -= bet;
				result = "Sorry you lost this round";
				checkGameOver();
			}

		} //check for low balance
		else if (bet > balance && balance != 0) {
			lowBalance = "You only have £" + balance + ". Make a smaller bet";
			System.out.println(lowBalance);
		}

		//set the players balance
		player.setBalance(balance);
		checkGameOver();

		//for debugging
		System.out.println(player.getBalance());
	}

	private void checkGameOver() {
		//check for end game
		if (balance == 0 || player.getBalance() >= highScore) {
			gameOver = true;

			//for debugging
			System.out.println(endMsg);

		}

	}

	public void updateDice() {

		//throw dice
		dice.throwDice();
		rdiceValue = dice.getValue();

		dice.throwDice();
		bdiceValue = dice.getValue();

		//set dice faces
		dieComp.getRedDie().setDiceFace(rdiceValue);
		dieComp.getBlackDie().setDiceFace(bdiceValue);
		dieComp.repaint();

		//for debugging
		System.out.println(rdiceValue + " ||" + bdiceValue);
	}

	public DiceComponent getDieComp() {
		return dieComp;
	}

	public int getRdiceValue() {
		return rdiceValue;
	}

	public int getBdiceValue() {
		return bdiceValue;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public String getLowBalance() {
		return lowBalance;
	}

	public int getBalance() {
		return balance;
	}

	public String getResult() {
		return result;
	}

	public Player getPlayer() {
		return player;
	}

	public String getEndMsg() {
		return endMsg;
	}

}
