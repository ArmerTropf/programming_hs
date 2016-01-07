import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Vector;

import javax.swing.*;

/**
 * 
 * Überblenden von ausgewählten Bilder
 * 
 * @author Michael Günster
 *
 */
public class ImageFade extends JComponent
{
	Image m_Img;
	
	int picHeight = 500;
	int picWidth = 600;
	
	int[] m_Img1Pix = new int[picWidth*picHeight]; 
	int[] m_Img2Pix = new int[picWidth*picHeight];
	int[] m_Pix = new int[picWidth*picHeight]; 
	
	MemoryImageSource m_ImgSrc;
	
	Vector <PixelGrabber> vecPixelGrabImages = new Vector<PixelGrabber>();
	Vector <int[]> vecPixelGrabImagesIntArr = new Vector<int[]>();
	
	int [] intArrFromImage1 = new int[600*500];
	int [] intArrToImage2 = new int[600*500] ;
	
	public ImageFade(Vector<Image> myImageVector, View_assignment _view) 
	{ 
		MediaTracker mt = new MediaTracker(this);
			
		try 
		{
			for (int i = 0 ; i < myImageVector.size() ; i++)
			{
				mt.addImage(myImageVector.get(i),0);
			}
			mt.waitForAll();
			
			for (int i = 0 ; i < myImageVector.size() ; i++)
			{
				vecPixelGrabImagesIntArr.add(new int [picWidth*picHeight]);
				vecPixelGrabImages.add(new PixelGrabber(myImageVector.get(i),0,0,picWidth,picHeight,vecPixelGrabImagesIntArr.get(i),0,picWidth));
				vecPixelGrabImages.get(i).grabPixels();
			}			
					
			m_ImgSrc = new MemoryImageSource(picWidth,picHeight,m_Pix,0,picWidth);
			m_ImgSrc.setAnimated(true);
			m_Img = createImage(m_ImgSrc);
			} 
		catch (InterruptedException e) 
		{}
		
		//Fenster für die Slideshow öffnen und starten
		new FadingWindow(this);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(m_Img,0,0, getWidth(),getHeight(),this);
	}
	
	public Dimension getPreferredSize() 
	{
		return getMinimumSize();
	}
	
	public Dimension getMinimumSize() 
	{ 
		return new Dimension(picWidth,picHeight);
	}
	private int compColor(int x1,int x2,int p) 
	{ 
		return x1+(x2-x1)* p / 100; 
	}
	
	private int compPix(int pix1,int pix2,int p) 
	{
		final int RED = compColor((pix1 >> 16) & 0xff,(pix2 >> 16) & 0xff,p);
		final int GREEN = compColor((pix1 >> 8) & 0xff,(pix2 >> 8) & 0xff,p);
		final int BLUE = compColor(pix1 & 0xff,pix2 & 0xff,p);
		return 0xff000000 | (RED << 16) | (GREEN << 8) | BLUE;
	}
	
	
	
	public void shuffle(int p) 
	{
				
			for(int i = 0;i < picWidth*picHeight;++i) 
			{	
				m_Pix[i] = compPix(intArrFromImage1[i],intArrToImage2[i],p);
			}
			
			m_ImgSrc.newPixels();
			revalidate();
		
	}

	
}
