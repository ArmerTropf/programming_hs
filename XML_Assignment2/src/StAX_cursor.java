import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.xml.stream.*;

/**
 * Klasse für das Auslesen eines WebFeeds
 * per StAX Cursor API.
 *  
 * @author Schriever/Günster
 * fsdf fdfd {@value} dfsdfsdf
 */
public class StAX_cursor 
{
	private String strTypeofInput;
	private String [] strArrSearchForWordInDescription;
	private String strWhichElement;
	private String strTitle;
	private String strDescription; 
	private URL url = null;
	private boolean boolWordHit = false;
	
	/*
	 * Vector für NewsItems Elemente zum speichern der Treffer eines WebFeeds 
	 */
	public Vector <NewsItems> vecNewsItems = new Vector<NewsItems>(1,1);

	/***
	 * @
	 * @param strFeedURL URL zum WebFeed. Wird als Zeichenketteübergeben.
	 * @param strTypeofInput Zeichenkette wird als Name der zu erstellenden XML-Treffer-Datei verwendet
	 * @param strSearchForWordInDescription Zeichenkette wird verwendet, um im Feed unter Description
	 * nach den gefundenen MewsItems zu filtern. 
	 * @throws FileNotFoundException 
	 * 
	 * 
	 */
	StAX_cursor(String strFeedURL, String strTypeofInput, String [] strArrSearchForWordInDescription ) throws FileNotFoundException 
	{
		this.strTypeofInput = strTypeofInput;
		this.strArrSearchForWordInDescription = strArrSearchForWordInDescription;
		  
		/**
		 * Löschen der bisherigen Einträge im XML-File
		 * 
		 * Assignment2 Kriterium: "Jeder Aufruf des Filterprogramms löscht alle bisherigen Einträge im
		 * Treffer-Dokument und nimmt nur die aktuell gefundenen Treffer in das Dokument auf."
		 * 
		 * Es ist möglich, dass eine Abrage Daten in das XML-Dokument schreibt. Wenn eine nachfolgende 
		 * Abfrage liefert keine Ergebnisse liefert verändert sie somit das vorhandene XML-Dokument NICHT.
		 * Das Kriterium wird durch das Löschen des Dateiinhalts erfüllt
		 */
		PrintWriter writer = new PrintWriter(this.strTypeofInput+ ".xml");
		writer.print("");
		writer.close();
		
		try 
		{
		  this.url = new URL(strFeedURL);
		  readFeed();
		  
		  
		  
		} 
		catch (MalformedURLException e) 
		{
		  throw new RuntimeException(e);
		}
	}

	public String getStrTypeofInput() {
		return strTypeofInput;
	}

	public Vector<NewsItems> getVecNewsItems() {
		return vecNewsItems;
	}

	public void setVecNewsItems(Vector<NewsItems> vecNewsItems) {
		this.vecNewsItems = vecNewsItems;
	}

