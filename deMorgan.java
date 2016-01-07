
class deMorgan
{
	
	public static void main(String[]args)
	{
		
		boolean A = false;
		boolean B = false;
		boolean OR = A||B;
		boolean DeMorgan = !(!A&&!B);
		
				
		System.out.println("A||B = "+ OR);
		System.out.println("!A&&!B = "+ DeMorgan);
		
		if (OR == DeMorgan)
			System.out.println("Wert für: A||B = "+ OR + " DeMorgan: !A&&!B = "+ DeMorgan  );
			
	}
		
}