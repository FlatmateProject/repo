package patterns.prototype;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()//
				.append(userId)//
				.append(accountName)//
				.append(language)//
				.append(transferLimit)//
				.append(parralelDownloads)//
				.append(haveToWait)//
				.append(dailyLimit)//
				.append(sslProtection)
				.append(option)
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj instanceof UserFeature == false){
			return false;
		}
		UserFeature object = (UserFeature) obj;
		return new EqualsBuilder()//
		.append(userId, object.userId)//
		.append(accountName, object.accountName)//
		.append(language, object.language)//
		.append(transferLimit, object.transferLimit)//
		.append(parralelDownloads, object.parralelDownloads)//
		.append(haveToWait, object.haveToWait)//
		.append(dailyLimit, object.dailyLimit)//
		.append(sslProtection, object.sslProtection)
		.append(option, object.option)//
		.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
