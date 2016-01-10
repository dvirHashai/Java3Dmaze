package view;

import java.util.Observer;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas {
	Maze3d maze;
	int dimension,row,Column;
	// just as a stub...
	/*int[][] mazeData={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
		};*/

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
		//setMaze(new Maze3d(1, 10, 10));
		
		
		
	}
	

	
	public void setMaze(Maze3d mazeData){
		
		
		this.maze = mazeData;
		this.dimension = maze.getDimension();
		this.row = maze.getRow();
		this.Column = maze.getColumn();
		System.out.println(maze.toString());
		getDisplay().syncExec(new Runnable() {
        
			@Override
			public void run() {
				redraw();
				
			}
		});
	}
	/*public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
		
	}*/
	
	public abstract  void setCharacterPosition(Maze3d maze);

	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();

}