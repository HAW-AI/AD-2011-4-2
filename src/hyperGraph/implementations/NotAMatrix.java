package hyperGraph.implementations;

import hyperGraph.interfaces.InzidenzMatrix;

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

public class NotAMatrix implements InzidenzMatrix {
	
	public static InzidenzMatrix singleInstance;

	/**
	 * 
	 * @return Singolton-Objekt of NotAnMatrix
	 */
	public static InzidenzMatrix create(){
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
	public InzidenzMatrix addColumn(int[] newColumn) {
		return singleInstance;
	}

	@Override
	public InzidenzMatrix addRow(int[] newRow) {
		return singleInstance;
	}

	@Override
	public InzidenzMatrix removeColumn(int column) {
		return singleInstance;
	}

	@Override
	public InzidenzMatrix removeRow(int row) {
		return singleInstance;
	}
	
	@Override
	public String toString(){
		return "NotAMatrix";
	}

	@Override
	public int[] getColumn(int columnId) {
		return new int[0];
	}

	@Override
	public int[] getRow(int rowId) {
		return new int[0];
	}

}
