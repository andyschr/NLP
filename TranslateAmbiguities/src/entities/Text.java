package entities;

import java.util.List;

public class Text {

	String wholeText;
	List<Sentence> sentences;
	int sentenceCounter;
	
	public void tokenizeText(){
		//TODO
	}
	
	public Sentence getNextSentence(){
		sentenceCounter+=1;
		return sentences.get(sentenceCounter-1);
	}
	
	public boolean hasMoreSentences(){
		if((sentences.size()-1) > sentenceCounter){
			return true;
		}
		else return false;
	}
	
	public Sentence get(int nr){
		if(sentences != null && nr >= 0 && nr < sentences.size()){
			return sentences.get(nr);
		}
		else return null;
	}
	
	/**
	 * Checks weather a word occurs in the whole text
	 * @param wordToOccure
	 * @return
	 */
	public boolean occursOnText(String wordToOccure){
		return wholeText.contains(wordToOccure);
	}
	
	public int getNumberOfSentence(){
		return (sentences == null) ? 0 : sentences.size();
	}
	
}
