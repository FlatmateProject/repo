package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

public class Cantor {

	private Singleton sing = Singleton.getInstance();
	private ResultSet rset1, rset2, rset3, rset4, rset5, rset6, change1,
			change2;

	public Cantor() {

	}

	public JTable createCurrTable() {
		try {
			int i = 0, j = 0, cols, rows;
			rset1 = sing.query("show columns from hotel.waluty;");
			rset2 = sing.query("select * from waluty;");
			rset1.last();
			cols = rset1.getRow();
			rset2.last();
			rows = rset2.getRow();
			Object rowData[][] = new Object[rows][cols];
			String columnNames[] = new String[cols];
			rset1.first();
			rset2.first();
			do {
				columnNames[i] = rset1.getString(1);
				i++;
			} while (rset1.next());
			i = 0;
			do {
				for (j = 0; j < cols; j++) {
					rowData[i][j] = rset2.getString(j + 1);
				}
				i++;
			} while (rset2.next());
			JTable t = new JTable(rowData, columnNames);
			t.setFillsViewportHeight(true);
			return t;
		} catch (Exception e) {
			System.out.println("Brak danych");
			Object rowData[][] = { { "Brak danych" } };
			String columnNames[] = { "Brak danych" };
			return new JTable(rowData, columnNames);
		}
	}

	public JTable createClientTable(String s1) {
		try {
			int i = 0, j = 0, cols, rows;
			rset3 = sing.query("show columns from hotel.klienci");
			rset4 = sing.query("select * from hotel.klienci" + s1);
			rset3.last();
			cols = rset3.getRow();
			rset4.last();
			rows = rset4.getRow();
			Object rowData[][] = new Object[rows][cols];
			String columnNames[] = new String[cols];
			rset3.first();
			rset4.first();
			do {
				columnNames[i] = rset3.getString(1);
				i++;
			} while (rset3.next());
			i = 0;
			do {
				for (j = 0; j < cols; j++) {
					rowData[i][j] = rset4.getString(j + 1);
				}
				i++;
			} while (rset4.next());
			JTable t = new JTable(rowData, columnNames);
			t.setFillsViewportHeight(true);
			return t;
		} catch (Exception e) {
			return new JTable();
		}
	}

	public JTable createCompTable(String s1) {
		try {
			int i = 0, j = 0, cols, rows;
			rset5 = sing.query("show columns from hotel.firmy");
			rset6 = sing.query("select * from hotel.firmy" + s1);
			rset5.last();
			cols = rset5.getRow();
			rset6.last();
			rows = rset6.getRow();
			Object rowData[][] = new Object[rows][cols];
			String columnNames[] = new String[cols];
			rset5.first();
			rset6.first();
			do {
				columnNames[i] = rset5.getString(1);
				i++;
			} while (rset5.next());
			i = 0;
			do {
				for (j = 0; j < cols; j++) {
					rowData[i][j] = rset6.getString(j + 1);
				}
				i++;
			} while (rset6.next());
			JTable t = new JTable(rowData, columnNames);
			t.setFillsViewportHeight(true);
			return t;
		} catch (Exception e) {
		}
		return null;
	}

	public String trimInput(String input) {
		return input.replaceAll("\\D*", "");
	}

	public boolean isPesel(String pesel) {
		pesel = trimInput(pesel);
		int psize = pesel.length();
		if (psize != 11) {
			return false;
		}
		int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
		int j = 0, sum = 0, control = 0;
		int csum = new Integer(pesel.substring(psize - 1)).intValue();
		for (int i = 0; i < psize - 1; i++) {
			char c = pesel.charAt(i);
			j = new Integer(String.valueOf(c)).intValue();
			sum += j * weights[i];
		}
		control = 10 - (sum % 10);
		if (control == 10) {
			control = 0;
		}
		return (control == csum);
	}

	public boolean isKRS(String krs) {
		if (krs.length() != 10)
			return false;
		for (int i = 0; i < krs.length(); ++i) {
			if (krs.charAt(i) <= '0' || krs.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	public float[] changeCalc(String s1, String s2, float much) {
		float curr1 = 0, curr2 = 0, curr3 = 0, cost = 0, temp = 0, temp1 = 0, temp2 = 0;
		float resulRet[] = new float [3];
		try {
			change1 = sing.query("select * from hotel.waluty where NAZWA="
					+ "'" + s1 + "'");
			change1.first();
			curr1 = change1.getFloat("CENA_KU");
			curr2 = change1.getFloat("CENA_SP");

			change2 = sing.query("select * from hotel.waluty where NAZWA="
					+ "'" + s2 + "'");
			change2.first();
			curr3 = change2.getFloat("CENA_SP");
			if (s1 == "PLN") {
				cost = much * curr3;
			} else if (s2 == "PLN") {
				cost = much * curr1;
			} else {
				temp = much * curr1;
				temp1 = temp / curr2;
				temp2 = temp1 * curr1;
				cost = temp2 / curr3;
				//
				System.out.println("Dostaniesz: " + cost + ", zysk: "
						+ (temp - temp2));
				resulRet[0] = much;
				resulRet[1] = cost;
				resulRet[2] = temp - temp2; 
			}

		} catch (Exception e) {
			System.out.println("Brak danych");
		}
		return resulRet;
	}

	public boolean changeMoney(boolean whatIs, String id, String data,
			String cur1, String cur2, float val1, float val2, float profit) {
		float number = 0, newNumber = 0;
		try {

			change1 = sing.query("select * from hotel.waluty where NAZWA="
					+ "'" + cur1 + "'");
			change2 = sing.query("select * from hotel.waluty where NAZWA="
					+ "'" + cur2 + "'");
			change1.first();
			change2.first();
			number = change2.getFloat("ILOSC");
			if (val2 > number)
				return false;
			else {
				if (whatIs) {
					sing
							.queryUp("insert into hotel.kantor (IDK_PESEL, DATA, WALUTA_1, WALUTA_2, WARTOSC_1, WARTOSC_2, ZYSK) VALUES("
									+ id
									+ ", "
									+ "'"
									+ data
									+ "'"
									+ ", "
									+ "'"
									+ cur1
									+ "'"
									+ ", "
									+ "'"
									+ cur2
									+ "'"
									+ ", "
									+ val1
									+ ", "
									+ val2
									+ ", "
									+ profit
									+ ")");

				} else {
					sing
							.queryUp("insert into hotel.kantor (IDF_KRS, DATA, WALUTA_1, WALUTA_2, WARTOSC_1, WARTOSC_2, ZYSK) VALUES("
									+ id
									+ ", "
									+ "'"
									+ data
									+ "'"
									+ ", "
									+ "'"
									+ cur1
									+ "'"
									+ ", "
									+ "'"
									+ cur2
									+ "'"
									+ ", "
									+ val1
									+ ", "
									+ val2
									+ ", "
									+ profit
									+ ")");
				}
				change1.first();
				number = change1.getFloat("ILOSC");
				newNumber = number + val1;
				sing.queryUp("update hotel.waluty set ILOSC=" + (int) newNumber
						+ " where NAZWA = '" + cur1 + "';");
				change2.first();
				number = change2.getFloat("ILOSC");
				newNumber = number - val2;
				sing.queryUp("update hotel.waluty set ILOSC=" + (int) newNumber
						+ " where NAZWA = '" + cur2 + "';");
			}

		} catch (SQLException e) {
			System.out.println("Brak danych");
		}
		return true;
	}
	
	public String ShowDate() {
				Date show;
				String show1;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                show =  new Date();
                show1 = df.format(show);
                return show1;
}
	
}
