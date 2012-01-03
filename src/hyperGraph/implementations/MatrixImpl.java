package hyperGraph.implementations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import hyperGraph.interfaces.Matrix;

public class MatrixImpl implements Matrix {

	final int[] values;
	final int width, height;
	
	public static Matrix create(int height, int width, int[] values){
		if (values.length != (width*height) ) {
			// TODO Return NotAnMatrix
			return null;
		} else if (!checkValues(values)){
			// TODO Return NotAnMatrix
			return null;
		} else {
			return new MatrixImpl(height, width, values);
		}
	}
	
	private MatrixImpl(int height, int width, int[] values){
		this.width = width;
		this.height = height;
		this.values = values;
	}
	
	@Override
	public int size() {
		return values.length;
	}
	
	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public int get(int i, int j) {
	     if (i < 0 || i >= this.size() || j < 0 || j >= this.size()) {
	            throw new ArrayIndexOutOfBoundsException();
	     }
	        return values[(i + (j * size()))];
	}

//	@Override
//	public Iterator<Integer> iterator(int i, int j) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Matrix addColumn(int[] newColumn) {
		int[] newValues;
		if (newColumn.length != height()){
			// TODO Return NotAnMatrix
			newValues = null;
		} else if (!checkValues(newColumn)){
			// TODO Return NotAnMatrix
			return null;
		} else {
			//Erzeugen eines neuen Arrays, mit der Größe der alten Matrix + eine Spalte
			newValues = new int[values.length+newColumn.length];
			for (int i = 0; i < height(); i++) {
				// Kopieren der alten Zeile i in die neue Matrix
				System.arraycopy(values, i*width(), newValues, i*(width()+1), width);
				// Wert i an Ende der Zeile i in neuer Matrix einfügen
				newValues[width]=newColumn[i];
			}
		}
		return create(height(),width()+1, newValues);
	}

	@Override
	public Matrix addRow(int[] newRow) {
		int[] newValues;
		if (newRow.length != width()){
			// TODO Return NotAnMatrix
			newValues = null;
		} else if (!checkValues(newRow)){
			// TODO Return NotAnMatrix
			return null;
		} else {
			// Erzeugen eines neuen Arrays, mit der Länge des alten Value-Arrays + der neuen Row
			//  Werte von Values werden an den Anfang des neuen Arrays eingefügt
			newValues = Arrays.copyOf(values, (values.length+newRow.length));
			// Kopieren der Werte der neuen Row an das Ende des neuen Arrays
			System.arraycopy(newRow, 0, newValues, values.length, newRow.length);
		}
		// Erzeugen und zurückgeben einer neuen Matrix
		return create( height()+1, width(),newValues);
	}

	@Override
	public Matrix removeColumn(int column) {
		int[] newValues;
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix removeRow(int row) {
		int[] newValues = new int[values.length-width()];
		for (int oldRow = 0, newRow = 0; oldRow < values.length; oldRow++, newRow++) {
			if (row == oldRow) {
				oldRow++;
			}
			// Aktuelle Zeile in neue Matrix kopieren
			System.arraycopy(values, oldRow*width(), newValues, newRow*width(), width());
		}
		return create( height()-1, width(),newValues);
	}
	
	@Override
	public String toString(){
		return "Verdammt, hier fehlt noch die toString-Implementierung!";
	}
	
	private static boolean checkValues(int[] values){
		for (int i = 0; i < values.length; i++) {
			int check = values[i];
			if (check != 0 || check != 1) {
				return false;
			}
		}
		return true;
	}



}
