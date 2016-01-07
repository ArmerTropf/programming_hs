import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * Diese Klasse beinhaltet Informationen über die
 * Bilderauswahl im unteren Bereich.
 * 
 * @author ArmerTropf
 *
 */
public class PreviewImage extends JComponent implements MouseListener,KeyListener
{
	
	View_assignment _view; 
	
	Image imgShowThisImage;
	
	Dimension dimImgShowThisImage  = new Dimension();

	Picture myPicture_caller;
	boolean boolReadyForSelectImages = false;
	boolean boolImageSelected = false;
	
 
	public PreviewImage(Image myImage, View_assignment view, Picture CallerImage )
	{
		_view = view;
		
		
		myPicture_caller = CallerImage;
		imgShowThisImage = myImage;
		
		addMouseListener(this);
		addKeyListener(this);

		//Tooltip für Info 'Shift + Click
		setToolTipText("Für eine Auswahl/Abwahl zur SlideShow 'Shift + Links-Click'");
				
		//Laden der Bilder. Dies ermöglicht danach die Abmessungen aus dem Bild zu lesen
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(imgShowThisImage,0);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	
		dimImgShowThisImage.setSize(imgShowThisImage.getWidth(null), imgShowThisImage.getHeight(null)); 
		
		_view.JPanel_lower.add(this);
		
	}
	public PreviewImage( ) 
	{
		_view = null;
		myPicture_caller = null;
		imgShowThisImage = null;


	
	}
	
	public void removeSelectedImageFromVector()
	{
		_view.vecFadingImages.remove(myPicture_caller.imgShowBig);
		boolImageSelected = false;
	}
	

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(imgShowThisImage, 0, 0, null);
	
		g.setColor(Color.yellow);
		if (boolImageSelected == true)
		{
			g.fillRect(0, 0, dimImgShowThisImage.width, 15);
			g.fillRect(0, dimImgShowThisImage.height-15, dimImgShowThisImage.width, 15);

		}
	
	}
	
	
	@Override
	public Dimension getPreferredSize()
	{
		return dimImgShowThisImage;
	}
	
	@Override
	public Dimension getMinimumSize()
	{
		return dimImgShowThisImage;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		boolReadyForSelectImages = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		requestFocus();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (boolReadyForSelectImages == false)
		{
			PresentationImage presentationImage = new PresentationImage(myPicture_caller.imgShowBig, myPicture_caller.dimImgShowBig.width, myPicture_caller.dimImgShowBig.height, _view, myPicture_caller);
			_view.previewImage_ActualPresentationImage = this;
			_view.disableEnableMenu(new JMenu [] {_view.JMenu_myMenu_Edit,_view.JMenu_myMenu_DiaShow,_view.JMenu_myMenu_Morphing,_view.JMenu_myMenu_AccepttoCallerImage,_view.JMenu_myMenu_Histogram}, true);
		}
		else
		{
			if (boolImageSelected == false)
			{
				_view.vecFadingImages.add(myPicture_caller.imgShowBig);
				boolImageSelected = true;

			}
			else
			{
				_view.vecFadingImages.remove(myPicture_caller.imgShowBig);
				boolImageSelected = false;		
			}
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if ( arg0.isShiftDown())
			boolReadyForSelectImages = true;	
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if ( arg0.isShiftDown() == false)
			boolReadyForSelectImages = false;	
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	
	

}
