
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MapCar that operates on Strings in a list
 *
 */
public class MapCarImpl extends MapCar {

	public static void main(String[] args) {
		MapCarImpl mc = new MapCarImpl();
		
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
	 * Converts the incoming string to upper case
	 */
	public String lambda(Object x) {
		return ((String)x).toUpperCase();
	}
}