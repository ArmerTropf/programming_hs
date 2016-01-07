import java.io.File;




import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import generated.*;
import generated.TrefferType.Item;


public class go 
{
	
	public static void main(String[] args) throws JAXBException
	{
		System.out.println("go");
		
		TrefferDokument test = new TrefferDokument();
		TrefferType treffer = new TrefferType();
		TrefferType.Item item = new Item();
		item.setDescription("GOGOGOO");
		test.getTreffer().add(treffer);
		treffer.getItem().add(item);

		
		
		
//		test.getTreffer().set(0, )
	   
	      
	      JAXBContext jc = JAXBContext.newInstance("generated");
	      Marshaller m = jc.createMarshaller();
	      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	      
	      m.marshal(test, new File("bums.xml"));
	}
	

}
