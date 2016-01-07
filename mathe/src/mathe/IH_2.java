package mathe;

public class IH_2 {

	public static void main(String[]args)
	{
		int timer = 20;
		float My = 0.3f;
		int K = 8;
		float x0 = 8f;
		float xn;
		
		for (int i = 0; i < timer; ++i)
		{
			xn = x0+My*(K-x0)*x0;
			System.out.println(xn);
			x0 = xn;
		}			
	}
}
