
/**
 * This method just keeps the players balance
 * @author Linda Scoon
 *
 */
public class Player {
	private int balance;

	/**
	 * 
	 */
	public Player() {
		setBalance(50);//eclipse did this, I like it so Ive kept it.
	}

	/**
	 * 
	 * @return balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

}
