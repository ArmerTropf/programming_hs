
class ausgabe
{
	
	public static void main(String[]args)
	{
		
		int zeile_spalte = 4;
		int zeile_i = 1;
		int spalte_i = 1;
		
		
		
			
		while (zeile_i <= zeile_spalte)
		{
			while (spalte_i <= zeile_spalte+7)
			{
				if ( ( ( zeile_i == 1 ) && ( (spalte_i % 2) == 0) ) )
					System.out.print("_");
				else if ( ( ( zeile_i == 1 ) && ( (spalte_i % 2) > 0) ) )
					System.out.print("X");
				
				if ( ( zeile_i == 2 ) && ( ( spalte_i == 1 ) || ( spalte_i +3 == spalte_i ) ) ) 
					System.out.print("X");
				else
				//	System.out.print("_");	
					
				spalte_i += 1;
			} 
			System.out.print("\r");
			spalte_i = 1;
			zeile_i += 1;
		}
		
		
		
		
	}
}