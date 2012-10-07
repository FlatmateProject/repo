package patterns.proxy;

public class ClientFTPProxy implements ClientResource {

	private final int maxSizeUploadedPackageInMB = 1000;
	
	private final int maxSizeDownloadedPackageInMB = 1000;
	
	private ClientFTP clientFTP;
	
	@Override
	public int upload(byte[] dataPackage) {
		if(dataPackage.length < maxSizeUploadedPackageInMB){
			clientFTP = new ClientFTP();
			return clientFTP.upload(dataPackage);
		}
		return 0;
	}

	@Override
	public int download(byte[] dataPackage) {
		if(dataPackage.length < maxSizeDownloadedPackageInMB){
			clientFTP = new ClientFTP();
			return clientFTP.download(dataPackage);
		}
		return 0;
	}

}
