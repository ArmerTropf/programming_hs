import java.io.ObjectInputStream.GetField;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;


public class go 
{
	public static void main(String[]args)
	{
		HashMap<Integer,Integer> myMap  = new HashMap<Integer, Integer>();   
		
		int [] t1 = new int[] {1,1,1,2,2,2,2,3,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7};	
//		BinarySearch myBinary = new BinarySearch();
		
//		Quicksort.sort(t1, 0, t1.length-1, 0);
		
		
		int t = 1 ;
		myMap.put(t1[0], 1);
		
		
		
		for (int i = 0 ; i < t1.length ; i++)
		{
			if (myMap.get(t1[i]) != null )
			{
				myMap.put(t1[i], myMap.get(t1[i]).intValue() + 1 );
			}
			else
				myMap.put(t1[i],1);
			
			
		}	
		System.out.println("******************");
		
				
		System.out.println(myMap);
		
		
	 	Iterator it = myMap.entrySet().iterator();
	 	int[][]auweia = new int[myMap.size()][2];
	 	int g = 0;
	 	while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
//	        System.out.println(pair.getKey() + " = " + pair.getValue());
	       
	        auweia[g][0] = (int) pair.getKey();
	        auweia[g++][1] = (int) pair.getValue();
	        
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	 	Quicksort.sort(auweia, 0, auweia.length-1, 1);
	 	for (int h = 0 ; h < auweia.length ; h++ )
	 	{
	 		System.out.println(auweia[h][0] + " " + auweia[h][1]);
	 	}

	}


}


