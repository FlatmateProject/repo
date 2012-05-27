package patterns.prototype;

import patterns.globalFactoryMethod.LANGUAGE;

public abstract class UserFeature {

	public static final long PREMIUM_USER_DOWNLOAD_LIMIT = 50;

	protected long    userId;

	protected String  accountName;
	
	protected LANGUAGE  language;

	protected boolean transferLimit;

	protected long    parralelDownloads;

	protected boolean haveToWait;

	protected boolean dailyLimit;

	protected boolean sslProtection;
	
	protected OPTION  option;
	
	public abstract UserFeature switchOption();
	
	protected void saveState(UserFeatureOrderState request){
		this.userId = request.getUserId();
		this.accountName = request.getAccountName();
		this.language = request.getLanguage();
	}
	
	protected UserFeatureOrderState buildUserFeatureOrderState(){
		return new UserFeatureOrderState(userId, accountName, language);
	}
	
	public long getUserId() {
		return userId;
	}

	public boolean isTransferLimit() {
		return transferLimit;
	}

	public long getParralelDownloads() {
		return parralelDownloads;
	}

	public boolean isHaveToWait() {
		return haveToWait;
	}

	public boolean isDailyLimit() {
		return dailyLimit;
	}

	public boolean isSslProtection() {
		return sslProtection;
	}

	public String getAccountName() {
		return accountName;
	}

	public OPTION getOption() {
		return option;
	}
	
	public boolean isStandard() {
		return option.equals(OPTION.STANDARD);
	}
	
	public boolean isPremium() {
		return option.equals(OPTION.PREMIUM);
	}
	
	public boolean isOption(OPTION option) {
		return this.option.equals(option);
	}

	public LANGUAGE getLanguage() {
		return language;
	}
}
