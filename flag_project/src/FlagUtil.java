import java.awt.Color;

/**
 * Performs calculations based on ratios defined in FlagConstants
 * @author Anuva
 *
 */
public class FlagUtil {
	
	/**
	 * Computes height of union based on ratio of union height to flag height
	 * @param height Height of flag
	 * @return height of union
	 */
	static double getUnionHeight(double height) {
		return FlagConstants.unionW * height;
	}

	/**
	 * Computes width of union as a fraction of flag width 
	 * The fraction is unionL/flagL
	 * @param width Width of flag
	 * @return width of union
	 */
	static double getUnionWidth(double width) {
		return (FlagConstants.unionL / FlagConstants.flagL) * width;
	}

	/**
	 * Calculates width of stripe on us flag. The flag has 7 short stripes and 6 long stripes.
	 * Width of short stripes  =  (width - union width)
	 * Width of long stripes = width of the flag
	 * @param i index of stripe
	 * @param width width of stripe
	 * @param unionWidth width of union
	 * @return width of stripe
	 */
	static double getStripeWidth(int i, double width, double unionWidth) {
		return (i > 7 ? width : width - unionWidth);
	}

	/**
	 * Calculates height of the stripe as a fraction of flag height
	 * There are 13 stripes on the flag. Each stripe is 1/13 * height of flag
	 * @param height Height of flag
	 * @return height of stripe
	 */
	static double getStripeHeight(double height) {
		return FlagConstants.stripeL * height;
	}
	
	/**
	 * Calculates y-coordinate of center of star as a fraction of the height of the flag
	 * The fraction is y offset of star / height of flag
	 * @param height Height of flag
	 * @return y-coordinate of center of star
	 */
	static int getCenterY(double height) {
		return (int) ((FlagConstants.starO / FlagConstants.flagW ) * height);
	}

	/**
	 * Calculates x-coordinate of center of star as a fraction of the width of the flag
	 * The fraction is x coordinate of star / width of flag
	 * @param width Width of flag
	 * @return x-coordinate of center of star
	 */
	static int getCenterX(double width) {
		return (int) ((FlagConstants.starG / FlagConstants.flagL ) * width);
	}
	
	/**
	 * Calculate vertical space between stars as a fraction of flag height
	 * @param height
	 * @return vertical space between star centers
	 */
	static int getSpaceY(double height) {
		return (int) (FlagConstants.starD / FlagConstants.flagW * height);
	}

	/**
	 * Calculate horizontal space between stars as a fraction of flag width
	 * @param width
	 * @return horizontal space between star centers
	 */
	static int getSpaceX(double width) {
		return (int) (FlagConstants.starH / FlagConstants.flagL * width);
	}

	/**
	 * Calculate radius of star as a fraction of flag height
	 * @param height
	 * @return
	 */
	static double getRadius(double height) {
		return FlagConstants.starDia/2 * height;
	}

	/**
	 * Calculate x-coordinate of stripe
	 * There are 7 short stripes and 6 long stripes
	 * The short stripes start at unionWidth
	 * The long stripes start at x-coordinate = 0
	 * @param i index of stripe
	 * @param unionWidth width of union
	 * @return x-coordinate of stripe
	 */
	static double getStripeX(int i, double unionWidth) {
		return (i > 7 ? 0 : unionWidth);
	}

	/**
	 * Calculate y-coordinate of stripe
	 * There are 13 stripes. The y-coordinate of each stripe is equal to y-coordinate of previous stripe + stripe height
	 * @param i index of stripe
	 * @param height height of flag
	 * @return y-coordinate of stripe
	 */
	static double getStripeY(int i, double height){
		return i * FlagUtil.getStripeHeight(height);
	}
	
	/**
	 * Return stripe color as red or white depending on whether the stripe number is even or odd
	 * @param i index of stripe
	 * @return color of stripe
	 */
	static Color getStripeColor(int i) {
		Color red = new Color(191, 10, 48);
		Color white = new Color(255,255,255);
		
		return (i % 2 == 0 ? red :white );
	}
}
