import java.awt.*;
import java.awt.event.*;
import java.awt.image.PixelGrabber;

import javax.swing.*;


public class MyPresentationLayer extends JComponent implements MouseListener,MouseMotionListener
{
	View_assignment _view; 
	
	Image imgShowThisImage;

	Dimension dimImgShowThisImage  = new Dimension();


	int [] intArrImgShowThisImage;
	
	
	MyPicture myPicture_caller;
	
	MySelection mySelection;
	
	
	
	
	public MyPresentationLayer(Image myImage, int intImgShowThisImageWidth, int intImgShowThisImageHeight, View_assignment view, MyPicture CallerImage ) 
	{
		_view = view;
		myPicture_caller = CallerImage;
		imgShowThisImage = myImage;
		addMouseListener(this);
		
				
		//LAden der Bilder. Dies ermöglicht danach die Abmessungen aus dem Bild zu lesen
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(imgShowThisImage,0);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		dimImgShowThisImage.setSize(imgShowThisImage.getWidth(null), imgShowThisImage.getHeight(null));
		
		
		
		
		intArrImgShowThisImage = new int [dimImgShowThisImage.width*dimImgShowThisImage.height]; 
		
		intArrImgShowThisImage = getPixelMap(imgShowThisImage);
		
	
		
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

	
	public void setEditedImageToCallerImage()
	{
//		System.out.println(this.intWhichImage2Show);
//		System.out.println(myPicture_caller.intWhichImage2Show);
//		
//		myPicture_caller.imgShowThisImage = imgShowThisImage.getScaledInstance(dimImgPreview.width, dimImgPreview.height, Image.SCALE_SMOOTH);
//		myPicture_caller.imgShowPrev = imgShowThisImage.getScaledInstance(dimImgPreview.width, dimImgPreview.height, Image.SCALE_SMOOTH);
//		myPicture_caller.imgShowBig = imgShowThisImage;
//		 
//		myPicture_caller.intArrImgPrev = getPixelMap(imgShowPrev);
//		myPicture_caller.intArrImgShowBig = getPixelMap(imgShowBig);
//		myPicture_caller.intArrImgShowThisImage= getPixelMap(imgShowThisImage) ;
//		
//		
//		myPicture_caller.repaint();
	}
	
	
	public boolean isPointInSelection(Point Click)
	{
//		System.out.println(Click.getX() + " >= " + pointSelectionFirst.x + " && " +  Click.getX() + " <= " +  pointSelectionSecond.x  + " && " + Click.getY() + " >= " + pointSelectionFirst.y + " && " + Click.getY() + " <= " + pointSelectionSecond.y);
//		System.out.println(Click.getX() >= pointSelectionFirst.x && Click.getX() <= pointSelectionSecond.x  && Click.getY() >= pointSelectionFirst.y && Click.getY() <= pointSelectionSecond.y);
		if  (Click.getX() >= mySelection.getPointSelectionFirst().x && Click.getX() <= mySelection.getPointSelectionFirst().x  && Click.getY() >= mySelection.getPointSelectionFirst().y && Click.getY() <= mySelection.getPointSelectionFirst().y)
			return true;
		else 
			return false;
	}
	

	
	@Override
	public void mouseReleased(MouseEvent e) {
	
			mySelection.setPointSelectionSecond(e.getPoint());


		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
			mySelection.setPointSelectionFirst(e.getPoint());
		
		
		
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {

		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {

		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("clicked in neu");
		if ( isPointInSelection(e.getPoint()) == false )
		{

			mySelection.setPointSelectionFirst(new Point(0,0));
			mySelection.setPointSelectionSecond(new Point(0,0));
			mySelection.setDimensionForSelection();
			repaint();
		}				
	
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if ( isPointInSelection(e.getPoint()) == false )
		{

		}	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

			mySelection.setPointSelectionSecond(e.getPoint());
			mySelection.setDimensionForSelection();
			repaint();


	}
	
}
