package tut_schaltjahr;

public class aufg1 
{
	public static void main(String[] args )
	{
		for (int i = 0 ; i < 100000 ; i++)
		{
			System.out.println(i);
			if (i == 99999)
				System.out.println("go");
		}
		
//		int [] jahr = {2000,2001,2002,2004};
//		
//		for(int i = 0; i < jahr.length; i++) {
//		boolean schaltjahr = false;
//		if ( ( jahr[i] % 4 ) == 0)
//		{
//			schaltjahr = true;
//			
//			if ( ( jahr[i] % 100 ) == 0)
//			{
//				schaltjahr = false;
//			}
//		}
//		if ( ( jahr[i] % 400 ) == 0 )
//		{
//			schaltjahr = true;
//		}
//		
//		System.out.println(schaltjahr);
//		}
	}	
}

