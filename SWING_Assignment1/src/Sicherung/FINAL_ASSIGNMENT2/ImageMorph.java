import java.awt.*;

import javax.swing.*;


/**
 * 
 * Morphing
 * 
 * @author Michael Günster
 *
 */
class ImageMorph extends JComponent 
{
	
	int intRotControlSelectionX;
	int intRotControlSelectionY;
	double intRotationX = 0.0;
	
	double intMovedX = 0.0;
	double intMovedY = 0.0;
		

	
	public int [] getShearedImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, Selection selectedArea)
	{
			
		Matrix matrix = new Matrix( 
				1,newX/imageWidth,0,
				newY/imageHeight,1,0,
				0,0,1
		);
		//- Mittelpunkt berechnen und in den Ursprung verschieben mittels Multiplikation
		//- Berechnungen für die Verzerrung durchführen
		//- Wieder zum Ausgangspunkt verschieben	
		int intCenterX = selectedArea.getPointSelectionFirst().x + ( selectedArea.getPointSelectionSecond().x-selectedArea.getPointSelectionFirst().x) / 2;
		int intCenterY  = selectedArea.getPointSelectionFirst().y + ( selectedArea.getPointSelectionSecond().y-selectedArea.getPointSelectionFirst().y) / 2;
		

		Matrix matrixTrans1 = new Matrix(
				1,0,intCenterX,
				0,1,intCenterY,
				0,0,1
		);	
		Matrix matrixTrans2 = new Matrix(		
		 
			 	1,0,-intCenterX,
				0,1,-intCenterY,
				0,0,1
		);	

		matrixTrans1 = matrixTrans1.matrix_Mult(matrix);
		matrixTrans1 = matrixTrans1.matrix_Mult(matrixTrans2);

		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,  matrixTrans1, selectedArea  );

	}
	
	public int [] getScaledImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, Selection selectedArea)
	{
		
		Matrix matrix = new Matrix( 
				1/newX,0,0,
				0,1/newY,0,
				0,0,1
		);	

		
		//- Mittelpunkt berechnen und in den Ursprung verschieben mittels Multiplikation
		//- Berechnungen für die Größe durchführen
		//- Wieder zum Ausgangspunkt verschieben
		int intCenterX = selectedArea.getPointSelectionFirst().x + ( selectedArea.getPointSelectionSecond().x-selectedArea.getPointSelectionFirst().x) / 2;
		int intCenterY  = selectedArea.getPointSelectionFirst().y + ( selectedArea.getPointSelectionSecond().y-selectedArea.getPointSelectionFirst().y) / 2;
		Matrix matrixTrans1 = new Matrix(
				1,0,intCenterX,
				0,1,intCenterY,
				0,0,1
		);	
		Matrix matrixTrans2 = new Matrix(		
		 
			 	1,0,-intCenterX,
				0,1,-intCenterY,
				0,0,1
		);	
		
		
		matrixTrans1 = matrixTrans1.matrix_Mult(matrix);
		matrixTrans1 = matrixTrans1.matrix_Mult(matrixTrans2);
		
		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,  matrixTrans1, selectedArea  );

	}
	
	public int [] getMovedImageArr(int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, Selection selectedArea)
	{
		//Verschiebeungwerte speichern, um ein mehrmaliges Verschieben nacheinander zu ermöglichen
		intMovedX += newX;
		intMovedY += newY;
			
		Matrix matrix = new Matrix( 
				1,0,-intMovedX,
				0,1,-intMovedY,
				0,0,1
		);	

		return go(intArrPixelMap_Original, imageWidth ,  imageHeight,  newX,  newY,   matrix , selectedArea );
		
		
	}
	
	public int [] getRotatedImageArr (int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, Selection selectedArea , PresentationImage presentationImage )
	{
		
		//Gradzahl zur gespeicherten Gradzahl addieren
		intRotationX += newX;
		
		//Grad in Bogenmass umrechnen
		double GO_newX = (Math.PI*2) * ( intRotationX / 360 );

		
		
		Matrix matrix = new Matrix( 
				Math.cos(-GO_newX),-Math.sin(-GO_newX),0,
				Math.sin(-GO_newX),Math.cos(-GO_newX),0,
				0,0,1
		);	
		
		//- Mittelpunkt berechnen und in den Ursprung verschieben mittels Multiplikation
		//- Berechnungen für die Rotation durchführen
		//- Wieder zum Ausgangspunkt verschieben
		int intCenterX = selectedArea.getPointSelectionFirst().x + ( selectedArea.getPointSelectionSecond().x-selectedArea.getPointSelectionFirst().x) / 2;
		int intCenterY  = selectedArea.getPointSelectionFirst().y + ( selectedArea.getPointSelectionSecond().y-selectedArea.getPointSelectionFirst().y) / 2;
		 
		Matrix matrixTrans1 = new Matrix(
				1,0,intCenterX,
				0,1,intCenterY,
				0,0,1
		);	
		 Matrix matrixTrans2 = new Matrix(		
				 
			 	1,0,-intCenterX,
				0,1,-intCenterY,
				0,0,1
		);	
			
		matrixTrans1 = matrixTrans1.matrix_Mult(matrix);
		matrixTrans1 = matrixTrans1.matrix_Mult(matrixTrans2);

		return go(intArrPixelMap_Original,  imageWidth ,  imageHeight,  newX,  newY,   matrixTrans1 , selectedArea );

	}
	
	
	
	private int [] go (int [] intArrPixelMap_Original, int imageWidth , int imageHeight, double newX, double newY, Matrix matrix, Selection selectedArea )
	{
		Vector vector = new Vector();
		
		int [] intArrPixelMap_New = new int[imageWidth*imageHeight];

		
		for (int y = 0 ; y < imageHeight; y++)
		{
			for (int x = 0 ; x < imageWidth ; x++)
			{
				/*
				 * Vectoren mit X,Y,Z Werten befüllen
				 */
				 vector.setX(x);
				 vector.setY(y);
				 vector.setZ(1);
				
				 /*
				  * Matrix auf Vector multiplizieren für jeden Punkt
				  */
				 vector =  matrix.matrix_Vector_Mult(vector);
				
				 /*
				  * Wenn die Koordinaten des Ergebnisvectors im Originalbild sind 
				  */
				if ( vector.getX() >= 0 && vector.getX() < imageWidth && vector.getY() >= 0 && vector.getY() < imageHeight )
				{
					
					/*
					 * Ist der X/Y-Wert des errechneten Vektors in der Auswahl vorhanden
					 * Wenn Ja: Errechne Position im Originalbild und schreibe an die neue
					 * Bildposition den errechneten Pixel des Originalbildes
					 */

					if ( selectedArea.isPointInSelection(new Point((int)vector.getX(),(int)vector.getY())) == true )
					{		
						int t1_neu = y * imageWidth + x;
						int t1_alt = (int)vector.getY() * imageWidth + (int)vector.getX();
						intArrPixelMap_New[t1_neu] = intArrPixelMap_Original[t1_alt];
					}
					/*
					 * Wenn der geltende x,y-Wert in der Selection liegt, dann schreibe einen weissen Bildpunkt
					 */
					else if (selectedArea.isPointInSelection(new Point(x,y)) == true )
					{
						/*
						 * Weissen einfügen, wenn er in der Auswahl liegt
						 */
						int t1_neu = y * imageWidth + x;
						intArrPixelMap_New[t1_neu] = 0xFFFFFFFF;
						
						/*
						 * Alten Bildpunkt einfügen wenn er in der Auswahl liegt
						 * intArrPixelMap_New[t1_neu] = intArrPixelMap_Original[t1_neu];
						 */
					}
					/*
					 * Wenn Nein:Schreibe die Position des Originalbildes an die Position
					 * des neuen Bildes
					 */
					else
					{
						int t1_neu = y * imageWidth + x;
						intArrPixelMap_New[t1_neu] = intArrPixelMap_Original[t1_neu];
					}
								
				}
				/*
				 * Wenn die Koordinaten des Ergebnisvectors NICHT im Originalbild sind
				 * Schreibe den Wert des OriginalPixels in den neuen Wert 
				 */
				else
					intArrPixelMap_New[y * imageWidth + x] = intArrPixelMap_Original[y * imageWidth + x];

			}
			
		}
		return intArrPixelMap_New;
		
	}

	
}
