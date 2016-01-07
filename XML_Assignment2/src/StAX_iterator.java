import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

//import de.vogella.rss.model.Feed;
//import de.vogella.rss.model.FeedMessage;


public class StAX_iterator 
{
	public String strTypeofInput;
	boolean deleteFlag = false;
	String strSearchForWordInDescription; 
	
	  static final String TITLE = "title";
	  static final String DESCRIPTION = "description";
	  static final String CHANNEL = "channel";
	  static final String LANGUAGE = "language";
	  static final String COPYRIGHT = "copyright";
	  static final String LINK = "link";
	  static final String AUTHOR = "author";
	  static final String ITEM = "item";
	  static final String PUB_DATE = "pubDate";
	  static final String GUID = "guid";

	  public Vector <NewsItems> vecNewsItems = new Vector<NewsItems>(1,1);
	  
	  URL url = null; 

	  StAX_iterator(String strFeedURL, String strTypeofInput, String strSearchForWordInDescription ) 
	  {
		    this.strTypeofInput = strTypeofInput;
		    this.strSearchForWordInDescription = strSearchForWordInDescription;
		  
			try 
			{
			  this.url = new URL(strFeedURL);
			  readFeed();
			  
			} catch (MalformedURLException e) 
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

	public void readFeed() 
	  {
//	    Feed feed = null;
	    try {
	      boolean boolIsFeedHeader = true;

	      String description = "";
	      String title = "";
	      String link = "";
	      String language = "";
	      String copyright = "";
	      String author = "";
	      String pubdate = "";
	      String guid = "";

	      // First create a new XMLInputFactory
	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

	      
	      // Setup a new eventReader
	      InputStream in = read();
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      // read the XML document
	      while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();
	       
	        if (event.isStartElement()) 
	        {
	        	//Element
	        	String localPart = event.asStartElement().getName().getLocalPart();
	          
	          switch (localPart) 
	          {
	          	  case "channel":
	          		  vecNewsItems.add(new NewsItems());
		            break;
	          
		          case ITEM:
		            if (boolIsFeedHeader) 
		            {
		            	boolIsFeedHeader = false;
		              //Hier wird der Title als Daten abgelegt, bevor bei dem eigentlichen ITEM witergemacht wird
//		              feed = new Feed(title, link, description, language,copyright, pubdate);
		              vecNewsItems.lastElement().setTitle(title);
		              vecNewsItems.lastElement().setLink(link);
		              vecNewsItems.lastElement().setDescription(description);
		              vecNewsItems.lastElement().setGuid(guid);
		              
		            }
		            else
		            {
		            	vecNewsItems.add(new NewsItems());
		            }
		            event = eventReader.nextEvent();
		            break;
		          case TITLE:
		            title = getCharacterData(event, eventReader);
		            vecNewsItems.lastElement().setTitle(title);
		            break;
		          case DESCRIPTION:
		            description = getCharacterData(event, eventReader);
		            if (strSearchForWordInDescription != "")
		            {
		            	if (!description.contains(strSearchForWordInDescription))
		            		deleteFlag = true;
		            }	
		            vecNewsItems.lastElement().setDescription(description);
		            
		            break;
		          case LINK:
		            link = getCharacterData(event, eventReader);
		            vecNewsItems.lastElement().setLink(link);
		            break;
		          case GUID:
		            guid = getCharacterData(event, eventReader);
		            vecNewsItems.lastElement().setGuid(guid);
		            break;
//		          case LANGUAGE:
//		            language = getCharacterData(event, eventReader);
//		            break;
//		          case AUTHOR:
//		            author = getCharacterData(event, eventReader);
//		            break;
		          case PUB_DATE:
		            pubdate = getCharacterData(event, eventReader);
		            vecNewsItems.lastElement().setPubDate(pubdate);
		            break;
//		          case COPYRIGHT:
//		            copyright = getCharacterData(event, eventReader);
//		            break;
	          }
	        } 
	        else if (event.isEndElement()) 
	        {
	          if (event.asEndElement().getName().getLocalPart() == (ITEM)) 
	          {
	        	  if ( deleteFlag == true)
	        	  {
	        		  vecNewsItems.removeElement(vecNewsItems.lastElement());
	        		  deleteFlag = false;
	        	  }
	          }
	        }
	      }
	    } catch (XMLStreamException e) 
	    {
	      throw new RuntimeException(e);
	    }

	  }

	  private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException 
	  {
	    String result = "";
	    event = eventReader.nextEvent();
	    if (event instanceof Characters) 
	    {
	      result = event.asCharacters().getData();
	    }
	    return result;
	  }

	  private InputStream read() 
	  {
	    try 
	    {
	      return url.openStream();
	    } catch (IOException e) 
	    {
	      throw new RuntimeException(e);
	    }
	  }
	  
	  //noch nicht getestet
	  private String subSpecialCharacters(String strStream)
	  {
		  System.out.println(strStream);
		  strStream = strStream.replace(" ", "GEFUNDEN");
		  return strStream;
		  
	  }
	  
	  
} 


