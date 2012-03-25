package service;

import static service.ERROR_MESSAGE.EMPTY_EXAMPLES;
import static service.ERROR_MESSAGE.EMPTY_WORD;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;


import java.util.Set;


import model.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class AddExamplesToWordService extends AbstractService<WordEntity> {
	
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
