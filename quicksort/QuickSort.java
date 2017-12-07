/**
 * QuickSort - This implementation picks last element as pivot
 * References:
  http://homepages.math.uic.edu/~leon/cs-mcs401-s08/handouts/quicksort.pdf
  http://algs4.cs.princeton.edu/lectures/23Quicksort.pdf
  http://www.cs.princeton.edu/courses/archive/spr07/cos226/lectures/04MergeQuick.pdf
 * @author Anuva Banwasi
 *
 */
	
public class QuickSort {
	
	public void sort(int[]num, int lo, int hi) {
		
		if (lo < hi ) {
			int p = partition(num, lo, hi);
			sort(num, lo, p-1);
			sort(num, p+1, hi);
		} else
			return;
	}
	
	public int partition(int[] num, int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivotIndex = hi;
		
		while(true){
			while(num[i] <= num[pivotIndex]){
				i++;
				if (i == hi) break;
			}
			while(num[j] > num[pivotIndex]){
				j--;
				if(j == lo) break;
			}
			
			if (i < j) {
				int tmp = num[i];
				num[i] = num[j];
				num[j] = tmp;
				//print(num);
			} else
				break;
			
		}
		int tmp = num[j];
		num[j] = num[pivotIndex];
		num[pivotIndex] = tmp;
		//System.out.println("pivotIndex  " +  pivotIndex + " j " + j);
		//print(num);
		return j;
		
	}
	
	public void print(int[] num) {
		for(int i = 0; i < num.length; i++)
			System.out.print(num[i] +  " ");
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		//int[] num = {9,9,2,1,4,5,1,9};
		//int[] num = {9,1,4,5,10};
		int[] num = {10,7,4,3,1};
		//int[] num = {4,1,2,3};
		//int[] num = {4,2,1,3,5};
		qs.print(num);
		qs.sort(num, 0 , num.length - 1);
		qs.print(num);
	}
}
