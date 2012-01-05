package funktionaleKomposition;
public interface Split {
	/**
	 * Kopiert den Split Objekt und gibt ein neues Split Objekt zurück, 
	 * bei dem die Einstellung trim auf true gesetzt ist.
	 * @return Split
	 */
	public Split trim();
	/**
	 * Kopiert den Split Objekt und gibt ein neues Split Objekt zurück, 
	 * bei dem die Einstellung noempty auf true gesetzt ist.
	 * @return Split
	 */	
	public Split notEmpty();

	/**
	 * Splitet den String und führt alle Funktionen auf den Array aus, die man eingestellt hat.
	 * @param from String zum Spliten
	 * @return
	 */
	public String[] from(String from);

}
