
	package view;

	import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import presenter.ICommand;

	

	public class CLI extends Observable {

		HashMap<String, ICommand> map;
		HashMap<String, String> regexMap;

		
		ArrayList<String[]> commandsList;
		
		
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
		//private View View;

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
			//this.View = view;
			this.commandsList = new ArrayList<>();
			this.regexMap = new HashMap<String,String>();
			setRegexMap();
		}
		
		public void setRegexMap(){
		
			regexMap.put("dir", "dir [^ \n]+");
			regexMap.put("generate", "generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}");
			regexMap.put("display", "display [^ \n]+");
			regexMap.put("display cross", "display cross section by [XYZxyz] [0-9]{1,2} for [A-Za-z0-9]+");
			regexMap.put("save", "save maze [A-Za-z0-9]+ [^ \n]+");
			regexMap.put("load", "load maze [^ \n]+ [A-Za-z0-9]+");
			regexMap.put("maze", "maze size [A-Za-z0-9]+");
			regexMap.put("file", "file size [A-Za-z0-9]+");
			regexMap.put("solve", "solve [A-Za-z0-9]+ [A-Za-z0-9]+");
			regexMap.put("display solution", "display solution [A-Za-z0-9]+");
			regexMap.put("help", "help");
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

			new Thread(new Runnable() {

				@Override
				public void run() {
					out.println("Enter your command: ");
					try {
						String line = null;

						while (!(line = in.readLine()).equals("exit")) {

							boolean commandOk = false;
							String[] command = line.split(" ");
							String commandRegex = null;
							
							if (command[0].equals("display")){
								if(command[1].equals("cross") || command[1].equals("solution")){
									commandRegex = regexMap.get(command[0]+" "+command[1]);
									commandOk = line.matches(commandRegex);
								}
								else{
									commandRegex = regexMap.get(command[0]);
									commandOk = line.matches(commandRegex);	
								}
							}
							else{
								commandRegex = regexMap.get(command[0]);
								commandOk = line.matches(commandRegex);
								
							}

							if (commandOk) {
								out.println("your command: " + line + " " + "is found in database");
								out.println("start opertion...");
								commandsList.clear();
								String[] regex = commandRegex.split("\b");
								
								commandsList.add(regex);
								commandsList.add(command);
							/*	View.setCommandRegex(commandRegex);
								View.setUserCommand(command);*/
								setChanged();
								notifyObservers();
						
								out.println("Enter your command: ");
							}

							else {
								out.println("sorry your command: " + line + " " + "undefined");
								out.println("Enter your command: ");
								setChanged();
								notifyObservers();
							}
						}
						exit();
					}

					catch (IOException e) {

						e.printStackTrace();
					}

				}
			}).start();
		}

		/**
		 * EXIT
		 */
		// to free all the open files and open threads
		public void exit() {
			//View.shutdown();
			
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



