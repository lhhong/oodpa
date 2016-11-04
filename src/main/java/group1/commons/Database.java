package group1.commons;

import group1.menu.FoodItem;
import group1.menu.Menu;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by low on 4/11/16 12:34 PM.
 */
public class Database {
	public static void save() {
		URL  url = null;
		try {
			url = new URL("ftp://b8_19113227:oodpcz2002@ftp.byethost8.com/myFile.txt;type=i");
		URLConnection urlc = url.openConnection();
		OutputStream os = urlc.getOutputStream(); // To upload
		OutputStream buffer = new BufferedOutputStream(os);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(new FoodItem("name", "desc", (float) 1.48) {
			@Override
			public void getDetails() {

			}
		});
		buffer.close();
		os.close();
		output.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
