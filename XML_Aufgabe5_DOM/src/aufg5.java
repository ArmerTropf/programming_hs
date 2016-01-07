import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
	
public class aufg5 
{
	 public static void main(String argv[]) {

	    try 
	    {

			File fXmlFile = new File("../XML_guenni/5_persons.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
	
			System.out.println("Start: " + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("children");
	
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
	
				Node nNode = nList.item(temp);
					
				
//				System.out.println("\nCurrent Element :" + nNode.getNodeType());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
	
					Element eElement = (Element) nNode;

					System.out.println("id : " + eElement.getAttribute("id"));
					
					if (eElement.getElementsByTagName("firstname").getLength() != 0)
					System.out.println("firstname : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					
					if (eElement.getElementsByTagName("lastname").getLength() != 0)
					System.out.println("lastname : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					
					if (eElement.getElementsByTagName("residence").getLength() != 0)
						System.out.println("residence : " + eElement.getElementsByTagName("residence").item(0).getTextContent());
					if (eElement.getElementsByTagName("children").getLength() != 0)
						System.out.println("Children : " + eElement.getElementsByTagName("children").item(0).getTextContent());
	
				}
				
				
			}
	    } 
	    catch (Exception e) {
		e.printStackTrace();
	    }
	  }


	
	
	
	

}
