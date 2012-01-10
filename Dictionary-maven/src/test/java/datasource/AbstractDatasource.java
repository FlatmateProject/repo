package datasource;

import org.hibernate.Session;

import service.AbstractProvider;
import service.AbstractService;
import exception.DatasourceException;

public abstract class  AbstractDatasource {
	
	private Session session; 
	
	public AbstractProvider datasourceProvider = createDatasourceProvider();
	
	protected AbstractGenerator generator = new GeneratorImpl();
	
	public AbstractDatasource(Session session) {
		this.session = session;
	}

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
	
	@SuppressWarnings("unchecked")
	public <T> T execute(AbstractService<T> service) throws DatasourceException {

		try {
			service.setSession(session);
			
			T result = (T) datasourceProvider.execute(service);

			session.connection().commit();

			return result;

		} catch (Exception e) {
			rollback();
			throw new DatasourceException(e.getMessage());
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
	
	private void rollback() throws DatasourceException{
		try {
			session.connection().rollback();
		} catch (Exception e) {
			throw new DatasourceException(e.getMessage());
		}
	}
	
	public boolean restrictionIsNotNull(Object object, String message) throws DatasourceException {
		if (object == null) {
			throw new DatasourceException(message);
		}
		return true;
	}
	
}
