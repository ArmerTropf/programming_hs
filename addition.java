/**
 * @(#)addition.java
 *
 *
 * @author 
 * @version 1.00 2014/10/14
 */


public class addition 
{

    public static void main(String[]args) 
    {
//    	int summe 	= 0;
//		int n 		= 5;
//		int i		= 0;
//
//		while(i <= n)
//		{
//			summe = summe + i;
//			i = i + 1;
//			//System.out.println(summe);
//		}
//		
//		System.out.println(summe);


		

		int test[]= new int [5];
		test[0] = 6;
		test[1] = 5;
		test[2] = 16;
		test[3] = 2;
		test[4] = 9;
		
		int max, min, sum, i;
		float avg;
		
		i = 1;
//		if (i == 1)
//		{
			min = test[i-1];
			max = test[i-1];
			sum = 0;
			avg = 0;
//		}
		
			while ( i < 5)
			{
				if (min >= test[i-1])
					min=test[i-1];
				if (max <= test[i-1])
					max=test[i-1];
				sum=sum+test[i-1];
				
				i = i+1;
			}
			avg = sum/i;
		System.out.println("min:"+min+" max:" + max + " sum:" + sum + " avg:" +avg );
			
	}
    	
    
    
    
}