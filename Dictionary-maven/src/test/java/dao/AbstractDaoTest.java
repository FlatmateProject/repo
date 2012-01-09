package dao;

import java.lang.reflect.Constructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mchange.util.AssertException;
import exception.DaoException;
import service.AbstractProvider;
import service.AbstractService;

public abstract class  AbstractDaoTest {

	private AbstractProvider daoProvider = createDaoProvider();

	private Session session;

	private Transaction transaction;

	public interface TestDaoPattern<S, R> {

		public abstract R initialize(S dao) throws DaoException;

		public abstract void assertResult(R result);
		
		public abstract boolean assertException(Exception exception);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, X> void patternTestMethod(TestDaoPattern testDaoPattern, Class<?> serviceName) {
		
		try {
			session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			
			T dao = (T) daoProvider.getInstance(serviceName);

			X result = (X) testDaoPattern.initialize(dao);

			testDaoPattern.assertResult(result);
			
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
			if (!testDaoPattern.assertException(e)) {
				e.printStackTrace();
				throw new AssertException(e.getMessage());
			}
		} finally {
			session.clear();
			HibernateUtil.closeSession(session);
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
				Constructor<T> constructor = (Constructor<T>) serviceName.getConstructor(Session.class);
				return (T) constructor.newInstance(session);
			}

			@Override
			public <T> T execute(AbstractService<?> service) throws Exception {
				return null;
			}

		};
	}

}
