package mathe;

public class GH_7 {

	
	public static void main(String[]args)
	{		
		int eingabe = 27;
		compute(eingabe,1000);
	}
	
	
	public static void compute(int startInt, int timer)
	{
		System.out.print(startInt);
		
		if (startInt == 1)
			return;
		
		if ( timer == 0 )
		{
			return;
		}
		else
		{			
			if (startInt % 2 == 0)
			{
				System.out.print(" \t| " + startInt + " / 2 \t= ");
				System.out.println(startInt/2);
				compute(startInt/2, timer-1);
			}
			else
			{
				System.out.print(" \t| 3 * " + startInt + " + 1 \t= ");
				System.out.println(3*startInt+1);
				compute(3*startInt+1, timer-1);
			}
		}
		
			
		
			
	}
}
