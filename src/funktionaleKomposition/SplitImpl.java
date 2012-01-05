package funktionaleKomposition;
import java.util.Arrays;


public class SplitImpl implements Split{
	private final String at;
	private final boolean notempty;
	private final boolean trim;
	
	private SplitImpl(String at, boolean notempty, boolean trim){
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
	 * @return String[]
	 */
	private String[] doTrim(String[] strArray){
		if(!trim) return strArray;
		String[] newStrArray=new String[strArray.length];
		int i=0;
		for(String s: strArray)
			newStrArray[i++]=s.trim();
		return Arrays.copyOf(newStrArray, i);
	}
	
	@Override
	public Split trim(){
		return new SplitImpl(at, notempty, true);
	}
	
	@Override
	public Split notEmpty(){
		return new SplitImpl(at, true, trim);
	}
	/**
	 * Creator
	 * @param at Das Zeichen an dem gesplitet wird.
	 * @return
	 */
	public static Split at(String at){
		return new SplitImpl(at, false, false);
	}
	@Override
	public String[] from(String from){
		return doTrim(doNotEmpty(from.split(at)));
	}
}
