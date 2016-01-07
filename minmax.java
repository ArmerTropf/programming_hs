

class minmax
{
	public static void main(String[]args)
	{
		int[] n = new int [6];
		
		n[1] = 5;
		n[2] = 10;
		n[3] = 3;
		n[4] = 2;
		n[5] = 12;
		
		int min = 0;
		int max = 0;
		int avg = 0;
		int sum = 0;
		
		int i = 1;
			
		while ( i <= 5 )
		{
			if ( i == 1 )
			{	
				max = n[i];
				min = n[i];
			}	
			
			if ( max < n[i] )
				max = n[i];
			
			if ( min > n[i] )
				min = n[i];
			
			sum = sum + n[i];
			i = i + 1;
		}
		avg = ( sum / (i - 1) );
		
		
		System.out.println("Minimum = " + min + "Maxmimum = " + max + "Summe = " + sum + "Durch = " + avg);
		
				
	}
	
}