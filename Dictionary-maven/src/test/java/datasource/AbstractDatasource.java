package datasource;

import static service.ERROR_MESSAGE.CREATE_SERVICE_ERROR;
import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;
import manager.ServiceManager;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;

import service.AbstractService;
import service.ERROR_MESSAGE;
import dao.DictionaryDao;
import exception.MyException;
import exception.ServiceException;

public abstract class AbstractDatasource {
	
	private ApplicationContext applicationContext;
	
	protected Generator generator;
	
	private void initializeService(AbstractService<?> service, Session session) {
		service.setSession(session);
		service.setDictionaryDao((DictionaryDao)applicationContext.getBean("myDictionaryDao"));
		service.setServiceManager((ServiceManager)applicationContext.getBean("myServiceManager"));
	}
	
	public <T> T execute(AbstractService<T> service) throws ServiceException {
		try {
			
			Session session = (Session)applicationContext.getBean("mySession");
			session.setFlushMode(FlushMode.COMMIT);
			Transaction transaction = session.beginTransaction();
			return (T) execute(service, session, transaction);
		} catch (MyException e) {
			throw new ServiceException(e.getErrorMessage(), e);
		} catch (Exception e) {
			throw new ServiceException(EXECUTE_SERVICE_ERROR, e);
		}
	}
	
	private <T> T execute(AbstractService<T> service, Session session, Transaction transaction) throws ServiceException {

		try {
			
			initializeService(service, session);
			
			T result = (T)service.executeService(applicationContext);

			transaction.commit();

			return result;

		} catch (MyException e) {
			transaction.rollback();
			throw new ServiceException(e.getErrorMessage(), e);
		} catch (Exception e) {
			throw new ServiceException(EXECUTE_SERVICE_ERROR, e);
		}finally {
			session.close();
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
	
	public void setApplicationContext(ApplicationContext context) {
		this.applicationContext = context;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
}
