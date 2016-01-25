package presenter;

import java.io.Serializable;

public class ClientProperties implements Serializable {

	private static final long serialVersionUID = -5759519276226753497L;

	private Integer clientPort;

	private String clientIp;
    
	private Integer widthSize;
	
	private Integer heightSize ;
	
	public ClientProperties() {
		clientPort = 3333;
		clientIp = "127.0.0.1";
		widthSize = 1300;
		heightSize = 800;
		
       
	}

	public ClientProperties(Integer clientPort, String clientIp, Integer widthSize, Integer heightSize) {
		super();
		this.clientPort = clientPort;
		this.clientIp = clientIp;
		this.widthSize = widthSize;
		this.heightSize = heightSize;

	}

	public Integer getClientPort() {
		return clientPort;
	}

	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Integer getWidthSize() {
		return widthSize;
	}

	public void setWidthSize(Integer widthSize) {
		this.widthSize = widthSize;
	}

	public Integer getHeightSize() {
		return heightSize;
	}

	public void setHeightSize(Integer heightSize) {
		this.heightSize = heightSize;
	}



}
