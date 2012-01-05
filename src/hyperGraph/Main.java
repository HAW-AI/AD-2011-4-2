/**
 * 
 *
 */
package hyperGraph;

import java.util.Arrays;

import hyperGraph.interfaces.InzidenzMatrix;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 *
 */
public class Main {
	public static void main(String[] args){
		int[] testMatrixArray = {1, 1, 0, 1,
				  				 0, 1, 1, 1,
				  				 1, 1, 1, 1};
		InzidenzMatrix testMatrix = Values.matrix(3, 4, testMatrixArray);
		System.out.println("testMatrix");
		System.out.println(testMatrix);
		
		InzidenzMatrix emptyMatrix = Values.matrix(0,0, new int[0]);
		System.out.println("emptyMatrix");
		System.out.println(emptyMatrix);
		
		InzidenzMatrix noEdgeOneVertexMatrix=emptyMatrix.addRow(new int[0]);
		System.out.println("noEdgeOneVertexMatrix");
		System.out.println(noEdgeOneVertexMatrix);
		
		InzidenzMatrix noEdgeTwoVertexMatrix=noEdgeOneVertexMatrix.addRow(new int[0]);
		System.out.println("noEdgeTwoVertexMatrix");
		System.out.println(noEdgeTwoVertexMatrix);
		
		InzidenzMatrix m1 = testMatrix.removeColumn(1);
		System.out.println("m1");
		System.out.println(m1);
		
		InzidenzMatrix m2 = testMatrix.removeColumn(2);
		System.out.println("m2");
		System.out.println(m2);
		
		InzidenzMatrix m3 = m2.addColumn(new int[]{0, 1, 0});
		System.out.println("m3");
		System.out.println(m3);
		
		InzidenzMatrix m4 = m3.removeRow(2);
		System.out.println("m4");
		System.out.println(m4);
		
		InzidenzMatrix m5 = m4.addRow(new int[]{1,0,1, 0});
		System.out.println("m5");
		System.out.println(m5);
		
		InzidenzMatrix m6 = m5.addColumn(new int[]{0,0,0});
		System.out.println("m6");
		System.out.println(m6);
		
		InzidenzMatrix m7 = m5.removeRow(1);
		System.out.println("m7");
		System.out.println(m7);
		
		InzidenzMatrix m8 = m5.removeRow(0);
		System.out.println("m8");
		System.out.println(m8);
		
		System.out.println(Arrays.toString(m8.getRow(0)));
		System.out.println(Arrays.toString(m8.getRow(1)));
		
		System.out.println(Arrays.toString(m8.getColumn(0)));
		System.out.println(Arrays.toString(m8.getColumn(1)));
		System.out.println(Arrays.toString(m8.getColumn(2)));
		System.out.println(Arrays.toString(m8.getColumn(3)));
	}
}
