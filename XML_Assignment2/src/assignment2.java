import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Veranstaltung: 	Programmieren 3 - Prof. Dr. Umland
 * Assignment:		Assignment 2
 * Zeitraum:		18.11.15. - 01.12.15
 * Bearbeiter:		Andre Schriever und Michael Günster
 * 
 * Beschreibung:
 * Mittels SAX und StAX soll ein RSS Feed ausgelesen, welches per Laufzeitparameter(String)
 * übergeben wird. Bestimmte Elemente des XML-Files, die aus dem Feed generiert werden,
 * sollen ausgelesen und nach vorgegebenen Wörtern im Element "description" gefiltert werden.
 * Die Ergebnisse beider Auslesemethoden sollen per JAXB in ein XML-Trefferdokument
 * geschrieben werden. Voraussetzung hierbei ist ein selbsterstelltes .xsf File für die
 * Generierung der Klassen durch den Kommandozeilenaufruf "xjc"
 * Erstellen der JAXB Klassen für das Schreiben in eine XML-Datei:
 * 
 * Als Parameter wird eine .xsd Schema-Datei genutzt
 * xjc assignment2.xsd
 * 
 * Kompilieren des Java-Programms
 * javac assignment2.java
 * Starten des Programms per Kommandozeile mit Parameter des Feeds
 * 
 * java assignment2 "http://www.spiegel.de/schlagzeilen/index.rss"
 * oder
 * java assignment2 "http://www.tagesschau.de/newsticker.rdf"
 * 
 * * weitere Feeds:
 * http://feeds.n24.de/n24/homepage?format=xml (Im Feed sind teilweise HTML-Anweisung in der Entität)
 * http://rss.focus.de/fol/XML/rss_folnews.xml 
 * File:///D:/spiegel231115.xml
 * 
 * http://www1.hs-bremerhaven.de/umland/testRss/test.rss
 * 
 * @author Schriever, Günster
 */
public class assignment2 
{
	/**
	 * 
	 * @param args Benötigt als Argument(args[0]) eine FeedURL
	 * @throws JAXBException
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[]args) throws JAXBException, IOException, SAXException, ParserConfigurationException
	{	
		
		String [] strArrFindWords = new String [] {"Android","Java","Welt","Mensch","Berlin"," "};

		SAX saxNewsItems = new SAX(args[0], "SAX" , strArrFindWords);
//		StAX_cursor saxNewsItems = new  StAX_cursor(args[0], "StAX" , strArrFindWords);

		
		for (int i = 0 ; i < saxNewsItems.getVecNewsItems().size() ; i++)
		{
			System.out.println("Title: "+ saxNewsItems.getVecNewsItems().get(i).getTitle());
			System.out.println("Description: " + saxNewsItems.getVecNewsItems().get(i).getDescription());
		}
		
		JAXB_out.write_XML_File(saxNewsItems.getVecNewsItems(), saxNewsItems.getStrTypeofInput());
				
	}
	

	
}







