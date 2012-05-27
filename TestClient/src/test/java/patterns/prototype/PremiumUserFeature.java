package patterns.prototype;

public class PremiumUserFeature extends UserFeature{

	private PremiumUserFeature(){
	
	}

	public static PremiumUserFeature order(long userId){
		PremiumUserFeature feature = new PremiumUserFeature();
		feature.userId = userId;
		setupPremium(feature);
		return feature;
	}
	
	@Override
	public UserFeature switchOption() {
		StandardUserFeature feature = StandardUserFeature.order(this.userId);
		return feature;
	}
	
	private static void setupPremium(UserFeature feature) {
		feature.transferLimit = false;
		feature.parralelDownload = PREMIUM_USER_DOWNLOAD_LIMIT;
		feature.haveToWait = false;
		feature.dailyLimit = false;
		feature.sslProtection = true;
	}
}
