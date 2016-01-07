

/**
 * Quicksort zum sortieren der RGB Arrays
 * Sortierung erfolgt nacheinander
 * 
 * @author Michael Günster
 *
 */
public class Quicksort 
{

    public static void qsort(int[][] intArrAllColorsKeep, int intColorLayer) {
        partition(intArrAllColorsKeep, 0, intArrAllColorsKeep.length - 1, intColorLayer, (intColorLayer+1)%3, (intColorLayer+2)%3);
    }

    private static void partition(int[][] intArrAllColorsKeep, int left, int right, int intColorLayerRED, int intColorLayerGREEN, int intColrLayerBLUE) {
        final int m = (left+right)/2;

        int intCurrentColorRED = intArrAllColorsKeep[m][intColorLayerRED];
        int intCurrentColorGREEN = intArrAllColorsKeep[m][intColorLayerGREEN];
        int intCurrentColorBLUE = intArrAllColorsKeep[m][intColrLayerBLUE];

        int intLeft = left;
        int intRight = right;

        while (intLeft <= intRight) {
            while (compare(intArrAllColorsKeep[intLeft][intColorLayerRED], intArrAllColorsKeep[intLeft][intColorLayerGREEN], intArrAllColorsKeep[intLeft][intColrLayerBLUE], intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE) < 0)
                ++intLeft;

            while (intRight > 0 & compare(intArrAllColorsKeep[intRight][intColorLayerRED], intArrAllColorsKeep[intRight][intColorLayerGREEN], intArrAllColorsKeep[intRight][intColrLayerBLUE], intCurrentColorRED, intCurrentColorGREEN, intCurrentColorBLUE) > 0)
                --intRight;

            if (intLeft <= intRight) {
                swap(intArrAllColorsKeep, intLeft, intRight);
                ++intLeft; --intRight;
            }
        }
        if(left < intRight)
            partition(intArrAllColorsKeep, left, intRight, intColorLayerRED, intColorLayerGREEN, intColrLayerBLUE);
        if(intLeft < right)
            partition(intArrAllColorsKeep, intLeft, right, intColorLayerRED, intColorLayerGREEN, intColrLayerBLUE);
    }

    private static void swap(int[][] intArrAllColorsKeep, int intLeftBound, int intRightBound) 
    {
        final int intTempRED = intArrAllColorsKeep[intRightBound][0];
        intArrAllColorsKeep[intRightBound][0] = intArrAllColorsKeep[intLeftBound][0];
        intArrAllColorsKeep[intLeftBound][0] = intTempRED;

        final int intTempGREEN = intArrAllColorsKeep[intRightBound][1];
        intArrAllColorsKeep[intRightBound][1] = intArrAllColorsKeep[intLeftBound][1];
        intArrAllColorsKeep[intLeftBound][1] = intTempGREEN;

        final int intTempBLUE = intArrAllColorsKeep[intRightBound][2];
        intArrAllColorsKeep[intRightBound][2] = intArrAllColorsKeep[intLeftBound][2];
        intArrAllColorsKeep[intLeftBound][2] = intTempBLUE;
    }

    private static int compare(int intColorKeepRED, int intColorKeepGREEN, int intColorKeepBLUE, int intCurrentColorRED, int intCurrentColorGREEN, int intCurrentColorBLUE) 
    {
        if(intColorKeepRED == intCurrentColorRED)
            if(intColorKeepGREEN == intCurrentColorGREEN)
                return intColorKeepBLUE - intCurrentColorBLUE;
            else
                return intColorKeepGREEN - intCurrentColorGREEN;
        else
            return intColorKeepRED - intCurrentColorRED;
    }
	
	/**
	 * Sortierung nach einer bestimmten Farbe des 
	 * übergebenen RGB - Arrays
	 */
	static void sort(int arrayToSort[][], int leftBound, int rightBound , int sortRedGreenBlue)
	{											
		/*
		 * solange mehr als 1 Folgenelement existiert
		 */
		if(rightBound > leftBound)
	    {		
			/*
			 * Variableninitialisierung mit Folgenrändern
			 */
	        int i = leftBound-1;
	        int j = rightBound;
	        int tmp [];				
	        
	        /*
	         * Endlosschleife; bricht ab, wenn i >= j
	         */
	        while(true)							
	        {									
	        	/*
	        	 * inkremt, bis größeres  Element gefunden wird
	        	 */
	            while( arrayToSort[++i][sortRedGreenBlue] < arrayToSort[rightBound][sortRedGreenBlue] )			
	            {
	            	
	            }
	            /*
	             * dekrem., bis kleineres Element gefunden wird
	             */
	            while( arrayToSort[--j][sortRedGreenBlue] > arrayToSort[rightBound][sortRedGreenBlue] && j > i)		
	            {
	            	
	            }	
	            /*
	             * brich ab, wenn sich die Folgenzeiger treffen
	             */
	            if( i >= j) 
	            	break;						
	            /*
	             * tausche kleineres mit größerem Element
	             */
	            tmp = arrayToSort[i]; 
	            arrayToSort[i] = arrayToSort[j]; 
	            arrayToSort[j] = tmp;	
	        }
	        /*
	         * tausche Trennelement
	         */
	        tmp = arrayToSort[i]; 
	        arrayToSort[i] = arrayToSort[rightBound]; 
	        arrayToSort[rightBound] = tmp;		

	        sort(arrayToSort, leftBound, i-1, sortRedGreenBlue);	//rekursiver Aufruf für linke Teilfolge
	        sort(arrayToSort, i+1, rightBound, sortRedGreenBlue);	//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	}
}




