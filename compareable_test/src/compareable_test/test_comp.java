package compareable_test;

public class  test_comp
{
	public static void main(String[]args)
	{
		Integer[] go = new Integer[3];
		
		go[0] = 1;
		go[1] = 2;
		go[2] = 3;
		
		test wohnung1 = new test();
		test wohnung2 = new test();
		
		wohnung1.QM = 200;
		wohnung2.QM = 100;
		
		test[] Wohnungen = new test[2];
		Wohnungen[0] = wohnung1;
		Wohnungen[1] = wohnung2;
		
		Heap <test,String,Integer> test = new Heap<test,String,Integer>(Wohnungen,"TRET",go);
		
			
		
		
		
//		System.out.println(wohnung1.compareTo(wohnung2));
		
		
		
		
	}

}

class Heap  <K extends Comparable<K>,I extends Comparable<I>,P> 
{
	K [] test1;
	I test2;
	
	Heap(K[] go , I go2, P[] go3)
	{
		test1 = go;
		test2 = go2;
		
		
		System.out.println("BAMMMM " + go[0].compareTo(go[1]));
		
		
		for (int i = 0 ; i < 3 ; ++i)
		{
//			System.out.println(go.compareTo((K) go3[i]));
//			System.out.println(go3[i]);
		}
	}
	
}



class test implements Comparable<test>
{
	int QM;
	String NAME;
	
	
	@Override
	public int compareTo(test go)
	{
		if ( this.QM > go.QM )
			return 1;
		else if ( this.QM == go.QM)
			return 0;
		else if (this.QM == go.QM)
			return -1;
		else
			return 99;
	}
	
}