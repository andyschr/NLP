package entities;

import java.util.StringTokenizer;

/**
 * This class stores a single sentence. It also checks all the parameters that are important
 * for the errorchecker like delimiter, first word and first word after last comma.
 * @author Andreas
 *
 */
public class Sentence {

	public final String getSentence() {
		return sentence;
	}

	public final String getDelimiter() {
		return delimiter;
	}

	public final String getFirstWord() {
		return firstWord;
	}

	public final String getFirstWordAfterComma() {
		return firstWordAfterComma;
	}

	public final boolean isInvalidDelimiter() {
		return invalidDelimiter;
	}

	String sentence;
	String delimiter;
	String firstWord;
	String firstWordAfterComma;
	boolean invalidDelimiter;
	
	public Sentence(String line) {
		this.sentence = line;
		determineAttributes();
	}

	/**
	 * Determines all the attributes as delimiter, firstWord and firstWordAfterComma
	 * If no regular delimiter was found the wrongDelimiter boolean is set to true.
	 */
	public void determineAttributes(){
		
		if(sentence == null){
			throw new IllegalStateException("Sentence hast not been initialised");
		}
		sentence = sentence.trim();
		String temp = sentence.substring(sentence.length()-1);
		if(!(temp.matches("[!\\?\\.]"))){
			invalidDelimiter = true;
			System.out.println("Delimiter is invalid");
		}
		this.delimiter = temp;
		
		//first word
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		this.firstWord = tokenizer.nextToken();
		
		//first word after last comma
		int commaOccurrence;
		if((commaOccurrence = sentence.lastIndexOf(',')) != -1){
			String cuttedSentence = sentence.substring(commaOccurrence+1);
			tokenizer = new StringTokenizer(cuttedSentence);
			this.firstWordAfterComma = tokenizer.nextToken();
		}
		
	}
}
