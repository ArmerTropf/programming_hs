


class schaltjahr_berechnen
{
	

	public static void main(String[]args)
	{
		int 	int_jahr 		= 2000;
		boolean	bool_schaltjahr 	= false;
		
		
		
		if ( ((int_jahr % 400)*400 )==0)
			bool_schaltjahr = true;
		else if ((int_jahr % 4)==0)
			bool_schaltjahr = true;
		else if ((int_jahr % 400)==0)
			bool_schaltjahr = false;
		else
			bool_schaltjahr = false;
			
		int int_jahrzaehler = 1900;
		
		while (int_jahrzaehler != 2001)
		{
			if ( ((int_jahrzaehler % 400)*400 )==0)
				bool_schaltjahr = true;
			else if ((int_jahrzaehler % 4)==0)
				bool_schaltjahr = true;
			else if ((int_jahrzaehler % 400)==0)
				bool_schaltjahr = false;
			else
				bool_schaltjahr = false;
			
			if (bool_schaltjahr == true)
				System.out.println(int_jahrzaehler + "=" + bool_schaltjahr);
			int_jahrzaehler++;
		}
			
		
		
	//	System.out.println(bool_schaltjahr);
	}


}