package engine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.Corpus;

import entities.Text;
import entities.TranslationEntry;
import entities.Translations;

public class TranslationProcessor {

	Text textToTranslate;
	Corpus corpus;
	Properties config;
	
	public TranslationProcessor() throws IOException{
		init();
	}
	
	private void init() throws IOException{
		config = new Properties();
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream("engineProperties.properties"));
		config.load(stream);
		stream.close();
	}
	
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
		//load properties
		Integer amountOfPoints = new Integer(0);
		List<String> otherWords = entry.getOtherWordsToSearchFor();
		Integer pointsForSame = Integer.valueOf(config.getProperty("Pointsforsamesentence"));
		Integer pointsForNear = Integer.valueOf(config.getProperty("Pointsfornearsentence"));
		Integer pointsFortext = Integer.valueOf(config.getProperty("Pointsforoccursintext"));
		Integer near = Integer.valueOf(config.getProperty("nearby"));
	
		//calculate borders for nearby
		int upperBorder = sentenceIndex+near;
		int lowerBorder = sentenceIndex-near;
		lowerBorder = (lowerBorder < 0) ? 0 : lowerBorder;
		upperBorder = (upperBorder > textToTranslate.getNumberOfSentence()) ? textToTranslate.getNumberOfSentence() : upperBorder;
		
		for(String otherWord :  otherWords){
			//occurance in same sentence
			if(textToTranslate.get(sentenceIndex).getWholeSentenceAsString().contains(otherWord)){
				amountOfPoints+=pointsForSame;
			}
			
			//occurence in sentence nearby
			
		}
	
		return null;
	}
}
