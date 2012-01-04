package hyperGraph.implementations;

import hyperGraph.interfaces.Matrix;

/**
 * 
 * @author Tobias Meurer
 * @author Stephan Berngruber
 *
 *
 * Objekte dieser Klasse werden erzeugt, wenn unzulässige Operationen
 * (bzw. Operationen mit unzulässigen Werten) auf eine Inzidenzmatrix aufgerufen werden
 * 
 */

public class NotAMatrix implements Matrix {
	
	public static Matrix singleInstance;

	/**
	 * 
	 * @return Singolton-Objekt of NotAnMatrix
	 */
	public static Matrix create(){
		if (singleInstance == null) {
			singleInstance = new NotAMatrix();
		}
		return singleInstance;
	}
	
	private NotAMatrix(){
	}
	
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public int width() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public Matrix addColumn(int[] newColumn) {
		return singleInstance;
	}

	@Override
	public Matrix addRow(int[] newRow) {
		return singleInstance;
	}

	@Override
	public Matrix removeColumn(int column) {
		return singleInstance;
	}

	@Override
	public Matrix removeRow(int row) {
		return singleInstance;
	}
	
	@Override
	public String toString(){
		return "NotAMatrix";
	}

}
