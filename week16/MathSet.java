
import java.util.HashSet;
import java.util.Set;

/**
 * Program to implement mathematical notation of a set. MathSet implements the Set interface, and provides union and intersection methods
 * @author Anuva Banwasi
 *
 * @param <T>
 */
public class MathSet<T> extends HashSet<T> implements Set<T>{

	public static void main(String[] args) {
		MathSet<Integer> set = new MathSet<Integer>();
		set.add(4);
		set.add(9);
		set.add(2);
		set.add(8);
		set.add(3);

		MathSet<Integer> other = new MathSet<Integer>();
		other.add(2);
		other.add(11);
		other.add(7);
		other.add(5);
		other.add(4);

		System.out.println(set.intersect(other));
		System.out.println(set.contains(12));
		System.out.println(set.anyContains(other));
	}

	/**
	 * Union of 2 sets
	 * @param other Set to union with the current set
	 * @return set representing union of the two sets
	 */
	public MathSet<Integer> union(MathSet<T> other) {
		int[] second = convertToArray(other);
		int[] first = convertToArray(this);
		int[] union = union(first, second);

		return convertToSet(union);
	}

	/**
	 * Intersection of 2 sets
	 * @param other Set for intersection with current set
	 * @return set representing intersection of the sets
	 */
	public MathSet<Integer> intersect(MathSet<T> other) {
		int[] second = convertToArray(other);
		int[] first = convertToArray(this);
		int[] intersection = intersect(first, second);

		return convertToSet(intersection);
	}

	/**
	 * Returns true if set contains the element a
	 * @param a
	 * @return
	 */
	public boolean contains(int a) {
		int[] arr = convertToArray(this);

		return contains(a, arr);
	}

	public boolean  anyContains(MathSet<T> other) {
		int[] second = convertToArray(other);
		int[] first = convertToArray(this);
		
		return anyContains(first, second);
	}

	/**
	 * Method to union 2 arrays
	 * @param a first integer array
	 * @param b second integer array
	 * @return array representing union of the 2 arrays
	 */
	public int[] union(int[] a, int[] b) {
		int[] union = new int[a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			union[i] = a[i];
		}
		for(int i = 0; i < b.length; i++) {
			union[i + a.length] = b[i];
		}

		return union;
	}

	/**
	 * Check if array ar contains the element a
	 * @param a element to check
	 * @param ar integer array
	 * @return true if array ar contains the element a
	 */
	public boolean contains(int a, int[] ar){
		for(int i = 0; i < ar.length; i++) {
			if(ar[i] == a)
				return true;
		}
		return false;
	}

	/**
	 * Check if any of elements of a are contained in array b
	 * @param a first array
	 * @param b second array
	 * @return true if any of the elements of a are contained in b
	 */
	boolean anyContains(int[] a, int[] b){
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				if(a[i] == b[j])
					return true;
			}
		}
		return false;
	}

	/**
	 * Intersection of 2 arrays
	 * @param a first integer array
	 * @param b second integer array
	 * @return array representing intersection of 2 arrays
	 */
	public int[] intersect(int[] a, int[] b){
		int i = 0, j = 0;
		a = sort(a);
		b = sort(b);
		int[] newArr = new int[findLength(a,b)];
		int count = 0;

		// adds elements common to both a and b to the new array
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				i++;
			else if (b[j] < a[i])
				j++;
			else {
				newArr[count] = b[j];
				count++;
				i++;
				j++;				
			}
		}

		return newArr;
	}

	/**
	 * Sorts an array using insertion sort
	 * @param arr integer array 
	 * @return sorted array
	 */
	public int[] sort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i+1] < arr[i]) {
				arr = swap(arr, i, i+1);
				for(int j = i; j > 0; j--) {
					if(arr[j-1] > arr[j])
						arr = swap(arr, j-1, j);
				}
			}
		}
		return arr;
	}

	/**
	 * Sway the contents of array[first] and array[second]
	 * @param array
	 * @param first
	 * @param second
	 * @return
	 */
	// swap method for sorting the array
	public int[] swap(int[] array, int first, int second) {
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
		return array;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int findLength(int[] a, int[] b) {
		int count = 0;
		int i = 0;
		int j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				i++;
			else if (b[j] < a[i])
				j++;
			else {
				count++;
				i++;
				j++;
			}
		}
		return count;
	}

	
	/**
	 * Convert elements of array to a set
	 * @param arr integer array
	 * @return MathSet representing the input array
	 */
	public MathSet<Integer> convertToSet(int[] arr) {
		MathSet<Integer> m = new MathSet<Integer>();

		for(int i = 0; i < arr.length; i++) {
			Integer element = new Integer(arr[i]);
			m.add(element);
		}

		return m;
	}

	/**
	 * Convert elements of set to array
	 * @param a MathSet
	 * @return array representing elements of MathSet
	 */
	public int[] convertToArray(MathSet<T> a) {
		int[] arr = new int[a.size()];
		int i = 0;
		for(T element: a) {
			arr[i++] = ((Integer) element).intValue();
		}
		return arr;
	}
}
