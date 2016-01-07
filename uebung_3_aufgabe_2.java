


class uebung_3_aufgabe_2
{
	public static void main (String[]args)
	{
		int i_zeile = 23;
		int i_spalte = 43;
		
		int zaehler_zeile = 0;
		int zaehler_spalte = 0;
		
		while ( zaehler_zeile < i_zeile )
		{
						
			while ( zaehler_spalte < i_spalte  )
			{
				if ( (zaehler_spalte % (zaehler_zeile+2) )== 0 )
					System.out.print("X");
				else
					System.out.print("_");
					
			
				zaehler_spalte += 1;
			}
			System.out.print("\r\n");
			zaehler_spalte = 0;
			zaehler_zeile += 1;
			
		}
	}
}