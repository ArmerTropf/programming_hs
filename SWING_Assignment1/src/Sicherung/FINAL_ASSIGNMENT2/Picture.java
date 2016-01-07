import javax.swing.*;
import java.awt.*;


/**
 * Klasse beinhaltet alle Informationen über ein
 * jeweiliges Bild
 * 
 * @author ArmerTropf
 *
 */
public class Picture extends JComponent
{
	View_assignment _view; 
	
	String strImagePath;
	
	Image imgOrg;
	Image imgShowPrev;
	Image imgShowBig;
	
	Dimension dimImgOrg = new Dimension();
	Dimension dimImgPreview  = new Dimension();
	Dimension dimImgShowBig  = new Dimension();

	int [] intArrImgOrg;
	int [] intArrImgPrev;
	int [] intArrImgShowBig;

//	ImageActions  imageActions = new ImageActions(); 
	
	public Picture(String myImagePath, int intImgBigWidth, int intImgBigHeight, int intImgPreviewWidth, int intImgPreviewHeight, View_assignment view) 
	{
		_view = view;
		strImagePath = myImagePath; 

		imgOrg = Toolkit.getDefaultToolkit().getImage(myImagePath);
		imgShowPrev = imgOrg.getScaledInstance(intImgPreviewWidth, intImgPreviewHeight, Image.SCALE_SMOOTH);
		imgShowBig = imgOrg.getScaledInstance(intImgBigWidth, intImgBigHeight, Image.SCALE_SMOOTH);
		
		//LAden der Bilder. Dies ermöglicht danach die Abmessungen aus dem Bild zu lesen
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(imgOrg,0);
		mt.addImage(imgShowPrev,0);
		mt.addImage(imgShowBig,0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		dimImgOrg.setSize(imgOrg.getWidth(null), imgOrg.getHeight(null));
		dimImgPreview.setSize(imgShowPrev.getWidth(null), imgShowPrev.getHeight(null));
		dimImgShowBig.setSize(imgShowBig.getWidth(null), imgShowBig.getHeight(null));
		
		
		//Arrays mit ArrayGröße initialisieren
		intArrImgOrg = new int [imgOrg.getWidth(null)*imgOrg.getHeight(null) ];
		intArrImgPrev = new int [imgShowPrev.getWidth(null)*imgShowPrev.getHeight(null) ];
		intArrImgShowBig  = new int [imgShowBig.getWidth(null)*imgShowBig.getHeight(null) ];
		
		intArrImgOrg = ImageActions.getPixelMap(imgOrg);
		intArrImgPrev = ImageActions.getPixelMap(imgShowPrev);
		intArrImgShowBig = ImageActions.getPixelMap(imgShowBig);

	}


		
	public String getImagePath()
	{
		return strImagePath;
	}



	
	

}
