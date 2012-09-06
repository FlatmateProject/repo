package service.statistic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;

@RunWith(value = Parameterized.class)
public class StatisticTest {
	
	private static final Logger log = Logger.getLogger(StatisticTest.class);
	
	private RAPORT_KIND raportKind;
	
	private int year;
	
	private MONTH month;
	
	private String serve;
	
	private String roomType;
	
	public StatisticTest(RAPORT_KIND raportKind, int year, MONTH month, String serve, String roomType) {
		this.raportKind = raportKind;
		this.year = year;
		this.month = month;
		this.serve = serve;
		this.roomType = roomType;
	}
	
	@Test
	public void shouldReturnEmptyRaport() {
		// given
		Statistic statistic = new Statistic();
		
		// when
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serve);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}
	
	@Parameters
	public static List<Object[]> prepareCases() {
		Object[][] datas = new Object[][] {//
				{ RAPORT_KIND.HOTEL_ROOM_TYPES, 2012, MONTH.September, null, null },//
				{ RAPORT_KIND.HOTEL_ROOMS, 2012, MONTH.September, null, "pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE_TYPES, 2012, MONTH.September, null, "pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE, 2012, MONTH.September, "rekreacja", null },//
				};
		return Arrays.asList(datas);
	}

}
