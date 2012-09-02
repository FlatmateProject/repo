package service.statictic;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import service.dictionary.MONTH;
import service.statictic.executors.RaportDetails;
import service.statictic.executors.RaportExecutor;
import service.statictic.templates.RaportTemplateBuilder;
import dao.Singleton;
import dao.StaticticDao;
import dao.StaticticDaoImpl;

public class Statistic {

	private static final Logger log = Logger.getLogger(Statistic.class);

	private final String DATABASE_ERROR = "Wystpił bład bazy danych\n";
	
	private double array[][];
	private Singleton db;
	private String resultText;

	private StaticticDao staticticDao;

	private StatisticRaport raport;
	
	public Statistic() {
		staticticDao = new StaticticDaoImpl();
	}

	public StatisticRaport finance(RAPORT_KIND raportKind, int mFrom, int mTo, int yFrom, int yTo) {
		try {
			resultText = "";
			array = null;
			raport = null;
			switch (raportKind) {
			case FINANCE_MONTH : createMonthRaport(mFrom, mTo, yFrom);break;//
			case FINANCE_YEAR  : createYearRaport(yFrom, yTo);break;//
			}
			if(raport == null){
				raport = new StatisticRaport(array, resultText);
			}
			return raport;
			} catch (Exception e) {
				e.printStackTrace();
				return null;	
			}
	}

	public StatisticRaport hotel(RAPORT_KIND raportKind, int month, int year, String classRoom, String serve){
		try {
			RaportExecutor executor = raportKind.getRaportExecutor();
			RaportTemplateBuilder temlpateBuilder = raportKind.getRaportTemplateBuilder();
			RaportDetails raportDetails = new RaportDetails(MONTH.getMonth(month), year, classRoom, serve);
			
			executor.setup(raportDetails);
			executor.injectStaticticDao(staticticDao);
			return executor.createRaport(temlpateBuilder);
//			switch (raportKind) {
//			case HOTEL_SERVICES      : createServesRaport(month, year);break;//
//			case HOTEL_SERVICE       : createServeRaport(month, year, serve);break;//

		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}

	private void createServesRaport(int month, int year) throws SQLException {
		int n = 0;
		int i = 0;
		ResultSet resultQuery = staticticDao.createServesRaport(month, year);
		resultQuery.last();
		n = resultQuery.getRow();
		resultQuery.beforeFirst();
		if (n != 0)
			array = new double[n][2];
		log.info("count row: " + n);// /
		resultText = "Raportu z wykorzystania us�ug  za miesi�c "+ MONTH.getMonthName(month) + " w roku " + year + "\n";
		while (resultQuery.next()) {
			resultText += "   Typ uslugi " + resultQuery.getString(1) + "(" + i	+ ").\n";
			array[i][0] = resultQuery.getFloat(3);
			resultText += "\tsumaryczny czas: " + resultQuery.getInt(2)	+ " godzin\n";
			resultText += "\tzysk: " + String.format("%.2f", array[i][0])+ "z�\n";
		
			int useNumber = staticticDao.countUseNumberForServe(resultQuery.getString(1));
			resultText += "\tliczba zameldowa�: " + useNumber + "\n";
			array[i][1] = array[i][0] / useNumber;
			resultText += "\tprzych�d jednostkowy: " + String.format("%.2f", array[i][1]) + "z�\n";
			i++;
		}
		if (n != 0) {
			resultText += "Legenda \n";
			resultText += " Slupek pierwsz przedstawia zysk\n";
			resultText += " Slupek drugi przedstawia przychód jednostkowy\n";
			resultText += " (na jedno zmaledowanie)\n";
		}

		if (n == 0)
			resultText += "W danym miesi�cu nie wprowadzano �adnych danych za zakresu.\n";
	}

	private void createServeRaport(int month, int year, String serve)
			throws SQLException {
		int n = 0;
		int m = 0;
		int i = 0;
		ResultSet resA = null;
		ResultSet resultQuery = staticticDao.createServeRaport(month, year,
				serve);
		if (resultQuery != null) {
			resultQuery.last();
			n = resultQuery.getRow();
			resultQuery.beforeFirst();
			if (n != 0)
				array = new double[n][2];
			resultText = "Raportu z wykorzystania us�ug  za miesi�c "
					+ MONTH.getMonthName(month) + " w roku " + year + "\n";
			while (resultQuery.next()) {
				resultText += "   Nazwa uslugi " + resultQuery.getString(1)
						+ "(" + i + ").\n";
				array[i][0] = resultQuery.getFloat(3);
				resultText += "\tsumaryczny czas: " + resultQuery.getInt(2)
						+ " godzin\n";
				resultText += "\tzysk: " + String.format("%.2f", array[i][0])
						+ "z�\n";
				String query2 = "SELECT count(*) ilosc_zameldowan FROM (SELECT count(rrk.id_rez) ilosc_rez ";
				query2 += "FROM rekreacja rrk JOIN uslugi uu ON rrk.id_uslugi=uu.id_uslugi JOIN rezerwacje ";
				query2 += "rrz ON rrk.id_rez=rrz.id_rez WHERE uu.nazwa='"
						+ resultQuery.getString(1) + "' ";
				query2 += "  GROUP BY rrk.id_rez ) tab";
				log.info("qurery(" + resultQuery.getString(1) + "): " + query2);
				resA = db.query(query2);
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
	}

	private void createMonthRaport(int monthFrom, int monthTo, int year)
			throws SQLException {
		int n = 0;
		int i = 0;
		if (monthFrom > monthTo) {
			i = monthFrom;
			monthFrom = monthTo;
			monthTo = i;
		}
		i = 0;

		ResultSet resultQuery = staticticDao.createMonthRaport(monthFrom,
				monthTo, year);
		if (resultQuery != null) {
			resultQuery.last();
			n = resultQuery.getRow();
			resultQuery.beforeFirst();
			if (n != 0)
				array = new double[n][4];
			if (monthFrom == monthTo)
				resultText = "Raportu zysk�w w miesi�cu "
						+ MONTH.getMonthName(monthFrom) + "\n";
			else
				resultText = "Raportu zysk�w w miesi�cach od "
						+ MONTH.getMonthName(monthFrom) + " do "
						+ MONTH.getMonthName(monthTo) + "\n";
			while (resultQuery.next()) {
				resultText += "   Miesiac "
						+ MONTH.getMonthName(resultQuery.getInt(1)) + "(" + i
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
	}

	private void createYearRaport(int yearFrom, int yearTo) throws SQLException {
		int n = -1;
		int i = 0;

		if (yearFrom > yearTo) {
			i = yearFrom;
			yearFrom = yearTo;
			yearTo = i;
		}
		i = 0;

		ResultSet resultQuery = staticticDao.createYearRaport(yearFrom, yearTo);
		if (resultQuery != null) {
			resultQuery.last();
			n = resultQuery.getRow();
			resultQuery.beforeFirst();
			if (n != 0)
				array = new double[n][4];
			if (yearFrom == yearTo)
				resultText = "Raportu zysk�w w roku " + yearFrom + "\n";
			else
				resultText = "Raportu zysk�w w latach od " + yearFrom + " do "
						+ yearTo + "\n";
			while (resultQuery.next()) {

				resultText += "   Rok " + resultQuery.getString(1) + "(" + n
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

	}
}
