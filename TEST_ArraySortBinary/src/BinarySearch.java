
public class BinarySearch 
{
	int myReturnValue = 0;
	
	public void find(int[][] intArr, int anfang, int ende, int zahl, int sortRedGreenBlue)  
	{
//			int computeLower = 0;
//			int computeMiddle = 0;
//			int computeHigher = 0;
		 
			
					
			//Array Teilen um die Mitte ausfindig zu machen
			int intArrayIndex = anfang + ((ende - anfang) / 2);
	        
			
//			System.out.println(intArr[intArrayIndex][sortRedGreenBlue] + " " + zahl);
			
			
	        if (intArr.length == 0) {
	            System.out.println("Array leer.");
	            myReturnValue = -5;
	           
	        }
	        
	        if (intArrayIndex >= intArr.length)
	        {
	            System.out.println(zahl + " nicht im Array enthalten.");
	            myReturnValue = -1;
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
	        //ZAHL IST GLEICH WERT in ARRAY
	        else if(zahl == intArr[intArrayIndex][sortRedGreenBlue]) 
	        {
//	            _histo.intpixel = (zahl);
	        	System.out.println("Hit");
	        	myReturnValue = intArrayIndex;
	        }   
	        else
	        {
	        	System.out.println("nix");
	        	myReturnValue = -2;
//	        	 _histo.intpixel = (zahl);
	     
//	        	if (intArrayIndex-1 >= 0 )
//	        		computeLower = Math.abs(zahl - intArr[intArrayIndex-1][sortRedGreenBlue]);
//	        	else
//	        		computeLower = 9999;
//	        	
//	        		computeMiddle = Math.abs(zahl - intArr[intArrayIndex][sortRedGreenBlue]);
//	        	
//	        	if (intArrayIndex+1 <= intArr.length-1 )
//	        	{
//	        		computeHigher = Math.abs(zahl - intArr[intArrayIndex+1][sortRedGreenBlue]);
//	        	}
//	        	else
//	        		computeHigher = 99999;
//	        			
//	        	
//	        	
////	        	System.out.println(zahl + " nicht GENAU im Array enthalten.");
//	        	if (computeLower <= computeMiddle && computeLower <= computeHigher && intArrayIndex-1 >= 0)
//	        	{
////	        		_histo.intpixel = intArr[intArrayIndex-1][sortRedGreenBlue];
////	        		return;
//	        	}	
//	        	else if (computeMiddle <= computeLower && computeMiddle <= computeHigher)
//	        	{
////	        		_histo.intpixel = intArr[intArrayIndex][sortRedGreenBlue];
////	        		return;
//	        	}
//	        	else if (computeHigher <= computeLower && computeHigher <= computeMiddle && intArrayIndex+1 <= intArr.length-1 )
//	        	{
////	        		_histo.intpixel = intArr[intArrayIndex+1][sortRedGreenBlue];
////	        		return;
//	        	}
	            
	        }
	    }

}
