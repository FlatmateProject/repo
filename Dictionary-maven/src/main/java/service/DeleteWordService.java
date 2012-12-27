package service;

import exception.DaoException;
import exception.ServiceException;
import model.dictionary.WordEntity;
import org.springframework.context.ApplicationContext;

import static service.ERROR_MESSAGE.EMPTY_PARAMETERS_LIST;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;

public class DeleteWordService extends AbstractService<Boolean> {
	
	private String wordName;

	private WordEntity word;

	private long wordId;

	@Override
	protected Boolean runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invokeFindWord(wordName, wordId);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		getDictionaryDao().delete(word);
		
		return Boolean.TRUE;
	}

	@Override
	public void validation() throws ServiceException {
		if(wordName == null && wordId <= 0){
			throw new ServiceException(EMPTY_PARAMETERS_LIST);
		}
	}


	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}
}
