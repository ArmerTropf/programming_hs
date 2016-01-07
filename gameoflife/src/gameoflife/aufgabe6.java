package gameoflife;

public class aufgabe6 {
	
	
	public static void main (String[]args)
	{
		
		int zeilen = 5;
		int spalten = 13;
		

		ausgabe(oldProgram(zeilen,spalten));
		ausgabe(newProgram(oldProgram(zeilen,spalten)));

		repeat(newProgram(oldProgram(zeilen,spalten)), 8);
		
	}
	public static void repeat(boolean [][] arrRepeat, int timer)
	{
	
		for (int i = 0 ; i < timer ; ++i )
		{
			repeat(newProgram(arrRepeat),timer-1);
			ausgabe(arrRepeat);	
			
			
		}
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
//OBEN	
				//oben links
				if (i_zeile != 0 && i_spalte != 0 && arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length]      [(i_spalte + 1 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				//oben
				if (arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length]      [(i_spalte + 1 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				//oben rechts
				if (i_zeile != 0 && (i_spalte % arrOriginalProgram[i_zeile].length - 1) != 0 && arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length]      [(i_spalte + 2 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
//MITTE
				//mitte links
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + 0 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				//mitte rechts
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + 2 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;				
//UNTEN				
				//unten links
				if (i_zeile != arrOriginalProgram.length-1 && i_spalte != 0 && arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + 0 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				//unten
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + 1 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				//unten rechts
				if ( (i_zeile % arrOriginalProgram.length-1) != 0 && (i_spalte % arrOriginalProgram[i_zeile].length-1) != 0 && arrOriginalProgram[(i_zeile + 2 +arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + 2 + arrOriginalProgram.length - 1) % arrOriginalProgram.length] == true)
					++count_bool;
				

				
				
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
