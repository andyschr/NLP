package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.Corpus;
import engine.TranslationProcessor;
import entities.Text;
import entities.TranslationEntry;
import entities.Translations;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Corpus dict = new Corpus();
		initCorpus(dict);
		Text textToTranslate = new Text();
		
		try {
			textToTranslate.readFromFile("inputText.txt");
			TranslationProcessor processor = new TranslationProcessor();
			processor.setCorpus(dict);
			processor.setTextToTranslate(textToTranslate);
			System.out.println(processor.translateText());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		

	}
	
	public static void initCorpus(Corpus corpusToFill){
	
		Translations pipe = new Translations();
		
		TranslationEntry entry = new TranslationEntry();
		entry.setOriginalWord("pipe");
		entry.setTranslatedWord("Rohrleitung");
		List<String> otherWords = new ArrayList<String>();
		otherWords.add("oil");
		otherWords.add("fuel");
		entry.setOtherWordsToSearchFor(otherWords);
		pipe.addEntry(entry);
		
		entry = new TranslationEntry();
		entry.setOriginalWord("pipe");
		entry.setTranslatedWord("Pfeife");
		otherWords = new ArrayList<String>();
		otherWords.add("tobacco");
		otherWords.add("smoking");
		entry.setOtherWordsToSearchFor(otherWords);
		pipe.addEntry(entry);
		
		corpusToFill.addEntry("pipe", pipe);
		
		
		
	}

}
