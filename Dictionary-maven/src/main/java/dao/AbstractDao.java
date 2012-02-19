package dao;

import org.hibernate.Session;

public abstract class AbstractDao {

	private Session session;

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
