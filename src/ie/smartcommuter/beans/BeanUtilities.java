package ie.smartcommuter.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BeanUtilities {
	
	public static void transportXMLWriter(String file,TransportCompany company) {
		XStream xs = new XStream(new DomDriver());
		
		try  {
			ObjectOutputStream outputStream = xs.createObjectOutputStream(new FileWriter(file));
			outputStream.writeObject(company);
			outputStream.close();
		} catch (FileNotFoundException ex)  {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TransportCompany transportXMLReader(String file) {
		
		TransportCompany company = null;
		
		ObjectInputStream inputStream = null;
		XStream xs = new XStream(new DomDriver());

		try {
          	inputStream = xs.createObjectInputStream(new FileReader(file));
          	company = (TransportCompany)inputStream.readObject();

          	inputStream.close();
      	} catch (FileNotFoundException ex) {
          	ex.printStackTrace();
      	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
      	if(company==null){
      		company = new TransportCompany();
      	}
		
		return company;
	}
	
	
	public static BufferedReader getFileContents(String file) {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			reader = null;
		}

		return reader;
	}
	

	/**
	 * This method is used to format the different date
	 * types that are returned into a universal format.
	 * That being HH:mm or Due.
	 * 
	 * @param myDate
	 * @return formattedDate
	 */
	public static String formatGivenDateString(String myDate) {
		
		Date date;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		long time;
		String formattedDate = "";
		
		myDate = myDate.replace("(?i)min", "");
		myDate = myDate.replace("*", "");
		
		if(myDate.matches("(?i)arrived")||myDate.matches("(?i)due")) {
			formattedDate = "Due";
		} else if(myDate.matches("[0-9]{2}:[0-9]{2}")){
			formattedDate = myDate;
		} else {
			int x = Integer.parseInt(myDate.replaceAll("\\D",""));
			
			date = new Date(); 
			time = date.getTime();
			time += x*60*1000;

			formattedDate = (String)dateFormat.format(time);
		}
		
		return formattedDate;
	}
	
	
	/**
	 * This method is used to return the contents of url
	 * in a string.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static String readFromUrl(String url) {
		
		StringBuffer contents = new StringBuffer("");
		String str = "";
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		    while ((str = in.readLine()) != null) {
		        contents.append(str);
		    }
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return contents.toString();
	}
	
	
	
	
	/**
	 * This method is used to grab HTML elements
	 * from a webpage.
	 * 
	 * @param url
	 * @param selection
	 * @return
	 * @throws IOException
	 */
	public static Elements getHtmlElementsForUrl(String url, String selection) {
		
		Elements htmlElements = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			htmlElements = doc.select(selection);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return htmlElements;
	}
}