package service.statictic;

import java.sql.ResultSet;
import java.sql.SQLException;

import service.dictionary.MONTH;
import service.statictic.executors.RaportDetails;
import service.statictic.executors.RaportCreator;
import service.statictic.templates.RaportTemplateBuilder;
import dao.StaticticDao;
import dao.StaticticDaoImpl;

public class Statistic {

	
	private double array[][];
	private String resultText;

	private StaticticDao staticticDao;

	private StatisticRaport raport;
	
	public Statistic() {
		staticticDao = new StaticticDaoImpl();
	}

	public Statistic(StaticticDao staticticDao) {
		this.staticticDao = staticticDao;
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

	public StatisticRaport hotel(RAPORT_KIND raportKind, MONTH month, int year, String classRoom, String serveTypeName){
		try {
			RaportCreator creator = raportKind.getRaportCreator();
			RaportTemplateBuilder temlpateBuilder = raportKind.getRaportTemplateBuilder();
			RaportDetails raportDetails = new RaportDetails(month, year, classRoom, serveTypeName);
			
			creator.setup(raportDetails);
			creator.injectStaticticDao(staticticDao);
			return creator.createRaport(temlpateBuilder);
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}

	private void createMonthRaport(int monthFrom, int monthTo, int year) throws SQLException {
		int n = 0;
		int i = 0;
		if (monthFrom > monthTo) {
			i = monthFrom;
			monthFrom = monthTo;
			monthTo = i;
		}
		i = 0;

		ResultSet resultQuery = staticticDao.createMonthRaport(monthFrom, monthTo, year);
		if (resultQuery != null) {
			resultQuery.last();
			n = resultQuery.getRow();
			resultQuery.beforeFirst();
			if (n != 0)
				array = new double[n][4];
			if (monthFrom == monthTo)
				resultText = "Raportu zysk�w w miesi�cu "+ MONTH.getMonthName(monthFrom) + "\n";
			else
				resultText = "Raportu zysk�w w miesi�cach od "	+ MONTH.getMonthName(monthFrom) + " do "+ MONTH.getMonthName(monthTo) + "\n";
			while (resultQuery.next()) {
				resultText += "   Miesiac "	+ MONTH.getMonthName(resultQuery.getInt(1)) + "(" + i+ ").\n";
				array[i][0] = resultQuery.getDouble(2);
				resultText += "\tzysk z rezerwacji: "+ String.format("%.2f", array[i][0]) + "z�\n";
				array[i][1] = resultQuery.getDouble(3);
				resultText += "\tzysk z uslug: "+ String.format("%.2f", array[i][1]) + "z�\n";
				array[i][2] = resultQuery.getDouble(4);
				resultText += "\tzysk z kantoru: "	+ String.format("%.2f", array[i][2]) + "z�\n";
				array[i][3] = array[i][0] + array[i][1] + array[i][2];
				resultText += "\tzysk sumaryczny: "	+ String.format("%.2f", array[i][3]) + "z�\n";
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

				resultText += "   Rok " + resultQuery.getString(1) + "(" + n+ ").\n";
				array[i][0] = resultQuery.getDouble(2);
				resultText += "\tzysk z rezerwacji: "+ String.format("%.2f", array[i][0]) + "z�\n";
				array[i][1] = resultQuery.getDouble(3);
				resultText += "\tzysk z uslug: "+ String.format("%.2f", array[i][1]) + "z�\n";
				array[i][2] = resultQuery.getDouble(4);
				resultText += "\tzysk z kantoru: "+ String.format("%.2f", array[i][2]) + "z�\n";
				array[i][3] = array[i][0] + array[i][1] + array[i][2];
				resultText += "\tzysk sumaryczny: "	+ String.format("%.2f", array[i][3]) + "z�\n";
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
