


class uebung_4
{
	public static void main (String[]args)
	{
		
		int zeilen = 5;
		int spalten = 11;
		int i_zeilen = 0;
		
		boolean [][]arr = new boolean[zeilen][spalten];
			
		while (i_zeilen < arr.length)
		{
			int i_spalten = 0;
			
			while (i_spalten < arr[i_zeilen].length)
			{
								
				if (( i_spalten % (i_zeilen + 2) ) == 0)
						arr[i_zeilen][i_spalten] = true;
				else
						arr[i_zeilen][i_spalten] = false;
						
				++i_spalten;
			}			
			++i_zeilen;
		}
		
		i_zeilen = 0;
		
		//AUSGABE
		while (i_zeilen < arr.length)
		{
			int i_spalten = 0;
			while (i_spalten < arr[i_zeilen].length)
			{
				
				if (arr[i_zeilen][i_spalten] == true )
					System.out.print("X");
				else				
					System.out.print("_");
				++i_spalten;
			}			
			System.out.print("\n");
			
			++i_zeilen;
		}
		
		
	}
}