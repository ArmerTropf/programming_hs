import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;



public class aufg5 
{
	public static void main(String[]args)
	{
		
		try 
		{
			      // XMLReader erzeugen
			      XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			      
			      // Pfad zur XML Datei
			      FileReader reader = new FileReader("../XML_guenni/5_persons.xml");
			      InputSource inputSource = new InputSource(reader);
	
			      // DTD kann optional übergeben werden
			      // inputSource.setSystemId("X:\\personen.dtd");
	
			      // PersonenContentHandler wird übergeben
			      xmlReader.setContentHandler(new PersonenContentHandler());
	
			      // Parsen wird gestartet
			      xmlReader.parse(inputSource);
			      
    
		} 
		catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (SAXException e) {
	      e.printStackTrace();
	    }
		
		
	}
	
}

class PersonenContentHandler implements ContentHandler 
{

	  private String currentValue;
	  private Person person;
	  
	  public HashMap<Integer, Person> myChildMap = new HashMap<Integer, Person>();
	  public HashMap<Integer, Person> myPersonMap = new HashMap<Integer, Person>();
	  
	  boolean boolChild = false; 
	  int intChildCounter = 0;
	  int intPersonCounter = 0;

	 
	  // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
	  // gespeichert
	  public void characters(char[] ch, int start, int length)throws SAXException 
	  {
	    currentValue = new String(ch, start, length);
	  }

	  // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
	  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException 
	  {		  
		  	if (localName.equals("person")) 
		    {
		  		Vector <Person> vecPerson = new Vector<Person>(1,1);
		  		
		      // Neue Person erzeugen
//		      person = new Person();
		      vecPerson.addElement(new Person());
		     
		      // Attribut id wird in einen Integer umgewandelt und dann zu der
		      // jeweiligen Person gesetzt
//		      person.setId(atts.getValue("id"));   
		      vecPerson.lastElement().setId(atts.getValue("id"));
		      
		      intPersonCounter++;
		      
		      System.out.println("Person auf: " + intPersonCounter);
			  if (intPersonCounter > 1)
		      {
				  
		    	  myPersonMap.put(myPersonMap.size(), person);
		    	  
		      }
					
		      
		    }

//ZU B.)	
		  	if (localName.equals("children"))
		    {
		  		intChildCounter++;
		  		
				if (intChildCounter > 1)
					myChildMap.put(myChildMap.size(), person);
				
				boolChild = true;
		    }  
	  }
	  
	  

	  // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt
	  public void endElement(String uri, String localName, String qName) throws SAXException 
	  {
		  
		  
//ZU B.) AUSGABE DES CHILDS
		if (boolChild)
    	{
//			if (localName.equals("firstname"))
//				System.out.println("Child-firstname: " + currentValue);
//			if (localName.equals("lastname"))
//				System.out.println("Child-lastname: " + currentValue);
//			if (localName.equals("residence"))
//				System.out.println("Child-residence: " + currentValue);
			
    	}
		  
		  
	    // Vorname setzen
	    if (localName.equals("firstname")) 
	    {
	    	
//ZU A.) AUSGABE DER VORNAMEN
//	    	System.out.println(currentValue);
	    	person.setFirstname(currentValue);
	    }
	    
	    // Name setzen
	    if (localName.equals("lastname")) {
	      
	    	person.setLastname(currentValue);
	      
	    }

	    // Ort setzen
	    if (localName.equals("residence")) {
	      person.setResidence(currentValue);
	    }

	    // Person in Personenliste abspeichern falls Person End-Tag erreicht
	    // wurde.
	    if (localName.equals("person")) 
	    {
	    	
	    	System.out.println("Person zu: " + intPersonCounter);
	    	
	    	if (intPersonCounter == 1)
	    	{
	    		myPersonMap.put(myPersonMap.size(), person);
	    	}
	    	
	    	intPersonCounter--;	
	    	
	    }

//ZU B.)	    
	    if (localName.equals("children"))
	    {	    	
	    	
	    	if (intChildCounter == 1)
	    	{
	    		person.setIsChild(true);
	    		myChildMap.put(myChildMap.size(), person);
	    	}
	    	boolChild = false;	
	    	
	    	intChildCounter--;	    	
	    }
	  
	    
	  }

//ZU C.) Ausgabe der in einer HashMap gespeicherten Person Objekte
	  public void endDocument() throws SAXException 
	  {
		  for (int i = 0 ; i < myPersonMap.size(); i++)
		  {
			  System.out.println("Person: "+ myPersonMap.get(i));  
		  }
		  
		  System.out.println();
		
		  for (int i = 0 ; i < myChildMap.size(); i++)
		  {		  
			  System.out.println("Child: "+ myChildMap.get(i));  
		  }
	
		
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


//ZU C.)
class Person 
{

	  private String id;
	  private String firstname;
	  private String lastname;
	  private String residence;
	  private Boolean isChild = false;
	  
	  public Person() 
	  {

	  }

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public String getLastname() {
	    return lastname;
	  }

	  public void setLastname(String lastName) {
	    this.lastname = lastName;
	  }

	  public String getFirstname() {
	    return firstname;
	  }

	  public void setFirstname(String firstName) {
	    this.firstname = firstName;
	  }


	  public String getResidence() {
	    return residence;
	  }

	  public void setResidence(String residence) {
	    this.residence = residence;
	  }

	  @Override
	  public String toString() {
	    return "[[" + this.id + "] ["+ this.firstname + "] [" + this.lastname + "]" + " [" + this.residence + "]";
	  }

	public Boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}
}


