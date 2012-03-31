package manager;

import model.dictionary.WordEntity;
import service.FindWordService;
import exception.ServiceException;

public class ServiceManagerImpl extends AbstractServiceManager implements ServiceManager {

	@Override
	public WordEntity invokeFindWord(String wordName) throws ServiceException {
		FindWordService service = getService(FindWordService.class);
		service.setWordName(wordName);
		return execute(service);
	}

}
