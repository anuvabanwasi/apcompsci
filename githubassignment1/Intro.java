/**
 * Solutions to 1-4 of the GitHub Assignment 1
 * @author anuvabanwasi
 *
 */
public class Intro {
	public static void main(String args[]) {
		ret();
		logic(true, true, false);
		stars(5);
		coins(10);
	}
	
	/**
	 * Returns 17
	 * @return 17
	 */
	private static int ret() {
		return 17;
	}
	
	/** Computes the logical and of the parameters
	 * @param three proposition constants with truth values represented by a boolean true of false
	 * @return the logical and of the parameters
	 */
	private static boolean logic(boolean a, boolean b, boolean c) {
		return a & b & c;
	}
	
	/** Prints a left-justified triangle using the * character.
	 * @param n - number of rows of stars
	 * prints a left-justified triangle made out of the "*" character of n rows
	 */
	private static void stars(int n) {
		for(int i = 1; i < n + 1; i++) {
			for(int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Computes the minimum number of coins (5 cents and 2 cents) needed to exchange for an amount num
	 * @param num - the sum to provide change for
	 * @return minimum number of coins needed for exchange
	 */
	private static int coins(int num) {
		int cnt = 0;
		int orig_num = num;
		
		// check if the sum to exchange is divisible by 5
		while(num >= 5) {
			num = num - 5;
			cnt++;
			if(num == 0)
				return cnt;
		}
		
		// if the remainder is odd, check if the original num is divisible by 2
		if(num % 2 == 1) {
			cnt = 0;
			if(orig_num % 2 == 0)
				cnt = cnt + orig_num / 2;
		}
		
		// if the remainder is divisible by 2
		if(num % 2 == 0) {
			cnt = cnt + num / 2;
		}
		
		if(cnt != 0)
			return cnt;
		
		// no change is possible
		return -1;
	}
}
