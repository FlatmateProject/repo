package patterns.prototype;

public class StandardUserFeature extends UserFeature {

	private StandardUserFeature(){
		
	}
	
	public static StandardUserFeature order(long userId){
		StandardUserFeature feature = new StandardUserFeature();
		feature.userId = userId;
		setupStandard(feature);
		return feature;
	}

	@Override
	public UserFeature switchOption() {
		PremiumUserFeature feature = PremiumUserFeature.order(this.userId);
		return feature;
	}
	
	private static void setupStandard(UserFeature feature) {
		feature.transferLimit = true;
		feature.parralelDownload = 1;
		feature.haveToWait = true;
		feature.dailyLimit = false;
		feature.sslProtection = false;
	}
}
