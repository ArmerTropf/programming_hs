



public class myGenerics {
	
	public static void main(String[]args)
	{
		mytest<Integer,Double> go1 = new mytest<Integer,Double>(); 
		
	}
	
	public static class mytest<T,G>
	{
		myOutGen<Integer,String,Double> go2 = new myOutGen<Integer,String,Double>(1233,"!"); 
	}
}

class myOutGen <H,Z,J>
{
	H babyH;
	Z babyZ;
	J babyJ;
	
	myOutGen(H bam1, Z bam2)
	{
		babyH = bam1;
		babyZ = bam2; 
	}
	
}
