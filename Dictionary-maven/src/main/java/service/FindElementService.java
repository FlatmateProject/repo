package service;

import org.jboss.system.ServiceContext;

import exception.DaoException;
import exception.ServiceException;
import hibernate.WordEntity;

public class FindElementService extends AbstractService<WordEntity> {
	
	public static final String EMPTY_WORD = "Słow nie możę być puste";
	public static final String EMPTY_RESULT = "Szukane słowo nie istnieje";
	private String word;
	private WordEntity result;
	

	@Override
	protected WordEntity runService(ServiceContext serviceContext) throws ServiceException, DaoException {
		
		result = getDictionaryDao().findWord(word);	
		
		restrictionIsNotNull(result, EMPTY_RESULT);
		
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
