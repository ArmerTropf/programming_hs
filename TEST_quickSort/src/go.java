import java.util.Vector;


public class go 
{
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int SORT_BY_MAX_OCCUR = 3;
	
	
	static BinarySearch bin = new BinarySearch();
	
	public static void main(String [] args)
	{
		int intStartIndexToKeep = 0;
		
		int intArrAllColorsKeepRED [][];
		int intArrAllColorsKeepGREEN [][];
		int intArrAllColorsKeepBLUE [][];
		
		int intArrAllColorsToReplace[][];
		
		int intCountColorsToKeep;
		int intCountColorsToReplace;
		
		int [][] intArrInitial = new int [][] {{8,4,2,20},{2,6,8,18},{4,1,3,13},{5,14,9,8},{5,4,3,5},{5,7,6,5},{5,7,1,4},{1,2,1,2}};

		/*
		 * Sortieren dess Arrays nach der 4. Ebene.
		 * Die 4. Ebene Beinhaltet die Häufigkeit der 
		 * vorkommenden Farbe
		 */
		quicksort(intArrInitial, 0, intArrInitial.length-1, SORT_BY_MAX_OCCUR);
		
		/*
		 * Startindex der Farben , welche behalten werden sollen
		 */
		intStartIndexToKeep = getGoodAndBadColors(intArrInitial,50);
		
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
		 * nach RGB sortiert
		 * 
		 * Einzelne RGB-Arrays mit Farben befüllen.
		 * Es sind die Farben, welche behalten werden sollen
		 */
		intArrAllColorsKeepRED 		= new int [intCountColorsToKeep][];
		intArrAllColorsKeepGREEN 	= new int [intCountColorsToKeep][];
		intArrAllColorsKeepBLUE 	= new int [intCountColorsToKeep][];
		
		int intLoopCounter = 0;
		for (int i = intStartIndexToKeep ; i < intArrInitial.length; i++) 
		{
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
		 * ROT: 	[[2, 6, 8, 18], [4, 1, 3, 13], [5, 14, 9, 8], [8, 4, 2, 20]]
		 * GRÜN: 	[[4, 1, 3, 13], [8, 4, 2, 20], [2, 6, 8, 18], [5, 14, 9, 8]]
		 * BLAU:	[[8, 4, 2, 20], [4, 1, 3, 13], [2, 6, 8, 18], [5, 14, 9, 8]]
		 */
		quicksort(intArrAllColorsKeepRED, 0, intArrAllColorsKeepRED.length-1, RED);
		quicksort(intArrAllColorsKeepGREEN, 0, intArrAllColorsKeepGREEN.length-1, GREEN);
		quicksort(intArrAllColorsKeepBLUE, 0, intArrAllColorsKeepBLUE.length-1, BLUE);
		

		//****************************
		//TEST eine Farbe isolieren aus den zu ersetztenden Farben
		int intReplaceRED = intArrAllColorsToReplace[1][0];		// Inhalt: 	5
		int intReplaceGREEN = intArrAllColorsToReplace[1][1];	// Inhalt:	7
		int intReplaceBLUE = intArrAllColorsToReplace[1][2];	// Inhalt:	1
		
		
		/*
		 * Binärsuche für einen Farbwert durchführen.
		 * Ergebnis wird in jweils 3 Variablen (RGB) als
		 * Index zwischengespeichert
		 */
		bin.find(intArrAllColorsKeepRED, 0, intArrAllColorsKeepRED.length-1, intReplaceRED, RED);
		int intIndexFoundRED = bin.intFoundIndex;
		bin.find(intArrAllColorsKeepGREEN, 0, intArrAllColorsKeepGREEN.length-1, intReplaceGREEN, GREEN);
		int intIndexFoundGREEN = bin.intFoundIndex;
		bin.find(intArrAllColorsKeepBLUE, 0, intArrAllColorsKeepBLUE.length-1, intReplaceBLUE, BLUE);
		int intIndexFoundBLUE = bin.intFoundIndex;
		
		/*
		 * Pythagoras für die Distanz anwenden:
		 */	
		int intResultOfPythagorasRED = getIndexFromPythagoras(intArrAllColorsKeepRED, intIndexFoundRED, intReplaceRED, intReplaceGREEN, intReplaceBLUE);
		int intResultOfPythagorasGREEN = getIndexFromPythagoras(intArrAllColorsKeepGREEN, intIndexFoundGREEN, intReplaceRED, intReplaceGREEN, intReplaceBLUE);
		int intResultOfPythagorasBLUE = getIndexFromPythagoras(intArrAllColorsKeepBLUE, intIndexFoundBLUE, intReplaceRED, intReplaceGREEN, intReplaceBLUE);
		

		/*
		 * Minumum der drei erechneten Werte ermitteln
		 */
		int intMinimumOfPythagoras = Math.min(intResultOfPythagorasBLUE, (Math.min(intResultOfPythagorasRED, intResultOfPythagorasGREEN)) );
		
		System.out.println();
	}
	
	/*
	 * Diese Funktion sucht des prozentualen Startindex.
	 * intArrAllSortedColors: 		nach Häufigkeit sortiertes Array mit den Farben
	 * intPercentOfColorsToKeep: 	Ganzahliger Prozentwert von zu behaltenen Farben
	 */
	public static int getGoodAndBadColors(int[][] intArrAllSortedColors, int intPercentOfColorsToKeep )
	{
		double intAllColors = intArrAllSortedColors.length;
		double dblPercentToKeep = intPercentOfColorsToKeep/100.0;
		int intIndexStartKeep = (int) ((intArrAllSortedColors.length) - ( intAllColors * dblPercentToKeep ));
		
		return intIndexStartKeep;
	}
	
	public static int getIndexFromPythagoras(int[][]intArrAllColorsKeep_COLOR, int intFoundIndex , int intReplaceRED, int intReplaceGREEN, int intReplaceBLUE  )
	{
		int intResultIndex;
//		/*
//		 * Für die Lesbarkeit:
//		 * Nachfolgende Variablen beschreiben die Werte die durch den 
//		 * Index bei der Binärsuche gefunden wurde. Also aus dem Array
//		 * der zu behaltenen Farben
//		 */
		int intValueOfSortedRED = intArrAllColorsKeep_COLOR[intFoundIndex][RED];
		int intValueOfSortedGREEN = intArrAllColorsKeep_COLOR[intFoundIndex][GREEN];
		int intValueOfSortedBLUE = intArrAllColorsKeep_COLOR[intFoundIndex][BLUE];
		
		/*
		 * Berechnung der Distanz über den Pythagoras
		 */
		intResultIndex 	= 		(int)Math.sqrt(
								Math.abs(Math.pow(intReplaceRED, 2)-(int)Math.pow(intValueOfSortedRED, 2)) + 
								Math.abs(Math.pow(intReplaceGREEN, 2)-(int)Math.pow(intValueOfSortedGREEN, 2)) +
								Math.abs(Math.pow(intReplaceBLUE, 2)-(int)Math.pow(intValueOfSortedBLUE, 2))
			);
		return intResultIndex;
	}
	
	static void quicksort_all(int arrayToSort[][], int leftBound, int rightBound , int sortRedGreenBlue)
	{	

		//a=Array, l=linker Rand, r=rechter Rand
		//solange mehr als 1 Folgenelement existiert
		if(rightBound > leftBound)
	    {		
			//Variableninitialisierung mit Folgenrändern
	        int i = leftBound-1;
	        int j = rightBound;
	        int tmp [];				
	        
	      //Endlosschleife; bricht ab, wenn i >= j
	        while(true)							
	        {									
	        	//inkrem., bis größeres  Element gefunden wird
	            while( arrayToSort[++i][sortRedGreenBlue] < arrayToSort[rightBound][sortRedGreenBlue] )			
	            {
	            	
	            }
	            //dekrem., bis kleineres Element gefunden wird
	            while( arrayToSort[--j][sortRedGreenBlue] > arrayToSort[rightBound][sortRedGreenBlue] && j > i)		
	            {
	            	
	            }	
	            //brich ab, wenn sich die Folgenzeiger treffen
	            if( i >= j) 
	            	break;						
	            //tausche kleineres mit größerem Element
	            tmp = arrayToSort[i]; 
	            arrayToSort[i] = arrayToSort[j]; 
	            arrayToSort[j] = tmp;	
	        }
	      //tausche Trennelement
	        tmp = arrayToSort[i]; 
	        arrayToSort[i] = arrayToSort[rightBound]; 
	        arrayToSort[rightBound] = tmp;		

	        quicksort(arrayToSort, leftBound, i-1, sortRedGreenBlue);				//rekursiver Aufruf für linke Teilfolge
	        quicksort(arrayToSort, i+1, rightBound, sortRedGreenBlue);				//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	  
	    
	}
	
	static void quicksort(int arrayToSort[][], int leftBound, int rightBound , int sortRedGreenBlue)
	{	

		//a=Array, l=linker Rand, r=rechter Rand
		//solange mehr als 1 Folgenelement existiert
		if(rightBound > leftBound)
	    {		
			//Variableninitialisierung mit Folgenrändern
	        int i = leftBound-1;
	        int j = rightBound;
	        int tmp [];				
	        
	      //Endlosschleife; bricht ab, wenn i >= j
	        while(true)							
	        {									
	        	//inkrem., bis größeres  Element gefunden wird
	            while( arrayToSort[++i][sortRedGreenBlue] < arrayToSort[rightBound][sortRedGreenBlue] )			
	            {
	            	
	            }
	            //dekrem., bis kleineres Element gefunden wird
	            while( arrayToSort[--j][sortRedGreenBlue] > arrayToSort[rightBound][sortRedGreenBlue] && j > i)		
	            {
	            	
	            }	
	            //brich ab, wenn sich die Folgenzeiger treffen
	            if( i >= j) 
	            	break;						
	            //tausche kleineres mit größerem Element
	            tmp = arrayToSort[i]; 
	            arrayToSort[i] = arrayToSort[j]; 
	            arrayToSort[j] = tmp;	
	        }
	      //tausche Trennelement
	        tmp = arrayToSort[i]; 
	        arrayToSort[i] = arrayToSort[rightBound]; 
	        arrayToSort[rightBound] = tmp;		

	        quicksort(arrayToSort, leftBound, i-1, sortRedGreenBlue);				//rekursiver Aufruf für linke Teilfolge
	        quicksort(arrayToSort, i+1, rightBound, sortRedGreenBlue);				//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	  
	    
	}
}



class BinarySearch 
{
	public int intFoundIndex;
	
	public void find(int[][] intArr, int anfang, int ende, int zahl, int sortRedGreenBlue) 
	{
			int computeLower = 0;
			int computeMiddle = 0;
			int computeHigher = 0;
		
			 
		
			//Array Teilen um die Mitte ausfindig zu machen
			int intArrayIndex = anfang + ((ende - anfang) / 2);			
			
	        if (intArr.length == 0) {
//	            System.out.println("Array leer.");
	            return;
	        }
	        else if (intArrayIndex >= intArr.length)
	        {
//	            System.out.println(zahl + " nicht im Array enthalten.");
	            return;
	        }        
	        // ZAHL GROESSER ALS WERT in ARRAY
	        else if (zahl > intArr[intArrayIndex][sortRedGreenBlue]) 
	        {
	            find(intArr, intArrayIndex + 1, ende, zahl, sortRedGreenBlue);
	        } 
	        //ZAHL KLEINER ALS WERT in ARRAY
	        else if (zahl < intArr[intArrayIndex][sortRedGreenBlue] && anfang != intArrayIndex) 
	        {
	            find(intArr, anfang, intArrayIndex - 1, zahl,sortRedGreenBlue);
	        } 
	        //ZAHL IST GLECH WERT in ARRAY
	        else if(zahl == intArr[intArrayIndex][sortRedGreenBlue]) 
	        {
	        	intFoundIndex = intArrayIndex;
	            return ;
	        }   
	        else
	        {

	     
	        	if (intArrayIndex-1 >= 0 )
	        		computeLower = Math.abs(zahl - intArr[intArrayIndex-1][sortRedGreenBlue]);
	        	else
	        		computeLower = 9999;
	        	
	        		computeMiddle = Math.abs(zahl - intArr[intArrayIndex][sortRedGreenBlue]);
	        	
	        	if (intArrayIndex+1 <= intArr.length-1 )
	        	{
	        		computeHigher = Math.abs(zahl - intArr[intArrayIndex+1][sortRedGreenBlue]);
	        	}
	        	else
	        		computeHigher = 99999;
	        			
	        	
	        	
//	        	System.out.println(zahl + " nicht GENAU im Array enthalten.");
	        	if (computeLower <= computeMiddle && computeLower <= computeHigher && intArrayIndex-1 >= 0)
	        	{
	        		intFoundIndex = intArrayIndex-1;
	        		return;
	        	}	
	        	else if (computeMiddle <= computeLower && computeMiddle <= computeHigher)
	        	{
	        		intFoundIndex = intArrayIndex;
	        		return;
	        	}
	        	else if (computeHigher <= computeLower && computeHigher <= computeMiddle && intArrayIndex+1 <= intArr.length-1 )
	        	{
	        		intFoundIndex = intArrayIndex+1;
	        		return;
	        	}
	            
	        }
	    }

}
