package patterns.proxy;

public interface ClientResource {

	int upload(byte[] dataPackage);

	int download(byte[] dataPackage);
}
