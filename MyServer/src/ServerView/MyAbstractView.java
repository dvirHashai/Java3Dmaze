package ServerView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
public abstract class MyAbstractView extends Observable implements InterfaeServerView, Observer {
	
	protected String commandRegex;
	protected ArrayList<Object> userCommand;
	protected MyServer client;

	protected MyClientHandler ch;
	
	public MyAbstractView() {
	}
	
	/**
	 * start method
	 */
	public void start() {
		//this.client.start();
	}
	public void setUserCommand(ArrayList<Object> object) {
		
		this.userCommand = object;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#getUserCommand(java.lang.String[])
	 */
	@Override
	public ArrayList<Object> getUserCommand() {
		return this.userCommand;
	}
	
	public void setClientHandler(MyClientHandler clientHandler){
		
		this.ch = clientHandler;
	}
	
	
	@Override
	public void setCommandRegex(String commandRegex) {
		this.commandRegex = commandRegex;
	}
	public String getCommandRegex() {
		return commandRegex;
	}
	/**
	 * @param client
	 */
	public void setClient(MyServer cli) {
		this.client = cli;
	}
	/**
	 * ShutDown method
	 */
	public void shutdown() {
	}
	/**
	 * @param run
	 */
	abstract public void startRunable(Runnable run);
	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#sendDisplySolution(java.lang.String[])
	 */
	@Override
	abstract public void sendDisplySolution(String[] solution);
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {

		if(o == ch){
			setCommandRegex(((ArrayList<Object>)arg).get(0).toString());
			setUserCommand(((ArrayList<Object>)arg));
			
			setChanged();
            notifyObservers();
		}

	}
	
	@Override
	public void DisplySolution(Object arg,String string) {
		ch.getResponse(arg,string);
		
		
	}
	
	
			
	}
