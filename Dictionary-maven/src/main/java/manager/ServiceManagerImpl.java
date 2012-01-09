package manager;

import org.hibernate.Session;

public class ServiceManagerImpl extends AbstractServiceManager implements ServiceManager {

	public ServiceManagerImpl(Session session) {
		super(session);
	}

}
