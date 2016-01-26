package ServerView;

public class ServerView extends MyAbstractView {

	public ServerView() {
		
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
