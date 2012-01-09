package manager;

import org.hibernate.Session;
import exception.DatasourceException;
import exception.ServiceException;
import service.AbstractProvider;
import service.AbstractService;

public abstract class AbstractServiceManager {

	private Session session; 
	
	public AbstractProvider serviceIntProvider = createServiceIntProvider();
	
	public AbstractServiceManager(Session session) {
		this.session = session;
	}


	private AbstractProvider createServiceIntProvider() {
		if (serviceIntProvider  != null) {
			return serviceIntProvider;
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
	public <T> T execute(AbstractService<T> service) throws ServiceException {

		try {
			service.setSession(session);
			
			T result = (T)serviceIntProvider.execute(service);

			return result;

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<?> serviceName) throws ServiceException {
		try {
			
			return (T)serviceIntProvider.getInstance(serviceName);
		} catch (Exception e) {
			throw new DatasourceException("Nie udało sie utworzyć usługi", e);
		}
	}
	
}
