
public class Quicksort 
{
	
	static void sort(int arrayToSort[][], int leftBound, int rightBound , int sortRedGreenBlue)
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
	        tmp=arrayToSort[i]; 
	        arrayToSort[i]=arrayToSort[rightBound]; 
	        arrayToSort[rightBound]=tmp;		

	        sort(arrayToSort, leftBound, i-1, sortRedGreenBlue);				//rekursiver Aufruf für linke Teilfolge
	        sort(arrayToSort, i+1, rightBound, sortRedGreenBlue);				//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	}
}
