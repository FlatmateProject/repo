package service.statictic.executors;

import java.sql.ResultSet;
import java.sql.SQLException;

import service.statictic.RAPORT_KIND;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;

public class FinanceYearRaportCreator extends RaportCreator {

	private double array[][];
	private String resultText;
	
	private int yearFrom;
	
	private int yearTo;
	
	
	@Override
	public void setup(RaportDetails raportDetails) {
		yearFrom = raportDetails.getYearFrom();
		yearTo = raportDetails.getYearTo();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		resultText = "";
		array = null;
		createYearRaport(yearFrom, yearTo);
		StatisticRaport raport = new StatisticRaport(RAPORT_KIND.FINANCE_YEAR, array, resultText);
		return raport;
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
