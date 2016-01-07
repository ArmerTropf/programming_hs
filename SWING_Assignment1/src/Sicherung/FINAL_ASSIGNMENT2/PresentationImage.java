import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Diese Klasse beinhaltet Informationen über das 
 * anzuzeigende Bild.
 * 
 * @author ArmerTropf
 *
 */
public class PresentationImage extends JComponent implements   MouseListener, MouseMotionListener
{
	Color colorRect = Color.YELLOW;
	View_assignment _view; 
	
	Image imgShowThisImage;

	Dimension dimImgShowThisImage  = new Dimension();
	int [] intArrImgShowThisImage;
	Picture myPicture_caller;


	public PresentationImage(Image myImage, int intImgShowThisImageWidth, int intImgShowThisImageHeight, View_assignment view, Picture CallerImage ) 
	{
		_view = view;
		
		myPicture_caller = CallerImage;

		addMouseListener(this);
		addMouseMotionListener(this);
		
		imgShowThisImage = myImage;
		//LAden der Bilder. Dies ermöglicht danach die Abmessungen aus dem Bild zu lesen
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(imgShowThisImage,0);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {e.printStackTrace();}

		dimImgShowThisImage.setSize(intImgShowThisImageWidth, intImgShowThisImageHeight);
		intArrImgShowThisImage = new int [dimImgShowThisImage.width*dimImgShowThisImage.height]; 
		intArrImgShowThisImage = ImageActions.getPixelMap(imgShowThisImage);
		
		_view.JPanel_upper.removeAll();
		_view.JPanel_upper.add(this);
		_view.JPanel_upper.revalidate();
		
		_view.presentationLayer_ActualPresentationImage = this;

	}
	
	// Übername des aktuellen Bildes
	public void setEditedImageToCallerImage()
	{
		_view.previewImage_ActualPresentationImage.removeSelectedImageFromVector();
		
		//Aktuelles Bild verkleinern und in das aufgerufene BildObjekt schreiben 
		myPicture_caller.imgShowPrev = imgShowThisImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		//Verkleinertes Bildobjekt in das aktuelle Vorschaubild schreiben
		_view.previewImage_ActualPresentationImage.imgShowThisImage = myPicture_caller.imgShowPrev;
		
		myPicture_caller.imgShowBig = imgShowThisImage.getScaledInstance(600, 500, Image.SCALE_SMOOTH);

		//Die Pixelmap berechnen und in das aufegrufene Bildobjekt speichern
		myPicture_caller.intArrImgShowBig = ImageActions.getPixelMap(myPicture_caller.imgShowBig);
		
		//Die Pixelmap in das aktuelle Bildobjekt speichern
		intArrImgShowThisImage  = myPicture_caller.intArrImgShowBig;
		
		//Vorschaubild neu zeichenen
		_view.previewImage_ActualPresentationImage.repaint();
		_view.presentationLayer_ActualPresentationImage.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(colorRect); 

		
		g.drawImage(imgShowThisImage, 0, 0, null);
		
		
		if ( _view.selection.boolSelectionSet == true)
 			g.drawRect(_view.selection.getPointSelectionFirst().x, _view.selection.getPointSelectionFirst().y , _view.selection.getDimSelection().width,_view.selection.getDimSelection().height);
		
		if (_view.checkBoxMenuItem_MorphSelection.getText() == "Linie" || _view.checkBoxMenuItem_MorphSelection.getText() == "Kreis" || _view.checkBoxMenuItem_MorphSelection.getText() == "gefuellter Kreis")
		{
			g.drawLine(_view.selection.getPointSelectionFirst().x, _view.selection.getPointSelectionFirst().y, _view.selection.getPointSelectionSecond().x, _view.selection.getPointSelectionSecond().y);
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) 
	{
	
		if (_view.checkBoxMenuItem_MorphSelection.getText() == "Linie")
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			imgShowThisImage = ImageActions.getImageFromArray(Bresenham.bresenLine(_view.selection.getPointSelectionFirst(),_view.selection.getPointSelectionSecond(),600,intArrImgShowThisImage,0xFFFFFFFF), 600, 500);
			_view.selection.boolSelectionSet = false;
			repaint();
			
		}
		else if (_view.checkBoxMenuItem_MorphSelection.getText() == "Kreis")
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			int Radius =  _view.selection.getPointSelectionSecond().x - _view.selection.getPointSelectionFirst().x + (_view.selection.getPointSelectionSecond().y - _view.selection.getPointSelectionFirst().y); 
								
			imgShowThisImage = ImageActions.getImageFromArray(Bresenham.bresenCircle(_view.selection.getPointSelectionFirst(),Radius,600,intArrImgShowThisImage,0xFFFFFFFF), 600, 500);
			_view.selection.boolSelectionSet = false;
			repaint();
		}
		else if (_view.checkBoxMenuItem_MorphSelection.getText() == "gefuellter Kreis")
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			
			
			imgShowThisImage = ImageActions.getImageFromArray(Bresenham.bresenFilledCircle(_view.selection.getPointSelectionFirst(),(_view.selection.getPointSelectionSecond().x-_view.selection.getPointSelectionFirst().x),600,intArrImgShowThisImage,0xFFFFFFFF), 600, 500);			
			_view.selection.boolSelectionSet = false;
			repaint();
		}
		else
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			_view.selection.boolSelectionSet = true;
		}

	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		_view.selection.setPointSelectionFirst(e.getPoint());
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{

	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		requestFocus();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (_view.checkBoxMenuItem_MorphSelection.getText() != "Linie" && _view.checkBoxMenuItem_MorphSelection.getText() != "Kreis"  && _view.checkBoxMenuItem_MorphSelection.getText() != "gefuellter Kreis")
		{
			if ( _view.selection.isPointInSelection(e.getPoint()) == false )
			{
				_view.selection.setPointSelectionFirst(new Point(0,0));
				_view.selection.setPointSelectionSecond(new Point(0,0));
				_view.selection.setDimensionForSelection();
				repaint();
			}	
			//Bildposition für das Einfügen des kopierten Bereichs speichern
			_view.selection.pointPositionForPastingFirst = new Point(e.getX(),e.getY());
			_view.selection.pointPositionForPastingSecond = new Point(_view.selection.pointPositionForPastingFirst.x + _view.selection.dimSelection.width, _view.selection.pointPositionForPastingFirst.y + _view.selection.dimSelection.height);
		
			_view.selection.pointSelectionFirst = _view.selection.pointPositionForPastingFirst;
			_view.selection.pointSelectionSecond = _view.selection.pointPositionForPastingSecond;
		     
			
			repaint();
			
		}
		
		
		
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{

	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if (_view.checkBoxMenuItem_MorphSelection.getText() == "Linie" || _view.checkBoxMenuItem_MorphSelection.getText() == "Kreis" || _view.checkBoxMenuItem_MorphSelection.getText() == "gefuellter Kreis")
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			repaint();
		}
		else
		{
			_view.selection.setPointSelectionSecond(e.getPoint());
			_view.selection.setDimensionForSelection();
			repaint();
		}
	}
	
}
