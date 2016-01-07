import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

class MyImageMorph extends JComponent 
{
//	
//	static final int MORPH_SCALE_IMAGE = 0; 
//	static final int SHOW_ORIGINAL_IMAGE = 1; 
	
	
	MyImageMorph()
	{
		
	}
	
	public int [] getShearedImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, MySelection selectedArea)
	{
		
		MyMatrix myMatrix = new MyMatrix( 
				1,newX,0,
				newY,1,0,
				0,0,1
		);	 
		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,  myMatrix, selectedArea  );

	}
	
	public int [] getScaledImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, MySelection selectedArea)
	{
		
		MyMatrix myMatrix = new MyMatrix( 
				newX,0,0,
				0,newY,0,
				0,0,1
		);	 
		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,  myMatrix, selectedArea  );

	}
	
	public int [] getMovedImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, MySelection selectedArea)
	{
		MyMatrix myMatrix = new MyMatrix( 
				1,0,-newX,
				0,1,-newY,
				0,0,1
		);	 
		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,   myMatrix , selectedArea );
		
	}
	
	public int [] getRotatedImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, MySelection selectedArea)
	{
		MyMatrix myMatrix = new MyMatrix( 
				Math.cos(newX),-Math.sin(newX),0,
				Math.sin(newX),Math.cos(newX),0,
				0,0,1
		);	 
		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,   myMatrix , selectedArea );
		
	}
	
	
	
	private int [] go (int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, MyMatrix myMatrix, MySelection selectedArea )
	{
		MyVector myVector = new MyVector();
		
		int [] intArrPixelMap_New = new int[imageWidth*imageHeight];
		
		for (int y = 0 ; y < imageHeight; y++)
		{
			for (int x = 0 ; x < imageWidth ; x++)
			{
				 myVector.setX(x);
				 myVector.setY(y);
				 myVector.setZ(1);
				
				 myVector =  myMatrix.matrix_Vector_Mult(myVector);
				
//				 selectedArea.setPointSelectionFirst(new Point(2, 2));
//				 selectedArea.setPointSelectionSecond(new Point(4, 4));
//				 System.out.println(x + " " + y);
//				 System.out.println(selectedArea.getPointSelectionFirst().x + " " + selectedArea.getPointSelectionFirst().y);
				
				 
				 //Wenn die Koordinaten des Ergebnisvectors im Originalbild sind 
				if ( myVector.getX() >= 0 && myVector.getX() < imageWidth && myVector.getY() >= 0 && myVector.getY() < imageHeight )
				{
					
					//Ist der X/Y-Wert des errechneten Vektors in der Auswahl vorhanden
					//Wenn Ja: Errechne Position im Originalbild und schreibe an die neue 
					//Bildposition den errechneten Pixel des Originalbildes
					if ( selectedArea.isPointInSelection(new Point((int)myVector.getX(),(int)myVector.getY())) == true )
					{	
						int t1_neu = y * imageWidth + x;
						int t1_alt = (int)myVector.getY() * imageWidth + (int)myVector.getX();
						
						intArrPixelMap_New[t1_neu] = intArrPixelMap_Original[t1_alt];
					}
					//Wenn der geltende x,y-Wert in der Selection liegt, dann schreibe einen weissen Bildpunkt
					else if (selectedArea.isPointInSelection(new Point(x,y)) == true )
					{
						int t1_neu = y * imageWidth + x;
						intArrPixelMap_New[t1_neu] = 0xFFFFFFFF;
					}
					//Wenn Nein:Schreibe die Position des Originalbildes an die Position
					//des neuen Bildes
					else
					{
						int t1_neu = y * imageWidth + x;
						intArrPixelMap_New[t1_neu] = intArrPixelMap_Original[t1_neu];
					}
								
				}
				//Wenn die Koordinaten des Ergebnisvectors NICHT im Originalbild sind 
				//Schreibe den Wert des OriginalPixels in den neuen Wert				
				else
					intArrPixelMap_New[y * imageWidth + x] = intArrPixelMap_Original[y * imageWidth + x];

			}
			
		}
		
		
		
		return intArrPixelMap_New;
		
	}

	
}
