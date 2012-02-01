package service;

import hibernate.WordEntity;

import java.util.List;

import org.jboss.system.ServiceContext;

import exception.DaoException;
import exception.ServiceException;

public class GetRandomWords extends AbstractService<List<WordEntity>> {
	
	public static final String EMPTY_RESULT = "Szukane słowo nie istnieje";
	public static final String NEGATIVE_VALUE = "Limit mniejszy niż zero o o o o";
	private int limit = 1;
	private List<WordEntity> result;

	@Override
	protected List<WordEntity> runService(ServiceContext serviceContext)throws ServiceException, DaoException {
		result = getDictionaryDao().getRandomWords(limit);	
		
		restrictionIsNotNull(result, EMPTY_RESULT);
		
		return result;
	}

	@Override
	public void validation() throws ServiceException {
		// TODO Auto-generated method stub
		restrictionIsNotNegativeValue(limit, NEGATIVE_VALUE);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
