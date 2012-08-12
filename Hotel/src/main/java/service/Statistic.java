package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.Singleton;

public class Statistic {
	
	private static final Logger log = Logger.getLogger(Statistic.class);
	
	private double array[][];
	private Singleton db;
	private ResultSet resultQuery;
	private String resultText;
	private String query;
	private String monthTab[];

	public Statistic() throws SQLException {
		db = Singleton.getInstance();
		resultText = new String("");
		query = new String("");
		monthTab = new String[13];
		monthTab[1] = "stycze�";
		monthTab[2] = "luty";
		monthTab[3] = "marzec";
		monthTab[4] = "kwiecie�";
		monthTab[5] = "maj";
		monthTab[6] = "czerwiec";
		monthTab[7] = "lipiec";
		monthTab[8] = "sierpie�";
		monthTab[9] = "wrzesie�";
		monthTab[10] = "pa�dziernik";
		monthTab[11] = "listopad";
		monthTab[12] = "grudzie�";
	}

	public void finance(int i, int mFrom, int mTo, int yFrom, int yTo) {
		resultText = "";
		array = null;
		switch (i) {
		case 0:
			createMonthRaport(mFrom, mTo, yFrom);
			break;
		case 1:
			createYearRaport(yFrom, yTo);
			break;
		case 2:
			break;
		}
	}

	public void hotel(int i, int month, int year, String classRoom, String serve) {
		resultText = "";
		array = null;
		switch (i) {
		case 0:
			createClassRaport(month, year);
			break;
		case 1:
			createRoomRaport(month, year, classRoom);
			break;
		case 2:
			createServesRaport(month, year);
			break;
		case 3:
			createServeRaport(month, year, serve);
			break;
		}
	}

	public double[][] getArrayResult() {
		return array;
	}

	public String getTextResult() {
		return resultText;
	}

