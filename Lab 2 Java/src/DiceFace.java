
/**
 * This dice code was provided in the lab notes
 * It defines the dice faces
 *
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class DiceFace {
	// Holds the seven possible dot positions on a standard die
	private Ellipse2D.Double[] dots = new Ellipse2D.Double[7];
	private Color color;
	private Rectangle box;
	private int xLeft;
	private int yTop;
	private int side;
	private int diceValue;

	public DiceFace(int s, int x, int y, int v, Color c) {
		side = s; // dimension of dice face
		xLeft = x; // position
		yTop = y; // position
		diceValue = v; // pip value
		this.color = c;
	}

	public void setDiceFace(int v) {
		diceValue = v;
	}

	public void draw(Graphics2D g2) {
		box = new Rectangle(xLeft, yTop, side, side);
		makeDots();

		// Black background
		g2.setColor(color);
		g2.fill(box);

		// White dots on black
		g2.setColor(Color.WHITE);

		// Draw dots
		if (diceValue == 1)
			g2.fill(dots[0]);
		else if (diceValue == 2) {
			g2.fill(dots[1]);
			g2.fill(dots[2]);
		} else if (diceValue == 3) {
			g2.fill(dots[0]);
			g2.fill(dots[1]);
			g2.fill(dots[2]);
		} else if (diceValue == 4) {
			g2.fill(dots[1]);
			g2.fill(dots[2]);
			g2.fill(dots[3]);
			g2.fill(dots[4]);
		} else if (diceValue == 5) {
			g2.fill(dots[0]);
			g2.fill(dots[1]);
			g2.fill(dots[2]);
			g2.fill(dots[3]);
			g2.fill(dots[4]);
		} else if (diceValue == 6) {
			g2.fill(dots[1]);
			g2.fill(dots[2]);
			g2.fill(dots[3]);
			g2.fill(dots[4]);
			g2.fill(dots[5]);
			g2.fill(dots[6]);
		}
	}

	public void makeDots() {
		int w = side / 6; // dot width
		int h = side / 6; // dot height

		dots[0] = new Ellipse2D.Double(xLeft + (2.5 * side) / 6, yTop + (2.5 * side) / 6, h, w);

		dots[1] = new Ellipse2D.Double(xLeft + (3.75 * side) / 6, yTop + (3.75 * side) / 6, h, w);

		dots[2] = new Ellipse2D.Double(xLeft + (1.25 * side) / 6, yTop + (1.25 * side) / 6, h, w);

		dots[3] = new Ellipse2D.Double(xLeft + (1.25 * side) / 6, yTop + (3.75 * side) / 6, h, w);

		dots[4] = new Ellipse2D.Double(xLeft + (3.75 * side) / 6, yTop + (1.25 * side) / 6, h, w);

		dots[5] = new Ellipse2D.Double(xLeft + (1.25 * side) / 6, yTop + (2.5 * side) / 6, h, w);

		dots[6] = new Ellipse2D.Double(xLeft + (3.75 * side) / 6, yTop + (2.5 * side) / 6, h, w);
	}
}