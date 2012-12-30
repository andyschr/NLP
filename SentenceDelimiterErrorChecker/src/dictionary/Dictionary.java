package dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	public boolean isQuestionWord(String wordToCheck){
		if(wordToCheck == null || this.questionWords == null){
			throw new IllegalStateException("WordToCheck and/or questionWords is null");
		}
		return questionWords.contains(wordToCheck);
	}
}
