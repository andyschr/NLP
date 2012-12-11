package engine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sun.java.swing.plaf.nimbus.LoweredBorder;

import common.Corpus;

import entities.Sentence;
import entities.Text;
import entities.TranslationEntry;
import entities.Translations;

/**
 * This class does the translation of each word
 * @author Andreas
 *
 */
public class TranslationProcessor {

	Text textToTranslate;
	Corpus corpus;
	Properties config;
	
	public final Text getTextToTranslate() {
		return textToTranslate;
	}

	public final void setTextToTranslate(Text textToTranslate) {
		this.textToTranslate = textToTranslate;
	}

	public final Corpus getCorpus() {
		return corpus;
	}

	public final void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}


	
	public TranslationProcessor() throws IOException{
		init();
	}
	
	/**
	 * Initialises the TranslationProcessor. Reads values from the properties file for example
	 * @throws IOException
	 */
	private void init() throws IOException{
		config = new Properties();
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream("src/engine/engineProperties.properties"));
		config.load(stream);
		stream.close();
	}
	
	/**
	 * Translates the whole text
	 * @return Translated text as String
	 */
	public String translateText(){
		String translatedText = "";
		int index = 0;
		Sentence sentence;
		String word;
		while((sentence = textToTranslate.getNextSentence()) != null){
			while((word = sentence.getNextWord()) != null){
				translatedText+=translateWord(word, index);
				translatedText+=" ";
			}
			index++;
		}
		
		return translatedText;
			
		
	}
	
	
	/**
	 * Translated the given word
	 * @param wordToTranslate
	 * @param sentenceIndex the index of the sentence in which the word occurs
	 * @return the translation with the most points. Otherwise wordToTranslate+"-NOTFOUND" if no translation was found.
	 */
	public String translateWord(String wordToTranslate, int sentenceIndex){
		//get all possible translations for specific word
		Translations translations = corpus.getTranslations(wordToTranslate);
		
		//List to hold the points of the possible translation
		List<Integer> points = new ArrayList<Integer>();	
		
		//if no translations for specific words were found
		if(translations == null){
			return wordToTranslate+"-NOTFOUND";
		}
		
		//get the entrys of the possible translations
		List<TranslationEntry> entrys = translations.getEntrys();
		
		//if only one possible translation is found then return it
		if(entrys.size() == 1){
			return entrys.get(0).getTranslatedWord();
		}
		
		//otherwise calculate points
		for(TranslationEntry entry : entrys){
			points.add(calculatePoints(entry, sentenceIndex));
		}
		
		//select translation with most points
		Integer biggestValue = 0;
		Integer indexOfBiggestValue = null;
		for(int i  = 0; i < points.size(); i++){
			if((points.get(i)) > biggestValue){
				indexOfBiggestValue = i;
				biggestValue = points.get(i);
			}
		}
		
		//return value
		if(biggestValue == 0){
			//take translation with biggest likelyhood
			return getWordWithBiggestLikelyhood(entrys);
			
		}
		else return entrys.get(indexOfBiggestValue).getTranslatedWord();
		
	}


	/**
	 * 
	 * @param entrys
	 * @return The translation with the biggest likelyhood based on statistical usage of the word
	 */
	private String getWordWithBiggestLikelyhood(List<TranslationEntry> entrys) {
		Integer indexOfBiggestLikelyhood = 0;
		double biggestLikelyhood = 0.0;
		int actualIndex = 0;
		for(TranslationEntry entry : entrys){
			if(entry.getLikelyhood() > biggestLikelyhood){
				biggestLikelyhood = entry.getLikelyhood();
				indexOfBiggestLikelyhood = actualIndex;
			}
			actualIndex++;
		}
		return entrys.get(indexOfBiggestLikelyhood).getTranslatedWord();
		
	}

	/**
	 * Calculates the points for the given entry
	 * @param entry possible translation
	 * @param sentenceIndex index of the sentence in which the word occurs
	 * @return amount of points for the specific translation
	 */
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
		
		//actual sentence that holds the word
		String actualSentence = textToTranslate.get(sentenceIndex).getWholeSentenceAsString();
		
		//calculate points
		
		for (String otherWord : otherWords) {
			// occurance in same sentence
			if (actualSentence.contains(otherWord)) {
				amountOfPoints += pointsForSame;
			}
			
			// occurence in sentence nearby
			for (int i = lowerBorder; i < upperBorder; i++) {
				if (i == sentenceIndex) {
					continue; // do not check in same sentence twice
				}
				if(textToTranslate.get(i).getWholeSentenceAsString().contains(otherWord)){
					amountOfPoints+= pointsForNear;
				}

			}
			
			//occurence in whole text
			if(textToTranslate.occursOnText(otherWord)){
				amountOfPoints+= pointsFortext;
			}
		}
		
		return amountOfPoints;
	}
}
