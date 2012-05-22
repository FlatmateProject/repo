package patterns.prototype;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserFeaturesHistoryTest {

	@Test
	public void testShouldContainThreeFeaturesInCorrectOrder(){
		//given
		long userId = 0;
		int i = 0;
		UserFeaturesHistory history = new UserFeaturesHistory();
		
		//when
		StandardUserFeature standard = StandardUserFeature.order(userId);
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
			assertEquals(exceptedFeatures[i].getParralelDownload(), feature.getParralelDownload());
			assertEquals(exceptedFeatures[i].isDailyLimit(), feature.isDailyLimit());
			assertEquals(exceptedFeatures[i].isHaveToWait(), feature.isHaveToWait());
			assertEquals(exceptedFeatures[i].isSslProtection(), feature.isSslProtection());
			assertEquals(exceptedFeatures[i].isTransferLimit(), feature.isTransferLimit());
			assertTrue(history.contains(feature));
			i++;
		}
	}
}
