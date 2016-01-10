package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Controller.Controller;
import Controller.MyController;

import Model.Model;
import Model.MyModel;
import view.CLI;
import view.MyView;
import view.View;

public class Run<T> {

	public static void main(String[] args) throws IOException {
		Controller controller = new MyController();
		View view = new MyView(controller);
		Model model = new MyModel(controller);
		controller.setModel(model);
		controller.setView(view);
		controller.setHashMap();
		CLI client = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true),
				controller.getCommand(), view);
		view.setClient(client);
		view.start();
	}

}
