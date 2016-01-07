package mathe;

public class IH_1 {
	
	public static void main(String[]args)
	{
		int timer = 50;
		
		int x0 = 0;
		int q = 1;
		
		int xn;
		
		for (int i = 0; i < timer; ++i)
		{
			xn = q ^ i * x0;		
			x0 = xn;
			System.out.println(x0);
		}			
	}
}
