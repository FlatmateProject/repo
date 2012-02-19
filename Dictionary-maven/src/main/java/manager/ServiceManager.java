package manager;

import hibernate.WordEntity;
import exception.ServiceException;

public interface ServiceManager {

	WordEntity invokeFindElementWord(String wordName) throws ServiceException;

}
