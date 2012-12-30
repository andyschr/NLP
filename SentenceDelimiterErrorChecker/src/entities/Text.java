package entities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/**
 * This class holds a list of alle sentences that. The sentences can be read from textfile.
 * Therefore every sentence has to be in a single line.
 * @author Andreas
 *
 */
public class Text {

	List<Sentence> sentences;
	
	/**
	 * Reads the text which has to be checked for errors. Structure has to be one sentence per line.
	 * @param filename
	 * @throws IOException 
	 */
	public void readText(String filename) throws IOException{
		
		if(sentences == null){
			sentences = new ArrayList<Sentence>();
		}
		
		//read from file
		FileInputStream inputStream = new FileInputStream(filename);
		DataInputStream stream = new DataInputStream(inputStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		while((line = reader.readLine()) != null){
			sentences.add(new Sentence(line));
		}
		reader.close();
		stream.close();
		inputStream.close();
		
		
	}
	
	public List<Sentence> getSentences(){
		
		if(this.sentences == null){
			throw new IllegalStateException("Sentences has not been initialised");
		}
		return this.sentences;
	}
	
	/**
	 * Adds a sentence to the list of sentences
	 * @param sentence
	 */
	public void addSentence(String sentence){
		if(sentences == null){
			this.sentences = new ArrayList<Sentence>();
		}
		this.sentences.add(new Sentence(sentence));
	}
	
	@Override
	public String toString(){
		if(this.sentences == null){
			throw new IllegalStateException("Sentences is null");
		}
		 StringBuffer buffer = new StringBuffer();
		 for(Sentence sentence : this.sentences){
			 buffer.append(sentence.getSentence());
			 buffer.append("\n");
		 }
		 
		 return buffer.toString();
	}
}
