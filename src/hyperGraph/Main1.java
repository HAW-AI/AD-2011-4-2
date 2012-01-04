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
public class Main1 {
	public static void main(String[] args){
		int[] testMatrixArray = {0, 1, 0, 1,
				  				 0, 1, 0, 1,
				  				 0, 1, 0, 1};
		Matrix testMatrix = Values.matrix(3, 4, testMatrixArray);
		System.out.println(testMatrix);
		
		Matrix emptyMatrix = Values.matrix(0,0, new int[0]);
		System.out.println(emptyMatrix);
		
		Matrix noEdgeOneVertexMatrix=emptyMatrix.addRow(new int[0]);
		System.out.println(noEdgeOneVertexMatrix);
		
		Matrix noEdgeTwoVertexMatrix=noEdgeOneVertexMatrix.addRow(new int[0]);
		System.out.println(noEdgeTwoVertexMatrix);
		
		Matrix m1 = testMatrix.removeColumn(1);
		System.out.println(m1);
	}
}
