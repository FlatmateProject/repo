package facade;

import exception.ApplicationException;
import exception.MyException;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AbstractProvider;
import service.AbstractService;

import static service.ERROR_MESSAGE.CREATE_SERVICE_ERROR;
import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;

public class FacadeUtil {
	
	private final static AbstractProvider serviceProvider = createServiceProvider();
	
	private final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<?> serviceName) throws ApplicationException {
		try {		
			return (T) serviceProvider.getInstance(serviceName);
		} catch (Exception e) {
			throw new ApplicationException(CREATE_SERVICE_ERROR, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T executeService(AbstractService<T> service) throws ApplicationException {
		try {
			Session session = service.getSession();
			session.setFlushMode(FlushMode.COMMIT);
			return (T)executeService(service, session.beginTransaction());
		} catch (MyException e) {
			throw new ApplicationException(e.getErrorMessage(), e);
		} catch (Exception e) {
			throw new ApplicationException(EXECUTE_SERVICE_ERROR, e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static <T> T executeService(AbstractService<?> service, Transaction transaction) throws Exception {
		try {

			T result = (T) serviceProvider.execute(service);

			transaction.commit();

			return result;

		} catch (MyException e) {
			transaction.rollback();
			throw new ApplicationException(e.getErrorMessage(), e);
		}catch (Exception e) {
			throw new ApplicationException(EXECUTE_SERVICE_ERROR, e);
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
				return (T) getBean("my" + serviceName.getSimpleName());
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T execute(AbstractService<?> service) throws Exception {
				return (T) service.executeService(applicationContext);
			}

		};
	}
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
