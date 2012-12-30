package processor;

import dictionary.Dictionary;
import entities.Sentence;
import entities.Text;

/**
 * This class is responsible for determining if a sentence has to be corrected or not.
 * If yes it corrects it.
 * @author Andreas
 *
 */
public class ErrorChecker {

	
	public final void setDict(Dictionary dict) {
		this.dict = dict;
	}

	Dictionary dict;
	
	/**
	 * Checks if a false delimiter was used and corrects it.
	 * @param text
	 * @return The corrected text of Type ({@link Text}}
	 */
	public Text correctText(Text text){
		if(dict == null){
			throw new IllegalStateException("Dictionary has not been set.");
		}
	
		Text ret = new Text();
		for(Sentence sentence : text.getSentences()){
			if(!sentence.isInvalidDelimiter()){
				boolean isQuestion = checkIfQuestion(sentence);
				if(isQuestion && (sentence.getDelimiter().equals(".")) || sentence.getDelimiter().equals("!")){
					ret.addSentence(correct(sentence));
				}
				else if(!isQuestion && (sentence.getDelimiter().equals("?"))){
					ret.addSentence(correct(sentence));
				}
				else ret.addSentence(sentence.getSentence());
			}
		}
		return ret;
	}

	/**
	 * Corrects a single sentence when the checker has detected the usage of a wrong delimiter.
	 * @param sentence
	 * @return Corrected String
	 */
	private String correct(Sentence sentence) {
		String sentencestr = sentence.getSentence();
		String ret = null;
		if(sentencestr.substring(sentencestr.length()-1).equalsIgnoreCase(".")){
			ret = sentencestr.replace('.', '?');
		}
		else if(sentencestr.substring(sentencestr.length()-1).equalsIgnoreCase("?")){
			ret = sentencestr.replace('?', '.');
		}
		else if(sentencestr.substring(sentencestr.length()-1).equalsIgnoreCase("!")){
			ret = sentencestr.replace('!', '?');
		}
		else {
			System.out.println("Cannot correct sentence");
			ret = sentencestr;
		}
		return ret;
	}

	/**
	 * Checks if the given sentence is a question oder not.
	 * To check this it looks at the dictionary
	 * @param sentence
	 * @return True if yes, false if not.
	 */
	private boolean checkIfQuestion(Sentence sentence) {
		
		if(sentence == null){
			throw new IllegalStateException("Parameter sentence is null");
		}
		return (dict.isQuestionWord(sentence.getFirstWord()) || dict.isQuestionWord(sentence.getFirstWordAfterComma()));
	}
	
	
}
