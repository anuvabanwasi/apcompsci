/**
 * @author Anuva Banwasi
 */
package apcs.sort;

public class ThreeSorts {
	public ThreeSorts() {
		
	} 
	
	public static void main(String[] args) {
		ThreeSorts x = new ThreeSorts();
		int[] books = {3, 1, 4, 1, 5, 12, 4, 7, 2, 5, 2, 1, 10};
		
		int[] bubbleSortedBooks = x.bubbleSort(books);
		x.printArray(bubbleSortedBooks);
		
		int[] insertionSortedBooks = x.insertionSort(books);
		x.printArray(insertionSortedBooks);
		
		int[] selectionSortedBooks = x.selectionSort(books);
		x.printArray(selectionSortedBooks);
		
	}
	
	/** 
	 * Bubble Sort
	 * ​compares ​the ​value ​of ​one ​index ​to ​the ​value ​of ​the ​index ​next ​to it 
	 * ​if ​the ​value ​at ​the ​second ​index ​is ​less ​than ​the ​value ​at ​the ​first ​index, ​​calls ​the swap ​method ​on ​the ​two ​indices
	 * @param arr - array to be sorted
	 * @return the sorted array
	 */
	public int[] bubbleSort(int[] arr) {
		for(int i = 0;  i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - i - 1; j++) {
				if(arr[j+1] < arr[j]) {
					arr = swap(arr, j, j+1);
				}
			}
		}
		return arr;
	}
	
	/**
	 * my version of Insertion Sort
	 * after ​swapping ​two elements,  ​nters ​a ​for ​loop ​where ​it ​checks ​if ​the ​swapped ​element ​can ​be
	 * swapped ​further ​within ​the ​previous ​indices ​of ​the ​array into its correct position
	 * @param arr - array to be sorted
	 * @return the sorted array
	 */
	public int[] insertionSort(int[] arr) {
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
	 * Another version of Insertion Sort from http://www.geeksforgeeks.org/insertion-sort/ website
	 * @param arr - array to be sorted
	 * @return the sorted array
	 */
	public int[] insertionSort2(int[] arr) {
		for(int i = 1; i <= arr.length - 1; i++) {
			int j = i;
 			while(j > 0 && arr[j-1] > arr[j]) {
 				arr = swap(arr, j, j - 1);
 				j--;
 			}
		}
		return arr;
	}
	
	/**
	 * Selection Sort
	 * find ​the ​most ​extreme ​element ​in ​the ​unsorted ​partition ​and ​swap ​it ​with ​the ​"first" ​of ​the ​sorted partition
	 * partition is ​then ​incremented ​to be ​between ​the ​boundary ​between ​the sorted ​portion ​and ​the ​unsorted ​portion of array
	 * @param arr
	 * @return
	 */
	public int[] selectionSort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			int indexOfMin = i;
			for(int j = i+1; j < arr.length; j++) {
				if(arr[j] < arr[indexOfMin]) 
					indexOfMin = j;
			}
			arr = swap(arr, i, indexOfMin);
		}
		return arr;

	}	
	
	/**
	 * swaps two elements in an array
	 * @param array
	 * @param first
	 * @param second
	 * @return the array with the two elements swapped
	 */ 
	public int[] swap(int[] array, int first, int second) {
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
		return array;
	}
	
	/**
	 * prints all values in the array
	 * @param arr array to be printed
	 */
	public void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	/**
	 * finds minimum value in the inputed array
	 * @param array to be sorted
	 * @return minimum value in the array passed to method
	 */
	public int findMin(int[] array) {
		int min = array[0];
		for(int j = 0; j < array.length; j++) {
			if(array[j] < min)
				min = j;
		}
		return min;
	}
}
