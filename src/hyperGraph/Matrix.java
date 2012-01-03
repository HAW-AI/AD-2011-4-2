package hyperGraph;

import java.util.Iterator;


/**
 * 
 * 
 * @author Tobias Meurer
 *
 */
public interface Matrix {

	public int width();
	public int height();
	public int get(int i, int j);
	public Iterator<Integer> iterator(int i, int j);
	public Matrix addColumn();
	public Matrix addRow();
	public Matrix removeColumn();
	public Matrix removeRow();
	
	
}
