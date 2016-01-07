
public class go 
{
	static int f = 0;
	
	static int intpixel = 0;
	
	public static void main(String[] args)
	{
		int [] go1 = new int [] {8,4,2,9,6,3,5};
		int [][] go2 = new int [][] {{1,4,1},{3,5,5},{5,6,6},{7,9,7},{8,11,10},{11,15,13},{18,16,16},{20,18,20},{23,22,25},{24,24,30},{25,26,33},{30,30,38}};
		
		//{1,4,2},{3,4,2},{5,4,2},{7,4,2},{8,4,2},{11,4,2},{18,4,2},{20,4,2},{23,4,2},{24,4,2},{25,4,2},{30,4,2}
		//{1,4,2},{3,4,2},{5,4,2},{7,4,2},{8,4,2},{11,4,2},{18,4,2}
		//{7,4,2},{8,4,2},{11,4,2}
		//{11,4,2}
		
		for ( int h = 0 ; h <  50 ; h++)
		{
			
			searchBinary(go2, 0 , go2.length-1,h,0);
			
		}
		
		
	}

	
	
	public static void searchBinary(int[][] intArr, int anfang, int ende, int zahl, int sortRedGreenBlue) 
	{
			int computeLower = 0;
			int computeMiddle = 0;
			int computeHigher = 0;
		
			
			System.out.println(zahl);
		
			//Array Teilen um die Mitte ausfindig zu machen
			int intArrayIndex = anfang + ((ende - anfang) / 2);
	        
	        if (intArr.length == 0) {
	            System.out.println("Array leer.");
	            return;
	        }
	        
	        if (intArrayIndex >= intArr.length){
	            System.out.println(zahl + " nicht im Array enthalten.");
	            return;
	        }

	        
	        // ZAHL GROESSER ALS WERT in ARRAY
	        if (zahl > intArr[intArrayIndex][sortRedGreenBlue]) 
	        {
	            searchBinary(intArr, intArrayIndex + 1, ende, zahl, sortRedGreenBlue);
	        } 
	        //ZAHL KLEINER ALS WERT in ARRAY
	        else if (zahl < intArr[intArrayIndex][sortRedGreenBlue] && anfang != intArrayIndex) 
	        {
	            searchBinary(intArr, anfang, intArrayIndex - 1, zahl,sortRedGreenBlue);
	        } 
	        //ZAHL IST GLECIH WERT in ARRAY
	        else if(zahl == intArr[intArrayIndex][sortRedGreenBlue]) 
	        {
	            System.out.println(zahl + " an Position " + intArrayIndex + " enthalten.");
	            intpixel = zahl;
	        } 
	        //ZAHL NICHT ALS WERT in ARRAY - AUSGABE von GRENZE BZW. Geteilter Wert
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
//	        	if (computeLower <= computeMiddle && computeLower <= computeHigher && intArrayIndex-1 >= 0)
//	        	{
//	        		System.out.println("Sollte aber sein bei Position " + (intArrayIndex-1));
//	        		System.out.println("Nutze " + intArr[intArrayIndex-1][sortRedGreenBlue]);
//	        		intpixel = intArr[intArrayIndex-1][sortRedGreenBlue];
//	        	}	
//	        	else if (computeMiddle <= computeLower && computeMiddle <= computeHigher)
//	        	{
//	        		System.out.println("Sollte aber sein bei Position " + (intArrayIndex));
//	        		System.out.println("Nutze " + intArr[intArrayIndex][sortRedGreenBlue]);
	        		intpixel = intArr[intArrayIndex][sortRedGreenBlue];
//	        	}
//	        	else if (computeHigher <= computeLower && computeHigher <= computeMiddle && intArrayIndex+1 <= intArr.length-1 )
//	        	{
//	        		System.out.println("Sollte aber sein bei Position " + (intArrayIndex+1));
//	        		System.out.println("Nutze " + intArr[intArrayIndex+1][sortRedGreenBlue]);
//	        		intpixel = intArr[intArrayIndex+1][sortRedGreenBlue];
//	        	}

	        	
	        	System.out.println("Gefundener Index: " + intArrayIndex + "|  " + computeLower + " " + computeMiddle + " " + computeHigher );
	        	
	        	
	        	
	        	
	            
	        }
	    }
	
}
