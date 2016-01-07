package gameoflife;

public class aufgabe7 {

	public static void main (String[]args)
	{
		
		int zeilen = 20;
		int spalten = 20;
		
		boolean [][] oldArr;
		boolean [][] newArr;

		
		oldArr = oldProgram(zeilen,spalten);
		newArr = newProgram(oldArr);
			
		repeat(newArr, 1);
		
	}
	
	public static void showMyCoordinates(int [][] myCoordinates)
	{
		System.out.println("Koordinaten");
		for (int i = 0; i < myCoordinates.length; ++i)
		{
			for (int t = 0; t < myCoordinates[i].length; ++t )
			{
				System.out.print(myCoordinates[i][t]+",");
			}
			System.out.println();
		}
		
	}
	
	public static void repeat(boolean [][] arrRepeat, int timer)
	{
	
		if (timer == 0)
			return;
		else
		{
			
			repeat(newProgram(arrRepeat),timer-1);
			ausgabe(arrRepeat);
			showMyCoordinates(findXNeighbour(arrRepeat, 5));
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
		System.out.println();
	}
	
	public static boolean initCellasBoolean()
	{
		int myRandomInt;
		
		myRandomInt = (int)(java.lang.Math.random()*1000);
		
		if (myRandomInt % 2 == 0 )
			return true;
		else 
			return false;
		
	}
	
	public static int [][] findXNeighbour(boolean [] [] arrOriginalProgram , int HowManyNeigbours)
	{
		boolean [][] arr_neighbour = new boolean[arrOriginalProgram.length][arrOriginalProgram[0].length];	
		int [][] myArrayCoordXY = new int[arrOriginalProgram.length*arrOriginalProgram[0].length][2];
		
		int ExactNeigbourFound_ArrayCounter = 0;
		int count_bool = 0;
		
		for ( int i_zeile = 0; i_zeile < arrOriginalProgram.length ; ++i_zeile )
		{
			for ( int i_spalte = 0; i_spalte < arrOriginalProgram[i_zeile].length; ++i_spalte  )
			{
//OBEN	
				//oben links
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length - 1 ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length - 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//oben
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length) % arrOriginalProgram.length] == true)
					++count_bool;
				//oben rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if ((i_spalte % arrOriginalProgram[i_zeile].length - 1) != 0 && arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
//MITTE
				//mitte links
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length -1 ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length- 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//mitte rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;				
//UNTEN				
				//unten links
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 +arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length -1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + arrOriginalProgram.length - 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//unten
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//unten rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length + 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram[i_zeile].length + 1) % arrOriginalProgram.length] == true)
					++count_bool;
				

				
								
				if ( count_bool == HowManyNeigbours )
				{
					//System.out.print(ExactNeigbourFound_ArrayCounter);
					
					myArrayCoordXY[ExactNeigbourFound_ArrayCounter][0] = i_zeile;
					myArrayCoordXY[ExactNeigbourFound_ArrayCounter][1] = i_spalte;
					++ExactNeigbourFound_ArrayCounter;
				}
				
				//Zelle True und True(Nachbar)=2 oder = 3 Wert = True
				if (arrOriginalProgram[i_zeile][i_spalte] == true && count_bool == 2 || count_bool == 3 )
				{
					arr_neighbour[i_zeile][i_spalte] = true;
				}
				// Zelle False aber 3 Nachbarn True = Zelle True
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
		return myArrayCoordXY;
	}
	
	
	public static boolean[][] oldProgram(int zeilen, int spalten)
	{
		boolean [][]arr = new boolean[zeilen][spalten];
		
		for ( int i_zeilen = 0 ; i_zeilen < arr.length ; ++i_zeilen )
		{
					
			for (int i_spalten =0; i_spalten < arr[i_zeilen].length; ++i_spalten)
			{
				//Altes Array initialisiert mit Zufallswerten
				arr[i_zeilen][i_spalten] = initCellasBoolean();
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
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length - 1 ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length - 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//oben
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length) % arrOriginalProgram.length] == true)
					++count_bool;
				//oben rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if ((i_spalte % arrOriginalProgram[i_zeile].length - 1) != 0 && arrOriginalProgram[(i_zeile + arrOriginalProgram.length - 1 ) % arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
//MITTE
				//mitte links
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length -1 ) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length- 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//mitte rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 1 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;				
//UNTEN				
				//unten links
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 +arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length -1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length]      [(i_spalte + arrOriginalProgram.length - 1) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//unten
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram.length) % arrOriginalProgram[i_zeile].length] == true)
					++count_bool;
				//unten rechts
				//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + 2 + arrOriginalProgram.length - 1 )%arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length + 1) % arrOriginalProgram[i_zeile].length + " | " );
				if (arrOriginalProgram[(i_zeile + 2 + arrOriginalProgram.length + 1 )%arrOriginalProgram.length][(i_spalte + arrOriginalProgram[i_zeile].length + 1) % arrOriginalProgram.length] == true)
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
