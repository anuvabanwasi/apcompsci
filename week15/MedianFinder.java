/**
 * This class computes the median of an array of integers using QuickSelect algorithm in O(n) time. It does not sort the input array.
 * It uses partitioning to find the median
 * @author Anuva Banwasi
 * @version 01/22/2018
 *
 */

public class MedianFinder {
	public static void main(String[] args){
		MedianFinder mf = new MedianFinder();
		//{1,2,3,5,8,13,19,21,34,55}
		//array is not sorted
		int[] num = new int[]{21,3,34,5,13,8,2,55,1,19};
		System.out.println(mf.computeMedian(num));
	}

	/**
	 * Compute median of an array of integers.
	 * This method uses the partition method to perform a quick selection of the middle two numbers or middle number depending on the length of the array
	 * Quick Select is an algorithm that finds the kth smallest element in an unordered list. 
	 * When doing selection, we already know which partition our desired element lies in, since the pivot is in its final sorted position, with all those preceding it in an unsorted order and all those following it in an unsorted order
	 * The median is the (n/2)th smallest element (if the array length is odd) 
	 * or the median is the average of the (n/2)th and (n/2+1)th smallest element if the array length is even
	 * if the array length is even then QuickSelect is called twice
	 * if the array length is odd then QuickSelect is called once
	 * @param num array of integers
	 * @return median
	 */
	private double computeMedian(int[] num) {
		
		if (num.length % 2 == 0) {
			int mid = num.length/2;
			
			int midNumber1 = select(num, mid);
			
			int midNumber2 = select(num, mid-1);
			
			printArr(num);
			return average(midNumber1, midNumber2);
			
		} else {
			printArr(num);
			int mid = num.length/2;
			return select(num,mid);
		}
	}

	private void printArr(int[] num) {
		for(int i = 0; i < num.length; i++)
			System.out.print(num[i] + " ");
		
		System.out.println();
	}

	/**
	 * Computes the average of two integers
	 * @param num1 first integer
	 * @param num2 second integer
	 * @return
	 */
	private double average(int num1, int num2) {
		return (num1 + num2)/ 2.0;
	}
		
	/**
	 * Quick Select is an algorithm that finds the kth smallest element in an unordered list with indices ranging from lo to hi
	 * Returns the k-th smallest element of list within lo..hi inclusive (i.e. lo <= k <= hi).
	 * @param num array to partition
	 * @param k kth smallest element to search
	 * @return element of array at kth index, i.e, num[k]
	 */
	private int select(int[] num, int k) {
		int i = 0;
		int j = num.length-1;
		
		while( i < j ){
			int pivot = partition(num, i, j);
			if (pivot == k)
				return num[pivot];
			else if (k > pivot)
				i = pivot + 1;
			else 
				j = pivot - 1;
		}
		
		return Integer.MIN_VALUE;
	}

	/**
	 * https://en.wikipedia.org/wiki/Quickselect
	 * Partition an array in linear time into two parts ; those less than a certain element, and those greater than or equal to the element
	 * @param a array to partition
	 * @param lo index of leftmost element
	 * @param hi index of rightmost element
	 * @return index of the pivot
	 */
	private int partition(int[] a, int lo, int hi) {
		int pivotIndex = hi;
		int i = lo;
		int j = hi;
		while (true){
			while(a[i] >= a[pivotIndex]){
				i++;
				if (i == hi) break;
			}
			
			while(a[j] < a[pivotIndex]){
				j--;
				if(j == lo) break;
			}
			
			if(i < j) {
				swap(a,i,j);
			} else
				break;
		}
		
		swap(a,j,pivotIndex);
		return j;
	}

	/**
	 * Swap num[i] and num[j] in an array num
	 * @param num
	 * @param i index
	 * @param j index
	 */
	void swap(int[]num, int i, int j){
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
