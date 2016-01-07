import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;

import javax.swing.*;


public abstract class ImageActions extends JComponent
{
	
	public static Image getImageFromArray(int [] myImageArray, int width, int height )
	{
		MemoryImageSource mediaSource_ImgSrc2 = new MemoryImageSource(width,height,myImageArray,0,width);
		mediaSource_ImgSrc2.setAnimated(true);
		mediaSource_ImgSrc2.newPixels();
		
		return  Toolkit.getDefaultToolkit().createImage(mediaSource_ImgSrc2);		
	}
	
	public static int [] getPixelMap(Image grabThisImage)
	{
	

		int [] intArrNewPixelMap = new int[grabThisImage.getWidth(null) * grabThisImage.getHeight(null)];
		PixelGrabber pxGrab1  = new PixelGrabber(grabThisImage,0,0,grabThisImage.getWidth(null),grabThisImage.getHeight(null),intArrNewPixelMap,0,grabThisImage.getWidth(null));
		
		try 
		{
			pxGrab1.grabPixels();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return intArrNewPixelMap;
	}
}
