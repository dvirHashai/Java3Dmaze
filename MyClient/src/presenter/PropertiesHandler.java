
package presenter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PropertiesHandler {

	/**
	 * 
	 */
	private static ClientProperties properties;

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 *             to check if we have xml file and if not, we create one
	 */
	public static ClientProperties getInstance() throws FileNotFoundException, Exception {
		if (properties == null) {
			properties = read("ClientProperties.xml");
		}

		return properties;
	}

	/**
	 * @param p
	 * @param filename
	 * @throws Exception
	 *             to write into the xml file
	 */
	public static void write(ClientProperties p, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

		encoder.writeObject(p);
		encoder.flush();
		encoder.close();
	}

	/**
	 * @param filename
	 * @return
	 * @throws Exception
	 *             to read from the xml file
	 */
	public static ClientProperties read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		ClientProperties o = (ClientProperties) decoder.readObject();
		decoder.close();
		return o;
	}

}
