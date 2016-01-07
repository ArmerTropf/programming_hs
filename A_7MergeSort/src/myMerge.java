
public class myMerge 
{
	
	//Das ist nur die Zusammenführung
	public static void main(String[]args)
	{
		Integer [] arr1 = new Integer[5];
		Integer [] arr2 = new Integer[5];
		
		arr1[0] = 200;
		arr1[1] = 102;
		arr1[2] = 45;
		arr1[3] = 23;
		arr1[4] = 16;
		
		arr2[0] = 155;
		arr2[1] = 40;
		arr2[2] = 30;
		arr2[3] = 22;
		arr2[4] = -17;
		
		ausgabe(sortiere(arr1, arr2));
		
	}
	
	
	static Integer[] sortiere(Integer[] arr1, Integer[] arr2)
	{
		int point_iArr1 = arr1.length-1;
		int point_iArr2 = arr2.length-1;
		int count_all = 0;
		int count_MAX = arr1.length + arr2.length;
			
		Integer[] myNewArr = new Integer[arr1.length+arr2.length];
		
		while (count_all < count_MAX)
		{
			if (point_iArr2 == -1 )
			{
				for (int i = count_all;  i < count_MAX ; i++)
				{
					myNewArr[i] = arr1[point_iArr1--];
					count_all++;
				}
			}
			else if (point_iArr2 == -1 )
			{
				for (int i = count_all;  i < count_MAX ; i++)
				{
					myNewArr[i] = arr1[point_iArr1--];
					count_all++;
				}
			}
			else
			{
				if (arr1[point_iArr1] < arr2[point_iArr2])
					myNewArr[count_all++] = arr1[point_iArr1--];
				else
					myNewArr[count_all++] = arr2[point_iArr2--];
			}
		}

		return myNewArr;
	}
	
	static void ausgabe(Integer[] myArr)
	{
		for (int i = 0 ; i < myArr.length ; i++)
		{
			System.out.println(myArr[i]);
		}
	}


}



