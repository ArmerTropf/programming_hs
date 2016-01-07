
class MyVector 
{
	public double [] arr_dblVector = new double [3]; 
	
	MyVector(double x, double y, double z)
	{
		setVector(x, y, z );
	}
	
	MyVector()
	{
		
	}
	
	public void setVector(double x, double y, double z)
	{
		arr_dblVector[0] = x;
		arr_dblVector[1] = y;
		arr_dblVector[2] = z;		
	}
	
	public void setX(double value) 
	{
		arr_dblVector[0] = value;
	}
	public void setY(double value) 
	{
		arr_dblVector[1] = value;
	}
	public void setZ(double value) 
	{
		arr_dblVector[2] = value;
	}
	public double getX() 
	{
		return arr_dblVector[0];
	}
	public double getY() 
	{
		return arr_dblVector[1];
	}
	public double getZ() 
	{
		return arr_dblVector[2];
	}
}
