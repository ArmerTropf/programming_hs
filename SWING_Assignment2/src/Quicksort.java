
public class Quicksort 
{
	// quicksort implementation
    public static void qsort(int[][] arr, int col) {
        partition(arr, 0, arr.length - 1, col, (col+1)%3, (col+2)%3);
    }

    private static void partition(int[][] arr, int left, int right, int col1, int col2, int col3) {
        final int m = (left+right)/2;

        int p1 = arr[m][col1];
        int p2 = arr[m][col2];
        int p3 = arr[m][col3];

        int l = left;
        int r = right;

        while (l <= r) {
            while (compare(arr[l][col1], arr[l][col2], arr[l][col3], p1, p2, p3) < 0)
                ++l;

            while (r > 0 & compare(arr[r][col1], arr[r][col2], arr[r][col3], p1, p2, p3) > 0)
                --r;

            if (l <= r) {
                swap(arr, l, r);
                ++l; --r;
            }
        }
        if(left < r)
            partition(arr, left, r, col1, col2, col3);
        if(l < right)
            partition(arr, l, right, col1, col2, col3);
    }

    private static void swap(int[][] arr, int i, int j) {
        final int r = arr[j][0];
        arr[j][0] = arr[i][0];
        arr[i][0] = r;

        final int g = arr[j][1];
        arr[j][1] = arr[i][1];
        arr[i][1] = g;

        final int b = arr[j][2];
        arr[j][2] = arr[i][2];
        arr[i][2] = b;
    }

    private static int compare(int col1, int col2, int col3, int p1, int p2, int p3) {
        if(col1 == p1)
            if(col2 == p2)
                return col3 - p3;
            else
                return col2 - p2;
        else
            return col1 - p1;
    }
	
	
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
	        tmp = arrayToSort[i]; 
	        arrayToSort[i] = arrayToSort[rightBound]; 
	        arrayToSort[rightBound] = tmp;		

	        sort(arrayToSort, leftBound, i-1, sortRedGreenBlue);				//rekursiver Aufruf für linke Teilfolge
	        sort(arrayToSort, i+1, rightBound, sortRedGreenBlue);				//rekursiver Aufruf für rechte Teilfolge
	        
	    }
	}
}




