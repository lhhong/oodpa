package group1.storage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class to store / read restaurant data on startup / shutdown into txt file by serializing Cache class
 * external ftp SERVER used to store the text file for easy code migration across different machines
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class Database {

	private static final String SERVER = "ftp://b8_19113227:oodpcz2002@ftp.byethost8.com/htdocs/";
	private static final String SUFFIX = ";type=i";
	private static final String FILE_NAME = "restaurant-data.dat";

	/**
	 * flushes the cache into a serialized file on ftp server
	 */
	public static void flush(Cache cache) {
		URL  url;
		try {
			url = new URL(SERVER + FILE_NAME + SUFFIX);
			URLConnection urlc = url.openConnection();
			OutputStream os = urlc.getOutputStream(); // To upload
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(cache);
			buffer.close();
			os.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads the serialized cache file from ftp server
	 * @return stored state of the restaurant app
	 */
	static Cache read() {
		URL url;
		Object o;
		try {
			url = new URL(SERVER + FILE_NAME + SUFFIX);
			URLConnection urlC = url.openConnection();
			InputStream is;
			try {
				is = urlC.getInputStream();
			} catch (FileNotFoundException e) {
				return null;
			}
			InputStream buffer = new BufferedInputStream(is);
			ObjectInput input = new ObjectInputStream(buffer);
			o = input.readObject();
			return (Cache) o;
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
