package manager;

import hibernate.WordEntity;
import service.FindElementService;
import exception.ServiceException;

public class ServiceManagerImpl extends AbstractServiceManager implements ServiceManager {

	@Override
	public WordEntity invokeFindElementWord(String wordName) throws ServiceException {
		FindElementService service = getService(FindElementService.class);
		service.setWordName(wordName);
		return execute(service);
	}

}
