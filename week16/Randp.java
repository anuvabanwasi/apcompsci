/**
 * A class that generates a special type of random object which keeps track of which numbers have already been selected so that there are no repeats 
 * as described in: paleyontology.com/AP_CS/randp.html
 * @author Anuva Banwasi
 *
 */
public class Randp {
	public static void main(String[] args) {
		Randp r = new Randp(6);
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt());

	}

	private int[] nums;
	private int numsLeft;

	public Randp(int n) {
		nums = new int[n];
		numsLeft = n;
		initNums(n);
	}
	
	/**
	 * Initializes array with numbers 1 to n (inclusive) in a random order
	 * @param n
	 */
	private void initNums(int n) {
		// fills the array with numbers from 1 to n (inclusive)
		for(int i = 0; i < n; i++) {
			nums[i] = i + 1;
		}
		
		// shuffles the nums array to put them in a random order
		for(int i = 0; i < nums.length; i++) {
			int index = (int) (Math.random() * n) ; // generates random number from 0 to n (exclusive)
			swap(i, index); // swaps nums[i] and nums[index]
		}
	}
	
	/**
	 * Swaps two numbers in nums array 
	 * @param a
	 * @param b
	 */
	private void swap(int a, int b) {
		int temp = nums[b];
		nums[b] = nums[a];
		nums[a] = temp;
	}

	/**
	 * Returns the next element in the array (if numsLeft is 0, returns 0)
	 * Order of Growth is O(1)
	 * @return the next element in array, 0 if numsLeft = 0
	 */
	public int nextInt() {
		if(numsLeft == 0) // if numsLeft is 0, returns 0 because all numbers used up
			return 0;
		else {
			return nums[nums.length - numsLeft--]; // returns the next number in the array if numsLeft is not 0
		}
	}
}
