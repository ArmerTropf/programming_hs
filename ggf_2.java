

class ggf_2
{
	
	public static void main(String[]args)
	{
		int zahl_1 =30, zahl_2=12;
		boolean teilergefunden = false;
		
		while (teilergefunden == false)
		{
			if ( ( zahl_1 % zahl_2 ) != 0)
			{
				zahl_2 = ( zahl_1 % zahl_2 );
				zahl_1 = zahl_2;
				
			}
			else
				teilergefunden = true;
		}
		System.out.println("ggf= "+ zahl_2);
		
	}
	
	
	
}