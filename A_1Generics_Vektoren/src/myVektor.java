import java.util.Vector;


public class myVektor <BUMS,BUMS2>
{
	
	public Vector <BUMS> VecClassVar = new Vector <BUMS>(50, 50);
	public Vector <BUMS2> VecClassVar2 = new Vector <BUMS2>(50, 50);
		
	public BUMS Var1;
	public BUMS2 Var2;
	
	
	
	
	myVektor(BUMS t , BUMS2 g)
	{
		Var1 = t;
		Var2 = g;	
	}
	
	
	

}

class view
{
	public static void main(String[]args)
	{
		myVektor<Integer,Double> test = new myVektor<Integer,Double>(5, 5.5);
		
	
		
		
		
		for (int i = 0; i < 1000; ++i )
		{
			test.VecClassVar.add((int)(Math.random()*1000));
			test.VecClassVar2.add(Math.random()*1000);
		}
		
		
		
//		for (Double T  : test.VecClassVar)
//		{
//			System.out.println(T);
//		}
		
		System.out.println(test.Var1 + " " + test.Var2);
		
	}
	
}
