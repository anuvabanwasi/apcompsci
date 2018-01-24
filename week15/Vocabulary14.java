/**
 * @author Anuva Banwasi
 * @version 01/22/2018
 */

import java.util.ArrayList;
import java.util.List;

public class Vocabulary14 {
	
	/**
	 * Vacab 1: UNION
	 * takes the union of the two lists passed as parameters to the method
	 * reference: https://stackoverflow.com/questions/3590677/how-to-do-union-intersect-difference-and-reverse-data-in-java
	 * @param a
	 * @param b
	 * @return the list containing the union of list a and list b (without any duplicates)
	 */
	public List<Integer> union(List<Integer> a, List<Integer> b) {
		List<Integer> unionList = new ArrayList<Integer>();
		addElements(unionList, a);
		addElements(unionList, b);
		return unionList;
	}

	/**
	 * adds the elements from one list into the second lists without any duplicates
	 * reference: https://stackoverflow.com/questions/3590677/how-to-do-union-intersect-difference-and-reverse-data-in-java
	 * @param newList
	 * @param originalList
	 * @return the list containing the elements in the originalList with no duplicates
	 */
	public void addElements(List<Integer> newList, List<Integer> originalList) {
		for(Integer element: originalList) {
			if(newList.indexOf(element) == -1) {
				newList.add(element);
			}
		}
	}

	/**
	 * VOCAB 2: INTERSECTION
	 * takes the intersection of the two lists passed as parameters to the method
	 * reference: https://stackoverflow.com/questions/3590677/how-to-do-union-intersect-difference-and-reverse-data-in-java
	 * @param a
	 * @param b
	 * @param c
	 * @return the list containing the intersection of list a and list b
	 */
	public List<Integer> intersection(List<Integer> a, List<Integer> b) {		
		List<Integer> intersectionList = new ArrayList<Integer>();
		for(Integer element: a) {
			if(b.indexOf(element) != -1) {
				intersectionList.add(element);
			}
		}
		return intersectionList;
	}

	/**
	 * VOCAB 3: SET DIFFERENCE
	 * takes the set difference between list a and list b (a - b)
	 * reference: https://stackoverflow.com/questions/3590677/how-to-do-union-intersect-difference-and-reverse-data-in-java
	 * @param a
	 * @param b
	 * @return the list containing the set difference between list a and list b  (a - b) 
	 */
	public List<Integer> setDifference(List<Integer> a, List<Integer> b) {
		List<Integer> setDifference = new ArrayList<Integer>();
		setDifference.addAll(a);
		setDifference.removeAll(b);
		return setDifference;
	}

	/**
	 * VOCAB 6: STANDARD DEVIATION
	 * calculates the standard deviation of the values in a list 
	 * takes the mean of all the values in the list
	 * subtracts the mean from each value in the list and then squares this value
	 * takes the mean of the squared differences
	 * takes the square root of the mean of the squared differences
	 * @param list
	 * @return the standard deviation of the values in a list
	 */
	public double standardDeviation(List<Double> list) {
		double originalMean = findMean(list);
		List<Double> difference = new ArrayList<Double>();
		for(Double element: list) {
			difference.add(Math.pow(element - originalMean, 2));
		}
		double differenceMean = findMean(difference);
		return Math.sqrt(differenceMean);
	}

	/**
	 * calculates the mean of the values in a list 
	 * (sums all the elements) / (number of elements in the list)
	 * @param list
	 * @return the standard deviation of the values in a list
	 */
	public double findMean(List<Double> list) {
		double sum = 0;
		for(Double element: list) {
			sum += element;
		}
		return sum/list.size();
	}

	/**
	 * VOCAB 7: BINOMIAL COEFFICIENT
	 * calculates the binomial coefficient given the coefficients of the two terms
	 * @param n the coeffcient of the first term
	 * @param r the coefficient of the second term
	 * @return the binomial coefficient of the two terms
	 */
	public int binomialCoefficient(int n, int k) {
		if (k == 0 || k == n)
			return 1;

		return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
	}


	/**
	 * calculates the factorial of a number recursively 
	 * @param num
	 * @return the factorial of the parameter num
	 */
	public int factorial(int num) {
		if(num == 0 || num == 1)
			return 1;
		return num * factorial(num - 1);

	}
	
