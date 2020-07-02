import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * main method
 * @author Linda Scoon
 *
 */
public class GameInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int height = 400;
		int width = 600;

		MainFrame frame = new MainFrame("**High_Low**");
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
