import java.util.*;

import javax.swing.*;


public class Histogram extends JFrame
{

	String[][] rowData;
	
	Histogram(PresentationImage myPicture)
	{
		setSize(600, 400);

		/*
		 * Hashmap für das Datenpaar von Pixelfarbe und die Anzahl wie oft diese Pixelfarbe vorkommt
		 * Farbwert des Pixels	Anahl vorkommend
		 * Integer=-54345344 	Integer=27	
		 */
		HashMap<Integer, Integer> hashmapPixelPointColorCount = new HashMap<Integer, Integer>();
		
		int [] myArrayMAP = new int[ImageActions.getPixelMap(myPicture.imgShowThisImage).length];
		myArrayMAP = ImageActions.getPixelMap(myPicture.imgShowThisImage);

		
		for (int i = 0 ; i < myArrayMAP.length ; i++)
		{
			
			if (hashmapPixelPointColorCount.size() == 0)
			{
				hashmapPixelPointColorCount.put(myArrayMAP[i], new Integer(0));
			}
			else
			{
				if (hashmapPixelPointColorCount.get(myArrayMAP[i]) != null)
					hashmapPixelPointColorCount.put(myArrayMAP[i], hashmapPixelPointColorCount.get(myArrayMAP[i]).intValue()+1);
				else
					hashmapPixelPointColorCount.put(myArrayMAP[i], new Integer(1));
			}
		}
		rowData = new String[hashmapPixelPointColorCount.size()][2];
		int t = 0;
		int g = 0;
		
		for(HashMap.Entry<Integer, Integer> e : hashmapPixelPointColorCount.entrySet())
		{	  
			
			if (e.getValue() > 0)
			{
				int alpha = (e.getKey() >> 24) & 0xff;
				int red   = (e.getKey() >> 16) & 0xff;
				int green = (e.getKey() >>  8) & 0xff;
				int blue  = (e.getKey()      ) & 0xff;
				 
				String str_ColorNumberWithRGB = e.getKey() + "   Rot: " + red + " Grün: " + green + " Blau: " + blue; 
				
				rowData[t][g] = str_ColorNumberWithRGB;
				rowData[t][g+1] = e.getValue().toString();
				t++;
			}
		}
		
		JTable jTable = new JTable(rowData,new String[]{"Farbe / RGB","Anzahl vorkommend"});
		
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setFillsViewportHeight(true);
		jTable.setAutoCreateRowSorter(true);
		add(scrollPane);
		setVisible(true);
		
	}
	

}
