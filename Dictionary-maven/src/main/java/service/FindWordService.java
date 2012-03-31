package service;

import model.dictionary.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

import static service.ERROR_MESSAGE.EMPTY_PARAMETERS_LIST;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;;

public class FindWordService extends AbstractService<WordEntity> {

	private String wordName;
	
	private WordEntity result;
	
	private long wordId;

	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {

		result = findByNameOrId();
		restrictionIsNotNull(result, WORD_NOT_FOUND);

		return result;
	}

	private WordEntity findByNameOrId() throws DaoException {
		if (wordId != 0) {
			return getDictionaryDao().findWordById(wordId);
		}
		
		if (wordName != null) {
			return getDictionaryDao().findWordByName(wordName);
		}
		
		return null;
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
