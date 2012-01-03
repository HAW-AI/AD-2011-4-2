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
public class main {
	public static void main(String[] args){
		int[] testMatrixArray = {0, 1, 0, 1,
				  				 0, 1, 0, 1,
				  				 0, 1, 0, 1};
		Matrix testMatrix = Values.matrix(3, 4, testMatrixArray);
		System.out.println(testMatrix);
	}
}
