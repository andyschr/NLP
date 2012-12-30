package app;

import java.io.IOException;

import dictionary.Dictionary;

import processor.ErrorChecker;
import entities.Text;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Text inputText = new Text();
		Text outputText = new Text();
		ErrorChecker echecker = new ErrorChecker();
		Dictionary dict = new Dictionary();
		
		dict.readFromFile("questionWords.txt");
		inputText.readText("inputText.txt");
		echecker.setDict(dict);
		
		outputText = echecker.correctText(inputText);
		
		System.out.println(inputText.toString());
		System.out.println(outputText.toString());

	}

}
