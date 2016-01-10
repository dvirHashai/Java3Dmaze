package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import UserCommands.ICommand;

public class CLI extends Thread {

	HashMap<String, ICommand> map;

	/**
	 * Input source (standard input , fill,communication channel, etc)
	 */
	private BufferedReader in;
	/**
	 * output source (standard input , fill,communication channel, etc)
	 */
	private PrintWriter out;

	/**
	 * data member view
	 */
	private View view;

	/**
	 * @param in
	 * @param out
	 * @param map
	 * @param view
	 */
	// constructor to initialize data members of CLI
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, ICommand> map, View view) {

		this.in = in;
		this.out = out;
		this.map = map;
		this.view = view;
	}

	/**
	 * @return
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * @param in
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * @return
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * @param out
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}

	/**
	 * @param map
	 */
	public void setMap(HashMap<String, ICommand> map) {
		this.map = map;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#start()
	 */
	// to start the program, the user will insert he's command and the system
	// will check it,
	// if its OK and the command Contained the data base we will send message to
	// view otherwise the system will ask to insert new command
	public void start() {

		view.startRunable(new Runnable() {

			@Override
			public void run() {
				out.println("Enter your command: ");
				try {
					String line = null;

					while (!(line = in.readLine()).equals("exit")) {

						boolean commandOk = false;

						String commandRegex = null;

						Iterator<String> regexIt = map.keySet().iterator();
						while (regexIt.hasNext() && !commandOk) {
							commandRegex = regexIt.next();
							commandOk = line.matches(commandRegex);

						}

						if (commandOk) {
							out.println("your command: " + line + " " + "is found in database");
							out.println("start opertion...");
							view.sendCommand(commandRegex);
							view.sendMsg(line.split(" "));
							out.println("Enter your command: ");
						}

						else {
							out.println("sorry your command: " + line + " " + "undefined");
							out.println("Enter your command: ");
						}
					}
					exit();
				}

				catch (IOException e) {

					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * EXIT
	 */
	// to free all the open files and open threads
	public void exit() {
		view.shutdown();
	}

	/**
	 * @param solution
	 */
	// to display our solution to the screen
	public void displySolution(String[] solution) {
		for (String string : solution) {
			out.println(string);
		}

	}

}
