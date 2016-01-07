import java.awt.Image;
import java.awt.MediaTracker;
import java.util.*;

import javax.swing.*;

/**
 * Klasse ersetzt Farben nach einem gewissen Prozentsatz
 * 
 * @author ArmerTropf
 *
 */
public class ReplaceColors extends JFrame
{
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int SORT_BY_MAX_OCCUR = 3;
	
	int intImageWidth;
	int intImageHeight;

	
	Image myImage;
	
	public double intPercentToReduceColors = 1.0;
	int [] intArrOriginalPixelMap;
	
	/*
	 * Hashmap für das Datenpaar von Pixelfarbe und die Anzahl wie oft diese Pixelfarbe vorkommt
	 * Farbwert des Pixels	Anahl vorkommend
	 * Integer=-54345344 	Integer=27	
	 */
	HashMap<Integer, Integer> hashmapPixelPointColorCount = new HashMap<Integer, Integer>();

	/*
	 * HashMap mit den Kominationen, welche Farbe mit 
	 * welcher ausgetauscht werden soll.
	 */
	HashMap<Integer, Integer> hashmapColorsToReplace = new HashMap<Integer, Integer>();
	
	
	/*
	 * Array wird gefüllt mit ALLEN Farben aus der HashMap
	 */
	int [][] intArrInitial;
	
	/*
	 * Integer Arrays für die Farben, welche behalten werden
	 * RGB, weil nach RGB sortiert werden muss
	 */
	int intArrAllColorsKeepRED [][];
	int intArrAllColorsKeepGREEN [][];
	int intArrAllColorsKeepBLUE [][];
	
	
	/*
	 * Beinhaltet alle restlichen Farben, die ersetzt werden
	 */
	int intArrAllColorsToKeep[][];
	/*
	 * Beinhaltet alle restlichen Farben, die ersetzt werden
	 */
	int intArrAllColorsToReplace[][];
	
	
	/*
	 * Neues IntegerArray für das neuzuerstellende Bild anlegen
	 */
	int [] intArrNewImage; 

	
	int intStartIndexToKeep = 0;
	
	int intCountColorsToKeep;
	int intCountColorsToReplace;
	int intMinDistance;
	
