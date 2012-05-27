package patterns.prototype;

public class PremiumUserFeature extends UserFeature {

	private PremiumUserFeature() {
		this.transferLimit = false;
		this.parralelDownloads = PREMIUM_USER_DOWNLOAD_LIMIT;
		this.haveToWait = false;
		this.dailyLimit = false;
		this.sslProtection = true;
		this.option = OPTION.PREMIUM;
	}

	public static PremiumUserFeature order(UserFeatureOrderState state){
		PremiumUserFeature feature = new PremiumUserFeature();
		feature.saveState(state);
		return feature;
	}
	
	@Override
	public UserFeature switchOption() {
		return  StandardUserFeature.order(buildUserFeatureOrderState());
	}
}
