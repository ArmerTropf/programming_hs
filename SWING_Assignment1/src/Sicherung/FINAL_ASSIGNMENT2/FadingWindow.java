import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JFrame in dem das Bild Überblendet wird
 * 
 * @author Michael Günster
 *
 */
public class FadingWindow extends JFrame implements Runnable
{
	Image imageToShow;
	ImageFade imageFade;
	
	boolean boolBreak = false;

	public FadingWindow(ImageFade imagesToFade) 
	{
		imageFade = imagesToFade;
		setSize(imageFade.picWidth,imageFade.picHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		Thread test2 = new Thread(this);
		test2.start();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				boolBreak = true;
			}
		});
		
		add(imagesToFade);
	}

	/*
	 * Überblendung innerhalb eines Threads 
	 */
	@Override
	public void run() 
	{
		int t = 0;
		while (boolBreak != true)
		{
				
				if ( (t+1) != imageFade.vecPixelGrabImagesIntArr.size())
				{
					imageFade.intArrFromImage1 = imageFade.vecPixelGrabImagesIntArr.get(t) ;
					imageFade.intArrToImage2 = imageFade.vecPixelGrabImagesIntArr.get(t+1);

					fade();
					t++;
				}
				else
				{
					imageFade.intArrFromImage1 = imageFade.vecPixelGrabImagesIntArr.get(imageFade.vecPixelGrabImagesIntArr.size()-1) ;
					imageFade.intArrToImage2 = imageFade.vecPixelGrabImagesIntArr.get(0);
					fade();
					t=0;
				}
				System.out.println("Im Thread ImageFade");
		}
		System.out.println("Thread ImageFade beendet");
	}
	
	private void fade()
	{
		for (int i = 0 ; i < 100 ; i+=1 )
		{
			imageFade.shuffle(i);
			try
			{
				Thread.sleep(20);
			}
			catch (Exception e)
			{}
		}
	}
	

	

}
