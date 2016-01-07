
/**
 * 
 * Aufbau einer Matrix.
 * Matrix x Vector
 * Matrix x Matrix
 * 
 * @author ArmerTropf
 *
 */
public class Matrix
{
	private double [][] arr_dblMatrix = new double[3][3];
	
	
	Matrix(	double x1, double x2, double x3, 
			 	double y1, double y2, double y3, 
		 		double z1, double z2, double z3)
	{
		arr_dblMatrix[0][0] = x1;arr_dblMatrix[0][1] = x2;arr_dblMatrix[0][2] = x3;
		arr_dblMatrix[1][0] = y1;arr_dblMatrix[1][1] = y2;arr_dblMatrix[1][2] = y3;
		arr_dblMatrix[2][0] = z1;arr_dblMatrix[2][1] = z2;arr_dblMatrix[2][2] = z3;		
	}
	
	Matrix()
	{
		
	}
	
	public Vector matrix_Vector_Mult(Vector vector)
	{
		Vector newVector = new Vector();
		double myTmp = 0.0;
		
		for (int i = 0 ; i < 3 ; i++)
		{
			for (int k = 0 ; k < 3 ; k++)
			{	
				myTmp += arr_dblMatrix[i][k] * vector.arr_dblVector[k];
			}
			newVector.arr_dblVector[i] = myTmp;
			myTmp = 0.0;
		}
		return newVector;
	}
	
	public Matrix matrix_Mult(Matrix Matrix)
	{
		Matrix tmp_Matrix = new Matrix();
		
		for(int i = 0; i < arr_dblMatrix.length; i++)
		{
		    for(int j = 0; j < Matrix.arr_dblMatrix[0].length; j++)
		    {
		        for(int k = 0; k < arr_dblMatrix[0].length; k++)
		        {
		        	tmp_Matrix.arr_dblMatrix[i][j] += arr_dblMatrix[i][k] * Matrix.arr_dblMatrix[k][j];
		        }
		    }
		}
		
		return tmp_Matrix;	
	}
	
	
}
