package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Singleton {

	private ResultSet rset;
	private static Singleton instance = null;
	private static Connection con;
	private Statement stmt = null;

	public static Singleton getInstance() {
		synchronized (Singleton.class) {
			if (instance == null) {
				instance = new Singleton();
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
				} catch (Exception e) {
					System.err.println(e.getLocalizedMessage()
							+ "\nBrak po��czenia z baz� danych!");
				}
			}
		}
		return instance;
	}

	public ResultSet query(String s) {
		try {
			stmt = (Statement) con.createStatement();
			rset = stmt.executeQuery(s);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean queryUp(String s) {
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(s);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
