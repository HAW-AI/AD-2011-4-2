package hyperGraph.interfaces;
/**
 * 
 * 
 * @author Tobias Meurer
 *
 */
public interface Matrix {

	public int size();
	public int width();
	public int height();
	public int get(int i, int j);
	//public Iterator<Integer> iterator(int i, int j);
	public Matrix addColumn(int[] newColumn);
	public Matrix addRow(int[] newRow);
	public Matrix removeColumn(int column);
	public Matrix removeRow(int row);
	
	
}
