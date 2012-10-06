package patterns.proxy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ClientResourceTest {

	@DataProvider
	public static Object[][] feedData() {
		return new Object[][] {//
				{ new ClientFTP(), 10, 10 },//
				{ new ClientFTP(), 10000, 10000 },//
				{ new ClientFTPProxy(), 10, 10 },//
				{ new ClientFTPProxy(), 0, 1000 }//
				};
	}

	@Test(dataProvider = "feedData")
	public void shoulUploadData(ClientResource resource, int expactedPackageSize, int packageSizeInMB) {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.upload(dataPackage);

		// then
		assertEquals(expactedPackageSize, resultPackageSize);
	}

	@Test(dataProvider = "feedData")
	public void shoulDownloadData(ClientResource resource, int expactedPackageSize, int packageSizeInMB) {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.download(dataPackage);

		// then
		assertEquals(expactedPackageSize, resultPackageSize);
	}
}
