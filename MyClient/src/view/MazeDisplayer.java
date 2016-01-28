package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
public abstract class MazeDisplayer extends Canvas  {
	Position startPosition ;
	Position goalPosition ;
	Position curentPosition ;
	Position checker;
	Maze3d maze;
	
	Image back ;
	Image charecter ;
	Image win ;
	Image sol ;
	Image finish ;
	Image wall ;
	Image pipeUp ;
	Image pipeDown ;
	
	Clip sound;
	boolean closeSound = false;
	ArrayList<Image> mazePicList;
	boolean closePaint = false;
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
		mazePicList = new ArrayList<>();
		mazePicList.add(back = new Image(getDisplay(),"back1.jpg"));
		back = new Image(getDisplay(),"back1.jpg");
		mazePicList.add(charecter = new Image(getDisplay(),"character.png"));
		mazePicList.add(win = new Image(getDisplay(),"win.png"));
		mazePicList.add( sol = new Image(getDisplay(),"sol.png"));
		mazePicList.add(finish = new Image(getDisplay(),"finish.png"));
		mazePicList.add( wall = new Image(getDisplay(),"wall.png"));
		mazePicList.add(pipeUp = new Image(getDisplay(),"pipeUp.png"));
		mazePicList.add(pipeDown = new Image(getDisplay(),"pipeDown.png"));
	}

	public void setCanvas(Object arg){
		System.out.println("fff");
		if(arg.getClass() == Maze3d.class){
		this.maze = (Maze3d)arg;
		this.dimension = maze.getDimension();
		this.row = maze.getRow();
		this.Column = maze.getColumn();
		startPosition = maze.getStartPosition();
		goalPosition = maze.getGoalPosition();
		curentPosition = maze.getStartPosition();
		
		
		}
		if(arg.getClass() == State.class){
			System.out.println("state");
			@SuppressWarnings("unchecked")
			State<Position> state = (State<Position>)arg;
			curentPosition = state.getState();
			
		}
		System.out.println(curentPosition.toString());
		System.out.println(maze.toString());
		if(curentPosition.equals(goalPosition)){
			closeSound = true;
		}
		/*if(closePaint){
			Thread.currentThread().stop();
			
		}*/
	/*	getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
			//Toolkit.getDefaultToolkit().getScreenSize();
			redraw();
				
			}*/
			
		//});
	}
		public void exitAndDisposMazeDisplayer(){
			for (Image is : mazePicList) {
				is.dispose();
			}
			this.dispose();
			
		}
		
		public void DisposMazePicList(){
			for (Image is : mazePicList) {
				is.dispose();
			}
		
		
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
	
	public void playSound(File file) {
		try {
			sound = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			sound.open(inputStream);
			sound.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean musicCheck(){
		if( closeSound)
			return true;
		return false;
	}
	
	/*public void Exit(){
		Display.class.di
		
		
	};*/
	
	
		
	
}
	//public abstract void winner();
