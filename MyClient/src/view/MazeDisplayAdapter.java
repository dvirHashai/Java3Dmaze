package view;
import java.util.Observable;

public class MazeDisplayAdapter extends Observable {

	MazeDisplayer mazePainter;
	boolean in = true;
	
	public MazeDisplayAdapter(MazeDisplayer mazeDisplayer) {
		this.mazePainter = mazeDisplayer;
	}
	
	public void setPainter(Object arg){
		mazePainter.setCanvas(arg);
		setChanged();
		notifyObservers();
	}
	public MazeDisplayer getMazeDisplayer(){
		return mazePainter;
	}
	
	public void paintMaze(){
		mazePainter.getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				if (in) {
					mazePainter.redraw();
					System.out.println("re");
				}
				
				
			}
		});
	}
	
	
}
