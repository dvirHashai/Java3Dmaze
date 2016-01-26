package view;
import java.util.Observable;
public class MazeDisplayAdapter extends Observable {
	MazeDisplayer mazePainter;
	boolean in = true;
	Boolean winMsg = false;
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
					mazePainter.setFocus();
					System.out.println("re");
					in =false;
				}
				
				
			}
		});
	}
	public void exit(){
		in = false;
		mazePainter.exitAndDisposMazeDisplayer();
		
	}
	
	
}
