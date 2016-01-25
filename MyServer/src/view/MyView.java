package view;

import java.util.ArrayList;

public class MyView extends MyAbstractView {

	public MyView() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendDisplySolution(String[] solution) {
		//client.displySolution(solution);
		
	}



	@Override
	public void startRunable(Runnable run) {
		this.notifyObservers(run);
		//this.
	}

	



	
	
    

}
