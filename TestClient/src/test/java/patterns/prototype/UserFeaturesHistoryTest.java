package patterns.prototype;

import static org.junit.Assert.*;

import org.junit.Test;

import patterns.globalFactoryMethod.LANGUAGE;

public class UserFeaturesHistoryTest {

	@Test
	public void testShouldContainThreeFeaturesInCorrectOrder(){
		//given
		int i = 0;
		long userId = 0;
		String accountName = "spoko";
		LANGUAGE language = LANGUAGE.Polish;
		UserFeaturesHistory history = new UserFeaturesHistory();

		//when
		UserFeatureOrderState request = new UserFeatureOrderState(userId, accountName, language);
		
		StandardUserFeature standard = StandardUserFeature.order(request);
		history.addFeature(standard);
		PremiumUserFeature premium = (PremiumUserFeature) standard.switchOption();
		history.addFeature(premium);
		StandardUserFeature standardAgain = (StandardUserFeature) premium.switchOption(); 
		history.addFeature(standardAgain);
		UserFeature[] exceptedFeatures = {standard, premium, standardAgain};
		
		//then
		assertNotNull(history);
		assertEquals(3, history.historySize());
		for(UserFeature feature : history.getFeatures()){
			assertEquals(userId, feature.getUserId());
			assertEquals(accountName, feature.getAccountName());
			assertEquals(language, feature.getLanguage());
			assertEquals(exceptedFeatures[i].getParralelDownloads(), feature.getParralelDownloads());
			assertEquals(exceptedFeatures[i].isDailyLimit(), feature.isDailyLimit());
			assertEquals(exceptedFeatures[i].isHaveToWait(), feature.isHaveToWait());
			assertEquals(exceptedFeatures[i].isSslProtection(), feature.isSslProtection());
			assertEquals(exceptedFeatures[i].isTransferLimit(), feature.isTransferLimit());
			assertTrue(history.contains(feature));
			if (feature.isPremium()) {
				assertPremium(feature);
			} else {
				assertStandard(feature);
			}
			i++;
		}
	}
	
	private void assertPremium(UserFeature premium){
		assertEquals(false, premium.isTransferLimit());
		assertEquals(UserFeature.PREMIUM_USER_DOWNLOAD_LIMIT, premium.getParralelDownloads());
		assertEquals(false, premium.isHaveToWait());
		assertEquals(false, premium.isDailyLimit());
		assertEquals(true, premium.isSslProtection());
	}
	
	private void assertStandard(UserFeature standard){
		assertEquals(true, standard.isTransferLimit());
		assertEquals(1, standard.getParralelDownloads());
		assertEquals(true, standard.isHaveToWait());
		assertEquals(true, standard.isDailyLimit());
		assertEquals(false, standard.isSslProtection());
	}
}
