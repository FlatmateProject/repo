package service.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;

@RunWith(value = Parameterized.class)
public class StatisticTest {
	
	private static final Logger log = Logger.getLogger(StatisticTest.class);
	
	private RAPORT_KIND raportKind;
	
	private int year;
	
	private int month;
	
	private String serve;
	
	private String classRoom;
	
	public StatisticTest(RAPORT_KIND raportKind, int year, int month, String serve, String classRoom) {
		this.raportKind = raportKind;
		this.year = year;
		this.month = month;
		this.serve = serve;
		this.classRoom = classRoom;
	}
	
	@Test
	public void shouldReturnEmptyRaport() {
		// given
		Statistic statistic = new Statistic();
		
		// when
		StatisticRaport raport = statistic.hotel(raportKind, month, year, classRoom, serve);

		// then
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(0, arrayResult.length);
	}
	
	@Parameters
	public static List<Object[]> prepareCases() {
		Object[][] datas = new Object[][] {//
				{ RAPORT_KIND.HOTEL_CLASS_ROOM, 2012, 9, null, null },//
				{ RAPORT_KIND.HOTEL_ROOM, 2012, 9, null, "pokój jednosobowy" },//
				{ RAPORT_KIND.HOTEL_SERVICE, 2012, 9, "ok", "pokój jednosobowy" },//
				};
		return Arrays.asList(datas);
	}

}
