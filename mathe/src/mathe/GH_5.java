package mathe;

public class GH_5 
{
		
	public static void main(String[]args)
	{
		
		float [] a = new float[51];
		a[0] = 0.001F;
		a[1] = 9;
		getMath(a, 0);
		
		for (int i = 0 ; i < a.length ; i++ )
		{
			System.out.println(i + "  "+a [i]);
		}
	
		
	}
	
	public static void getMath( float newA[], int iAnzahl )
	{
		
		if (iAnzahl == newA.length-2)
		{
			return;
		}
		else if (iAnzahl == 0)
		{
			newA[iAnzahl+2] = newA[iAnzahl]+newA[iAnzahl+1];
			getMath(newA, iAnzahl+1);
		}
		else
		{
			newA[iAnzahl+1] = newA[iAnzahl]+newA[iAnzahl-1];
			getMath(newA, iAnzahl+1);
			return;
		}
					
		
	}

 
	
}
