package dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Thic class holds all the words that can be used to determine whether a sentence is a question or not.
 * @author Andreas
 *
 */
public class Dictionary {
	List<String> questionWords;
	
	/**
	 * Reads question words from file
	 * @param filename
	 * @throws IOException
	 */
	public void readFromFile(String filename) throws IOException{
		if(questionWords == null){
			questionWords = new ArrayList<String>();
		}
		FileInputStream inputStream = new FileInputStream(filename);
		DataInputStream stream = new DataInputStream(inputStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		String word;
		while((word = reader.readLine()) != null){
			questionWords.add(word);
		}
		
		reader.close();
		stream.close();
		inputStream.close();
		
	}
	
	/**
	 * Determines if a given word is found in the dictionary or not.
	 * @param wordToCheck
	 * @return true if it's found, false if not
	 */
	public boolean isQuestionWord(String wordToCheck){
		if(this.questionWords == null){
			throw new IllegalStateException("WordToCheck and/or questionWords is null");
		}
		if(wordToCheck == null){
			return false;
		}
		return questionWords.contains(wordToCheck);
	}
}
