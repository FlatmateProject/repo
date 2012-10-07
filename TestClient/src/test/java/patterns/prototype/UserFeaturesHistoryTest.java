package patterns.prototype;

import org.jboss.logging.Logger;
import org.testng.annotations.Test;
import patterns.globalFactoryMethod.LANGUAGE;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserFeaturesHistoryTest {

	private static final Logger log = Logger.getLogger(UserFeaturesHistoryTest.class);

	@Test
	public void testShouldContainThreeFeaturesInCorrectOrder() {
		// given
		int i = 0;
		UserFeaturesHistory history = new UserFeaturesHistory();
		UserFeatureOrderState request = new UserFeatureOrderState(0L, "spoko", LANGUAGE.Polish);

		// when
		StandardUserFeature standard = StandardUserFeature.order(request);
		history.addFeature(standard);
		PremiumUserFeature premium = (PremiumUserFeature) standard.switchOption();
		history.addFeature(premium);
		StandardUserFeature standardAgain = (StandardUserFeature) premium.switchOption();
		history.addFeature(standardAgain);
		UserFeature[] exceptedFeatures = { standard, premium, standardAgain };

		// then
		assertNotNull(history);
		assertEquals(3, history.historySize());
		for (UserFeature feature : history.getFeatures()) {
			log.info("expected: " + exceptedFeatures[i]);
			log.info("actual:   " + feature);
			assertEquals(exceptedFeatures[i], feature);
			assertEquals(true, history.contains(feature));
			if (feature.isPremium()) {
				assertPremium(feature);
			} else {
				assertStandard(feature);
			}
			i++;
		}
	}

	private void assertPremium(UserFeature premium) {
		assertEquals(false, premium.isTransferLimit());
		assertEquals(UserFeature.PREMIUM_USER_DOWNLOAD_LIMIT, premium.getParallelDownloads());
		assertEquals(false, premium.isHaveToWait());
		assertEquals(false, premium.isDailyLimit());
		assertEquals(true, premium.isSslProtection());
	}

	private void assertStandard(UserFeature standard) {
		assertEquals(true, standard.isTransferLimit());
		assertEquals(1, standard.getParallelDownloads());
		assertEquals(true, standard.isHaveToWait());
		assertEquals(true, standard.isDailyLimit());
		assertEquals(false, standard.isSslProtection());
	}
}
