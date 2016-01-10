package algorithms.search;

import org.junit.Before;
import org.junit.Test;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.mazeGenerator.Position;
import static org.junit.Assert.*;

public class AStarTest {

	Maze3d maze;
	MyMaze3dGenerator myGenerator;
	Maze3dSearchable<Position> mazeSearch;
	

	@Before
	public void setUp(){
		myGenerator = new MyMaze3dGenerator();
		maze = myGenerator.generate(5, 5, 5);
		mazeSearch = new Maze3dSearchable<Position>(maze, 10);
	}
	
	@Test
	public void testsolA(){
		AStar<Position> astar = new AStar<>(new ManhattanDistance());
		Solution<Position> sol = astar.search(mazeSearch);
		assertTrue("Error instance", sol instanceof Solution);
	}
	
	@Test
	public void testsolM(){
		AStar<Position> man = new AStar<>(new ManhattanDistance());
		Solution<Position> sol = man.search(mazeSearch);
		assertTrue("Error get class",sol.getClass() == Solution.class);
	}
	
	@Test
	public void testGetNodesManhattanAfter(){
		AStar<Position> man = new AStar<>(new ManhattanDistance());
		@SuppressWarnings("unused")
		Solution<Position> sol = man.search(mazeSearch);
		assertTrue("Error get nodes",man.getNumberOfNodesEvaluated() > 0);
	}
	
	@Test
	public void testGetManhattanBefor(){
		AStar<Position> man = new AStar<>(new ManhattanDistance());
		assertTrue("Error get nodes",man.getNumberOfNodesEvaluated() == 0);
	}
	
	@Test
	public void testGetNodesAirAfter(){
		AStar<Position> astar = new AStar<>(new ManhattanDistance());
		@SuppressWarnings("unused")
		Solution<Position> sol = astar.search(mazeSearch);
		assertTrue("Error get nodes",astar.getNumberOfNodesEvaluated() > 0);
	}
	
	@Test
	public void testGetAirBefor(){
		AStar<Position> astar = new AStar<>(new ManhattanDistance());
		assertTrue("Error get nodes",astar.getNumberOfNodesEvaluated() == 0);
	}

}
