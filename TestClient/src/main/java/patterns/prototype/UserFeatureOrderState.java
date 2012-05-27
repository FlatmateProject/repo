package patterns.prototype;

import patterns.globalFactoryMethod.LANGUAGE;

public class UserFeatureOrderState {

	private long userId;
	
	private String accountName;

	private LANGUAGE  language;
	
	public UserFeatureOrderState(long userId, String accountName, LANGUAGE language) {

		this.userId = userId;
		this.accountName = accountName;
		this.language = language;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public LANGUAGE getLanguage() {
		return language;
	}

	public void setLanguage(LANGUAGE language) {
		this.language = language;
	}
	

}
