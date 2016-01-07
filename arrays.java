

class arrays
{
	public static void main(String[]args)
	{
		int [] arr1 = new int[5];
		int [] arr2 = new int[15];
		arr1[0]=15;
		
		arr2 = arr1;
		System.out.println(arr2[0]);
	}
}