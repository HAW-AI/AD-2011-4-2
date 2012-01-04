package hyperGraph.implementations;

import java.util.Arrays;
import hyperGraph.Values;
import hyperGraph.interfaces.Matrix;
/**
* 
* @author Tobias Meurer
* @author Stephan Berngruber
*
* Implementation der Inzidenzmatrix
* 
*/

public class MatrixImpl implements Matrix {

	final int[] values;			//Werte der Matrix sind in diesem 1-Dim Array gespeichert
	final int width, height;	//Breite und H�he der Matrix
	
	
	
	/** Creater der Matrix, wird auch durch Factory-Klasse Values aufgerufen
	 * 
	 * @param height H�he der Matrix (int)
	 * @param width  Breite der Matrix (int)
	 * @param values Werte der Matix (int[])
	 * @return Neue Instanz einer InzidenzMatrix
	 */
	public static Matrix create(int height, int width, int[] values){
		if (values.length != (width*height) ) {
			//stimmt die Anzahl der Elemente nicht mit Breite mal H�he �berein?
			return Values.notAMatrix();
		} else if (!checkValues(values)){
			//enth�lt values Werte != 0 oder 1
			return Values.notAMatrix();
		} else if (hasInvalidColumns(height, width, values)){
			//Gibt es Kanten, die keine Verbindung zu einer Ecke haben?
			return Values.notAMatrix();
		} else {
			//Erzeuge g�ltige Matrix
			return new MatrixImpl(height, width, values);
		}
	}
	
	/** Konstruktor der Matrix. Ist private, zum Erzeugen neuer Objekte create(...) verwenden!
	 * 
	 * @param height H�he der Matrix (int)
	 * @param width  Breite der Matrix (int)
	 * @param values Werte der Matix (int[])
	 * @return Neue Instanz einer MatrixImpl
	 */
	private MatrixImpl(int height, int width, int[] values){
		this.width = width;
		this.height = height;
		this.values = values;
	}
	
	

	/**Gibt die Gr��e der Matrix zur�ck
	 * 
	 * @return size / number of ellements
	 */
	@Override
	public int size() {
		return values.length;
	}
	
	/**Gibt die Breite der Matrix zur�ck
	 * 
	 * @return width (n, number of columns)
	 */
	@Override
	public int width() {
		return width;
	}

	/**Gibt die H�he der Matrix zur�ck
	 * 
	 * @return width (m, number of rows)
	 */
	@Override
	public int height() {
		return height;
	}

	/**Gibt ein Element aus der Matrix zur�ck
	 * 
	 * @param  i, number of the row
	 * @param  j, number of the column
	 * @return value (0 oder 1)
	 */
	private int get(int i, int j) {
	     if (i < 0 || i >= this.height() || j < 0 || j >= this.width()) {
	            throw new IndexOutOfBoundsException();
	     }
	        return values[(j + (i * width()))];
	}


	/**F�gt der Matrix eine Spalte hinzu
	 * 
	 * @param  int[] mit Werten f�r die Spalte
	 * @return new InzidenzMatrix
	 */
	@Override
	public Matrix addColumn(int[] newColumn) {
		int[] newValues;
		if (newColumn.length != height()){
			// Enth�lt die Einzuf�gende Ecke eine falsche Anzahl an Werten?
			return Values.notAMatrix();
		} else if (!checkValues(newColumn)){
			// enth�lt die neue Spalte Werte != 0 oder 1?
			return Values.notAMatrix();
		} else {
			//Erzeugen eines neuen Arrays, mit der Gr��e der alten Matrix + die Gr��e einer Spalte
			newValues = new int[values.length+newColumn.length];	//Array f�r neue Werte
			int widthOfNewValues = width()+1; //Breite der neuen Matrix
			for (int i = 0; i < height(); i++) {
				// Kopieren der alten Zeile i in die neue Matrix
				System.arraycopy(values, i*width(), newValues, i*(width()+1), width());
				// Wert i an Ende der Zeile i in neuer Matrix einf�gen
				newValues[width()+widthOfNewValues*i]=newColumn[i];
			}
		}
		//Erzeugen und zur�ckgeben einer neuen Matrix
		return create(height(),width()+1, newValues);
	}

