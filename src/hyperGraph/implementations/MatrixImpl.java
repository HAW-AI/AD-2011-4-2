package hyperGraph.implementations;

import java.util.Iterator;
import java.util.List;

import hyperGraph.interfaces.Matrix;

public class MatrixImpl implements Matrix {

	final int[] values;
	final int width, height;
	
	static public Matrix create(int width, int height, int[] values){
		if (values.length != (width*height) ) {
			// TODO Return NotAnMatrix
			return null;
		} else {
			return new MatrixImpl(width, height, values);
		}
	}
	
	private MatrixImpl(int width, int height, int[] values){
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
	public Matrix addColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix addRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix removeColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix removeRow() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		return "Verdammt, hier fehlt noch die toString-Implementierung!";
	}



}
