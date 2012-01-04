/**
 * 
 *
 */
package hyperGraph;

import hyperGraph.interfaces.Matrix;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 *
 */
public class Main {
	public static void main(String[] args){
		int[] testMatrixArray = {0, 1, 0, 1,
				  				 0, 1, 0, 1,
				  				 0, 1, 0, 1};
		Matrix testMatrix = Values.matrix(3, 4, testMatrixArray);
		System.out.println("testMatrix");
		System.out.println(testMatrix);
		
		Matrix emptyMatrix = Values.matrix(0,0, new int[0]);
		System.out.println("emptyMatrix");
		System.out.println(emptyMatrix);
		
		Matrix noEdgeOneVertexMatrix=emptyMatrix.addRow(new int[0]);
		System.out.println("noEdgeOneVertexMatrix");
		System.out.println(noEdgeOneVertexMatrix);
		
		Matrix noEdgeTwoVertexMatrix=noEdgeOneVertexMatrix.addRow(new int[0]);
		System.out.println("noEdgeTwoVertexMatrix");
		System.out.println(noEdgeTwoVertexMatrix);
		
		Matrix m1 = testMatrix.removeColumn(1);
		System.out.println("m1");
		System.out.println(m1);
		
		Matrix m2 = testMatrix.removeColumn(2);
		System.out.println("m2");
		System.out.println(m2);
		
		Matrix m3 = m2.addColumn(new int[]{0, 1, 0});
		System.out.println("m3");
		System.out.println(m3);
		
		Matrix m4 = m3.removeRow(2);
		System.out.println("m4");
		System.out.println(m4);
		
		Matrix m5 = m4.addRow(new int[]{1,0,1, 0});
		System.out.println("m5");
		System.out.println(m5);
	}
}
