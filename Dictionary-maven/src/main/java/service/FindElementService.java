package service;

import hibernate.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class FindElementService extends AbstractService<WordEntity> {
	
	public static final String EMPTY_WORD = "Słow nie możę być puste";
	public static final String WORD_NOT_EXIST = "Szukane słowo nie istnieje";
	private String word;
	private WordEntity result;
	

	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		result = getDictionaryDao().findWord(word);	
		restrictionIsNotNull(result, WORD_NOT_EXIST);
		
		return result;
	}

	@Override
	public void validation() throws ServiceException {
		
		restrictionIsNotNullAndEmpty(word, EMPTY_WORD);
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
