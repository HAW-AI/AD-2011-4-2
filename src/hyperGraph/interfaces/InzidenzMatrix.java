package hyperGraph.interfaces;
/**
 * 
 * 
 * @author Tobias Meurer
 * @author Stephan Berngruber
 *
 */
public interface InzidenzMatrix {

	public int size();
	public int width();
	public int height();
	public InzidenzMatrix addColumn(int[] newColumn);
	public InzidenzMatrix addRow(int[] newRow);
	public InzidenzMatrix removeColumn(int column);
	public InzidenzMatrix removeRow(int row);
	public int[] getColumn(int columnId);
	public int[] getRow(int rowId);
	
	
}