	/**F�gt der Matrix eine Zeile hinzu
	 * 
	 * @param  int[] mit Werten f�r die Zeile
	 * @return new InzidenzMatrix
	 */
	@Override
	public Matrix addRow(int[] newRow) {
		int[] newValues;
		if (newRow.length != width()){
			// Enth�lt die Einzuf�gende Ecke eine falsche Anzahl an Werten?
			return Values.notAMatrix();
		} else if (!checkValues(newRow)){
			// enth�lt die neue Spalte Werte != 0 oder 1?
			return Values.notAMatrix();
		} else if (width()==0) {
			// Wenn Ecke hinzugef�gt werden soll, es jedoch noch keine Kante gibt
			newValues=new int[0];
		} else {
			// Erzeugen eines neuen Arrays, mit der L�nge des alten Value-Arrays + der neuen Row
			//  Werte von Values werden an den Anfang des neuen Arrays eingef�gt
			newValues = Arrays.copyOf(values, (values.length+newRow.length));
			// Kopieren der Werte der neuen Row an das Ende des neuen Arrays
			System.arraycopy(newRow, 0, newValues, values.length, newRow.length);
		}	
		// Erzeugen und zur�ckgeben einer neuen Matrix
		return create(height()+1, width(),newValues);
	}

	/**L�scht eine Spalte aus der Matrix
	 * 
	 * @param  Nummer der zu l�schenden Spalte
	 * @return new InzidenzMatrix
	 */
	@Override
	public Matrix removeColumn(int column) {
		if (column>=width() || column<0) {
			//Wenn ung�ltige Spalten nummer �bergeben wurde
			throw new IndexOutOfBoundsException();
		}
		
		int[] newValues = new int[values.length-height()];  //Array f�r neue Werte, mit entsprechender L�nge
		int currentRow =0;	//Zeile, die momentan abgeabeitet wird
		for (int oldIndex = 0, newIndex=0; oldIndex < values.length; oldIndex++, newIndex++) {
			if (oldIndex!=column+(currentRow*width())) {
				//Wenn man sich nicht in der zu l�schenden Spalte befindet, werden die Werte in das neue
				// Array kopiert.
				newValues[newIndex] = values[oldIndex];
			} else {
				//Wenn man sich in der zu l�schenden Spalte befindet, werden die Werte nicht kopiert
				//Nachfolgende counter werden angepasst:
				newIndex--;
				currentRow++;
			}
		}
		// Erzeugen und zur�ckgeben einer neuen Matrix
		return create( height(), width()-1,newValues);
	}

	/**L�scht eine Zeile aus der Matrix
	 * 
	 * @param  Nummer der zu l�schenden Zeile
	 * @return new InzidenzMatrix
	 */
	@Override
	public Matrix removeRow(int row) {
		int[] newValues = new int[values.length-width()]; //Array f�r neue Werte, mit entsprechender L�nge
		if (row>=height() || row<0) {
			//Wenn ung�ltige Spalten nummer �bergeben wurde
			throw new IndexOutOfBoundsException();
		} else if (width()==0) {
			// Wenn Ecke gel�scht werden soll, es jedoch noch keine Kante gibt
			newValues=new int[0];
		} else {
			for (int oldRow = 0, newRow = 0; oldRow < height(); oldRow++, newRow++) {
				if (row != oldRow) {
					// Wenn man sich nicht in der zu l�schenden Zeile befinden, wird diese in neues Array kopieren
					System.arraycopy(values, oldRow*width(), newValues, newRow*width(), width());
				} else {
					// Ansonsten muss der newRow Counter angepasst werden
					newRow--;
				}
			}
		}
		// Erzeugen und zur�ckgeben einer neuen Matrix
		return create( height()-1, width(),newValues);
	}
	
