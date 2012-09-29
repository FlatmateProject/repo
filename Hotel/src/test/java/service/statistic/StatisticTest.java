package service.statistic;

import static conditions.raport.contain.StringCondition.footerContainLegend;
import static conditions.raport.contain.MonthCondition.headerContainMonth;
import static conditions.raport.contain.IntegerCondition.headerContainYear;
import static conditions.raport.contain.PeriodOfMonthsCondition.headerContainPeriodOfMonths;
import static org.fest.assertions.Assertions.assertThat;

import org.apache.log4j.Logger;
import org.fest.assertions.Condition;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDaoImpl;

public class StatisticTest {

	private static final Logger log = Logger.getLogger(StatisticTest.class);

	@Test(dataProvider = "prepareCasesForHotelRaport")
	public void shouldCreateEmptyHotelRaport(RAPORT_KIND raportKind, int year, MONTH month, String serve, String roomType) {
		// given
		Statistic statistic = new Statistic(new StaticticDaoImpl());

		// when
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serve);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.isNot(footerContainLegend());
		log.info(textRaport);

		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}

	@DataProvider
	public static Object[][] prepareCasesForHotelRaport() {
		Object[][] datas = new Object[][] {//
				{ RAPORT_KIND.HOTEL_ROOM_TYPES, 2012, MONTH.September, null, null },//
				{ RAPORT_KIND.HOTEL_ROOMS, 2012, MONTH.September, null,	"pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE_TYPES, 2012, MONTH.September, null,	"pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE, 2012, MONTH.September,	"rekreacja", null },//
		};
		return datas;
	}

	@Test(dataProvider = "prepareCasesForFinanceMonthRaport")
	public void shouldCreateEmptyFinanceMonthRaport(MONTH monthFrom, MONTH monthTo, Condition<String> monthCondition) {
		// given
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		RAPORT_KIND raportKind = RAPORT_KIND.FINANCE_MONTH;
		int year = 2012;
		
		// when
		StatisticRaport raport = statistic.finance(raportKind, monthFrom, monthTo, year, 0);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(monthCondition)//
				.is(headerContainYear(year))//
				.isNot(footerContainLegend());
		log.info(textRaport);

		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}

	@DataProvider
	public static Object[][] prepareCasesForFinanceMonthRaport() {
		Object[][] datas = new Object[][] {//
				{ MONTH.September, MONTH.December, headerContainPeriodOfMonths(MONTH.September, MONTH.December) },//
				{ MONTH.September, MONTH.September, headerContainMonth(MONTH.September) },//
				{ MONTH.September, MONTH.May, headerContainPeriodOfMonths(MONTH.May, MONTH.September) },//
		};
		return datas;
	}
	
	@Test(dataProvider = "prepareCasesForFinanceYearRaport")
	public void shouldCreateEmptyFinanceYearRaport(int yearFrom, int yearTo) {
		// given
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		RAPORT_KIND raportKind = RAPORT_KIND.FINANCE_YEAR;
		
		// when
		StatisticRaport raport = statistic.finance(raportKind, null, null, yearFrom, yearTo);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainYear(yearFrom))//
				.is(headerContainYear(yearTo))//
				.isNot(footerContainLegend());
		log.info(textRaport);

		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}
	
	@DataProvider
	public static Object[][] prepareCasesForFinanceYearRaport() {
		Object[][] datas = new Object[][] {//
				{ 2012, 2013 },//
				{ 2012, 2012 },//
				{ 2012, 2011 },//
		};
		return datas;
	}
}
