package gameoflife;

public class aufgabe5 {
	
	
	public static void main (String[]args)
	{
		
		int zeilen = 5;
		int spalten = 13;
		
		
		ausgabe(oldProgram(zeilen,spalten));
		
		ausgabe(newProgram(oldProgram(zeilen,spalten)));

		System.out.print("FDG");
		
	}
	
	
	
	public static void ausgabe(boolean [][] myArray )
	{
		for (int i_zeilen = 0 ; i_zeilen < myArray.length; ++i_zeilen)
		{
			
			for (int i_spalten = 0; i_spalten < myArray[i_zeilen].length; ++i_spalten)
			{
				
				if (myArray[i_zeilen][i_spalten] == true )
					System.out.print("T");
				else				
					System.out.print("F");
			}			
			System.out.print("\n");
			
		}
		System.out.print("\n");
	}
	
	public static boolean[][] oldProgram(int zeilen, int spalten)
	{
		boolean [][]arr = new boolean[zeilen][spalten];
		
		for ( int i_zeilen = 0 ; i_zeilen < arr.length ; ++i_zeilen )
		{
					
			for (int i_spalten =0; i_spalten < arr[i_zeilen].length; ++i_spalten)
			{
								
				if (( i_spalten % (i_zeilen + 2) ) == 0)
						arr[i_zeilen][i_spalten] = true;
				else
						arr[i_zeilen][i_spalten] = false;
			}			
		}
		return arr;
	}
	
	public static boolean [][] newProgram(boolean [][] arrOriginalProgram)
	{
		boolean [][] arr_neighbour = new boolean[arrOriginalProgram.length][arrOriginalProgram[0].length];	
		
		System.out.println(arr_neighbour.length+ " " + arr_neighbour[0].length);
		
		int count_bool = 0;
		
		for ( int i_zeile = 0; i_zeile < arrOriginalProgram.length ; ++i_zeile )
		{
			for ( int i_spalte = 0; i_spalte < arrOriginalProgram[i_zeile].length; ++i_spalte  )
			{
				
		
									
				count_bool = 0;
			}
			
		}
		return arr_neighbour;
		
	}

}