	/**String-R�ckgabe der Matrix
	 * 
	 * @return String, formatiert als Matrix
	 */
	@Override
	public String toString(){
		
		//StringBuilder wird benutzt, da dieser Mutable ist. Mit String w�rden extrem viele Objekte
		//  erzeugt und wieder gel�scht, sodass sich dies stark auf die Performance auswirken kann.
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
			//Spalten�berschrift + Zeilentrenner:
			result.append("XXXX|").append(delimiter).append(nl).append(rowDelimiter).append(nl);
			for (int i = 0; i < height(); i++) {
				//Ausgabe der einzelnen Zeilen/Ecken
				result.append(String.format("v%-3d|", i)).append(delimiter).append(nl);
			}
			
		// Zusammenstellen des AusgabeStrings
		//   Falls keine Spalten vorhanden
		} else if (height()==0) {
			result.append("Empty");
			
		// Zusammenstellen des AusgabeStrings
		//   Normalfall
		} else {
			//Spalten�berschrift + Zeilentrenner:
				result.append("XXXX|").append(delimiter);
				StringBuilder breite =  new StringBuilder(rowDelimiter);
				for (int n = 0; n < width(); n++) {
					result.append(String.format("e%-3d", n)).append(delimiter);
					breite.append(rowDelimiter);
				}
				result.append(nl).append(breite).append(nl);
				
			//Ausgabe der einzelnen Spalten
			for (int m = 0; m < height(); m++) {
				result.append(String.format("v%-3d|", m)).append(delimiter);
				for (int n = 0; n < width(); n++) {
					result.append(String.format("%4d", get(m, n))).append(delimiter);
				}
				result.append(nl);
			}
		}
		
		//StringBuiler in Strig umwandeln und zur�ckgeben
		return result.toString();
	}
	
	
	/**Pr�ft, ob int-Array nur 1 und 0 enth�lt
	 * Unsere Matrix speichert nur 1 (Ecke und Kante sind verbunden) und
	 *                             0 (Ecke und Kante sind nicht verbunden)
	 * 
	 * @param Array mit zu pr�fenden Werten
	 * @return true, wenn Werte g�ltig; false, wenn ung�ltige Werte vorhanden
	 */
	private static boolean checkValues(int[] values){
		//�ber Array iterrieren und false returnen, wenn einer der Werte != 0 und != 1
		for (int i = 0; i < values.length; i++) {
			int check = values[i];
			if (check != 0 && check != 1) {
				return false;
			}
		}
		return true;
	}

	/**Pr�ft, ob int-Array, bei angagebner Breite und H�he der zu erstellenden Matrix, 
	 *  Spalten enthalten sind, die nur mit Nullen gef�llt sind. Diese Spalten entsprechen
	 *  Kanetn, die mit KEINER Ecke verbunden sind. F�r Graphen sind "freie" Kanten ung�ltig.
	 * 
	 * @param h H�he
	 * @param w Breite
	 * @param vals int-Array mit Werten
	 * @return true, wenn Ung�ltige Spalten vorhanden, false, wenn nicht
	 */
	private static boolean hasInvalidColumns(int h, int w, int[] vals){
		
		// �ber Spalten iterrieren
		for (int spalte = 0; spalte < w; spalte++) {
			int isNotConnected=0;
			
			//�ber Zeilen iterrieren
			for (int zeile = 0; zeile < h; zeile++) {
				if (vals[spalte+zeile*w]==1) {
					isNotConnected++;
				}
			}
			// Wenn hier isNotConnected noch immer 0 ist, stand keine 1 in der Spalte,
			//  somit ist diese ung�ltig
			if (isNotConnected==0) {
				return true;
			}
		}
		return false;
	}


}
