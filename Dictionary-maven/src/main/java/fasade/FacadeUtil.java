package fasade;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.system.ServiceContext;
import dao.HibernateUtil;
import exception.ApplicationException;
import service.AbstractProvider;
import service.AbstractService;

public class FacadeUtil {

	private final static ServiceContext serviceContext = new ServiceContext();

	private final static AbstractProvider serviceProvider = createServiceProvider();
	
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<?> serviceName) throws ApplicationException {
		try {		
			return (T) serviceProvider.getInstance(serviceName);
		} catch (Exception e) {
			throw new ApplicationException("Nie udało sie utworzyc uslugi", e);
		}
	}

	public static <T> T executeService(AbstractService<T> service) throws ApplicationException {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			return executeService(service, session, transaction);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static <T> T executeService(AbstractService<?> service,	Session session, Transaction transaction) throws ApplicationException {
		try {
			session = HibernateUtil.getSession();

			service.setSession(session);

			T result = (T) service.executeService(serviceContext);

			transaction.commit();

			return result;

		} catch (Exception e) {
			transaction.rollback();
			throw new ApplicationException(e);
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	private static AbstractProvider createServiceProvider() {
		if (serviceProvider != null) {
			return serviceProvider;
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
				ServiceContext context = new ServiceContext();
				return (T) service.executeService(context);
			}

		};
	}

}
