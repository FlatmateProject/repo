package manager;

import hibernate.WordEntity;
import exception.ServiceException;

public interface ServiceManager {

	WordEntity invoceElementWord(String wordName) throws ServiceException;

}
