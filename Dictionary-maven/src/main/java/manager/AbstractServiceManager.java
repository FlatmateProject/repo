package manager;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import service.AbstractService;
import dao.ClientDao;
import dao.DictionaryDao;
import exception.DatasourceException;
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

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<?> serviceName) throws ServiceException {
		try {
			return (T)serviceName.newInstance();
		} catch (Exception e) {
			throw new DatasourceException("Nie udało sie utworzyć usługi", e);
		}
	}
	
	public boolean restrictionIsNotNull(Object object, String message) throws ServiceException {
		if (object == null) {
			throw new ServiceException(message);
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
