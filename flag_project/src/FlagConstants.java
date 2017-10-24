/**
 * Represents constants used in drawing US Flag to scale. Refers US flag ratios specs at http://www.usflag.org/flag.specs.html 
 * @author Anuva
 *
 */
public class FlagConstants {
	// Height of the flag
	public final static double flagW = 1.0;
	
	// Width is 1.9 times height of the flag
	public final static double flagL = 1.9;
	
	// Union has 7 stripes. Therefore Height of the union is 7/13 * height of the flag
	public final static double unionW = flagW * 7/13;
	
	// Union height is 2/5 flag height
	public final static double unionL = flagL * 2/5;
	
	// y-coordinate offset of first star
	public final static double starO = unionW/10;
	
	// Vertical distance between 2 stars is height of union / 10
	public final static double starD = unionW/10;
	
	// x-coordinate offset of first star
	public final static double starG = unionL/12;
	
	// Horizontal distance between 2 stars is width of union / 12
	public final static double starH = unionL/12;
	
	// Stripe height is 1/13 * width of flag
	public final static double stripeL = flagW/13;
	
	// Diameter of star
	public final static double starDia = stripeL * 4/5;
	
	// Number of stripes on star
	public static final int STRIPES = 13;

}
