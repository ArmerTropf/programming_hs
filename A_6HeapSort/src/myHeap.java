
public class myHeap {
	
	
	
	public static void main(String[]args)
	{
		int [] myArr = new int[7];
		
				
		Heap_Sort mySort = new Heap_Sort();
		
		mySort.insert(myArr, 25);
		mySort.insert(myArr, 12);
		mySort.insert(myArr, 5);
		mySort.insert(myArr, 3);
		mySort.insert(myArr, 2);
		mySort.insert(myArr, 36);
		mySort.insert(myArr, 42);
		
		mySort.showArray(myArr);
		System.out.println();
		
//		mySort.heapUp(myArr);
		mySort.showArray(myArr);
	}
}

class Heap_Sort
{
	int myCounter_elements = 0;
	int myTempElement;
	int myCounter_elements_temp = 0;
	
	
	
	Heap_Sort()
	{
		myCounter_elements_temp = myCounter_elements;
	}
	
	public void heapUp(int[] go)
	{
		myCounter_elements_temp = myCounter_elements;

		if (myCounter_elements_temp > 1)
		{	
			while ( myCounter_elements_temp  > 0 )
			{
				int sonElement = myCounter_elements_temp-1;
				int fatherElement = (myCounter_elements_temp-2)/2;
				
				if (go[sonElement] > go[fatherElement] )
				{
					myTempElement = go[fatherElement];
					go[fatherElement] = go[sonElement];
					go[sonElement] = myTempElement;
					
					if (fatherElement != 0 )
						myCounter_elements_temp  = fatherElement+1;
					else
						myCounter_elements_temp  = fatherElement;
				}
				else
					myCounter_elements_temp  = fatherElement;
			}
		}
	}
	
	public void insert(int [] myArr, int meineZahl)
	{
		myArr[myCounter_elements++] = meineZahl;
		heapUp(myArr);
		
	}
	
	public void showArray(int[] go)
	{
		for (int i = 0 ; i <= go.length-1; ++i)
		{
			System.out.println(go[i]);
		}
		
	}

}
