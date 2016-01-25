package presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import model.Model;
import view.View;

public class MyPresenter extends MyAbstractPresenter {

	public MyPresenter(Model m, View v) {
		super(m, v);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == model) {
			if (arg != null) {
				//System.out.println(model.getNotify().toString());
				view.DisplySolution(arg,model.getNotify());
				
			} else {
				String[] s = model.getupdateData();
				view.sendDisplySolution(s);
			}

		}
		if (o == view) {
			String command = view.getCommandRegex();
			ArrayList<Object> msg = view.getUserCommand();
			try {
				CommandsMap.get(command).docommand(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
