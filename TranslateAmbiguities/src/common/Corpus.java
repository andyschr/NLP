package common;

import java.util.HashMap;
import java.util.Map;

import entities.Translations;

/**
 * This class defines the corpus or the dictionary in that case
 * Every kexword can have more than one translation.
 * @author Andreas
 *
 */
public class Corpus {

	static private Map<String, Translations> corpusEntrys;
	
	public Corpus(){
		if(corpusEntrys == null){
			corpusEntrys = new HashMap<String, Translations>();
		}
	}
	
	/**
	 * this function adds an entry to the corpus
	 * @param key
	 * @param translations
	 * @return true if a previous entry was overwritten. False if it was a new entry.
	 */
	public boolean addEntry(String key, Translations translations){
		if(corpusEntrys == null){
			throw new IllegalStateException("Corpus is not initialised");
		}
		if(corpusEntrys.containsKey(key)){
			corpusEntrys.put(key, translations);
			return true;
		}
		else {
			corpusEntrys.put(key, translations);
			return false;
		}
		
	}
	
	public boolean readFromInputFile(String filename){
		//TODO
		return false;
	}
	
	public Translations getTranslations(String wordToTranslate){
	
		//TODO
		return null;
	}
}
