
public class Exercises {	
	private static double real;
	private static double imag;
	private static int n = 10;
	
	public static void main(String[] args) {
	}
	
	/**
	 * class method
	 * @param c
	 * @return
	 */
	public static double abs(Complex c) {
		return Math.sqrt(c.real * c.real + c.imag * c.imag);
	}
	
	/**
	 * object method
	 * @return
	 */
	public double abs() {
		return Math.sqrt(real * real * imag * imag);
	}
	
	/**
	 * object method
	 * @param b
	 * @return
	 */
	public boolean equals(Complex b) {
		return(real == b.real && imag == b.imag);
	}
	
	public static boolean equals() {
		return(real == real && imag == imag);
	}
	
	
	
}
