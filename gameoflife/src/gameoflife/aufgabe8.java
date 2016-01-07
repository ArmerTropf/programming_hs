package gameoflife;



class myGame
{
		int zeilen = 5;
		int spalten = 13;
		int HowManyNeigbours = 3;
		int timer = 20;
		
		int [][] myArrayCoordXY;

		int [][] MyStandardProgrammArray;
		int [][] MyNewProgrammArray;
		
		void showMyCoordinates()
		{
			System.out.println("Koordinaten");
			for (int i = 0; i < myArrayCoordXY.length; ++i)
			{
				for (int t = 0; t < myArrayCoordXY[i].length; ++t )
				{
					System.out.print(myArrayCoordXY[i][t]+",");
				}
				System.out.println();
			}
			
		}

		void repeat()
		{
		
			if (timer == 0)
				return;
			else
			{
				findXNeighbour();
				ausgabe();
				--timer;
				repeat();

			}
			

		}
			
		void ausgabe()
		{
			for (int i_zeilen = 0 ; i_zeilen < MyNewProgrammArray.length; ++i_zeilen)
			{
				
				for (int i_spalten = 0; i_spalten < MyNewProgrammArray[i_zeilen].length; ++i_spalten)
				{
					switch (MyNewProgrammArray[i_zeilen][i_spalten])
					{
						case 0: System.out.print("0");
						break;
						case 1: System.out.print(".");
						break;
						case 2: System.out.print("o");
						break;
						case 3: System.out.print("O");
						break;
						default: System.out.print("*");
						break;
					}
				}			
				System.out.print("\n");
				
			}
			System.out.println();
		}
		
		int initCellasBoolean()
		{
			int myRandomInt;
			
			myRandomInt = (int)(java.lang.Math.random()*1000);
			
			if (myRandomInt % 2 == 0 )
				return 1;
			else 
				return 0;	
		}
		
		void findXNeighbour()
		{	
			myArrayCoordXY = new int[MyStandardProgrammArray.length*MyStandardProgrammArray[0].length][2];
			
			int ExactNeigbourFound_ArrayCounter = 0;
			int count_bool = 0;
			
			for ( int i_zeile = 0; i_zeile < MyStandardProgrammArray.length ; ++i_zeile )
			{
				for ( int i_spalte = 0; i_spalte < MyStandardProgrammArray[i_zeile].length; ++i_spalte  )
				{
	//OBEN	
					//oben links
					//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length - 1 ) % arrOriginalProgram[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + MyStandardProgrammArray.length - 1 )% MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length - 1) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;
					//oben
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + MyStandardProgrammArray.length - 1 ) % MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length ) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + MyStandardProgrammArray.length - 1 ) % MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length) % MyStandardProgrammArray.length] > 0)
						++count_bool;
					//oben rechts
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + MyStandardProgrammArray.length - 1 ) % MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length + 1) % MyStandardProgrammArray[i_zeile].length + " | " );
					if ((i_spalte % MyStandardProgrammArray[i_zeile].length - 1) != 0 && MyStandardProgrammArray[(i_zeile + MyStandardProgrammArray.length - 1 ) % MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length + 1) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;
	//MITTE
					//mitte links
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + 1 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length -1 ) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + 1 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length- 1) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;
					//mitte rechts
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + 1 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length + 1) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + 1 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length + 1) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;				
	//UNTEN				
					//unten links
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + 2 +MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length -1) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + 2 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length]      [(i_spalte + MyStandardProgrammArray.length - 1) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;
					//unten
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + 2 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + 2 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray.length) % MyStandardProgrammArray[i_zeile].length] > 0)
						++count_bool;
					//unten rechts
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + 2 + MyStandardProgrammArray.length - 1 )%MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length + 1) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (MyStandardProgrammArray[(i_zeile + 2 + MyStandardProgrammArray.length + 1 )%MyStandardProgrammArray.length][(i_spalte + MyStandardProgrammArray[i_zeile].length + 1) % MyStandardProgrammArray.length] > 0)
						++count_bool;
					

					
					//Koordinaten wenn Nachbaranzahl gefunden				
					if ( count_bool == HowManyNeigbours )
					{
						//System.out.print(ExactNeigbourFound_ArrayCounter);
						
						myArrayCoordXY[ExactNeigbourFound_ArrayCounter][0] = i_zeile;
						myArrayCoordXY[ExactNeigbourFound_ArrayCounter][1] = i_spalte;
						++ExactNeigbourFound_ArrayCounter;
					}
					
					//Zelle True und True(Nachbar)=2 oder = 3 Wert = True
					if (MyStandardProgrammArray[i_zeile][i_spalte] > 0 && count_bool == 2 || count_bool == 3 )
					{
						++MyNewProgrammArray[i_zeile][i_spalte];
					}
					// Zelle 0 aber 3 Nachbarn  = Zelle True
					else if (MyStandardProgrammArray[i_zeile][i_spalte] == 0 && count_bool == 3 )
					{
						++MyNewProgrammArray[i_zeile][i_spalte];
					}
					else if (count_bool < 2 || count_bool > 3 )
					{
						MyNewProgrammArray[i_zeile][i_spalte] = 0;
					}
					else
					{
						MyNewProgrammArray[i_zeile][i_spalte] = MyStandardProgrammArray[i_zeile][i_spalte];
					}
					
					count_bool = 0;
				}
			}
			MyStandardProgrammArray = MyNewProgrammArray;
		}
		
		
		void compute_oldProgram()
		{
			MyStandardProgrammArray = new int[zeilen][spalten];
			for ( int i_zeilen = 0 ; i_zeilen < MyStandardProgrammArray.length ; ++i_zeilen )
			{
						
				for (int i_spalten =0; i_spalten < MyStandardProgrammArray[i_zeilen].length; ++i_spalten)
				{
					//Altes Array initialisiert mit Zufallswerten
					MyStandardProgrammArray[i_zeilen][i_spalten] = initCellasBoolean();
				}			
			}
			MyNewProgrammArray = MyStandardProgrammArray;
		}
		
}



public class aufgabe8 {

		public static void main(String[]args)
		{
			myGame test = new myGame();
			test.compute_oldProgram();
			test.ausgabe();
			test.repeat();
		}
}
