import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Java Swing application that displays a US flag to scale. 
 * Flag specifications are outlined in http://www.usflag.org/flag.specs.html
 * The flag can be resized to desired dimensions
 * @author Anuva
 */
public class Flag extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Paints Components in Frame that appears on the screen. Calls Methods that paints the Union, Stripes and Stars
	 * 
	 * @param g Graphics context
	 *            
	 */

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Store width of parent panel so that when paint() is invoked, resizing of flag can occur relative to the current width of the panel
		double width = super.getSize().getWidth(); 
		
		// Store height of parent panel so that when paint() is invoked, resizing of flag can occur relative to the current height of the panel
		double height = super.getSize().getHeight();

		// Paints union on the flag
		paintUnion(g2, width, height);

		// Paints stripes on the flag
		paintStripes(g2, width, height);

		// Paints stars on the flag
		paintStars(g2, width, height);
	}

	/**
	 * Paints Union on the flag with color, width and height as specified by US Flag Specs - http://www.usflag.org/flag.specs.html 
	 * @param g Graphics2D context
	 * @param width Represents the width of the flag
	 * @param height Represents the height of the flag
	 */

	private void paintUnion(Graphics2D g, double width, double height) {

		// Set union width as a ratio of the flag width
		double unionWidth = FlagUtil.getUnionWidth(width);
		
		// Set union height as a ratio of the flag height
		double unionHeight = FlagUtil.getUnionHeight(height);

		// Paint Union
		Color blue = new Color(0, 40, 104);
		
		Union u = new Union(blue, 0, 0, unionWidth, unionHeight);
		u.paintUnion(g);
	}

	/**
	 * Paints Stripes on the flag with color, width and height as specified by US Flag Specs - http://www.usflag.org/flag.specs.html 
	 * 
	 * @param g Graphics2D context
	 * @param width Represents the width of the flag
	 * @param height Represents the height of the flag
	 *            
	 */

	private void paintStripes(Graphics2D g, double width, double height) {
		// Set union width as a ratio of the flag width
		double unionWidth = FlagUtil.getUnionWidth(width);
		
		// Set stripe height as a ratio of the flag height
		double stripeHeight = FlagUtil.getStripeHeight(height);

		// 13 alternating red and white stripes on the US flag
		for (int i = 0; i < FlagConstants.STRIPES; i++) {
			
			// Get stripe color, alternates between red and white
			Color color = FlagUtil.getStripeColor(i);

			// Get x coordinate of stripe as a ratio of union width
			double x = FlagUtil.getStripeX(i, unionWidth);
			
			// Get y coordinate of stripe as a ratio of flag height
			double y = FlagUtil.getStripeY(i, height);

			// Get stripe width as ratio of flag width
			double stripeWidth = FlagUtil.getStripeWidth(i, width, unionWidth);

			// Paint stripe
			Stripe s = new Stripe(color, x, y, stripeWidth, stripeHeight);
			s.paintStripe(g);
		}
	}

	/**
	 * Paints Stars on the flag as specified by US Flag Specs - http://www.usflag.org/flag.specs.html . 
	 * Alternates between 5 rows of 6 stars each and 4 rows of 5 stars each for a total of 50 stars (5*6+4*5 = 50)
	 * 
	 * @param g2 Graphics2D context
	 * @param width Represents the width of the flag
	 * @param height Represents the width of the flag
	 *            
	 */

	private void paintStars(Graphics2D g2, double width, double height) {
		
		// x coordinate of the center of first star
		int centerX = FlagUtil.getCenterX(width);
		
		// y coordinate of the center of first star
		int centerY = FlagUtil.getCenterY(height);

		// x coordinate of the space between stars
		int spaceX = FlagUtil.getSpaceX(width);
		
		// y coordinate of the space between stars
		int spaceY = FlagUtil.getSpaceY(height);

		// radius of each star as a ratio of height of flag
		double radius = FlagUtil.getRadius(height);
		
		Color white = new Color(255,255,255);

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 6; col++) {
				// Draw a row of 6 stars placed equally apart. x and y coordinate of each star is as follows - 
				// Each star is positioned relative to the center coordinates of the first star
				// First star is at centerX, centerY
				// Second star is at centerX + 2 * spaceX, centerY + 2 * spaceY i.e the x coordinate of center of the second star is centerX of first star + 2 times the horizontal space between stars, the y-coordinate of center of second star is centerY of first star + 2 times the vertical space between stars
				
				// Paint star of radius at desired x, y
				Star s = new Star(white, centerX + col * 2 * spaceX, centerY + row * 2 * spaceY, radius);
				s.paintStar(g2);
			}
		}

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 5; col++) {
				// Draw a row of 5 stars placed equally apart. x and y coordinate of each star is as follows -  
				// Each star is positioned relative to the center coordinates of the first star
				// First star is at centerX+spaceX, centerY+spaceY, since the first star in the rows of the 5 stars is offset from the left edge of the union by spaceX and spaceY on the x and y axis respectively
				// Second star is at centerX + spaceX + 2 * spaceX, i.e the center of the second star is x coordinate of center of first star + 2 times the horizontal space between stars, the y-coordinate of center of second star is centerY of first star + 2 times the vertical space between stars
				
				// Paint star of radius at desired x, y
				Star s = new Star(white, centerX + spaceX + col * 2 * spaceX, centerY + spaceY + row * 2 * spaceY,
						radius);
				s.paintStar(g2);
			}
		}
	}

	/**
	 * Main method of the Application. Creates the JFrame, adds the Flag panel to it and sets the frame to visible and resizable
	 * 
	 * @param args String
	 *            
	 */

	public static void main(String[] args) {
		JFrame frame = new JFrame("Star Spangled Banner Flag");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.white);
		frame.setSize(760, 400);
		//instantiate the flag panel
		Flag panel = new Flag();
		//get the container from the frame
		Container c = frame.getContentPane();
		//add the panel to the container so the frame now has the Flag Panel
		c.add(panel);
		//set the frame to visible and resizable since the flag is part of the frame at this point
		frame.setVisible(true);
		frame.setResizable(true);
	}
}