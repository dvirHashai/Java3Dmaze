package presenter;

import java.io.Serializable;

/**
 * our ClientProperties class to create all the properties of our project in xml
 * and get it in every run
 *
 */
public class ClientProperties implements Serializable {

	private static final long serialVersionUID = -5759519276226753497L;

	/**
	 * port data member
	 */
	private Integer clientPort;

	/**
	 * clientIp data member
	 */
	private String clientIp;

	/**
	 * widthSize data member to initialize the width of the project window
	 */
	private Integer widthSize;

	/**
	 * heightSize data member to initialize the height of the project window
	 */
	private Integer heightSize;

	/**
	 * numOfThreads data member to run the specific number of threads
	 */
	private Integer numOfThreads;

	/**
	 * constructor to initialize our data members
	 */
	public ClientProperties() {
		clientPort = 3333;
		clientIp = "127.0.0.1";
		widthSize =675;
		heightSize = 550;
		numOfThreads = 5;

	}

	/**
	 * @param clientPort
	 * @param clientIp
	 * @param widthSize
	 * @param heightSize
	 * @param numOfThreads
	 *            constructor if we want to create new xml file and initialize
	 *            all the data members
	 */
	public ClientProperties(Integer clientPort, String clientIp, Integer widthSize, Integer heightSize,
			Integer numOfThreads) {
		super();
		this.clientPort = clientPort;
		this.clientIp = clientIp;
		this.widthSize = widthSize;
		this.heightSize = heightSize;
		this.setNumOfThreads(numOfThreads);
	}

	/**
	 * @return clientPort
	 */
	public Integer getClientPort() {
		return clientPort;
	}

	/**
	 * @param clientPort
	 */
	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}

	/**
	 * @return clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return widthSize
	 */
	public Integer getWidthSize() {
		return widthSize;
	}

	/**
	 * @param widthSize
	 */
	public void setWidthSize(Integer widthSize) {
		this.widthSize = widthSize;
	}

	/**
	 * @return heightSize
	 */
	public Integer getHeightSize() {
		return heightSize;
	}

	/**
	 * @param heightSize
	 */
	public void setHeightSize(Integer heightSize) {
		this.heightSize = heightSize;
	}

	/**
	 * @return numOfThreads
	 */
	public Integer getNumOfThreads() {
		return numOfThreads;
	}

	/**
	 * @param numOfThreads
	 */
	public void setNumOfThreads(Integer numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

}
