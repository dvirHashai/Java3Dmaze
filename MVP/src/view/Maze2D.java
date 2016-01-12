package view;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
public class Maze2D extends MazeDisplayer {
	
	 public Maze2D(Composite parent,int style){
	        super(parent, SWT.DOUBLE_BUFFERED);
	        
	    	// set a white background   (red, green, blue)
	    	//setBackground(new Color(null, 255, 255, 255));
	        //TODO fix the pic
	    	Image back = new Image(getDisplay(),"back1.jpg");
	    	Image charecter = new Image(getDisplay(),"charecter.jpg");
	    	Image win = new Image(getDisplay(),"win.jpg");
	    	Image sol = new Image(getDisplay(),"sol.jpg");
			setBackgroundImage(back);
	    	addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					try {
						if(maze!=null && curentPosition!=null &&startPosition!=null && goalPosition!=null){
								
					   e.gc.setForeground(new Color(null,255,255,255));
					   e.gc.setBackground(new Color(null,0,0,0));
					   int width=getSize().x;
					   int height=getSize().y;
					   int w=width/maze.getMaze3d()[1][1].length;
					   int h=height/maze.getMaze3d()[1].length;
					   for(int i=1;i<maze.getMaze3d()[curentPosition.getDimension()][i].length-1;i++)
					      for(int j=1;j<maze.getMaze3d()[curentPosition.getDimension()][j].length-1;j++){
					          int x=j*w;
					          int y=i*h;
					          if(maze.getMaze3d()[curentPosition.getDimension()][i][j]!=0)
					              e.gc.fillRectangle(x,y,w,h);
					          if (i == curentPosition.getRows() && j == curentPosition.getColumns()) {
					        	  e.gc.setAlpha(255);
					        		e.gc.drawImage(charecter, 0, 0, charecter.getBounds().width, charecter.getBounds().height, x, y,w,h);
					        		System.out.println(curentPosition);
					        		//e.gc.drawImage(image, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight);
								}
					       /*   if ( curentPosition== curentPosition) {
					        	  e.gc.setAlpha(255);
					        		e.gc.drawImage(charecter, 0, 0, charecter.getBounds().width, charecter.getBounds().height, x, y,
					        				w,h);		
								}*/
					          	  
					      }
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					}
			});
	 }

	private void moveCharacter(Position move) {
		
			
		
			
			
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveUp()
	 */
	@Override
	public void moveCharacterUp() {
	checker = curentPosition;
	System.out.println(curentPosition+"1");
	if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.BACKWARD))){	
		Position.MergerPos(curentPosition, Position.BACKWARD);
		System.out.println(curentPosition+"2");
		redraw();
	}
		//moveCharacter(checker);
	}

	
	/* (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveCharacterDown() {
		checker = curentPosition;
		
		if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.FORWARD))){
			Position.MergerPos(curentPosition, Position.FORWARD);
			redraw();
		}
			//moveCharacter(checker);
	}

	
	/*  (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveCharacterLeft() {
		checker = curentPosition;
		if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.RIGHT))){
			Position.MergerPos(curentPosition, Position.RIGHT);
			redraw();
		}
		//moveCharacter(dimension, y, z);
	}

	
	/*  (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveCharacterRight() {
		checker = curentPosition;
		if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.LEFT))){
			Position.MergerPos(curentPosition, Position.LEFT);
			redraw();
		}
		//moveCharacter(dimension, y, z);
	}
	@Override
	public void moveCharacterUpFloor() {
		checker = curentPosition;
		if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.UP))){
			Position.MergerPos(curentPosition, Position.UP);
			redraw();
		}
		//moveCharacter(x, y, z);
	}
	@Override
	public void moveCharacterDownFloor() {
		checker = curentPosition;
		if(	maze.PossibleMoves(checker).contains(Position.MergerPos(checker, Position.DOWN))){
			Position.MergerPos(curentPosition, Position.DOWN);
			redraw();
		}
		//moveCharacter(x, y, z);
	}

	@Override
	public void setCharacterPosition(Maze3d maze) {
		Position p = maze.getStartPosition();
		p.setRows(startPosition.getColumns()+1);
		moveCharacter(p);
	}


}