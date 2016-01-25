package boot;

import java.io.FileNotFoundException;

import javax.swing.plaf.basic.BasicComboBoxUI.PropertyChangeHandler;

import model.MyModel;

import presenter.MyPresenter;
import presenter.Properties;
import presenter.PropertiesHandler;
import view.MyClientHandler;
import view.MyServer;
import view.MyView;

public class RunServer {

	public static void main(String[] args) {
		try {
			Properties properties;
			try {
				properties = PropertiesHandler.getInstance();
			} catch (FileNotFoundException e) {
				System.out.println("Could not find properties file, using default set");
				properties = new Properties();
				try {
					PropertiesHandler.write(properties, "properties.xml");
				} catch (FileNotFoundException e1) {
					System.out.println("Could not save default properties file, please check manually");
				}
			}
			MyModel model = new MyModel();
			MyView view = new MyView();
			MyPresenter presenter = new MyPresenter(model, view);
			model.addObserver(presenter);
			view.addObserver(presenter);
			
			MyClientHandler ch = new MyClientHandler();
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
