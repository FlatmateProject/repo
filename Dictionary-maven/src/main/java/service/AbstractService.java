package service;

import java.util.Set;

import manager.ServiceManager;
import manager.ServiceManagerImpl;

import org.hibernate.Session;
import org.jboss.system.ServiceContext;
import dao.DictionaryDao;
import dao.DictionaryDaoImpl;
import exception.DaoException;
import exception.ServiceException;

public abstract class AbstractService<T> {

	private Session session;

	private DictionaryDao dictionaryDao;
	
	private ServiceManager serviceManager;

	protected T result = null;

	protected abstract T runService(ServiceContext serviceContext) throws ServiceException, DaoException;
	
	public abstract void validation() throws ServiceException;
	        
	
	private void initializeFields() throws ServiceException {
		dictionaryDao = new DictionaryDaoImpl(session);
		serviceManager = new ServiceManagerImpl(session);
	}
	
	public T executeService(ServiceContext serviceContext) throws ServiceException {
		try {
			
			initializeFields();
			
			validation();

			result = runService(serviceContext);
			
			finalizeFields();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return result;
	}
	
	private void finalizeFields() {
		session = null;
		dictionaryDao = null;
	}

	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		if (this.session == null) {
			this.session = session;
		}
	}
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public boolean restrictionIsNotNull(Object object, String message) throws ServiceException {
		if (object == null) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	public boolean restrictionIsNotEmpty(String object, String message) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	public boolean restrictionIsNotEmpty(Set<?> object, String message) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	public boolean restrictionIsNotNullAndEmpty(String object, String message) throws ServiceException {
		return restrictionIsNotNull(object, message) && restrictionIsNotEmpty(object, message);
	}
	
	public boolean restrictionIsNotNullAndEmpty(Set<?> object, String message) throws ServiceException {
		return restrictionIsNotNull(object, message) && restrictionIsNotEmpty(object, message);
	}
}
