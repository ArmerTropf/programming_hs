package gameoflife;


class stone
{	
	int int_Alive = 0;
	int int_neighbours = 0;
	
	stone(int isAlive)
	{
		int_Alive = isAlive;
	}
	
	char print(){
		switch (int_Alive)
		{
			case 0: return '0';
			case 1: return '.'; 
			case 2: return 'o';
			case 3: return 'O';
			default: return '*';
		}
	}
	
	void countNeighbour(int myNeighbours){
		int_neighbours = myNeighbours;
	}
	
	int isAlive(){
		return int_Alive;	
	}

	void computeNext(){
		++int_Alive;
	}
	
	void die(){
		int_Alive = 0;
	}
}


class myGame2
{
		int zeilen = 10;
		int spalten = 10;
		int HowManyNeigbours = 3;
		int timer = 2;

		void repeat(stone [][]myStonesArray)
		{
			if (timer == 0)
				return;
			else
			{
				findXNeighbour(myStonesArray);
				ausgabe(myStonesArray);
				--timer;
				repeat(myStonesArray);
			}
		}
			
		void ausgabe(stone [][] myStonesArray)
		{
			for (int i_zeilen = 0 ; i_zeilen < myStonesArray.length; ++i_zeilen)
			{		
				for (int i_spalten = 0; i_spalten < myStonesArray[i_zeilen].length; ++i_spalten)
				{
					System.out.print(myStonesArray[i_zeilen][i_spalten].print());
				}			
				System.out.println();
			}
			System.out.println();
		}
		
		int initCell()
		{
			int myRandomInt;	
			myRandomInt = (int)(java.lang.Math.random()*1000);
			return myRandomInt % 2 == 0?1:0;
				
		}

		void findXNeighbour(stone[][] myStonesArray)
		{	
			int count_bool = 0;
			for ( int i_zeile = 0; i_zeile < myStonesArray.length ; ++i_zeile )
			{
				for ( int i_spalte = 0; i_spalte < myStonesArray[i_zeile].length; ++i_spalte  )
				{
	//OBEN	
					//oben links
					//System.out.print(" " + i_zeile + " " + arrOriginalProgram.length + " " + (i_zeile + arrOriginalProgram.length - 1 )% arrOriginalProgram.length + "," +(i_spalte + arrOriginalProgram.length - 1 ) % arrOriginalProgram[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + myStonesArray.length - 1 )% myStonesArray.length][(i_spalte + myStonesArray.length - 1) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;
					//oben
					//System.out.print(" " + i_zeile + " " + MyStandardProgrammArray.length + " " + (i_zeile + MyStandardProgrammArray.length - 1 ) % MyStandardProgrammArray.length + "," +(i_spalte + MyStandardProgrammArray.length ) % MyStandardProgrammArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + myStonesArray.length - 1 ) % myStonesArray.length][(i_spalte + myStonesArray.length) % myStonesArray.length].isAlive() > 0)
						++count_bool;
					//oben rechts
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + myStonesArray.length - 1 ) % myStonesArray.length + "," +(i_spalte + myStonesArray.length + 1) % myStonesArray[i_zeile].length + " | " );
					if ((i_spalte % myStonesArray[i_zeile].length - 1) != 0 && myStonesArray[(i_zeile + myStonesArray.length - 1 ) % myStonesArray.length][(i_spalte + myStonesArray.length + 1) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;
	//MITTE
					//mitte links
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + 1 + myStonesArray.length - 1 )%myStonesArray.length + "," +(i_spalte + myStonesArray.length -1 ) % myStonesArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + 1 + myStonesArray.length - 1 )%myStonesArray.length][(i_spalte + myStonesArray.length- 1) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;
					//mitte rechts
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + 1 + myStonesArray.length - 1 )%myStonesArray.length + "," +(i_spalte + myStonesArray.length + 1) % myStonesArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + 1 + myStonesArray.length - 1 )%myStonesArray.length][(i_spalte + myStonesArray.length + 1) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;				
	//UNTEN				
					//unten links
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + 2 +myStonesArray.length - 1 )%myStonesArray.length + "," +(i_spalte + myStonesArray.length -1) % myStonesArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + 2 + myStonesArray.length - 1 )%myStonesArray.length]      [(i_spalte + myStonesArray.length - 1) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;
					//unten
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + 2 + myStonesArray.length - 1 )%myStonesArray.length + "," +(i_spalte + myStonesArray.length) % myStonesArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + 2 + myStonesArray.length - 1 )%myStonesArray.length][(i_spalte + myStonesArray.length) % myStonesArray[i_zeile].length].isAlive() > 0)
						++count_bool;
					//unten rechts
					//System.out.print(" " + i_zeile + " " + myStonesArray.length + " " + (i_zeile + 2 + myStonesArray.length - 1 )%myStonesArray.length + "," +(i_spalte + myStonesArray.length + 1) % myStonesArray[i_zeile].length + " | " );
					if (myStonesArray[(i_zeile + 2 + myStonesArray.length + 1 )%myStonesArray.length][(i_spalte + myStonesArray[i_zeile].length + 1) % myStonesArray.length].isAlive() > 0)
						++count_bool;

					//Zelle True und True(Nachbar)=2 oder = 3 Wert = True
					if (myStonesArray[i_zeile][i_spalte].isAlive() > 0 && count_bool == 2 || count_bool == 3 )
					{
						myStonesArray[i_zeile][i_spalte].computeNext();
					}
					// Zelle 0 aber 3 Nachbarn  = Zelle True
					else if (myStonesArray[i_zeile][i_spalte].isAlive() == 0 && count_bool == 3 )
					{
						myStonesArray[i_zeile][i_spalte].computeNext();
					}
					else if (count_bool < 2 || count_bool > 3 )
					{
						myStonesArray[i_zeile][i_spalte].die();
					}
					
					//Nachbaranzahl in stone-objekt schreiben
					myStonesArray[i_zeile][i_spalte].countNeighbour(count_bool);
							
					count_bool = 0;
				}
			}	
		}
			
		//Man braucht das myStonesArray nicht wieder zurückzugeben, da die Referenz übergeben wird
		void initStoneArray(stone [][] myStonesArray)
		{
			for (int i = 0 ; i < myStonesArray.length ; ++i )
			{
				for (int j = 0 ; j < myStonesArray[i].length ; ++j  )
				{
					myStonesArray[i][j] = new stone(initCell());
					
				}
			}
		}	
}

public class aufgabe9 {

	public static void  main(String[]args)
	{
		myGame2 newGame = new myGame2();	
		stone [][] myStonesArray = new stone[newGame.zeilen][newGame.spalten];
		newGame.initStoneArray(myStonesArray);
		newGame.ausgabe(myStonesArray);
		newGame.repeat(myStonesArray);
		
	}
}
