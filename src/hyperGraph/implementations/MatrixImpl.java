package hyperGraph.implementations;

import java.util.Arrays;

import hyperGraph.Values;
import hyperGraph.interfaces.Matrix;

public class MatrixImpl implements Matrix {

	final int[] values;
	final int width, height;
	
	public static Matrix create(int height, int width, int[] values){
		if (values.length != (width*height) ) {
			return Values.notAMatrix();
		} else if (!checkValues(values)){
			return Values.notAMatrix();
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

	private int get(int i, int j) {
	     if (i < 0 || i >= this.height() || j < 0 || j >= this.width()) {
	            throw new IndexOutOfBoundsException();
	     }
	        return values[(j + (i * width()))];
	}


	@Override
	public Matrix addColumn(int[] newColumn) {
		int[] newValues;
		if (newColumn.length != height()){
			return Values.notAMatrix();
		} else if (!checkValues(newColumn)){
			return Values.notAMatrix();
		} else {
			//Erzeugen eines neuen Arrays, mit der Größe der alten Matrix + eine Spalte
			newValues = new int[values.length+newColumn.length];
			int widthOfNewValues = width()+1;
			for (int i = 0; i < height(); i++) {
				// Kopieren der alten Zeile i in die neue Matrix
				System.arraycopy(values, i*width(), newValues, i*(width()+1), width());
				// Wert i an Ende der Zeile i in neuer Matrix einfügen
				newValues[width()+widthOfNewValues*i]=newColumn[i];
			}
		}
		return create(height(),width()+1, newValues);
	}

	@Override
	public Matrix addRow(int[] newRow) {
		int[] newValues;
		if (newRow.length != width()){
			return Values.notAMatrix();
		} else if (!checkValues(newRow)){
			return Values.notAMatrix();
		} else if (width()==0) {
			// Wenn Ecke hinzugefügt werden soll, es jedoch noch keine Kante gibt
			newValues=new int[0];
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
		if (column>=width() || column<0) {
			throw new IndexOutOfBoundsException();
		}
		
		int[] newValues = new int[values.length-height()];
		int currentRow =0;
		for (int oldIndex = 0, newIndex=0; oldIndex < values.length; oldIndex++, newIndex++) {
			if (oldIndex!=column+(currentRow*width())) {
				newValues[newIndex] = values[oldIndex];
			} else {
				newIndex--;
				currentRow++;
			}
		}
		return create( height(), width()-1,newValues);
	}

	@Override
	public Matrix removeRow(int row) {
		int[] newValues = new int[values.length-width()];
		if (row>=height() || row<0) {
			throw new IndexOutOfBoundsException();
		} else if (width()==0) {
			// Wenn Ecke gelöscht werden soll, es jedoch noch keine Kante gibt
			newValues=new int[0];
		} else {
			for (int oldRow = 0, newRow = 0; oldRow < height(); oldRow++, newRow++) {
				if (row != oldRow) {
					// Aktuelle Zeile in neue Matrix kopieren
					System.arraycopy(values, oldRow*width(), newValues, newRow*width(), width());
				} else {
					newRow--;
				}
			}
		}
		return create( height()-1, width(),newValues);
	}
	
	@Override
	public String toString(){
		
		
		StringBuilder result = new StringBuilder("");
		// Einmalige StringErzeugung
//		final String space = " ";
		final String delimiter = "| ";
		final String nl = "\n";
//		final String connected = " X ";
//		final String notConnected = " - ";
		final String rowDelimiter = "------";
		
		// Zusammenstellen des AusgabeStrings
		//   Falls keine Spalten vorhanden
		if (width()==0 & height()>0){
			result.append("XXXX|").append(delimiter).append(nl).append(rowDelimiter).append(nl);
			for (int i = 0; i < height(); i++) {
				result.append(String.format("v%-3d|", i)).append(delimiter).append(nl);
			}
			
		// Zusammenstellen des AusgabeStrings
		//   Falls keine Spalten vorhanden
		} else if (height()==0) {
			result.append("Empty");
			
		// Zusammenstellen des AusgabeStrings
		//   Normalfall
		} else {
			result.append("XXXX|").append(delimiter);
			StringBuilder breite =  new StringBuilder(rowDelimiter);
			for (int n = 0; n < width(); n++) {
				result.append(String.format("e%-3d", n)).append(delimiter);
				breite.append(rowDelimiter);
			}
			result.append(nl).append(breite).append(nl);
			for (int m = 0; m < height(); m++) {
				result.append(String.format("v%-3d|", m)).append(delimiter);
				for (int n = 0; n < width(); n++) {
					result.append(String.format("%4d", get(m, n))).append(delimiter);
				}
				result.append(nl);
			}
		}
		return result.toString();
	}
	
	private static boolean checkValues(int[] values){
		for (int i = 0; i < values.length; i++) {
			int check = values[i];
			if (check != 0 && check != 1) {
				return false;
			}
		}
		return true;
	}



}
