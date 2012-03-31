package service;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.util.AssertException;

import datasource.DictionaryDatasource;
import exception.MyException;
import exception.ServiceException;

public abstract class AbstractServiceTest {

	private AbstractProvider serviceProvider = createServiceProvider();

	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	
	protected DictionaryDatasource  dictionaryDatasource;

	private Transaction transaction;
	
	public interface TestPattern<S extends AbstractService<?>, R> {

		public abstract void initialize(S service) throws ServiceException;

		public abstract void assertResult(R result);
		
		public abstract boolean assertException(MyException exception);
	}
	
	private void initializeDatasource() {
		dictionaryDatasource = (DictionaryDatasource)applicationContext.getBean("myDictionarytDatasource");
		dictionaryDatasource.setApplicationContext(applicationContext);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void patternTestMethod(TestPattern testPattern, Class<?> serviceName) {
		
		try {
			initializeDatasource();

			AbstractService<T> service = serviceProvider.getInstance(serviceName);
			
			transaction = beginTransaction(service.getSession());
			
			testPattern.initialize(service);

			T result = (T) serviceProvider.execute(service);

			testPattern.assertResult(result);
			
			transaction.commit();
						
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (!testPattern.assertException((MyException)e)) {
				e.printStackTrace();
				throw new AssertException(e.getMessage());
			}
		} finally {
			transaction = null;
		}
	}

	private Transaction beginTransaction(Session session) {
		session.setFlushMode(FlushMode.COMMIT);
		return session.beginTransaction();
	}

	private AbstractProvider createServiceProvider() {
		if (serviceProvider != null) {
			return serviceProvider;
		}
		return new AbstractProvider() {

			@SuppressWarnings("unchecked")
			@Override
			public <T> T getInstance(Class<?> serviceName) throws Exception {
				return (T) applicationContext.getBean("my" + serviceName.getSimpleName());
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T execute(AbstractService<?> service) throws Exception {
				return (T) service.executeService(applicationContext);
			}

		};
	}

}