	/**
	 * Funktion zum einlesen des WebFeeds.
	 * Nach durchlauf, wird ein Vector mit den gefundenen
	 * Treffern befüllt
	 */
	public void readFeed()
	{

		try 
	    {
			/*
			 * Boolscher Ausdruck ob sich der Stream noch im FeedHeader befindet
			 */
			boolean boolIsFeedHeader = true;

			/*
			 * Neues Instanz der Factory erzeugen
			 */
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			inputFactory.setProperty(XMLInputFactory.IS_COALESCING, false);			
	      	
			/*
			 * Einlesen des Streams aus URL
			 */
			InputStream in = read();
			
			/*
			 * Reader erstellen für das Auslesen
			 */
			XMLStreamReader xmlr = inputFactory.createXMLStreamReader(in);

	      
			/*
			 * Reader durclaufen, bis keine Elemente mehr zur Verfügung stehen 
			 */
			while (xmlr.hasNext()) 
			{
		
				/*
				 * Switch-Anweisung wird genutzt, um den Typ des gefundenen Elements 
				 * zu bestimmten. Je nach Typ werden verschiedene Aktionen durchgeführt
				 */
	    	    switch (xmlr.getEventType()) 
	    	    {
	    	    
	    	    	/*
	    	    	 * StartElement
	    	    	 */
		    		case XMLStreamConstants.START_ELEMENT:
	//	    	    	System.out.print("Start Element\n");
		    			strWhichElement = xmlr.getLocalName();	
		    			
		    			/*
		    			 * Wenn das erste Item gefunden wurde, ist der FeedHeader durchlaufen
		    			 * worden und der boolsche Wert, der dies kennzeichnet wird gesetzt
		    			 */
		    			if (strWhichElement.equals("item"))
		    				boolIsFeedHeader = false;
		    			break;
	
	    			/*
	    			 * EndElement
	    			 */
	    			case XMLStreamConstants.END_ELEMENT:
	//	    	      	System.out.print("End Element\n");
	    				strWhichElement = "";
		    	    	
			    	    /*
			    	     * Objekt erst anlegen, wenn klar ist, dass das gesuchte Wort in der Description vorkommt
			    	     */
	    				if ( ( xmlr.getLocalName() == "item" && boolWordHit == true && boolIsFeedHeader == false ) )
			    	    {
	    					//Vectorelement erstellen und ein Objekt vom Typ NewsItems erzugen
			    	    	vecNewsItems.add(new NewsItems());
			    	    	vecNewsItems.lastElement().setTitle(strTitle);
			    	    	vecNewsItems.lastElement().setDescription(strDescription);
			    	    	boolWordHit = false;
			    	    }
			    	    break;
	    	    
		    	    /*
		    	     * Zeichenfolge
		    	     */
		    	    
	    			case XMLStreamConstants.CHARACTERS:
	//	    	      	System.out.print("Characters\n");
	    	      
		    	    	/*
		    	    	 * Wenn das erste  Item gedunden wurde
		    	    	 */
		    	    	if (boolIsFeedHeader == false)
	    	    		{
		    	    		switch (strWhichElement) 
		    	    		{
		    	
		    	    			case "item":
		    	    				boolWordHit = false;
		    	    				break;
		    	    			case "title":
		    	    				int start = xmlr.getTextStart();
		    	    				int len = xmlr.getTextLength();
		    	    				
		    	    				strTitle =  new String(new String(xmlr.getTextCharacters(),start,len));  
		    	    				break;
		    	    			case "description":        	  
		    	    				strDescription = new String(new String(xmlr.getTextCharacters(),xmlr.getTextStart(),xmlr.getTextLength()));
		    	    				
		    	    				/*
		    	    				 * Befindet sich in der Description das übergebene gesuchte Wort
		    	    				 * Wenn ja, setzte boolsche Variable auf true für das Schreiben in den Vector
		    	    				 */ 
		    	    				for (int counter = 0 ; counter < strArrSearchForWordInDescription.length ; counter++)
		    	    				{
			    	    				if (strDescription.contains(strArrSearchForWordInDescription[counter]) == true )
			    	    					boolWordHit = true;
		    	    				}
		    	    				break;
		    	    		}
	    	    		}
		    	    	break;
		    	      
	    			case XMLStreamConstants.SPACE:
	//	    	      	System.out.print("Space\n");
	    				break;
	
		    	    case XMLStreamConstants.PROCESSING_INSTRUCTION:
	//	    	      	System.out.print("Processing Instrcutions\n");
		    	    	break;
	
		    	    case XMLStreamConstants.CDATA:
		    	    	System.out.print("<![CDATA[");
		    	    	break;
	
		    	    case XMLStreamConstants.COMMENT:
	//	    	      	System.out.print("Comment\n");
		    	    	break;
	
		    	    case XMLStreamConstants.DTD:
	//	    	      	System.out.print("DTD\n");
		    	    	break;
	
		    	    case XMLStreamConstants.ENTITY_REFERENCE:
	//	    	      	System.out.print("Entity Reference\n");
		    	    	break;
	
		    	    case XMLStreamConstants.ENTITY_DECLARATION:
	//	    	      	System.out.print("Entity Declaration\n");
		    	    	break;
	
		    	    case XMLStreamConstants.START_DOCUMENT:
	//	    	      	System.out.print("Start Document\n");
		    	    	break;
	
		    	    case XMLStreamConstants.END_DOCUMENT:
		    	    	System.out.print("End Document\n");
		    	    	break;
	    	      
	
	    	    }
	    	    xmlr.next();
	    	  
			}
	    } 
	    catch (XMLStreamException e) 
	    {
	      throw new RuntimeException(e);
	    }

	  }


	/***
	 * Laden des InputStreams aus der übergebenen URL
	 * @return Rückgabe ist ein InputStream
	 */
	private InputStream read() 
	{
		try 
		{
			return url.openStream();
		} 	
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
  }

	  	  
} 


