package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.mazeGenerator.Position;
import algorithms.search.AStar;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Maze3dSearchable;
import algorithms.search.Searchable;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;


/**
 * @param <T> generic type
 * Demo class to run the project and check 3 type of searchers and the difference between them
 */
public class Demo<T> {

	public static void  run() throws IOException {
		
		MyMaze3dGenerator myMazeGenerator = new MyMaze3dGenerator();
		Maze3d maze = myMazeGenerator.generate(5, 5, 5);
		System.out.println(maze);
		Searchable<Position> acter = new Maze3dSearchable<Position>(maze);
		BFS<Position> bfs = new BFS<Position>();
		AStar<Position> astar = new AStar<Position>(new ManhattanDistance());
		AStar<Position> astar1 = new AStar<Position>(new AirDistance());
		System.out.println("******BFS-PATH******");
		bfs.search(acter);
		System.out.println("evaluatedNodes:" + bfs.getNumberOfNodesEvaluated());
		System.out.println("********************");
		System.out.println("******Astar-Manhattan*********");
		astar.search(acter);
		System.out.println("evaluatedNodes:" + astar.getNumberOfNodesEvaluated());
		System.out.println("********************");
		System.out.println("******Astar-AirDistance*********");
		astar1.search(acter);
		System.out.println("evaluatedNodes:" + astar1.getNumberOfNodesEvaluated());
		System.out.println("********************");
		
		
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		
		System.out.println(loaded.equals(maze));
		
		
		
	}
	
	public static void main (String[] args) throws IOException{
		
		Demo.run();
	}
	
}

