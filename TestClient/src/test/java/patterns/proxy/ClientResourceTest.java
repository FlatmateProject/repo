package patterns.proxy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

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
	public void shouldUploadData(ClientResource resource, int expectedPackageSize, int packageSizeInMB) {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.upload(dataPackage);

		// then
		assertThat(resultPackageSize).isEqualTo(expectedPackageSize);
	}

	@Test(dataProvider = "feedData")
	public void shouldDownloadData(ClientResource resource, int expectedPackageSize, int packageSizeInMB) {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.download(dataPackage);

		// then
        assertThat(resultPackageSize).isEqualTo(expectedPackageSize);
    }
}
