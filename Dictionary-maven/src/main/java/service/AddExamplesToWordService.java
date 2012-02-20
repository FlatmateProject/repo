package service;

import java.util.Set;

import org.springframework.context.ApplicationContext;

import hibernate.WordEntity;

import exception.DaoException;
import exception.ServiceException;
import service.AbstractService;

public class AddExamplesToWordService extends AbstractService<WordEntity> {

	public static final String WORD_NOT_FOUND = "Podane słowo nie istnieje";
	
	public static final String EMPTY_WORD = "Słowo nie może być puste";
	
	public static final String EMPTY_EXAMPLES = "Lista przykładów nie może być pusta";
	
	private String wordName;

	private WordEntity word;

	private Set<String> examples;

	
	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invokeFindElementWord(wordName);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		word.addExamples(examples);
		
		return word;
	}

	@Override
	public void validation() throws ServiceException {
		restrictionIsNotNullAndEmpty(wordName, EMPTY_WORD);
		restrictionIsNotNullAndEmpty(examples, EMPTY_EXAMPLES);
	}


	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setExamples(Set<String> examples) {
		this.examples = examples;
	}

}
