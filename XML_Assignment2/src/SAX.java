import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;



/**
 * 
 * Klasse zum einlesen von WebFeeds mittels SAX.
 * @author Schriever/Günster
 *
 */
public class SAX implements ContentHandler 
{
	/**
	 * Wird von JAXB genutzt, um den XML-Dateinamen festzulegen.
	 */
	private String strTypeofInput;
	/**
	 * Ausgelesener Wert eines Elements.
	 */
	private String currentValue;
	/**
	 * Boolsche Variable, welche anzeigt, dass Elemente die Folgen zu einem 
	 * Item gehören. 
	 */
	private boolean boolOpenItemFound = false;
	
	/**
	 * Vector, welche die NewsItems-Elemente aufnimmt.
	 */
	private Vector <NewsItems> vecNewsItems = new Vector<NewsItems>(1,1);
	
	
	private boolean boolWordHit = false;
	private String strTitle;
	private String strDescription;
	private String [] strArrSearchForWordInDescription;  
	/**
	 * Wird für das Erstellen der Strings genutzt. 
	 */
	StringBuilder builder;
	
	SAX(String strFeedURL, String strTypeofInput, String [] strArrSearchForWordInDescription ) throws IOException, SAXException, ParserConfigurationException 
	{
		this.strTypeofInput = strTypeofInput;
		this.strArrSearchForWordInDescription = strArrSearchForWordInDescription;
		
		/*a
		 * XMLReader erzeugen
		 */
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		  
		/*
		 * Pfad zur XML Datei
		 */
//		FileReader reader = new FileReader("../../../../Lerngruppe Alpha/3. Semester/Programmieren_3_Umland/Assignment2/spiegel231115.xml");
		  
		/*
		 * Für XML-Datei auf Filesystem
		 */
//		InputSource inputSource = new InputSource(reader);
		  
		  
		/*
		 * Für Online Newsfeed-XML
		 */
		InputSource inputSource = new InputSource(strFeedURL);
		
		/*
		 * DTD kann optional übergeben werden
		 */
		//inputSource.setSystemId("X:\\personen.dtd");
		
		/*
		 * ContentHandler wird übergeben
		 */
		xmlReader.setContentHandler(this);
		
		/*
		 * Parsen wird gestartet
		 */
		xmlReader.parse(inputSource); 		
	}
	  
	 
	public String getStrTypeofInput() {
		return strTypeofInput;
	}


	public Vector<NewsItems> getVecNewsItems() 
	{
		return vecNewsItems;
	}


	public void setVecNewsItems(Vector<NewsItems> vecNewsItems) 
	{
		this.vecNewsItems = vecNewsItems;
	}


	/*
	 * Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable(non-Javadoc) gespeichert.
	 * 
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length)throws SAXException 
	{  
		  /*
		   * Es kann passieren, dass in dem ausgelesenen XML-Stream Wörter vorkommen wie "&amp; &gt; &lt;"
		   * Da dieses vorkommen immer als eine neue Entität behandelt wird, ist alles vor der Entität abgeschnitten 
		   * Die lösung ist hierbei die Zeichen, die als Beginn einer Entität gelten, nach und nach an ein StringBuilder
		   * anzufügen.  
		   * 
		   */		  
			builder.append(ch,start,length);	
  	}

	  /**
	   * Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
	   */
	  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException 
	  {		  
		  builder = new StringBuilder();
		  
		  if (localName.equals("item")) 
		   {

		     
		  		/*
		  		 * Möglickeit um Attribute auszulesen.
		  		 * Nur zur Dokumentation wurde dieser Punkt im Code belassen
		  		 */
//		      	person.setId(atts.getValue("id"));   
//		      	vecPerson.lastElement().setId(atts.getValue("id"));
 
		  		boolOpenItemFound = true;
		    }
	  }
	  
	  

	  /**
	   * Methode wird aufgerufen, wenn der Parser zu einem End-Tag kommt
	   */
	  public void endElement(String uri, String localName, String qName) throws SAXException 
	  {
		  String currentValue = builder.toString();
		 
		  if (boolOpenItemFound)
		  {
			  	if (localName.equals("title"))
				{
			  		strTitle = currentValue;
				}

				if (localName.equals("description"))
				{	
					strDescription = currentValue;
					
					/*
    				 * Befindet sich in der Description das übergebene gesuchte Wort
    				 * Wenn ja, setzte boolsche Variable auf true für das schreiben in den Vector
    				 */ 
    				for (int counter = 0 ; counter < strArrSearchForWordInDescription.length ; counter++)
    				{
	    				if (strDescription.contains(strArrSearchForWordInDescription[counter]) == true )
	    					boolWordHit = true;
    				}			
				}
				if (localName.equals("item"))
				{
									
					if (boolWordHit)
		    	    {
						/*
				  		 * Neue NewsItem wird im VECTOR erzeugt		  		
				  		 */
		    	    	vecNewsItems.add(new NewsItems());
		    	    	vecNewsItems.lastElement().setTitle(strTitle);
		    	    	vecNewsItems.lastElement().setDescription(strDescription);
		    	    	boolWordHit = false;
		    	    }
					boolOpenItemFound = false;
				}
				
					
		  }

	
	  }


	  public void endDocument() throws SAXException 
	  {
		
	  }

	  
	  public void endPrefixMapping(String prefix) throws SAXException {}
	  public void ignorableWhitespace(char[] ch, int start, int length)
	      throws SAXException {}
	  public void processingInstruction(String target, String data)
	      throws SAXException {}
	  public void setDocumentLocator(Locator locator) {  }
	  public void skippedEntity(String name) throws SAXException {}
	  public void startDocument() throws SAXException {}
	  public void startPrefixMapping(String prefix, String uri)
	    throws SAXException {}
}