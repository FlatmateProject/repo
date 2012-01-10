package manager;

import hibernate.WordEntity;

import org.hibernate.Session;

import service.FindElementService;
import exception.ServiceException;

public class ServiceManagerImpl extends AbstractServiceManager implements ServiceManager {

	public ServiceManagerImpl(Session session) {
		super(session);
	}

	@Override
	public WordEntity invoceElementWord(String wordName) throws ServiceException {
		FindElementService service = getService(FindElementService.class);
		service.setWord(wordName);
		return execute(service);
	}

}
