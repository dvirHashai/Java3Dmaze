package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public abstract class GameCharecter extends Canvas {

	protected int axis1, axis2, width, height;
	protected int oldAxis1, oldAxis2;

	public GameCharecter(Composite parent,int style){
        super(parent, SWT.DOUBLE_BUFFERED);

		parent.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				e.gc.setAntialias(SWT.ON);
				e.gc.setInterpolation(SWT.HIGH);

				drawCharacter(e.gc, getAxis1(), getAxis2(), getWidth(), getHeight());
			}
		});
	}

	public abstract void drawCharacter(GC gc, int axis1, int axis2, int width, int height);

	public int getAxis1() {
		return axis1;
	}

	public void setAxis1(int axis1) {
		this.oldAxis1 = this.axis1;
		this.axis1 = axis1;
	}

	public int getAxis2() {
		return axis2;
	}

	public void setAxis2(int axis2) {
		this.oldAxis2 = this.axis2;
		this.axis2 = axis2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}