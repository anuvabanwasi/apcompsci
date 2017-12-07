import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MapCar that operates on Strings in a list
 * @author Anuva Banwasi
 *
 */
public class MapCarTitleCaseImpl extends MapCar{
	
	public static void main(String[] args) {
		MapCarTitleCaseImpl mc = new MapCarTitleCaseImpl();
		
		// Create a list of objects
		List<Object> list = new ArrayList<Object>();
		list.add("anna");
		list.add("brian");
		list.add("charlie");

		// invoke lambda function on each element of the list
		List<Object> resultList = mc.mapcar(list);

		// print the result
		for (Object result : resultList) {
			System.out.print(result + " ");
		}
	}

	/**
	 * Specific implementation of the lambda function. Each class extending MapCar must provide an implementation
	 * Converts the first character of incoming string to upper case (CamelCase)
	 */
	public String lambda(Object x) {
		return ((String)x).substring(0,1).toUpperCase() + ((String)x).substring(1);
	}
}
