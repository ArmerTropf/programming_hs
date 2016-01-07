import java.awt.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.JFrame;


public class MyHistogram 
{
	public MyHistogram(MyPicture myPicture)
	{
		System.out.println(myPicture.intWhichImage2Show);
		System.out.println("Wie viel Pixel hat das Bild: " + myPicture.getPixelMap(myPicture.imgShowThisImage).length);
		System.out.println("Wert eines Pixels an stelle [0]: " + myPicture.getPixelMap(myPicture.imgShowThisImage)[0]);
		
		
		//Hashmap für das Datenpaar von Pixelfarbe und die Azhal wie Oft diese Pixelfarbe vorkommt	
		//Farbwert des Pixels	Anahl vorkommend
		//Integer=-54345344 	Integer=27

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int [] myArrayMAP = new int[myPicture.getPixelMap(myPicture.imgShowThisImage).length];
		myArrayMAP = myPicture.getPixelMap(myPicture.imgShowThisImage);

		
		for (int i = 0 ; i < myArrayMAP.length ; i++)
		{
			
			if (hm.size() == 0)
			{
				hm.put(myArrayMAP[i], new Integer(0));
			}
			else
			{
				if (hm.get(myArrayMAP[i]) != null)
					hm.put(myArrayMAP[i], hm.get(myArrayMAP[i]).intValue()+1);
				else
					hm.put(myArrayMAP[i], new Integer(1));
			}
		}

		for(HashMap.Entry<Integer, Integer> e : hm.entrySet())
		{	  
			
			if (e.getValue() > 1000)
			{
				int alpha = (e.getKey() >> 24) & 0xff;
				int red   = (e.getKey() >> 16) & 0xff;
				int green = (e.getKey() >>  8) & 0xff;
				int blue  = (e.getKey()      ) & 0xff;
				System.out.println(e.getKey() + "  " + e.getValue() + "   Rot: " + red + " Grün: " + green + " Blau: " + blue ); 
			}
		}
		
		
		

		
		
//		MemoryImageSource mediaSource_ImgSrc2 = new MemoryImageSource(width,height,myImageArray,0,width);
//		mediaSource_ImgSrc2.setAnimated(true);
////		
////		
//		Image Test = Toolkit.getDefaultToolkit().createImage(myPicture);
////		


		
		
	}
	

}
