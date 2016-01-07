import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class MyPicture extends JComponent implements MouseListener
{
	View_assignment _view; 
	
	String strImagePath;
	
	Image imgOrg;
	Image imgShowPrev;
	Image imgShowBig;
	Image imgShowThisImage;
	
	Dimension dimImgOrg = new Dimension();
	Dimension dimImgPreview  = new Dimension();
	Dimension dimImgShowBig  = new Dimension();

	int [] intArrImgOrg;
	int [] intArrImgPrev;
	int [] intArrImgShowBig;
	int [] intArrImgShowThisImage;
	

	static final int SHOW_ORIGINAL_IMAGE = 0; 
	static final int SHOW_PREVIEW_IMAGE = 1;
	static final int SHOW_BIG_IMAGE = 2;
	
	public boolean boolMouseListenerClicktoSetBigImage = true;
	 
	//Welche Bild gezeichnet werden soll
	int intWhichImage2Show;
	//Image welches in der DrawMethode verwendet wird
	MyPicture myPicture_caller;
	
	public MyPicture(String myImagePath, int intImgBigWidth, int intImgBigHeight, int intImgPreviewWidth, int intImgPreviewHeight, int WhichImage, View_assignment view, MyPicture CallerImage ) 
	{
		_view = view;
		myPicture_caller = CallerImage;
		addMouseListener(this);
		
		strImagePath = myImagePath; 
		intWhichImage2Show = WhichImage;
		
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dimImgOrg.setSize(imgOrg.getWidth(null), imgOrg.getHeight(null));
		dimImgPreview.setSize(imgShowPrev.getWidth(null), imgShowPrev.getHeight(null));
		dimImgShowBig.setSize(imgShowBig.getWidth(null), imgShowBig.getHeight(null));
		
		
		//Arrays mit ArrayGröße initialisieren
		intArrImgOrg = new int [imgOrg.getWidth(null)*imgOrg.getHeight(null) ];
		intArrImgPrev = new int [imgShowPrev.getWidth(null)*imgShowPrev.getHeight(null) ];
		intArrImgShowBig  = new int [imgShowBig.getWidth(null)*imgShowBig.getHeight(null) ];
		intArrImgShowThisImage = new int [intArrImgShowBig.length]; 
		
		intArrImgOrg = getPixelMap(imgOrg);
		intArrImgPrev = getPixelMap(imgShowPrev);
		intArrImgShowBig = getPixelMap(imgShowBig);
		intArrImgShowThisImage = intArrImgShowBig.clone();
		
		setImage2Show(intWhichImage2Show);
		
	}
	
	private void setPictureFromFullPath()
	{
		
	}
	
	private void setPictureFromI()
	{
		
	}
	
	
	public void setImage2Show(int WhichImageToShow)
	{
		switch (WhichImageToShow) {
		case 0:
			setSize(dimImgOrg);
			intWhichImage2Show= WhichImageToShow;
			imgShowThisImage = imgOrg;
			break;
		case 1:
			setSize(dimImgPreview);
			intWhichImage2Show= WhichImageToShow;
			imgShowThisImage = imgShowPrev;
			
			break;
		case 2:
			setSize(dimImgShowBig);
			intWhichImage2Show= WhichImageToShow;
			imgShowThisImage = imgShowBig;
			break;
		default:
			break;
		}
	}
	
	
	
	public int []  getPixelMap(Image grabThisImage)
	{
		int [] intArrNewPixelMap = new int[grabThisImage.getWidth(this) * grabThisImage.getHeight(this)];
				
		PixelGrabber pxGrab1  = new PixelGrabber(grabThisImage,0,0,grabThisImage.getWidth(this),grabThisImage.getHeight(this),intArrNewPixelMap,0,grabThisImage.getWidth(this));
		try 
		{
			pxGrab1.grabPixels();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return intArrNewPixelMap;
	}
	
	public Image getImageFromArray(int [] myImageArray, int width, int height )
	{
		MemoryImageSource mediaSource_ImgSrc2 = new MemoryImageSource(width,height,myImageArray,0,width);
		mediaSource_ImgSrc2.setAnimated(true);
		mediaSource_ImgSrc2.newPixels();
		
		return createImage(mediaSource_ImgSrc2);		
	}
	
	public void setEditedImageToCallerImage()
	{
		System.out.println(this.intWhichImage2Show);
		System.out.println(myPicture_caller.intWhichImage2Show);
		
		myPicture_caller.imgShowThisImage = imgShowThisImage.getScaledInstance(dimImgPreview.width, dimImgPreview.height, Image.SCALE_SMOOTH);
		myPicture_caller.imgShowPrev = imgShowThisImage.getScaledInstance(dimImgPreview.width, dimImgPreview.height, Image.SCALE_SMOOTH);
		myPicture_caller.imgShowBig = imgShowThisImage;
		 
		myPicture_caller.intArrImgPrev = getPixelMap(imgShowPrev);
		myPicture_caller.intArrImgShowBig = getPixelMap(imgShowBig);
		myPicture_caller.intArrImgShowThisImage= getPixelMap(imgShowThisImage) ;
		
		
		myPicture_caller.repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getImagePath()
	{
		return strImagePath;
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return this.getSize();
	}
	
	@Override
	public Dimension getMinimumSize()
	{
		return this.getSize();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(imgShowThisImage, 0, 0, null);

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {

		if (boolMouseListenerClicktoSetBigImage == true)
		{
			_view.setBigImage(this);

		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
