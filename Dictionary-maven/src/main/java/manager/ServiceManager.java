package manager;

import model.dictionary.WordEntity;
import exception.ServiceException;

public interface ServiceManager {

	WordEntity invokeFindWord(String wordName) throws ServiceException;

}
