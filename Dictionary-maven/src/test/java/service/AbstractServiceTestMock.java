package service;

import dao.ClientDao;
import dao.DictionaryDao;
import exception.ServiceException;
import manager.ServiceManager;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public abstract class AbstractServiceTestMock {

	@Mock
	protected DictionaryDao dictionaryDao;

	@Mock
	protected ClientDao clientDao;

	@Mock
	protected ServiceManager serviceManager;

	private <S extends AbstractService<?>> void setup(S service) {
        initMocks(this);
        injectDependencyIntoService(service);
	}

    private <S extends AbstractService<?>> void injectDependencyIntoService(S service) {
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
}
