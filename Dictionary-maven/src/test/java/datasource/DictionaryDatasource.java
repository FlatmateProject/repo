package datasource;

import exception.DaoException;
import exception.ServiceException;
import hibernate.WordEntity;

public interface DictionaryDatasource {

	public WordEntity createWord() throws ServiceException, DaoException, Exception;
}
