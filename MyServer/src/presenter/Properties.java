package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	private static final long serialVersionUID = -5759519276226753497L;

	private Integer serverPort;

	private Integer numOfAllowedClients;

	private String mazeGenerator;

	private String solveAlgorithm;

	private Integer maxNumOfThreads;

	public Properties() {
		serverPort = 3333;
		mazeGenerator = "MyMaze3dGenerator";
		solveAlgorithm = "AirDistance";
		maxNumOfThreads = 5;
		numOfAllowedClients = 5;
       
	}

	public Properties(Integer serverPort, Integer numOfAllowedClients, String mazeGenerator, String solveAlgorithm,
			Integer maxNumOfThreads, Integer adminPort) {
		super();
		this.serverPort = serverPort;
		this.numOfAllowedClients = numOfAllowedClients;
		this.mazeGenerator = mazeGenerator;
		this.solveAlgorithm = solveAlgorithm;
		this.maxNumOfThreads = maxNumOfThreads;

	}

	public Integer getNumOfAllowedClients() {
		return numOfAllowedClients;
	}

	public void setNumOfAllowedClients(Integer numOfAllowedClients) {
		this.numOfAllowedClients = numOfAllowedClients;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getMazeGenerator() {
		return mazeGenerator;
	}

	public void setMazeGenerator(String mazeGenerator) {
		this.mazeGenerator = mazeGenerator;
	}

	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}

	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}

	public Integer getMaxNumOfThreads() {
		return maxNumOfThreads;
	}

	public void setMaxNumOfThreads(Integer maxNumOfThreads) {
		this.maxNumOfThreads = maxNumOfThreads;
	}

}
