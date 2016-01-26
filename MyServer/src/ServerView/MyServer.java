package ServerView;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ServerPresenter.Properties;
import ServerPresenter.ServerPropertiesHandler;

public class MyServer implements Closeable {
	int port = 3333;
	ServerSocket server;
	// Socket theClient;

	ObjectOutputStream toClient;
	ObjectInputStream fromClient;

	String serverIP = "127.0.0.1";
	ArrayList<Object> commandLine;
	Object soltion;

	// View view;

	ClientHandler clinetHandler;
	int numOfClients = 3;

	ExecutorService threadpool;

	volatile boolean stop;

	Thread mainServerThread;
	Properties p;

	int clientsHandled = 0;

	/**
	 * Constructor of the server
	 * 
	 * @param port
	 * @param numOfClients
	 * @param clinetHandler
	 */
	public MyServer(int port, int numOfClients, ClientHandler clinetHandler) {

		try {
			p = ServerPropertiesHandler.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.commandLine = new ArrayList<>();

		this.port = p.getServerPort();
		this.clinetHandler = clinetHandler;
		this.numOfClients = p.getNumOfAllowedClients();
	}

	public void start() throws Exception {
		server = new ServerSocket(port);
		server.setSoTimeout(10 * 1000);
		threadpool = Executors.newFixedThreadPool(numOfClients);
		System.out.println("\tserver is alive ");

		mainServerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					try {
						final Socket someClient = server.accept();
						if (someClient != null) {
							threadpool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										clientsHandled++;
										System.out.println("\thandling client " + clientsHandled);

										clinetHandler.handleClient(someClient.getInputStream(),
												someClient.getOutputStream());
										System.out.println("after");
										while (true) {

											if ((clinetHandler.checkClose() != true)) {
												// Thread.sleep(3000);
												System.out.println("the socket is closed");
												someClient.close();

												System.out.println("\tdone handling client " + clientsHandled);
												if (clinetHandler.getClose()) {
													close();
												}
												return;
											}

										}

									} catch (IOException e) {
										e.printStackTrace();
									} catch (Exception e) {

										e.printStackTrace();
									}
								}
							});
						}
					} catch (SocketTimeoutException e) {
						System.out.println("no clinet connected...");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});

		mainServerThread.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	public void close() throws IOException {
		stop = true;
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have
		// finished
		@SuppressWarnings("unused")
		boolean allTasksCompleted = false;
		try {
			while ((allTasksCompleted = threadpool.awaitTermination(10, TimeUnit.SECONDS)))
				;
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("all the tasks have finished");
		try {
			mainServerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main server thread is done");

		server.close();
		System.out.println("server is safely closed");

	}
}
