package engine;

import java.util.ArrayList;
import java.util.List;

import common.Corpus;

import entities.Text;
import entities.TranslationEntry;
import entities.Translations;

public class TranslationProcessor {

	Text textToTranslate;
	Corpus corpus;
	
	
	public String translateWord(String wordToTranslate, int sentenceIndex){
		Translations translations = corpus.getTranslations(wordToTranslate);
		
		List<Integer> points = new ArrayList<Integer>();	//List to hold the points of the possible translation
		
		if(translations == null){
			return wordToTranslate+"-NOTFOUND";
		}
		
		List<TranslationEntry> entrys = translations.getEntrys();
		if(entrys.size() == 1){
			return entrys.get(0).getTranslatedWord();
		}
		
		//calculate points
		for(TranslationEntry entry : entrys){
			points.add(calculatePoints(entry, sentenceIndex));
		}
		
		return null;
		
	}


	private Integer calculatePoints(TranslationEntry entry, int sentenceIndex) {
		Integer amountOfPoints = new Integer(0);
		
		return null;
	}
}
