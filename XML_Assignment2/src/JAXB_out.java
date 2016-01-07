import java.io.File;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Importieren derKlassen die durch xjc erstellt worden sind
 */
import generated.*;

/**
 * Klassen, welche mittels JAXB in eine XML-Datei schreibt.
 * Die Daten werden als Vector übergeben. In dem Vector befinden sich
 * NewsItems Objekte. Von diesen Objekten, werden nur der Titel und die Beschreibung
 * eines Feeds genutzt.
 * 
 * @author Schriever/Günster
 *
 */
public class JAXB_out 
{
	/**
	 * Statische Funktion die ein XML-Trefferdokument erzeugt	
	 * @param feedDetails Vector der NewsItems-Objekte enthält 
	 * @param strWhichInputType Zeichenkette für den XML-Dateinamen
	 * @throws JAXBException
	 */
	public static void write_XML_File(Vector<NewsItems> feedDetails, String strWhichInputType) throws JAXBException
	{
		TrefferDokument trefferDokument = new TrefferDokument();
		
		for (int i = 0 ; i < feedDetails.size() ; i++)
		{
			
	    	/*
	    	 * Hier werden die Klassen benutzt, die mittels xjc erstellt
	    	 * worden. gut nachzuvollziehen, wenn sich die assignment2.xsd 
	    	 * angeschaut wird.
	    	 */
			TrefferType type = new TrefferType();
	    	ItemType item = new ItemType();
	    	
	    	item.setTitle(feedDetails.get(i).getTitle());
	    	item.setDescription(feedDetails.get(i).getDescription());
	    	
	    	type.getItem().add(item);
	    	trefferDokument.getTreffer().add(type);
	    	  
	
			JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
		    Marshaller m = jc.createMarshaller();
		    /*
		     * Output wird für eine gewohnte Lesbarkeit formatiert
		     */
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		     
		      
		    m.marshal(trefferDokument, new File(strWhichInputType +".xml"));
		}
	}
}
