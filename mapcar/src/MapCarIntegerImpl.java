
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MapCarMapCar that operates on integers in a list
 * @author anuva banwasi
 *
 */
public class MapCarIntegerImpl extends MapCar{

	public static void main(String[] args) {
		MapCarIntegerImpl mc = new MapCarIntegerImpl();
		
		// Create a list of objects
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);

		// invoke lambda function on each element of the list
		List<Object> resultList = mc.mapcar(list);
		
		// print the result
		for (Object result : resultList) {
			System.out.print((Integer)result + " ");
		}
	}

	/**
	 * Specific implementation of the lambda function. Each class extending MapCar must provide an implementation
	 * Adds 2 to each integer in the list
	 */
	public Integer lambda(Object s) {
		return ((Integer)s).intValue() + 2;
	}

}