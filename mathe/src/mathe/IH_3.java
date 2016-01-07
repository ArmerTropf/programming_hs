package mathe;

public class IH_3 {

	public static void main(String[]args)
	{
		//double My = 0.1; 		// gegen 0
		double My = 2.9;		// gegen 0.6551

		double x0 = 0.1f;
		double xn = 0;
		
				
		int counter =100;
				
		for (int i = 0; i < counter; ++i)
		{		
			xn = My*(1-x0)*x0;
			x0 = xn;
			System.out.println(My+ ": \t" + x0);
		}		
		
	}
	
}
