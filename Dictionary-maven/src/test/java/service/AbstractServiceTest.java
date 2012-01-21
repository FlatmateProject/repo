package service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.system.ServiceContext;
import com.mchange.util.AssertException;
import dao.HibernateUtil;
import datasource.DictionaryDatasource;
import datasource.DictionarytDatasourceImpl;
import exception.DatasourceException;

public abstract class AbstractServiceTest {

	private AbstractProvider serviceProvider = createServiceProvider();

	protected DictionaryDatasource  dictionaryDatasource;

	private Transaction transaction;

	private Session session;
	
	public interface TestPattern<S, R> {

		public abstract void initialize(S service) throws DatasourceException;

		public abstract void assertResult(R result);
		
		public abstract boolean assertException(Exception exception);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void patternTestMethod(TestPattern testPattern, Class<?> serviceName) {
		
		try {
			
			session = HibernateUtil.getSession();
			
			transaction = session.beginTransaction();
			
			dictionaryDatasource = new DictionarytDatasourceImpl();
			
			AbstractService<T> service = serviceProvider.getInstance(serviceName);
			service.setSession(session);

			testPattern.initialize(service);

			T result = (T) serviceProvider.execute(service);

			testPattern.assertResult(result);
			
			transaction.commit();
						
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (!testPattern.assertException(e)) {
				e.printStackTrace();
				throw new AssertException(e.getMessage());
			}
		} finally {
			session.clear();
			transaction = null;
			HibernateUtil.closeSession(session);
		}
	}

	private AbstractProvider createServiceProvider() {
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