	private void createClassRaport(int month, int year) {
		int n = 0;
		int m = 0;
		int i = 0;
		try {
			query = "SELECT k.opis opis, count(r.id_rez) meldunki, sum((r.data_w-r.data_z)*k.cena) zysk ";
			query += "FROM rezerwacje r ";
			query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju  JOIN klasy k ON p.id_klasy=k.id_klasy ";
			query += "WHERE MONTH(r.data_w)=" + month + " and YEAR(r.data_w)="
					+ year + " GROUP BY k.id_klasy ORDER BY k.id_klasy";
			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][2];
				log.info("count row: " + n);// /
				resultText = "Raportu z wykorzystania klas pokoi za miesi�c "
						+ monthTab[month] + " w roku " + year + "\n";
				while (resultQuery.next()) {
					resultText += "   Klasa " + resultQuery.getString(1) + "("
							+ i + ").\n";
					m = resultQuery.getInt(2);
					array[i][0] = resultQuery.getFloat(3);
					resultText += "\tzyski: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					array[i][1] = array[i][0] / m;
					resultText += "\tliczba meldunk�w: " + m + "\n";
					resultText += "\tprzych�d jednostkowy: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zyski\n";
					resultText += " Slupek drugi przedstawia przych�d jednostkowy\n";
					resultText += " (na jedno zameldowanie)\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createRoomRaport(int month, int year, String classRoom) {
		int n = 0;
		int i = 0;
		int m = 0;
		try {
			query = "SELECT p.id_pokoju pokuj, count(r.id_rez) meldunki, sum((r.data_w-r.data_z)*k.cena) zysk ";
			query += "FROM rezerwacje r ";
			query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju  JOIN klasy k ON p.id_klasy=k.id_klasy ";
			query += "WHERE MONTH(r.data_w)=" + month + " and YEAR(r.data_w)="
					+ year + " and k.opis='" + classRoom + "' ";
			query += " GROUP BY p.id_pokoju ORDER BY p.id_pokoju";
			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][2];
				log.info("count row: " + n);// /
				resultText = "Raportu z wykorzystania pokoi z klasy "
						+ classRoom + " za miesi�c " + monthTab[month]
						+ " w roku " + year + "\n";
				while (resultQuery.next()) {
					resultText += "   Pok�j nr: " + resultQuery.getString(1)
							+ "(" + i + ").\n";
					m = resultQuery.getInt(2);
					array[i][0] = resultQuery.getFloat(3);
					resultText += "\tzyski: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					array[i][1] = array[i][0] / m;
					resultText += "\tliczba meldunk�w: " + m + "\n";
					resultText += "\tprzych�d jednostkowy: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zyski\n";
					resultText += " Slupek drugi przedstawia przych�d jednostkowy\n";
					resultText += " (na jedno zameldowanie)\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createServesRaport(int month, int year) {
		int n = 0;
		int m = 0;
		int i = 0;
		ResultSet resA = null;
		try {
			query = "SELECT u.typ, sum(rk.czas) czas, sum(rk.czas*u.cena) zysk ";
			query += "FROM rekreacja rk JOIN uslugi u ON rk.id_uslugi=u.id_uslugi ";
			query += "JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
			query += "WHERE MONTH(rz.data_w)=" + month
					+ " and YEAR(rz.data_w)=" + year + " GROUP BY u.typ";

			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][2];
				log.info("count row: " + n);// /
				resultText = "Raportu z wykorzystania us�ug  za miesi�c "
						+ monthTab[month] + " w roku " + year + "\n";
				while (resultQuery.next()) {
					resultText += "   Typ uslugi " + resultQuery.getString(1)
							+ "(" + i + ").\n";
					array[i][0] = resultQuery.getFloat(3);
					resultText += "\tsumaryczny czas: " + resultQuery.getInt(2)
							+ " godzin\n";
					resultText += "\tzysk: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					query = "SELECT count(*) ilosc_zameldowan FROM (SELECT count(rrk.id_rez) ilosc_rez ";
					query += "FROM rekreacja rrk JOIN uslugi uu ON rrk.id_uslugi=uu.id_uslugi JOIN rezerwacje ";
					query += "rrz ON rrk.id_rez=rrz.id_rez WHERE uu.typ='"
							+ resultQuery.getString(1) + "' ";
					query += "  GROUP BY rrk.id_rez ) tab";
					log.info("qurery(" + resultQuery.getString(1)
							+ "): " + query);
					resA = db.query(query);
					resA.next();
					m = resA.getInt(1);
					resultText += "\tliczba zameldowa�: " + m + "\n";
					array[i][1] = array[i][0] / m;
					resultText += "\tprzych�d jednostkowy: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zysk\n";
					resultText += " Slupek drugi przedstawia przych�d jednostkowy\n";
					resultText += " (na jedno zmaledowanie)\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createServeRaport(int month, int year, String serve) {
		int n = 0;
		int m = 0;
		int i = 0;
		ResultSet resA = null;
		try {
			query = "SELECT u.nazwa, sum(rk.czas) czas, sum(rk.czas*u.cena) zysk ";
			query += "FROM rekreacja rk JOIN uslugi u ON rk.id_uslugi=u.id_uslugi ";
			query += "JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
			query += "WHERE MONTH(rz.data_w)=" + month
					+ " and YEAR(rz.data_w)=" + year + " AND u.typ='" + serve
					+ "' ";
			query += "GROUP BY u.nazwa";
			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][2];
				resultText = "Raportu z wykorzystania us�ug  za miesi�c "
						+ monthTab[month] + " w roku " + year + "\n";
				while (resultQuery.next()) {
					resultText += "   Nazwa uslugi " + resultQuery.getString(1)
							+ "(" + i + ").\n";
					array[i][0] = resultQuery.getFloat(3);
					resultText += "\tsumaryczny czas: " + resultQuery.getInt(2)
							+ " godzin\n";
					resultText += "\tzysk: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					query = "SELECT count(*) ilosc_zameldowan FROM (SELECT count(rrk.id_rez) ilosc_rez ";
					query += "FROM rekreacja rrk JOIN uslugi uu ON rrk.id_uslugi=uu.id_uslugi JOIN rezerwacje ";
					query += "rrz ON rrk.id_rez=rrz.id_rez WHERE uu.nazwa='"
							+ resultQuery.getString(1) + "' ";
					query += "  GROUP BY rrk.id_rez ) tab";
					log.info("qurery(" + resultQuery.getString(1)
							+ "): " + query);
					resA = db.query(query);
					resA.next();
					m = resA.getInt(1);
					resultText += "\tliczba zameldowa�: " + m + "\n";
					array[i][1] = array[i][0] / m;
					resultText += "\tprzych�d jednostkowy: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zysk\n";
					resultText += " Slupek drugi przedstawia przych�d jednostkowy\n";
					resultText += " (na jedna osobe)\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createMonthRaport(int monthFrom, int monthTo, int year) {
		int n = 0;
		int i = 0;
		try {
			if (monthFrom > monthTo) {
				i = monthFrom;
				monthFrom = monthTo;
				monthTo = i;
			}
			i = 0;
			query = "SELECT MONTH(r.data_w) miesiac, sum((r.data_w-r.data_z)*k.cena) zysk_r, ";
			query += " ( SELECT sum(rk.czas*u.cena) FROM rekreacja rk JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
			query += "JOIN uslugi u ON rk.id_uslugi=u.id_uslugi WHERE MONTH(rz.data_w)=MONTH(r.data_w) ";
			query += ") zysk_u, ( SELECT sum(ka.wartosc) FROM kantor ka ";
			query += "WHERE MONTH(ka.data)=MONTH(r.data_w) ) zysk_k FROM rezerwacje r ";
			query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju JOIN klasy  k ON p.id_klasy=k.id_klasy ";
			query += "WHERE MONTH(r.data_w)>=" + monthFrom
					+ " AND MONTH(r.data_w)<=" + monthTo + " ";
			query += " AND YEAR(r.data_w)=" + year
					+ " GROUP BY MONTH(r.data_w)";
			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][4];
				if (monthFrom == monthTo)
					resultText = "Raportu zysk�w w miesi�cu "
							+ monthTab[monthFrom] + "\n";
				else
					resultText = "Raportu zysk�w w miesi�cach od "
							+ monthTab[monthFrom] + " do " + monthTab[monthTo]
							+ "\n";
				while (resultQuery.next()) {
					resultText += "   Miesiac "
							+ monthTab[resultQuery.getInt(1)] + "(" + i
							+ ").\n";
					array[i][0] = resultQuery.getDouble(2);
					resultText += "\tzysk z rezerwacji: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					array[i][1] = resultQuery.getDouble(3);
					resultText += "\tzysk z uslug: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					array[i][2] = resultQuery.getDouble(4);
					resultText += "\tzysk z kantoru: "
							+ String.format("%.2f", array[i][2]) + "z�\n";
					array[i][3] = array[i][0] + array[i][1] + array[i][2];
					resultText += "\tzysk sumaryczny: "
							+ String.format("%.2f", array[i][3]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zysk z rezerwacji\n";
					resultText += " Slupek drugi przedstawia zysk z uslug\n";
					resultText += " Slupek trzeci przedstawia zysk z kantoru\n";
					resultText += " Slupek czwarty przedstawia zysk sumaryczny\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createYearRaport(int yearFrom, int yearTo) {
		int n = -1;
		int i = 0;
		try {
			if (yearFrom > yearTo) {
				i = yearFrom;
				yearFrom = yearTo;
				yearTo = i;
			}
			i = 0;
			query = "SELECT YEAR(r.data_w) miesiac, sum((r.data_w-r.data_z)*k.cena) zysk_r, ";
			query += " ( SELECT sum(rk.czas*u.cena) FROM rekreacja rk JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
			query += "JOIN uslugi u ON rk.id_uslugi=u.id_uslugi WHERE YEAR(rz.data_w)=YEAR(r.data_w) ";
			query += ") zysk_u, ( SELECT sum(ka.wartosc) FROM kantor ka ";
			query += "WHERE YEAR(ka.data)=YEAR(r.data_w) ) zysk_k FROM rezerwacje r ";
			query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju JOIN klasy  k ON p.id_klasy=k.id_klasy ";
			query += "WHERE YEAR(r.data_w)>=" + yearFrom
					+ " AND YEAR(r.data_w)<=" + yearTo + " ";
			query += "GROUP BY YEAR(r.data_w)";
			log.info("qurery: " + query);
			resultQuery = db.query(query);
			if (resultQuery != null) {
				resultQuery.last();
				n = resultQuery.getRow();
				resultQuery.beforeFirst();
				if (n != 0)
					array = new double[n][4];
				if (yearFrom == yearTo)
					resultText = "Raportu zysk�w w roku " + yearFrom + "\n";
				else
					resultText = "Raportu zysk�w w latach od " + yearFrom
							+ " do " + yearTo + "\n";
				while (resultQuery.next()) {

					resultText += "   Rok " + resultQuery.getString(1) + "("
							+ n + ").\n";
					array[i][0] = resultQuery.getDouble(2);
					resultText += "\tzysk z rezerwacji: "
							+ String.format("%.2f", array[i][0]) + "z�\n";
					array[i][1] = resultQuery.getDouble(3);
					resultText += "\tzysk z uslug: "
							+ String.format("%.2f", array[i][1]) + "z�\n";
					array[i][2] = resultQuery.getDouble(4);
					resultText += "\tzysk z kantoru: "
							+ String.format("%.2f", array[i][2]) + "z�\n";
					array[i][3] = array[i][0] + array[i][1] + array[i][2];
					resultText += "\tzysk sumaryczny: "
							+ String.format("%.2f", array[i][3]) + "z�\n";
					i++;
				}
				if (n != 0) {
					resultText += "Legenda \n";
					resultText += " Slupek pierwsz przedstawia zysk z rezerwacji\n";
					resultText += " Slupek drugi przedstawia zysk z uslug\n";
					resultText += " Slupek trzeci przedstawia zysk z kantoru\n";
					resultText += " Slupek czwarty przedstawia zysk sumaryczny\n";
				}
			} else
				resultText += "Wyst�pi� b�ad bazy danych\n";
			if (n == 0)
				resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
