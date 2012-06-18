package repo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

	private Singleton sing = Singleton.getInstance();
	private ResultSet rset;

	public Boolean check(String login, char[] pass) {
		System.out.println(login + " " + String.valueOf(pass));
		try {
			rset = sing.query("select haslo from pracownicy where idp_pesel = \"" + login + "\"");
			rset.next();
			return String.valueOf(pass).equals(rset.getString(1));
		} catch (SQLException e) {
			return false;
		}
	}

}