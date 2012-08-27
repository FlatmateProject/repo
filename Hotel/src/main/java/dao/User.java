package dao;

import java.util.GregorianCalendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class User {

	private long idUser;

	private String email;

	private String password;

	private int authorization;

	private GregorianCalendar date_b;

	private GregorianCalendar date_in;

	private String contact;

	private String sex;

	public User() {

	}

	public long getIdUser() {
		return idUser;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getAuthorization() {
		return authorization;
	}

	public GregorianCalendar getDate_b() {
		return date_b;
	}

	public GregorianCalendar getDate_in() {
		return date_in;
	}

	public String getContact() {
		return contact;
	}

	public String getSex() {
		return sex;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
