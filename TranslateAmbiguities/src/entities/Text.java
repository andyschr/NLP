package entities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class consists of a list of sentences and also of a string which represents the whole text
 * @author Andreas
 *
 */
public class Text {

	String wholeText;
	List<Sentence> sentences;
	int sentenceCounter = 0;
	private BufferedReader br;
	
	public void tokenizeText(){
		//TODO
	}
	
	/**
	 * Returns next sentence in text
	 * @return Null if no more sentence
	 */
	public Sentence getNextSentence(){
		if(sentenceCounter >= sentences.size()){
			return null;
		}
		Sentence ret = sentences.get(sentenceCounter);
		sentenceCounter++;
		
		return ret;
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
	 * @return True if text contains the word. False if not
	 */
	public boolean occursOnText(String wordToOccure){
		return wholeText.contains(wordToOccure);
	}
	
	public int getNumberOfSentence(){
		return (sentences == null) ? 0 : sentences.size();
	}
	
	/**
	 * Reads the text from a file. The file hast to have each sentence in a different line
	 * so this method can "tokenize" directly into sentences
	 * @param filename
	 * @throws IOException
	 */
	public void readFromFile(String filename) throws IOException{
		FileInputStream fstream = new FileInputStream("inputText.txt");
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		
		//read each line as sentence
		String line;
		while((line = br.readLine()) != null){
			addSentence(line);
			//add to text as String
			wholeText += line + " ";
		}
		
		br.close();
		in.close();
		
		
	}

	
	private void addSentence(String line) {
		if(this.sentences == null){
			sentences = new ArrayList<Sentence>();
			
		}
		Sentence sentence = new Sentence();
		sentence.setWholeSentenceAsString(line);
		sentence.tokenizeSentence();
		sentences.add(sentence);
	}
	
}
