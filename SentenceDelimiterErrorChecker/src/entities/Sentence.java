package entities;

public class Sentence {

	String sentence;
	String delimiter;
	String firstWord;
	String firstWordAfterComma;
	boolean wrongDelimiter;
	
	/**
	 * Determines all the attributes as delimiter, firstWord and firstWordAfterComma
	 * If no regular delimiter was found the wrongDelimiter boolean is set to true.
	 */
	public void determineAttributes(){
		
		if(sentence == null){
			throw new IllegalStateException("Sentence hast not been initialised");
		}
		sentence = sentence.trim();
		String temp;
		if((temp = sentence.substring(sentence.length())).matches("!|?|.")){
			wrongDelimiter = true;
		}
		
	}
}
