package daddeln;
import java.time.LocalTime;
import java.time.Clock;

public class nurso 
{
	public static void main(String[]args)
	{
//		Clock test;
				
		int n = 5000;
		int [][] A,B,C; 
		long myTime1 = 0;
		long myTime2 = 0;
		
		
		A = new int [n][n];
		B = new int [n][n];
		C = new int [n][n];
		
		Object[] all = new Object[3];
		
		all[0] = A;
		all[1] = B;
		all[2] = C;
		
		myTime1 = System.currentTimeMillis();
		for (int g = 0; g != A.length; ++g)
		{
			for (int y = 0; y != A[g].length; ++y)
			{
//				System.out.println("Z="+g+" S=" + y + " " +(int)(Math.random()*100));
				A[g][y] = (int)(Math.random()*10);
			}
		}
		myTime2 = System.currentTimeMillis();
		System.out.println("A Init: " + (myTime2 - myTime1));
		
		
		
		myTime1 = System.currentTimeMillis();
		for (int g = 0; g != A.length; ++g)
		{
			for (int y = 0; y != A[g].length; ++y)
			{
				//System.out.println("AG " + A[g][y] * A[g][y] + (System.currentTimeMillis()));
				
			}
		}
		myTime2 = System.currentTimeMillis();

		System.out.println("Berechnung 1: " + (myTime2 - myTime1));
		
		myTime1 = System.currentTimeMillis();

		for (int g = A.length-1; g != 0; --g)
		{
			for (int y = A[g].length-1; y != 0; --y)
			{
				//System.out.println("AG " + A[g][y] * A[g][y] + (System.currentTimeMillis()));
				
			}
		}
		myTime2 = System.currentTimeMillis();

		System.out.println("Berechnung 2: "+(myTime2 - myTime1));
		
	}
}