	public static void main(String[] args) {
		Vocabulary14 v = new Vocabulary14();

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(4);
		a.add(6);
		a.add(7);
		a.add(2);
		a.add(5);
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(0);
		b.add(3);
		b.add(1);
		b.add(5);
		b.add(7);

		System.out.println("original list 1: " + a);
		System.out.println("original list 2: " + b);
		System.out.println();

		System.out.println("union of list 1 and 2: " + v.union(a, b));
		System.out.println("intersection of list 1 and 2: " + v.intersection(a, b));
		System.out.println("set difference of list 1 and 2: " + v.setDifference(a, b));
		System.out.println();

		System.out.println("The binomial coefficient of two terms with coeffficients 100 and 5 is: " + v.binomialCoefficient(100, 5));
		System.out.println();

		ArrayList<Double> list = new ArrayList<>();
		list.add(5.0);
		list.add(17.0);
		list.add(3.0);
		list.add(20.0);
		list.add(14.0);

		System.out.println("The list is: " + list);
		System.out.println("The population standard deviation in the list is: " + v.standardDeviation(list));
		System.out.println();
		
		Limit l = new Limit();
		l.testCase1();
		l.testCase2();
		l.testCase3();
		l.testCase4();
		
	}

}


/**
 * VOCAB 4: LIMIT
 * VOCAB 5: L'HOSPITAL'S RULE
 * @author Anuva Banwasi
 * @version 01/22/2018
 * Definition of Limit: https://en.wikipedia.org/wiki/Limit_(mathematics)
 * Definition of L'Hopital's Rule: https://en.wikipedia.org/wiki/L%27H%C3%B4pital%27s_rule
 * Reference for evaluating limits: http://www.milefoot.com/math/calculus/limits/LimitStrategies11.htm
 */

class Limit {

	//Value of Epsilon so limit can lie between a defined interval
	private final double EPSILON = 0.001;

	/**
	 * https://en.wikipedia.org/wiki/Limit_(mathematics)
	 * Compute limit using approximation technique
	 * @param x array of doubles representing input values 
	 * @param fx array of doubles fx[i] represents value of the function at input x[i]
	 * @return limit of f(x)
	 */
	public double computeLimit(double[] x, double[] fx) {

		double limit = 0.0;

		int medianIndex = (x.length - 1) / 2;
		//if Median of the sorted array is positive infinity or negative infinity then find the limit by picking the elements
		//to the left and right of the median and compute the limit
		if (Double.isInfinite(fx[medianIndex])) {
			double diff = Math.abs(fx[medianIndex + 1] - fx[medianIndex - 1]);
			if (diff / 2 < EPSILON)
				limit = diff / 2 + fx[medianIndex - 1];
			else
				return Double.NaN;
		} else
			//if the median of the sorted array is not positive infinity or not negative infinity then return the median as the limit 
			limit = fx[medianIndex];

		return limit;
	}
	
	/**
	 * Compute limit by substitution 
	 * @param x array of doubles representing input values
	 * @param fx array of doubles fx[i] represents value of the function at input x[i]
	 * @return limit of f(x)
	 */
	
	public double computeLimit2(double[] x, double[] fx) {

		double limit = 0.0;

		int medianIndex = (x.length - 1) / 2;
		limit = fx[medianIndex];
		return limit;
	}

	/**
	 * https://en.wikipedia.org/wiki/Limit_(mathematics)
	 * Compute limit of f(x)/g(x) using L'Hospital's Rule
	 * if both limits of f(x) / limit of g(x) is indeterminate form, compute limit of f'(x)/g'(x), where f'(x) is derivative of f(x) and g'(x) is derivative of g(x)
	 * @param x array of doubles representing input values
	 * @param fx array of doubles fx[i] represents value of the function at input x[i]
	 * @param gx array of doubles gx[i] represents value of the function at input x[i]
	 * @param fprime array of doubles fprime[i] represents value of the derivative of the function f(x) at input x[i]
	 * @param gprime array of doubles gprime[i] represents value of the derivative of the function g(x) at input x[i]
	 * @return limit
	 */
	public double computeLimitWithLopital(double[] x, double[] fx, double[] gx, double[] fprime,
			double[] gprime) {

		double limitFx = computeLimit2(x, fx);
		double limitGx = computeLimit2(x, gx);

		double limitFprime = 0.0;
		double limitGprime = 0.0;
		
		// Handle indeterminate form 0/0
		if (isZero(limitFx, 0.0001) && isZero(limitGx, 0.001)) {
			limitFprime = computeLimit2(x, fprime);
			limitGprime = computeLimit2(x, gprime);
		} 
		// Handle indeterminate form +-INFINITY/+-INFINITY
		else if (Double.isInfinite(limitFx) && Double.isInfinite(limitGx)) {
			limitFprime = computeLimit2(x, fprime);
			limitGprime = computeLimit2(x, gprime);
		} 
		// Handle form 0/not 0
		else if (isZero(limitFx, 0.0001) && !isZero(limitGx, 0.001)) {
			return 0.0;
		} 
		
		// Handle form INFINITY/not INFINITY
		else if (Double.isInfinite(limitFx) && !Double.isInfinite(limitGx)) {
			return limitFx;
		} 
		
		// All other cases
		else {
			return limitFx/limitGx;
		}
	
		// Handle limit f'(x) /  g'(x) indeterminate forms
		if (isZero(limitFprime, 0.001) && isZero(limitGprime, 0.001))
			return Double.NaN;
		
		if (Double.isInfinite(limitFprime) && Double.isInfinite(limitGprime))
			return Double.NaN;
		
		// compute limit f'(x)/ g'(x)
		return limitFprime / limitGprime;
		
	}

