package entities;

import java.util.List;

public class Sentence {

	String wholeSentenceAsString;
	List<String> words;
	int wordCounter;
	
	public void tokenizeSentence(){
		//TODO
	}
	
	/**
	 * This function returns the next word in the sentence
	 * @return word if there is another that exists. Null if no words left.
	 */
	public String getNextWord(){
		if(hasMoreWords()){
			wordCounter+=1;
			return words.get(wordCounter-1);
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
		if((words.size()-1) > wordCounter){
			return true;
		}
		else return false;
	}
}
