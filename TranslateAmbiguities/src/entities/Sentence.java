package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Consists of the sentence as String and also holds a list of words
 * @author Andreas
 *
 */
public class Sentence {

	String wholeSentenceAsString = "";
	List<String> words = new ArrayList<String>();
	int wordCounter=0;
	
	public void tokenizeSentence(){
		StringTokenizer tokenizer = new StringTokenizer(wholeSentenceAsString);
		while(tokenizer.hasMoreTokens()){
			words.add(tokenizer.nextToken());
		}
	}
	
	/**
	 * This function returns the next word in the sentence
	 * @return word if there is another that exists. Null if no words left.
	 */
	public String getNextWord(){
		if(hasMoreWords()){
			String ret = words.get(wordCounter);
			wordCounter+=1;
			return ret;
		}
		else return null;
	}
	
	public String getWholeSentenceAsString() {
		return wholeSentenceAsString;
	}

	public void setWholeSentenceAsString(String wholeSentenceAsString) {
		this.wholeSentenceAsString = wholeSentenceAsString;
	}

	public boolean hasMoreWords(){
		if((words.size()) > wordCounter){
			return true;
		}
		else return false;
	}
}
