package hyperGraph.implementations;

import java.util.Arrays;
import java.util.Set;

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
			//Erzeugen eines neuen Arrays, mit der Gr��e der alten Matrix + eine Spalte
			newValues = new int[values.length+newColumn.length];
			for (int i = 0; i < height(); i++) {
				// Kopieren der alten Zeile i in die neue Matrix
				System.arraycopy(values, i*width(), newValues, i*(width()+1), width);
				// Wert i an Ende der Zeile i in neuer Matrix einf�gen
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
			return null;
		} else if (!checkValues(newRow)){
			// TODO Return NotAnMatrix
			return null;
		} else if (width()==0) {
			// Wenn Ecke hinzugef�gt werden soll, es jedoch noch keine Kante gibt
			newValues=newValues=new int[0];
		} else {
			// Erzeugen eines neuen Arrays, mit der L�nge des alten Value-Arrays + der neuen Row
			//  Werte von Values werden an den Anfang des neuen Arrays eingef�gt
			newValues = Arrays.copyOf(values, (values.length+newRow.length));
			// Kopieren der Werte der neuen Row an das Ende des neuen Arrays
			System.arraycopy(newRow, 0, newValues, values.length, newRow.length);
		}
		// Erzeugen und zur�ckgeben einer neuen Matrix
		return create( height()+1, width(),newValues);
	}

	@Override
	public Matrix removeColumn(int column) {
		if (column<width() || column<0) {
			// TODO Return NotAnMatrix
			return null;
		}
		int[] newValues = new int[values.length-height()];
		for (int oldIndex = 0, newIndex=0; oldIndex < values.length; oldIndex++, newIndex++) {
			if (oldIndex%width()!=column) {
				newValues[newIndex] = values[oldIndex];
				oldIndex++;
			} else {
				oldIndex--;
				newIndex--;
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix removeRow(int row) {
		int[] newValues = new int[values.length-width()];
		if (row<height() || row<0) {
			// TODO Return NotAnMatrix
			return null;
		} else if (width()==0) {
			// Wenn Ecke gel�scht werden soll, es jedoch noch keine Kante gibt
			newValues=new int[0];
		} else {
			for (int oldRow = 0, newRow = 0; oldRow < values.length; oldRow++, newRow++) {
				if (row == oldRow) {
					oldRow++;
				}
				// Aktuelle Zeile in neue Matrix kopieren
				System.arraycopy(values, oldRow*width(), newValues, newRow*width(), width());
			}
		}
		return create( height()-1, width(),newValues);
	}
	
	@Override
	public String toString(){
		
		
//		StringBuilder result = new StringBuilder("");
//		// Einmalige StringErzeugung
//		String space = " ";
//		String delimiter = "| ";
//		String nl = "\n";
//		String nan = "   X";
//		String rowDelimiter = "-------";
//		
//		result.append(nl);
//
//		// Zusammenstellen der Ausgabe
//		for (int i = 0; i < adjazenzMatrix.size(); i++) {
//			Set<Integer> n = getNeighbourIndexesOf_Directed(i);
//			StringBuilder row = new StringBuilder();
//			for (int j = 0; j < adjazenzMatrix.size(); j++) {
//				result.append(space);
//				if (n.contains(j)) {
//					result.append(String.format("%4d", adjazenzMatrix.get(i)
//							.get(j).value()));
//				} else {
//					result.append(nan);
//				}
//				result.append(delimiter);
//				row.append(rowDelimiter);
//			}
//			result.append(nl);
//			result.append(row);
//			result.append(nl);
//		}
//		return result.toString();
		
		
		
		
		
		
		
		
		
		return ("Verdammt, hier fehlt noch die toString-Implementierung!\n");
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
