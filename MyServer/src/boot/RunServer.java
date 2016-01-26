package boot;

import java.io.FileNotFoundException;

import ServerModel.MyModel;
import ServerPresenter.MyPresenter;
import ServerPresenter.Properties;
import ServerPresenter.ServerPropertiesHandler;
import ServerView.MyClientHandler;
import ServerView.MyServer;
import ServerView.ServerView;

public class RunServer {

	public static void main(String[] args) {
		try {
			Properties properties;
			try {
				properties = ServerPropertiesHandler.getInstance();
			} catch (FileNotFoundException e) {
				System.out.println("Could not find properties file, using default set");
				properties = new Properties();
				try {
					ServerPropertiesHandler.write(properties, "properties.xml");
				} catch (FileNotFoundException e1) {
					System.out.println("Could not save default properties file, please check manually");
				}
			}
			MyModel model = new MyModel();
			ServerView view = new ServerView();
			MyPresenter presenter = new MyPresenter(model, view);
			model.addObserver(presenter);
			view.addObserver(presenter);
			
			MyClientHandler ch = new MyClientHandler();
			@SuppressWarnings("resource")
			MyServer server = new MyServer(3333,5,ch);
			view.setClientHandler(ch);
			server.start();
			ch.addObserver(view);
			
			

		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	
		
	}

	
}
