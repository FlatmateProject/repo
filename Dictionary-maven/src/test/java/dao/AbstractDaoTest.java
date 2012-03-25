package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.AbstractProvider;
import service.AbstractService;

import com.mchange.util.AssertException;

import exception.DaoException;
import exception.MyException;

public abstract class  AbstractDaoTest {

	private AbstractProvider daoProvider = createDaoProvider();
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	
	private Session session;

	private Transaction transaction;

	public interface TestDaoPattern<S, R> {

		public abstract R initialize(S dao) throws DaoException;

		public abstract void assertResult(R result);
		
		public abstract boolean assertException(MyException exception);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, X> void patternTestMethod(TestDaoPattern testDaoPattern, Class<?> serviceName) {
		
		try {

			T dao = (T) daoProvider.getInstance(serviceName);
			
			session = (Session)applicationContext.getBean("mySession");
			((AbstractDao)dao).setSession(session);

			transaction = session.beginTransaction();
			
			X result = (X) testDaoPattern.initialize(dao);

			testDaoPattern.assertResult(result);
			
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
			if (!testDaoPattern.assertException((MyException)e)) {
				e.printStackTrace();
				throw new AssertException(e.getMessage());
			}
		} finally {
			transaction = null;
			session.close();
			session = null;
		}
	}

	private AbstractProvider createDaoProvider() {
		if (daoProvider != null) {
			return daoProvider;
		}
		return new AbstractProvider() {

			@SuppressWarnings("unchecked")
			@Override
			public <T> T getInstance(Class<?> serviceName) throws Exception {
				return (T) applicationContext.getBean("my" + serviceName.getSimpleName());
			}

			@Override
			public <T> T execute(AbstractService<?> service) throws Exception {
				return null;
			}

		};
	}

}
