package view;

import java.util.ArrayList;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
import algorithms.search.State;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas {
	Position startPosition ;
	Position goalPosition ;
	Position curentPosition ;
	Position checker;
	Maze3d maze;
	ArrayList<State<Position>> solList;
	int dimension,row,Column;
	// just as a stub...
	int[][][] mazeData={
			
			{
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1},
				{1,0,0,1,1,1,1,1,0,0,1,0,1,0,1,1,1},
				{1,1,1,1,0,0,0,1,0,1,1,0,1,0,0,1,1},
				{1,1,0,1,0,1,1,1,0,0,0,0,1,1,0,1,1},
				{1,1,1,0,0,0,1,0,0,1,1,1,1,0,0,1,1},
				{1,1,0,0,1,0,0,1,0,0,0,0,1,0,1,1,1},
				{1,1,0,1,1,0,1,1,0,1,1,0,0,0,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		},
			{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1},
			{1,0,0,1,1,1,1,1,0,0,1,0,1,0,1,1,1},
			{1,1,1,1,0,0,0,1,0,1,1,0,1,0,0,1,1},
			{1,1,0,1,0,1,1,1,0,0,0,0,1,1,0,1,1},
			{1,1,1,0,0,0,1,0,0,1,1,1,1,0,0,1,1},
			{1,1,0,0,1,0,0,1,0,0,0,0,1,0,1,1,1},
			{1,1,0,1,1,0,1,1,0,1,1,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		},
			{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1},
			{1,0,0,1,1,1,1,1,0,0,1,0,1,0,1,1,1},
			{1,1,1,1,0,0,0,1,0,1,1,0,1,0,0,1,1},
			{1,1,0,1,0,1,1,1,0,0,0,0,1,1,0,1,1},
			{1,1,1,0,0,0,1,0,0,1,1,1,1,0,0,1,1},
			{1,1,0,0,1,0,0,1,0,0,0,0,1,0,1,1,1},
			{1,1,0,1,1,0,1,1,0,1,1,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		}
	};

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
		new Maze3d(1, 10, 10);
		solList =new ArrayList<>();
	}

	public void setMaze(Maze3d mazeData){
		
		
		this.maze = mazeData;
		this.dimension = maze.getDimension();
		this.row = maze.getRow();
		this.Column = maze.getColumn();
		startPosition = maze.getStartPosition();
		goalPosition = maze.getGoalPosition();
		curentPosition = maze.getStartPosition();

		System.out.println(maze.toString());
	}
	public void key(KeyListener keyListener){
		addKeyListener(new KeyAdapter() {
			
		});
	}
	public abstract  void setCharacterPosition(Maze3d maze);

	public abstract void moveCharacterUp();

	public abstract  void moveCharacterDown();

	public abstract  void moveCharacterLeft();

	public  abstract void moveCharacterRight();

	public abstract void moveCharacterUpFloor();

	public abstract void moveCharacterDownFloor();
	
	public void displayerSol(ArrayList<State<Position>> solution){
		
		
		//System.out.println("a");
		for (State<Position> state : solution) {
			solList.add(state);
		}
		
		
	};
	
	
		
	
}
	//public abstract void winner();
