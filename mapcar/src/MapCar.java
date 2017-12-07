
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MapCar in Java. See http://www.lee-mac.com/mapcarlambda.html for more information on what is a Mapcar
 * This MapCar is an implementation that takes in a list and a lambda function and returns a list by applying the lambda function to each element of the list
 * @author Anuva Banwasi
 *
 */
public abstract class MapCar {

	/**
	 * Abstract lambda function that must be implemented by all classes that extend MapCar
	 * @param s Object on which the lambda function will be invoked
	 * @return
	 */
	public abstract Object lambda(Object s);
	
	/**
	 * Apply the lambda function to each element of the list
	 * @param list after application of the function
	 * @return
	 */
	public List<Object> mapcar(List<Object> list) {
		List<Object> result = new ArrayList<Object>();
		list.forEach(x -> result.add(lambda(x)));
		return result;
	}
}