/**
 * 
 */
package algorithms.search;

import java.util.Comparator;

/**
 * @author Chen
 *
 */
public class StateComparator<T> implements Comparator<State<T>> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(State<T> arg0, State<T> arg1) {
		if (arg0.getCost() < arg1.getCost()) {
			return -1;
		}
		else if (arg0.getCost() == arg1.getCost()) {
			return 0;
		}
		else {
			return 1;
		}
	}

}
