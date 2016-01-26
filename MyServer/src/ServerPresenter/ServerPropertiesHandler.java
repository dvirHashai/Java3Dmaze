
package ServerPresenter;
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
public class ServerPropertiesHandler {

    private static  Properties properties;
    

    public static Properties getInstance() throws FileNotFoundException, Exception {
        if (properties == null) {
            properties =  read("properties.xml");
            }
        
        return properties;
    }



    public static void write(Properties p, String filename) throws Exception {
        XMLEncoder encoder
                = new XMLEncoder(
                        new BufferedOutputStream(
                                new FileOutputStream(filename)));

        encoder.writeObject(p);
        encoder.flush();
        encoder.close();
    }

    public static Properties read(String filename) throws Exception {
        XMLDecoder decoder
                = new XMLDecoder(new BufferedInputStream(
                        new FileInputStream(filename)));
        Properties o = (Properties) decoder.readObject();
        decoder.close();
        return o;
    }

}
