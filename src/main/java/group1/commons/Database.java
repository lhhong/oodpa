package group1.commons;

import group1.menu.FoodItem;
import group1.menu.Menu;
import group1.reservation.Reservation;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by low on 4/11/16 12:34 PM.
 */
public class Database {
	private static final String server = "ftp://b8_19113227:oodpcz2002@ftp.byethost8.com/htdocs/";
	private static final String suffix = ";type=i";

	public static void save(Object o) {
		URL  url = null;
		String fileName = null;
		if (o instanceof Menu) {
			fileName = "menu";
		}
		if (o instanceof ReservationList) {
			fileName = "Reservation";
		}
		try {
			url = new URL(server + fileName + suffix);
			URLConnection urlc = url.openConnection();
			OutputStream os = urlc.getOutputStream(); // To upload
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(o);
			buffer.close();
			os.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object read() {
		URL url = null;
		Object o = null;
		try {
			url = new URL(server + "myFile3.txt" + suffix);
			URLConnection urlc = url.openConnection();
			InputStream is;
			try {
				is = urlc.getInputStream();
			} catch (FileNotFoundException e) {
				return null;
			}
			InputStream buffer = new BufferedInputStream(is);
			ObjectInput input = new ObjectInputStream(buffer);
			o = input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return o;

	}
}
