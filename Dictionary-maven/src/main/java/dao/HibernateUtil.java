package dao;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionfactory();

	private static SessionFactory buildSessionfactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		Session session;
		try {
			session = sessionFactory.openSession();
			session.setFlushMode(FlushMode.COMMIT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		return session;
	}

	public static void closeSession(Session session) {
		if (session == null) {
			return;
		}
		try {
		//session.flush();
		session.close();
		session = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
}
