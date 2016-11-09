package group1.storage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class to store / read restaurant data on startup / shutdown into txt file by serializing Cache class
 * external ftp server used to store the text file for easy code migration across different machines
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class Database {

	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	private static final String server = "ftp://b8_19113227:oodpcz2002@ftp.byethost8.com/htdocs/";
	private static final String suffix = ";type=i";
	private static final String fileName = "restaurant-data.dat";

	/**
	 * flushes the cache into a serialized file on ftp server
	 */
	public static void flush() {
		URL  url = null;
		try {
			url = new URL(server + fileName + suffix);
			URLConnection urlc = url.openConnection();
			OutputStream os = urlc.getOutputStream(); // To upload
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(CacheService.getCache());
			buffer.close();
			os.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads the serialized cache from ftp server
	 * @return stored state of the restaurant app
	 */
	public static Cache read() {
		URL url = null;
		Object o = null;
		try {
			url = new URL(server + fileName + suffix);
			URLConnection urlc = url.openConnection();
			InputStream is;
			try {
				is = urlc.getInputStream();
			} catch (FileNotFoundException e) {
				logger.info("file not found in ftp");
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
