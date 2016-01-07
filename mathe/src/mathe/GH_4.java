package mathe;

public class GH_4 {
	
	public static void main(String[]args)
	{
		
		long [] a = new long[80];
//		double [] b = new double[50];
		a[0] = 27;
//		a[1] = 54.3f;
//		a[2] = 54.3f;
		
//		b[0] = 0;
//		b[1] = 0.1;
		
//		getMath(a, 0);
//		getMath(b, 0);
		
		
		System.out.printf("\tMit Float \t\t Mit Double\n");
		for (int i = 1 ; i < a.length ; i++ )
		{
			
			//a[i]=4*(1-a[i-1])*a[i-1];
			// 6. a[i] = a[i-1]+2*(a[i-2])-a[i-3];
			a[i] = (a[i-1] % 2 == 0 ) ? a[i-1]/2 : (a[i-1] * 3 + 1) / 2 ;
			
			//b[i]=4*(1-b[i-1])*b[i-1];
			
			System.out.println(a[i]);
			
			//System.out.printf("%d\t %1.10f",i,a[i]);
			System.out.print("\t");
//			System.out.printf("\t %1.10f",b[i]);
			//System.out.print(a[i]/a[i-1]);
		}
	
		
	}
	
	public static void getMath( float newA[], int iAnzahl )
	{
		
		if (iAnzahl == newA.length)
		{
			return;
		}
		else if (iAnzahl == 0 || iAnzahl == 1)
		{
			getMath(newA, iAnzahl+1);
		}
		else
		{
			newA[iAnzahl] = 4*(1-newA[iAnzahl-1])*newA[iAnzahl-1];
			getMath(newA, iAnzahl+1);
			return;
		}
					
		
	}
	
	public static void getMath( double newB[], int iAnzahl )
	{
		
		if (iAnzahl == newB.length)
		{
			return;
		}
		else if (iAnzahl == 0 || iAnzahl == 1)
		{
			getMath(newB, iAnzahl+1);
		}
		else
		{
			newB[iAnzahl] = 4*(1-newB[iAnzahl-1])*newB[iAnzahl-1];
			getMath(newB, iAnzahl+1);
			return;
		}
					
		
	}
}
