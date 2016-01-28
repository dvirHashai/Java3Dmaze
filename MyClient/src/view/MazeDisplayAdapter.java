package view;
import java.util.Observable;

import algorithms.mazeGenerator.Position;
import algorithms.search.State;
public class MazeDisplayAdapter extends Observable {
	MazeDisplayer mazePainter;
	boolean in = true;
	Boolean winMsg = false;
	Boolean generateBG = false;
	
	public MazeDisplayAdapter(MazeDisplayer mazeDisplayer) {
		this.mazePainter = mazeDisplayer;
	}
	
	@SuppressWarnings("unchecked")
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
				if(generateBG){
					mazePainter.setBackgroundImage(mazePainter.back);
					
				}
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
