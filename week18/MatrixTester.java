import java.util.Arrays;
/**
 * @author Anuva Banwasi
 * 3/8/18
 *
 */
public class MatrixTester {
	public static void main(String[] args) {
		Matrix m = new Matrix();
		int[] a1 = {4, 4, 2, 1};
		int[] b1 = {5, 6, 4, 2};
		int[] result1 = m.amatmult(a1, b1);
		System.out.println("Test 1:" + Arrays.toString(result1));
		
		int[] a2 = {7, 1, 5, 9, 2, 4, 135, 55, 6, 8, 9, 1, 2, -4};
		int[] b2 = {1, 0, 1, 5, 2, 2, 7, 0};
		int[] result2 = m.amatmult(a2, b2);
		System.out.println("Test 2:" + Arrays.toString(result2));
		
		int[] a3 = {-4, -1, -7, 8, 1, 8, 1, 9};
		int[] b3 = {2, 1, 0, 3};
		int[] result3 = m.amatmult(a3, b3);
		System.out.println("Test 3:" + Arrays.toString(result3));
		
		int[] a4 = {-5, 8,-4, 7, 9, 8, 2, -4, 8};
		int[] b4 = {4, 8, 2, -5, 7, 1, 6, -3, 7};
		int dotProduct1 = m.adot(a4, b4);
		System.out.println("Dot product: " + dotProduct1);
	}
}
