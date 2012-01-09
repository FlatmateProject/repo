package service;


public interface AbstractProvider {
	
	public abstract <T> T getInstance(Class<?> serviceName) throws Exception;

	public abstract <T> T execute(AbstractService<?> service) throws Exception;

}
