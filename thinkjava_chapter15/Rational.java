/**
 * This class creates a rational number. A rational number is a number that can be represented as the ratio of two integers(a/b)
 * 
 * It uses integer primitives to represent the numerator and denominator of the rational number and provides operations like add, negate, reduce, invert, toDouble and print on the rational number
 * @author anuvabanwasi
 *
 */
public class Rational {
	private int num;
	private int denom;
	
	public static void main(String[] args) throws Exception {
		Rational a = new Rational(3, 14);
		System.out.println("First rational number");
		a.printRational();
		
		
		Rational b = new Rational(2, 7);
		System.out.println("Second rational number");
		b.printRational();
		
		System.out.println("Negate rational number");
		b.negate();
		b.printRational();
		
		System.out.println("Convert rational number to double");
		System.out.println(b.toDouble());
		
		System.out.println("Reduce rational number");
		Rational c = new Rational(5, 15);
		Rational d = c.reduce();
		d.printRational();
		
		System.out.println("add 2 rational numbers");
		Rational r1 = new Rational(2,3);
		Rational r2 = new Rational(4,5);
		
		Rational r3 = r1.add(r2);
		r3.printRational();
		
		System.out.println("subtract 2 rational numbers");
		Rational r4 = new Rational(2,3);
		Rational r5 = new Rational(4,5);
		
		//Legal
		//Rational r6 = Rational.subtract(r4,r5);
		Rational r6 = r4.subtract(r4,r5);
		r6.printRational();
	}
	
	/**
	 * no-args constructor that sets numerator to 0 and denominator to 1
	 */
	public Rational() {
		num = 0;
		denom = 1;
	}
	
	/**
	 * constructor that sets the numerator to the num parameter and the denominator to the den parameter
	 * @param num integer representing numerator of rational number
	 * @param den integer representing denominator of rational number
	 * @throws Exception if denominator is 0
	 */
	public Rational(int num, int denom) throws Exception {
		this.num = num;
		if(denom == 0)
			throw new Exception("denominator of rational number cannot be 0");
		this.denom = denom;
	}
	/**
	 * prints a rational number in the form a/b
	 * @param num the rational number to be printed
	 */
	public void printRational() {
		System.out.println(this.num + "/" + this.denom);
	}
	
	/**
	 * negates sign of the current rational number on which it is invoked
	 */
	public void negate() {
		this.num = -num;
	}
	
	/**
	 * swaps the numerator and denominator of the rational number
	 * @param rational number rat with flipped numerator and denominator
	 */
	public void invert(Rational rat) {
		int temp = rat.num;
		rat.num = rat.denom;
		rat.denom = temp;
	}
	
	/**
	 * returns the decimal value of a rational number
	 * @return double value of rational number rat
	 */
	public double toDouble() {
		double result = (double)this.num/this.denom;
		return result;
	}
	
	/**
	 * finds the greatest common factor of two numbers 
	 * @param a
	 * @param b
	 * @return gcf of a and b
	 */
	public int gcf(int a, int b) {
		if(b == 0) {
			return a;
		}
		return gcf(b, a % b);
	}
	
	/**
	 * reduces a rational number to its lowest terms by finding the greatest common divisor (GCD) of the 
	 * numerator and denominator and dividing through
	 * prints the result of the reduced rational number
	 * @throws Exception 
	 */
	public Rational reduce() throws Exception {
		int gcf = gcf(this.num, this.denom);
		int numer = num/gcf;
		int denomin = denom/gcf;
		Rational rat2 = new Rational(numer, denomin);
		return rat2;
	}
	
	/**
	 * Method to add 2 rational numbers. Add current(this) rational number to another rational number passed as argument
	 * @param rat2 second rational number
	 * @throws Exception if denominator is 0
	 */
	public Rational add(Rational rat2) throws Exception {
		int overall_denom = this.denom * rat2.denom;
		int rat_num = this.num * rat2.denom;
		int rat2_num = rat2.num * this.denom;
		int overall_num = rat_num + rat2_num;
		
		Rational rat3 = new Rational(overall_num, overall_denom);
		rat3.reduce();
		return rat3;
	}
	
	public static Rational subtract(Rational rat, Rational rat2) throws Exception {
		//Illegal to reference instance variable from class method
		//System.out.println("the numerator of the rational number is " + this.num);
		int overall_denom = rat.denom * rat2.denom;
		int rat_num = rat.num * rat2.denom;
		int rat2_num = rat2.num * rat.denom;
		int overall_num = rat_num - rat2_num;
		
		Rational rat3 = new Rational(overall_num, overall_denom);
		rat3.reduce();
		return rat3;
	}
}
