import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * This is where the two dice are draw
 * @author Linda Scoon
 *
 */
public class DiceComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private DiceFace redDie;
	private DiceFace blackDie;
	private int noSides;
	private int rdieSize;// red die
	private int rxTop;
	private int bdieSize;// black die
	private int bxTop;
	private int rdiceValue;
	private int bdiceValue;
	private Color red;
	private Color black;

	/**
	 * 
	 */
	public DiceComponent() {

		rdieSize = 200;
		rxTop = 350;
		bdieSize = 200;
		bxTop = 50;
		//default dice values
		setRdiceValue(4);
		setBdiceValue(3);
		noSides = 6;
		red = Color.RED;
		black = Color.BLACK;
		redDie = new DiceFace(rdieSize, rxTop, noSides, rdiceValue, red);
		blackDie = new DiceFace(bdieSize, bxTop, noSides, bdiceValue, black);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		redDie.draw(g2);
		blackDie.draw(g2);
	}

	public int getRdiceValue() {
		return rdiceValue;
	}

	public void setRdiceValue(int rdiceValue) {
		this.rdiceValue = rdiceValue;
	}

	public int getBdiceValue() {
		return bdiceValue;
	}

	public void setBdiceValue(int bdiceValue) {
		this.bdiceValue = bdiceValue;
	}

	public int getNoSides() {
		return noSides;
	}

	public void setNoSides(int noSides) {
		this.noSides = noSides;
	}

	public DiceFace getRedDie() {
		return redDie;
	}

	public DiceFace getBlackDie() {
		return blackDie;
	}

}
