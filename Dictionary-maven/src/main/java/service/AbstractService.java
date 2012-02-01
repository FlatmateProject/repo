package service;

import java.util.HashSet;
import java.util.Set;

import manager.ServiceManager;
import manager.ServiceManagerImpl;
import org.hibernate.Session;
import dao.DictionaryDao;
import dao.DictionaryDaoImpl;
import exception.DaoException;
import exception.ServiceException;

public abstract class AbstractService<T> {

	private Session session;

	private DictionaryDao dictionaryDao;
	
	private ServiceManager serviceManager;

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

			T result = runService(serviceContext);
			
			finalizeFields();
			
			return result;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private void finalizeFields() {
		session = null;
		dictionaryDao = null;
	}

	protected DictionaryDao getDictionaryDao() {
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
	
	protected ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	protected String reduceWhitespace(String text) {
		return text.replaceAll("\\s+", " ");
	}
	
	protected Set<String> reduceWhitespace(Set<String> sentences) {
		Set<String> result = new HashSet<String>();
		for(String s : sentences){
			result.add(reduceWhitespace(s));
		}
		return result;
	}
	
	protected boolean restrictionIsNotNull(Object object, String message) throws ServiceException {
		if (object == null) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	protected boolean restrictionIsNotEmpty(String object, String message) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	protected boolean restrictionIsNotEmpty(Set<?> object, String message) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(message);
		}
		return true;
	}
	
	protected boolean restrictionIsNotNullAndEmpty(String object, String message) throws ServiceException {
		return restrictionIsNotNull(object, message) && restrictionIsNotEmpty(object, message);
	}
	
	protected boolean restrictionIsNotNullAndEmpty(Set<?> object, String message) throws ServiceException {
		return restrictionIsNotNull(object, message) && restrictionIsNotEmpty(object, message);
	}
}
