package service;

import java.util.Set;

import model.dictionary.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

import static service.ERROR_MESSAGE.EMPTY_EXAMPLES;
import static service.ERROR_MESSAGE.EMPTY_PARAMETERS_LIST;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;

public class AddExamplesToWordService extends AbstractService<WordEntity> {
	
	private String wordName;

	private WordEntity word;

	private Set<String> examples;

	private long wordId;

	
	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invokeFindWord(wordName, wordId);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		word.addExamples(examples);
		
		return word;
	}

	@Override
	public void validation() throws ServiceException {
		if(wordName == null && wordId <= 0){
			throw new ServiceException(EMPTY_PARAMETERS_LIST);
		}
		restrictionIsNotNullAndEmpty(examples, EMPTY_EXAMPLES);
	}


	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setExamples(Set<String> examples) {
		this.examples = examples;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

}
