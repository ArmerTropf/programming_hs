


class uebung_4
{
	public static void main (String[]args)
	{
		
		int zeilen = 4;
		int spalten = 4;
		
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
		
//		i_zeilen = 0;
		
		//AUSGABE
		for (int i_zeilen = 0 ; i_zeilen < arr.length; ++i_zeilen)
		{
			
			for (int i_spalten = 0; i_spalten < arr[i_zeilen].length; ++i_spalten)
			{
				
				if (arr[i_zeilen][i_spalten] == true )
					System.out.print("T");
				else				
					System.out.print("F");
			}			
			System.out.print("\n");
			
		}
		
		boolean [][] arr_neighbour = new boolean[arr.length][arr[0].length];
		
		int count_bool = 0;
		
		
		for ( int i_zeile = 0; i_zeile < arr.length ; ++i_zeile )
		{
			for ( int i_spalte = 0; i_spalte < arr[i_zeile].length; ++i_spalte  )
			{
				//System.out.print(arr[i_zeile][i_spalte]);
				
					//ecke links oben
					if ( (i_spalte == 0) && (i_zeile == 0) )
					{
						//rechts
						if( arr[i_zeile][i_spalte+1] == true )
						{
							++count_bool;
							System.out.println("ecke oben links");
						}
						//rechts unten
						if( arr[i_zeile+1][i_spalte+1] == true )
						{
							++count_bool;
							System.out.println("ecke rechts");
						}
						//unten
						if( arr[i_zeile+1][i_spalte] == true )
						{
							++count_bool;
							System.out.println("ecke unten");
						}
						
					} 
						
					if ( (i_zeile > 0 && i_spalte > 0)  && ( (i_spalte < arr[i_zeile].length-1 ) && ( i_zeile < arr.length-1 ) )) 
					{
						System.out.println(i_zeile + " " + i_spalte);
//						//OBEN LINKS
//						if( arr[i_zeile-1][i_spalte-1] == true )
//						{
//							++count_bool;
//							//System.out.println("oben links");
//						}
//						//OBEN
//						if( arr[i_zeile-1][i_spalte] == true )
//						{
//							++count_bool;
//							//System.out.println("oben");
//						}
//						//OBEN RECHTS
//						if( arr[i_zeile-1][i_spalte+1] == true )
//						{
//							++count_bool;
//							//System.out.println("oben rechts");
//						}
//						//LINKS
//						if( arr[i_zeile][i_spalte-1] == true )
//						{
//							++count_bool;
//							//System.out.println("links");
//						}
							
					}
					
				
				
				if ( ( arr[i_zeile][i_spalte] == true ) && ( count_bool == 2 || count_bool == 3 ) )
					arr_neighbour[i_zeile][i_spalte] = true;
				else if ( arr[i_zeile][i_spalte] == false && count_bool == 3 )
					arr_neighbour[i_zeile][i_spalte] = true;
				else if ( count_bool > 3 || count_bool < 2 )
					arr_neighbour[i_zeile][i_spalte] = false;
				else
					arr_neighbour[i_zeile][i_spalte] = false;
					
				count_bool = 0;
			}
			
		}
		
			//AUSGABE
		
		System.out.println();
		for (int i_zeilen_neighbour = 0 ; i_zeilen_neighbour < arr_neighbour.length; ++i_zeilen_neighbour)
		{
			
			for (int i_spalten_neighbour = 0; i_spalten_neighbour < arr_neighbour[i_zeilen_neighbour].length; ++i_spalten_neighbour)
			{
				
				if (arr_neighbour[i_zeilen_neighbour][i_spalten_neighbour] == true )
					System.out.print("T");
				else				
					System.out.print("F");
			}			
			System.out.print("\n");
			
		} 
		
		
	}
}