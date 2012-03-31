package model;

import org.hibernate.Session;

public interface AddSesion {
	
	Session getSession();
	
	void setSession(Session session);

}
