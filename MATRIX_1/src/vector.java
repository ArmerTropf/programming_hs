

public class vector
{
	public static void main(String[] args)
	{
		myVector myVec = new myVector(
				0.0, 
				3.0, 
				1.0);
		
		myMatrix myMat = new myMatrix(
				1, 0, -1, 
				0, 1, -1, 
				0, 0, 1
		);
	
		myMat.matrix_Mult(myVec);
	
	}
}


class myVector 
{
	public double [] arr_dblVector = new double [3]; 
	
	myVector(double x, double y, double z)
	{
		setVector(x, y, z );
	}
	
	myVector()
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

class myMatrix
{
	private double [][] arr_dblMatrix = new double[3][3];
	
	
	myMatrix(double x1, double x2, double x3, 
			 double y1, double y2, double y3, 
			 double z1, double z2, double z3)
	{
		arr_dblMatrix[0][0] = x1;arr_dblMatrix[0][1] = x2;arr_dblMatrix[0][2] = x3;
		arr_dblMatrix[1][0] = y1;arr_dblMatrix[1][1] = y2;arr_dblMatrix[1][2] = y3;
		arr_dblMatrix[2][0] = z1;arr_dblMatrix[2][1] = z2;arr_dblMatrix[2][2] = z3;		
	}
	
	public myVector matrix_Mult(myVector myVector)
	{
		myVector newVector = new myVector();
		double myTmp = 0.0;
		
		for (int i = 0 ; i < 3 ; i++)
		{
			for (int k = 0 ; k < 3 ; k++)
			{	
				myTmp += arr_dblMatrix[i][k] * myVector.arr_dblVector[k];
			}
			newVector.arr_dblVector[i] = myTmp;
			myTmp = 0.0;
		}
		System.out.println(newVector.getX() + "\n" + newVector.getY());
		return newVector;
	}
	
	
}