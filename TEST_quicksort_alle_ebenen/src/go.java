import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;



public class go 
{
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int SORT_BY_MAX_OCCUR = 3;
	
	
	
	
	public static void main(String [] args)
	{
		int intStartIndexToKeep = 0;
		
		int intArrAllColorsKeepRED [][];
		int intArrAllColorsKeepGREEN [][];
		int intArrAllColorsKeepBLUE [][];
		
		int intArrAllColorsToReplace[][];
		
		int intCountColorsToKeep;
		int intCountColorsToReplace;
		
//		int [][] intArrInitial = new int [][] {{8,4,2,20},{2,6,8,18},{4,1,3,13},{5,14,9,8},{5,4,3,5},{5,7,6,5},{5,7,1,4},{1,2,1,2}};
		int [][] intArrInitial = new int [][] {{8,4,2},{2,6,8},{5,14,9},{5,4,3},{5,7,6},{5,7,1},{4,1,3},{1,2,1}};

		quicksort_all(intArrInitial, 0, intArrInitial.length-1, 0);

	
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

	        quicksort_all(arrayToSort, leftBound, i-1, sortRedGreenBlue);				//rekursiver Aufruf für linke Teilfolge
	        quicksort_all(arrayToSort, i+1, rightBound, sortRedGreenBlue);				//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	  System.out.println(arrayToSort.toString());
	    System.out.println();
	}

}
