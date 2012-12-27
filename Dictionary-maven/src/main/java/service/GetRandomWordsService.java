package service;

import exception.DaoException;
import exception.ServiceException;
import model.dictionary.WordEntity;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static service.ERROR_MESSAGE.EMPTY_WORD_NAME;
import static service.ERROR_MESSAGE.LIMIT_NEGATIVE_VALUE;

public class GetRandomWordsService extends AbstractService<List<WordEntity>> {
	
	private int limit = 1;
	private List<WordEntity> result;

	@Override
	protected List<WordEntity> runService(ApplicationContext serviceContext)throws ServiceException, DaoException {
		result = getDictionaryDao().getRandomWords(limit);	
		
		restrictionIsNotNull(result, EMPTY_WORD_NAME);
		
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
