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
					        		e.gc.drawImage(charecter, 0, 0, charecter.getBounds().width, charecter.getBounds().height, x, y,
					        				w,h);		
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

	private void moveCharacter(int x, int y, int z) {
		if (x > 0 && x < maze.getMaze3d().length && y > 0 && y < maze.getMaze3d()[0][0].length && z > 0
				&& z < maze.getMaze3d()[0].length && maze.getMaze3d()[x][y][z] == 0) {
			dimension = x;
			row = y;
			Column = z;
			redraw();
			
			
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveUp()
	 */
	@Override
	public void moveUp() {
		int y = row;
		int z = Column;
		y = y - 1;
		moveCharacter(dimension, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveDown()
	 */
	@Override
	public void moveDown() {
		int y = row;
		int z = Column;
		y = y + 1;
		moveCharacter(dimension, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveLeft()
	 */
	@Override
	public void moveLeft() {
		int y = row;
		int z = Column;
		z = z - 1;
		moveCharacter(dimension, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MazeDisplayer#moveRight()
	 */
	@Override
	public void moveRight() {
		int y = row;
		int z = Column;
		z = z + 1;
		moveCharacter(dimension, y, z);
	}
	@Override
	public void upFloor() {
		int x = dimension;
		int y = row;
		int z = Column;
		x = x + 1;
		moveCharacter(x, y, z);
	}
	@Override
	public void downFloor() {
		int x = dimension;
		int y = row;
		int z = Column;
		x = x - 1;
		moveCharacter(x, y, z);
	}

	@Override
	public void setCharacterPosition(Maze3d maze) {
		Position p = maze.getStartPosition();
		this.dimension = p.getDimension();
		this.row = p.getRows();
		this.Column = p.getColumns();
		moveCharacter(dimension, Column, row);
	}
}