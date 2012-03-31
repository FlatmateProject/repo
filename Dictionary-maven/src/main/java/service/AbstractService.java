package service;

import java.util.HashSet;
import java.util.Set;

import manager.ServiceManager;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import dao.DictionaryDao;
import exception.DaoException;
import exception.MyException;
import exception.ServiceException;

import static service.ERROR_MESSAGE.EXECUTE_SERVICE_ERROR;

public abstract class AbstractService<T> {

	private Session session;

	private DictionaryDao dictionaryDao;
	
	private ServiceManager serviceManager;

	protected abstract T runService(ApplicationContext serviceContext) throws ServiceException, DaoException;
	
	public abstract void validation() throws ServiceException;
	        
	
	private void initializeFields(ApplicationContext applicationContext) throws ServiceException {
		dictionaryDao.setSession(session);
		serviceManager.setSession(session);
		serviceManager.setApplicationContext(applicationContext);
	}
	
	public T executeService(ApplicationContext applicationContext) throws ServiceException {
		try {
			
			initializeFields(applicationContext);
			
			validation();

			T result = runService(applicationContext);
			
			return result;
			
		} catch (MyException e) {
			throw new ServiceException(e.getErrorMessage(), e);
		}catch (Exception e) {
			throw new ServiceException(EXECUTE_SERVICE_ERROR, e);
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
	
	protected boolean restrictionIsNotNull(Object object, ERROR_MESSAGE errorMessage) throws ServiceException {
		if (object == null) {
			throw new ServiceException(errorMessage);
		}
		return true;
	}
	
	protected boolean restrictionIsNotEmpty(String object, ERROR_MESSAGE errorMessage) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(errorMessage);
		}
		return true;
	}
	
	protected boolean restrictionIsNotEmpty(Set<?> object, ERROR_MESSAGE errorMessage) throws ServiceException {
		if (object.isEmpty()) {
			throw new ServiceException(errorMessage);
		}
		return true;
	}
	
	protected boolean restrictionIsNotNullAndEmpty(String object, ERROR_MESSAGE errorMessage) throws ServiceException {
		return restrictionIsNotNull(object, errorMessage) && restrictionIsNotEmpty(object, errorMessage);
	}
	
	protected boolean restrictionIsNotNullAndEmpty(Set<?> object, ERROR_MESSAGE errorMessage) throws ServiceException {
		return restrictionIsNotNull(object, errorMessage) && restrictionIsNotEmpty(object, errorMessage);
	}
	protected boolean restrictionIsNotNegativeValue(int value, ERROR_MESSAGE errorMessage) throws ServiceException {
		if (value <= 0 ) {
			throw new ServiceException(errorMessage);
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
