package manager;

import static service.ERROR_MESSAGE.CREATE_SERVICE_ERROR;
import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import service.AbstractService;
import service.ERROR_MESSAGE;
import dao.ClientDao;
import dao.DictionaryDao;
import exception.MyException;
import exception.ServiceException;

public abstract class AbstractServiceManager {

	private ApplicationContext applicationContext;
	
	private Session session;
	
	private DictionaryDao dictionaryDao;
	
	private ClientDao clientDao;
	
	private void initializeService(AbstractService<?> service) {
		service.setSession(session);
		service.setDictionaryDao(dictionaryDao);
		service.setClientDao(clientDao);
		service.setServiceManager((ServiceManager)this);
	}
	
	public <T> T execute(AbstractService<T> service) throws ServiceException {

		try {
			
			initializeService(service);
			
			T result = (T)service.executeService(applicationContext);

			return result;

		} catch (MyException e) {
			throw new ServiceException(e.getErrorMessage(), e);
		}catch (Exception e) {
			throw new ServiceException(EXECUTE_SERVICE_ERROR, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<?> serviceName) throws ServiceException {
		try {
			return (T)serviceName.newInstance();
		} catch (Exception e) {
			throw new ServiceException(CREATE_SERVICE_ERROR, e);
		}
	}
	
	public boolean restrictionIsNotNull(Object object, ERROR_MESSAGE errorMessage) throws ServiceException {
		if (object == null) {
			throw new ServiceException(errorMessage);
		}
		return true;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
