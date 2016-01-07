public class rekursion
{
	public static void main(String[] args)
	{
		
		
		
		my_test(10);
	//	System.out.println("test");
	}
	
	static void my_test(int myint)
	{
		if ( myint > 0 )
		{	
			my_test(myint-1);
			System.out.println("NOCHT DRIN: " + myint);
			//my_test(myint-1);
		}
		else
			System.out.println("raus");
	}
	
}