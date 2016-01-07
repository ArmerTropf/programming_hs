import java.util.HashMap;


public class go 
{

	public static void main(String[]args)
	{
		HashMap<Integer, Integer> go = new HashMap<Integer, Integer>();
		
		go.put(1, 1234);
		go.put(2, 5678);
		
		System.out.println(go.get(2).intValue());
		
		
				
	}
	
	
}
