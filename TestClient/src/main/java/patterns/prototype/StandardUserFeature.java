package patterns.prototype;

public class StandardUserFeature extends UserFeature {

	private StandardUserFeature() {
		this.transferLimit = true;
		this.parralelDownloads = 1;
		this.haveToWait = true;
		this.dailyLimit = true;
		this.sslProtection = false;
		this.option = OPTION.STANDARD;
	}
	
	public static StandardUserFeature order(UserFeatureOrderState state){
		StandardUserFeature feature = new StandardUserFeature();
		feature.saveState(state);
		return feature;
	}

	@Override
	public UserFeature switchOption() {
		return PremiumUserFeature.order(buildUserFeatureOrderState());
	}
}
