/**
 * A Class that computes the value of a function using a recursive process and an iterative process
 * The function is defined as:
 * f(n) = n if n < 3,
 * f(n) = f(n-1) + 2f(n-2) + 3f(n-3) if n >= 3
 * @author anuvabanwasi
 *
 */

public class Recursion {
	
	/**
	 * Recursively calculate value of function using logic:
	 * f(n) = n if n < 3 - base case
	 * f(n) = f(n-1) + 2f(n-2) + 3f(n-3) if n >= 3 - recursive case
	 * @param n input value for function
	 * @return value of function f(n)
	 */

	private int calcFunction(int n) {
		//base case for recursion to terminate, f(n) = n if n < 3
		if (n < 3)
			return n;
		else
			// recursively compute value of function as f(n) = f(n-1) + 2f(n-2) + 3f(n-3)
			return calcFunction(n - 1) + 2 * calcFunction(n - 2) + 3 * calcFunction(n - 3);
	}
	
	/**
	 * Using Iteration calculate value of function using logic:
	 * f(n) = n if n < 3
	 * f(n) = f(n-1) + 2f(n-2) + 3f(n-3) if n >= 3
	 * @param n input value for function
	 * @return  value of function f(n)
	 */

	private int calcFunctionIterative(int n) {
		int[] arr = new int[n+1];
		
		for (int i = 0; i <= n; i++) 
			if(i < 3)
				arr[i] = i;
			else
				arr[i] = arr[i-1] + 2 * arr[i-2] + 3 * arr[i-3];

		return arr[n];
	}
	
	/**
	 * Main method that invokes calcFunction and calcFunctionIterative
	 * @param args
	 * @throws Exception
	 */
	
	   public static void main(String[] args) throws Exception {
         
       Recursion r = new Recursion();
       try {
	       int n = Integer.parseInt(args[0]);
	       int output1 = r.calcFunction(n);
		   int output2 = r.calcFunctionIterative(n);
		   System.out.println("Value of Function calculated recursively "+ output1);
		   System.out.println("Value of Function calculated iteratively "+ output2);
		   if (output1 == output2)
			System.out.println("Success!");
		   else
			System.out.println("Something is wrong. Value of Function calculated recursively differs from that calculated iteratively.");
       }
       catch (Exception e) {
    	        System.out.println("\nUsage: \"java Recursion <n> (must be positive integer)>\"\n");
    	   		e.printStackTrace();
       }
      
   }
}
