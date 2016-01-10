package view;

public class MyView extends MyAbstractView {

	public MyView() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendDisplySolution(String[] solution) {
		client.displySolution(solution);
		
	}

/*	@Override
	public String [] getUserCommand(String[] commandRegex) {
		
		String[] msg= commandRegex;
		notifyObservers();
		return msg;
	}*/

	@Override
	public void startRunable(Runnable run) {
		this.notifyObservers(run);
		//this.
	}

}
