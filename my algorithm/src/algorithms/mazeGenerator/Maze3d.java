package algorithms.mazeGenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class Maze3d.
 */

public class Maze3d implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6737728978375703196L;

	/** The begin. */
	private Position StartPosition;
	
	/** The goal. */
	private Position GoalPosition;
	
	/** The maze. */
	private int[][][] maze3d;

	private int dimension;
	private int column;
	private int row;

	
	/**
	 * 
	 * 
	 * @param height
	 * @param row
	 * @param column
	 *
	 */
	// crating a casing for the maze 
	// add two cells to the maze and prevent exceptions for the start position while we try to step back;     
	public Maze3d(int dimensions, int rows, int columns) {
		this.dimension = dimensions + 2;
		this.column = columns + 2;
		this.row = rows + 2;
		this.maze3d = new int[dimensions + 2][rows + 2][columns + 2];
	}
	
	
	
	/**
	 * read data from file and create our maze3d
	 * @param bytearray
	 * @throws IOException
	 */
	// Constructor by file
	public Maze3d(byte[] bytearray) throws IOException {
	 
		ByteArrayInputStream inArray= new ByteArrayInputStream(bytearray);
		DataInputStream data = new DataInputStream(inArray);
		this.dimension = data.readInt();
		this.row = data.readInt();
		this.column = data.readInt();
		StartPosition = new Position(data.readInt(),data.readInt(),data.readInt());
		GoalPosition = new Position(data.readInt(),data.readInt(),data.readInt());
		
		this.maze3d = new int[this.dimension][this.row][this.column];
		
		for (int i = 0; i < this.getDimension(); i++) {
			for (int j = 0; j < this.getRow(); j++) {
				for (int j2 = 0; j2 < this.getColumn(); j2++) {
					this.maze3d[i][j][j2] = data.readByte();
				}
			}
		}
	}
	
	

	/**
	 * Gets the begin.
	 *
	 * @return the begin state
	 */
	public Position getStartPosition() {
		return StartPosition;
	}

	/**
	 * Sets the begin. Get the start position and change the index value to 0
	 * 
	 * @param begin
	 */
	public void setStartPosition(Position startPosition) {
		this.StartPosition = startPosition;
	}
	

	/**
	 * Gets the end.
	 *
	 * @return the goal state
	 */
	public Position getGoalPosition() {
		return GoalPosition;
	}

	/**
	 * Sets the goal state.
	 * 
	 * @param Position
	 */
	public void setGoalPosition(Position goalPosition) {
		this.GoalPosition = goalPosition;
	}

	public int[][][] getMaze3d() {
		return maze3d;
	}

	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column length
	 */
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}


	/**
	 * Sets the index. Get Position and change the index value to the number
	 * 
	 * @param Position
	 * @param number
	 */
	public void setMaze3dIndex(Position in, int num) {
		this.maze3d[in.getDimension()][in.getRows()][in.getColumns()] = num;
	}

	public void setIndex(int dimenstion, int rows, int columns,int num){
		maze3d[dimenstion][rows][columns]=num;
		
	}
	
	
	/**
	 * Gets the index.
	 *
	 * @param dimension
	 * @param row
	 * @param column
	 * @return the index value
	 */
	public int getmaze3dIndex(int dimenstion, int rows, int columns) {
		return this.maze3d[dimenstion][rows][columns];
	}

	/**
	 * Gets the index.
	 *
	 * @param Position
	 * @return the index value
	 */
	public int getmaze3dIndex(Position position) {
		return this.maze3d[position.getDimension()][position.getRows()][position
				.getColumns()];
	}

	
	/**
	 * Gets the possible move. Receiving position and check where he can move
	 * (near index value = 0)
	 * 
	 * @param Position
	 * @return possible move in ArryList<Position>
	 */
	
	public ArrayList<Position> PossibleMoves(Position position) {
		ArrayList<Position> possibleMoveList = new ArrayList<>();

		Position positionUp = Position.MergerPos(position, Position.UP);
		Position positionDown = Position.MergerPos(position, Position.DOWN);
		Position positionRight = Position.MergerPos(position, Position.RIGHT);
		Position positionLeft = Position.MergerPos(position, Position.LEFT);
		Position positionBackward = Position.MergerPos(position, Position.BACKWARD);
		Position positionForward = Position.MergerPos(position, Position.FORWARD);

		if (getmaze3dIndex(positionUp) == 0) {
			possibleMoveList.add((positionUp));
		}

		if (getmaze3dIndex(positionDown) == 0) {
			possibleMoveList.add((positionDown));
		}

		if (getmaze3dIndex(positionRight) == 0) {
			possibleMoveList.add((positionRight));
		}

		if (getmaze3dIndex(positionLeft) == 0) {
			possibleMoveList.add((positionLeft));
		}

		if (getmaze3dIndex(positionBackward) == 0) {
			possibleMoveList.add((positionBackward));
		}

		if (getmaze3dIndex(positionForward) == 0) {
			possibleMoveList.add((positionForward));
		}

		return possibleMoveList;
	}

	
	/**
	 * Gets the possible move. Receiving position and check where he can move
	 * (near index value = 0)
	 * 
	 * @param Position
	 * @return possible move in string[]
	 */
	
	public String[] getPossibleMoves(Position p) {
		String[] possibleMove = new String[PossibleMoves(p).size()];
		
		for (int i = 0; i < possibleMove.length; i++) {
			possibleMove[i] = PossibleMoves(p).get(i).toString();
		}
		
		return possibleMove;
		
	}

	/**
	 * Gets the cross section by x. cross the maze by column
	 * 
	 * @param int
	 *            column
	 * @return the cross section by x int[]
	 */
	
	public int[][] getCrossSectionByX(int num) {
		int[][] x = new int[getDimension() - 2][getRow() - 2];
		for (int i = 1; i < getDimension() - 2; i++) {
			for (int j = 1; j < getRow() - 2; j++) {
				x[i][j] = maze3d[i][j][num];
			}
		}
		return x;
	}

	/**
	 * Gets the cross section by y. cross the maze by dimension
	 * @param int
	 *            dimension
	 * @return the cross section by y int[]
	 */
	public int[][] getCrossSectionByY(int num) {
		int[][] y = new int[getRow() - 2][getColumn() - 2];
		for (int i = 1; i < getRow() - 2; i++) {
			for (int j = 1; j < getColumn() - 2; j++) {
				y[i][j] = maze3d[num][i][j];
			}
		}
		return y;
	}

	/**
	 * Gets the cross section by z. cross the maze by row
	 * 
	 * @param int
	 *            row
	 * @return the cross section by z int[]
	 */
	public int[][] getCrossSectionByZ(int num) {
		int[][] z = new int[getDimension() - 2][getColumn() - 2];
		for (int i = 1; i < getDimension() - 2; i++) {
			for (int j = 1; j < getColumn() - 2; j++) {
				z[i][j] = maze3d[i][num][j];
			}
		}
		return z;
	}

	
	/**
	 * Prints the cross int[].
	 *
	 * @param int[]
	 *            maze2d
	 */
	static public void printCross(int[][] maze2d) {
		for (int i = 0; i < maze2d.length; i++) {
			if (i != 0) {
			}
			for (int j = 0; j < maze2d[0].length; j++) {
				if (j == 0) {
					System.out.print("{");
				}
				System.out.print(maze2d[i][j]);
				if (j != maze2d[0].length - 1) {
					System.out.print(",");
				} else {
					System.out.println("}");
				}
			}
		}
	}

	public String CrossToString(int[][] maze2d) {
		String s = new String();
		for (int i = 1; i < maze2d.length - 1; i++) {
			if (i != 0) {
			}
			for (int j = 1; j < maze2d[0].length - 1; j++) {
				if (j == 0) {
					s += "{";
				}
				s += maze2d[i][j];
				if (j != maze2d[0].length - 1) {
					s += ",";
				} else {
					s += "}";
					s += "\n";
				}
			}
		}
		return s;
	}

	@Override
	public boolean equals(Object other) {
		Maze3d m = (Maze3d) other;
		if (getDimension() == m.getDimension() && getRow() == m.getRow()
				&& getColumn() == m.getColumn()) {
			if (StartPosition.equals(m.getStartPosition())
					&& GoalPosition.equals(m.getGoalPosition())) {
				for (int i = 0; i < maze3d.length; i++) {
					for (int j = 0; j < maze3d[0].length; j++) {
						for (int j2 = 0; j2 < maze3d[0][0].length; j2++) {
							if (maze3d[i][j][j2] != m.getmaze3dIndex(i, j, j2)) {
								return false;
							}
						}
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	

	public String toString() {
		String s = new String();

		for (int i = 1; i < dimension - 1 ; i++) {
			for (int j = 1; j < row - 1 ; j++) {
				for (int j2 = 1; j2 < column - 1 ; j2++) {
					s += maze3d[i][j][j2] + " ";
				}

				s += "\n";
			}

			s += "\n";
		}
		return s;
	}

	
/**
 * 1) create dynamic byte array from  ByteArrayOutputStream
 * 2) cover up the byte array with DataOutputStream to let us use the method writeint (get int and count int as 4 bytes)
 * 3) use write int to write all the impotent data from the maze into the bytearray     
 * @return byte of array 
 * @throws IOException
 */

// the method convert primitive to byte of array
public byte[] toByteArray() throws IOException{
	
	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
	DataOutputStream data = new DataOutputStream(byteArray);
	data.writeInt(this.getDimension());
	data.writeInt(this.getRow());
	data.writeInt(this.getColumn());
	data.writeInt(this.getStartPosition().dimension);
	data.writeInt(this.getStartPosition().rows);
	data.writeInt(this.getStartPosition().columns);
	data.writeInt(this.getGoalPosition().dimension);
	data.writeInt(this.getGoalPosition().rows);
	data.writeInt(this.getGoalPosition().columns);
	
	
	for (int i = 0; i < this.getDimension(); i++) {
		for (int j = 0; j < this.getRow(); j++) {
			for (int j2 = 0; j2 < this.getRow(); j2++) {
				data.writeByte(maze3d[i][j][j2]);;
				
			}
		}
	}
	
	
	return byteArray.toByteArray();
	
}
	
	
}
