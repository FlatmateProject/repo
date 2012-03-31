package service;

import manager.ServiceManager;

import org.easymock.EasyMock;

import dao.DictionaryDao;
import exception.ServiceException;

public abstract class AbstractServiceTestMock {
	
	protected DictionaryDao dictionaryDao;

	protected ServiceManager serviceManager;
	
	private <S extends AbstractService<?>> void setup(S service) {
		
		//mocks dependencies
		dictionaryDao = EasyMock.createMock(DictionaryDao.class);
		dictionaryDao.setSession(null);
		
		serviceManager = EasyMock.createMock(ServiceManager.class);
		serviceManager.setSession(null);
		serviceManager.setApplicationContext(null);

		//inject dependency into service
		service.setDictionaryDao(dictionaryDao);
		service.setServiceManager(serviceManager);
		serviceManager.setApplicationContext(null);
	}
	
	protected <S extends AbstractService<?>> S getService(Class<? extends AbstractService<?>> className) {
		S service = getInstance(className);
		setup(service);
		return service;
	}
	
	@SuppressWarnings("unchecked")
	protected <R, S extends AbstractService<?>> R executeService(S service) throws ServiceException {
		R result = (R) service.executeService(null);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private <S extends AbstractService<?>> S getInstance(Class<? extends AbstractService<?>> className) {
		try {
			return (S) className.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}


	
}
