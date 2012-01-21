package datasource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import service.AbstractProvider;
import service.AbstractService;
import dao.HibernateUtil;
import exception.DatasourceException;

public abstract class AbstractDatasource {
	
	public AbstractProvider datasourceProvider = createDatasourceProvider();
	
	protected AbstractGenerator generator = new GeneratorImpl();
	
	private AbstractProvider createDatasourceProvider() {
		if (datasourceProvider  != null) {
			return datasourceProvider;
		}
		return new AbstractProvider() {

			@SuppressWarnings("unchecked")
			@Override
			public <T> T getInstance(Class<?> serviceName) throws Exception {
				return (T) serviceName.newInstance();
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T execute(AbstractService<?> service) throws Exception {
				return (T) service.executeService(null);
			}

		};
	}
	
	public <T> T execute(AbstractService<T> service) throws DatasourceException {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			return (T) execute(service, session, transaction);
		} catch (Exception e) {
			throw new DatasourceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T execute(AbstractService<T> service, Session session, Transaction transaction) throws DatasourceException {

		try {
			service.setSession(session);
			
			T result = (T) datasourceProvider.execute(service);

			transaction.commit();

			return result;

		} catch (Exception e) {
			transaction.rollback();
			throw new DatasourceException(e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<?> serviceName) throws DatasourceException {
		try {
			
			return (T) datasourceProvider.getInstance(serviceName);
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
	
}
