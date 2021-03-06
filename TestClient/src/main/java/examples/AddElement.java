package examples;

import java.util.HashSet;
import java.util.Set;

import exception.ApplicationException;
import facade.DictionaryBeanFacade;
import facade.DictionaryEndPoint;
import facade.response.WordResponse;


public class AddElement {


	public static void main(String[] args) throws ApplicationException 
	{
		String wordName;

		Set<String> translations = new HashSet<String>();

		Set<String> examples = new HashSet<String>();

		wordName = "answer back";
		translations.add("odpowiadać niegrzecznie");
		translations.add("pyskować");
		examples.add("How dare you answer back like that?!");
			
		DictionaryEndPoint dictionary = new DictionaryBeanFacade();
		
		WordResponse result = dictionary.addWord(wordName, translations, examples);
		
		System.out.println("addElement result: " + result);
		
	}

}
