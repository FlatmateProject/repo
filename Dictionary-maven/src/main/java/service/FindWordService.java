package service;

import static service.ERROR_MESSAGE.EMPTY_WORD;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;


import model.dictionary.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class FindWordService extends AbstractService<WordEntity> {
	
	private String wordName;
	private WordEntity result;
	

	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		result = getDictionaryDao().findWord(wordName);	
		restrictionIsNotNull(result, WORD_NOT_FOUND);
		
		return result;
	}

	@Override
	public void validation() throws ServiceException {
		restrictionIsNotNullAndEmpty(wordName, EMPTY_WORD);
	}
	
	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}
}
