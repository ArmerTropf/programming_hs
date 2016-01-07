
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;


public class go 
{
	public static void main(String[]args)
	{
		
		
		myView go = new myView();
	
	
		
	}
}

class myView extends JFrame
{
	
	myView()
	{	
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		myBlend myBlend_GO = new myBlend();
		
		
		setLayout(new FlowLayout());
		
		add(myBlend_GO);
		
		
		for (int i = 0 ; i < 100 ; i+=1 )
		{
			myBlend_GO.shuffle(i);
			try
			{
				Thread.sleep(20);
			}
			catch (Exception e)
			{}
		}
		
		
		
	}
		
}

class myBlend extends JComponent
{
	Image myImage1;
	Image myImage2;
	
	Image myImage1_scaled;
	Image myImage2_scaled;
	
	Image m_Img;
	
	int picHeight = 500;
	int picWidth = 500;
	
	int[] m_Img1Pix = new int[picWidth*picHeight]; 
	int[] m_Img2Pix = new int[picWidth*picHeight];
	int[] m_Pix = new int[picWidth*picHeight]; 
	
	MemoryImageSource m_ImgSrc;
	
	
	
	public myBlend() 
	{
		myImage1 = Toolkit.getDefaultToolkit().getImage("c:/1.jpg");
		myImage2 = Toolkit.getDefaultToolkit().getImage("c:/2.gif");
		
		myImage1_scaled = myImage1.getScaledInstance(500, 500, Image.SCALE_FAST);
		myImage2_scaled = myImage2.getScaledInstance(500, 500, Image.SCALE_FAST);
		repaint();
		
		try 
		{
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(myImage1,0);
			mt.addImage(myImage2,0);
			mt.waitForAll();
			
			PixelGrabber grab1 = new PixelGrabber(myImage1,0,0,picWidth,picHeight,m_Img1Pix,0,picWidth);
			PixelGrabber grab2 = new PixelGrabber(myImage2,0,0,picWidth,picHeight,m_Img2Pix,0,picWidth);
			
			grab1.grabPixels();
			grab2.grabPixels();
			
			System.out.println(m_Img1Pix.length);
			
			System.out.println(m_Img1Pix[18500]);
			
			
			m_ImgSrc = new MemoryImageSource(picWidth,picHeight,m_Pix,0,picWidth);
			m_ImgSrc.setAnimated(true);
			m_Img = createImage(m_ImgSrc);
			} 
		catch (InterruptedException e) 
		{}
		
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
		return x1+(x2-x1)*p/100; 
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
			m_Pix[i] = compPix(m_Img1Pix[i],m_Img2Pix[i],p);
			m_Img1Pix[200] = 0xff345678;
			m_Img1Pix[202] = 0xff345678;
			m_Img1Pix[203] = 0xff345678;
			m_Img1Pix[204] = 0xff345678;
		}
		
		m_ImgSrc.newPixels();
		revalidate();
	}
	
}
