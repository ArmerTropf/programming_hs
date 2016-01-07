package gameoflife;

public class aufgabe5 {
	
	
	public static void main (String[]args)
	{
		
		int zeilen = 5;
		int spalten = 13;
		
		
		ausgabe(oldProgram(zeilen,spalten));
		ausgabe(newProgram(oldProgram(zeilen,spalten)));

		
		
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
		
		int count_bool = 0;
		
		for ( int i_zeile = 0; i_zeile < arrOriginalProgram.length ; ++i_zeile )
		{
			for ( int i_spalte = 0; i_spalte < arrOriginalProgram[i_zeile].length; ++i_spalte  )
			{
				//Erste Zeile
				if ( i_zeile == 0)
				{
					//obere linke Ecke
					if (i_spalte == 0)
					{
						if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
							++count_bool;
					}
					//obere rechte Ecke
					else if (i_spalte == arrOriginalProgram[i_zeile].length -1)
					{
						
						if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
							++count_bool;
						
					}
					//Zwischenraum
					else
					{
						if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
							++count_bool;
					}
					

				}				
				//Letzte Zeile
				if ( i_zeile == arrOriginalProgram.length - 1)
				{
					//untere linke Ecke
					if (i_spalte == 0)
					{
						if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
							++count_bool;
					}
					//untere rechte Ecke
					else if (i_spalte == arrOriginalProgram[i_zeile].length -1)
					{

						if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
							++count_bool;

					}
					//Zwischenraum
					else
					{
						if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte-1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
							++count_bool;
					}
					
				}	
				//Erste Spalte
				if ( i_spalte == 0 &&  i_zeile != 0 && i_zeile != arrOriginalProgram.length -1 )
				{
					//Zwischenraum
						if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile-1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte+1] == true)
							++count_bool;
						if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
							++count_bool;
						
				}
				//Letzte Spalte
				if ( i_spalte == arrOriginalProgram[i_zeile].length-1 && i_zeile != 0  && i_zeile != arrOriginalProgram.length-1)
				{
					//Zwischenraum
					if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile-1][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile+1][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
						++count_bool;
				}
				
				if ( ( i_zeile > 0 && i_zeile < arrOriginalProgram.length-2 ) && ( i_spalte > 0 && i_spalte < arrOriginalProgram[i_zeile].length-2 ) )
				{
					//Zwischenraum
					if (arrOriginalProgram[i_zeile-1][i_spalte] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile-1][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile+1][i_spalte-1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile+1][i_spalte] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile+1][i_spalte+1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile][i_spalte+1] == true)
						++count_bool;
					if (arrOriginalProgram[i_zeile-1][i_spalte+1] == true)
						++count_bool;
				}
				
				
				
				if (arrOriginalProgram[i_zeile][i_spalte] == true && count_bool == 2 || count_bool == 3 )
				{
					arr_neighbour[i_zeile][i_spalte] = true;
				}
				else if (arrOriginalProgram[i_zeile][i_spalte] == false && count_bool == 3 )
				{
					arr_neighbour[i_zeile][i_spalte] = true;
				}
				else if (count_bool < 2 || count_bool > 3 )
				{
					arr_neighbour[i_zeile][i_spalte] = false;
				}
				else
				{
					arr_neighbour[i_zeile][i_spalte] = arrOriginalProgram[i_zeile][i_spalte];
				}
							
				count_bool = 0;
			}
		}
		return arr_neighbour;
		
	}

}
