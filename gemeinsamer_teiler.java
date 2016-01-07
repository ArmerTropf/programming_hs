
class gemeinsamer_teiler
{
	
	public static void main(String[]args)
	{
		
		int zahl1 = 5433;
		int zahl2 = 23;
		int erg = 99;
				
		while (erg != 0)
		{
			
			if ((zahl1 % zahl2) != 0)
			{
				System.out.println(zahl1 + " \t: " + zahl2 + " \t=" + zahl1/zahl2 + " \tRest: "+zahl1%zahl2);
				zahl1 = zahl1 / zahl2;
				zahl2 = zahl1 % zahl2;
			}
			else
				erg = 0;
		}
		System.out.println(zahl1 + " \t\t: " + zahl2 + " \t=" + zahl1/zahl2 + " \tRest: "+zahl1%zahl2);
		System.out.println(GG)
		
		
		
	}
	
}