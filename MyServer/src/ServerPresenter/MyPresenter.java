package ServerPresenter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import ServerModel.Model;
import ServerView.InterfaceServerView;
public class MyPresenter extends MyAbstractPresenter {
	public MyPresenter(Model m, InterfaceServerView v) {
		super(m, v);
	}
	@Override
	public void update(Observable o, Object arg) {
		if (o == model) {
			if (arg != null) {
				
				view.DisplySolution(arg, model.getNotify());
				
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