public class class_myMatrix
{
	private double [][] arr_dblMatrix = new double[3][3];
	
	
	class_myMatrix(	double x1, double x2, double x3, 
			 		double y1, double y2, double y3, 
		 			double z1, double z2, double z3)
	{
		arr_dblMatrix[0][0] = x1;arr_dblMatrix[0][1] = x2;arr_dblMatrix[0][2] = x3;
		arr_dblMatrix[1][0] = y1;arr_dblMatrix[1][1] = y2;arr_dblMatrix[1][2] = y3;
		arr_dblMatrix[2][0] = z1;arr_dblMatrix[2][1] = z2;arr_dblMatrix[2][2] = z3;		
	}
	
	class_myMatrix()
	{
		
	}
	
	public class_myVector matrix_Vector_Mult(class_myVector myVector)
	{
		class_myVector newVector = new class_myVector();
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
		return newVector;
	}
	
	public class_myMatrix matrix_Mult(class_myMatrix myMatrix)
	{
		class_myMatrix tmp_Matrix = new class_myMatrix();
		double myTmp = 0.0;
		int t = 0 ;
		
		for(int i = 0; i < arr_dblMatrix.length; i++)
		{
		    for(int j = 0; j < myMatrix.arr_dblMatrix[0].length; j++)
		    {
		        for(int k = 0; k < arr_dblMatrix[0].length; k++)
		        {
		        	tmp_Matrix.arr_dblMatrix[i][j] += arr_dblMatrix[i][k] * myMatrix.arr_dblMatrix[k][j];
		        }
		    }
		}
		
		return tmp_Matrix;
/*		
		function matmult(A,B,l,m,n)
		  C = zeroes(l,n)                           // Ergebnismatrix C mit Nullen initialisieren
		  for i = 1 to l                            // Schleife über die Zeilen von C
		    for k = 1 to n                          // Schleife über die Spalten von C
		      for j = 1 to m                        // Schleife über die Spalten von A / Zeilen von B
		        C(i,k) = C(i,k) + A(i,j) * B(j,k)   // Bildung der Produktsumme
		      end
		    end
		  end
		  return C
*/
	
	}
	
	
}
