package patterns.proxy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ClientResourceTest {

	private ClientResource resource;

	private int expactedPackageSize;

	private int packageSizeInMB;

	public ClientResourceTest(ClientResource resource, int expactedPackageSize, int packageSizeInMB) {
		this.resource = resource;
		this.expactedPackageSize = expactedPackageSize;
		this.packageSizeInMB = packageSizeInMB;
	}

	@Parameters
	public static List<Object[]> feedData() {
		Object[][] datas = new Object[][] {//
				{ new ClientFTP(), 10, 10 },//
				{ new ClientFTP(), 10000, 10000 },//
				{ new ClientFTPProxy(), 10, 10 },//
				{ new ClientFTPProxy(), 0, 1000 }//
				};
		return Arrays.asList(datas);
	}

	@Test
	public void shoulUploadData() {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.upload(dataPackage);

		// then
		assertEquals(expactedPackageSize, resultPackageSize);
	}

	@Test
	public void shoulDownloadData() {
		// given
		byte[] dataPackage = new byte[packageSizeInMB];

		// when
		int resultPackageSize = resource.download(dataPackage);

		// then
		assertEquals(expactedPackageSize, resultPackageSize);
	}
}
