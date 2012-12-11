package entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Consists of all translation entrys for a specific word
 * @author Andreas
 *
 */
public class Translations {

	List<TranslationEntry> entrys;
	
	public List<TranslationEntry> getEntrys(){
		return entrys;
	}
	
	public void addEntry(TranslationEntry entry){
		
		if(entrys == null){
			entrys = new ArrayList<TranslationEntry>();
		}
		entrys.add(entry);
	}
}
