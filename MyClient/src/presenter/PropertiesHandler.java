
package presenter;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;




/**
 *
 * @author User-Pc
 */
public class PropertiesHandler {

    private static  ClientProperties properties;
    

    public static ClientProperties getInstance() throws FileNotFoundException, Exception {
        if (properties == null) {
            properties =  read("ClientProperties.xml");
            }
        
        return properties;
    }

 
    public static void write(ClientProperties p, String filename) throws Exception {
        XMLEncoder encoder
                = new XMLEncoder(
                        new BufferedOutputStream(
                                new FileOutputStream(filename)));

        encoder.writeObject(p);
        encoder.flush();
        encoder.close();
    }

    public static ClientProperties read(String filename) throws Exception {
        XMLDecoder decoder
                = new XMLDecoder(new BufferedInputStream(
                        new FileInputStream(filename)));
        ClientProperties o = (ClientProperties) decoder.readObject();
        decoder.close();
        return o;
    }

}
