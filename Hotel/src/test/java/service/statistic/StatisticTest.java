package service.statistic;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDaoImpl;

public class StatisticTest {

	private static final Logger log = Logger.getLogger(StatisticTest.class);

	@Test(dataProvider = "prepareCases")
	public void shouldReturnEmptyRaport(RAPORT_KIND raportKind, int year, MONTH month, String serve, String roomType) {
		// given
		Statistic statistic = new Statistic(new StaticticDaoImpl());

		// when
		StatisticRaport raport = statistic.hotel(raportKind, month, year,
				roomType, serve);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);

		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}

	@DataProvider
	public static Object[][] prepareCases() {
		Object[][] datas = new Object[][] {//
				{ RAPORT_KIND.HOTEL_ROOM_TYPES, 2012, MONTH.September, null,
						null },//
				{ RAPORT_KIND.HOTEL_ROOMS, 2012, MONTH.September, null,
						"pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE_TYPES, 2012, MONTH.September, null,
						"pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE, 2012, MONTH.September,
						"rekreacja", null },//
		};
		return datas;
	}

}
