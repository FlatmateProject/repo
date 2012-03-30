package manager;

import model.WordEntity;
import exception.ServiceException;

public interface ServiceManager {

	WordEntity invokeFindWord(String wordName) throws ServiceException;

}
