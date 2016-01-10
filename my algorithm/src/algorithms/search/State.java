package algorithms.search;

import java.io.Serializable;

/**
 * The Class State.
 *
 * @param <T> the generic type
 */
public class State<T> implements Comparable<State<T>>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7271546973830478280L;

	/** The state. */
	private T state;
	
	/** The cost. */
	private double cost;
	
	/** The came from. */
	private State<T> cameFrom;

	/**
	 * Instantiates a new state.
	 * Constructor
	 * @param state the state
	 */
	public State(T state) {
		this.setState(state);
		this.cost = 10;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Gets the state.
	 *
	 * @return <T>
	 */
	public T getState() {
		return state;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost
	 */
	public void setState(T state) {
		this.state = state;
	}
	
	
	
	/**
	 * Gets the from.
	 *
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the from.
	 *
	 * @param from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		
		State<T> state = (State<T>) obj;
		
		if (this.state.equals(state.state)) {
			return true;
		}
		
		return false;
	};

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(State<T> s) {
		return (int) (this.cost - s.getCost());
	}


}