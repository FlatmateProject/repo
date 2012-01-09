package fasade;

import org.hibernate.Session;
import org.jboss.system.ServiceContext;
import dao.HibernateUtil;
import service.AbstractProvider;
import service.AbstractService;

public class FacadeUtil {

	private final static ServiceContext serviceContext = new ServiceContext();

	private final static AbstractProvider serviceProvider = createServiceProvider();
	
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<?> serviceName) {
		try {		
			return (T) serviceProvider.getInstance(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static <T> T executeService(AbstractService<T> service)  {
		T result = null;
		try {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			service.setSession(session);
			
			result = service.executeService(serviceContext);
			
			commit(session);
			
		} catch (Exception e) {
			rollback(service.getSession());
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(service.getSession());
		}
		return result;
	}
	
	
	private static void commit(Session session) throws Exception {
		session.connection().commit();
	}

	private static void rollback(Session session) {
		try {
			session.connection().rollback();
		} catch (Exception e) {
			e.printStackTrace();
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
