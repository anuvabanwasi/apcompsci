/**
 * @author Anuva Banwasi
 * 3/8/18
 *
 */
import java.util.Arrays;

public class Matrix {
	/**
	 * multiply the set of vectors by a vector and return the result as a one-dimensional array
	 * @param a array representing set of vectors
	 * @param b array representing one dimensional vector
	 * @return multiplies the two arrays together and returns one-dimensional array representing product
	 */
	int[] amatmult(int[] a, int[] b) {
		int[] result = new int[a.length / b.length];
		int count = 0;
		int sum = 0;

		for (int i = 0, j = 0; i < a.length && j < b.length;) {

			sum += a[i] * b[j];
			i++;
			j++;

			if (i % b.length == 0) {
				result[count] = sum;
				count++;
				sum = 0;
				j = 0;
			}
		}

		return  result;
	}
	
	/**
	 * calculates the arithmetic dot product of two arrays
	 * @param a first array
	 * @param b second array
	 * @return dot product of first and second arrays
	 */
	int adot(int[] a, int[] b){
		int dotProduct = 0;
		for(int i = 0; i < b.length; i++) {
			dotProduct += a[i] * b[i];

		}
		return dotProduct;
	}
}
