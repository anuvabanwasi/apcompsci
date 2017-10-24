import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Draws a star based on Geometric & Trigonometry. To see more information on how a US flag star shape is determined see information on Pentagrams on wikipedia at https://en.wikipedia.org/wiki/Pentagram
 * and http://www.hyperflight.com/pentagon-construct.htm
 * @author Anuva
 *
 */
public class Star {
	private static final int COORDS_SIZE = 10; //A pentagram is formed by joining 10 points in a coordinate system
	private static final double PI = Math.PI;  //Value of PI is used to convert from degrees to radians
	
	public Color color;
	public int x;
	public int y;
	
	// Radius of circle circumscribing star
	public double R;
	
	/**
	 * Constructor sets x and y coordinates of center of star and radius
	 * @param c Color of star
	 * @param x x-coordinate of center of star
	 * @param y y-coordinate of center of star
	 * @param radius Radius of star
	 */
	Star(Color c, int x, int y, double radius){
		this.x = x;
		this.y = y;
		this.R = radius;
	}
	
	/**
	 * Draws a single star at specified x, y coordinates
	 * Calculates outer and inner coordinates of points of the pentagram representing the star
	 * Utilizes fill polygon to draw the star
	 * @param g2 GraphicsContext
	 */
	void paintStar(Graphics2D g2) {
		int xcoords[] = new int[COORDS_SIZE];
		int ycoords[] = new int[COORDS_SIZE];
		
		// Calculate inner radius of star based on outer radius
		double r = R*Math.sin(Math.toRadians(18)/Math.sin(Math.toRadians(54)));
		
		// Set x, y coordinates of points of pentagram lying on outer circle
		setOuterCoords(x, y, xcoords, ycoords, R);
		
		// Set x, y coordinates of points of pentagram lying on inner circle
		setInnerCoords(x, y, xcoords, ycoords, r);
		
		g2.setColor(Color.WHITE);
		
		// Fill Polygon 
		g2.fillPolygon(xcoords, ycoords, COORDS_SIZE);
	}
	
	/**
	 * Calculates x and y coordinates of five points of pentagram on inner circle
	 * Populate odd indices of arrays representing x and y coordinates of pentagram
	 * @param centerX x-coordinate of center of star
	 * @param centerY y-coordinate of center of star
	 * @param xcoords array representing x-coordinates of pentagram
	 * @param ycoords array representing y-coordinates of pentagram
	 * @param innerRadius radius of the inner circle
	 */
	private void setInnerCoords(int centerX, int centerY, int[] xcoords, int[] ycoords, double innerRadius) {
		int index = 1;
		for (int i = 0 ; i < 5; i = i + 1) {
			xcoords[index] = getXCoords(centerX, innerRadius, i, 3);
			ycoords[index] = getYCoords(centerY, innerRadius, i, 3);

			index = index + 2;
		}
	}
	
	/**
	 * Calculate x and y coordinates of five points of pentagram on outer circle
	 * Populate even indices of arrays representing x and y coordinates of pentagram
	 * @param centerX x-coordinate of center of star
	 * @param centerY y-coordinate of center of star
	 * @param xcoords array representing x-coordinates of pentagram
	 * @param ycoords array representing y-coordinates of pentagram
	 * @param outerRadius
	 */
	private void setOuterCoords(int centerX, int centerY, int[] xcoords, int[] ycoords, double outerRadius) {
		int index = 0;
		for (int i = 0 ; i < 5; i = i + 1) {
			xcoords[index] = getXCoords(centerX, outerRadius, i, 1);
			ycoords[index] = getYCoords(centerY, outerRadius, i, 1);
			
			index = index + 2;
		}
	}
	
	/**
	 * Compute y coordinates of pentagram for both inner and outer circle 
	 *
	 * A pentagram with center (0,0) has points on the circumference at 18, 90, 162, 234, and 306 degrees
	 * The first point starts with y coordinate on the circumference of the circle 18 degrees counter clockwise to the x axis
	 * Therefore, the y coordinate is equal to r*sin(18)
	 * The second point is at 90 degrees = 18 + 72. The rest of the points are at 162, 234 and 306 degrees
	 * 72 degrees = (2 * PI) / 5
	 * Similarly, for the inner circle, the points on the circumference of the inner circle are at 54, 126, 198, 270, and 342 degrees
	 * use of factor = 3 denotes inner circle and factor = 1 denotes outer circle
	 * since the top left is the origin the y-coodrdinate is computed by subtracting the distance of point from the x-axis from centerY		
		
	 * @param centerY - y-coordinate of center of circle
	 * @param radius - radius of circle circumscribing star
	 * @param i index of point on pentagram
	 * @param factor Used to calculate angle for inner and outer circle
	 * @return y coordinate of point on pentagram
	 */
	private int getYCoords(int centerY, double radius, int i, int factor) {
		return centerY - (int) (radius * Math.sin( PI/10 * factor + (2 * PI) / 5 * i));
	}
	
	/**
	 * Compute x coordinates for both inner and outer circle 
	 * A pentagram with center (0,0) has points on the circumference of the outer circle are at 18, 90, 162, 234, and 306 degrees
	 * The first point starts with x coordinate on the circumference of the circle 18 degrees counter clockwise to the x axis
	 * Therefore, the x coordinate is equal to r*cos(18)
	 * The second point is at 90 degrees = 18 + 72. The rest of the points are at 162, 234 and 306 degrees
	 * 72 degrees = (2 * PI) / 5
	 * Similarly, for the inner circle, the points on the circumference of the inner circle are at 54, 126, 198, 270, and 342 degrees
	 * use of factor = 3 denotes inner circle and factor = 1 denotes outer circle
		
	 * @param centerX x-coordinate of center of circle
	 * @param radius radius of circle circumscribing star
	 * @param i index of point on pentagram
	 * @param factor Used to calculate angle for inner and outer circle
	 * @return x-coordinate of point on pentagram
	 */
	private int getXCoords(int centerX, double radius, int i, int factor) {
		return centerX + (int) (radius * Math.cos( PI/10 * factor + (2 * PI) / 5 * i));
	}
}
