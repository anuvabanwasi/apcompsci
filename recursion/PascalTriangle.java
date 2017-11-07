/**
 * This Class Prints out values of a Pascal Triangle. 
 * The program expects a row number to print out the Pascal Triangle Numbers from 1 to the input row number  
 * @author anuvabanwasi
 *
 */

public class PascalTriangle {
	
	/**
	 * Print values
	 * @param r Pascal Triangle Max Row Number to generate triangle
	 */
	
	private void printPascalNumbers(int r) {
	//start from row 1 and go upto Pascal Triangle Max Row Number
       for (int row = 0; row < r; row++) {
    	       //start from col 1 and iterate till col = current row number
           for (int col = 0; col <= row; col++) {
               System.out.print(computePascalNumbers(row, col) + " ");
           }
           System.out.println();
       }
   }
	
	/**
	 * Compute Pascal Triangle Values based on the Previous Row Values - Recursive Method
	 * @param row
	 * @param col
	 * @return computed Pascal Triangle values
	 */

   private int computePascalNumbers(int row, int col) {
	   //base case (when recursion terminates) for Pascal Triangle values is when (row, 0) or (row, row) = 1
	   if (col == 0 || row == col ) {
	       return 1;
	   } else {
		   //Add (previous row, previous col) value to (previous row, current col) value and Recurse
	       return computePascalNumbers(row - 1, col - 1) + computePascalNumbers(row - 1, col);
	   }

   }
   
   /**
    * Main Method to Recursively Calculate and Print Pascal Triangle values
    * Usage: java PascalTriangle <Pascal Triangle Max Row Number>
    * @param args
    * @throws Exception
    */

    public static void main(String[] args) throws Exception {

       PascalTriangle ptr = new PascalTriangle();
       try {
	       int r = Integer.parseInt(args[0]);
	       if (r == 0) 
	    	   	System.out.println("Pascal Triangle Max Row Number must be an integer greater than 0");
	       else
	    	   	ptr.printPascalNumbers(r);
       }
       catch (Exception e) {
    	   		System.out.println("\nUsage: \"java PascalTriangle <Pascal Triangle Max Row Number (must be greater than 0)>\"\n");
    	   		e.printStackTrace();
       }
     
   }
    
    
    
}
