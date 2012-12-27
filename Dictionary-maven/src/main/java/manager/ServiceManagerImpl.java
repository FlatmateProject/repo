package manager;

import exception.ServiceException;
import model.dictionary.WordEntity;
import service.FindWordService;

public class ServiceManagerImpl extends AbstractServiceManager implements ServiceManager {

	@Override
	public WordEntity invokeFindWord(String wordName, long wordId) throws ServiceException {
		FindWordService service = getService(FindWordService.class);
		service.setWordName(wordName);
		return execute(service);
	}

}
