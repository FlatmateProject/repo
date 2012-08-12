package service;

import java.sql.ResultSet;

public class Singleton {

	private static Singleton instance = null;

	public static Singleton getInstance() {
		synchronized (Singleton.class) {
			if (instance == null) {
				// mysql://localhost:3306/hotel", "root", ""
				instance = new Singleton();
			}
		}
		return instance;
	}

	public ResultSet query(String s) {
		return null;
	}

	public Boolean queryUp(String s) {
		return null;
	}

}