	/**
	 * https://stackoverflow.com/questions/18260213/how-to-test-if-a-double-is-zero
	 * Checks if a double is zero, an error threshold is used to detect if the value is near 0, but not quite 0
	 * @param value double to check
	 * @param threshold error threshold to check that the value is near to 0
	 * @return
	 */
	public boolean isZero(double value, double threshold) {
		return value >= -threshold && value <= threshold;
	}
	
	public void testCase1() {
		double[] v = { 0.9, 0.99, 0.999, 1.0, 1.001, 1.01, 1.1 };
		double[] fv = { 1.900, 1.990, 1.999, Double.NEGATIVE_INFINITY, 2.001, 2.010, 2.100 };
		
		System.out.print ("TestCase 1 : Computing limit" + " -> ");
		System.out.println("Actual : " + computeLimit(v, fv) + " Expected : " + 2.0);
		System.out.println();
	}
	
	public void testCase2() {
		double[] v = { 0.9, 0.99, 0.999, 1.0, 1.001, 1.01, 1.1 };
		double[] fw = { 1.900, 1.990, 1.999, Double.POSITIVE_INFINITY, 2.001, 2.010, 2.100 };
		
		System.out.print("TestCase 2 : Computing limit"  + " -> ");
		System.out.println("Actual : " + computeLimit(v, fw) + " Expected : " + 2.0);
		System.out.println();

	}
	
	public void testCase3() {
		double[] x = { 0.9, 0.99, 0.999, 0.9999, 1, 1.0001, 1.001, 1.01, 1.1 };
		double[] fx = { -0.19, -0.0199, -0.001999, -0.00019999, 0, 0.00020001, 0.002001, 0.0201, 0.21 };
		double[] gx = { -0.49, -0.0499, -0.004999, -0.00049999, 0, 0.00050001, 0.005001, 0.0501, 0.51 };
		double[] fprime = { 1.8, 1.98, 1.998, 1.9998, 2, 2.0002, 2.002, 2.02, 2.2 };
		double[] gprime = { 4.8, 4.98, 4.998, 4.9998, 5, 5.0002, 5.002, 5.02, 5.2 };
		
		System.out.print("TestCase3 : Computing limit using L'Hospital's rule" +  " -> ");
		System.out.println("Actual : " + computeLimitWithLopital(x, fx, gx, fprime, gprime) + " Expected : " + 0.4);
		System.out.println();

	}
	
	
	public void testCase4() {
		double[] m = {999998.9,999998.99,999998.999,999998.9999,Double.POSITIVE_INFINITY,999999.0001,999999.001,999999.01,999999.1};
		double[] fm = {999998350,999998485,999998498.5,999998499.9,Double.POSITIVE_INFINITY,999998500.2,999998501.5,999998515,999998650};
		double[] gm = {13815494.26,13815495.59,13815495.73,13815495.74,Double.POSITIVE_INFINITY,13815495.74,13815495.76,13815495.89,13815497.22};
		
		double[] fmprime = {999.99945,999.999495,999.9994995,999.9994999,Double.POSITIVE_INFINITY,999.9995005,999.999505,999.99955};
		double[] gmprime = {13.81550946,13.81550955,13.81550956,13.81550956,Double.POSITIVE_INFINITY,13.81550956,13.81550956,13.81550957,13.81550966};
		
		double[] fmdprime = {749.9995875,749.9996212,749.9996246,749.999625,Double.POSITIVE_INFINITY,749.999625,749.9996254,749.9996287,749.9996625};
		double[] gmdprime = {1,1,1,1,1,1,1,1,1};
		
		System.out.println("TestCase4 : Computing limit using L'Hospital's rule"  + " -> ");

		System.out.println(
				"Actual : " + computeLimitWithLopital(m, fm, gm, fmprime, gmprime) + " Expected : " + Double.NaN);
		System.out.println(
				"Actual : " + computeLimitWithLopital(m, fm, gm, fmdprime, gmdprime) + " Expected : " + Double.POSITIVE_INFINITY);
		
	}

}
