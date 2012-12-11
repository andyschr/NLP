package entities;

import java.util.List;

/**
 * Consists of the original word, the translated word and other words to identify the right context for this translations.
 * Further is has a likelyhood to translate on a statistical approach
 * @author Andreas
 *
 */
public class TranslationEntry {

	String originalWord;
	String translatedWord;
	
	List<String> otherWordsToSearchFor;
	
	private double likelyhood;

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public String getTranslatedWord() {
		return translatedWord;
	}

	public void setTranslatedWord(String translatedWord) {
		this.translatedWord = translatedWord;
	}

	public List<String> getOtherWordsToSearchFor() {
		return otherWordsToSearchFor;
	}

	public void setOtherWordsToSearchFor(List<String> otherWordsToSearchFor) {
		this.otherWordsToSearchFor = otherWordsToSearchFor;
	}

	public double getLikelyhood() {
		return likelyhood;
	}

	public void setLikelyhood(double likelyhood) {
		this.likelyhood = likelyhood;
	}

	
}
