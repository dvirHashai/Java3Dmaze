package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

public class Maze3D extends MazeDisplayer {
	public Position startPosition = maze.getStartPosition();
	public Position goalPosition = maze.getGoalPosition();
	public Position curentPosition = startPosition;
	
	
	
	
	public void paintCube(double[] p, double h, PaintEvent e) {
		int[] f = new int[p.length];
		for (int k = 0; k < f.length; f[k] = (int) Math.round(p[k]), k++)
			;

		e.gc.drawPolygon(f);

		int[] r = f.clone();
		for (int k = 1; k < r.length; r[k] = f[k] - (int) (h), k += 2)
			;

		int[] b = { r[0], r[1], r[2], r[3], f[2], f[3], f[0], f[1] };
		e.gc.drawPolygon(b);
		int[] fr = { r[6], r[7], r[4], r[5], f[4], f[5], f[6], f[7] };
		e.gc.drawPolygon(fr);

		e.gc.fillPolygon(r);

	}

	public Maze3D(Composite parent, int style) {
		super(parent, style);
		
		final Color white = new Color(null, 255, 255, 255);
		//final Color black = new Color(null, 150, 150, 150);
		setBackground(white);

		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				
				try {
					if(maze != null){
				
				System.out.println("ss");
				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

				int width = getSize().x;
				int height = getSize().y;

				int mx = width / 2;

				double w = (double) width / maze.getMaze3d()[1][1].length;
				double h = (double) height / maze.getMaze3d()[1].length;

				for (int i = 1; i < maze.getMaze3d()[curentPosition.getDimension()][i].length; i++) {
					double w0 = 0.7 * w + 0.3 * w * i / maze.getMaze3d()[1].length;
					double w1 = 0.7 * w + 0.3 * w * (i + 1) / maze.getMaze3d()[1].length;
					double start = mx - w0 * maze.getMaze3d()[1][i].length / 2;
					double start1 = mx - w1 * maze.getMaze3d()[1][i].length / 2;
					for (int j = 1; j < maze.getMaze3d()[curentPosition.getDimension()][i].length; j++) {
						double[] dpoints = { start + j * w0, i * h, start + j * w0 + w0, i * h, start1 + j * w1 + w1,
								i * h + h, start1 + j * w1, i * h + h };
						double cheight = h / 2;
						
						if (maze.getMaze3d()[1][i][j] != 0)
							paintCube(dpoints, cheight, e);

						if (i == row && j == Column) {
							e.gc.setBackground(new Color(null, 200, 0, 0));
							e.gc.fillOval((int) Math.round(dpoints[0]), (int) Math.round(dpoints[1] - cheight / 2),
									(int) Math.round((w0 + w1) / 2), (int) Math.round(h));
							e.gc.setBackground(new Color(null, 255, 0, 0));
							e.gc.fillOval((int) Math.round(dpoints[0] + 2),
									(int) Math.round(dpoints[1] - cheight / 2 + 2),
									(int) Math.round((w0 + w1) / 2 / 1.5), (int) Math.round(h / 1.5));
							e.gc.setBackground(new Color(null, 0, 0, 0));
						}
						
					}
				}
					}
				
		}catch (Exception e2) {
				
			}
		}
		
});	
	}	
	
	
	private void moveCharacter(int x, int y, int z) {
		if (x >= 0 && x < maze.getMaze3d().length && y >= 0 && y < maze.getMaze3d()[0][0].length && z >= 0
				&& z < maze.getMaze3d()[0].length && maze.getMaze3d()[x][y][z] == 0) {
			dimension = x;
			row = y;
			Column = z;
			getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					redraw();
				}
			});
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
	public void setCharacterPosition(Maze3d maze) {
		Position p = maze.getStartPosition();
		this.dimension = p.getDimension();
		this.row = p.getRows();
		this.Column = p.getColumns();
		moveCharacter(dimension, Column, row);
	}

	@Override
	public void upFloor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downFloor() {
		// TODO Auto-generated method stub
		
	}


}