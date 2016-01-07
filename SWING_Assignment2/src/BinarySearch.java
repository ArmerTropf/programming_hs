
public class BinarySearch 
{
		
	public static int search(int[][] heystack, int[] needle, int col) 
	{
	
	    return searchRec(heystack, needle, col, (col+1)%3, (col+2)%3, 0, heystack.length - 1);
	}
	
	private static int searchRec(int[][] heystack, int[] needle, int col1, int col2, int col3, int l, int r) 
	{
	    if (l <= r) 
	    {
	        final int m = l + ((r - l) / 2);
	        final int hey1 = heystack[m][col1];
	        final int needle1 = needle[col1];
	
	        if (hey1 < needle1)
	            return searchRec(heystack, needle, col1, col2, col3, m + 1, r);
	        else if (hey1 > needle1)
	            return searchRec(heystack, needle, col1, col2, col3, l, m - 1);
	        else {
	            // component is identical. search for next colorcomponent
	            final int hey2 = heystack[m][col2];
	            final int needle2 = needle[col2];
	
	            if(hey2 < needle2)
	                return searchRec(heystack, needle, col1, col2, col3, m + 1, r);
	            else if(hey2 > needle2)
	                return searchRec(heystack, needle, col1, col2, col3, l, m - 1);
	            else {
	                // and the last one
	                final int hey3 = heystack[m][col3];
	                final int needle3 = needle[col3];
	
	                if(hey3 < needle3)
	                    return searchRec(heystack, needle, col1, col2, col3, m + 1, r);
	                else if(hey3 < needle3)
	                    return searchRec(heystack, needle, col1, col2, col3, l, m - 1);
	                else
	                    return m;
	            }
	        }
	    } else
	        return r;
	}

}










