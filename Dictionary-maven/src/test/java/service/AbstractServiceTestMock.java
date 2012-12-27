package service;

import dao.ClientDao;
import dao.DictionaryDao;
import exception.ServiceException;
import manager.ServiceManager;

public abstract class AbstractServiceTestMock {

	private MockAnnotationParser annotationParser = new MockAnnotationParser();

	@Mock
	protected DictionaryDao dictionaryDao;

	@Mock
	protected ClientDao clientDao;
	
	@Mock
	protected ServiceManager serviceManager;

	private <S extends AbstractService<?>> void setup(S service) {

		createReflectionMocks();

		// mocks dependencies
		dictionaryDao.setSession(null);

		clientDao.setSession(null);
		
		serviceManager.setSession(null);
		serviceManager.setApplicationContext(null);

		// inject dependency into service
		service.setDictionaryDao(dictionaryDao);
		service.setClientDao(clientDao);
		service.setServiceManager(serviceManager);
	}

	protected <S extends AbstractService<?>> S getService(Class<? extends AbstractService<?>> className) {
		S service = getInstance(className);
		setup(service);
		return service;
	}

	@SuppressWarnings("unchecked")
	protected <R, S extends AbstractService<?>> R executeService(S service)	throws ServiceException {
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

	private void createReflectionMocks() {
		try {
			annotationParser.parse(getClass(), this);
			annotationParser.parse(this.getClass().getSuperclass(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
