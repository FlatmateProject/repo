package manager;

import model.WordEntity;
import exception.ServiceException;

public interface ServiceManager {

	WordEntity invokeFindElementWord(String wordName) throws ServiceException;

}
