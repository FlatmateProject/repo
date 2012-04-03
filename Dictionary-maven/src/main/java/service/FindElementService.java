package service;

import hibernate.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class FindElementService extends AbstractService<WordEntity> {
	
	public static final String EMPTY_WORD = "Słow nie możę być puste";
	public static final String WORD_NOT_EXIST = "Szukane słowo nie istnieje";
	private String wordName;
	private WordEntity result;
	

	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		result = getDictionaryDao().findWord(wordName);	
		restrictionIsNotNull(result, WORD_NOT_EXIST);
		
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
