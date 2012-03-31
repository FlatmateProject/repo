package service;

import static service.ERROR_MESSAGE.EMPTY_WORD;
import static service.ERROR_MESSAGE.LIMIT_NEGATIVE_VALUE;


import java.util.List;


import model.dictionary.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class GetRandomWordsService extends AbstractService<List<WordEntity>> {
	
	private int limit = 1;
	private List<WordEntity> result;

	@Override
	protected List<WordEntity> runService(ApplicationContext serviceContext)throws ServiceException, DaoException {
		result = getDictionaryDao().getRandomWords(limit);	
		
		restrictionIsNotNull(result, EMPTY_WORD);
		
		return result;
	}

	@Override
	public void validation() throws ServiceException {
		restrictionIsNotNegativeValue(limit, LIMIT_NEGATIVE_VALUE);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
