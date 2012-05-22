package patterns.prototype;

public abstract class UserFeature {

	public static final long PREMIUM_USER_DOWNLOAD_LIMIT = 50;

	protected long userId;
	
	protected boolean transferLimit;
	
	protected long parralelDownload;
	
	protected boolean haveToWait;
	
	protected boolean dailyLimit;
	
	protected boolean sslProtection;

	public abstract UserFeature switchOption();
	
	public long getUserId() {
		return userId;
	}

	public boolean isTransferLimit() {
		return transferLimit;
	}

	public long getParralelDownload() {
		return parralelDownload;
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
}
