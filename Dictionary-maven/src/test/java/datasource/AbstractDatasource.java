package datasource;

import manager.ServiceManager;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;

import service.AbstractService;
import dao.DictionaryDao;
import exception.DatasourceException;

public abstract class AbstractDatasource {
	
	private ApplicationContext applicationContext;
	
	protected Generator generator;
	
	private void initializeService(AbstractService<?> service, Session session) {
		service.setSession(session);
		service.setDictionaryDao((DictionaryDao)applicationContext.getBean("myDictionaryDao"));
		service.setDictionaryDao((DictionaryDao)applicationContext.getBean("myClientDao"));
		service.setServiceManager((ServiceManager)applicationContext.getBean("myServiceManager"));
	}
	
	public <T> T execute(AbstractService<T> service) throws DatasourceException {
		try {
			Session session = (Session)applicationContext.getBean("mySession");
			session.setFlushMode(FlushMode.COMMIT);
			Transaction transaction = session.beginTransaction();
			return (T) execute(service, session, transaction);
		} catch (Exception e) {
			throw new DatasourceException(e);
		}
	}
	
	private <T> T execute(AbstractService<T> service, Session session, Transaction transaction) throws DatasourceException {

		try {
			
			initializeService(service, session);
			
			T result = (T)service.executeService(applicationContext);

			transaction.commit();

			return result;

		} catch (Exception e) {
			transaction.rollback();
			throw new DatasourceException(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<?> serviceName) throws DatasourceException {
		try {
			return (T)serviceName.newInstance();
		} catch (Exception e) {
			throw new DatasourceException("Nie udało sie utworzyć usługi", e);
		}
	}

	
	public boolean restrictionIsNotNull(Object object, String message) throws DatasourceException {
		if (object == null) {
			throw new DatasourceException(message);
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
