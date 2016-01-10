package algorithms.mazeGenerator;

import java.io.Serializable;

/**
 * The Class Position.
 */
public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6374320388742541070L;
	
	/**
	 * Static Final Position to control our base move from specific position  
	 */
	
	public static final Position UP = new Position(1, 0, 0);
	public static final Position DOWN = new Position(-1, 0, 0);
	public static final Position RIGHT = new Position(0, 0, 1);
	public static final Position LEFT = new Position(0, 0, -1);
	public static final Position FORWARD = new Position(0, 1, 0);
	public static final Position BACKWARD = new Position(0, -1, 0);

	/** The dimension. */
	protected int dimension;
	
	/** The column. */
	protected int columns;
	
	/** The row. */
	protected int rows;

	public Position position;
		
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Instantiates a new position.
	 * Constructor
	 * @param dimension
	 * @param row
	 * @param column
	 */
	//constructor
	public Position(int dimensions, int rows, int columns) {
		this.dimension = dimensions;
		this.columns = columns;
		this.rows = rows;
	}

	/**
	 * Instantiates a new position.
	 * copy constructor
	 * @param Position
	 */
	//copy constructor
	public Position(Position p) {
		this.position = new Position(p);
		this.dimension = p.dimension;
		this.rows = p.rows;
		this.columns = p.columns;
	}


	/**
	 * Gets the dimension.
	 *
	 * @return dimension
	 */
	//return the dimension
	public int getDimension() {
		return dimension;
	}

	/**
	 * Sets the dimension.
	 *
	 * @param dimension
	 */
	//set the dimension
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	//return the column
	public int getColumns() {
		return columns;
	}

	/**
	 * Sets the column.
	 *
	 * @param column
	 */
	//set the column
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	//return the row
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the row.
	 *
	 * @param row
	 */
	//set the row
	public void setRows(int rows) {
		this.rows = rows;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//override to to string for print the index
	@Override
	public String toString() {
		return ("{" + this.dimension + "," + this.rows + "," + this.columns + "}");
	}
	
	
	/* (non-Javadoc)
	   * @see java.lang.Object#equals(java.lang.Object)
	   */
	  //override to equal for make compare between index
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			return false;

		Position position = (Position) obj;

		if (this.dimension == position.dimension && this.rows == position.rows && this.columns == position.columns) {
			return true;
		}

		return false;
	}
	
	
	
	/**
	 * Merge two positions:one specific from the maze and one from the position class .
	 *Used: making new position for checking the possible move(MyMaze3dGenerator).
	 * @param position1
	 * @param position2
	 * @return the position after we move one step
	 */
	
	// Merge two positions  
	public static Position MergerPos(Position position1, Position position2) {
		int dimension = position1.dimension + position2.dimension;
		int row = position1.rows + position2.rows;
		int column = position1.columns + position2.columns;
		
		return new Position(dimension, row, column);
	}
}
