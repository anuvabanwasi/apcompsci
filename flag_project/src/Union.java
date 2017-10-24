import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents Union on US Flag
 * @author Anuva
 *
 */
public class Union {
	public Color color;
	public double x;
	public double y;
	public double width;
	public double height;
	
	/**
	 * Constructor to set color, width, height, x and y coordinates of union
	 * @param c Color of union
	 * @param x x-coordinate of rectangle representing union
	 * @param y y-coordinate of rectangle representing union
	 * @param width width of rectangle representing union
	 * @param height height of rectangle representing union
	 */
	Union(Color c, double x, double y, double width, double height){
		this.color = c;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Draw union of specified color as a rectangle starting at x, y having specified width and height
	 * Since Union starts at the origin (top left of frame, the x and y coordinates are 0
	 * @param g
	 */
	public void paintUnion(Graphics2D g) {
		g.setColor(color);
		g.fill(new Rectangle2D.Double(0, 0, width, height));
	}
}