	ReplaceColors(Image myPicture, int width, int height)
	{
		setSize(600, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		intImageWidth = width;
		intImageHeight = height;

		loadImage(myPicture);

	}
	
	
	public void loadImage(Image imgOriginalImage)
	{
		MediaTracker mt = new MediaTracker(this);
		
		mt.addImage(imgOriginalImage,0);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		/*
		 * PixelArray mit Arraygröße vom OriginalBild erstellen
		 */
		intArrOriginalPixelMap = new int[ImageActions.getPixelMap(imgOriginalImage).length];

		/*
		 * Array von Pixeln erzeugen
		 */
		intArrOriginalPixelMap = ImageActions.getPixelMap(imgOriginalImage);
		
		/*
		 * Einzelne Farben in eine HashMap schreiben und dabei mehrfach vorkommende
		 * zählen. Key = Farbwert des Pixels - Value = Häufigkeit 
		*/
		for (int i = 0 ; i < intArrOriginalPixelMap.length ; i++)
		{
					
			if (hashmapPixelPointColorCount.size() == 0)
			{
				hashmapPixelPointColorCount.put(intArrOriginalPixelMap[i], new Integer(0));
			}
			else
			{
				/*
				 * Wenn get() zum Pixelwert im Array passt, dann im selben Wertepaar den Value+1
				 */
				if (hashmapPixelPointColorCount.get(intArrOriginalPixelMap[i]) != null)
					hashmapPixelPointColorCount.put(intArrOriginalPixelMap[i], hashmapPixelPointColorCount.get(intArrOriginalPixelMap[i]).intValue()+1);
				/*
				 * Da der Pixelwert nicht gefunden wurde, wird ein neues Wertepaar mit 
				 * Pixelwert und initialvalue = 1 eingetragen
				 */
				else
					hashmapPixelPointColorCount.put(intArrOriginalPixelMap[i], new Integer(1));
			}
		}
		
		/*
		 * Neues BildArray mit Größe initialisieren 
		 */
		intArrNewImage = new int[intArrOriginalPixelMap.length];
		
		/*
		 * Farben aus der Hashmap in InitialArray schreiben.
		 */
		intArrInitial = fillInitialArrayFromHashMap();
		
		/*
		 * Sortieren dess Arrays nach der 4. Ebene.
		 * Die 4. Ebene Beinhaltet die Häufigkeit der 
		 * vorkommenden Farbe
		 */
		Quicksort.sort(intArrInitial, 0, intArrInitial.length-1, SORT_BY_MAX_OCCUR);

		/*
		 * Startindex der Farben , welche behalten werden sollen
		 */
		intStartIndexToKeep = getGoodAndBadColors(intArrInitial,intPercentToReduceColors);

		/*
		 * 
		 * intCountColorsToKeep: Anzahl der Farben, welche behalten werden sollen
		 * Wird genutzt zur initialisierung der einzelnen RGB-Arrays
		 * 
		 * intStartIndexToReplace: Anzahl der restlichen Farben, welche 
		 * ersetzt werden sollen
		 */
		intCountColorsToKeep 	= Math.abs(intArrInitial.length-intStartIndexToKeep);
		intCountColorsToReplace = intArrInitial.length-intCountColorsToKeep;
	
		
		/*
		 * Aufbauen der einzelnen FarbArrays mit den häufigsten Farben.
		 * Alle Arrays haben zu Beginn die selben Werte. Werden später 
		 * nach RGB sortiert ausser dem Initialarray.
		 * 
		 * Einzelne RGB-Arrays mit Farben befüllen.
		 * Es sind die Farben, welche behalten werden sollen
		 */
		intArrAllColorsToKeep		= new int [intCountColorsToKeep][];
		intArrAllColorsKeepRED 		= new int [intCountColorsToKeep][];
		intArrAllColorsKeepGREEN 	= new int [intCountColorsToKeep][];
		intArrAllColorsKeepBLUE 	= new int [intCountColorsToKeep][];
		
		int intLoopCounter = 0;
		
		for (int i = intStartIndexToKeep ; i < intArrInitial.length; i++) 
		{
			intArrAllColorsToKeep[intLoopCounter] = intArrInitial[i]; 
			intArrAllColorsKeepRED[intLoopCounter] = intArrInitial[i];
			intArrAllColorsKeepGREEN[intLoopCounter] = intArrInitial[i];
			intArrAllColorsKeepBLUE[intLoopCounter++] = intArrInitial[i];
		}
		
		/*
		 *  Aufbauen des Farbarrays.
		 *  In diesem Array sind alle Farben, welche zu ersetzten sind.
		 */
		intArrAllColorsToReplace = new int[intCountColorsToReplace][];
		
		intLoopCounter = 0;
		for (int i = 0 ; i < intStartIndexToKeep; i++) 
		{
			intArrAllColorsToReplace[intLoopCounter++] = intArrInitial[i];
		}
		
		/*
		 * Sortieren der einzlenen Farbarrays nach RGB.
		 * Der 4. Parameter in Quicksort beschreibt die Ebene,
		 * die sortiert werden soll.
		 * 
		 * Ergebnis: 
		 * ROT: 	
		 * GRÜN: 	
		 * BLAU:	
		 */
		Quicksort.qsort(intArrAllColorsKeepRED, RED);
		Quicksort.qsort(intArrAllColorsKeepGREEN, GREEN);
		Quicksort.qsort(intArrAllColorsKeepBLUE, BLUE);

		/*
		 * Alle Farben die behalten werden sollen, in die HashMap schreiben
		 * Diese Farben werden 1 zu 1 substituiert
		 */
		for (int  i = 0 ; i < intArrAllColorsToKeep.length ; i++ )
		{
			int intCurrentValue		= 0xff000000 | (intArrAllColorsToKeep[i][0] << 16) | (intArrAllColorsToKeep[i][1] << 8) | intArrAllColorsToKeep[i][2] ;
			hashmapColorsToReplace.put(intCurrentValue, intCurrentValue);
		}
				
		
		for (int  i = 0 ; i < intArrAllColorsToReplace.length ; i++ )
		{
			
			/*
			 * Farbe aufgespaltet in RGB
			 */
			int intCurrentColorRED = intArrAllColorsToReplace[i][0];
			int intCurrentColorGREEN = intArrAllColorsToReplace[i][1];
			int intCurrentColorBLUE = intArrAllColorsToReplace[i][2];
			
			
			/*
			 * Binärsuche für einen Farbwert durchführen.
			 * Ergebnis wird in jweils 3 Variablen (RGB) als
			 * Index zwischengespeichert
			 */

			int intIndexFoundRED = BinarySearch.search(intArrAllColorsKeepRED, intArrAllColorsToReplace[i], RED);
			if ( intIndexFoundRED < 1)
				intIndexFoundRED = 1;


			int intIndexFoundGREEN = BinarySearch.search(intArrAllColorsKeepGREEN, intArrAllColorsToReplace[i], GREEN);
			if ( intIndexFoundGREEN < 1)
				intIndexFoundGREEN = 1;


			int intIndexFoundBLUE = BinarySearch.search(intArrAllColorsKeepBLUE, intArrAllColorsToReplace[i], BLUE);
			if ( intIndexFoundBLUE < 1)
				intIndexFoundBLUE = 1;
			
			/*
			 * Pythagoras für die Distanz anwenden:
			 */	
			int intResultOfPythagorasRED = getDistanceFromPythagoras(intArrAllColorsKeepRED, intIndexFoundRED, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			int intResultOfPythagorasGREEN = getDistanceFromPythagoras(intArrAllColorsKeepGREEN, intIndexFoundGREEN, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			int intResultOfPythagorasBLUE = getDistanceFromPythagoras(intArrAllColorsKeepBLUE, intIndexFoundBLUE, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			
			int intResultOfPythagorasREDLeft = getDistanceFromPythagoras(intArrAllColorsKeepRED, intIndexFoundRED-1, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			int intResultOfPythagorasGREENLeft = getDistanceFromPythagoras(intArrAllColorsKeepGREEN, intIndexFoundGREEN-1, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			int intResultOfPythagorasBLUELeft = getDistanceFromPythagoras(intArrAllColorsKeepBLUE, intIndexFoundBLUE-1, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
			
			
			int[] intArrMatchedColor = new int [4];

			/*
			 * Minumum der drei erechneten Werte ermitteln
			 */
			
			int [][] intArrLower = { 
					{intResultOfPythagorasRED, intIndexFoundRED, RED},
					{intResultOfPythagorasREDLeft, intIndexFoundRED-1, RED},
					{intResultOfPythagorasGREEN, intIndexFoundGREEN, GREEN},
					{intResultOfPythagorasGREENLeft, intIndexFoundGREEN-1, GREEN},
					{intResultOfPythagorasBLUE, intIndexFoundBLUE, BLUE},
					{intResultOfPythagorasBLUELeft, intIndexFoundBLUE-1, BLUE}
			};
			
			int intMinDistance = 999999999;
			int index = 0;
			int array = 0;
			
			
			for (int j = 0; j < intArrLower.length; j++) 
			{
				if(intArrLower[j][0] < intMinDistance) 
				{
					intMinDistance = intArrLower[j][0];
					index = intArrLower[j][1];
					array = intArrLower[j][2];
				}
			}
	
			
			
			if(array == RED) {				
				intArrMatchedColor = intArrAllColorsKeepRED[index];
			} else if(array == GREEN) {				
				intArrMatchedColor = intArrAllColorsKeepGREEN[index];
			} else if(array == BLUE) {				
				intArrMatchedColor = intArrAllColorsKeepBLUE[index];
			}

		
			/*
			 * Aus den RGB Farben jeweils die kleinste Distanz
			 * subtrahieren und addieren
			 * Wird genutzt, um den Suchbereich einzuschränken
			 * 
			 */
			int intCurrentColorREDLeft = intCurrentColorRED - intMinDistance;
			int intCurrentColorREDRight = intCurrentColorRED + intMinDistance;
			
			int intCurrentColorGREENLeft = intCurrentColorGREEN - intMinDistance;
			int intCurrentColorGREENRight = intCurrentColorGREEN + intMinDistance;
			
			int intCurrentColorBLUELeft = intCurrentColorBLUE - intMinDistance;
			int intCurrentColorBLUERight = intCurrentColorBLUE + intMinDistance;
			
			/*
			 * Suche nach besseren Farben.
			 * Durchlaufen des des Arrays, welches die zu behaltenen Farben beinhaltet.
			 * 
			 * Jeweils für Rot / Grün / Blau
			 * Wenn die Farbe im KEEP-Array im Bereich der jeweiligen Farbe liegt, wird 
			 * der Pythagoras neu berechnet. Wenn die erhaltene Distanz geringer ist als die 
			 * zuvor errechnete, dann wird die Farbe als neuer Farbwert übernommen und die Distanz 
			 * neu gesetzt.
			 * 
			 */
			for (int  t = 0 ; t < intArrAllColorsToKeep.length ; t++ )
			{
				if (intArrAllColorsToKeep[t][RED] >= intCurrentColorREDLeft && intArrAllColorsToKeep[t][RED] <= intCurrentColorREDRight )
				{
					int intResultOfPythagorasREDOptimized = getDistanceFromPythagoras(intArrAllColorsToKeep, t, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
					if (intResultOfPythagorasREDOptimized < intMinDistance)
					{
						intMinDistance = intResultOfPythagorasREDOptimized;
						intArrMatchedColor = intArrAllColorsToKeep[t];	
					}
				}
				if (intArrAllColorsToKeep[t][GREEN] >= intCurrentColorGREENLeft && intArrAllColorsToKeep[t][GREEN] <= intCurrentColorGREENRight )
				{
					int intResultOfPythagorasGREENOptimized = getDistanceFromPythagoras(intArrAllColorsToKeep, t, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
					if (intResultOfPythagorasGREENOptimized < intMinDistance)
					{
						intMinDistance = intResultOfPythagorasGREENOptimized;
						intArrMatchedColor = intArrAllColorsToKeep[t];		
					}
				}
				if (intArrAllColorsToKeep[t][BLUE] >= intCurrentColorBLUELeft && intArrAllColorsToKeep[t][BLUE] <= intCurrentColorBLUERight )
				{
					int intResultOfPythagorasBLUEOptimized = getDistanceFromPythagoras(intArrAllColorsToKeep, t, intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE);
					if (intResultOfPythagorasBLUEOptimized < intMinDistance)
					{
						intMinDistance = intResultOfPythagorasBLUEOptimized;
						intArrMatchedColor = intArrAllColorsToKeep[t];	
					}
				}
				
			}
	
			int intCurrentValue		= 0xff000000 | (intCurrentColorRED << 16) | (intCurrentColorGREEN << 8) | intCurrentColorBLUE ;
			int intColorThatFits  	= 0xff000000 | (intArrMatchedColor[RED] << 16) | (intArrMatchedColor[GREEN] << 8) | intArrMatchedColor[BLUE] ;
			
			
			hashmapColorsToReplace.put(intCurrentValue, intColorThatFits);

		}
		


		/*
		 * BildPixelArray durchlaufen und Farben 
		 * mit dem in der HashMap gefundenen Treffer
		 * ersetzten
		 */
		for (int i = 0 ; i < intArrOriginalPixelMap.length ; i++)
		{
			intArrNewImage[i] = hashmapColorsToReplace.get(intArrOriginalPixelMap[i]).intValue();
		}

		
		/*
		 * Neues Bild erstellen
		 */
		myImage = ImageActions.getImageFromArray(intArrNewImage, 600, 500);

		
		
	}
	
	public static int getGoodAndBadColors(int[][] intArrAllSortedColors, double intPercentOfColorsToKeep )
	{
		double intAllColors = intArrAllSortedColors.length;
		double dblPercentToKeep = intPercentOfColorsToKeep;
		double dblCountOfColorsToKeep = intAllColors * dblPercentToKeep;
		int intIndexStartKeep = (int) (intAllColors - dblCountOfColorsToKeep);
		
		return intIndexStartKeep;
	}
	
	private int[][] fillInitialArrayFromHashMap()
	{
		intArrInitial = new int [hashmapPixelPointColorCount.size()][4];
				
		
		/*
		 * Einträge zum interieren aus der Hashmap nehmen
		 */
		Iterator it = hashmapPixelPointColorCount.entrySet().iterator();
	 	int counter = 0;
	 	
	 	/*
	 	 * Durch das nach Farben summierte Array interieren
	 	 */
	 	while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			        
	        int alpha = ((int)pair.getKey() >> 24) & 0xff;
			int red   = ((int)pair.getKey() >> 16) & 0xff;
			int green = ((int)pair.getKey() >>  8) & 0xff;
			int blue  = ((int)pair.getKey()      ) & 0xff;
			int intCount = (int)pair.getValue();

	        intArrInitial[counter][0] = red; 
	        intArrInitial[counter][1] = green;
	        intArrInitial[counter][2] = blue;
	        intArrInitial[counter][3] = intCount;
	        
	        counter++;
	        it.remove(); 
	    }
	 	return intArrInitial; 
		
	}
	
	public static int getDistanceFromPythagoras(int[][]intArrAllColorsKeep_COLOR, int intFoundIndex , int intReplaceRED, int intReplaceGREEN, int intReplaceBLUE  )
	{
		int intResultIndex;
		/*
		 * Für die Lesbarkeit:
		 * Nachfolgende Variablen beschreiben die Werte die durch den 
		 * Index bei der Binärsuche gefunden wurde. Also aus dem Array
		 * der zu behaltenen Farben
		 */
		
		int intValueOfSortedRED = intArrAllColorsKeep_COLOR[intFoundIndex][RED];
		int intValueOfSortedGREEN = intArrAllColorsKeep_COLOR[intFoundIndex][GREEN];
		int intValueOfSortedBLUE = intArrAllColorsKeep_COLOR[intFoundIndex][BLUE];
		
		/*
		 * Berechnung der Distanz über den Pythagoras
		 */
		intResultIndex 	= 		(int)Math.sqrt(
				Math.pow(intReplaceRED 		- intValueOfSortedRED, 2) + 
				Math.pow(intReplaceGREEN 	- intValueOfSortedGREEN, 2) +
				Math.pow(intReplaceBLUE 	- intValueOfSortedBLUE, 2)
				);
		return intResultIndex;
	}


	public Image getMyImage() {
		return myImage;
	}


	public int getIntCountColorsToKeep() {
		return intCountColorsToKeep;
	}
	
}
