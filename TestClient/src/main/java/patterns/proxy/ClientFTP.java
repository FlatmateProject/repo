package patterns.proxy;

public class ClientFTP implements ClientResource {

	@Override
	public int upload(byte[] dataPackage) {
		return dataPackage.length;
	}

	@Override
	public int download(byte[] dataPackage) {
		return dataPackage.length;
	}

}
