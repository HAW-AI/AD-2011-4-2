import java.util.Arrays;


public class Split {
	private final String at;
	private final boolean notempty;
	private final boolean trim;
	
	private Split(String at, boolean notempty, boolean trim){
		this.at=at;
		this.notempty=notempty;
		this.trim=trim;
	}
	/**
	 * Gibt ein neues String Array ohne die Leeren Datensätze aus.
	 * @param strArray String Array zum Berechnen
	 * @return String[]
	 */
	private String[] doNotEmpty(String[] strArray){
		if(!notempty) return strArray;
		String[] newStrArray=new String[strArray.length];
		int i=0;
		for(String s: strArray)
			if(!("".equals(s) || "".equals(s.trim()))) newStrArray[i++]=s;
		return Arrays.copyOf(newStrArray, i);
	}
	/**
	 * Führt die trim Operation auf jedes Element im Array aus. 
	 * @return
	 */
	private String[] doTrim(String[] strArray){
		if(!trim) return strArray;
		String[] newStrArray=new String[strArray.length];
		int i=0;
		for(String s: strArray)
			newStrArray[i++]=s.trim();
		return Arrays.copyOf(newStrArray, i);
	}
	/**
	 * Kopiert den Split Objekt und gibt ein neues Split Objekt zurück, 
	 * bei dem die Einstellung trim auf true gesetzt ist.
	 * @return Split
	 */
	public Split trim(){
		return new Split(at, notempty, true);
	}
	public Split notEmpty(){
		return new Split(at, true, trim);
	}
	/**
	 * Konstrukt
	 * @param at Das Zeichen an dem gesplitet wird.
	 * @return
	 */
	public static Split at(String at){
		return new Split(at, false, false);
	}
	/**
	 * Splitet den String und führt alle Funktionen auf den Array aus, die man eingestellt hat.
	 * @param from String zum Spliten
	 * @return
	 */
	public String[] from(String from){
		return doTrim(doNotEmpty(from.split(at)));
	}
}
