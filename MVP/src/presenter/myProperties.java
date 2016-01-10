package presenter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;	
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class myProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5759519276226753497L;


	
public void saveXML() throws FileNotFoundException, IOException{
	this.storeToXML(new FileOutputStream("Properties.xml"), "@Chen&Dvir@");
	
	
}



public void loadXML() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
	
    this.loadFromXML(new FileInputStream("Properties.xml"));
	
}
}


