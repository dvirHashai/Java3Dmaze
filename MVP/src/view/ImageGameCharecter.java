package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class ImageGameCharecter extends GameCharecter {

	private Image image;

	public ImageGameCharecter(Composite parent, int style, Image image) {
		super(parent, SWT.DOUBLE_BUFFERED);

		this.image = image;

		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				// TODO Auto-generated method stub
				image.dispose();
			}
		});
	}

	@Override
	public void drawCharacter(GC gc, int axis1, int axis2, int width, int height) {
		gc.setAlpha(255);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, axis1, axis2,
				(int) Math.round(width * ((double) image.getBounds().width / image.getBounds().height)),
				(int) Math.round(height * ((double) image.getBounds().height / image.getBounds().width)));
	}
}
