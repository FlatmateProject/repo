package service;

import java.util.HashSet;
import java.util.Set;

import manager.AbstractServiceManager;
import manager.ServiceManager;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import dao.AbstractDao;
import dao.DictionaryDao;
import exception.DaoException;
import exception.ServiceException;

public abstract class AbstractService<T> {

	private Session session;

	private DictionaryDao dictionaryDao;
	
	private ServiceManager serviceManager;

	protected abstract T runService(ApplicationContext serviceContext) throws ServiceException, DaoException;
	
	public abstract void validation() throws ServiceException;
	        
	
	private void initializeFields(ApplicationContext applicationContext) throws ServiceException {
		((AbstractDao)dictionaryDao).setSession(session);
		((AbstractServiceManager)serviceManager).setSession(session);
		((AbstractServiceManager)serviceManager).setApplicationContext(applicationContext);
	}
	
	public T executeService(ApplicationContext applicationContext) throws ServiceException {
		try {
			
			initializeFields(applicationContext);
			
			validation();

			T result = runService(applicationContext);
			
			return result;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
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
	protected boolean restrictionIsNotNegativeValue(int value, String message) throws ServiceException {
		if (value <= 0 ) {
			throw new ServiceException(message);
		}
		return true;
	}

	protected DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}
	
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}
	
	protected ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
