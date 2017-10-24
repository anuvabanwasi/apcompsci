import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * Represents Stripe on US Flag
 * @author Anuva
 *
 */
public class Stripe {
	public Color color;
	public double x;
	public double y;
	public double width;
	public double height;

	/**
	 * Constructor creates stripe with coordinates specified at x,y having stripe height and width and color c
	 * @param c Color of stripe
	 * @param x x-coordinate of stripe
	 * @param y y-coordinate of stripe
	 * @param stripeWidth - width of stripe 
	 * @param stripeHeight - height of stripe
	 */
	public Stripe(Color c, double x, double y, double stripeWidth, double stripeHeight) {
		color = c;
		this.x = x;
		this.y = y;
		this.width = stripeWidth;
		this.height = stripeHeight;
	}
	
	/**
	 * Draw stripe of specified color at x, y having specified width and height
	 * @param g
	 */
	public void paintStripe(Graphics2D g) {
		g.setColor(color);
		g.fill(new Rectangle2D.Double(x, y, width, height));
	}
}
