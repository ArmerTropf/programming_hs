/**
 * 
 * Binärsuche wird für die prozentuale Ersetzung von Farben
 * genutzt.
 * Rückgabewert ist der Index an d
 * 
 * @author Michael Günster
 *
 */
public class BinarySearch 
{
		
	public static int search(int[][] intArrColorsToSearchFor, int[] intArrToReplaceColorRGB, int intColorLayer) 
	{
	    return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intColorLayer, (intColorLayer+1)%3, (intColorLayer+2)%3, 0, intArrColorsToSearchFor.length - 1);
	}
	
	private static int searchRec(int[][] intArrColorsToSearchFor, int[] intArrToReplaceColorRGB, int intLayerRED, int intLayerGREEN, int intLayerBLUE, int intLeftBound, int intRightBound) 
	{
	    if (intLeftBound <= intRightBound) 
	    {
	        final int intColors = intLeftBound + ((intRightBound - intLeftBound) / 2);
	        final int intKeepArrRedValue = intArrColorsToSearchFor[intColors][intLayerRED];
	        final int intReplacedArrRedValue = intArrToReplaceColorRGB[intLayerRED];
	
	        if (intKeepArrRedValue < intReplacedArrRedValue)
	            return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intColors + 1, intRightBound);
	        else if (intKeepArrRedValue > intReplacedArrRedValue)
	            return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intLeftBound, intColors - 1);
	        else 
	        {
	            final int intKeepArrGreenValue = intArrColorsToSearchFor[intColors][intLayerGREEN];
	            final int intReplacedArrGreenValue = intArrToReplaceColorRGB[intLayerGREEN];
	
	            if(intKeepArrGreenValue < intReplacedArrGreenValue)
	                return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intColors + 1, intRightBound);
	            else if(intKeepArrGreenValue > intReplacedArrGreenValue)
	                return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intLeftBound, intColors - 1);
	            else 
	            {
	                final int intKeepArrBlueValue = intArrColorsToSearchFor[intColors][intLayerBLUE];
	                final int intReplacedArrBlueValue = intArrToReplaceColorRGB[intLayerBLUE];
	
	                if(intKeepArrBlueValue < intReplacedArrBlueValue)
	                    return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intColors + 1, intRightBound);
	                else if(intKeepArrBlueValue < intReplacedArrBlueValue)
	                    return searchRec(intArrColorsToSearchFor, intArrToReplaceColorRGB, intLayerRED, intLayerGREEN, intLayerBLUE, intLeftBound, intColors - 1);
	                else
	                    return intColors;
	            }
	        }
	    } 
	    else
	        return intRightBound;
	}

}

